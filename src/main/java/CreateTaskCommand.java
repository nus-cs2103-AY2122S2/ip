package tesseract.command;

import tesseract.command.Command;

import tesseract.main.Storage;
import tesseract.main.TaskList;
import tesseract.main.TessUi;
import tesseract.main.Date;

import tesseract.task.Deadline;
import tesseract.task.Event;
import tesseract.task.Task;
import tesseract.task.Todo;

class CreateTaskCommand extends Command {
    protected String taskType;
    protected String[] cmdArr;

    public CreateTaskCommand(String[] cmdArr) {
        super(cmdArr[0]);
        this.taskType = cmdArr[0];
        this.cmdArr = cmdArr;
    }

    @Override
    public void execute(TaskList taskList, TessUi ui, Storage storage) {
        Task newTask;
        String description = generateDescription(cmdArr);
        Date date;
        int cmdLen = cmdArr.length;
        switch (taskType) {
            case "event":
                newTask = new Event(description, cmdArr[cmdLen - 1]);
                break;
            case "deadline":
                newTask = new Deadline(description, cmdArr[cmdLen - 1]);
                break;
            default: // "todo"
                newTask = new Todo(description);
        }
        taskList.addTask(newTask);
        storage.needUpdate();
        ui.addTaskRes(newTask.toString(), taskList.size());
    }

    String generateDescription(String[] arr) {
        int cmdLen = arr.length;
        String description = "";
        for (int i = 1; i < cmdLen - 1; i++) {
            if (arr[i].equals("/at") || arr[i].equals("/by")) {
                return description.substring(0, description.length());
            }
            description += arr[i] + " ";
        }
        description += arr[cmdLen - 1];
        return description;
    }
}
