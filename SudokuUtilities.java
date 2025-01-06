package kth.se.melinaka.sudoku.model;

import java.util.Random;

public class SudokuUtilities {

    public enum SudokuLevel {EASY, MEDIUM, HARD}

    public static final int GRID_SIZE = 9;
    public static final int SECTIONS_PER_ROW = 3;
    public static final int SECTION_SIZE = 3;

    /**
     * Create a 3-dimensional matrix with initial values and solution in Sudoku.
     *
     * @param level The level, i.e. the difficulty, of the initial standing.
     * @return A 3-dimensional int matrix.
     * [row][col][0] represents the initial values, zero representing an empty cell.
     * [row][col][1] represents the solution.
     * @throws IllegalArgumentException if the length of stringRepresentation is not 2*81 characters and
     *                                  for characters other than '0'-'9'.
     */
    public static Square[][] generateSudokuMatrix(SudokuLevel level) {
        String representationString;
        String str;
        switch (level) {
            case EASY:
                representationString = easy;
                str = randomGame(representationString);
                break;
            case MEDIUM:
                representationString = medium;
                str = randomGame(representationString);
                break;
            case HARD:
                representationString = hard;
                str = randomGame(representationString);
                break;
            default:
                representationString = medium;
                str = randomGame(representationString);
        }
        return convertStringToSquareMatrix(str);
    }

    /**
     * randomGame creates a random squareMatrix to start game from
     * @param representationString
     * @return
     */

    private static String randomGame(String representationString) {
        char[] charArray = representationString.toCharArray();
        int random;
        String str = new String();
        Random rn = new Random();
        random = rn.nextInt(3);
        //use switch(random)
        switch (random) {
            case 0:
                for (int i = 0; i < charArray.length; i++) {
                    if (charArray[i] == '7') {
                        charArray[i] = '9';
                    } else if (charArray[i] == '9') {
                        charArray[i] = '7';
                    }
                }
                return str.valueOf(charArray);
            case 1:
                for (int i = 0; i < charArray.length; i++) {
                    if (charArray[i] == '5') {
                        charArray[i] = '3';
                    } else if (charArray[i] == '3') {
                        charArray[i] = '5';
                    }
                }
                return str.valueOf(charArray);
            case 2:
                return str.valueOf(charArray);
        }
        return representationString;
    }

    /**
     * Create a 3-dimensional matrix with initial values and solution in Sudoku.
     *
     * @param stringRepresentation A string of 2*81 characters, 0-9. The first 81 characters represents
     *                             the initial values, '0' representing an empty cell.
     *                             The following 81 characters represents the solution.
     * @return A 3-dimensional int matrix.
     * [row][col][0] represents the initial values, zero representing an empty cell.
     * [row][col][1] represents the solution.
     * @throws IllegalArgumentException if the length of stringRepresentation is not 2*81 characters and
     *                                  for characters other than '0'-'9'.
     */
    /*package private*/
    static Square[][] convertStringToSquareMatrix(String stringRepresentation) {
        if (stringRepresentation.length() != GRID_SIZE * GRID_SIZE * 2)
            throw new IllegalArgumentException("representation length " + stringRepresentation.length());

        Square[][] values = new Square[GRID_SIZE][GRID_SIZE];
        char[] charRepresentation = stringRepresentation.toCharArray();

        int inpIndex = 0;
        int solIndex = 81;
        // initial values + solution values
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                values[row][col] =
                        convertCharToSudokuSquare(charRepresentation[inpIndex++], charRepresentation[solIndex++]);

            }
        }
        return values;
    }

    private static Square convertCharToSudokuSquare(char inp, char sol) {
        if (inp < '0' || inp > '9') throw new IllegalArgumentException("character " + inp);
        int input = inp - '0';
        if (sol < '0' || sol > '9') throw new IllegalArgumentException("character " + sol);
        int solution = sol - '0';
        boolean isSet;
        if (input != 0) {
            isSet = true;
        } else {
            isSet = false;
        }
        Square newSquare = new Square(input, solution, isSet, true);
        return newSquare;
    }


    private static final String easy =
            "000914070" +
                    "010000054" +
                    "040002000" +
                    "007569001" +
                    "401000500" +
                    "300100000" +
                    "039000408" +
                    "650800030" +
                    "000403260" + // solution values after this substring
                    "583914672" +
                    "712386954" +
                    "946752183" +
                    "827569341" +
                    "461238597" +
                    "395147826" +
                    "239675418" +
                    "654821739" +
                    "178493265";

    private static final String medium =
            "300000010" +
                    "000050906" +
                    "050401200" +
                    "030000080" +
                    "002069400" +
                    "000000002" +
                    "900610000" +
                    "200300058" +
                    "100800090" + //
                    "324976815" +
                    "718253946" +
                    "659481273" +
                    "536142789" +
                    "872569431" +
                    "491738562" +
                    "985617324" +
                    "267394158" +
                    "143825697";

    private static final String hard =
            "030600000" +
                    "000010070" +
                    "080000000" +
                    "000020000" +
                    "340000800" +
                    "500030094" +
                    "000400000" +
                    "150800200" +
                    "700006050" + //
                    "931687542" +
                    "465219378" +
                    "287345916" +
                    "876924135" +
                    "349561827" +
                    "512738694" +
                    "693452781" +
                    "154873269" +
                    "728196453";
}
