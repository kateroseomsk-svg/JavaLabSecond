package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FinanceReport {
    private Payment[] payments;
    private String author;
    private LocalDate creationDate;

    public FinanceReport(Payment[] payments, String author, LocalDate creationDate) {
        if (payments == null || author == null || creationDate == null) {
            throw new IllegalArgumentException("Аргументы не должны быть null");
        }
        this.payments = payments;
        this.author = author;
        this.creationDate = creationDate;
    }

    public FinanceReport(FinanceReport other) { //10
        this.author = other.author;
        this.creationDate = other.creationDate;
        this.payments = new Payment[other.payments.length];
        for (int i = 0; i < other.payments.length; i++) {
            this.payments[i] = new Payment(other.payments[i]);
        }
    }

    public int getPaymentsCount() {
        return payments.length;
    }

    public Payment getPayment(int index) {
        checkIndex(index);
        return payments[index];
    }

    public String getAuthor() {
        return author;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setPayment(int index, Payment payment) {
        checkIndex(index);
        if (payment == null) {
            throw new IllegalArgumentException("Платёж не должен быть null");
        }
        payments[index] = payment;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= payments.length) {
            throw new IndexOutOfBoundsException("Нет платежа с номером " + index);
        }
    }

    @Override
    public String toString() {    //9
        StringBuilder lines = new StringBuilder();
        for (int i = 0; i < payments.length; i++) {
            lines.append(payments[i].toString());
        }
        return String.format("[Автор: %s, дата: %s, Платежи: [\n%s]]",
                author,
                creationDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                lines);
    }
}