package Controller;



import Model.Worker.*;
import Service.DataInputHandler;
import View.List;

import java.util.Scanner;

public class Menu<T extends Worker> {
    private DataBase<Worker> dataBAse;

    public Menu(DataBase input) {
        this.dataBAse = input;

    }

    public boolean startMenu(Scanner scanner ) {

        View.View.showMenu();
        int choice = scanner.nextInt();
        scanner.nextLine();
        DataInputHandler inputHandler = new DataInputHandler(scanner);
        switch (choice) {
            case 1:
                List workerList = new List(dataBAse,inputHandler);
                workerList.showMenu();
                return true;
            case 2:
                AddObj adderWorker = new AddObj(dataBAse,inputHandler);
                adderWorker.addWorker();
                return true;
            case 3:
                Delete destroyer = new Delete(dataBAse,inputHandler);
                destroyer.deleteRecord();
                return true;
            case 4:
                Serialization backup = new Serialization(dataBAse,inputHandler);
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



