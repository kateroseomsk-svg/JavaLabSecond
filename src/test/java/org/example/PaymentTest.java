package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Payment sample() {
        return new Payment("Иванов Иван Иванович", 5, 3, 2020, 123456);
    }

    @Test
    void constructor_setsFullName() {
        assertEquals("Иванов Иван Иванович", sample().getFullName());
    }
    @Test
    void constructor_setsDate() {
        Payment p = sample();
        assertEquals("5.3.2020", p.getDay() + "." + p.getMonth() + "." + p.getYear());
    }
    @Test
    void constructor_setsAmount() {
        assertEquals(123456, sample().getAmount());
    }
    @Test
    void constructor_nullNameThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Payment(null, 1, 1, 2020, 100));
    }
    @Test
    void copyConstructor_copiesAllFields() {
        Payment p = sample();
        assertEquals(p, new Payment(p));
    }
    @Test
    void copyConstructor_createsIndependentObject() {
        Payment p = sample();
        Payment copy = new Payment(p);
        p.setAmount(1);
        assertEquals(123456, copy.getAmount());
    }
    @Test
    void setters_changeFields() {
        Payment p = sample();
        p.setFullName("Петров Петр Петрович");
        p.setDay(10);
        p.setMonth(12);
        p.setYear(2021);
        p.setAmount(500);
        assertEquals(new Payment("Петров Петр Петрович", 10, 12, 2021, 500), p);
    }
    @Test
    void setFullName_nullThrowsException() {
        Payment p = sample();
        assertThrows(IllegalArgumentException.class, () -> p.setFullName(null));
    }
    @Test
    void equals_sameFieldsAreEqual() {
        assertEquals(sample(), sample());
    }
    @Test
    void equals_differentAmountNotEqual() {
        assertNotEquals(sample(), new Payment("Иванов Иван Иванович", 5, 3, 2020, 1));
    }
    @Test
    void equals_differentNameNotEqual() {
        assertNotEquals(sample(), new Payment("Сидоров Сидор Сидорович", 5, 3, 2020, 123456));
    }
    @Test
    void equals_nullNotEqual() {
        assertNotEquals(null, sample());
    }
    @Test
    void equals_otherTypeNotEqual() {
        assertNotEquals("строка", sample());
    }
    @Test
    void equals_sameObjectIsEqual() {
        Payment p = sample();
        assertEquals(p, p);
    }
    @Test
    void hashCode_equalObjectsHaveEqualHashCodes() {
        assertEquals(sample().hashCode(), sample().hashCode());
    }
    @Test
    void toString_formatsPayment() {
        assertEquals("Плательщик: Иванов Иван Иванович, дата: 05.03.2020 сумма: 1234 руб. 56 коп.\n",
                sample().toString());
    }
    @Test
    void toString_kopecksHaveLeadingZero() {
        Payment p = new Payment("Иванов И.И.", 1, 1, 2020, 100005);
        assertEquals("Плательщик: Иванов И.И., дата: 01.01.2020 сумма: 1000 руб. 05 коп.\n",
                p.toString());
    }
    @Test
    void toString_zeroAmount() {
        Payment p = new Payment("Иванов И.И.", 10, 11, 2020, 0);
        assertEquals("Плательщик: Иванов И.И., дата: 10.11.2020 сумма: 0 руб. 00 коп.\n",
                p.toString());
    }
}