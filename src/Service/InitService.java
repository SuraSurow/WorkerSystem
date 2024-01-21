package Service;

import Model.Worker.Worker;
import Controller.DataBase;

public class InitService {
    public static boolean PeselChecker(String personalId) {
        boolean isValid = true;

        if (personalId.length() != 11) {
            isValid = false;
        }

        int[] weights = new int[]{1, 3, 7, 9, 1, 3, 7, 9, 1, 3, 1};
        int sum = 0;

        try {
            for (int i = 0; i < personalId.length(); i++) {
                sum +=  Integer.valueOf(String.valueOf(personalId.charAt(i))) * weights[i];
            }
            if (sum % 10 != 0) {
                isValid = false;
            }
        } catch (ArrayIndexOutOfBoundsException exc) {
            System.err.println("Error: " + exc.getMessage());
        }

        return isValid;
    }

    public static boolean exceptionalPesel(String Pesel , DataBase<Worker> dataBase)
    {
        String[] arrayKey = dataBase.getAllPesels().toArray(new String[0]);
        for ( int i = 0 ; i < arrayKey.length ;i++)
        {
            if ( arrayKey[i].equals(Pesel))
            {
                return false;
            }
        }
        return true;
    }
}
