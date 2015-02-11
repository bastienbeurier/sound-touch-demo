package fr.myapplication;

import android.app.Activity;
import android.os.Bundle;

import com.smp.soundtouchandroid.SoundStreamAudioPlayer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File f = new File(getCacheDir()+"/lion.mp3");
        if (!f.exists()) try {

            InputStream is = this.getResources().openRawResource(R.raw.lion);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            FileOutputStream fos = new FileOutputStream(f);
            fos.write(buffer);
            fos.close();
        } catch (Exception e) { throw new RuntimeException(e); }

        SoundStreamAudioPlayer player = null;
        try {
            player = new SoundStreamAudioPlayer(0, f.getPath(), 6.0f, 5.0f);
            new Thread(player).start();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
