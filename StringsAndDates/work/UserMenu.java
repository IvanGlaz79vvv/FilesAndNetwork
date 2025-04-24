package work;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class UserMenu {

    int index = 0;
    BigDecimal EXPENSE = BigDecimal.ZERO;
    BigDecimal INCOME = BigDecimal.ZERO;


    public UserMenu() {
    }

    /**      INPUT     */
    public void inFromUser() {

        InputClass inputClass = new InputClass();
        String[] mainArr = new String[4];
        mainArr = inputClass.arrFromInput();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        if (mainArr.length > 1) {
            Transaction transaction =
                    new Transaction(mainArr[0].strip(),
                            new BigDecimal(mainArr[1].strip()),
                            mainArr[2].strip().toUpperCase(),
                            LocalDate.parse(mainArr[3].strip(),
                                    formatter));


            if (index < Transaction.arrTransaction.length) {
                Transaction.arrTransaction[index] = transaction;
            }

            index++;

            // смещение массива
            if (index == Transaction.arrTransaction.length + 1) {
                int lengthArr = Transaction.arrTransaction.length;
                for (int i = 0; i < lengthArr - 1; i++) {
                    Transaction.arrTransaction[i] = Transaction.arrTransaction[i + 1];
                }
                Transaction.arrTransaction[lengthArr - 1] = transaction;
                index = lengthArr;
            }

            if (transaction.type().equals("EXPENSE")) {
                EXPENSE = EXPENSE.add(BigDecimal.valueOf(Double.parseDouble(mainArr[1])));

            } else {
                INCOME = INCOME.add(BigDecimal.valueOf(Double.parseDouble(mainArr[1])));

            }
            inFromUser();
        }
        if (mainArr[0].strip().toUpperCase().equals("REPORT")) {
            getReport();
        }
        if (mainArr[0].strip().toUpperCase().equals("EXIT")) {
            endOfProgramm();
        }

        if (mainArr[0].strip().toUpperCase().equals("HELP")) {
            getInstructions();
        }

    }

    /**      REPORT     */
    public void getReport() {
        printReport();
        inFromUser();
    }

    /**      print REPORT     */
    public void printReport() {
        DecimalFormat df = new DecimalFormat("#,###.00", DecimalFormatSymbols.getInstance(Locale.US));
        System.out.println();
        System.out.println("Отчёт о финансах:");
        System.out.println("Общий доход: " + df.format(INCOME));
        System.out.println("Общие расходы: " + df.format(EXPENSE));
        System.out.println("Балланс: " + df.format(INCOME.subtract(EXPENSE)));
        System.out.println(String.format("%-15s %-15s %-15s %-15s", "Дата", "Сумма", "Тип", "Описание"));
        System.out.println("---------------------------------------------------------------------------");

        for (Transaction Transaction : Transaction.arrTransaction) {
            System.out.println(Transaction);
        }
    }


    /**      HELP     */
    public void getInstructions(){
        System.out.println("\n*************************************");
        HelpClass.getHelp();
        System.out.println("*************************************");
        inFromUser();
    }

    /**      EXIT     */
    public void endOfProgramm() {
        printReport();
        System.out.println("\nРабота программы завершена, ждём вас снова!\n");
        System.exit(0);
    }
}



