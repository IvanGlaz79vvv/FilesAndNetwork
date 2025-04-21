package work;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputClass {
    Scanner scanner = new Scanner(System.in);
    int index = 0;


    //    INPUT
    public String[] arrFromInput() {
        String[] arr = new String[3];
        String input = scanner.nextLine();
        if(input.equals("REPORT")) {
            UserMenu userMenu = new UserMenu();
            userMenu.getReport();
        }
        arr = input.split("; ");

        return arr;
    }
}


