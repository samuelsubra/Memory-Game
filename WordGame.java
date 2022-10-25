import java.util.Random;
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