package kth.se.melinaka.sudoku.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import kth.se.melinaka.sudoku.model.Square;
import kth.se.melinaka.sudoku.model.SquareMatrix;
import kth.se.melinaka.sudoku.model.SudokuUtilities;

import static kth.se.melinaka.sudoku.model.SudokuUtilities.SudokuLevel.*;
import static kth.se.melinaka.sudoku.view.GridView.GRID_SIZE;

public class GameView extends BorderPane {
    private MenuBar menuBar;
    private VBox root;

    private SquareMatrix model;
    private Controller controller;
    private GridView view;
    private Button hintButton;
    private Button checkButton;
    private Menu fileMenu;
    private MenuItem loadItem;
    private MenuItem saveItem;
    private MenuItem exitItem;
    private MenuItem lastLevelItem;
    private Menu gameMenu;
    private MenuItem clearItem;
    private Menu helpMenu;
    private MenuItem rulesItem;
    private Menu levelMenu;
    private MenuItem[] levels;
    private Button[] inpButtons;
    private SudokuUtilities.SudokuLevel level;


    public GameView() {
        super();
        model = new SquareMatrix();
        view = new GridView(model);
        createLeftSideButtons();
        createRightSideButtons();
        this.setCenter(view.getTilePane());
        createMenus();
        root = new VBox(menuBar, this);
        controller = new Controller(model, this);
        addEventHandlers(controller);
        level = SudokuUtilities.SudokuLevel.EASY;
    }

    public VBox getRoot() {
        return this.root;
    }

    private void createLeftSideButtons() {
        VBox leftPane = new VBox();
        checkButton = new Button("check");
        hintButton = new Button("hint");
        checkButton.setStyle("-fx-background-color: plum; -fx-border-color: hotpink; -fx-border-radius: 5");
        hintButton.setStyle("-fx-background-color: orchid; -fx-border-color: mediumorchid; -fx-border-radius: 5");
        leftPane.getChildren().add(checkButton);
        leftPane.getChildren().add(hintButton);
        leftPane.setAlignment(Pos.CENTER_LEFT);
        this.setLeft(leftPane);
    }

    private void createMenus() {
        fileMenu = new Menu("File");
        fileMenu.setStyle("-fx-background-color: yellow;");
        loadItem = new MenuItem("Load Game");
        saveItem = new MenuItem("Save Game");
        exitItem = new MenuItem("Exit");

        fileMenu.getItems().add(loadItem);
        fileMenu.getItems().add(saveItem);
        fileMenu.getItems().add(exitItem);

        gameMenu = new Menu("Game");
        gameMenu.setStyle("-fx-background-color: lightgreen;");
        lastLevelItem = new MenuItem("New game, same level as before");
        levelMenu = new Menu("New game, choose level: ");
        levels = new MenuItem[3];
        levels[0] = new MenuItem("Easy");
        levels[1] = new MenuItem("Medium");
        levels[2] = new MenuItem("Hard");
        for (int i = 0; i < 3; i++) {
            levelMenu.getItems().add(levels[i]);
        }
        gameMenu.getItems().add(levelMenu);

        gameMenu.getItems().add(lastLevelItem);

        helpMenu = new Menu("Help");
        helpMenu.setStyle("-fx-background-color: orange;");
        rulesItem = new MenuItem("Sudoku Rules");
        clearItem = new MenuItem("Clear all");
        helpMenu.getItems().add(rulesItem);
        helpMenu.getItems().add(clearItem);

        this.menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, gameMenu, helpMenu);
    }

    private void createRightSideButtons() {
        VBox rightPane = new VBox();
        inpButtons = new Button[10];
        inpButtons[0] = new Button("C");
        inpButtons[0].setStyle("-fx-background-color: coral; -fx-border-color: red; -fx-border-radius: 5");
        for (int i = 1; i < 10; i++) {
            inpButtons[i] = new Button("" + i);
            inpButtons[i].setStyle("-fx-background-color: lightblue; -fx-border-color: blue; -fx-border-radius: 5");
        }
        for (int i = 0; i < 10; i++) {
            rightPane.getChildren().add(inpButtons[i]);
        }
        rightPane.setAlignment(Pos.CENTER_RIGHT);
        this.setRight(rightPane);
    }

    public void updateGridView(Square[][] matrix) {
        this.view.updateGridView(matrix);
    }

    public void checkCorrectView(Square[][] matrix) {
        this.view.checkCorrectView(matrix);
    }

    private void addEventHandlers(Controller controller) {
        EventHandler<ActionEvent> hintHandler = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                controller.handleHint();
                controller.handleSolved();
            }

        };
        hintButton.addEventHandler(ActionEvent.ACTION, hintHandler);

        EventHandler<ActionEvent> helpHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.handleHelp();
            }
        };

        rulesItem.addEventHandler(ActionEvent.ACTION, helpHandler);

        EventHandler<ActionEvent> checkHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.handleCheck();
            }
        };
        checkButton.addEventHandler(ActionEvent.ACTION, checkHandler);

        EventHandler<ActionEvent> lastLevelHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.handleChooseLevel(level);
            }
        };
        lastLevelItem.addEventHandler(ActionEvent.ACTION, lastLevelHandler);


        EventHandler<ActionEvent> levelsHandler = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (event.getSource() == levels[0]) {
                    level = EASY;
                    controller.handleChooseLevel(level);
                }
                if (event.getSource() == levels[1]) {
                    level = MEDIUM;
                    controller.handleChooseLevel(level);
                }
                if (event.getSource() == levels[2]) {
                    level = HARD;
                    controller.handleChooseLevel(level);
                }
            }
        };
        for (int i = 0; i < 3; i++) {
            levels[i].addEventHandler(ActionEvent.ACTION, levelsHandler);
        }

        EventHandler<ActionEvent> clearAllHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.handleClearAll();
            }
        };
        clearItem.addEventHandler(ActionEvent.ACTION, clearAllHandler);

        EventHandler<ActionEvent> saveToFileHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    controller.handleSaveFile();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        saveItem.addEventHandler(ActionEvent.ACTION, saveToFileHandler);

        EventHandler<ActionEvent> openFileHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    controller.handleOpenFile();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        loadItem.addEventHandler(ActionEvent.ACTION, openFileHandler);

        EventHandler<ActionEvent> exitHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    controller.handleExit();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        exitItem.addEventHandler(ActionEvent.ACTION, exitHandler);

        EventHandler<ActionEvent> inpButtonsHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int input = 0;
                for (int i = 0; i < 10; i++) {
                    if (event.getSource() == inpButtons[i]) {
                        input = i;
                    }
                }
                controller.handleInput(input);
            }
        };
        for (int i = 0; i < 10; i++) {
            inpButtons[i].addEventHandler(ActionEvent.ACTION, inpButtonsHandler);
        }

        EventHandler<MouseEvent> tileClickHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for (int row = 0; row < GRID_SIZE; row++) {
                    for (int col = 0; col < GRID_SIZE; col++) {
                        if (event.getSource() == view.getNumberTiles()[row][col]) {
                            controller.handleIndexInput(row, col);
                            controller.handleSolved();
                        }
                    }
                }
            }
        };
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                view.getNumberTiles()[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, tileClickHandler);
            }
        }
    }
}