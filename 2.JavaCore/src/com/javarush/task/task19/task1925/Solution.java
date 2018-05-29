package com.javarush.task.task19.task1925;

/* 
Длинные слова
В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит слова, разделенные пробелом.
Записать через запятую в Файл2 слова, длина которых строго больше 6.
В конце файла2 запятой не должно быть.
Закрыть потоки.

Пример выходных данных в файл2:
длинное,короткое,аббревиатура


Требования:
1. Программа НЕ должна считывать данные с консоли.
2. Программа должна считывать содержимое первого файла (используй FileReader c конструктором String).
3. Поток чтения из файла (FileReader) должен быть закрыт.
4. Программа должна записывать через запятую во второй файл все слова из первого файла длина которых строго больше 6(используй FileWriter).
5. Поток записи в файл (FileWriter) должен быть закрыт.
*/

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileReader(args[0]));
             FileWriter fileWriter = new FileWriter(args[1])) {

            ArrayList<String> list = new ArrayList<>();
            String str;

            while (scanner.hasNext()) {
                str = scanner.next();
                if (str.length() > 6) list.add(str);
            }

            for (int i = 0; i < list.size(); i++) {
                fileWriter.write(list.get(i));
                if (i != list.size()-1) fileWriter.write(",");
            }
        } catch (Exception e) {}
    }
}
