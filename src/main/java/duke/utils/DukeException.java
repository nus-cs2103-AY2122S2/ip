package duke.utils;

public class DukeException extends Exception {

    public static class DukeInvalidCommandException extends DukeException {

        @Override
        public String toString() {
            return super.toString() + "NO SUCH COMMAND FOUND";
        }

    }

    public static class DukeNoTaskGivenException extends DukeException {

        @Override
        public String toString() {
            return super.toString() + "PLEASE SPECIFY A TASK";
        }
    }

    public static class DukeInvalidNumberException extends DukeException {

        @Override
        public String toString() {
            return super.toString() + "PLEASE ENTER A VALID NUMBER TO MARK/UNMARK/DELETE";
        }
    }

    public static class DukeNoTimeProvided extends DukeException {

        @Override
        public String toString() {
            return super.toString() + "PLEASE ENTER A VALID DATE FOR THE EVENT/DEADLINE";
        }
    }

    public String toString() {
        return "ERROR: ";
    }
}
