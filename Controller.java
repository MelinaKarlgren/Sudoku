package kth.se.melinaka.sudoku.view;

import javafx.scene.control.Alert;
import javafx.stage.*;
import kth.se.melinaka.sudoku.model.*;
import java.io.*;
import java.lang.System;

/**
 * controller class decleration
 */
public class Controller {
    private SquareMatrix model;
    private GameView view;
    private int input;

    /**
     * controller constructor
     *
     * @param model
     * @param view
     */

    public Controller(SquareMatrix model, GameView view) {
        this.model = model;
        this.view = view;
    }


    public void handleInput(int input) {
        this.input = input;
    }


    public void handleIndexInput(int row, int col) {
        model.setSquare(row, col, input);
        view.updateGridView(model.getSquareMatrix());
    }

    public void handleClearAll() {
        model.clearAll();
        view.updateGridView(model.getSquareMatrix());
    }

    public void handleCheck() {
        view.checkCorrectView(model.getSquareMatrix());
    }

    public void handleChooseLevel(SudokuUtilities.SudokuLevel level) {
        model.newGameMatrix(level);
        view.updateGridView(model.getSquareMatrix());
    }

    public void handleSaveFile() throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save to:");
        Stage fileWindow = new Stage();

        File selectedFile = fileChooser.showSaveDialog(fileWindow);
        //serialize model to file

        if (selectedFile != null) {
            ObjectOutputStream oos = null;
            try {
                FileOutputStream fout = new FileOutputStream(selectedFile);
                oos = new ObjectOutputStream(fout);
                oos.writeObject(model);

                System.out.println("Serializing successfully completed");
            } finally {
                oos.close();
            }
        } else {
            throw new FileNotFoundException();
        }
    }

    public void handleOpenFile() throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open:");
        //filtrera filer med extenstionfilter
        Stage fileWindow = new Stage();
        File selectedFile = fileChooser.showOpenDialog(fileWindow);
        //deserialize model from file
        if (selectedFile != null) {
            ObjectInputStream ois = null;
            try {
                FileInputStream fin = new FileInputStream(selectedFile);
                ois = new ObjectInputStream(fin);

                // Downcast!
                model = (SquareMatrix) ois.readObject();

                System.out.println("Deserializing successfully completed");
                System.out.println(model.toString());

            } finally {
                ois.close();
            }
        } else {
            throw new FileNotFoundException();
        }
        view.updateGridView(model.getSquareMatrix());
        // link to model
    }

    public void handleExit() throws Exception {
        handleSaveFile();
        System.exit(0);
    }

    public void handleHint() {
        model.hint();
        view.updateGridView(model.getSquareMatrix());
    }

    public void handleHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Sudoku rules: ");
        alert.setTitle("Help");
        alert.setContentText("The rules for sudoku are simple. " +
                "\nA 9×9 square must be filled in with numbers from 1-9" +
                "\nwith no repeated numbers in each line, horizontally or " +
                "\nvertically. To challenge you more, there are 3×3 squares " +
                "\nmarked out in the grid, and each of these squares can't " +
                "\nhave any repeated numbers either.");
        alert.showAndWait();
    }

    public void handleSolved() {
        if (model.numberOfFilled() == 81) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Puzzle");
            alert.setHeaderText("Puzzle solved???");
            if (model.numberOfCorrect() == 81) {
                alert.setContentText("Congrats! You solved the Sudoku Puzzle correctly!");
            } else {
                alert.setContentText("Sorry! You haven't solved the Puzzle Correctly yet. Try again!");
            }
            alert.showAndWait();
        }
    }
}
