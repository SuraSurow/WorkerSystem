import Controller.Application;


import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Application apk = new Application();
        apk.run(scanner);
        scanner.close();

    }
}