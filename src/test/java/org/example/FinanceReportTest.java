package org.example;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class FinanceReportTest {

    private FinanceReport sample() {
        Payment[] payments = {
                new Payment("Иванов Иван Иванович", 5, 3, 2020, 123456),
                new Payment("Петров Петр Петрович", 6, 4, 2020, 50000)
        };
        return new FinanceReport(payments, "Сидорова А.А.", LocalDate.of(2021, 2, 1));
    }

    // ---------- 8:FinanceReport ----------

    @Test
    void getPaymentsCount_returnsNumberOfPayments() {
        assertEquals(2, sample().getPaymentsCount());
    }
    @Test
    void getPaymentsCount_emptyReport() {
        FinanceReport r = new FinanceReport(new Payment[0], "Автор", LocalDate.of(2021, 1, 1));
        assertEquals(0, r.getPaymentsCount());
    }
    @Test
    void getPayment_returnsPaymentByIndex() {
        assertEquals(new Payment("Петров Петр Петрович", 6, 4, 2020, 50000),
                sample().getPayment(1));
    }
    @Test
    void setPayment_replacesPayment() {
        FinanceReport r = sample();
        Payment p = new Payment("Новый Н.Н.", 1, 1, 2022, 100);
        r.setPayment(0, p);
        assertEquals(p, r.getPayment(0));
    }
    @Test
    void getPayment_negativeIndexThrowsException() {
        FinanceReport r = sample();
        assertThrows(IndexOutOfBoundsException.class, () -> r.getPayment(-1));
    }
    @Test
    void getPayment_tooBigIndexThrowsException() {
        FinanceReport r = sample();
        assertThrows(IndexOutOfBoundsException.class, () -> r.getPayment(2));
    }
    @Test
    void setPayment_badIndexThrowsException() {
        FinanceReport r = sample();
        Payment p = new Payment("Н.Н.", 1, 1, 2022, 100);
        assertThrows(IndexOutOfBoundsException.class, () -> r.setPayment(5, p));
    }
    @Test
    void setPayment_nullThrowsException() {
        FinanceReport r = sample();
        assertThrows(IllegalArgumentException.class, () -> r.setPayment(0, null));
    }
    @Test
    void constructor_nullArrayThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new FinanceReport(null, "Автор", LocalDate.of(2021, 1, 1)));
    }
    @Test
    void constructor_nullAuthorThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new FinanceReport(new Payment[0], null, LocalDate.of(2021, 1, 1)));
    }
    @Test
    void getAuthor_returnsAuthor() {
        assertEquals("Сидорова А.А.", sample().getAuthor());
    }
    @Test
    void getCreationDate_returnsDate() {
        assertEquals(LocalDate.of(2021, 2, 1), sample().getCreationDate());
    }

    // ---------- 9: toString ----------

    @Test
    void toString_formatsReport() {
        Payment[] payments = {new Payment("Иванов И.И.", 5, 3, 2020, 123456)};
        FinanceReport r = new FinanceReport(payments, "Сидорова А.А.", LocalDate.of(2021, 2, 1));
        assertEquals("[Автор: Сидорова А.А., дата: 01.02.2021, Платежи: [\n"
                + "Плательщик: Иванов И.И., дата: 05.03.2020 сумма: 1234 руб. 56 коп.\n"
                + "]]", r.toString());
    }
    @Test
    void toString_emptyReport() {
        FinanceReport r = new FinanceReport(new Payment[0], "Сидорова А.А.", LocalDate.of(2021, 2, 1));
        assertEquals("[Автор: Сидорова А.А., дата: 01.02.2021, Платежи: [\n]]", r.toString());
    }

    // ---------- 10: copyConstructor ----------

    @Test
    void copyConstructor_copiesContent() {
        FinanceReport original = sample();
        FinanceReport copy = new FinanceReport(original);
        assertEquals(original.toString(), copy.toString());
    }
    @Test
    void copyConstructor_changingPaymentInOriginalDoesNotChangeCopy() {
        FinanceReport original = sample();
        FinanceReport copy = new FinanceReport(original);
        original.getPayment(0).setAmount(1);
        assertEquals(123456, copy.getPayment(0).getAmount());
    }
    @Test
    void copyConstructor_replacingPaymentInOriginalDoesNotChangeCopy() {
        FinanceReport original = sample();
        FinanceReport copy = new FinanceReport(original);
        original.setPayment(1, new Payment("Другой Д.Д.", 1, 1, 2000, 7));
        assertEquals("Петров Петр Петрович", copy.getPayment(1).getFullName());
    }
    @Test
    void copyConstructor_changingCopyDoesNotChangeOriginal() {
        FinanceReport original = sample();
        FinanceReport copy = new FinanceReport(original);
        copy.getPayment(0).setFullName("Изменено");
        assertEquals("Иванов Иван Иванович", original.getPayment(0).getFullName());
    }
    @Test
    void copyConstructor_copiesAuthorAndDate() {
        FinanceReport copy = new FinanceReport(sample());
        assertEquals("Сидорова А.А. 2021-02-01", copy.getAuthor() + " " + copy.getCreationDate());
    }
}