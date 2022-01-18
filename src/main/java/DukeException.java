public class DukeException extends Exception{

    public static class DukeInvalidCommandException extends DukeException {
        @Override
        public String toString() {
            return super.toString() + ": What do you want me to do? Valid command only. :O";
        }
    }

    public static class DukeNoDescriptionFoundException extends DukeException {
        @Override
        public String toString() {
            return super.toString() + ": What do you want me to record? :(";
        }
    }

    public static class DukeTimeNotFoundException extends DukeException {
        @Override
        public String toString() {
            return super.toString() + ": When?????????, I can't control the time.";
        }
    }

    public static class DukeTaskNotFoundException extends DukeException {
        @Override
        public String toString() {
            return super.toString() + ": please enter a task index from the LIST.";
        }
    }

    public static class DukeNotAValidNumberException extends DukeException {
        @Override
        public String toString() {
            return super.toString() + ": Stop trying to be funny, enter a valid task number from LIST only.";
        }
    }

    public static class DukeEmptyTaskException extends DukeException {
        @Override
        public String toString() {
            return super.toString() + ": You do not have any task, please add one!";
        }
    }
}
