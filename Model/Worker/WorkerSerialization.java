package Model.Worker;


import Service.CurrentDirectory;
import Service.FileService;

import java.util.Scanner;

public class WorkerSerialization {


    private WorkerDataBase<Worker> dataBase;

    private String fileName;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public WorkerSerialization(WorkerDataBase dataBase)
    {
        this.dataBase=dataBase;
        this.fileName="data";
    }
    public WorkerSerialization(WorkerDataBase dataBase,String name)
    {
        this.dataBase=dataBase;
        this.fileName=name;
    }

    public void backup(Scanner scanner)
    {
        System.out.println("KOPIA ZAPASOWA");
        System.out.println("\n[Z]achowaj/[O]dtwórz dane ? ");
        String choice = scanner.nextLine().toUpperCase();
        while (true)
        {
            if (choice.equals("Z"))
            {
                exportDate(scanner);
                break;
            }
            else if (choice.equals("O")) {
                importDate(scanner);
                break;
            }
            else
                {
                    System.out.println("\nBlad , Wybierz Ponownie !!!");
                    continue;
                }
        }
    }

    private void exportDate(Scanner scanner ) {
        System.out.println("============================\nWybrano Zachowaj Dane ");
        System.out.println("Kompresja : [Z]ip / [G]zip ? ");
        String choice = scanner.nextLine().toUpperCase();
        while (true) {
            if (choice.equals("Z")) {
                exportDateToZip(scanner);
                break;
            } else if (choice.equals("G")) {
                exportDateToGzip(scanner);
                break;
            }
        }
    }



    private void importDate (Scanner scanner )
    {
        System.out.println("============================\nWybrano Zaimportuj Dane ");
        System.out.println("Kompresja : [Z]ip / [G]zip ? ");
        String choice = scanner.nextLine().toUpperCase();
        if ( choice.equals("Z"))
        {
            ImportDateFromZip(scanner);
        }
        else if ( choice.equals("G"))
        {
            ImportDateFromGzip(scanner);
        }
    }

    private void ImportDateFromGzip(Scanner scanner) {
        System.out.println("============================\nWybrano ZIP");
        System.out.println("Zmieniamy Nazwe z domyślnej 'data.txt/ser/zip/gzip' [Y]es / [N]o   ? ");
        String choice = scanner.nextLine().toUpperCase();
        if ( choice.equals("Y"))
        {
            System.out.println("Wpisz nazwe plikow : ");
            String name = scanner.nextLine();
            setFileName(name);
        }
        dataBase.setWorkerByPesel(FileService.deserializeGzip(fileName+".gzip"));
    }

    private void ImportDateFromZip(Scanner scanner) {
        System.out.println("============================\nWybrano GZIP");
        System.out.println("Zmieniamy Nazwe z domyślnej 'data.txt/ser/zip/gzip' [Y]es / [N]o   ? ");
        String choice = scanner.nextLine().toUpperCase();
        if ( choice.equals("Y"))
        {
            System.out.println("Wpisz nazwe plikow : ");
            String name = scanner.nextLine();
            setFileName(name);
        }
        dataBase.setWorkerByPesel(FileService.deserializeZip(fileName+".zip"));
    }


    private void exportDateToZip(Scanner scanner )
    {
        System.out.println("============================\nWybrano ZIP");
        System.out.println("Zmieniamy Nazwe z domyślnej 'data.txt/ser/zip/gzip' [Y]es / [N]o   ? ");
        String choice = scanner.nextLine().toUpperCase();
        if ( choice.equals("Y"))
        {
            System.out.println("Wpisz nazwe plikow : ");
            String name = scanner.nextLine();
            setFileName(name);
        }
        FileService.serializeToFile(dataBase,fileName+".ser");
        FileService.createZip(fileName+".ser",fileName+".zip");
    }
    private void exportDateToGzip(Scanner scanner) {
        System.out.println("============================\nWybrano ZIP");
        System.out.println("Zmieniamy Nazwe z domyślnej 'data.txt/ser/zip/gzip' [Y]es / [N]o   ? ");
        String choice = scanner.nextLine().toUpperCase();
        if ( choice.equals("Y"))
        {
            System.out.println("Wpisz nazwe plikow : ");
            setFileName(scanner.nextLine());
            scanner.nextLine();
        }
        FileService.serializeToFile(dataBase,fileName+".ser");
        FileService.createGzip(fileName+".ser",fileName+".gzip");
    }



}
