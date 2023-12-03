package CONTROLLER;



import MODEL.Worker.Worker;
import MODEL.Worker.WorkerDataBase;
import MODEL.Worker.WorkerDelete;
import MODEL.Worker.WorkerList;

import java.util.Scanner;

public class Menu<T extends Worker> {
    private WorkerDataBase<Worker> dataBAse;

    public Menu(WorkerDataBase input) {
        this.dataBAse = input;

    }

    public void startMenu(Scanner scanner) {
        System.out.println("MENU\n" +
                "\t1. Lista pracowników\n" +
                "\t2. Dodaj pracownika\n" +
                "\t3. Usuń pracownika\n" +
                "\t4. Kopia zapasowa\n" +
                "\t5. Wyjście");

        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                WorkerList<Worker> workerList = new WorkerList(dataBAse);
                workerList.showMenu(scanner);
                break;
            case 2:
                AddWorker adderWorker = new AddWorker(dataBAse);
                adderWorker.addWorker(scanner);
                break;
            case 3:
                WorkerDelete destroyer = new WorkerDelete(dataBAse);
                destroyer.deleteRecord(scanner);
                break;
            case 4:
                // Tutaj możesz dodać obsługę kopiowania zapasowego
                break;
            case 5:
                System.out.println("Wyjście z programu");
                break;
            default:
                System.out.println("Nieprawidłowy wybór.");
        }
    }
}



