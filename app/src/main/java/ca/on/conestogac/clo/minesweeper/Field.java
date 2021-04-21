package ca.on.conestogac.clo.minesweeper;

import java.util.Random;

public class Field {
    private boolean flagged = false;
    private boolean isHidden = true;
    private boolean containsBomb = false;

    public boolean doesContainBomb() {
        return containsBomb;
    }

    private void makeContainBomb() {
        this.containsBomb = true;
    }

    public static Field [] [] createBoard(int dimension, int bombs) {
        Random random = new Random();
        int bombsAdded = 0;
        Field [] [] board = new Field [dimension] [dimension];
        for(int i = 0; i < dimension; i++)
        {
            for (int j = 0; j < dimension; j++)
            {
                Field field = new Field();
                board[i][j] = field;
            }
        }

        while(bombsAdded < bombs) {
            int randomIndex = random.nextInt(((int)Math.pow(dimension, 2)) - 1);
            int row = randomIndex / dimension;
            int col = randomIndex % dimension;

            if(!board[row][col].doesContainBomb()) {
                board[row][col].makeContainBomb();
                bombsAdded++;
            }
        }
        return board;
    }
}
