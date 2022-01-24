package duke;
public class Parser {
    public Parser() {}

    public boolean takeInput(String input, TaskList taskList) throws DukeException {
        if(input.equals("bye")) {
            System.out.println("~BYE!~ Come back to Duke anytime");
            return true;
        }

        //Check if input == list
        else if(input.equals("list")) {
            taskList.printList();
        }

        //new instruction to reset the arraylist
        else if(input.equals("reset")) {
            taskList.reset();
            System.out.println("List of tasks has been resetted");
        }

        //Check if input == unmark or delete or mark
        else if(input.contains("unmark") || input.contains("delete") || input.contains("mark")) {
            String[] splitted = input.split("\\s+");
            String instr = splitted[0];
            if (splitted.length < 2) {
                System.out.println("Did you miss out the index in your input?");
            } else {
                try {
                    int index = Integer.parseInt(splitted[1]);
                    if (instr.equals("unmark")) {
                        taskList.unmarkTask(index);
                    } else if (instr.equals("mark")) {
                        taskList.markTask(index);
                    } else if (instr.equals("delete")) {
                        taskList.deleteTask(index);
                    } else {
                        throw new DukeException("You have entered an invalid instruction");
                    }
                } catch (DukeException e) {
                    System.out.println(e);
                }
            }
        }

        //input is a new type of task
        else if(input.contains("todo") || input.contains("event") || input.contains("deadline")) {
            //identify type of task
            String[] arr = input.split(" ", 2);

            if(arr.length < 2){
                throw new DukeException("Description of task cannot be empty!");
            }

            String taskType = arr[0];
            String taskDetails = arr[1];

            Task newTask = new Task("");

            if(taskType.equals("todo")){
                newTask = new Todo(taskDetails);
            } else if(taskType.equals("deadline")){
                String[] spl = taskDetails.split("/by");
                if(spl.length < 2){
                    throw new DukeException("Description of deadline must include a date/time! Did you miss out a /by?");
                }
                String details = spl[0].trim();
                String dateTime = spl[1].trim();
                newTask = new Deadline(details,dateTime);
            } else if(taskType.equals("event")){
                String[] spl = taskDetails.split("/at");
                if(spl.length < 2){
                    throw new DukeException("Description of event must include a date/time! Did you miss out a /at?");
                }
                String details = spl[0].trim();
                String dateTime = spl[1].trim();
                newTask = new Event(details,dateTime);
            }

            taskList.addTask(newTask);
        } else {
            throw new DukeException("no such task type");
        }
        Ui.printSeparator();
        return false;
    }
}
