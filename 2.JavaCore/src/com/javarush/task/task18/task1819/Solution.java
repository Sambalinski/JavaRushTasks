package com.javarush.task.task18.task1819;

/* 
Объединение файлов
Считать с консоли 2 имени файла.
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов.
Закрыть потоки.

Требования:
1. Программа должна два раза считать имена файлов с консоли.
2. Для первого файла создай поток для чтения и считай его содержимое.
3. Затем, для первого файла создай поток для записи. Для второго - для чтения.
4. Содержимое первого и второго файла нужно объединить в первом файле.
5. Сначала должно идти содержимое второго файла, затем содержимое первого.
6. Созданные для файлов потоки должны быть закрыты.
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName1 = scanner.nextLine();
        String fileName2 = scanner.nextLine();
        try (InputStream in1 = new FileInputStream(fileName1);
             ) {
            byte[] bytesF1 = new byte[in1.available()];
            in1.read(bytesF1);
            try(OutputStream out1 = new FileOutputStream(fileName1);
                InputStream in2 = new FileInputStream(fileName2)) {
                byte[] bytesF2 = new byte[in2.available()];
                in2.read(bytesF2);
                byte[] bytesF2F1 = new  byte[bytesF2.length + bytesF1.length];
                System.arraycopy(bytesF2, 0, bytesF2F1, 0, bytesF2.length);
                System.arraycopy(bytesF1, 0, bytesF2F1, bytesF2.length, bytesF1.length);
                out1.write(bytesF2F1);
            } catch (Exception e) {}
        } catch (Exception e) {}
    }
}
