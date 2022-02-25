package jarvis.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jarvis.Jarvis;

public class JarvisTest {
    @Test
    public void runJarvis_run() {
        Jarvis duke = new Jarvis();
        assertTrue(duke.isRunning());
    }
}
