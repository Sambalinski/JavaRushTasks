package com.javarush.task.task19.task1920;

/* 
Самый богатыйСамый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом.

Для каждого имени посчитать сумму всех его значений.
Вывести в консоль имена в алфавитном порядке, у которых максимальная сумма.
Имена разделять пробелом либо выводить с новой строки.
Закрыть потоки.

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров


Требования:
1. Программа НЕ должна считывать данные с консоли.
2. Программа должна считывать содержимое файла (используй FileReader).
3. Поток чтения из файла (FileReader) должен быть закрыт.
4. Программа должна выводить в консоль имена, у которых максимальная сумма.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            TreeMap<String, Double> map = new TreeMap<>();

            String str;

            while (br.ready()) {
                str = br.readLine();
                if (map.get(str.split(" ")[0]) != null)
                    map.put(str.split(" ")[0], map.get(str.split(" ")[0]) + Double.parseDouble(str.split(" ")[1]));
                else map.put(str.split(" ")[0], Double.parseDouble(str.split(" ")[1]));
            }

            String maxStr;
            double max = 0;
            StringBuilder sb = new StringBuilder();

            for (Map.Entry<String, Double> pair : map.entrySet())
                if (pair.getValue() > max) {
                maxStr = pair.getKey();
                max = pair.getValue();
                }

            for (Map.Entry<String, Double> pair : map.entrySet())
                if (pair.getValue() == max) sb.append(pair.getKey() + " ");

            System.out.println(sb);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
