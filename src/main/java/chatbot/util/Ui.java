package chatbot.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * User interface handler.
 */
public class Ui {
    public static void playSound(String fileName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Ui.class.getResource(fileName));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
