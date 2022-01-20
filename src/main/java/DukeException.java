/**
 * DukeException class is a custom exception class to handle
 * problems that might occur in Duke.java
 */
public class DukeException extends Exception {

    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    public DukeException() {}

    /**
     * checker method is used to run checks for all the different types of request
     * and check if it invalids any of them. Returns a description or advice on what
     * is missing or how to resolve it.
     * @param arr This String array stores the user's input, used to check for validity
     * @param curr Holds the capacity for the stored list, used to check if request is out of bounds
     * @throws DukeException
     */
    public void checker(String[] arr, int curr) throws DukeException {
        String request = arr[0];
        int len = arr.length;
        if (request.equals("delete")) {
            if (len == 1) {
                throw new DukeException(" ☹ OOPS!!! Please tell me the task's" +
                        " index number so that I can delete it from the list.");
            } else {
                Integer index = Integer.parseInt(arr[1]);
                if (index < 1 || index > curr) {
                    throw new DukeException(" ☹ OOPS!!! The task you want to delete is out of bounds," +
                            " please double check the index number");
                }
            }
        } else if (request.equals("mark")) {
            if (len == 1) {
                throw new DukeException(" ☹ OOPS!!! Please tell me the task's" +
                        " index number so that I can mark it as done.");
            } else {
                Integer index = Integer.parseInt(arr[1]);
                if (index < 1 || index > curr) {
                    throw new DukeException(" ☹ OOPS!!! The task you want to mark as done is out of bounds," +
                            " please double check the index number");
                }
            }
        } else if (request.equals("unmark")) {
            if (len == 1) {
                throw new DukeException("☹ OOPS!!! Please tell me the task's\" +\n" +
                        "                        \" index number so that I can mark it as not done.");
            } else {
                Integer index = Integer.parseInt(arr[1]);
                if (index < 1 || index > curr) {
                    throw new DukeException(" ☹ OOPS!!! The task you want to mark as not done is out of bounds," +
                            " please double check the index number");
                }
            }
        } else if (request.equals("todo")) {
            if (len == 1) {
                throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
            }
        } else if (request.equals("deadline")) {
            if (len == 1) {
                throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
            } else {
                String[] components = arr[1].split("/by", 2);
                if (components[0].equals("") || components[0].equals(" ")) {
                    throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                } else if (components.length == 1 || components[1].equals("") || components[1].equals(" ")) {
                    throw new DukeException(" ☹ OOPS!!! Please input a due date for this task");
                } else {}
            }
        } else if (request.equals("event")) {
            if (len == 1) {
                throw new DukeException(" ☹ OOPS!!! The description of a event cannot be empty.");
            } else {
                String[] components = arr[1].split("/at", 2);
                if (components[0].equals("") || components[0].equals(" ")) {
                    throw new DukeException(" ☹ OOPS!!! The description of a event cannot be empty.");
                } else if (components.length == 1 || components[1].equals("") || components[1].equals(" ")) {
                    throw new DukeException(" ☹ OOPS!!! Please input a due date for this task");
                } else {}
            }
        } else if (request.equals("bye") || request.equals("list")) {}
        else {
            throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n " +
                    "                         Please add a valid request");
        }
    }
}
