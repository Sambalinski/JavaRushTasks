package com.javarush.task.task18.task1822;

/* 
Поиск данных внутри файла
Считать с консоли имя файла.
Найти в файле информацию, которая относится к заданному id, и вывести ее на экран в виде, в котором она записана в файле.
Программа запускается с одним параметром: id (int).
Закрыть потоки.

В файле данные разделены пробелом и хранятся в следующей последовательности:
id productName price quantity
где id - int.
productName - название товара, может содержать пробелы, String.
price - цена, double.
quantity - количество, int.

Информация по каждому товару хранится в отдельной строке.

Требования:
1. Программа должна считать имя файла с консоли.
2. Создай для файла поток для чтения.
3. Программа должна найти в файле и вывести информацию о id, который передается первым параметром.
4. Поток для чтения из файла должен быть закрыт.
*/

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(new FileInputStream(new Scanner(System.in).nextLine()))){
            String s;
            while (scanner.hasNext()) {
                if ((s = scanner.nextLine()).split("\\s")[0].equals(args[0])) System.out.println(s);
            }
        }catch (Exception e) {e.printStackTrace();}
    }
}
//        try (InputStream in = new FileInputStream(new Scanner(System.in).nextLine())) {
//            StringBuilder sb = new StringBuilder();
//            while (in.available() > 0) sb.append((char) in.read());
//            String[] s = sb.toString().split("\\n");
//            for (int i = 0; i < s.length; i++) if (s[i].split("\\s")[0].equals(args[0])) System.out.println(s[i]);
////            for (int i = 0; i < s.length; i++) if (Integer.parseInt(s[i].split("\\s")[0]) == Integer.parseInt(args[0])) System.out.println(s[i]);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}