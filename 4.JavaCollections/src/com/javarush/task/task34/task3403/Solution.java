package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/
public class Solution {
    public void recursion(int n) {
        for (int i = 2; i <= n; ) {
            if (n % i == 0) {
                System.out.print(i + " ");
                recursion(n / i);
                return;
            } else i++;
        }
    }

    public static void main(String[] args) {
        new Solution().recursion(132);
    }
}
