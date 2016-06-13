
package com.game.sudarshan.tic_tac_toe.game;
/*
This class contains the main logic of the tic tac toe game , methods for the winning conditions,methods for switching to another player
each and everything is available here
 */
public class TicTacToe {
  public static int switchPlayer=0;
  public static final String X_TURN="X_TURN";
  public static final String O_TURN="O_TURN";
  public  static  int playerNumber=1;
  public static int[][] buttonValue=new int[3][3];
  public static final String PLAYER_ONE="PLAYER_0";
  public static final String PLAYER_TWO="PLAYER_X";
  public static final String MATCH_TIE="MATCH_TIE";
  public  static String decideWinner="";
  public static boolean isWinnerAvailable=false;

  //This method is used to pass the values which will help to decide whose player is having the turn either X or O
  public static String changePlayer(){
      switchPlayer++;
    switch(switchPlayer%2) {
      case 0:
        playerNumber = 1;
        return O_TURN;
      case 1:
        playerNumber = 2;
        return X_TURN;

    }
    return null;
  }
  //This method is used to check for the winner
  public static void checkForWinner(){
    if(switchPlayer>=4) {
      checkRows();
      if (!isWinnerAvailable) {
        checkColumns();
        if (!isWinnerAvailable) {
          checkDiagonals();
          if (!isWinnerAvailable && switchPlayer==8)
          {   isWinnerAvailable=true;
            decideWinner= MATCH_TIE;
          }
        }
      }
    }
  }
  //Checking the rows
  private static void checkRows() {
    for(int i=0;i<=2;i++) {
      for (int j = 0; j < 1; j++) {
        if(buttonValue[i][j]==buttonValue[i][j+1] && buttonValue[i][j+1]==buttonValue[i][j+2]){
        rowColumnWinner(i,j);
        }
      }
    }
  }
  //checking the columns
  private static void checkColumns() {
    for(int i=0;i<1;i++) {
      for (int j = 0; j <= 2; j++) {
        if(buttonValue[i][j]==buttonValue[i+1][j] && buttonValue[i+1][j]==buttonValue[i+2][j])
          rowColumnWinner(i,j);
      }
    }
  }
  //deciding if winner is there in rows and columns
  private static void rowColumnWinner(int i,int j){
    isWinnerAvailable=true;
    switch (buttonValue[i][j]){
      case 1:
        decideWinner=PLAYER_ONE;
        break;
      case 2:
        decideWinner=PLAYER_TWO;
        break;
    }
  }
  //checking the diagonals
  private static void checkDiagonals(){
    if(buttonValue[0][0]==buttonValue[1][1] && buttonValue[1][1]==buttonValue[2][2])
      diagonalWinner();
    else if(buttonValue[2][0]==buttonValue[1][1]&&buttonValue[1][1]==buttonValue[0][2])
      diagonalWinner();

  }

  //deciding if winner is there in diagonals
  private static void diagonalWinner(){
    isWinnerAvailable=true;
    switch (buttonValue[1][1]){
      case 1:
        decideWinner=PLAYER_ONE;
        break;
      case 2:
        decideWinner=PLAYER_TWO;
        break;
    }
  }
}
//end of the TicTacToe class