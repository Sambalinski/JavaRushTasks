package com.javarush.task.task08.task0807;

import java.util.*;

/* 
LinkedList и ArrayList
Нужно создать два списка – LinkedList и ArrayList.

Требования:
1. Программа не должна выводить текст на экран.
2. Программа не должна считывать значения с клавиатуры.
3. Программа должна содержать только три метода.
4. Метод createArrayList() должен создавать и возвращать список ArrayList.
5. Метод createLinkedList() должен создавать и возвращать список LinkedList.
*/

public class Solution {
    public static Object createArrayList() {
        ArrayList list = new ArrayList();
        return list;
    }

    public static Object createLinkedList() {
        LinkedList list = new LinkedList();
        return list;
    }

    public static void main(String[] args) {

    }
}
