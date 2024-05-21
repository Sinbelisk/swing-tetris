package tetris.gui.loadResources;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class LoadSoundtrack {
    private Clip clip;
    private final String song;
    private boolean isLoaded = false;

    public LoadSoundtrack(String song) {
        this.song = song;
    }

    private void load() {
        try {
            URL url = getClass().getClassLoader().getResource(song);

            if (url == null) {
                throw new RuntimeException("Soundtrack not found!");
            }

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            isLoaded = true;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    //Play Music
    public void play() {
        if (!isLoaded) {
            load();
        }
        clip.start();
    }

    //Stop Music
    public void stop() {
        if (isLoaded) {
            clip.stop();
        }
    }

    //Resume Music
    public void resume() {
        if (isLoaded) {
            clip.start();
        }
    }

    // Set Volume (0.0 to 1.0)
    public void setVolume(float volume) {
        if (isLoaded && clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            float db;

            if (volume == 0) {
                db = min;
            } else {
                db = (float) (Math.log10(volume) * 20);
            }

            volumeControl.setValue(Math.max(min, db));
        }
    }
}

