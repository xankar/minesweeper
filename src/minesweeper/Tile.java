package minesweeper;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import java.awt.event.MouseEvent;
import javafx.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;


public class Tile extends StackPane
{
        Rectangle rectangle;
        Label label;
        boolean isBomb = false;
        boolean opened = false;

        int x;
        int y;

        int val;

        Tile killmenow[][];

        InitiateBoard board;

        Group grid;

        public Tile( String name, int x, int y, double width, double height, Group grid, InitiateBoard board)
        {
            this.x = x;
            this.y = y;
            this.board = board;
            //val = Integer.parseInt(name);
            this.grid = grid;

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

            setOnMouseClicked(e-> open());
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

        public void open()
        {
          if(opened)
          {
            return;
          }
          else if(isBomb)
          {
            grid.getChildren().clear();
            label = new Label("Game Over");
            grid.getChildren().addAll(label);
            System.out.println("GAME OVER");
          }
          else if(label.getText().contains("0"))
          {
            rectangle.setFill(Color.LIGHTGREY);
            opened = true;
            List<Tile> adjacent;
            adjacent = openEmpty(this);

            for(int i = 0; i < adjacent.size(); i++)
            {
              adjacent.get(i).open();
            }
          }
          else
          {
            opened = true;
            rectangle.setFill(Color.LIGHTGREY);
            label.setVisible(true);
          }
        }

        private List<Tile> openEmpty(Tile tile)
        {
            List<Tile> adjacent = new ArrayList<>();

            if ((tile.x / 32) - 1 >= 0 && (tile.y / 32) + 1 < board.yTiles)              {adjacent.add(killmenow[(tile.x / 32) - 1][(tile.y / 32) + 1]);} // O
            if ((tile.x / 32) - 1 >= 0)                                                  {adjacent.add(killmenow[(tile.x / 32) - 1][(tile.y / 32)]);}     // O
            if ((tile.x / 32) - 1 >= 0 && (tile.y / 32) - 1 >= 0 )                       {adjacent.add(killmenow[(tile.x / 32) - 1][(tile.y / 32) - 1]);}
            if ((tile.y / 32) - 1 >= 0 )                                                 {adjacent.add(killmenow[(tile.x / 32)][(tile.y / 32) - 1]);}
            if ((tile.x / 32) + 1 < board.xTiles && (tile.y / 32) - 1 >= 0)              {adjacent.add(killmenow[(tile.x / 32) + 1][(tile.y / 32) - 1]);}
            if ((tile.x / 32) + 1 < board.xTiles)                                        {adjacent.add(killmenow[(tile.x / 32) + 1][(tile.y / 32)]);}
            if ((tile.x / 32) + 1 < board.xTiles && (tile.y / 32) + 1 < board.yTiles)    {adjacent.add(killmenow[(tile.x / 32) + 1][(tile.y / 32) + 1]);}
            if ((tile.y / 32) + 1 < board.yTiles)                                        {adjacent.add(killmenow[(tile.x / 32)][(tile.y / 32) + 1]);}

            return adjacent;
        }

        public void setArray(Tile[][] killmenow)
        {
          this.killmenow = killmenow;
        }

}
