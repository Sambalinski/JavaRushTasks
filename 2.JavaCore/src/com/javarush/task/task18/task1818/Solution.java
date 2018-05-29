package com.javarush.task.task18.task1818;

/* 
Два в одном
Считать с консоли 3 имени файла.
Записать в первый файл содержимого второго файла, а потом дописать в первый файл содержимое третьего файла.
Закрыть потоки.

Требования:
1. Программа должна три раза считать имена файлов с консоли.
2. Для первого файла создай поток для записи. Для двух других - потоки для чтения.
3. Содержимое второго файла нужно переписать в первый файл.
4. Содержимое третьего файла нужно дописать в первый файл (в который уже записан второй файл).
5. Созданные для файлов потоки должны быть закрыты.
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        try (OutputStream out = new FileOutputStream(new BufferedReader(new InputStreamReader(System.in)).readLine(), true);
             InputStream in1 = new FileInputStream(new BufferedReader(new InputStreamReader(System.in)).readLine());
             InputStream in2 = new FileInputStream(new BufferedReader(new InputStreamReader(System.in)).readLine())) {
            while (in1.available() > 0) out.write(in1.read());
            while (in2.available() > 0) out.write(in2.read());
        } catch (Exception e) {
            System.out.println("Ошибочка вышла");
        }
    }
}
