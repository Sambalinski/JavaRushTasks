package com.javarush.task.task08.task0812;

import java.io.*;
import java.util.ArrayList;

/* 
Cамая длинная последовательность
1. Создай список чисел.
2. Добавь в список 10 чисел с клавиатуры.
3. Вывести на экран длину самой длинной последовательности повторяющихся чисел в списке.

Пример для списка 2, 4, 4, 4, 8, 8, 9, 12, 12, 14:
3

Искомое значение равно 3, т.к. самая длинная последовательность повторяющихся чисел состоит из трех четверок.

Требования:
1. Программа должна выводить число на экран.
2. Программа должна считывать значения с клавиатуры.
3. В методе main объяви переменную типа ArrayList с типом элементов Integer и сразу проинициализируй ee.
4. Программа должна добавлять в коллекцию 10 чисел, согласно условию.
5. Программа должна выводить на экран длину самой длинной последовательности повторяющихся чисел в списке.
*/
public class Solution {
    // Моё решение:
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList<>();
        list.add(Integer.parseInt(reader.readLine()));
        int max = 1, tmp_max = 1;
        for (int i = 1; i < 10; i++) {
            list.add(Integer.parseInt(reader.readLine()));
            if (list.get(i).equals(list.get(i - 1))) tmp_max++;
            else {
                if (tmp_max > max) max = tmp_max;
                tmp_max = 1;
            }
        }
        System.out.println(max > tmp_max ? max : tmp_max);
    }

//    public static void main(String[] args)throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(Integer.parseInt(reader.readLine()));
//        int max = 1, tmp_max = 1;
//        for(int i = 0, j, k; i < 9; i++) {
//            j = Integer.parseInt(reader.readLine());
//            list.add(j);
////            k = list.get(i);
//            if (j == list.get(i)) tmp_max++;
//            else if (tmp_max > max) {
//                max = tmp_max;
//                tmp_max = 1;
//            }
//        }
//        System.out.println(max > tmp_max ? max : tmp_max);
//    }
//    public static void main(String[] args) throws IOException {
//        ArrayList<Integer> list = reader(new ArrayList<Integer>());
//        maxSequence(list);
//    }
//    public static ArrayList <Integer> reader (ArrayList <Integer> list) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        while (true) {
//            list.add(Integer.parseInt(reader.readLine()));
//            if (list.size() == 10) break;
//        }
//        return list;
//    }
//
//    public static void maxSequence (ArrayList <Integer> list) {
//        int count = 1, temp = 1;
//        for (int i = 0; i < list.size(); i++) {
//            if (i + 1 == list.size()) break;
//            if (list.get(i).equals(list.get(i + 1))) count++;
//            else if (!(list.get(i).equals(list.get(i + 1))) && count > temp) {temp = count; count = 1;}
//        }
//       if (count == 1 && temp == 1) System.out.println(0);
//       else if (count != 1 || temp != 1) System.out.println((count > temp ? count : temp));
//    }
}