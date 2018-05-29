package com.javarush.task.task18.task1807;

/* 
Подсчет запятых
С консоли считать имя файла.
Посчитать в файле количество символов ',', количество вывести на консоль.
Закрыть потоки.

Подсказка:
нужно сравнивать с ascii-кодом символа ','.

Требования:
1. Программа должна считывать имя файла с консоли.
2. Для чтения из файла используй поток FileInputStream.
3. В консоль должно выводится число запятых в считанном файле.
4. Поток чтения из файла должен быть закрыт.
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        byte[] b = ",".getBytes("ascii");
        int count = 0;
        try (InputStream inputStream = new FileInputStream(new BufferedReader(new InputStreamReader(System.in)).readLine())) {
            while (inputStream.available() > 0) if (b[0] == inputStream.read()) count ++;
        } catch (Exception e) {
        }
        System.out.println(count);
    }
}
