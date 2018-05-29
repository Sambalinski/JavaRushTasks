package com.javarush.task.task18.task1802;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

/* 
Минимальный байт
Ввести с консоли имя файла.
Найти минимальный байт в файле, вывести его на экран.
Закрыть поток ввода-вывода.

Требования:
1. Программа должна считывать имя файла с консоли.
2. Для чтения из файла используй поток FileInputStream.
3. В консоль должен выводиться минимальный байт, считанный из файла.
4. Поток чтения из файла должен быть закрыт.
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        try (InputStream inputStream = new FileInputStream(new Scanner(System.in).nextLine())) {
            int minByte = inputStream.read();
            while (inputStream.available() > 0) {
                int data = inputStream.read();
                if (minByte > data) minByte = data;
            }
            System.out.println(minByte);
        } catch (Exception e) {
        }
    }
}
