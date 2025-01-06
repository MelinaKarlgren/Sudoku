package kth.se.melinaka.sudoku.view;


import kth.se.melinaka.sudoku.model.*;

import javafx.scene.layout.TilePane;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Pos;

public class GridView {
    private Label[][] numberTiles; // the tiles/squares to show in the ui grid
    private TilePane numberPane;
    private SquareMatrix model;

    public static final int GRID_SIZE = 9;
    public static final int SECTIONS_PER_ROW = 3;
    public static final int SECTION_SIZE = 3;

    public GridView(SquareMatrix model) {
        numberTiles = new Label[GRID_SIZE][GRID_SIZE];
        initNumberTiles();
        numberPane = makeNumberPane();
        this.model = model;
    }

    public TilePane getTilePane() {
        return this.numberPane;
    }

    public Label[][] getNumberTiles() {
        return this.numberTiles;
    }

    // called by constructor (only)
    private final void initNumberTiles() {
        SquareMatrix matrix = new SquareMatrix();
        Square[][] sMatrix = matrix.getSquareMatrix();
        Font font = Font.font("Monospaced", FontWeight.BOLD, 20);

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (sMatrix[row][col].getInput() == 0) {
                    Label tile = new Label();
                    tile.setPrefWidth(30);
                    tile.setPrefHeight(30);
                    tile.setFont(font);
                    tile.setAlignment(Pos.CENTER);
                    tile.setStyle("-fx-border-color: black; -fx-border-width: 0.5px;");
                    numberTiles[row][col] = tile;
                } else {
                    Label tile = new Label(sMatrix[row][col].toString()); // data from model
                    tile.setPrefWidth(30);
                    tile.setPrefHeight(30);
                    tile.setFont(font);
                    tile.setAlignment(Pos.CENTER);
                    tile.setStyle("-fx-border-color: black; -fx-border-width: 0.5px;");
                    numberTiles[row][col] = tile;
                }
            }
        }
    }

    private final TilePane makeNumberPane() {
        // create the root tile pane
        TilePane root = new TilePane();
        root.setPrefColumns(SECTIONS_PER_ROW);
        root.setPrefRows(SECTIONS_PER_ROW);
        root.setStyle(
                "-fx-border-color: #000000; -fx-border-width: 1.0px; -fx-background-color: #ffffff;");

        // create the 3*3 sections and add the number tiles
        TilePane[][] sections = new TilePane[SECTIONS_PER_ROW][SECTIONS_PER_ROW];
        int i = 0;
        for (int srow = 0; srow < SECTIONS_PER_ROW; srow++) {
            for (int scol = 0; scol < SECTIONS_PER_ROW; scol++) {
                TilePane section = new TilePane();
                section.setPrefColumns(SECTION_SIZE);
                section.setPrefRows(SECTION_SIZE);
                section.setStyle("-fx-border-color: grey; -fx-border-width: 1.0px;");

                // add number tiles to this section
                for (int row = 0; row < SECTION_SIZE; row++) {
                    for (int col = 0; col < SECTION_SIZE; col++) {
                        // calculate which tile and add
                        section.getChildren().add(
                                numberTiles[srow * SECTION_SIZE + row][scol * SECTION_SIZE + col]);
                    }
                }


                // add the section to the root tile pane
                root.getChildren().add(section);
            }
        }

        return root;
    }

    public void updateGridView(Square[][] matrix) {
        Font font1 = Font.font("Monospaced", FontWeight.BOLD, 20);
        Font font2 = Font.font("Monospaced", FontWeight.NORMAL, 20);
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (!matrix[row][col].isInitiallySet() && matrix[row][col].getInput() != 0) {
                    numberTiles[row][col].setStyle("-fx-text-fill: black; -fx-border-color: black; -fx-border-width: 0.5px;");
                    numberTiles[row][col].setFont(font2);
                    numberTiles[row][col].setText("" + matrix[row][col].getInput());
                }
                if (matrix[row][col].getInput() == 0) {
                    numberTiles[row][col].setText("");
                }
                if (matrix[row][col].isInitiallySet()) {
                    numberTiles[row][col].setStyle("-fx-text-fill: black; -fx-border-color: black; -fx-border-width: 0.5px;");
                    numberTiles[row][col].setFont(font1);
                    numberTiles[row][col].setText("" + matrix[row][col].getInput());
                }
            }
        }

    }

    public void checkCorrectView(Square[][] matrix) {
        Font font = Font.font("Times new roman", FontWeight.NORMAL, 20);
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (matrix[row][col].getInput() != matrix[row][col].getSolution() && matrix[row][col].getInput() != 0) {
                    numberTiles[row][col].setStyle("-fx-text-fill: red; -fx-border-color: black; -fx-border-width: 0.5px;");
                }
            }
        }
    }
}
