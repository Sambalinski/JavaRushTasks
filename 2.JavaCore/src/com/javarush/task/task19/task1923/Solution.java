package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит строки со словами, разделенные пробелом.
Записать через пробел в Файл2 все слова, которые содержат цифры, например, а1 или abc3d.
Закрыть потоки.

Требования:
1. Программа НЕ должна считывать данные с консоли.
2. Программа должна считывать содержимое первого файла (используй FileReader c конструктором String).
3. Поток чтения из файла (FileReader) должен быть закрыт.
4. Программа должна записывать во второй файл все слова из первого файла которые содержат цифры (используй FileWriter).
5. Поток записи в файл (FileWriter) должен быть закрыт.
*/

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(new FileReader(args[0]));
            FileWriter fileWriter = new FileWriter(args[1])) {

            StringBuilder stringBuild = new StringBuilder();

            String s;
            while (scanner.hasNext()) {
                s = scanner.next();
                if (!s.replaceAll("\\D", "").isEmpty()) stringBuild.append(s).append(" ");
            }

            fileWriter.write(stringBuild.toString());
        }catch (Exception e) {e.printStackTrace();}
    }
}
