package siri;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class to make sense of user commands and execute the commands.
 */
class Parser {
    private TaskList taskList;

    /**
     * Constructor of Parser class.
     *
     * @param taskList TaskList object in which execution of the commands will be carried out on.
     */
    Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Method to handle the command being passed in.
     *
     * @param command String representation of the command that is passed in.
     * @return true to indicate further acceptance of command is allowed and false to indicate
     * further acceptance of command is not allowed.
     * @throws SiriException if command structure is invalid, command is not recognised or
     * if there are missing or extra fields in command.
     */
    public boolean handleCommand(String command) throws SiriException {

        String[] inputSplit = command.split(" ", 2);
        boolean continueExecution = true;

        if (inputSplit[0].equals("")) {
            throw new SiriException("Please ENTER something!!");
        } else {
            switch (inputSplit[0]) {
            case "mark":
                if (inputSplit.length == 1 || inputSplit[1].trim().length() == 0) {
                    throw new SiriException("Please ENTER the item number to mark!!");
                } else if (this.taskList.size() == 0) {
                    throw new SiriException("There is currently no tasks!!");
                } else {
                    try {
                        int index = Integer.parseInt(inputSplit[1].trim());
                        index--;

                        if (index >= this.taskList.size() || index < 0) {
                            throw new SiriException("Please ENTER a number within the number of tasks!!");
                        } else {
                            this.taskList.markItem(index);
                            break;
                        }
                    } catch (NumberFormatException nfe) {
                        throw new SiriException("Please ENTER a valid item number to mark!!");
                    }
                }
            case "unmark":
                if (inputSplit.length == 1 || inputSplit[1].trim().length() == 0) {
                    throw new SiriException("Please ENTER the item number to unmark!!");
                } else if (this.taskList.size() == 0) {
                    throw new SiriException("There is currently no tasks!!");
                } else {
                    try {
                        int index = Integer.parseInt(inputSplit[1].trim());
                        index--;

                        if (index >= this.taskList.size() || index < 0) {
                            throw new SiriException("Please ENTER a number within the number of tasks!!");
                        } else {
                            this.taskList.unmarkItem(index);
                            break;
                        }
                    } catch (NumberFormatException nfe) {
                        throw new SiriException("Please ENTER a valid item number to unmark!!");
                    }
                }
            case "list":
                if (inputSplit.length == 1 || inputSplit[1].trim().length() == 0) {
                    this.taskList.print();
                    break;
                } else {
                    throw new SiriException("OPPS!! list does not take in any parameter!!");
                }
            case "todo":
                if (inputSplit.length == 1 || inputSplit[1].trim().length() == 0) {
                    throw new SiriException("todo cannot be EMPTY!! Please ENTER something for todo!!");
                } else {
                    ToDos todoTask = new ToDos(inputSplit[1].trim(), false);
                    this.taskList.addItem(todoTask);
                    break;
                }
            case "deadline":
                if (inputSplit.length == 1 || inputSplit[1].trim().length() == 0) {
                    throw new SiriException("deadline cannot be EMPTY!! Please ENTER something for deadline!!");
                } else {
                    String[] dlSplit = inputSplit[1].split(" /by ", 2);

                    if (dlSplit.length == 1 || dlSplit[1].trim().length() == 0) {
                        throw new SiriException("deadline has no date/time!! Please ENTER a date/time for deadline!!");
                    } else {
                        try {
                            Deadline dlTask;
                            String[] dlDateTime = dlSplit[1].split(" ", 2);
                            LocalDate dlDate = Parser.stringToDate(dlDateTime[0].trim());
                            if (dlDateTime.length == 1 || dlDateTime[1].trim().length() == 0) {
                                dlTask = new Deadline(dlSplit[0], false, dlDate);
                            } else {
                                LocalTime dlTime = Parser.stringToTime(dlDateTime[1].trim());
                                dlTask = new Deadline(dlSplit[0], false, dlDate, dlTime);
                            }
                            this.taskList.addItem(dlTask);
                            break;
                        } catch (DateTimeParseException dtpe) {
                            throw new SiriException("deadline date/time format is wrong!!\n"
                                    + "Please ENTER your date time in DD-MM-YYYY HH:MM (if applicable) format!!");
                        }
                    }
                }
            case "event":
                if (inputSplit.length == 1 || inputSplit[1].trim().length() == 0) {
                    throw new SiriException("event cannot be EMPTY!! Please ENTER something for event!!");
                } else {
                    String[] eventSplit = inputSplit[1].split(" /at ", 2);

                    if (eventSplit.length == 1 || eventSplit[1].trim().length() == 0) {
                        throw new SiriException("event has no date/time!! Please ENTER a date and time for event!!");
                    } else {
                        try {
                            String[] eventDateTime = eventSplit[1].split(" ", 2);
                            LocalDate eDate = Parser.stringToDate(eventDateTime[0].trim());
                            Event eventTask;
                            if (eventDateTime.length == 1 || eventDateTime[1].trim().length() == 0) {
                                throw new SiriException("Missing date/time field!!\n"
                                        + "Please ENTER date your date time in DD-MM-YYYY HH:MM format!!");
                            } else {
                                LocalTime eTime = Parser.stringToTime(eventDateTime[1].trim());
                                eventTask = new Event(eventSplit[0], false, eDate, eTime);
                            }
                            this.taskList.addItem(eventTask);
                            break;
                        } catch (DateTimeParseException dtpe) {
                            throw new SiriException("event date/time format is wrong!!\n"
                                    + "Please ENTER your date time in DD-MM-YYYY  HH:MM format!!");
                        }
                    }
                }
            case "delete":
                if (inputSplit.length == 1 || inputSplit[1].trim().length() == 0) {
                    throw new SiriException("Please ENTER the item number to delete!!");
                } else if (this.taskList.size() == 0) {
                    throw new SiriException("There is currently no tasks!!");
                } else {
                    try {
                        int index = Integer.parseInt(inputSplit[1].trim());
                        index--;

                        if (index >= this.taskList.size() || index < 0) {
                            throw new SiriException("Please ENTER a number within the number of tasks!!");
                        } else {
                            this.taskList.deleteTask(index);
                            break;
                        }
                    } catch (NumberFormatException nfe) {
                        throw new SiriException("Please ENTER a valid item number to unmark!!");
                    }
                }
            case "eprint":
                if (inputSplit.length == 1 || inputSplit[1].trim().length() == 0) {
                    throw new SiriException("Please ENTER a date!!");
                } else {
                    try {
                        LocalDate eCheckedDate = Parser.stringToDate(inputSplit[1].trim());
                        this.taskList.printEventOn(eCheckedDate);
                    } catch (DateTimeParseException dtpe) {
                        throw new SiriException("Please ENTER your date in DD-MM-YYYY format!!");
                    }
                }
                break;
            case "dlprint":
                if (inputSplit.length == 1 || inputSplit[1].trim().length() == 0) {
                    throw new SiriException("Please ENTER a date!!");
                } else {
                    try {
                        LocalDate dlCheckedDate = Parser.stringToDate(inputSplit[1].trim());
                        this.taskList.printDeadlineOn(dlCheckedDate);
                    } catch (DateTimeParseException dtpe) {
                        throw new SiriException("Please ENTER your date in DD-MM-YYYY format!!");
                    }
                }
                break;
            case "find":
                if (inputSplit.length == 1 || inputSplit[1].trim().length() == 0) {
                    throw new SiriException("Please ENTER a word to find!!");
                } else {
                    this.taskList.find(inputSplit[1].trim());
                }
                break;
            case "bye":
                continueExecution = false;
                break;
            default:
                throw new SiriException("OPPS!! I did not understand what you had keyed!! Please try again!!");
            }
        }

        return continueExecution;
    }

    private static LocalDate stringToDate(String dateString) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate ld = LocalDate.parse(dateString, dtf);

        return ld;
    }

    private static LocalTime stringToTime(String timeString) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime lt = LocalTime.parse(timeString, dtf);

        return lt;
    }

}
