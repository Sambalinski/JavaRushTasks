package com.javarush.task.task18.task1808;

/* 
Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать большую часть.
Закрыть потоки.

Требования:
1. Программа должна три раза считать имена файлов с консоли.
2. Для чтения из файла используй поток FileInputStream, для записи в файлы - FileOutputStream
3. Первую половину байт из первого файла нужно записать во второй файл.
4. Вторую половину байт из первого файла нужно записать в третий файл.
5. Потоки FileInputStream и FileOutputStream должны быть закрыты.
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        try (InputStream inputStream = new FileInputStream(new BufferedReader(new InputStreamReader(System.in)).readLine());
             OutputStream outputStream1 = new FileOutputStream(new Scanner(System.in).nextLine());
             OutputStream outputStream2 = new FileOutputStream(new Scanner(System.in).nextLine())
        ) {
            if (inputStream.available() % 2 == 0) {
                byte[] buff1 = new byte[inputStream.available() / 2];
                inputStream.read(buff1, 0, inputStream.available() / 2);
                byte[] buff2 = new byte[inputStream.available()];
                inputStream.read(buff2);
                outputStream1.write(buff1);
                outputStream2.write(buff2);
                System.out.println("СРаботало условие 1");
            } else {
                byte[] buff1 = new byte[inputStream.available() / 2 + 1];
                inputStream.read(buff1, 0, inputStream.available() / 2 +1);
                byte[] buff2 = new byte[inputStream.available()];
                inputStream.read(buff2);
                outputStream1.write(buff1);
                outputStream2.write(buff2);
                System.out.println("СРаботало условие 2");
            }
        } catch (Exception e) {
            System.out.println("Ошибочка вышла");
        }
    }
}
