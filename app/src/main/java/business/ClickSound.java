package business;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import club.glamajestic.healthtrack.R;

public class ClickSound implements ApplicationConstants {
    protected SoundPool sp;
    protected  int play;
    protected int sound;
    public ClickSound(Context ctx){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            createNewSoundPool();
        }else{
            createOldSoundPool();
        }
        play = sp.load(ctx, R.raw.next, 1);


    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void createNewSoundPool(){

        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        sp = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();

    }
    public void play(){
        sp.play(play, 1, 1, 0, 0, 1);
    }

    @SuppressWarnings("deprecation")
    protected void createOldSoundPool(){
       sp = new SoundPool(5,AudioManager.STREAM_MUSIC,0);
    }
}
