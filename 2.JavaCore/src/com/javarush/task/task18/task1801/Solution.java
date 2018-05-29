package com.javarush.task.task18.task1801;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/* 
Максимальный байт
Ввести с консоли имя файла.
Найти максимальный байт в файле, вывести его на экран.
Закрыть поток ввода-вывода.

Требования:
1. Программа должна считывать имя файла с консоли.
2. Для чтения из файла используй поток FileInputStream.
3. В консоль должен выводиться максимальный байт, считанный из файла.
4. Поток чтения из файла должен быть закрыт.
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        try
                (InputStream inputStream = new FileInputStream(new Scanner(System.in).nextLine())) {
            int maxByte = 0;
            while (inputStream.available() != -1) {
                int data = inputStream.read();
                if (data > maxByte) maxByte = data;
            }
            System.out.println(maxByte);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
//    public static void main(String[] args) throws IOException {
//        try
//                (InputStream inputStream = new FileInputStream(new Scanner(System.in).nextLine())) {
//            int maxByte = 0;
//            while (true) {
//                int data = inputStream.read();
//                if (data == -1) break;
//                if (data > maxByte) maxByte = data;
//            }
//            System.out.println(maxByte);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}
