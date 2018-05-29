package com.javarush.task.task18.task1828;

/* 
Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD

Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id

Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u - обновляет данные товара с заданным id
-d - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19847   Шорты пляжные синие           159.00  12
198479  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234

Требования:
1. Программа должна считать имя файла для операций CrUD с консоли.
2. При запуске программы без параметров список товаров должен остаться неизменным.
3. При запуске программы с параметрами "-u id productName price quantity" должна обновится информация о товаре в файле.
4. При запуске программы с параметрами "-d id" строка товара с заданным id должна быть удалена из файла.
5. Созданные для файлов потоки должны быть закрыты.
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        String fileName;
        if (args[0].equals("-d")) {
            try (Scanner scannerName = new Scanner(System.in);
                 Scanner scannerFile = new Scanner(new FileInputStream(fileName = scannerName.nextLine()))) {
                ArrayList<String> strings = new ArrayList<>();
                while (scannerFile.hasNext()) strings.add(scannerFile.nextLine());

                try (FileOutputStream fos = new FileOutputStream(fileName)) {
                    for (int i = 0; i < strings.size(); i++) {
                        if (strings.get(i).substring(0, 8).trim().equals(args[1])) i++;
                        fos.write((strings.get(i) + "\n").getBytes());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (args[0].equals("-u")) {
            try (Scanner scannerName = new Scanner(System.in);
                 Scanner scannerFile = new Scanner(new FileInputStream(fileName = scannerName.nextLine()))) {
                ArrayList<String> strings = new ArrayList<>();
                while (scannerFile.hasNext()) strings.add(scannerFile.nextLine());

                try (FileOutputStream fos = new FileOutputStream(fileName)) {
                    for (int i = 0; i < strings.size(); i++) {
                        if (strings.get(i).substring(0, 8).trim().equals(args[1])) {
                            String s = strings.get(i);
                            s = String.format("%-8s%-30s%-8s%-4s%n", args[1], args[2], args[3], args[4]);
                            fos.write(s.getBytes());
                            i++;
                        }
                        fos.write((strings.get(i) + "\n").getBytes());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
