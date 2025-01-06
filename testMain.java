package kth.se.melinaka.sudoku.model;

import java.util.Scanner;

public class testMain {
    public static void main(String[] args) {
        System.out.println("Welcome to sudoku!");
        SquareMatrix s = new SquareMatrix();
        System.out.println(s.toString());
        //System.out.println("Enter row: ");
        //Scanner scan = new Scanner(System.in);
        //int row = Integer.parseInt(scan.next());
        //System.out.println("Enter column: ");
        //int col = Integer.parseInt(scan.next());
        //System.out.println("Enter input (1-9): ");
        //int input = Integer.parseInt(scan.next());
        //s.setSquare(row, col, input);
    }
}
