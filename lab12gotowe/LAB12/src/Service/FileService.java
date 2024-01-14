package Service;

import Model.Worker.Worker;
import Model.Worker.WorkerDataBase;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.*;

public class FileService<T extends Worker> {
    private WorkerDataBase<T> DataBase;
    private String nameFile;
    private String path;


    public FileService(String nameFile,String path, WorkerDataBase<T> dataBase) {
        this.DataBase = dataBase;
        this.nameFile = nameFile;
        this.path = path;
    }

    public static String getCommonNameFile()
    {
        return "MyData";
    }
    public static String getPathCommonFile()
    {
        return CurrentDirectory.getCurrentDirectory()+"\\";
    }




    public static void serializeToFile(WorkerDataBase dataBase,String path,String fileName)
    {
        serializeToFile(dataBase.getWorkerByPesel(),path+fileName);
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

    public static Map<String, Worker> deserializeZip(String path,String zipFileName)
    {
        return deserializeFromZip(path+zipFileName);
    }
    public static Map<String, Worker> deserializeGzip(String path,String zipFileName)
    {
        return deserializeFromGzip(path+zipFileName);
    }
    private static Map<String, Worker> deserializeFromZip(String zipFileName) {
        try {
            //TU
            Map<String, Worker> deserializedMap = new HashMap<>();
            File file = new File(zipFileName);
            FileInputStream fis = new FileInputStream(file);
            ZipInputStream zipInputStream = new ZipInputStream(fis);
            zipInputStream.getNextEntry();
            ObjectInputStream objectInputStream = new ObjectInputStream(zipInputStream);
            deserializedMap = (Map<String,Worker>)objectInputStream.readObject();
            zipInputStream.closeEntry();
            zipInputStream.close();
            objectInputStream.close();

            return deserializedMap;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    private static Map<String, Worker> deserializeFromGzip(String gzipFileName) {
        try {
            Map<String, Worker> deserializedMap;
            FileInputStream fis = new FileInputStream(gzipFileName);
            GZIPInputStream gzipInputStream = new GZIPInputStream(fis);
            ObjectInputStream objectInputStream = new ObjectInputStream(gzipInputStream);
            deserializedMap = (Map<String, Worker>) objectInputStream.readObject();
            objectInputStream.close();
            gzipInputStream.close();

            return deserializedMap;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
