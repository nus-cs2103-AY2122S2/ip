public class Parser {

    public Boolean isValidCommand(String taskType) {
        return taskType.equals("todo") || taskType.equals("deadline")
                || taskType.equals("event");
    }

    public Task parseCommandFromUser(String taskType, String instruction) throws CustomException {
        Task taskParsed;
        if (taskType.equals("todo")) {
            String description = instruction.substring(4);
            if (description.isBlank()) {
                throw new CustomException("todo description cannot be blank! Please add task details");
            } else {
                taskParsed = new Todo(description.substring(1));
            }
        } else if (taskType.equals("deadline")) {
            int index = instruction.indexOf("/by");
            if (index != -1) {
                String description = instruction.substring(9, index);
                String time = instruction.substring(index + 4);
                taskParsed = new Deadline(description, time);
            } else {
                throw new CustomException("Incorrect time format: ensure to prefix time with '/by'");
            }
        } else {
            int index = instruction.indexOf("/at");
            if (index != -1) {
                String description = instruction.substring(6, index);
                String time = instruction.substring(index + 4);
                int indexOfDifferentiator = time.indexOf("-");
                String dateAndStartTime = time.substring(0, indexOfDifferentiator).trim();
                String endTime = time.substring(indexOfDifferentiator + 1).trim();
                taskParsed = new Event(description, dateAndStartTime, endTime);
            } else {
                throw new CustomException("Incorrect time format: ensure to prefix time with '/at'");
            }

        }
        return taskParsed;
    }
}
