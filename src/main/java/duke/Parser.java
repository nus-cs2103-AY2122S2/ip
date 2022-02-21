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
                if (fullCommand.equals("bye")) {
                        return new ExitCommand();
                } else if (fullCommand.equals("list")) {
                        return new ListCommand();
                } else if (fullCommand.equals("todo")) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                } else if (fullCommand.equals("deadline")) {
                        throw new DukeException("☹ OOPS!!! Please provide a deadline for your task.");
                } else if (fullCommand.equals("event")) {
                        throw new DukeException("☹ OOPS!!! Please provide a timing for your event.");
                } else {
                        String[] arrOfStr = fullCommand.split(" ", 2);
                        if (arrOfStr.length > 1) {
                                String command = arrOfStr[0];
                                if(command.equals("todo")) {
                                   return new TodoCommand(arrOfStr[1]);
                                } else if(command.equals("deadline")) {
                                        String[] arrOfStr2 = arrOfStr[1].split("/", 2);
                                        String[] arrOfStr3 = arrOfStr2[1].split(" ", 3);
                                        return new DeadlineCommand(arrOfStr2[0], LocalDate.parse(arrOfStr3[1]), arrOfStr3[2]);
                                } else if(command.equals("event")) {
                                        String[] arrOfStr2 = arrOfStr[1].split("/", 2);
                                        String[] arrOfStr3 = arrOfStr2[1].split(" ", 2);
                                        assert(arrOfStr3[0].equals("at")) : "event command is not entered properly.";
                                        return new EventCommand(arrOfStr2[0],(" " + arrOfStr3[1]));
                                } else if(command.equals("find")) {
                                        return new FindCommand(arrOfStr[1]);
                                } else {
                                        int number = Integer.parseInt(arrOfStr[1]);
                                        if(command.equals("mark")) {
                                                return new MarkCommand(number);
                                        } else if(command.equals("unmark")) {
                                                return new UnmarkCommand(number);
                                        } else if(command.equals("delete")) {
                                                return new DeleteCommand(number);
                                        } else {
                                                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                                        }
                                }
                        } else {
                                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                }
        }
}

