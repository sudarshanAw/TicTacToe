package com.game.sudarshan.tic_tac_toe.soundeffects;
/* This interface defines some of the common methods which are Overridden in the all activities
 to load ,play and stop the sound
  */
public interface SoundInterface {
    //This method is to load the sounds associated with the activity
    public abstract void loadSounds();

    //This method is to playSound associated with animation or the button
    public abstract void playSound(int id);

    //This method is to stop the sound
    public abstract void stopSound();
}
