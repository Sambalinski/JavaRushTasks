package com.javarush.task.task18.task1804;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

/* 
Самые редкие байты
Ввести с консоли имя файла.
Найти байт или байты с минимальным количеством повторов.
Вывести их на экран через пробел.
Закрыть поток ввода-вывода.

Требования:
1. Программа должна считывать имя файла с консоли.
2. Для чтения из файла используй поток FileInputStream.
3. В консоль через пробел должны выводиться все байты из файла с минимальным количеством повторов.
4. Данные в консоль должны выводится в одну строку.
5. Поток чтения из файла должен быть закрыт.
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        int[] arrBytes = new int[256];
        try (InputStream inputStream = new FileInputStream(new Scanner(System.in).nextLine())) {
            while (inputStream.available() > 0) arrBytes[inputStream.read()]++;
        } catch (Exception e) {
        }
        int minCount = 1;
        for (int i = 0; i <= 255; i++)
            if (arrBytes[i] == minCount) System.out.print(i + " ");
    }
}
