package tetrisgame;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AudioPlayer {
    private String soundsFolder = "sounds" + File.separator;
    private Clip clearLineSound;
    private Clip gameOverSound;
    File lineSound = new File(soundsFolder + "clear.wav");
    File overSound = new File(this.soundsFolder + "success.wav");

    public AudioPlayer() {
        try {
            AudioInputStream lineStream = AudioSystem.getAudioInputStream(lineSound);
            this.clearLineSound = AudioSystem.getClip();
            this.clearLineSound.open(lineStream);

            AudioInputStream overStream = AudioSystem.getAudioInputStream(overSound);
            this.gameOverSound = AudioSystem.getClip();
            this.gameOverSound.open(overStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException var3) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, (String)null, var3);
        }

    }


    public void playClearLine() {
        clearLineSound.setFramePosition(0);
        clearLineSound.start();
    }

    public void playGameOver() {
        gameOverSound.setFramePosition(0);
        gameOverSound.start();
    }
}
