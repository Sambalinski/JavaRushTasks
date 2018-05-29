package com.javarush.task.task18.task1820;

/* 
Округление чисел
Считать с консоли 2 имени файла.
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415.
Округлить числа до целых и записать через пробел во второй файл.
Закрыть потоки.

Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
-3.49 - -3
-3.50 - -3
-3.51 - -4

Требования:
1. Программа должна два раза считать имена файлов с консоли.
2. Для первого файла создай поток для чтения. Для второго - поток для записи.
3. Считать числа из первого файла, округлить их и записать через пробел во второй.
4. Должны соблюдаться принципы округления, указанные в задании.
5. Созданные для файлов потоки должны быть закрыты.
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        try(InputStream in = new FileInputStream(new Scanner(System.in).nextLine());
            OutputStream out = new FileOutputStream(new Scanner(System.in).nextLine())){
            StringBuilder sb = new StringBuilder();
            while (in.available() > 0) sb.append((char)in.read());
            String s [] = sb.toString().split("\\s");
            sb = new StringBuilder();
            for (int i = 0; i < s.length; i++) sb.append(Math.round(Double.parseDouble(s[i]))).append(" ");
            out.write(sb.toString().getBytes());
        }catch (Exception e) {e.printStackTrace();}
    }
}
