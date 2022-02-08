package stevie.exception.messages;

public enum ParserExceptionMessages {
    AddEventParseError {
        @Override
        public String toString() {
            return "Event task requires a name and date!";
        }
    },
    AddDeadlineParseError {
        @Override
        public String toString() {
            return "Deadline task requires a name and date!";
        }
    },
    AddTodoParseError {
        @Override
        public String toString() {
            return "Todo task requires a name!";
        }
    },
    DateParseError {
        @Override
        public String toString() {
            return "Date format is unacceptable!";
        }
    },
    IndexParseError {
        @Override
        public String toString() {
            return "Index must be an integer!";
        }
    },
    InvalidCommandParseError {
        @Override
        public String toString() {
            return "Oops! Your instructions were unclear!";
        }
    }
}
