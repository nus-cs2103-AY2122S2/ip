package duke.ui;

/**
 * Deals with interactions with users.
 */
public class Ui {

    /**
     * Prints out the statement "LOADING ERROR + error message".
     *
     * @param dukeError DukeException message. Could be either because
     *                  of missing directory or file.
     */
    public void showLoadingError(DukeException dukeError) {
        System.out.println("LOADING ERROR: " + dukeError.getMessage());
    }

}
