package karen.command;

/**
 * Centralises all invalid messages alerted to user
 */
public enum InvalidMessage {
    INVALID_DEFAULT {
        @Override
        public String toString() {
            return "I don't understand anything - I want to speak with your manager";
        }
    },
    INVALID_DATE {
        @Override
        public String toString() {
            return "Wrong date formatting. It should be in yyyy-mm-dd";
        }
    },
    MISSING_BY {
        @Override
        public String toString() {
            return "You're missing an /by flag needed to add deadlines";
        }
    },
    MISSING_AT {
        @Override
        public String toString() {
            return "You're missing an /at flag needed to add events";
        }
    },
    MISSING_DELETE {
        @Override
        public String toString() {
            return "You're missing arguments to delete stuff";
        }
    },
    MISSING_EDIT {
        @Override
        public String toString() {
            return "You're missing arguments to edit stuff."
                    + "\nThe command should look like this 'edit (index) /description (editValue)'";
        }
    },
    INCORRECT_MODIFY {
        @Override
        public String toString() {
            return "Incorrect arguments passed into mark/unmark command";
        }
    },
    INCORRECT_DELETE {
        @Override
        public String toString() {
            return "Incorrect arguments passed into delete command";
        }
    },
    INCORRECT_FIND {
        @Override
        public String toString() {
            return "Incorrect arguments passed into find command.\n'find' requires a term to search with.";
        }
    },
    INCORRECT_EDIT {
        @Override
        public String toString() {
            return "";
        }
    },
    INVALID_INDEX {
        @Override
        public String toString() {
            return "Are you sure that [%d] is even in the 'list' command?";
        }
    }

}
