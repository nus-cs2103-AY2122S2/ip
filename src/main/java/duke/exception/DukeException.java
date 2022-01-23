package duke.exception;

import duke.task.Tasklist;

import java.util.regex.Pattern;

public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }

    public static String[] taskValidity(int index, String input, String taskType) throws DukeException {
        if (taskType.equals("event")) {
            if (index == -1) {
                throw new DukeException("Please specify a date using '/at'.\n");
            }
            if (index - 1 < 6) {
                throw new DukeException("Please key in a valid task name.\n");
            }
        } else {
            if (index == -1) {
                throw new DukeException("Please specify a date using '/by'.\n");
            }
            if (index - 1 < 9) {
                throw new DukeException("Please key in a valid task name.\n");
            }
        }
        if (index + 4 > input.length() || input.substring(index + 4).strip().equals("")) {
            throw new DukeException("Please key in a valid time.\n");
        }
        if (input.contains("|")) {
            throw new DukeException("Sorry, avoid using '|' as it is a special character.\n");
        }
        String timeString = input.substring(index + 4);
        String[] timeArray = timeString.split(" ");
        if (timeArray.length == 0 || timeArray.length > 2) {
            throw new DukeException("Please provide time in the format 'DD/MM/YYYY <time>'.\n");
        }
        String[] dateArray = timeArray[0].split("/");
        if (dateArray.length != 3) {
            throw new DukeException("Please provide time in the format 'DD/MM/YYYY <time>'.\n");
        }
        String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex);
        for (int i = 0; i < 3; i++) {
            if (!p.matcher(dateArray[i]).matches()) {
                throw new DukeException("Please provide time in the format 'DD/MM/YYYY <time>'.\n");
            }
        }
        if (timeArray.length == 1) {
            return dateArray;
        } else {
            return new String[]{dateArray[0], dateArray[1], dateArray[2], timeArray[1]};
        }
    }

    public static void taskValidity(String input) throws DukeException {
        if (input.length() < 5 || input.substring(5).strip().equals("")) {
            throw new DukeException("Please key in a valid task name.\n");
        }
        if (input.contains("|")) {
            throw new DukeException("Sorry, avoid using '|' as it is a special character.\n");
        }
    }

    public static int indexValidity(String input, Tasklist l) throws DukeException {
        String [] keywords = input.split(" ");
        int index;
        if (keywords.length != 2) {
            throw new DukeException("Please provide a single task number.\n");
        }
        try {
            index = Integer.parseInt(keywords[1]) - 1;
            if (index < 0) {
                throw new DukeException("Please key in a number starting from 1.\n");
            }
            if (index >= l.getTotalTasks()) {
                throw new DukeException("No task with this number exists yet!\n");
            }
        } catch (NumberFormatException err) {
            throw new DukeException("Please key in a valid digit.\n");
        }
        return index;
    }

    public static String wordValidity(String input) throws DukeException {
        if (input.length() < 6) {
            throw new DukeException("Please type in a word to search through the tasks.\n");
        }
        return input.substring(5);
    }
}