package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом.

Для каждого имени посчитать сумму всех его значений.
Все данные вывести в консоль, предварительно отсортировав в возрастающем порядке по имени.
Закрыть потоки.

Пример входного файла:
Петров 2
Сидоров 6
Иванов 1.35
Петров 3.1

Пример вывода:
Иванов 1.35
Петров 5.1
Сидоров 6.0


Требования:
1. Программа НЕ должна считывать данные с консоли.
2. Программа должна считывать содержимое файла (используй FileReader).
3. Поток чтения из файла (FileReader) должен быть закрыт.
4. Программа должна выводить в консоль каждое имя и сумму всех его значений,
        все данные должны быть отсортированы в возрастающем порядке по имени.
*/

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileReader(args[0]))) {
            TreeMap<String, Double> map = new TreeMap<>();

            String s;

            while (scanner.hasNext()) {
                s = scanner.nextLine();
                if (map.get(s.split(" ")[0]) != null)    map.put(s.split(" ")[0], map.get(s.split(" ")[0]) + Double.parseDouble(s.split(" ")[1]));
                else map.put(s.split(" ")[0], Double.parseDouble(s.split(" ")[1]));
            }
            for (Map.Entry <String,Double> pair : map.entrySet())
                System.out.println(pair.getKey() + " " + pair.getValue());
//            map.entrySet().stream()
//                    .sorted(Map.Entry.comparingByValue())
//                    .forEach(r -> System.out.println(r.getKey() + " " + r.getValue()));

        }catch (Exception e) {e.printStackTrace();}
    }
}
