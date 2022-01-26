package chatbot.util;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Ui {
    public static void print(Object x) {
        System.out.print(x);
    }

    public static void println(Object x) {
        System.out.println(x);
    }

    public static void playSound(String filePath) {
        try {
            File wavFile = new File(filePath);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(wavFile));
            clip.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
