package com.lab;

import java.util.Scanner;

public class SquareArea {
    public static void main(String[] args) {
        // code to print the area of square by input

        Scanner scan = new Scanner(System.in);
        int side = Integer.parseInt(scan.nextLine());

        int area = side * side;

        System.out.println(area);

    }
}
