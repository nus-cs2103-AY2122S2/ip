import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> itemList = new ArrayList<>(0);
    private Path absolutePath;
    private String fileName;
    private File targetFile;

    //read file and initialize arraylist: if dont have existing file, create.
    public TaskList(String fileName) {
        String currentDir = System.getProperty("user.dir");
        Path currentPath = Path.of(currentDir + File.separator + "data");
        this.absolutePath = currentPath.toAbsolutePath();
        this.fileName = fileName;
    }

    public void initFile() {
        try {
            this.absolutePath = Files.createDirectories(this.absolutePath);
        } catch (IOException err) {
            System.out.println(err.getMessage() + " create dir");
        }

        try {
            this.absolutePath = this.absolutePath.resolve(this.fileName);
            Files.createFile(this.absolutePath);
        } catch (IOException err) {
            //its ok if file already exists.
        }

        try {
            FileReader fileReader = new FileReader(this.absolutePath.toString());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while((line  = bufferedReader.readLine()) != null) {
                //write to arraylist
                //create task
                String[] tokens = line.split(" / ");
                boolean isMarked = tokens[tokens.length - 1].equals("1");
                switch(tokens[0]) {
                case "todo":
                    String todoTitle = "todo " + tokens[1];
                    this.itemList.add(new TodoTask(todoTitle, isMarked));
                    break;
                case "event":
                    String eventTitle = "event " + tokens[1];
                    String eventDeadline = tokens[3];
                    this.itemList.add(new EventTask(eventTitle, eventDeadline, isMarked));
                    break;
                case "deadline":
                    String deadlineTitle = "deadline " + tokens[1];
                    String deadlineDeadline = tokens[3];
                    this.itemList.add(new DeadlineTask(deadlineTitle, deadlineDeadline, isMarked));
                    break;
                }
                //add task to list
            }
        } catch (Exception err) {
            System.out.println(err.getMessage() + " create reader");
        }
        this.targetFile = new File(this.absolutePath.toString());
    }

    public void addTodo(String taskKey) {
        writeToFile(taskKey, "T", false);
        String[] tokens = taskKey.split("todo ");
        String taskTitle = "";
        try {
            if (tokens.length < 2) {
                throw new BadDescriptionException("todo"); //type
            } else {
                taskTitle = tokens[1];
            }
        } catch (BadDescriptionException err) {
            System.out.println(err.getMessage());
            return;
        }
        Task newTask = new TodoTask(taskTitle);
        this.itemList.add(newTask);
        System.out.println(
                    "----------------------------" +
                            "----------------------------\n" +
                            "Got it. I've added this task:\n"
                            + "  " + newTask + "\n"
                            + "Now you have " + this.itemList.size() + " tasks in the list."
                            + "\n"
                            + "--------------------------------------------------------"
            );
    }

    public void addEvent(String taskKey) {
        String[] tokens = taskKey.split("event ");
        String taskTitle = "";
        try {
            if (tokens.length < 2) {
                throw new BadDescriptionException("event");

            } else {
                taskTitle = tokens[1];
            }
        } catch (BadDescriptionException err) {
            System.out.println(err.getMessage());
            return;
        }
        String[] secondSplit = taskTitle.split(" /at ");
        taskTitle = secondSplit[0];
        String deadline = "";
        try {
            if (secondSplit.length < 2) {
                throw new BadDeadlineException("event");
            } else {
                deadline = secondSplit[1];
            }
        } catch (BadDeadlineException err) {
            System.out.println(err.getMessage());
            return;
        }
        Task newTask = new EventTask(taskTitle, deadline);
        this.itemList.add(newTask);
        writeToFile(taskKey, "E", false);
        System.out.println(
                "----------------------------" +
                        "----------------------------\n" +
                        "Got it. I've added this task:\n"
                        + "  " + newTask + "\n"
                        + "Now you have " + this.itemList.size() + " tasks in the list."
                        + "\n"
                        + "--------------------------------------------------------"
        );
    }

    public void addDeadline(String taskKey) {
        String[] tokens = taskKey.split("deadline ");
        String taskTitle = "";
        try {
            if (tokens.length < 2) {
                throw new BadDescriptionException("deadline");
            } else {
                taskTitle = tokens[1];
            }
        } catch (BadDescriptionException err) {
            System.out.println(err.getMessage());
            return;
        }
        String[] secondSplit = taskTitle.split(" /by ");
        taskTitle = secondSplit[0];
        String deadline = "";
        try {
            if (secondSplit.length < 2) {
                throw new BadDeadlineException("deadline");
            } else {
                deadline = secondSplit[1];
            }
        } catch (BadDeadlineException err) {
            System.out.println(err.getMessage());
            return;
        }
        Task newTask = new DeadlineTask(taskTitle, deadline);
        this.itemList.add(newTask);
        writeToFile(taskKey, "D", false);
        System.out.println(
                "----------------------------" +
                        "----------------------------\n" +
                        "Got it. I've added this task:\n"
                        + "  " + newTask + "\n"
                        + "Now you have " + this.itemList.size() + " tasks in the list."
                        + "\n"
                        + "--------------------------------------------------------"
        );
    }

    public void deleteTask(String taskKey) {
        String[] tokens = taskKey.split(" ");
        String strIndex = tokens[1]; //error here
        int index = Integer.parseInt(strIndex);
        Task targetTask = this.itemList.get(index);
        this.itemList.remove(index);
        System.out.println(
                "----------------------------" +
                        "----------------------------\n" +
                        "Noted. I've removed this task:\n"
                        + "  " + targetTask + "\n"
                        + "Now you have " + this.itemList.size() + " tasks in the list."
                        + "\n"
                        + "--------------------------------------------------------"
        );
    }

    public void markTask(String taskKey) {
        String[] tokens = taskKey.split(" ");
        String strIndex = tokens[1]; //error here
        int index = Integer.parseInt(strIndex);
        Task targetTask = this.itemList.get(index);
        Task newTask = targetTask.markTask();
        this.itemList.set(index, newTask);
        System.out.println(
                "----------------------------" +
                        "----------------------------\n" +
                "Nice! I've marked this task as done:"
                        + "\n" + "  " + targetTask
                        + "\n"
                        + "--------------------------------------------------------"
        );
    }

    public void unmarkTask(String taskKey) {
        String[] tokens = taskKey.split(" ");
        String strIndex = tokens[1]; //error here
        int index = Integer.parseInt(strIndex);
        Task targetTask = this.itemList.get(index);
        Task newTask = targetTask.unmarkTask();
        this.itemList.set(index, newTask);
        System.out.println(
                "----------------------------" +
                        "----------------------------\n" +
                "OK, I've marked this task as not done yet:"
                        + "\n" + "  " + targetTask
                        + "\n"
                        + "--------------------------------------------------------"
        );
    }

    public String printList() {
        StringBuilder initList = new StringBuilder();

        for (int i = 1; i < itemList.size() + 1; i++) {
            initList.append(i + ".");
            initList.append(itemList.get(i - 1));
            initList.append("\n");
        }

        return initList.toString();
    }

    public void writeToFile(String taskKey, String taskType, boolean isMarked) {
        String mark = isMarked ? "1" : "0";
        String toWrite = "";
        try {
            FileWriter fw = new FileWriter(this.absolutePath.toString(), true);
            BufferedWriter bufferedWriter = new BufferedWriter(fw);
            String newLine = "";
            if (this.targetFile.length() > 0) {
                newLine = "\n";
            }
            switch(taskType) {
            case "T":
                String todoTokens[] = taskKey.split(" ");
                toWrite = String.join(" / ", todoTokens);
                toWrite = toWrite + " / " + mark;
                bufferedWriter.append(newLine + toWrite);
                break;
            case "E":
                String eventTokens[] = taskKey.split(" /at ");
                toWrite = String.join(" ", eventTokens);
                String eSplit[] = toWrite.split(" ");
                toWrite = String.join(" / ", eSplit );
                toWrite = toWrite + " / " + mark;
                bufferedWriter.append(newLine + toWrite);
                break;
            case "D":
                String deadlineTokens[] = taskKey.split(" /by ");
                toWrite = String.join(" ", deadlineTokens);
                String dSplit[] = toWrite.split(" ");
                toWrite = String.join(" / ", dSplit );
                toWrite = toWrite + " / " + mark;
                bufferedWriter.append(newLine + toWrite);
                break;
            }
            bufferedWriter.close();
        } catch(Exception err) {
            err.printStackTrace();
        }
    }
}
