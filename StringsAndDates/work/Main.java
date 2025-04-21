package work;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
//FinancialAccounting financialAccounting = new FinancialAccounting("Билет на Марс", 2499.99, UserMenu.EXPENSE, 24.03.2036);
        /*
        Билет на Марс; 2499.99; EXPENSE; 24.03.2036
        Оплата телепорта; 400.00; EXPENSE; 24.03.2036
        Создание нейромодуля; 2500; INCOME; 25.03.2036
        Билет на Марс; 2599.99; EXPENSE; 25.03.2036
        Премия; 10000; INCOME; 25.03.2036
        Скин на цифрового аватара; 3900; EXPENSE; 26.03.2036
        Доставка наноеды; 745.89; EXPENSE; 27.03.2036
        */

        InputFromUser inputFromUser = new InputFromUser();
        String[] mainArr = inputFromUser.arrFromInput();
//        System.out.println(mainArr);
//        System.out.println(mainArr.length);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        FinancialAccounting[] arrFinancialAccounting = new FinancialAccounting[3];
        FinancialAccounting financialAccounting = new FinancialAccounting(mainArr[0].strip(), new BigDecimal(mainArr[1]), Enum.valueOf(UserMenu.class, mainArr[2].toUpperCase()), LocalDate.parse(mainArr[3], formatter));

        int index = InputFromUser.index;
        System.out.println("InputFromUser.index: " + InputFromUser.index);
        arrFinancialAccounting[InputFromUser.index] = financialAccounting;
        InputFromUser.index++;
        System.out.println("InputFromUser.index: " + InputFromUser.index);

        System.out.println(arrFinancialAccounting[index]);

        mainArr = inputFromUser.arrFromInput();



    }
}
