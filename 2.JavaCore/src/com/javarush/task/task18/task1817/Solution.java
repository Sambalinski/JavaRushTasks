package com.javarush.task.task18.task1817;

/* 
Пробелы
В метод main первым параметром приходит имя файла.
Вывести на экран соотношение количества пробелов к количеству всех символов. Например, 10.45.
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой.
4. Закрыть потоки.

Требования:
1. Считывать с консоли ничего не нужно.
2. Создай поток чтения из файла, который приходит первым параметром в main.
3. Посчитай отношение пробелов ко всем символам в файле и выведи в консоль это число.
4. Выведенное значение необходимо округлить до 2 знаков после запятой.
5. Поток чтения из файла должен быть закрыт.
*/

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;

public class Solution {
    public static void main(String[] args) {
        try (InputStream inputStream = new FileInputStream(args[0])) {
            StringBuilder sb = new StringBuilder();
            while (inputStream.available() > 0) sb.append((char)inputStream.read());
            double a = sb.toString().length();
            double b = sb.toString().split("\\s").length-1;
            double d =  b/ a*100;
//            System.out.println((String.format("%.2f", d)));
            DecimalFormat df = new DecimalFormat("##0.00");
            String dStr = df.format(d);
            System.out.println(dStr);
        } catch (Exception e) {System.out.println("Ошибочка вышла.");}
    }
}
