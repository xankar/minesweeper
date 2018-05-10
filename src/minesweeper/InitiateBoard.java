
package minesweeper;

import java.util.Random;

public class InitiateBoard
{
    int xTiles; // minefield width
    int yTiles; // minefield height

    int mines;  // no. of mines

    char[][] mineField; // mineField data

    public InitiateBoard(int selection) // 1 for Beginner, 2 for Intermediate, 3 for Expert, 0 for Custom
    {
        switch(selection)
        {
          case 1: beginnerBoard();
          break;
          case 2: interBoard();
          break;
          case 3: expertBoard();
          break;
          default: beginnerBoard();
          break;
        }
    }

    private void beginnerBoard()
    {
      emptyField(8,8);
      generateMines(10);
      drawMinefield();
    }

    private void interBoard()
    {
      emptyField(16,16);
      generateMines(40);
      drawMinefield();
    }

    private void expertBoard()
    {
      emptyField(31,16);
      generateMines(99);
      drawMinefield();
    }

    public void emptyField(int x, int y)
    {
      xTiles = x;
      yTiles = y;

      mineField = new char[xTiles][yTiles];

      for (int xP = 0; xP < xTiles; xP++)
      {
        for (int yP = 0; yP < yTiles; yP++)
        {
            mineField[xP][yP] = '0';
        }
      }
    }

    public void generateMines(int nMines)
    {
        mines = nMines;

        for (int x = 0; x < xTiles; x++)
        {
            for(int y = 0; y < yTiles; y++)
            {
              if(nMines != 0)
              {
                mineField[x][y] = '*';

                nMines -= 1;
              }
            }
        }

        shuffle(mineField);
        generateNumbers();
    }

    private void generateNumbers()
    {
      for(int x = 0; x < xTiles; x++)
      {
        for(int y = 0; y < yTiles; y++)
        {
          if(mineField[x][y] != '*')
          {
            int counter = 0;

            if (x - 1 >= 0 && y + 1 < yTiles && mineField[x - 1][y + 1] == '*'){counter++;}
            if (x - 1 >= 0 && mineField[x - 1][y] == '*'){counter++;}
            if (x - 1 >= 0 && y - 1 >= 0 && mineField[x - 1][y - 1] == '*'){counter++;}
            if (y - 1 >= 0 && mineField[x][y - 1] == '*'){counter++;}
            if (x + 1 < xTiles && y - 1 >= 0 && mineField[x + 1][y - 1] == '*'){counter++;}
            if (x + 1 < xTiles && mineField[x + 1][y] == '*'){counter++;}
            if (x + 1 < xTiles && y + 1 < yTiles && mineField[x + 1][y + 1] == '*'){counter++;}
            if (y + 1 < yTiles && mineField[x][y + 1] == '*'){counter++;}

            String test = String.valueOf(counter);
            char c = test.charAt(0);

            mineField[x][y] = c;
          }
        }
      }
    }

    public void drawMinefield() {
  for(int x = 0; x < xTiles; x ++) {
    for(int y = 0; y < yTiles; y ++) {
      System.out.print(mineField[x][y]);
    }
    System.out.print("\n");
  }
}

    void shuffle(char[][] a)
    {
        Random random = new Random();

        for (int i = a.length - 1; i > 0; i--)
        {
            for (int j = a[i].length - 1; j > 0; j--)
            {
                int m = random.nextInt(i + 1);
                int n = random.nextInt(j + 1);

                char temp = a[i][j];
                a[i][j] = a[m][n];
                a[m][n] = temp;
            }
        }
    }

    public char[][] getBoard()
    {
      return mineField;
    }

    public int getWidth()
    {
      return xTiles;
    }

    public int getHeight()
    {
      return yTiles;
    }

    public int getMines()
    {
      return mines;
    }
}
