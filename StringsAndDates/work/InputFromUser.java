package work;

import java.util.Scanner;

public class InputFromUser {
    Scanner scanner = new Scanner(System.in);
    static int index = 0;

    public String[] arrFromInput() {
        String input = scanner.nextLine();
        String[] arr = input.split("; ");
        return arr;
    }
}


