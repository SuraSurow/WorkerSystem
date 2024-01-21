package Controller;



import Model.Worker.*;
import Service.DataInputHandler;

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
        DataInputHandler inputHandler = new DataInputHandler(scanner);
        switch (choice) {
            case 1:
                WorkerList workerList = new WorkerList(dataBAse,inputHandler);
                workerList.showMenu();
                return true;
            case 2:
                WorkerAdd adderWorker = new WorkerAdd(dataBAse,inputHandler);
                adderWorker.addWorker();
                return true;
            case 3:
                WorkerDelete destroyer = new WorkerDelete(dataBAse,inputHandler);
                destroyer.deleteRecord();
                return true;
            case 4:
                WorkerSerialization backup = new WorkerSerialization(dataBAse,inputHandler);
                backup.backup();
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



