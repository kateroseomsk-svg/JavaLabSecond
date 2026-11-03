package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringProcessorTest {

    // ----------- 1: repeat ----------

    @Test
    void repeat_severalCopies() {
        assertEquals("ababab", StringProcessor.repeat("ab", 3));
    }
    @Test
    void repeat_oneCopy() {
        assertEquals("abc", StringProcessor.repeat("abc", 1));
    }
    @Test
    void repeat_zeroGivesEmptyString() {
        assertEquals("", StringProcessor.repeat("abc", 0));
    }
    @Test
    void repeat_emptyStringStaysEmpty() {
        assertEquals("", StringProcessor.repeat("", 5));
    }
    @Test
    void repeat_negativeNThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> StringProcessor.repeat("abc", -1));
    }

    // ---------- 2: numberOfOccurrences ----------

    @Test
    void numberOfOccurrences_substringEqualsString() {
        assertEquals(1, StringProcessor.numberOfOccurrences("abc", "abc"));
    }
    @Test
    void numberOfOccurrences_severalMatches() {
        assertEquals(3, StringProcessor.numberOfOccurrences("abcabcabc", "abc"));
    }
    @Test
    void numberOfOccurrences_noMatches() {
        assertEquals(0, StringProcessor.numberOfOccurrences("hello", "xyz"));
    }
    @Test
    void numberOfOccurrences_singleChar() {
        assertEquals(3, StringProcessor.numberOfOccurrences("banana", "a"));
    }
    @Test
    void numberOfOccurrences_emptyFirstString() {
        assertEquals(0, StringProcessor.numberOfOccurrences("", "a"));
    }
    @Test
    void numberOfOccurrences_emptySecondStringThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> StringProcessor.numberOfOccurrences("abc", ""));
    }
    @Test
    void numberOfOccurrences_nullSecondStringThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> StringProcessor.numberOfOccurrences("abc", null));
    }
    @Test
    void numberOfOccurrences_nullFirstStringThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> StringProcessor.numberOfOccurrences(null, "a"));
    }

    // ---------- 3: replace ----------

    @Test
    void replace_allThreeDigits() {
        assertEquals("одиндватри", StringProcessor.replace("123"));
    }
    @Test
    void replace_mixedWithText() {
        assertEquals("a один b два c три", StringProcessor.replace("a 1 b 2 c 3"));
    }
    @Test
    void replace_otherDigitsUntouched() {
        assertEquals("0456789", StringProcessor.replace("0456789"));
    }
    @Test
    void replace_noDigits() {
        assertEquals("hello", StringProcessor.replace("hello"));
    }
    @Test
    void replace_emptyString() {
        assertEquals("", StringProcessor.replace(""));
    }
    @Test
    void replace_repeatedDigit() {
        assertEquals("двадва", StringProcessor.replace("22"));
    }
    @Test
    void replace_nullThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> StringProcessor.replace(null));
    }

    // ---------- 4: deleteSecondChar ----------

    @Test
    void deleteSecondChar_evenLength() {
        StringBuilder sb = new StringBuilder("abcdef");
        StringProcessor.deleteSecondChar(sb);
        assertEquals("ace", sb.toString());
    }
    @Test
    void deleteSecondChar_singleChar() {
        StringBuilder sb = new StringBuilder("a");
        StringProcessor.deleteSecondChar(sb);
        assertEquals("a", sb.toString());
    }
    @Test
    void deleteSecondChar_twoChars() {
        StringBuilder sb = new StringBuilder("ab");
        StringProcessor.deleteSecondChar(sb);
        assertEquals("a", sb.toString());
    }
    @Test
    void deleteSecondChar_emptyBuilder() {
        StringBuilder sb = new StringBuilder();
        StringProcessor.deleteSecondChar(sb);
        assertEquals("", sb.toString());
    }
    @Test
    void deleteSecondChar_nullThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> StringProcessor.deleteSecondChar(null));
    }

    // ---------- 5: reverseString ----------

    @Test
    void reverseString_exampleFromTask() {
        assertEquals(" dd cc bbb aaa", StringProcessor.reverseString(" aaa bbb cc dd"));
    }
    @Test
    void reverseString_multipleSpacesStayInPlace() {
        assertEquals("c   b  a", StringProcessor.reverseString("a   b  c"));
    }
    @Test
    void reverseString_trailingSpaces() {
        assertEquals("b a  ", StringProcessor.reverseString("a b  "));
    }
    @Test
    void reverseString_singleWord() {
        assertEquals("hello", StringProcessor.reverseString("hello"));
    }
    @Test
    void reverseString_onlySpaces() {
        assertEquals("   ", StringProcessor.reverseString("   "));
    }
    @Test
    void reverseString_nullThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> StringProcessor.reverseString(null));
    }

    // ---------- 6: changeHexadecimal ----------

    @Test
    void changeHexadecimal_exampleFromTask() {
        assertEquals("Васе 16 лет", StringProcessor.changeHexadecimal("Васе 0x00000010 лет"));
    }
    @Test
    void changeHexadecimal_severalNumbers() {
        assertEquals("1 и 255", StringProcessor.changeHexadecimal("0x00000001 и 0x000000FF"));
    }
    @Test
    void changeHexadecimal_lowerCaseDigits() {
        assertEquals("255", StringProcessor.changeHexadecimal("0x000000ff"));
    }
    @Test
    void changeHexadecimal_letterDigits() {
        assertEquals("10", StringProcessor.changeHexadecimal("0x0000000A"));
    }
    @Test
    void changeHexadecimal_zero() {
        assertEquals("0", StringProcessor.changeHexadecimal("0x00000000"));
    }
    @Test
    void changeHexadecimal_maxValue() {
        assertEquals("4294967295", StringProcessor.changeHexadecimal("0xFFFFFFFF"));
    }
    @Test
    void changeHexadecimal_numberAtEnd() {
        assertEquals("Васе 16", StringProcessor.changeHexadecimal("Васе 0x00000010"));
    }
    @Test
    void changeHexadecimal_twoNumbersInRow() {
        assertEquals("12", StringProcessor.changeHexadecimal("0x000000010x00000002"));
    }
    @Test
    void changeHexadecimal_noNumbers() {
        assertEquals("просто текст", StringProcessor.changeHexadecimal("просто текст"));
    }
    @Test
    void changeHexadecimal_emptyString() {
        assertEquals("", StringProcessor.changeHexadecimal(""));
    }
    @Test
    void changeHexadecimal_tooShortNumberUntouched() {
        assertEquals("0x123", StringProcessor.changeHexadecimal("0x123"));
    }
    @Test
    void changeHexadecimal_nullThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> StringProcessor.changeHexadecimal(null));
    }
   }