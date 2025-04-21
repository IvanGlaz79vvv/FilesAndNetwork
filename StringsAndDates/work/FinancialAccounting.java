package work;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record FinancialAccounting(String description, BigDecimal cost, Enum type, LocalDate date) {
//    private String description;
//    private BigDecimal cost;
//    private Enum type;
//    private LocalDate date;

    public FinancialAccounting(String description, BigDecimal cost, Enum type, LocalDate date) {
        this.description = description;
        this.cost = cost;
        this.type = type;
        this.date = date;
    }


    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static String header = String.format("%-15s %-15s %-15s %-15s", "Дата", "Сумма", "Тип", "Описание");

    @Override
    public String toString() {
        return header + "\n--------------------------------------------------------------------------------\n"
                + String.format("%-15s %-15s %-15s %-15s",
                date.format(formatter), cost, type.toString(), description());
    }
}
