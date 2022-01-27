package duke.utils;

/**
 * Class that handles exceptions related to Duke
 */
public class DukeException extends Exception{

    /**
     * Handle the exception where the user doesn't
     * enter a valid command
     */
    public static class DukeInvalidCommandException extends DukeException{

        /**
         * Return error message telling user that
         * the command is invalid
         *
         * @return String representation of error message
         */
        @Override
        public String toString(){
            return super.toString() + "NO SUCH COMMAND FOUND";
        }

    }

    /**
     * Handle the exception where the user enters a
     * task without any description
     */
    public static class DukeNoTaskGivenException extends DukeException{

        /**
         * Return error message telling user that
         * no description has been provided
         *
         * @return String representation of error message
         */
        @Override
        public String toString(){
            return super.toString() + "PLEASE SPECIFY A TASK";
        }
    }

    /**
     * Handle the exception where the user enters an
     * invalid index for a action on the list
     */
    public static class DukeInvalidNumberException extends DukeException{

        /**
         * Return error message telling user that
         * the index entered is invalid
         *
         * @return String representation of error message
         */
        @Override
        public String toString(){
            return super.toString() + "PLEASE ENTER A VALID NUMBER TO MARK/UNMARK/DELETE";
        }
    }

    /**
     * Handle the exception where the user doesn't enter a
     * date when adding a new event/deadline
     */
    public static class DukeNoTimeProvided extends DukeException{

        /**
         * Return error message telling user that
         * no date has been provided
         *
         * @return String representation of error message
         */
        @Override
        public String toString(){
            return super.toString() + "PLEASE ENTER A VALID DATE FOR THE EVENT/DEADLINE";
        }
    }

    /**
     * Return default error message
     *
     * @return String representation of error message
     */
    public String toString(){
        return "ERROR: ";
    }
}
