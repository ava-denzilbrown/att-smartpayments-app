package com.att.smartpayments;

import java.util.List;

/** Static sample payment data for the demo portal. */
public record Payment(String id, String customer, String amount, String status) {

    public static final List<Payment> SAMPLE = List.of(
            new Payment("PAY-100245", "Northwind Traders", "$12,450.00", "Completed"),
            new Payment("PAY-100246", "Contoso Ltd.", "$3,280.50", "Pending"),
            new Payment("PAY-100247", "Fabrikam Inc.", "$845.75", "Completed"),
            new Payment("PAY-100248", "Adventure Works", "$27,900.00", "Failed"),
            new Payment("PAY-100249", "Tailspin Toys", "$1,120.00", "Completed"),
            new Payment("PAY-100250", "Wide World Importers", "$9,615.20", "Pending"),
            new Payment("PAY-100251", "Litware Corp.", "$4,300.00", "Completed"));
}
