package CONTROLLER;




import MODEL.Worker.Worker;
import MODEL.Worker.WorkerDataBase;

import java.util.Scanner;

public class ApplicationController {
    public void run(Scanner scanner)
    {

        System.out.println("Uruchomiono Program\n");
        boolean run = true;
        WorkerDataBase<Worker> bazaDanych;
        bazaDanych = new WorkerDataBase<>();
        Menu<Worker> menu = new Menu(bazaDanych);
        while(run)
        {
            menu.startMenu(scanner);

        }

        System.out.println("\nZakonczono dzialanie programu\n");
    }

}
