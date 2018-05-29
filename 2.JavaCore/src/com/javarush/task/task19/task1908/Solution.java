package com.javarush.task.task19.task1908;

/* 
Выделяем числа
Считать с консоли 2 имени файла.
Вывести во второй файл все числа, которые есть в первом файле.
Числа выводить через пробел.
Закрыть потоки.

Пример тела файла:
12 text var2 14 8v 1

Результат:
12 14 1

Требования:
1. Программа должна считывать имена файлов с консоли (используй BufferedReader).
2. BufferedReader для считывания данных с консоли должен быть закрыт.
3. Программа должна считывать содержимое первого файла (используй BufferedReader c конструктором FileReader).
4. Поток чтения из файла (BufferedReader) должен быть закрыт.
5. Программа должна записывать во второй файл все числа, через пробел, из первого файла (используй BufferedWriter с конструктором FileWriter).
6. Поток записи в файл (BufferedWriter) должен быть закрыт.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader consolReader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(new FileReader(consolReader.readLine()));
//        BufferedReader reader = new BufferedReader(new FileReader(consolReader.readLine()));
//        BufferedWriter writer = new BufferedWriter(new FileWriter(consolReader.readLine()));
        //Способ 1
//        StringBuilder sb = new StringBuilder();
//
//        while (reader.ready()) {
//            String sIn = reader.readLine();
//            String sArr[] = sIn.split(" ");
//            for (String s : sArr) {
//                if (isDigit(s)) sb.append(s).append(" ");
//            }
//        }
//        writer.write(sb.toString());

        //Способ 2. Stream API
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String collect = Files.lines(Paths.get(reader.readLine()))
                    .flatMap(s -> Arrays.stream(s.split(" ")))
                    .filter(s -> isDigit(s))
                    .collect(Collectors.joining(" "));

            System.out.println(collect);

        } catch (Exception e) {
        }

        consolReader.close();
//        reader.close();
//        writer.close();
    }
    public static boolean isDigit(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
