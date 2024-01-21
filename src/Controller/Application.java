package Controller;




import Model.Worker.Worker;

import java.util.Scanner;



public class Application {
    public void run(Scanner scanner)
    {
        System.out.println("\n\nUruchomiono Program\n");
        DataBase<Worker> bazaDanych;
        bazaDanych = new DataBase<>();
        Menu<Worker> menu = new Menu(bazaDanych);
        while(true)
        {
            if ( !menu.startMenu(scanner) ) break;
        }
        System.out.println("\nZakonczono dzialanie programu\n");
    }
}
