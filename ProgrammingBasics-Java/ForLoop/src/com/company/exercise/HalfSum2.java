package com.company.exercise;

import java.util.Scanner;

public class HalfSum2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int maxNum = Integer.MIN_VALUE, sum = 0;
        int sumWithoutMaxNum = 0;

        for (int i = 1; i <= n; i++) {

            int num = Integer.parseInt(sc.nextLine());

            sum += num;

            if (num > maxNum) {
                maxNum = num;
            }



        }

        sumWithoutMaxNum = sum - maxNum;

        if (maxNum == sumWithoutMaxNum) {

            System.out.println("Yes");
            System.out.println("Sum = "+ maxNum);
        } else {
            System.out.println("No");
            System.out.println("Diff = "+ (Math.abs(maxNum - sumWithoutMaxNum)));
        }
    }

}
