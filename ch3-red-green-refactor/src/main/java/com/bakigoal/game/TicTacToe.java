package com.bakigoal.game;

import java.util.Arrays;
import java.util.stream.Stream;

class TicTacToe {

    private static final char EMPTY = '\0';
    private static final int SIZE = 3;

    private char[][] board = new char[3][3];
    private TicTacToePlayer currentPlayer = TicTacToePlayer.O;

    String play(int x, int y) {
        checkAxis(x);
        checkAxis(y);
        currentPlayer = nextPlayer();
        setBox(x, y, currentPlayer.getSign());
        if (isWin(x, y)) {
            return currentPlayer + " is the winner";
        }
        return isDraw() ? "The result is draw" : "No winner";
    }

    private boolean isDraw() {
        return Stream.of(board).map(Arrays::toString).noneMatch(s -> s.contains("" + EMPTY));
    }

    private boolean isWin(int x, int y) {
        int winningSum = currentPlayer.getSign() * 3;
        int horizontal = 0;
        int vertical = 0;
        int diagonal1 = 0;
        int diagonal2 = 0;

        for (int i = 0; i < SIZE; i++) {
            horizontal += board[i][y - 1];
            vertical += board[x - 1][i];
            diagonal1 += board[i][i];
            diagonal2 += board[i][SIZE - i - 1];
        }

        return horizontal == winningSum || vertical == winningSum || diagonal1 == winningSum || diagonal2 == winningSum;
    }

    private void setBox(int x, int y, char sign) {
        if (board[x - 1][y - 1] != EMPTY) throw new RuntimeException("An occupied space");
        board[x - 1][y - 1] = sign;
    }

    private void checkAxis(int pos) {
        if (pos < 1 || pos > 3) throw new RuntimeException("Position is outside board");
    }

    TicTacToePlayer nextPlayer() {
        return currentPlayer.nextPlayer();
    }
}
