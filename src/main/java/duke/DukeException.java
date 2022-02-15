package duke;

import duke.common.Constant;

/**
 * A class that handles errors in Duke.
 */
public class DukeException extends Exception {

    /**
     * A class that invalid command errors.
     */
    public static class DukeInvalidCommandException extends DukeException {
        @Override
        public String toString() {
            return "What do you want me to do? Valid command only. :O";
        }
    }

    /**
     * A class that handles commands that require descriptions but has no descriptions.
     */
    public static class DukeNoDescriptionFoundException extends DukeException {
        @Override
        public String toString() {
            return "What is the description? :(";
        }
    }

    /**
     * A class that handles commands that require time but has no time.
     */
    public static class DukeTimeNotFoundException extends DukeException {
        @Override
        public String toString() {
            return "When?????????, I can't read your mind";
        }
    }

    /**
     * A class that handles invalid date time format.
     */
    public static class DukeDateTimeFormatException extends DukeException {
        @Override
        public String toString() {
            return "Please use this format " + Constant.INPUT_DATE_TIME_FORMAT;
        }
    }

    /**
     * TA class that handles input time that are in the past.
     */
    public static class DukePastTimeException extends DukeException {
        @Override
        public String toString() {
            return "Please enter a time in the future! We ain't time travellers.";
        }
    }

    /**
     * A class that handles empty task index.
     */
    public static class DukeTaskNotFoundException extends DukeException {
        @Override
        public String toString() {
            return "please enter a task index from the list.";
        }
    }

    /**
     * A class that handles invalid task index.
     */
    public static class DukeNotAValidNumberException extends DukeException {
        @Override
        public String toString() {
            return "Stop trying to be funny, enter a valid task number from the list only.";
        }
    }

    /**
     * A class that handles doing operation on tasks when the list is empty.
     */
    public static class DukeEmptyTaskException extends DukeException {
        @Override
        public String toString() {
            return "You do not have any task, please add one!";
        }
    }

    /**
     * A class that handles IO errors.
     */
    public static class DukeIoException extends DukeException {
        @Override
        public String toString() {
            return "There is an error processing the data :(";
        }
    }

    /**
     * A class that handles marking tasks that are already marked.
     */
    public static class DukeMarkedException extends DukeException {
        @Override
        public String toString() {
            return "You have already marked this task. ;/";
        }
    }

    /**
     * A class that handles unmarking tasks that are already unmarked.
     */
    public static class DukeUnMarkException extends DukeException {
        @Override
        public String toString() {
            return "This task is already not marked. ;/";
        }
    }
}
