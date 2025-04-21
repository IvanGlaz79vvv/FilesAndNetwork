package work;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public record FinancialAccounting(String description, BigDecimal cost, String type, LocalDate date) {
//    private String description;
//    private static BigDecimal cost;
//    private Enum type;
//    private LocalDate date;

    static FinancialAccounting[] arrFinancialAccounting = new FinancialAccounting[3];

    public FinancialAccounting {
    }

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    //    static String header = String.format("%-15s %-15s %-15s %-15s", "Дата", "Сумма", "Тип", "Описание");

    static DecimalFormat df = new DecimalFormat("#,###.00", DecimalFormatSymbols.getInstance(Locale.US));
    @Override
    public String toString() {
        return String.format("%-15s %-15s %-15s %-15s", date.format(formatter), df.format(cost), type, description());
    }
}
