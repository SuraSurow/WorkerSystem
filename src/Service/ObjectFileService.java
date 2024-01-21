package Service;

import Model.Worker.Worker;
import Controller.DataBase;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.zip.*;

public class ObjectFileService<T extends Worker> {
    private final String pathToDirectory = "src" + File.separator + "BackUp";

    public static void cleanDirectory(String directoryPath) {
        Path directory = Paths.get(directoryPath);

        try {
            Files.walkFileTree(directory, EnumSet.noneOf(FileVisitOption.class), Integer.MAX_VALUE, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("Błąd podczas czyszczenia katalogu", e);
        }
    }

    private int countGzipFiles(File directory) {
        File[] gzipFiles = directory.listFiles((dir, name) -> name.endsWith(".gz"));
        return gzipFiles != null ? gzipFiles.length : 0;
    }

    private int countZipFiles(File directory) {
        File[] zipFiles = directory.listFiles((dir, name) -> name.endsWith(".zip"));
        return zipFiles != null ? zipFiles.length : 0;
    }

    public void importDataFromGzip(DataBase<T> dataBase) {
        System.out.println("============================\nWybrano GZIP");
        File backupDirectory = new File(pathToDirectory);

        int numberOfGzipFiles = countGzipFiles(backupDirectory);
        System.out.println("Liczba plików GZIP: " + numberOfGzipFiles);

        ExecutorService executor = Executors.newFixedThreadPool(10);
        CompletableFuture<Void>[] futures = new CompletableFuture[numberOfGzipFiles];

        Set<String> pesels = dataBase.getAllPesels();
        List<String> peselList = new ArrayList<>(pesels);
        int name = 1;
        for (int i = 0; i < numberOfGzipFiles; i++) {
            final String nameFile = Integer.toString(name++);
            String compressedFileName = pathToDirectory + File.separator + nameFile+ ".gz";
            int finalName = name;
            futures[i] = CompletableFuture.runAsync(() -> {
                Worker worker = deserializeWorkerFromGzip(compressedFileName);
                if (worker != null) {
                    System.out.println("Employee No. " + Integer.toString(finalName) + ": " + worker.getName());
                    dataBase.addWorker( (T) worker);
                }
            }, executor);
        }
        CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(futures);
        try {
            allOfFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void importDataFromZip(DataBase<T> dataBase) {
        System.out.println("============================\nWybrano ZIP");
        File backupDirectory = new File(pathToDirectory);
        int numberOfZipFiles = countZipFiles(backupDirectory);
        System.out.println("Liczba plików ZIP: " + numberOfZipFiles);

        ExecutorService executor = Executors.newFixedThreadPool(10);
        CompletableFuture[] futures = new CompletableFuture[numberOfZipFiles];

        int name = 1;
        for (int i = 0; i < numberOfZipFiles; i++, name++) {
            final String nameFile = Integer.toString(name);
            String compressedFileName = pathToDirectory + File.separator + nameFile + ".zip";
            final int finalName = name;
            futures[i] = CompletableFuture.runAsync(() -> {
                try {
                    Worker worker = deserializeWorkerFromZip(compressedFileName);
                    if (worker != null) {
                        System.out.println("Employee No. " + finalName + ": " + worker.getName());
                        dataBase.addWorker((T) worker);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, executor);
        }

        CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(futures);
        try {
            allOfFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }


    private static Worker deserializeWorkerFromZip(String zipFileName) {
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFileName))) {
            ZipEntry entry = zipInputStream.getNextEntry();
            if (entry != null) {
                try (ObjectInputStream objectInputStream = new ObjectInputStream(zipInputStream)) {
                    Worker worker = (Worker) objectInputStream.readObject();
                    return worker;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    private static Worker deserializeWorkerFromGzip(String gzipFileName) {
        try (GZIPInputStream gzipInputStream = new GZIPInputStream(new FileInputStream(gzipFileName))) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(gzipInputStream)) {
                Worker worker = (Worker) objectInputStream.readObject();
                return worker;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
//--------------------------------------------------------------------
    public void exportDataToGzip(DataBase<T> dataBase) {
        System.out.println("============================\nWybrano GZIP");
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CompletableFuture<Void>[] futures = new CompletableFuture[dataBase.getSize()];
        Set<String> pesels = dataBase.getAllPesels();
        List<String> peselList = new ArrayList<>(pesels);
        int name = 1;
        for (int i = 0; i < peselList.size(); i++) {
            final String nameFile  = Integer.toString(name++);
            Worker worker = dataBase.getWorkerByPesel(peselList.get(i));
            futures[i] = CompletableFuture.runAsync(() -> {
                serializeWorkerToGzip(worker, nameFile);
            }, executor);
        }
        CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(futures);
        try {
            allOfFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
            try {
                executor.awaitTermination(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }//

    private void serializeWorkerToGzip(Worker worker, String nameFile) {
        if (worker != null) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new GZIPOutputStream(new FileOutputStream(getGzipFileName(nameFile))))) {
                objectOutputStream.writeObject(worker);
                System.out.println("Serialized worker to GZIP file: " + getGzipFileName(nameFile));
            } catch (IOException e) {
                throw new RuntimeException("Błąd podczas zapisu do pliku .gz", e);
            }
        } else {
            System.out.println("Received null worker, skipping serialization for PESEL " + nameFile);
        }
    }

    private String getGzipFileName(String pesel) {
        return pathToDirectory + File.separator + pesel + ".gz";
    }
//---------------------------------------------------------------------
    public void exportDataToZip(DataBase<T> dataBase) {
        System.out.println("============================\nWybrano ZIP");
        ExecutorService executor = Executors.newFixedThreadPool(10);

        CompletableFuture<Void>[] futures = new CompletableFuture[dataBase.getSize()];

        Set<String> pesels = dataBase.getAllPesels();
        List<String> peselList = new ArrayList<>(pesels);
        int name = 1;
        for (int i = 0; i < peselList.size(); i++) {
            final String nameFile  = Integer.toString(name++);
            Worker worker = dataBase.getWorkerByPesel(peselList.get(i) );

            futures[i] = CompletableFuture.runAsync(() -> {
                serializeWorkerToZip(worker, nameFile );
            }, executor);
        }

        CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(futures);

        try {
            allOfFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
            try {
                executor.awaitTermination(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void serializeWorkerToZip(Worker worker, String fileName) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(getSerFileName(fileName)))) {
            objectOutputStream.writeObject(worker);
            createZipFromSer(getSerFileName(fileName), fileName);
        } catch (IOException e) {
            throw new RuntimeException("Błąd podczas zapisu do pliku .zip", e);
        }
    }

    private String getSerFileName(String pesel) {
        return pathToDirectory + File.separator + pesel + ".ser";
    }

    private void createZipFromSer(String serFileName, String pesel) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(serFileName));
             ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(getZipFileName(pesel))))) {

            ZipEntry zipEntry = new ZipEntry("worker.ser");
            zipOutputStream.putNextEntry(zipEntry);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = bis.read(buffer)) > 0) {
                zipOutputStream.write(buffer, 0, length);
            }

            zipOutputStream.closeEntry();
        } catch (IOException e) {
            throw new RuntimeException("Błąd podczas tworzenia pliku .zip", e);
        }
    }

    private String getZipFileName(String pesel) {
        return pathToDirectory + File.separator + pesel + ".zip";
    }
}
