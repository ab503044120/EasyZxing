package org.huihui.easyzxing.camera;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;

import org.huihui.easyzxing.R;

import java.io.IOException;

import static android.content.Context.AUDIO_SERVICE;
import static android.content.Context.VIBRATOR_SERVICE;

/**
 * User: Administrator
 * Date: 2016-12-23 {HOUR}:52
 */
public class BeepManager implements MediaPlayer.OnCompletionListener {
    private Activity mContext;
    private MediaPlayer mediaPlayer;
    private static final float BEEP_VOLUME = 0.10f;
    private static final long VIBRATE_DURATION = 200L;

    public BeepManager(Activity context) {
        mContext = context;
    }

    public void init() {
        AudioManager audioService = (AudioManager) mContext.getSystemService(AUDIO_SERVICE);
        if (mediaPlayer == null && audioService.getRingerMode() == AudioManager.RINGER_MODE_NORMAL) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it too loud,
            // so we now play on the music stream.
            mContext.setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(this);

            AssetFileDescriptor file = mContext.getResources().openRawResourceFd(R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mediaPlayer.seekTo(0);
    }

    public void release() {
        mediaPlayer.release();
        mediaPlayer = null;
        mContext = null;
    }


    public void playBeepSoundAndVibrate() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
        Vibrator vibrator = (Vibrator) mContext.getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(VIBRATE_DURATION);
    }
}