package com.javarush.task.task19.task1906;

/* 
Четные символы
Считать с консоли 2 имени файла.
Вывести во второй файл все символы с четным индексом.

Пример первого файла:
text in file
Вывод во втором файле:
eti ie
Закрыть потоки ввода-вывод

Требования:
1. Программа должна считывать имена файлов с консоли (используй BufferedReader).
2. BufferedReader для считывания данных с консоли должен быть закрыт.
3. Программа должна считывать содержимое первого файла (используй FileReader c конструктором String).
4. Поток чтения из файла (FileReader) должен быть закрыт.
5. Программа должна записывать во второй файл все байты из первого файла с четным индексом (используй FileWriter).
6. Поток записи в файл (FileWriter) должен быть закрыт.
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try(FileReader fileReader = new FileReader(new BufferedReader(new InputStreamReader(System.in)).readLine());
            FileWriter fileWriter = new FileWriter(bufferedReader.readLine())){

            for (int i = 1; fileReader.ready(); i++) {
                int data = fileReader.read();
                if (i%2 != 0) continue;
                fileWriter.write(data);
            }
        }catch (Exception e){e.printStackTrace();}
        bufferedReader.close();
    }
}
