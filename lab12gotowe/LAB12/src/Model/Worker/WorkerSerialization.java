package Model.Worker;


import Service.FileService;
import Service.ObjectFileService;

import java.io.File;
import java.util.Scanner;

public class WorkerSerialization {


    private WorkerDataBase<Worker> dataBase;

    private String fileName;

    private String path;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public WorkerSerialization(WorkerDataBase dataBase)
    {
        this.dataBase=dataBase;
        this.fileName=FileService.getCommonNameFile();
        this.path = FileService.getPathCommonFile();
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
                //exportDateToZip(scanner);
                exportDateToZip();
                break;
            } else if (choice.equals("G")) {
                //exportDateToGzip(scanner);
                exportDateToGzip();
                break;
            }
        }
    }



    private void importDate (Scanner scanner )
    {
        System.out.println("============================\nWybrano Zaimportuj Dane ");
        System.out.println("Kompresja : [Z]ip / [G]zip ? ");
        String choice = scanner.nextLine().toUpperCase();
        while(true)
        {
            if ( choice.equals("Z"))
            {
                //ImportDateFromZip(scanner);
                ImportDateFromZip();
                break;
            }
            else if ( choice.equals("G"))
            {
                //ImportDateFromGzip(scanner);
                ImportDateFromGzip();
                break;
            }
        }
    }


    private void ImportDateFromGzip() {
        ObjectFileService<Worker> palabellumService = new ObjectFileService<>();
        palabellumService.importDataFromGzip(dataBase);
    }
private void ImportDateFromZip() {

    ObjectFileService<Worker> palabellumService = new ObjectFileService<>();
    palabellumService.importDataFromZip(dataBase);
    //dataBase.setWorkerByPesel(FileService.deserializeZip(path,fileName+".zip"));
}
private void exportDateToZip()
{
    ObjectFileService<Worker>palabellumService = new ObjectFileService<>();
    palabellumService.exportDataToZip(dataBase);

}
private void exportDateToGzip()
{
    ObjectFileService<Worker>palabellumService = new ObjectFileService<>();
    palabellumService.exportDataToGzip(dataBase);
}


}
