package duke;

public class Ui {

    /**
     * Print statement with divider line
     *
     * @param str input string to be printed
     */
    public void print(Object str) {
        System.out.println(str);
        System.out.println("_".repeat(100));
    }

    /**
     * Prints oridinary System.out.println
     *
     * @param str input string to be printed
     */
    public void println(Object str) {
        System.out.println(str);
    }

    /**
     * Print statement for when system exits
     */
    public void printBye(){
        this.print("Bye. Hope I've motivated you as much as I could have, and SMILE :D");
    }

    public void throwDukeException(String message) throws DukeException {
        throw new DukeException(message);
    }


}
