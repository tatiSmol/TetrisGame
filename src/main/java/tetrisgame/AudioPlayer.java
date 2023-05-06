package tetrisgame;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AudioPlayer {
    private String soundsFolder = "sounds" + File.separator;
    private String clearLinePath = soundsFolder + "clear.wav";
    private String gameOverPath = soundsFolder + "success.wav";

    private Clip clearLineSound, gameOverSound;

    public AudioPlayer() {
        try {
            clearLineSound = AudioSystem.getClip();
            gameOverSound = AudioSystem.getClip();

            clearLineSound.open(AudioSystem.getAudioInputStream(new File(clearLinePath).getAbsoluteFile()));
            gameOverSound.open(AudioSystem.getAudioInputStream(new File(gameOverPath).getAbsoluteFile()));
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
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
