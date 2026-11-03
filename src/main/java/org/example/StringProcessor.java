package org.example;

import java.util.ArrayList;
import java.util.List;

public class StringProcessor {

    public static String repeat(String s, int N) {
        if(s == null){
            throw new IllegalArgumentException("Строка пустая");
        }
        if(N < 0) {
        throw new IllegalArgumentException("N не мб < 0");
        }
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < N; i++){
            result.append(s);
        }
        return result.toString();
        }

        public static int numberOfOccurrences(String s, String sub){
        if(sub == null || sub.isEmpty()) {
        throw new IllegalArgumentException(" Вторая строка не должна быть null / empty");
        }
        int count = 0;
        int index = s.indexOf(sub);
        while(index != -1){
            count++;
            index = s.indexOf(sub, index + sub.length());
        }
        return count;
    }

    public static String replace(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Строка не должна быть null");
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            String ch = String.valueOf(s.charAt(i));
            if (ch.equals("1")) {
                result.append("один");
            } else if (ch.equals("2")) {
                result.append("два");
            } else if (ch.equals("3")) {
                result.append("три");
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static void deleteSecondChar(StringBuilder s){
        if(s == null){
            throw new IllegalArgumentException("Строка не мб null");
        }
        int write = 0;
        for(int i = 0; i < s.length(); i+= 2){
            s.setCharAt(write, s.charAt(i));
            write++;
        }
        s.setLength(write);
    }

public static String reverseString(String s){
    if (s == null) {
        throw new IllegalArgumentException("Строка не должна быть null");
    }
    List<String> words = new ArrayList<>();
    int i = 0;
    while (i < s.length()) {
        if (s.charAt(i) == ' ') {
            i++;
        } else {
            int start = i;
            while (i < s.length() && s.charAt(i) != ' ') {
                i++;
            }
            words.add(s.substring(start, i));
        }
    }
    StringBuilder result = new StringBuilder();
    int wordIndex = words.size() - 1;
    i = 0;
    while (i < s.length()) {
        if (s.charAt(i) == ' ') {
            result.append(' ');
            i++;
        } else {
            while (i < s.length() && s.charAt(i) != ' ') {
                i++;
            }
            result.append(words.get(wordIndex));
            wordIndex--;
        }
    }
    return result.toString();
    }

    //0xNNNNNNNN
    private static boolean symbolHexadecimal(char c) {
        return ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'));
    }
    private static boolean numberHexadecimal(String s, int index) {
        if (index + 10 > s.length()) {
            return false;
        }
        if (s.charAt(index) != '0' || s.charAt(index + 1) != 'x') {
            return false;
        }
        for (int i = index + 2; i < index + 10; i++) {
            if (!symbolHexadecimal(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String changeHexadecimal(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Строка не может быть null");
        }
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            if (numberHexadecimal(s, i)) {
                String count = s.substring(i + 2, i + 10);
                long value = Long.parseLong(count, 16);
                result.append(value);
                i = i + 10;
            } else {
                result.append(s.charAt(i));
                i++;
            }
        }
        return result.toString();
    }

    }
