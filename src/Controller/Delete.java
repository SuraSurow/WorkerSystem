package Controller;

import Model.Worker.Worker;
import Service.DataInputHandler;
import View.View;

public class Delete {
    private DataBase<Worker> dataBase;
    private DataInputHandler inputHandler;

    public Delete(DataBase<Worker> dataBase , DataInputHandler inputHandler)
    {
        this.dataBase = dataBase;
        this.inputHandler=inputHandler;
    }

    public boolean deleteRecord()
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
            String choice = inputHandler.getUserInput();
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
