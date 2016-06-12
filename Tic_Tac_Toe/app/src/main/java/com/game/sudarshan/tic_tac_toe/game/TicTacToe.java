
package com.game.sudarshan.tic_tac_toe.game;
import android.widget.Button;
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
    if(switchPlayer%2==0) {
      playerNumber = 1;
      return O_TURN;
    }
    else {
      playerNumber = 2;
      return X_TURN;
    }
  }
  //This method is used to check whether the block is already used or already not used
  public static boolean checkButtonStatus(Button button){
    if(!button.getText().toString().isEmpty())
      return true;
    else
      return  false;
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
    if (buttonValue[0][0] == buttonValue[0][1] && buttonValue[0][1] == buttonValue[0][2]) {
      isWinnerAvailable=true;
      if (buttonValue[0][0] == 1) {
        decideWinner=PLAYER_ONE;
      } else
        decideWinner=PLAYER_TWO;
    } else if (buttonValue[1][0] == buttonValue[1][1] && buttonValue[1][1] == buttonValue[1][2]) {
      isWinnerAvailable=true;
      if (buttonValue[1][0] == 1) {
        decideWinner= PLAYER_ONE;
      } else
        decideWinner=PLAYER_TWO;
    } else if (buttonValue[2][0] == buttonValue[2][1] && buttonValue[2][1] == buttonValue[2][2]) {
      isWinnerAvailable=true;
      if (buttonValue[2][0] == 1) {
        decideWinner=PLAYER_ONE;
      } else if(buttonValue[2][0]==2)
        decideWinner=PLAYER_TWO;
    }
  }
  //checking the columns
  private static void checkColumns() {
    if (buttonValue[0][0] == buttonValue[1][0] && buttonValue[1][0] == buttonValue[2][0]) {
      isWinnerAvailable=true;
      if (buttonValue[0][0] == 1) {
        decideWinner=PLAYER_ONE;
      } else
        decideWinner=PLAYER_TWO;
    } else if (buttonValue[0][1] == buttonValue[1][1] && buttonValue[1][1] == buttonValue[2][1]) {
      isWinnerAvailable=true;
      if (buttonValue[0][1] == 1) {
        decideWinner= PLAYER_ONE;
      } else
        decideWinner=PLAYER_TWO;
    } else if (buttonValue[0][2] == buttonValue[1][2] && buttonValue[1][2] == buttonValue[2][2]) {
      isWinnerAvailable=true;
      if (buttonValue[0][2] == 1) {
        decideWinner=PLAYER_ONE;
      } else
        decideWinner=PLAYER_TWO;
    }
  }
  //checking the diagonals
  private static void checkDiagonals(){
    if(buttonValue[0][0]==buttonValue[1][1] && buttonValue[1][1]==buttonValue[2][2]){
      isWinnerAvailable=true;
      if(buttonValue[0][0]==1){
        decideWinner=PLAYER_ONE;
      }
      else
        decideWinner=PLAYER_TWO;
    }
    else if(buttonValue[2][0]==buttonValue[1][1]&&buttonValue[1][1]==buttonValue[0][2]){
      isWinnerAvailable=true;
      if(buttonValue[0][0]==1){
        decideWinner=PLAYER_ONE;
      }
      else
        decideWinner=PLAYER_TWO;
    }
  }

}
//end of the TicTacToe class