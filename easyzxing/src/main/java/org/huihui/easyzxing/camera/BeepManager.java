package org.huihui.easyzxing.camera;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Vibrator;

import org.huihui.easyzxing.R;

import static android.content.Context.AUDIO_SERVICE;
import static android.content.Context.VIBRATOR_SERVICE;

/**
 * User: Administrator
 * Date: 2016-12-23 {HOUR}:52
 */
public class BeepManager {
    private Activity mContext;
    private static final long VIBRATE_DURATION = 200L;
    private SoundPool pool;

    public BeepManager(Activity context) {
        mContext = context;
    }

    public void init() {
        AudioManager audioService = (AudioManager) mContext.getSystemService(AUDIO_SERVICE);
        if (pool == null && audioService.getRingerMode() == AudioManager.RINGER_MODE_NORMAL) {
            pool = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
            pool.load(mContext, R.raw.beep, 1);
        }
    }


    public void release() {
        if (pool != null) {
            pool.release();
        }
        mContext = null;
        pool = null;
    }


    public void playBeepSoundAndVibrate() {
        if (pool != null) {
            pool.play(1, 1, 1, 0, 0, 1);
        }
        Vibrator vibrator = (Vibrator) mContext.getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(VIBRATE_DURATION);
    }
}