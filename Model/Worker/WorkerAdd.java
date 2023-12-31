package Model.Worker;

import Model.Inherited.Director;
import Model.Inherited.Trader;
import Service.InitService;
import View.View;
import Service.UniversalSetterField;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkerAdd {

    private WorkerDataBase<Worker> dataBase;

    public WorkerAdd(WorkerDataBase<Worker> dataBase) {
        this.dataBase = dataBase;
    }

    public void addWorker(Scanner scanner) {

        System.out.println(" Dodawanie Pracownika");

        Worker newWorker = null;


        while (newWorker == null) {
            System.out.println("\n\nWybierz Kategorie\n\n[D]yrektor/[H]handlowiec: ");

            String category = scanner.nextLine().toUpperCase();

            if (category.equals("D")) {
                newWorker =  new Director();
            } else if (category.equals("H")) {
                newWorker =  new Trader();
            } else {
                System.out.println("Nieprawidłowa kategoria pracownika. Wybierz ponownie.");
            }
        }

        View view = new View();
        view.show(newWorker);
        ArrayList<String> basicFields = newWorker.getBasicFieldName();
        ArrayList<String> specificFields = new ArrayList<>();
        if ( newWorker instanceof Director)
        {
            specificFields = Director.getSpecificFieldName();
        }
        else if ( newWorker instanceof  Trader)
        {
            specificFields = Trader.getSpecificFieldName();
        }

        List<String> combinedFields = new ArrayList<>(basicFields);
        combinedFields.addAll(specificFields);

        System.out.println("\nProszę o oddzielanie danych za pomoca znaku '_' tzw podloga !!!");
        for(int i = 0 ; i < combinedFields.size() ; i++)
        {
            boolean success = true;
            do {
                String fieldName = combinedFields.get(i);
                System.out.print("wpisz "+ fieldName +" = ");
                String input = scanner.next();
                success = UniversalSetterField.setField(newWorker,fieldName,input);
                if ( fieldName == "Pesel")
                {
                    if ( ! InitService.PeselChecker(input) )
                    {
                        System.out.println("\nWpisano bledny Pesel ( Syntax ) !!!");
                        success = false;
                        continue;
                    }
                    if ( ! InitService.exceptionalPesel(input,dataBase) )
                    {
                        System.out.println("\nWpisano Pesel ktory znajduje sie w Systemie !!!");
                        success = false;
                        continue;
                    }
                }

            }while(!success);
        }

        dataBase.addWorker(newWorker);

    }
}

