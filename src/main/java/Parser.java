public class Parser {

    public static Task parseToTask(String userInput) throws FailedTaskParseException {
        try {
            if (userInput.startsWith("todo")) {
                return Parser.newToDo(userInput);
            } else if (userInput.startsWith("deadline")) {
                return Parser.newDeadline(userInput);
            } else if (userInput.startsWith("event")) {
                return Parser.newEvent(userInput);
            } else {
                throw new FailedTaskParseException(userInput);
            }
        } catch (InvalidTaskDataTimeException exception) {
            throw new FailedTaskParseException(userInput);
        } catch (InvalidTaskDescriptionException exception) {
            throw new FailedTaskParseException(userInput);
        }
    }
    private static ToDo newToDo(String s) throws InvalidTaskDescriptionException{
        String taskName =  s.replaceFirst("todo","").strip();
        return new ToDo(taskName);
    }
    private static Event newEvent(String s) throws InvalidTaskDataTimeException,InvalidTaskDescriptionException{
        String taskName =  s.replaceFirst("event","").strip();
        return new Event(taskName);
    }
    private static Deadline newDeadline(String s) throws InvalidTaskDataTimeException,InvalidTaskDescriptionException{
        String taskName =  s.replaceFirst("deadline","").strip();
        return new Deadline(taskName);
    }

    public static Task parseToTaskFromFile(String fileInput) throws FailedTaskParseException {
        // <type>\t<done>\t<name>\t<date>
        char type;
        char done;
        String name;
        String date;
        Task t;

        String[] split = fileInput.split("\t");
        type = split[0].toCharArray()[0];
        done = split[1].toCharArray()[0];
        name = split[2];
        date = split[3];

        try {
            switch(type){
                case 'T':
                    t = new ToDo(name);
                    break;

                case 'D':
                    t = new Deadline(name + " /by " + date);
                    break;

                case 'E':
                    t = new Event(name + " /at " + date);
                    break;

                default:
                    throw new FailedTaskParseException(fileInput);
            }

            if (done == 'X') {
                t.markDone();
            }

            return t;

        } catch (InvalidTaskDescriptionException exception) {
            throw new FailedTaskParseException(fileInput);
        } catch (InvalidTaskDataTimeException exception) {
            throw new FailedTaskParseException(fileInput);
        }

    }

    public static Command parse(String input){
        if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")){
            return new AddCommand(input);
        } else if (input.startsWith("list")){
            return new ListCommand();
        } else if (input.startsWith("mark") || input.startsWith("unmark")){
            return new MarkCommand(input);
        }
             return null;

    }

}
