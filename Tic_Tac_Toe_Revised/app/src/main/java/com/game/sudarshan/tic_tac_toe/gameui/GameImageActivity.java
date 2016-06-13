package com.game.sudarshan.tic_tac_toe.gameui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.game.sudarshan.tic_tac_toe.R;
import com.game.sudarshan.tic_tac_toe.game.TicTacToe;
import com.game.sudarshan.tic_tac_toe.soundeffects.SoundInterface;
import com.squareup.picasso.Picasso;

public class GameImageActivity extends AppCompatActivity implements View.OnClickListener,SoundInterface {
    ImageView[][] block;
    Button replayGame;
    TextView turnText;
    private MediaPlayer welcomeAudio;
    private int row;
    private int column;
    private static final int FIRST_PLAYER = 1;
    private static final int SECOND_PLAYER = 2;
    private static final String PLAYER_O = "O";
    private static final String PLAYER_X = "X";
    private static final String REPLAY_TEXT = "Do u wish to replay the game ?";
    private static final String WIN_TEXT="WINNER OF THE GAME";
    private static final String UNUSABLE_BLOCK="can't use same image twice";
    private static final String MAIN_MENU_EXIT="Are you sure you want to exit to the Main Menu ?";
    private static final String WINNER_IS="We have a Winner : ";
    private static final String MATCH_TIE_TEXT="This match is a tie " ;
    private static final String O_URL="http://i.imgur.com/LoguMj5.png";
    private static final String X_URL="http://i.imgur.com/CraMLrZ.png";
    SoundPool soundPool;
    int istMusic,secMusic,replayMusic,exitMusic,gameComp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_image);
        playWelcomeAudio();
        initializeViews();
        settingClickEvents();
        changeInitialValues();
        soundPool=new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        loadSounds();
    }
    //Method to initialize the Views
    private void initializeViews() {
        block = new ImageView[3][3];
        block[0][0] = (ImageView) findViewById(R.id.b_00);
        block[0][1] = (ImageView) findViewById(R.id.b_01);
        block[0][2] = (ImageView) findViewById(R.id.b_02);
        block[1][0] = (ImageView) findViewById(R.id.b_10);
        block[1][1] = (ImageView) findViewById(R.id.b_11);
        block[1][2] = (ImageView) findViewById(R.id.b_12);
        block[2][0] = (ImageView) findViewById(R.id.b_20);
        block[2][1] = (ImageView) findViewById(R.id.b_21);
        block[2][2] = (ImageView) findViewById(R.id.b_22);
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
                row=0;
                column=0;
                clickFunction(row,column);

                break;
            case R.id.b_01:
                row=0;
                column=1;
                clickFunction(row,column);
                break;
            case R.id.b_02:
                row=0;
                column=2;
                clickFunction(row,column);
                break;
            case R.id.b_10:
                row=1;
                column=0;
                clickFunction(row,column);
                break;
            case R.id.b_11:
                row=1;
                column=1;
                clickFunction(row,column);
                break;
            case R.id.b_12:
                row=1;
                column=2;
                clickFunction(row,column);
                break;
            case R.id.b_20:
                row=2;
                column=0;
                clickFunction(row,column);
                break;
            case R.id.b_21:
                    row=2;
                    column=1;
                clickFunction(row,column);
                break;
            case R.id.b_22:
                row=2;
                column=2;
                clickFunction(row,column);
                break;
            case R.id.replay:
                replay();
                replayGameSound();
                break;
        }
    }
    //This method will return the name of Player which is having current turn
    private String player() {
        if (TicTacToe.playerNumber == FIRST_PLAYER)
            return PLAYER_O;
        else
            return PLAYER_X;
    }
    //function to call the current player
    private void playerCall(int row,int column){
        String url=null;
        switch (player()){
            case PLAYER_O:
                TicTacToe.buttonValue[row][column] = FIRST_PLAYER;
                url=O_URL;
                break;
            case PLAYER_X:
                TicTacToe.buttonValue[row][column] = SECOND_PLAYER;
                url=X_URL;
                break;
        }
        loadPlayer(block[row][column], url);
    }
    //The code to load the player image set it into clicked image view
    private void loadPlayer(ImageView imageView,String url){
        Picasso.with(getApplicationContext()).load(url).into(imageView);
    }
    //to decide whether the image view was previously clicked or not
    private boolean isClicked(int row,int column){
        if(TicTacToe.buttonValue[row][column] == 1 || TicTacToe.buttonValue[row][column] == 2) {
            Toast.makeText(getApplicationContext(),UNUSABLE_BLOCK,Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
    //function defining all events occuring on a click event
    private void clickFunction(int row,int column){
        playerSound();
        if(!isClicked(row,column)) {
            playerCall(row, column);
            getWinner();
            turnText.setText(TicTacToe.changePlayer());
        }
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
            switch (TicTacToe.decideWinner) {
                case TicTacToe.PLAYER_ONE:
                    winDialog(replayDialogBuilder,WINNER_IS);
                    break;
                case TicTacToe.PLAYER_TWO:
                    winDialog(replayDialogBuilder,MATCH_TIE_TEXT);
                    break;
                case TicTacToe.MATCH_TIE :
                    createDialog(replayDialogBuilder, WIN_TEXT, " This Match is a tie  " + "\n" + REPLAY_TEXT);
                    break;
            }
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
    //using builder functionalities to add some feature to AlertDialog
    private void createDialog(AlertDialog.Builder builder, String title, String message) {
        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(message);
    }
    //to create a dialog on game completion
    private void winDialog(AlertDialog.Builder builder,String text){
        createDialog(builder, WIN_TEXT, text + TicTacToe.decideWinner + "\n" + REPLAY_TEXT);
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
        switch (player()){
            case PLAYER_O:
                playSound(istMusic);
                break;
            case PLAYER_X:
                playSound(secMusic);
                break;
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
                block[i][j].setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
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
        soundPool.play(id, 1, 1, 1, 0, 1);
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




