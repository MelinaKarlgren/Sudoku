package kth.se.melinaka.sudoku.model;

import java.io.Serializable;

/**
 * class declaration
 */
public class Square implements Serializable {
    private int input;
    private int solution;
    private boolean isCorrect;
    private boolean isInitiallySet;

    /**
     * Square constructor
     *
     * @param input
     * @param solution
     * @param isCorrect
     */
    public Square(int input, int solution, boolean isInitiallySet, boolean isCorrect) {
        this.input = input;
        this.solution = solution;
        this.isCorrect = isCorrect;
        this.isInitiallySet = isInitiallySet;
    }

    public void setSquare(int newInput) {
        this.input = newInput;
    }


    /**
     * setEmpty sets tile input to zero
     */

    public void setEmpty() {
        this.input = 0;
    }

    /**
     * setSolution sets correct solution input in tile
     * getSolution returns solutionInput
     */
    public void setSolution() {
        this.input = this.solution;
        this.isInitiallySet = false;
        this.isCorrect = true;
    }
    public int getSolution() {
        return this.solution;
    }

    /**
     * @return input
     */
    public int getInput() {
        return this.input;
    }

    /**
     * @return true if square is empty or filled and correct
     * @return false if a square is filled but not correct
     */
    public boolean isCorrect() {
        if (this.input == 0 || this.isCorrect) {
            return true;
        }
        return false;
    }

    /**
     * isInitallySet returns true if tile input is initally set
     */
    public boolean isInitiallySet() {
        return isInitiallySet;
    }

    /**
     * toString of Square
     * @return
     */

    @Override
    public String toString() {
        String info = "" + input;
        return info;
    }
}
