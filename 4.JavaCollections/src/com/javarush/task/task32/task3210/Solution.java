package com.javarush.task.task32.task3210;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 Используем RandomAccessFile
В метод main приходят три параметра:
1) fileName - путь к файлу;
2) number - число, позиция в файле;
3) text - текст.

Считать текст с файла начиная с позиции number, длинной такой же как и длинна переданного текста в третьем параметре.
Если считанный текст такой же как и text, то записать в конец файла строку 'true', иначе записать 'false'.
Используй RandomAccessFile и его методы seek(long pos), read(byte b[], int off, int len), write(byte b[]).
Используй один из конструкторов класса String для конвертации считанной строчки в текст.


Требования:
1. В методе main класса Solution необходимо использовать RandomAccessFile,
    который должен использовать файл, который приходит первым параметром.
2. В методе main класса Solution программа должна устанавливать позицию в файле,
    которая передана во втором параметре.
3. В методе main класса Solution программа должна считывать данные с файла
     при помощи метода read(byte b[], int off, int len).
4. Запись должна происходить в конец файла.
5. Если считанный текст такой же как и text, то программа должна записать в конец переданного файла строку 'true'.
6. Если считанный текст НЕ такой же как и text, то программа должна записать в конец переданного файла строку 'false'.
*/

public class Solution {
    public static void main(String... args) {
        args = new String[]{"test", "25", "forms of air"};

        final String fileName = args[0];
        final long position = Long.parseLong(args[1]);
        final String text = args[2];

        byte[] buffer = new byte[1024];
        try(RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {

            raf.seek(position);

            raf.read(buffer, 0, text.getBytes().length);

            String s = new String(buffer);

            raf.seek(raf.length());

            if (s.trim().equals(text)) {
                raf.write("true".getBytes());
            } else {
                raf.write("false".getBytes());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}