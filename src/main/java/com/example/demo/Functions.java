package com.example.demo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Functions {
    // Проверка - является ли строка числом
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    // Проверка - является ли строка датой (в формате yyyy-mm-dd)
    public static boolean isValidDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(dateString, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // Проверка - является ли строка временем (в формате HH:MM)
    public static boolean isValidTime(String timeString) {
        // Регулярное выражение для формата HH:MM (24-часовой формат)
        String regex = "^([01][0-9]|2[0-3]):[0-5][0-9]$";

        // Компилируем регулярное выражение в Pattern
        Pattern pattern = Pattern.compile(regex);

        // Создаем Matcher для сопоставления строки с шаблоном
        Matcher matcher = pattern.matcher(timeString);

        // Проверяем, соответствует ли строка шаблону
        return matcher.matches();
    }
}
