package Controller;

import Model.Worker.Worker;
import Service.DataInputHandler;
import View.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WorkerList {
    private WorkerDataBase dataBase;

    private DataInputHandler inputHandler;
    public WorkerList(WorkerDataBase base , DataInputHandler inputHandler)
    {
        this.dataBase = base;
        this.inputHandler = inputHandler;

    }

    public void showMenu() {

        View showman = new View();
        System.out.println("\nLista Pracownikow\n\n");
        if (dataBase.getSize() == 0)
        {
            System.out.println("\nLISTA PUSTA!!!\n");
            return;
        }
        Set<String> pesels = dataBase.getAllPesels();
        List<String> peselList = new ArrayList<>(pesels);
        boolean run = true;
        int position = 0;
        while (run && position < peselList.size()) {
            String pesel = peselList.get(position);
            Worker worker = dataBase.getWorkerByPesel(pesel);
            showman.show(worker);
            System.out.println("\n\t\t\t\tPozycja "+ (position+1) +" / " + peselList.size());
            System.out.println("\nN - Następny | P - Poprzedni | Q - Wyjście");
            String choice = inputHandler.getUserInputUpperCase();

            switch (choice) {
                case "N":
                    position = (position + 1) % peselList.size();
                    break;
                case "P":
                    position = (position - 1 + peselList.size()) % peselList.size();
                    break;
                case "Q":
                    run = false;
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór.");
            }
        }
    }
}
