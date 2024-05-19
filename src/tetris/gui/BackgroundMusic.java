package tetris.gui;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class BackgroundMusic {
    private Clip clip;

    public BackgroundMusic() {
        try {
            File file = new File("resources/backgroundMusic.wav");

            URL url = getClass().getClassLoader().getResource("resources/backgroundMusic.wav");

            if (url == null) {
                throw new RuntimeException("Archivo de música no encontrado");
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
