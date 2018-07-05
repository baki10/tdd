package com.bakigoal.game.tdd;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Connect4TDD {

  private static final int ROWS = 6;
  private static final int COLUMNS = 7;
  private static final String EMPTY = " ";
  private static final String RED = "R";
  private static final String GREEN = "G";
  private static final String DELIMITER = "|";
  private static final int DISCS_TO_WIN = 4;

  private String[][] board = new String[ROWS][COLUMNS];
  private String currentPlayer = RED;
  private PrintStream outputChannel;
  private String winner = "";

  public Connect4TDD(PrintStream out) {
    outputChannel = out;
    for (String[] row : board) {
      Arrays.fill(row, EMPTY);
    }
  }

  public int getNumberOfDiscs() {
    return IntStream.range(0, COLUMNS)
        .map(this::getNumberOfDiscsInColumn).sum();
  }

  private int getNumberOfDiscsInColumn(int column) {
    return (int) IntStream.range(0, ROWS)
        .filter(row -> !EMPTY.equals(board[row][column])).count();
  }

  public int putDiscInColumn(int column) {
    checkColumn(column);
    int row = getNumberOfDiscsInColumn(column);
    checkPositionToInsert(row, column);
    board[row][column] = currentPlayer;
    checkWinner(row, column);
    printBoard();
    switchPlayer();
    return row;
  }

  private void checkColumn(int column) {
    if (column < 0 || column >= COLUMNS) throw new RuntimeException("Invalid column " + column);
  }


  private void checkPositionToInsert(int row, int column) {
    if (row == ROWS) throw new RuntimeException("No more room in column " + column);
  }

  private void switchPlayer() {
    currentPlayer = RED.equals(currentPlayer) ? GREEN : RED;
  }

  public String getCurrentPlayer() {
    outputChannel.printf("Player %s turn%n", currentPlayer);
    return currentPlayer;
  }

  private void printBoard() {
    for (int row = ROWS - 1; row >= 0; row--) {
      StringJoiner stringJoiner = new StringJoiner(DELIMITER, DELIMITER, DELIMITER);
      Stream.of(board[row]).forEachOrdered(stringJoiner::add);
      outputChannel.println(stringJoiner.toString());
    }
  }

  public boolean isFinished() {
    return getNumberOfDiscs() == ROWS * COLUMNS;
  }

  public String getWinner() {
    return winner;
  }

  private void checkWinner(int row, int column) {
    if (winner.isEmpty()) {
      String colour = board[row][column];
      Pattern winPattern = Pattern.compile(".*" + colour + "{" + DISCS_TO_WIN + "}.*");

      // vertical
      String vertical = IntStream.range(0, ROWS)
          .mapToObj(r -> board[r][column])
          .reduce(String::concat).orElse(EMPTY);
      // horizontal
      String horizontal = Stream.of(board[row])
          .reduce(String::concat).orElse(EMPTY);

      if (winPattern.matcher(vertical).matches() || winPattern.matcher(horizontal).matches()) {
        winner = colour;
        return;
      }

      // diagonal 1
      int startOffset = Math.min(column, row);
      int myColumn = column - startOffset,
          myRow = row - startOffset;
      StringJoiner stringJoiner = new StringJoiner("");
      do {
        stringJoiner.add(board[myRow++][myColumn++]);
      } while (myColumn < COLUMNS && myRow < ROWS);
      if (winPattern.matcher(stringJoiner.toString()).matches()) {
        winner = currentPlayer;
        return;
      }
      // diagonal 2
      startOffset = Math.min(column, ROWS - 1 - row);
      myColumn = column - startOffset;
      myRow = row + startOffset;
      stringJoiner = new StringJoiner("");
      do {
        stringJoiner.add(board[myRow--][myColumn++]);
      } while (myColumn < COLUMNS && myRow >= 0);
      if (winPattern.matcher(stringJoiner.toString()).matches()) {
        winner = currentPlayer;
      }
    }
  }
}
