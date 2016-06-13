package com.game.sudarshan.tic_tac_toe.gameui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.game.sudarshan.tic_tac_toe.R;
import com.game.sudarshan.tic_tac_toe.soundeffects.SoundInterface;

public class StartScreenActivity extends AppCompatActivity implements SoundInterface {
    private  MediaPlayer startScreenMusic;
    private int startAudio,exitAudio;
    SoundPool screenSound;
    private static final String EXIT_STRING="Please press Exit Button to exit the Game";
    private static final String EXIT_TIP="Exit Tip";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        screenSound=new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        loadSounds();
        playBackgroundAudio();
    }

    //this start game method is for the click event of the start game button i.e to start the game

   public void startGame(View v){
       playSound(startAudio);
       Intent intent=new Intent(this,GameImageActivity.class);
       startActivity(intent);
       stopSound();
   }


    //for the functionality of exit button i.e to exit the game
    public void exit(View v){
        playSound(exitAudio);
        finish();
    }

    //code to play the BackGround Music
    private  void playBackgroundAudio(){
        new Thread(){
            @Override
            public void run() {
                startScreenMusic=MediaPlayer.create(getApplicationContext(), R.raw.startscreenmusic);
                startScreenMusic.start();
                startScreenMusic.setLooping(true);
            }
        }.start();

    }

//Find the description of these three methods in the SoundInterface interface
    @Override
    public void loadSounds() {
        startAudio=screenSound.load(getApplicationContext(), R.raw.startbutton, 1);
        exitAudio=screenSound.load(getApplicationContext(),R.raw.exitgamescreen,1);
    }

    @Override
    public void playSound(int id) {
        screenSound.play(id,1,1,1,0,1);
    }

    @Override
    public void stopSound() {
        startScreenMusic.stop();
    }


    //Replaying the Background Music when the Activity is Restarted
    @Override
    protected void onRestart() {
        super.onRestart();
        playBackgroundAudio();
    }
    //stopping the sound when the Activity is Stopped
    @Override
    protected void onStop() {
        super.onStop();
        stopSound();
    }
//releasing the SoundPool Object and the MediaPlayer object when Activity is destroyed
    @Override
    protected void onDestroy() {
        super.onDestroy();
        startScreenMusic.release();
        screenSound.release();
    }
/*
This BackPressed is Overridden as when we press the back button while navigating on the Main Menu and if we press this
a Dialog Box will appear notifying that only by pressing the exit button we can exit the game
 */
    @Override
    public void onBackPressed() {
        AlertDialog.Builder exitTipBuilder=new AlertDialog.Builder(this);
        exitTipBuilder.setTitle(EXIT_TIP);
        exitTipBuilder.setCancelable(false);
        exitTipBuilder.setMessage(EXIT_STRING);
        exitTipBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog exitTipDialog=exitTipBuilder.create();
        exitTipDialog.show();
    }
}
//End of the StartScreenActivity class