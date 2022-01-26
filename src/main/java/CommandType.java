import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CommandType {
    LIST {
        @Override
        boolean isValid(String fullCommand) {
            return true;
        }
    },
    BYE {
        @Override
        boolean isValid(String fullCommand) {
            return true;
        }
    },
    TODO {
        @Override
        boolean isValid(String fullCommand) throws KarenException {
            if (fullCommand.matches("^todo .*$")) {
                return true;
            }
            throw new KarenException("'todo' command is missing an argument");
        }
    },
    DEADLINE {
        @Override
        boolean isValid(String fullCommand) throws KarenException {
            if (fullCommand.matches("^deadline .* \\/by .*$")) {
                Pattern p = Pattern.compile("deadline .* \\/by (.*)");
                Matcher m = p.matcher(fullCommand);
                m.find();

                // date validation
                try {
                    LocalDate.parse(m.group(1));
                }
                catch (DateTimeParseException err) {
                    throw new KarenException("Wrong date formatting. It should be in yyyy-mm-dd");
                }

                return true;
            } else if (fullCommand.matches("^((?!\\/by).)*$")) {
                throw new KarenException("You're missing an /by flag needed to add deadlines");
            }
            throw new KarenException("'deadline' command is missing argument(s)");
        }
    },
    EVENT {
        @Override
        boolean isValid(String fullCommand) throws KarenException {
            if (fullCommand.matches("^event .* \\/at .*$")) {
                Pattern p = Pattern.compile("event .* \\/at (.*)");
                Matcher m = p.matcher(fullCommand);
                m.find();

                // date validation
                try {
                    LocalDate.parse(m.group(1));
                }
                catch (DateTimeParseException err) {
                    throw new KarenException("Wrong date formatting. It should be in yyyy-mm-dd");
                }
                return true;
            } else if (fullCommand.matches("^((?!\\/at).)*$")) {
                throw new KarenException("You're missing an /at flag needed to add events");
            }
            throw new KarenException("'event' command is missing argument(s)");
        }
    },
    UNMARK {
        @Override
        boolean isValid(String fullCommand) throws KarenException {
            if (fullCommand.matches("^unmark \\d+$")) {
                return true;
            } else if (fullCommand.matches("^unmark .*$")) {
                throw new KarenException("You can only unmark with an integer index");
            }
            throw new KarenException("'unmark' command is missing argument(s)");
        }
    },
    MARK {
        @Override
        boolean isValid(String fullCommand) throws KarenException {
            if (fullCommand.matches("^mark \\d+$")) {
                return true;
            } else if (fullCommand.matches("^mark .*$")) {
                throw new KarenException("You can only mark with an integer index");
            }
            throw new KarenException("'mark' command is missing argument(s)");
        }
    },
    DELETE {
        @Override
        boolean isValid(String fullCommand) throws KarenException {
            if (fullCommand.matches("^delete \\d+$")) {
                return true;
            } else if (fullCommand.matches("^delete .*$")) {
                throw new KarenException("You can only delete with an integer index");
            }
            throw new KarenException("'delete' command is missing argument(s)");
        }
    },
    NA {
        @Override
        boolean isValid(String fullCommand) throws KarenException {
            throw new KarenException("I don't understand anything - I want to speak with your manager");
        }
    };

    abstract boolean isValid(String fullCommand) throws KarenException;
}
