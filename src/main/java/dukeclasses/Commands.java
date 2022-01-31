package dukeclasses;

public enum Commands {
    HI{
        @Override
        public String toString() {
            return "    Hello! I'm Duke\n    What can I do for you?\n";
        }
    },
    BYE{
        @Override
        public String toString() {
            return "    Bye. Hope to see you again soon!\n";
        }
    },
    LIST{
        @Override
        public String toString() {
            return "    Here are the tasks in your list:\n";
        }
    },
    MARK{
        @Override
        public String toString() {
            return "    Nice! I've marked this task as done:\n";
        }
    },
    UNMARK{
        @Override
        public String toString() {
            return "    Ok! I've marked this task as not done yet:\n";
        }
    },
    ADD{
        @Override
        public String toString() {
            return "    Got it. I've added this task:\n";
        }
    },
    DELETE{
        @Override
        public String toString() {
            return "    Noted. I've removed this task:\n";
        }
    },
    MATCH{
        @Override
        public String toString(){
            return "    Here are the matching tasks in your list:\n";
        }
    }

}
