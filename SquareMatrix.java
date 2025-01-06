package kth.se.melinaka.sudoku.model;

import java.io.Serializable;
import java.util.Random;

/**
 * class declaration of Square Matrix implements serializable
 */
public class SquareMatrix implements Serializable {
    public static final int GRID_SIZE = 9;
    private Square[][] squareMatrix;
    private int randomRow;
    private int randomCol;

    /**
     * Square matrix constructor
     */
    public SquareMatrix() {
        squareMatrix = SudokuUtilities.generateSudokuMatrix(SudokuUtilities.SudokuLevel.EASY);
    }

    /**
     * newGame sets square matrix to chosen level
     * @param level
     */
    public void newGameMatrix(SudokuUtilities.SudokuLevel level) {
        squareMatrix = SudokuUtilities.generateSudokuMatrix(level);
    }

    /**
     * clear all sets tile inputs to zero
     */
    public void clearAll() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (!squareMatrix[i][j].isInitiallySet()) {
                    squareMatrix[i][j].setEmpty();
                }
            }
        }
    }
    public int numberOfFilled(){
        int nb = 0;
        for(int i=0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                if(squareMatrix[i][j].getInput() != 0){
                    nb++;
                }
            }
        }
        return nb;
    }
    public int numberOfCorrect(){
        int nb = 0;
        for(int i=0; i<GRID_SIZE; i++){
            for(int j=0; j<GRID_SIZE; j++){
                if(squareMatrix[i][j].getInput() == squareMatrix[i][j].getSolution()){
                    nb++;
                }
            }
        }
        return nb;
    }

    /**
     * set method for square
     * @param i
     * @param j
     * @param newInput
     */

    public void setSquare(int i, int j, int newInput) {
        if (!squareMatrix[i][j].isInitiallySet()) {
            squareMatrix[i][j].setSquare(newInput);
        }
    }

    /**
     * hint shows correct answer in randomly chosen tile
     */
    public void hint() {
        Random rowRan = new Random();
        randomRow = rowRan.nextInt(GRID_SIZE);
        Random colRan = new Random();
        randomCol = colRan.nextInt(GRID_SIZE);
        if (squareMatrix[randomRow][randomCol].getInput() == 0) {
            squareMatrix[randomRow][randomCol].setSolution();
        } else {
            hint();
        }
    }

    /**
     * get-method for updates
     * @return copy of GridSquareMatrix
     */

    public Square[][] getSquareMatrix() {
        Square[][] copyMatrix = new Square[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                copyMatrix[i][j] = squareMatrix[i][j];
            }
        }
        return copyMatrix;
    }

    /**
     * toString squareMatrix
     * @return
     */

    @Override
    public String toString() {
        String info = "[";
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                info += squareMatrix[i][j].toString() + " ,";
            }
            info += "]\n[";
        }
        return info;
    }
}
