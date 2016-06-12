package com.game.sudarshan.tic_tac_toe.gameui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.game.sudarshan.tic_tac_toe.R;
import com.game.sudarshan.tic_tac_toe.game.TicTacToe;
import com.game.sudarshan.tic_tac_toe.soundeffects.SoundInterface;
/*
This Activity is the main game activity Where we are supposed to play the Tic Tac Toe Game
 */
public class GameActivity extends AppCompatActivity implements View.OnClickListener,SoundInterface {
    Button[][] block;
    Button replayGame;
    TextView turnText;
    private MediaPlayer welcomeAudio;
    private static final int FIRST_PLAYER = 1;
    private static final int SECOND_PLAYER = 2;
    private static final String PLAYER_O = "O";
    private static final String PLAYER_X = "X";
    private static final String REPLAY_TEXT = "Do u wish to replay the game ?";
    private static final String UNUSABLE_BLOCK="can't use same block twice";
    private static final String WIN_TEXT="WINNER OF THE GAME";
    private static final String MAIN_MENU_EXIT="Are you sure you want to exit to the Main Menu ?";
    SoundPool soundPool;
    int istMusic,secMusic,replayMusic,exitMusic,gameComp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.game.sudarshan.tic_tac_toe.R.layout.activity_game);
        playWelcomeAudio();
        initializeViews();
        settingClickEvents();
        changeInitialValues();
        soundPool=new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        loadSounds();
    }
    //Method to initialize the Views
    private void initializeViews() {
        block = new Button[3][3];
        block[0][0] = (Button) findViewById(R.id.b_00);
        block[0][1] = (Button) findViewById(R.id.b_01);
        block[0][2] = (Button) findViewById(R.id.b_02);
        block[1][0] = (Button) findViewById(R.id.b_10);
        block[1][1] = (Button) findViewById(R.id.b_11);
        block[1][2] = (Button) findViewById(R.id.b_12);
        block[2][0] = (Button) findViewById(R.id.b_20);
        block[2][1] = (Button) findViewById(R.id.b_21);
        block[2][2] = (Button) findViewById(R.id.b_22);
        replayGame = (Button) findViewById(R.id.replay);
        turnText = (TextView) findViewById(R.id.turn_text);
        turnText.setText(TicTacToe.O_TURN);
    }
    //Method to initialize the Click Events of all the Buttons
    private void settingClickEvents() {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                block[i][j].setOnClickListener(this);
            }
        }
        replayGame.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_00:
                if (TicTacToe.checkButtonStatus(block[0][0])) {
                    showToastMessage();
                } else {
                    playerSound();
                    block[0][0].setText(player());
                    if (player().equals(PLAYER_O)) {
                        TicTacToe.buttonValue[0][0] = FIRST_PLAYER;
                    } else
                        TicTacToe.buttonValue[0][0] = SECOND_PLAYER;
                    block[0][0].setTextColor(playerColour());
                    getWinner();
                    turnText.setText(TicTacToe.changePlayer());
                }
                break;
            case R.id.b_01:
                if (TicTacToe.checkButtonStatus(block[0][1])) {
                    showToastMessage();
                } else {
                    playerSound();
                    block[0][1].setText(player());
                    if (player().equals(PLAYER_O)) {
                        TicTacToe.buttonValue[0][1] = FIRST_PLAYER;
                    } else
                        TicTacToe.buttonValue[0][1] = SECOND_PLAYER;
                    block[0][1].setTextColor(playerColour());
                    getWinner();
                    turnText.setText(TicTacToe.changePlayer());
                }
                break;
            case R.id.b_02:
                if (TicTacToe.checkButtonStatus(block[0][2])) {
                    showToastMessage();
                } else {
                    playerSound();
                    block[0][2].setText(player());
                    if (player().equals(PLAYER_O)) {
                        TicTacToe.buttonValue[0][2] = FIRST_PLAYER;
                    } else
                        TicTacToe.buttonValue[0][2] = SECOND_PLAYER;
                    block[0][2].setTextColor(playerColour());
                    getWinner();
                    turnText.setText(TicTacToe.changePlayer());
                }
                break;
            case R.id.b_10:
                if (TicTacToe.checkButtonStatus(block[1][0])) {
                    showToastMessage();
                }
                else {
                    playerSound();
                    block[1][0].setText(player());
                    if (player().equals(PLAYER_O)) {
                        TicTacToe.buttonValue[1][0] = FIRST_PLAYER;
                    } else
                        TicTacToe.buttonValue[1][0] = SECOND_PLAYER;
                    block[1][0].setTextColor(playerColour());
                    getWinner();
                    turnText.setText(TicTacToe.changePlayer());
                }
                break;
            case R.id.b_11:
                if (TicTacToe.checkButtonStatus(block[1][1])) {
                    showToastMessage();
                } else {
                    playerSound();
                    block[1][1].setText(player());
                    if (player().equals(PLAYER_O)) {
                        TicTacToe.buttonValue[1][1] = FIRST_PLAYER;
                    } else
                        TicTacToe.buttonValue[1][1] = SECOND_PLAYER;
                    block[1][1].setTextColor(playerColour());
                    getWinner();
                    turnText.setText(TicTacToe.changePlayer());
                }
                break;
            case R.id.b_12:
                if (TicTacToe.checkButtonStatus(block[1][2])) {
                    showToastMessage();
                } else {
                    playerSound();
                    block[1][2].setText(player());
                    if (player().equals(PLAYER_O)) {
                        TicTacToe.buttonValue[1][2] = FIRST_PLAYER;
                    } else
                        TicTacToe.buttonValue[1][2] = SECOND_PLAYER;
                    block[1][2].setTextColor(playerColour());
                    getWinner();
                    turnText.setText(TicTacToe.changePlayer());
                }
                break;
            case R.id.b_20:
                if (TicTacToe.checkButtonStatus(block[2][0])) {
                    showToastMessage();
                } else {
                    playerSound();
                    block[2][0].setText(player());
                    if (player().equals(PLAYER_O)) {
                        TicTacToe.buttonValue[2][0] = FIRST_PLAYER;
                    } else
                        TicTacToe.buttonValue[2][0] = SECOND_PLAYER;
                    block[2][0].setTextColor(playerColour());
                    getWinner();
                    turnText.setText(TicTacToe.changePlayer());
                }
                break;
            case R.id.b_21:
                if (TicTacToe.checkButtonStatus(block[2][1])) {
                    showToastMessage();
                } else {
                    playerSound();
                    block[2][1].setText(player());
                    if (player().equals(PLAYER_O)) {
                        TicTacToe.buttonValue[2][1] = FIRST_PLAYER;
                    } else
                        TicTacToe.buttonValue[2][1] = SECOND_PLAYER;
                    block[2][1].setTextColor(playerColour());
                    getWinner();
                    turnText.setText(TicTacToe.changePlayer());
                }
                break;
            case R.id.b_22:
                if (TicTacToe.checkButtonStatus(block[2][2])) {
                    showToastMessage();
                } else {
                    playerSound();
                    block[2][2].setText(player());
                    if (player().equals(PLAYER_O)) {
                        TicTacToe.buttonValue[2][2] = FIRST_PLAYER;
                    } else
                        TicTacToe.buttonValue[2][2] = SECOND_PLAYER;
                    block[2][2].setTextColor(playerColour());
                    getWinner();
                    turnText.setText(TicTacToe.changePlayer());
                }
                break;
            case R.id.replay:
                replay();
                replayGameSound();
                break;
        }
    }
    /*A General Function to display the Toast Message when the
      Button is already used by a player and still another player is trying to use it
      */
    private void showToastMessage() {
        Toast.makeText(this, UNUSABLE_BLOCK, Toast.LENGTH_SHORT).show();
    }
    //This method will return the name of Player which is having current turn
    private String player() {
        if (TicTacToe.playerNumber == FIRST_PLAYER)
            return PLAYER_O;
        else
            return PLAYER_X;
    }
    //This method is to set the colour of the player
    private int playerColour(){
        if(player().equals(PLAYER_O))
            return Color.WHITE;
        else
            return Color.BLACK;
    }
    /*
    Here the BackPressed Button is Overridden to make a Alert Dialog Box appear to confirm whether to exit to
    Main Menu or not
     */
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        createDialog(builder, "EXIT",MAIN_MENU_EXIT);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                replay();
                finish();
                exitGameSound();
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if(TicTacToe.isWinnerAvailable)
                    replay();
                replayGameSound();
            }
        });
        final AlertDialog exitDialog = builder.create();
        exitDialog.show();
    }

    /*
    This Method is to declare the Winner and To make a Dialog Appear with the Winner Name
    and Options whether to replay the game or not
     */
    private void getWinner() {
        TicTacToe.checkForWinner();
        if (TicTacToe.isWinnerAvailable) {
            AlertDialog.Builder replayDialogBuilder = new AlertDialog.Builder(this);
            if (TicTacToe.decideWinner.equals(TicTacToe.PLAYER_ONE) || TicTacToe.decideWinner.equals(TicTacToe.PLAYER_TWO)) {
                createDialog(replayDialogBuilder, WIN_TEXT, "Winner is  " + TicTacToe.decideWinner + "\n" + REPLAY_TEXT);
            }
            else if (TicTacToe.decideWinner.equals(TicTacToe.MATCH_TIE))
                createDialog(replayDialogBuilder, WIN_TEXT, " This Match is a tie  " + "\n" + REPLAY_TEXT);
            replayDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    replay();
                    dialog.dismiss();
                    replayGameSound();
                }

            });
            replayDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onBackPressed();
                    exitGameSound();
                }
            });
            AlertDialog replayDialog = replayDialogBuilder.create();
            playSound(gameComp);
            replayDialog.show();
        }
    }
    //a method to create a dialog
    private void createDialog(AlertDialog.Builder builder, String title, String message) {
        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(message);
    }
    /*
    This method is gonna reset all the values to their initial values
     */
    private void replay() {
        changeInitialValues();
        TicTacToe.switchPlayer = 0;
        TicTacToe.isWinnerAvailable = false;
        turnText.setText(TicTacToe.O_TURN);
        TicTacToe.playerNumber = 1;
    }
    /*
    This Method is to play the sound associated with either oneplayer or the secondplayer
     */
    private void playerSound(){
            if (player().equals(PLAYER_O)) {
               playSound(istMusic);
            }
            else {
                playSound(secMusic);
            }
    }
    /*
    To play the sound while exiting to Main Menu and While Pressing No on the Dialog Box for replay
         */
    private void exitGameSound(){
      playSound(exitMusic);
    }
    //To Play the sound if we chose to replay the game or StartOver
    private void replayGameSound(){
     playSound(replayMusic);
    }
    /*
    As all the members of buttonValues[i][j] will have value 0 at the begining which might create a bug in the program
    So,the values of all the members are changed in this method
     */
    private void changeInitialValues(){
        int x=3;
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                TicTacToe.buttonValue[i][j] = x;
                x++;
                block[i][j].setText("");
            }
        }
    }
    //These 3 methods description is provided in the SoundInterface interface
    @Override
    public void loadSounds() {
        istMusic=soundPool.load(getApplicationContext(),R.raw.playerone,1);
        secMusic=soundPool.load(getApplicationContext(),R.raw.playertwo,1);
        replayMusic=soundPool.load(getApplicationContext(),R.raw.replaygame,1);
        exitMusic=soundPool.load(getApplicationContext(),R.raw.exitgamescreen,1);
        gameComp=soundPool.load(getApplicationContext(),R.raw.gamecomplete,1);
    }
    @Override
    public void playSound(int id) {
        soundPool.play(id,1,1,1,0,1);
    }
    @Override
    public void stopSound() {
        welcomeAudio.release();
        soundPool.release();
    }
//This method is used to play the Welcome Audio
    private void playWelcomeAudio(){
        new Thread(){
            @Override
            public void run() {
                welcomeAudio=MediaPlayer.create(getApplicationContext(),R.raw.welcome);
                welcomeAudio.start();
            }
        }.start();
    }
/*
The Mediaplayer Object and the SoundPool Object are Released when the Activity is Destroyed
 */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopSound();
    }
}

//End of the Game Activity