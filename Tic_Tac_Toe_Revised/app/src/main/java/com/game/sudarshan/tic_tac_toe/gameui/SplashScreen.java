package com.game.sudarshan.tic_tac_toe.gameui;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.game.sudarshan.tic_tac_toe.R;
import com.game.sudarshan.tic_tac_toe.soundeffects.SoundInterface;
/*
This Activity is a SplashScreen in which few animations are performed with ImageView and TextViews
with soundeffects in each animation and at the end a new Activity i.e StartScreenActivity is launched
after all animations are over
 */
public class SplashScreen extends AppCompatActivity implements SoundInterface {
    private ImageView splashImage;
    private Animation imageAnim,ticAnim,tacAnim,toeAnim;
    private TextView tic,tac,toe;
    private int scaleMusic,ticMusic,tacMusic,toeMusic;
    SoundPool gameSounds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        gameSounds=new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        loadSounds();
        initializeViews();
        loadAnimations();
        startAnimation();
    }

    //for the initialization of the Views used in this Activity
    private void initializeViews(){
      splashImage=(ImageView)findViewById(R.id.splash_image);
        tic=(TextView)findViewById(R.id.tic);
        tac=(TextView)findViewById(R.id.tac);
        toe=(TextView)findViewById(R.id.toe);
    }
    /*
    This method is used to load the animations from the which are stored as the XML files in
     the anim Directory present in res Directory .
     */

    private void loadAnimations(){
        imageAnim= AnimationUtils.loadAnimation(this,R.anim.splash_image_scale);
        ticAnim=AnimationUtils.loadAnimation(this,R.anim.tic_anim);
        tacAnim=AnimationUtils.loadAnimation(this,R.anim.tac_anim);
        toeAnim=AnimationUtils.loadAnimation(this,R.anim.toe_anim);
    }
    //This method is used for starting the animation

    private void startAnimation(){
        //animation for the ImageView
        splashImage.setAnimation(imageAnim);
        imageAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                playSound(scaleMusic);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //Animation for the tic TextView
               tic.setAnimation(ticAnim);
                ticAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    playSound(ticMusic);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        //Animation for the toe TextView
                    toe.setAnimation(toeAnim);
                        toeAnim.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                             playSound(tacMusic);
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                //Animation for the tac TextView
                                tac.setAnimation(tacAnim);
                                tacAnim.setAnimationListener(new Animation.AnimationListener() {
                                    @Override
                                    public void onAnimationStart(Animation animation) {
                                      playSound(toeMusic);
                                    }
                                    //Now all Animations are over and starting the StartScreenActivity
                                    @Override
                                    public void onAnimationEnd(Animation animation) {
                                        finish();
                                        Intent i=new Intent(getBaseContext(),StartScreenActivity.class);
                                        startActivity(i);
                                    }

                                    @Override
                                    public void onAnimationRepeat(Animation animation) {

                                    }
                                });
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    //Description of these 3 methods is available in SoundInterface interface
    @Override
    public void loadSounds() {
        scaleMusic=gameSounds.load(getApplicationContext(),R.raw.scale,1);
        ticMusic=gameSounds.load(getApplicationContext(),R.raw.tic,1);
        tacMusic=gameSounds.load(getApplicationContext(),R.raw.tac,1);
        toeMusic=gameSounds.load(getApplicationContext(),R.raw.toe,1);
    }
    @Override
    public void playSound(int id) {
        gameSounds.play(id,1,1,1,0,1);
    }
    @Override
    public void stopSound() {
        gameSounds.release();
    }
    //releasing the SoundPool Object when the Activity is Destroyed.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopSound();
    }
}

//End of SplashScreen class