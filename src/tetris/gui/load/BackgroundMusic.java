package tetris.gui.load;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class BackgroundMusic {
    private Clip clip;

    public BackgroundMusic(String song) {
        try {
            URL url = getClass().getClassLoader().getResource(song);

            if (url == null) {
                throw new RuntimeException("Soundtrack doesn't found!");
            }

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);

            clip = AudioSystem.getClip();

            clip.open(audioInputStream);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    //Play Music
    public void play() {
        clip.start();
    }

    //Stop Music
    public void stop() {
        clip.stop();
    }

    //Resume Music
    public void resume() {
        clip.start();
    }
}
