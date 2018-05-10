package minesweeper;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.*;


public class Tile extends StackPane
{
        public static int totalBombs;
        public static int totalTiles;
        public static int flagCounter;
        public static int openedCounter;
        
        Rectangle rectangle;
        Label label;
        boolean isBomb = false;
        boolean opened = false;
        boolean flagged = false;
        
        int x;
        int y;

        int val;

        Tile gameBoardTiles[][];

        InitiateBoard board;

        Group grid;

        public Tile( String name, int x, int y, double width, double height, Group grid, InitiateBoard board, Text scoreBar)
        {
            this.x = x;
            this.y = y;
            this.board = board;
            //val = Integer.parseInt(name);
            this.grid = grid;
            
            this.flagCounter = board.mines;
            this.openedCounter = 0;
            this.totalBombs = board.mines;
            this.totalTiles = board.xTiles * board.yTiles;
            
            scoreBar.setText("Flag Counter: " + flagCounter);

            // create rectangle
            rectangle = new Rectangle(width, height);
            rectangle.setStroke(Color.GREY);
            rectangle.setFill(Color.BLACK);

            // create label
            label = new Label(name);
            label.setVisible(false);

            // detect if rectangle is a bomb
            if(name.contains("*"))
            {
              isBomb = true;
            }

            //Button button = new Button();

            //button.setPrefWidth(width);
            //button.setPrefHeight(height);

            // set position
            setTranslateX(x);
            setTranslateY(y);

            this.setOnMousePressed((MouseEvent event) -> {
                MouseButton button = event.getButton();
                if(button==MouseButton.PRIMARY){
                    open(1, scoreBar);
                }else if(button==MouseButton.SECONDARY){
                    open(2, scoreBar);
                }
            });
        
        //(e->open());
            
            // rectangle.setFill(Color.LIGHTGREY);
            // label.setVisible(true););

        //     setOnAction(new EventHandler<ActionEvent>() {
        //     @Override
        //     public void handle(ActionEvent event) {
        //         rectangle.setFill(Color.LIGHTGREY);
        //         label.setVisible(true);
        //
        //     }
        // });

            getChildren().addAll(rectangle, label);
        }

        
        
        public void open(int clickNum, Text scoreBar)
        {
          if(opened)
          {
            return;
          }
          else if(clickNum == 2 && !flagged)
          {
              rectangle.setFill(Color.BLUE);
              flagged = true;
              flagCounter -= 1;
              scoreBar.setText("Flag Counter: " + flagCounter);
          }
          else if(clickNum == 2 && flagged)
          {
              rectangle.setFill(Color.BLACK);
              flagged = false;
              flagCounter += 1;
              scoreBar.setText("Flag Counter: " + flagCounter);
          }
          else if(isBomb && clickNum == 1)
          {
            grid.getChildren().clear();
            label = new Label("Game Over - YOU LOSE!");
            grid.getChildren().addAll(label);
            scoreBar.setText("");
          }
          else if(label.getText().contains("0") && clickNum == 1)
          {
            rectangle.setFill(Color.LIGHTGREY);
            opened = true;
            openedCounter += 1;
            List<Tile> adjacent;
            adjacent = openEmpty(this);

            for(int i = 0; i < adjacent.size(); i++)
            {
              adjacent.get(i).open(1, scoreBar);
            }
          }
          else if(clickNum == 1)
          {
            opened = true;
            openedCounter += 1;
            rectangle.setFill(Color.LIGHTGREY);
            label.setVisible(true);
          }
          if(totalTiles - openedCounter == totalBombs)
          {
            grid.getChildren().clear();
            label = new Label("Game Over - YOU WIN!");
            grid.getChildren().addAll(label);
            scoreBar.setText("");
          }
        }

        private List<Tile> openEmpty(Tile tile)
        {
            List<Tile> adjacent = new ArrayList<>();

            if ((tile.x / 32) - 1 >= 0 && (tile.y / 32) + 1 < board.yTiles)              {adjacent.add(gameBoardTiles[(tile.x / 32) - 1][(tile.y / 32) + 1]);} // O
            if ((tile.x / 32) - 1 >= 0)                                                  {adjacent.add(gameBoardTiles[(tile.x / 32) - 1][(tile.y / 32)]);}     // O
            if ((tile.x / 32) - 1 >= 0 && (tile.y / 32) - 1 >= 0 )                       {adjacent.add(gameBoardTiles[(tile.x / 32) - 1][(tile.y / 32) - 1]);}
            if ((tile.y / 32) - 1 >= 0 )                                                 {adjacent.add(gameBoardTiles[(tile.x / 32)][(tile.y / 32) - 1]);}
            if ((tile.x / 32) + 1 < board.xTiles && (tile.y / 32) - 1 >= 0)              {adjacent.add(gameBoardTiles[(tile.x / 32) + 1][(tile.y / 32) - 1]);}
            if ((tile.x / 32) + 1 < board.xTiles)                                        {adjacent.add(gameBoardTiles[(tile.x / 32) + 1][(tile.y / 32)]);}
            if ((tile.x / 32) + 1 < board.xTiles && (tile.y / 32) + 1 < board.yTiles)    {adjacent.add(gameBoardTiles[(tile.x / 32) + 1][(tile.y / 32) + 1]);}
            if ((tile.y / 32) + 1 < board.yTiles)                                        {adjacent.add(gameBoardTiles[(tile.x / 32)][(tile.y / 32) + 1]);}

            return adjacent;
        }

        public void setArray(Tile[][] gameBoardTiles)
        {
          this.gameBoardTiles = gameBoardTiles;
        }

}
