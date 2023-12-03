package MODEL.Worker;

import VIEW.View;


import java.util.Scanner;

public class WorkerDelete {
    private WorkerDataBase<Worker> dataBase;

    public WorkerDelete(WorkerDataBase<Worker> dataBase)
    {
        this.dataBase = dataBase;
    }

    public boolean deleteRecord(Scanner scanner)
    {
        System.out.println(" Usuwanie Pracownika ");
        Worker deleteWorker  = null;
        View showMan = new View();
        int position = 0;
        String[] keyArray = dataBase.getAllPesels().toArray(new String[0]);
        while( true ){
            if (dataBase.isEmpty())
            {
                System.out.println("\nBRAK PRACOWNIKOW DO USUNIECIA !!!\n\nWychodzenie\n");
                return false;
            }
            System.out.println("\nWybierz Pracownika do Usuniecia");
            deleteWorker =dataBase.getWorkerByPesel(keyArray[position]);
            showMan.show(deleteWorker);
            System.out.println("\n\t\t\t\tPozycja "+ (position+1) +" / " + dataBase.getSize());
            System.out.println("\nN - Następny | P - Poprzedni | D - Usun | Q - Wyjście ");
            String choice = scanner.nextLine();
            choice.toUpperCase();
            switch (choice) {
                case "N": {
                    position = (position + 1) % dataBase.getSize();
                    continue;
                }
                case "P": {
                    position = (position - 1 + dataBase.getSize()) % dataBase.getSize();
                    continue;
                }
                case "D": {
                    dataBase.deleteWorker(deleteWorker);
                    System.out.println("\nUsunieto Pomyslnie Pracownika");
                    return true;
                }
                case "Q": {
                    System.out.println("Wyjscie z Trybu Usuwania");
                    return true;
                }
                default:{
                    System.out.println("Nieprawidłowy wybór.");
                    continue;
                }
            }
        }
    }
}
