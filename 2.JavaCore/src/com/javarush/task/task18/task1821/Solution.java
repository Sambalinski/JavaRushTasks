package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
Программа запускается с одним параметром - именем файла, который содержит английский текст.
Посчитать частоту встречания каждого символа.
Отсортировать результат по возрастанию кода ASCII (почитать в инете).

Пример:
','=44, 's'=115, 't'=116.

Вывести на консоль отсортированный результат:
[символ1] частота1
[символ2] частота2
Закрыть потоки.

Пример вывода:
, 19
- 7
f 361

Требования:
1. Считывать с консоли ничего не нужно.
2. Создай поток для чтения из файла, который приходит первым параметром в main.
3. В файле необходимо посчитать частоту встречания каждого символа и вывести результат.
4. Выведенный в консоль результат должен быть отсортирован по возрастанию кода ASCII.
5. Поток для чтения из файла должен быть закрыт.
*/

import java.io.FileInputStream;
import java.io.InputStream;

public class Solution {
    public static void main(String[] args) {
        try (InputStream in = new FileInputStream(args[0])) {
            long [] longsOfBytes = new long[256];
            while (in.available() > 0) longsOfBytes[in.read()]++;
            for (int i = 0; i < longsOfBytes.length; i++) if(longsOfBytes[i] > 0) System.out.println((char)i + " " + longsOfBytes[i]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
