package Service;

import java.util.Scanner;

public class DataInputHandler {
    private Scanner scanner;

    public DataInputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public String getUserInputUpperCase() {
        return getUserInput().toUpperCase();
    }
}