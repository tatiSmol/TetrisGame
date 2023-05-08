package tetrisgame;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AudioPlayer {
    private Clip clearLineSound, gameOverSound;

    public AudioPlayer() {
        try {
            InputStream lineStream = new BufferedInputStream(
                    getClass().getClassLoader().getResourceAsStream("sounds/clear.wav")
            );
            this.clearLineSound = AudioSystem.getClip();
            this.clearLineSound.open(AudioSystem.getAudioInputStream(lineStream));

            InputStream overStream = new BufferedInputStream(
                    getClass().getClassLoader().getResourceAsStream("sounds/success.wav")
            );
            this.gameOverSound = AudioSystem.getClip();
            this.gameOverSound.open(AudioSystem.getAudioInputStream(overStream));
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, e);
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


