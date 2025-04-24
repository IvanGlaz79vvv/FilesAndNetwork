package work;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class UserMenu {
//    HELP(1), // — вывести инструкцию по использованию приложения. Инструкция должна содержать список доступных команд и пример строки для ввода новой транзакции.
//    REPORT(2),  /* — вывести отчёт о финансах.*/
//    EXIT(3), //— вывести отчёт и завершить работу программы.
//    EXPENSE, //расход
//    INCOME; //доход

//    UserMenu(int code) {
//        this.code = code;
//    }

//    public int getCode() {
//        return code;
//    }

    int index = 0;
    BigDecimal EXPENSE = BigDecimal.ZERO;
    BigDecimal INCOME = BigDecimal.ZERO;
//    FinancialAccounting financialAccounting = null;

    public UserMenu() {
    }

    public void inFromUser() {
//        Scanner scanner = new Scanner(System.in);
        InputClass inputClass = new InputClass();
        String[] mainArr = new String[4];
        mainArr = inputClass.arrFromInput();
        System.out.println("mainArr.length: " + mainArr.length);
        System.out.println("mainArr[0]: " + mainArr[0]);
//        System.out.println("mainArr[1]: " + mainArr[1]);
//        System.out.println("mainArr[2]: " + mainArr[2]);
//        System.out.println("mainArr[3]: " + mainArr[3]);
//        if (input.toUpperCase().equals("REPORT")) {
//            UserMenu userMenu = new UserMenu();
//            userMenu.getReport();
//        }


//        String[] mainArr = new String[4];
//        mainArr = inputClass.arrFromInput();
//        String input = scanner.nextLine();
//        mainArr = input.split("; ");


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        if (mainArr.length > 1) {
            FinancialAccounting financialAccounting =
                    new FinancialAccounting(mainArr[0].strip(),
                            new BigDecimal(mainArr[1]),
                            mainArr[2].toUpperCase(),
                            LocalDate.parse(mainArr[3],
                                    formatter));


            financialAccounting.arrFinancialAccounting[index] = financialAccounting;
            index++;
//        System.out.println("index после index++: " + index);

            if (index >= FinancialAccounting.arrFinancialAccounting.length) {
                int lengthArr = FinancialAccounting.arrFinancialAccounting.length;
                for (int i = lengthArr - 1; i > 0; i--) {
                    FinancialAccounting.arrFinancialAccounting[i] = FinancialAccounting.arrFinancialAccounting[i - 1];
                }
                FinancialAccounting.arrFinancialAccounting[0] = financialAccounting;
                index = 0;
            }

//            if (index < FinancialAccounting.arrFinancialAccounting.length) {
//            System.out.println("index перед inFromUser(): " + index);
//                inFromUser();
//            }
            System.out.println("financialAccounting.type(): " + financialAccounting.type());
            if (financialAccounting.type().equals("EXPENSE")) {
                EXPENSE = EXPENSE.add(BigDecimal.valueOf(Double.parseDouble(mainArr[1])));
                System.out.println("EXPENSE: " + EXPENSE);
            } else {
                INCOME = INCOME.add(BigDecimal.valueOf(Double.parseDouble(mainArr[1])));
                System.out.println("INCOME: " + INCOME);
            }
            inFromUser();
        }
        if(mainArr[0].strip().toUpperCase().equals("REPORT")) {
            System.out.println("getReport()");
            getReport();
        }
//        try {
//        System.out.println("financialAccounting.type(): " + financialAccounting.type());
//        if (financialAccounting.type().equals("EXPENSE")) {
//            EXPENSE = EXPENSE.add(BigDecimal.valueOf(Double.parseDouble(mainArr[1])));
//            System.out.println("EXPENSE: " + EXPENSE);
//        } else {
//            INCOME = INCOME.add(BigDecimal.valueOf(Double.parseDouble(mainArr[1])));
//            System.out.println("INCOME: " + INCOME);
//        }
//        }catch (NullPointerException e) {}
    }


    public void getReport() {
//        System.out.println("index: " + index);
        DecimalFormat df = new DecimalFormat("#,###.00", DecimalFormatSymbols.getInstance(Locale.US));
        System.out.println();
        System.out.println("Отчёт о финансах:");
//        String formattedINCOME = nf.format(INCOME);
        System.out.println("Общий доход: " + df.format(INCOME));
        System.out.println("Общие расходы: " + df.format(EXPENSE));
        System.out.println("Балланс: " + df.format(INCOME.subtract(EXPENSE)));

        System.out.println(String.format("%-15s %-15s %-15s %-15s", "Дата", "Сумма", "Тип", "Описание"));
        System.out.println("---------------------------------------------------------------------------");

        for (FinancialAccounting financialAccounting : FinancialAccounting.arrFinancialAccounting) {
            System.out.println(financialAccounting);
        }
    }

    //    EXIT
    public void endOfProgramm() {
        System.out.println("Работа программы завершена, ждём вас снова!\n");
        System.exit(0);
    }
}


//    public void inReport() {
//        InputClass inputClass = new InputClass();
//        String[] mainArr = inputClass.arrFromInput();
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//
//        FinancialAccounting[] arrFinancialAccounting = new FinancialAccounting[5];
//        FinancialAccounting financialAccounting = new FinancialAccounting(mainArr[0].strip(), new BigDecimal(mainArr[1]), Enum.valueOf(UserMenu.class, mainArr[2].toUpperCase()), LocalDate.parse(mainArr[3], formatter));
//
//
//        System.out.println("InputClass.index: " + index);
//        arrFinancialAccounting[index] = financialAccounting;
//        index++;
//        System.out.println("InputClass.index: " + index);
//
//        System.out.println(arrFinancialAccounting[index]);
//        inReport();
//    }


