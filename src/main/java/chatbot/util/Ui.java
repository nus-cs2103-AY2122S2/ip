package chatbot.util;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;

public class Ui {
    public static void print(Object x) {
        System.out.print(x);
    }

    public static void println(Object x) {
        System.out.println(x);
    }

    public static void playSound(String fileName) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new BufferedInputStream(Ui.class.getResourceAsStream(fileName))));
            clip.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
