package Controller;



import Model.Worker.*;
import Service.CurrentDirectory;
import Service.FileService;
import Service.InitService;

import java.util.Scanner;

public class Menu<T extends Worker> {
    private WorkerDataBase<Worker> dataBAse;

    public Menu(WorkerDataBase input) {
        this.dataBAse = input;

    }

    public boolean startMenu(Scanner scanner ) {

        View.View.showMenu();
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                WorkerList workerList = new WorkerList(dataBAse);
                workerList.showMenu(scanner);
                return true;
            case 2:
                WorkerAdd adderWorker = new WorkerAdd(dataBAse);
                adderWorker.addWorker(scanner);
                return true;
            case 3:
                WorkerDelete destroyer = new WorkerDelete(dataBAse);
                destroyer.deleteRecord(scanner);
                return true;
            case 4:
                WorkerSerialization backup = new WorkerSerialization(dataBAse);
                backup.backup(scanner);
                return true;
            case 5:
                System.out.println("Wyjście z programu");
                return false;
            default:
                System.out.println("Nieprawidłowy wybór.");
                return  true;

        }
    }
}



