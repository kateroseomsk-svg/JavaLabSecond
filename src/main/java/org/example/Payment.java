package org.example;

import java.util.Objects;

public class Payment {
    private String fullName;
    private int day;
    private int month;
    private int year;
    private int amount;

    public Payment(String fullName, int day, int month, int year, int amount) {
        if (fullName == null) {
            throw new IllegalArgumentException("ФИО не должно быть null");
        }
        this.fullName = fullName;
        this.day = day;
        this.month = month;
        this.year = year;
        this.amount = amount;
    }

    public Payment(Payment other) {
        this(other.fullName, other.day, other.month, other.year, other.amount);
    }

    public String getFullName() {
        return fullName;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getAmount() {
        return amount;
    }

    public void setFullName(String fullName) {
        if (fullName == null) {
            throw new IllegalArgumentException("ФИО не должно быть null");
        }
        this.fullName = fullName;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Payment other = (Payment) o;
        return day == other.day
                && month == other.month
                && year == other.year
                && amount == other.amount
                && fullName.equals(other.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, day, month, year, amount);
    }

    @Override
    public String toString() {
        return String.format("Плательщик: %s, дата: %02d.%02d.%d сумма: %d руб. %02d коп.\n",
                fullName, day, month, year, amount / 100, amount % 100);
    }
}