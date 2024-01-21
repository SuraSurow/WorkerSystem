import Controller.ApplicationController;


import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ApplicationController apk = new ApplicationController();
        apk.run(scanner);
        scanner.close();

    }
}