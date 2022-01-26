import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class McBot {
    
    private static void updateData(ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter("./data/McBot.txt");
            for (Task t : taskList) {
                fw.write(t.toDataString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void appendData(ArrayList<Task> taskList, Task task) {
        try {
            FileWriter fw = new FileWriter("./data/McBot.txt", true);
            fw.write(task.toDataString() + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        String frameLine = "==========================================";
        String logo = "\n" +
                "___  ___    ______       _\n" +
                "|  \\/  |    | ___ \\     | |\n" +
                "| .  . | ___| |_/ / ___ | |_\n" +
                "| |\\/| |/ __| ___ \\/ _ \\| __|\n" +
                "| |  | | (__| |_/ / (_) | |_\n" +
                "\\_|  |_/\\___\\____/ \\___/ \\__|\n" +
                "\n\n";
        System.out.println(logo);
        System.out.println(frameLine);
        System.out.println("Ahoy! Me name be McBot.\nTell me lad, what do you want?");
        System.out.println(frameLine);

        Scanner sc = new Scanner(System.in);
        boolean running = true;
        ArrayList<Task> arrList = new ArrayList<>(100);
        //Load data from McBot.txt, create folder or file or both if missing
        try {
            File f = new File("./data/McBot.txt");
            if (!f.exists()) {
                File folder = new File("./data");
                if (!folder.exists()) {
                    boolean isFolderCreated = folder.mkdir();
                    boolean isFileCreated = f.createNewFile();
                    if (isFolderCreated && isFileCreated)
                        System.out.println("I'ave created a new folder and file for ya, data/McBot.txt to save yer list");
                } else {
                    boolean isFileCreated = f.createNewFile();
                    if (isFileCreated)
                        System.out.println("I'ave created a new file for ya, data/McBot.txt to save yer list");
                }
            }
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] str = s.nextLine().split(" \\| ");
                Task t;
                
                switch (str[0]) {
                case "T": {
                    t = new ToDo(str[2]);
                    break;
                }
                case "D": {
                    LocalDate date = LocalDate.parse(str[3]);
                    t = new Deadline(str[2], date);
                    break;
                }
                case "E": {
                    LocalDate date = LocalDate.parse(str[3]);
                    t = new Event(str[2], date);
                    break;
                }
                default:
                    throw new McBotException("I dont understand the words in the file");
                }
                if (str[1].equals("1"))
                    t.markDone();
                arrList.add(t);
            }
        } catch (IOException | McBotException e) {
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Data loaded is not format correctly, some data might be missing");
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        while(running) {
            try {
                String fullCommand = sc.nextLine();
                String[] command = fullCommand.split(" ", 2);
                System.out.println(frameLine);
                switch(command[0]) {
                case "bye": {
                    System.out.println("Arghh! This ain't the last time ye see me lad");
                    running = false;
                    break;
                }
                case "list": {
                    try {
                        int i = 1;
                        if (arrList.size() == 0) {
                            throw new McBotException("Your list is empty boi");
                        }
                        System.out.println("Here are yer tasks boi:");
                        for (Task task : arrList) {
                            System.out.println(i + "." + task);
                            i++;
                        }
                    } catch (McBotException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case "mark": {
                    try {
                        if (command[1].isBlank()) //mark with whitespaces
                            throw new invalidCommandException();
                        int num = Integer.parseInt(command[1]);
                        if (num < 1 || num > arrList.size())
                            throw new McBotException();
                        Task t = arrList.get(num - 1);
                        if (!t.isMarked()) {
                            t.markDone();
                            updateData(arrList);
                            System.out.println("Aye I'ave marked it done:");
                            System.out.println(t);
                        } else {
                            System.out.println("You fool!! It is already mark'd");
                        }
                    } catch (ArrayIndexOutOfBoundsException | invalidCommandException e) {
                        System.out.println("I don't know which one to mark boi");
                    } catch (NumberFormatException e) {
                        System.out.println("I only accept integers boi");
                    } catch (McBotException e) {
                        System.out.println("Don't mess with me boi, that number is not in the list");
                    }
                    break;
                }
                case "unmark": {
                    try {
                        if (command[1].isBlank())
                            throw new invalidCommandException();
                        int num = Integer.parseInt(command[1]);
                        if (num < 1 || num > arrList.size())
                            throw new McBotException();
                        Task t = arrList.get(num - 1);
                        if (t.isMarked()) {
                            t.undoDone();
                            updateData(arrList);
                            System.out.println("Aye I'ave unmarked it:");
                            System.out.println(t);
                        }
                        else {
                            System.out.println("You fool!! It is already unmark'd");
                        }
                    } catch (ArrayIndexOutOfBoundsException | invalidCommandException e) {
                        System.out.println("I don't know which one to unmark boi");
                    } catch (NumberFormatException e) {
                        System.out.println("I only accept integers boi");
                    } catch (McBotException e) {
                        System.out.println("Don't mess with me boi, that number is not in the list");
                    }
                    break;
                }
                case "todo": {
                    try {
                        String taskName = command[1];
                        if (taskName.isBlank())
                            throw new invalidCommandException();
                        Task t = new ToDo(taskName);
                        arrList.add(t);
                        appendData(arrList, t);
                        System.out.println("Got 'em down as todo:");
                        System.out.println(t);
                        System.out.println("Ye now have " + arrList.size() + " tasks in list lad");
                    } catch (invalidCommandException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("Sorry boi, ye can't leave todo task empty");
                    }
                    break;
                }
                case "deadline": {
                    try {
                        String[] str = command[1].split(" /by ");
                        String taskName = str[0];
                        String[] deadlineStr = str[1].split(" ");
                        String dateStr = deadlineStr[0];
                        LocalDate deadlineDate = LocalDate.parse(dateStr, dateFormatter);
                        Task t;
                        if (taskName.isBlank())
                            throw new invalidCommandException("you can't leave your deadline task empty");
                        if (dateStr.isBlank())
                            throw new invalidCommandException("you can't leave your deadline date empty");
                        if (deadlineStr.length == 2) {
                            String timeStr = deadlineStr[1];
                            LocalTime deadlineTime = LocalTime.parse(timeStr, timeFormatter);
                            t = new Deadline(taskName, deadlineDate, deadlineTime);
                        } else {
                            t = new Deadline(taskName, deadlineDate);
                        }
                        arrList.add(t);
                        appendData(arrList, t);
                        System.out.println("Got 'em down as deadline:");
                        System.out.println(t);
                        System.out.println("Ye now have " + arrList.size() + " tasks in list lad");
                    } catch (invalidCommandException e) {
                        System.out.println(e.getMessage());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Fool, follow this format: deadline -TASKNAME- /by DD/MM/YYYY");
                    }
                    break;
                }
                case "event": {
                    try {
                        String[] str = command[1].split(" /at ");
                        String taskName = str[0];
                        String[] eventDetails = str[1].split(" ");
                        String dateStr = eventDetails[0];
                        LocalDate eventDate = LocalDate.parse(dateStr, dateFormatter);
                        Task t;
                        if (taskName.isBlank())
                            throw new invalidCommandException("you can't leave your event task empty");
                        if (dateStr.isBlank())
                            throw new invalidCommandException("you can't leave your event date/time empty");
                        if (eventDetails.length == 2) {
                            String timeStr = eventDetails[1];
                            LocalTime eventTime = LocalTime.parse(timeStr, timeFormatter);
                            t = new Event(taskName, eventDate, eventTime);
                        } else {
                            t = new Event(taskName, eventDate);
                        }
                        arrList.add(t);
                        appendData(arrList, t);
                        System.out.println("Got 'em down as event:");
                        System.out.println(t);
                        System.out.println("Ye now have " + arrList.size() + " tasks in list lad");
                    } catch (invalidCommandException e) {
                        System.out.println(e.getMessage());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Fool, follow this format: event -TASKNAME- /at -DATE/TIME-");
                    }
                    break;
                }
                case "delete": {
                    try {
                        if (command[1].isBlank())
                            throw new invalidCommandException("Fool, I need a number to know which one to delete");
                        int num = Integer.parseInt(command[1]);
                        if (num < 1 || num > arrList.size())
                            throw new McBotException("Boi, I can't delete a number that ain't on the list");
                        Task t = arrList.get(num - 1);
                        arrList.remove(num - 1);
                        updateData(arrList);
                        System.out.println("Aye, I 'ave deleted it");
                        System.out.println(t);
                        System.out.println("Ye now have " + arrList.size() + " tasks in list lad");
                    } catch (McBotException e) {
                        System.out.println(e.getMessage());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Fool, I need a number to know which one to delete");
                    } catch (NumberFormatException e) {
                        System.out.println("Boi, I only accept integers here for deletion");
                    }
                    break;
                }
                default: {
                    throw new invalidCommandException("I don't understand a word ye're sayin'");
                }
                }
            } catch (McBotException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println(frameLine);
            }
        }
        sc.close();
    }
}
