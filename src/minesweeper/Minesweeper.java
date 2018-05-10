
package minesweeper;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;


public class Minesweeper extends Application
{
    // n = number of tiles horizontal
    // m = number of tiles vertical
    private int n;
    private int m;

    // width and height of the application
    double appH = 1024;
    double appW = 600;

    // width and height of the game tiles
    int gameH = 32;
    int gameW = 32;

    char[][] mineField;

    BorderPane layout;

    InitiateBoard board;

    int mines;

    Tile[][] gameBoardTiles;

    @Override
    public void start(Stage primaryStage)
    {
        Menu mBar1 = new Menu("Game");
        MenuItem beginner = new MenuItem("Beginner");
        MenuItem intermediate = new MenuItem("Intermediate");
        MenuItem expert = new MenuItem("Expert");
        Menu mBar2 = new Menu("Help");
        MenuItem instructions = new MenuItem("Instructions");
        MenuItem about = new MenuItem("About");

        MenuBar menuBar = new MenuBar();

        mBar1.getItems().add(beginner);
        mBar1.getItems().add(intermediate);
        mBar1.getItems().add(expert);

        mBar2.getItems().add(instructions);
        mBar2.getItems().add(about);

        menuBar.getMenus().addAll(mBar1,mBar2);

        layout = new BorderPane();
        layout.setTop(menuBar);
        
        Text scoreBar = new Text("");

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
            gameBoardTiles = new Tile[n][m];
            updateGrid(grid, board, scoreBar);
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
            gameBoardTiles = new Tile[n][m];
            updateGrid(grid, board, scoreBar);

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
            gameBoardTiles = new Tile[n][m];
            updateGrid(grid, board, scoreBar);

          }
        });

        instructions.setOnAction(new EventHandler<ActionEvent>()
        {
          public void handle(ActionEvent t)
          {
            BorderPane newPane = new BorderPane();
       
            Text Rules = new Text("You are presented with a board of squares. Some squares contain mines (bombs), others don't. If you click on a square containing a bomb, you lose.\n If you manage to click all the squares (without clicking on any bombs) you win.\n" +
"Clicking a square which doesn't have a bomb reveals the number of neighbouring squares containing bombs. Use this information plus some guess work to avoid the bombs.\n" +
"To open a square, point at the square and click on it. To mark a square you think is a bomb, point and right-click.");
            Rules.setTextAlignment(TextAlignment.CENTER);
            newPane.setCenter(Rules);
            Stage stage = new Stage();
            stage.setTitle("Instructions");
            stage.setScene(new Scene(newPane, appH, appW));
            stage.show();
          }
        });
        
        about.setOnAction(new EventHandler<ActionEvent>()
        {
          public void handle(ActionEvent t)
          {
            BorderPane newPane = new BorderPane();
       
            Text About = new Text("CREATED BY: \n\n Jake Manning\n + \n Jonathan Stewart");
            About.setTextAlignment(TextAlignment.CENTER);
            newPane.setCenter(About);
            Stage stage = new Stage();
            stage.setTitle("About");
            stage.setScene(new Scene(newPane, 450, 450));
            stage.show();
          }
        });

        layout.setCenter(grid);
        // layout = new BorderPane();
        //layout = new BorderPane();
        layout.setBottom(scoreBar);
        //layout.setCenter();

        // StackPane root = new StackPane();
        Scene scene = new Scene(layout, appH, appW, Color.GREY);

        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    private void updateGrid(Group grid, InitiateBoard board, Text scoreBar)
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
              Tile tile = new Tile(strArr[i][j], i * gameW, j * gameH, gameW, gameH, grid, board, scoreBar);
              gameBoardTiles[i][j] = tile;
              // add node to group
              grid.getChildren().add(tile);
          }
      }

      for( int i=0; i < n; i++) {
          for( int j=0; j < m; j++) {

              gameBoardTiles[i][j].setArray(gameBoardTiles);

          }
      }

    }
}
