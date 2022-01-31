
import ari.Ari;

/**
 * Starts the running of Ari
 */
public class Main {
    /**
     * Runs Ari
     *
     * @param args
     */
    public static void main(String[] args) {
        Ari ari = new Ari("data/ari.txt");
        ari.run();
    }
}
