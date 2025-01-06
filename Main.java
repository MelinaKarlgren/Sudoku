package kth.se.melinaka.sudoku;

import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;
import kth.se.melinaka.sudoku.view.*;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameView view = new GameView();
        Scene scene = new Scene(view.getRoot());
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Sudoku");
        primaryStage.show();
    }
}



