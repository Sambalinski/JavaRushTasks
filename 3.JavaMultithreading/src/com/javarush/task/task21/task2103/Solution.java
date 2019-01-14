package com.javarush.task.task21.task2103;

/* 
Все гениальное - просто!
Упростить. Переменные не переименовывать, комментарии не оставлять.

Требования:
1. Метод calculate должен быть статическим.
2. Метод calculate должен возвращать значение типа boolean.
3. Метод calculate должен принимать четыре параметра типа boolean.
4. Метод calculate должен быть максимально упрощен(поведение должно остаться прежним).
        return (a && b && c && !d) || (!a && c) || (!b && c) || (c && d);
*/
public class Solution {
    public static boolean calculate(boolean a, boolean b, boolean c, boolean d) {
        return c && (a && b && !d || !a || !b || d);
    }

    public static void main(String[] args) {

    }
}
