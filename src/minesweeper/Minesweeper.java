
package minesweeper;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import java.util.stream.Stream;
import javafx.scene.input.MouseButton;
import javafx.scene.Node;
import java.awt.event.MouseEvent;


public class Minesweeper extends Application
{
    // n = number of tiles horizontal
    // m = number of tiles vertical
    private int n;
    private int m;

    // width and height of the application
    double appH = 250;
    double appW = 250;

    // width and height of the game tiles
    int gameH = 32;
    int gameW = 32;

    char[][] mineField;

    BorderPane layout;

    InitiateBoard board;

    int mines;

    Tile[][] killmenow;

    @Override
    public void start(Stage primaryStage)
    {
        Menu mBar1 = new Menu("Game");
        MenuItem beginner = new MenuItem("Beginner");
        MenuItem intermediate = new MenuItem("Intermediate");
        MenuItem expert = new MenuItem("Expert");
        Menu mBar2 = new Menu("Help");

        MenuBar menuBar = new MenuBar();

        mBar1.getItems().add(beginner);
        mBar1.getItems().add(intermediate);
        mBar1.getItems().add(expert);

        mBar2.getItems().add(new MenuItem("Instructions"));
        mBar2.getItems().add(new MenuItem("About"));

        menuBar.getMenus().addAll(mBar1,mBar2);

        layout = new BorderPane();
        layout.setTop(menuBar);

        Group grid = new Group();

        beginner.setOnAction(new EventHandler<ActionEvent>()
        {
          public void handle(ActionEvent t)
          {
            board = new InitiateBoard(1);
            mineField = board.getBoard();
            n = board.getWidth();
            m = board.getHeight();
            mines = board.getMines();
            killmenow = new Tile[n][m];
            updateGrid(grid, board);
          }
        });

        intermediate.setOnAction(new EventHandler<ActionEvent>()
        {
          public void handle(ActionEvent t)
          {
            board = new InitiateBoard(2);
            mineField = board.getBoard();
            n = board.getWidth();
            m = board.getHeight();
            mines = board.getMines();
            killmenow = new Tile[n][m];
            updateGrid(grid, board);

          }
        });

        expert.setOnAction(new EventHandler<ActionEvent>()
        {
          public void handle(ActionEvent t)
          {
            board = new InitiateBoard(3);
            mineField = board.getBoard();
            n = board.getWidth();
            m = board.getHeight();
            mines = board.getMines();
            killmenow = new Tile[n][m];
            updateGrid(grid, board);

          }
        });



        layout.setLeft(grid);
        // layout = new BorderPane();

        Text scoreBar = new Text("gay faggot");
        //layout = new BorderPane();
        layout.setBottom(scoreBar);
        //layout.setCenter();

        // StackPane root = new StackPane();
        Scene scene = new Scene(layout, appH, appW);

        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    private void updateGrid(Group grid, InitiateBoard board)
    {
      grid.getChildren().clear();

      String[][] strArr = new String[mineField.length][mineField[0].length];
      for (int i = 0; i < mineField.length; i++)
      {
          for (int j = 0; j < mineField[0].length; j++)
          {
              strArr[i][j] = Character.toString(mineField[i][j]);
          }
      }

      for( int i=0; i < n; i++) {
          for( int j=0; j < m; j++) {

              // create node
              // Tile tile = new Tile( "0", i * gridWidth, j * gridHeight, 50, 50
              Tile tile = new Tile(strArr[i][j], i * gameW, j * gameH, gameW, gameH, grid, board);
              killmenow[i][j] = tile;
              // add node to group
              grid.getChildren().add(tile);
          }
      }

      for( int i=0; i < n; i++) {
          for( int j=0; j < m; j++) {

              killmenow[i][j].setArray(killmenow);

          }
      }

    }
}
