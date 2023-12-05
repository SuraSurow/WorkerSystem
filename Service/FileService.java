package Service;

import Model.Worker.Worker;
import Model.Worker.WorkerDataBase;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.*;

public class FileService<T extends Worker> {
    private WorkerDataBase<T> DataBase;
    private String path;

    public FileService(String nameFile, WorkerDataBase<T> dataBase) {
        this.DataBase = dataBase;
        this.path = nameFile;
    }


    public static void serializeToFile(WorkerDataBase dataBase,String fileName)
    {
        serializeToFile(dataBase.getWorkerByPesel(),fileName);
    }

    private static void serializeToFile(Object obj, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(obj);
            System.out.println("Serialized data written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean createZip(String soruceFileName, String zipFileName)
    {
        try {
            createZipArchive(soruceFileName, zipFileName);
            return  true;
        }
        catch (Exception e) {

            return  false;}
    }
    public static boolean createGzip(String sourceFileName, String gzipFileName) {
        try {
            createGzipArchive(sourceFileName, gzipFileName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    private static void createZipArchive(String sourceFileName, String zipFileName) {
        try (FileInputStream fis = new FileInputStream(sourceFileName);
             FileOutputStream fos = new FileOutputStream(zipFileName);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            ZipEntry zipEntry = new ZipEntry(sourceFileName);
            zos.putNextEntry(zipEntry);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, bytesRead);
            }

            System.out.println("Created ZIP archive: " + zipFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void createGzipArchive(String sourceFileName, String gzipFileName) {
        try (FileInputStream fis = new FileInputStream(sourceFileName);
             FileOutputStream fos = new FileOutputStream(gzipFileName);
             GZIPOutputStream gzos = new GZIPOutputStream(fos)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) > 0) {
                gzos.write(buffer, 0, bytesRead);
            }

            System.out.println("Created GZIP archive: " + gzipFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Worker> deserializeZip(String zipFileName)
    {
        return deserializeFromZip(zipFileName);
    }
    public static Map<String, Worker> deserializeGzip(String zipFileName)
    {
        return deserializeFromGzip(zipFileName);
    }
    private static Map<String, Worker> deserializeFromZip(String zipFileName) {
        Map<String, Worker> deserializedMap = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(zipFileName);
             ZipInputStream zis = new ZipInputStream(fis)) {

            ZipEntry zipEntry = zis.getNextEntry();

            try (ObjectInputStream ois = new ObjectInputStream(zis)) {
                Object obj = ois.readObject();
                if (obj instanceof Map) {
                    deserializedMap = (Map<String, Worker>) obj;
                    System.out.println("Deserialized data from ZIP archive: " + zipFileName);
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deserializedMap;
    }
    private static Map<String, Worker> deserializeFromGzip(String gzipFileName) {
        Map<String, Worker> deserializedMap = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(gzipFileName);
             GZIPInputStream gzis = new GZIPInputStream(fis);
             ObjectInputStream ois = new ObjectInputStream(gzis)) {

            Object obj = ois.readObject();
            if (obj instanceof Map) {
                deserializedMap = (Map<String, Worker>) obj;
                System.out.println("Deserialized data from GZIP archive: " + gzipFileName);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deserializedMap;
    }

}
