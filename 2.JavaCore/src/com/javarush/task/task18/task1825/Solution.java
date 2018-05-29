package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.*;

/* 
Собираем файл
Собираем файл из кусочков.
Считывать с консоли имена файлов.
Каждый файл имеет имя: [someName].partN.

Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.

Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end".
В папке, где находятся все прочтенные файлы, создать файл без суффикса [.partN].

Например, Lion.avi.

В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть потоки.

Требования:
1. Программа должна считывать имена файлов с консоли, пока не будет введено слово "end".
2. Создай поток для записи в файл без суффикса [.partN] в папке, где находятся все считанные файлы.
3. В новый файл перепиши все байты из файлов-частей *.partN.
4. Чтение и запись должны происходить с использованием буфера.
5. Созданные для файлов потоки должны быть закрыты.
*/

public class Solution {
    public static void main(String[] args) {
        Map<Integer, byte[]> map = new TreeMap<>();
        String s;
        try (Scanner fileName = new Scanner(System.in)) {
            String nameOfFile = null;
            while (!(s = fileName.nextLine()).equals("end")) {
                nameOfFile = s.substring(0, s.lastIndexOf(".part"));
                try (InputStream in = new FileInputStream(s)) {
                    byte[] bytes = new byte[in.available()];
                    in.read(bytes);
                    int i = Integer.parseInt(s.substring(s.lastIndexOf(".")+5));
                    map.put(i, bytes);
                }
            }
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                for (Map.Entry<Integer, byte[]> pair : map.entrySet()) {
                    bos.write(pair.getValue());
                }
                try (OutputStream fos = new FileOutputStream (nameOfFile)) {
                    fos.write(bos.toByteArray());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*
Пример с  ByteArrayOutputStream из предыдущей задачи:

public static void main(String[] args) throws Exception {
    String fileName1 = "D:\\f1.txt", fileName2 = "D:\\f2.txt";

    ByteArrayOutputStream array = new ByteArrayOutputStream();
    FileInputStream file1 = new FileInputStream(fileName1);
    FileInputStream file2 = new FileInputStream(fileName2);

    while (file2.available() > 0) array.write(file2.read());
    while (file1.available() > 0) array.write(file1.read());
    file1.close();
    file2.close();

    new FileOutputStream(fileName1).write(array.toByteArray());
}
*/
