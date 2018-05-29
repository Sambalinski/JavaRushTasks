package com.javarush.task.task18.task1827;

/* 
Прайсы
CrUD для таблицы внутри файла.
Считать с консоли имя файла для операций CrUD.

Программа запускается со следующим набором параметров:
-c productName price quantity

Значения параметров:
где id - 8 символов.
productName - название товара, 30 chars (60 bytes).
price - цена, 8 символов.
quantity - количество, 4 символа.
-c - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле.

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity

Данные дополнены пробелами до их длины.

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234

Требования:
1. Программа должна считать имя файла для операций CrUD с консоли.
2. При запуске программы без параметров список товаров должен остаться неизменным.
3. При запуске программы с параметрами "-c productName price quantity" в конец файла должна добавится новая строка с товаром.
4. Товар должен иметь следующий id, после максимального, найденного в файле.
5. Форматирование новой строки товара должно четко совпадать с указанным в задании.
6. Созданные для файлов потоки должны быть закрыты.
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        String fileName;
        if (args[0].equals("-c")) {
            try (Scanner scanner = new Scanner(System.in);
                 BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName = scanner.nextLine()))) {

                int max = 0;
                while (bufferedReader.ready()) {
                    int id = Integer.parseInt(bufferedReader.readLine().substring(0, 8).trim());
                    if (max < id) max = id;
                }

                String id = String.format("%-8d", ++max);
                String productName = String.format("%-30s", args[1]);
                String price = String.format("%-8s", args[2]);
                String quantity = String.format("%-4s", args[3]);

                String outString = "\n" + id+productName+price+quantity;

                try (FileOutputStream out = new FileOutputStream(fileName, true)) {
                    out.write(outString.getBytes());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}