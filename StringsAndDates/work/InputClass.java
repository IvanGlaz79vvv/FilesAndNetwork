package work;

import java.util.Scanner;

public class InputClass {
    Scanner scanner = new Scanner(System.in);
    int index = 0;


    //    INPUT
    public String[] arrFromInput() {
        String[] arr = new String[4];
        String input = scanner.nextLine();
        arr = input.split(";");

        return arr;
    }
}


