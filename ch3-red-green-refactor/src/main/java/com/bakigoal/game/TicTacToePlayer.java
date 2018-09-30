package com.bakigoal.game;

public enum TicTacToePlayer {
    X('X'), O('O');

    private final char sign;

    TicTacToePlayer(char sign) {
        this.sign = sign;
    }

    public char getSign() {
        return sign;
    }

    public TicTacToePlayer nextPlayer() {
        return this == TicTacToePlayer.O ? TicTacToePlayer.X : TicTacToePlayer.O;
    }
}
