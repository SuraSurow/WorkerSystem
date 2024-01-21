package Controller;




import Model.Worker.Worker;

import java.util.Scanner;



public class ApplicationController {
    public void run(Scanner scanner)
    {
        System.out.println("\n\nUruchomiono Program\n");
        WorkerDataBase<Worker> bazaDanych;
        bazaDanych = new WorkerDataBase<>();
        Menu<Worker> menu = new Menu(bazaDanych);
        while(true)
        {
            if ( !menu.startMenu(scanner) ) break;
        }
        System.out.println("\nZakonczono dzialanie programu\n");
    }
}
