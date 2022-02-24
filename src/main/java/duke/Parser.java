package duke;

import java.time.LocalDate;

/**
 * Parser for commands
 */
public class Parser {
    // private String command;

    /**
     * Parses user command
     * @param fullCommand command given
     * @return Command object
     * @throws DukeException when invalid command is given
     */
    public static Command parse(String fullCommand) throws DukeException {
        switch (fullCommand) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "todo":
                throw new DukeException("Buddy!!! You gotta tell me what it is exactly you want me to add as a to-do!");
            case "deadline":
                throw new DukeException("Bud, a deadline needs a description, a date and a time yo!");
            case "event":
                throw new DukeException("Buddy you didn't tell me where your date was!!");
            default:
                String[] arrOfStr = fullCommand.split(" ", 2);
                if (arrOfStr.length > 1) {
                    String command = arrOfStr[0];
                    switch (command) {
                        case "todo":
                            return new TodoCommand(arrOfStr[1]);
                        case "deadline": {
                                             String[] arrOfStr2 = arrOfStr[1].split("/", 2);
                                             String[] arrOfStr3 = arrOfStr2[1].split(" ", 3);
                                             return new DeadlineCommand(arrOfStr2[0], LocalDate.parse(arrOfStr3[1]), arrOfStr3[2]);
                        }
                        case "event": {
                                          String[] arrOfStr2 = arrOfStr[1].split("/", 2);
                                          String[] arrOfStr3 = arrOfStr2[1].split(" ", 2);
                                          assert (arrOfStr3[0].equals("at")) : "event command is not entered properly.";
                                              return new EventCommand(arrOfStr2[0], (" " + arrOfStr3[1]));
                        }
                        case "find":
                                      return new FindCommand(arrOfStr[1]);
                        default:
                                      int number = Integer.parseInt(arrOfStr[1]);
                                      switch (command) {
                                          case "mark":
                                              return new MarkCommand(number);
                                          case "unmark":
                                              return new UnmarkCommand(number);
                                          case "delete":
                                              return new DeleteCommand(number);
                                          case "snooze":
                                              return new SnoozeCommand(number);
                                          default:
                                              throw new DukeException("Bud!!! What in the world " +
                                                      "are you talking about? Are you following the right format bud?");
                                      }
                    }
                } else {
                    throw new DukeException("Bud!!! What in the world are you talking about? Are you following the" +
                            " right format bud?");
                }
        }
    }
}

