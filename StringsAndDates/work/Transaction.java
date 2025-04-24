package work;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public record Transaction(String description, BigDecimal cost, String type, LocalDate date) {


    static Transaction[] arrTransaction = new Transaction[5];

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    static DecimalFormat df = new DecimalFormat("#,###.00", DecimalFormatSymbols.getInstance(Locale.US));

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-15s %-15s", date.format(formatter), df.format(cost), type, description());
    }
}
