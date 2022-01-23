import Duke.Managers.DukeManager;

public class Duke {
    public static void main(String[] args) {
        DukeManager duke = new DukeManager("data/duke.txt");
        duke.run();
    }
}
