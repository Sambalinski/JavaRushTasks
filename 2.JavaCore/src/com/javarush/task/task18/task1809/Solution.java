package com.javarush.task.task18.task1809;

/* 
Реверс файла
Считать с консоли 2 имени файла: файл1, файл2.
Записать в файл2 все байты из файл1, но в обратном порядке.
Закрыть потоки.

Требования:
1. Программа должна два раза считать имена файлов с консоли.
2. Для чтения из файла используй поток FileInputStream, для записи в файл - FileOutputStream
3. Во второй файл нужно записать все байты из первого в обратном порядке.
4. Потоки FileInputStream и FileOutputStream должны быть закрыты.
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        try (InputStream inputStream = new FileInputStream(new Scanner(System.in).nextLine());
             OutputStream outputStream = new FileOutputStream(new Scanner(System.in).nextLine())) {
            byte[] fileImage = new byte[inputStream.available()];
            inputStream.read(fileImage);
            for (int i = fileImage.length-1; i >=0; i--) outputStream.write(fileImage[i]);
        } catch (Exception e) {
        }
    }
}
