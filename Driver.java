import java.util.Scanner;
import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scan1 = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        Scanner scan3 = new Scanner(System.in);
        Scanner scan4 = new Scanner(System.in);
        System.out.println("How big of a board (Enter an even number)?");
        int boardSize = scan1.nextInt();
        WordGame game1 = new WordGame(boardSize);
        int h, i, j, k;
        while ( (game1.allRevealed(game1, boardSize) != true) && (boardSize % 2 == 0) )
        {
            WordGame.printBoard(game1, boardSize);

            do
            {
                System.out.println("Enter First Row");
                h = scan1.nextInt();
            } while (h >= boardSize);

            do
            {
                System.out.println("Enter First Column");
                i = scan2.nextInt();
            } while (i >= boardSize);

            game1.showPosition(game1, h, i);
            WordGame.printBoard(game1, boardSize);

            do
            {
                System.out.println("Enter Second Row");
                j = scan3.nextInt();
            } while (j >= boardSize);

            do
            {
                System.out.println("Enter Second Column");
                k = scan4.nextInt();
            } while (k >= boardSize);

            game1.showPosition(game1, j, k);
            game1.printBoard(game1, boardSize);

            if (game1.match(game1, h, i, j, k) == false)
            {
                game1.hidePosition(game1, h, i);
                game1.hidePosition(game1, j, k);
            }
            else
            {
                game1.showPosition(game1, h, i);
                game1.showPosition(game1, j, k);
            }
        }
        System.out.println("YOU WIN!");
    }
}

public class WordGame
{
    private char[][] charArray;
    private boolean[][] boolArray;

    WordGame()
    {
        charArray = new char[2][2];
        boolArray = new boolean[2][2];
    }

    WordGame(int bs)
    {
        if (bs % 2 == 0)
        {
            charArray = new char[bs][bs];
            boolArray = new boolean[bs][bs];
            fillBoard(bs);
        }
        else
        {
            System.out.println("Dimension must be even");
        }
    }

    void fillBoard(int bs)
    {
        Random rd = new Random();
        char myChar = 'A';
        int count1 = 0;
        int count2 = 0;
        int row, col;
        do
        {
            do
            {
                row = rd.nextInt(bs);
                col = rd.nextInt(bs);
                if (charArray[row][col] == '\u0000')
                {
                    charArray[row][col] = myChar;
                    count1++;
                }
            } while (count1 < 2);
            myChar++;
            count1 = 0;
            count2++;
        } while (count2 < (bs*bs/2) );
    }

    static void printBoard(WordGame myGame, int bs)
    {
        int i, j;
        System.out.print("   ");
        for (i = 0; i < bs; i++)
        {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("   -------");
        for (i = 0; i < bs; i++)
        {
            System.out.print(i + " | ");
            for (j = 0; j < bs; j++)
            {
                if (myGame.boolArray[i][j] == true)
                {
                    System.out.print(myGame.charArray[i][j] + " ");
                }
                else
                {
                    System.out.print("+ ");
                }
            }
            System.out.println();
        }
    }

    static void showPosition(WordGame myGame, int row, int column)
    {
        myGame.boolArray[row][column] = true;
    }

    static void hidePosition(WordGame myGame, int row, int column)
    {
        myGame.boolArray[row][column] = false;
    }

    static boolean match(WordGame myGame, int r1, int c1, int r2, int c2)
    {
        if (myGame.charArray[r1][c1] == myGame.charArray[r2][c2])
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    static boolean allRevealed(WordGame myGame, int bs)
    {
        int falseCounter = 0;
        for (int i = 0; i < bs; i++)
        {
            for (int j = 0; j < bs; j++)
            {
                if (myGame.boolArray[i][j] == false)
                {
                    falseCounter++;
                }
            }
        }
        if (falseCounter > 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}