import java.util.ArrayList;
import java.util.Scanner;

public class McBot {
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

        while(running) {
            try {
                String fullCommand = sc.nextLine();
                String[] command = fullCommand.split(" ", 2);
                switch(command[0]) {
                    case "bye": {
                        System.out.println(frameLine);
                        System.out.println("Arghh! This ain't the last time ye see me lad");
                        System.out.println(frameLine);
                        running = false;
                        break;
                    }
                    case "list": {
                        int i = 1;
                        System.out.println(frameLine);
                        System.out.println("Here are yer tasks boi:");
                        for (Task task : arrList) {
                            System.out.print(i + ".[" + task.getTaskIcon() + "]");
                            System.out.print("[" + task.getStatusIcon() + "] ");
                            System.out.println(task.getFullDetails());
                            i++;
                        }
                        System.out.println(frameLine);
                        break;
                    }
                    case "mark": {
                        try {
                            if (command[1].isBlank())
                                throw new McBotException();
                            int num = Integer.parseInt(command[1]);
                            Task t = arrList.get(num - 1);
                            if (!t.isMarked()) {
                                t.markDone();
                                System.out.println(frameLine);
                                System.out.println("Aye I'ave marked it done:");
                                System.out.println("[" + t.getTaskIcon() + "][" + t.getStatusIcon() + "] " + t.getFullDetails());
                                System.out.println(frameLine);
                            }
                            else {
                                System.out.println(frameLine);
                                System.out.println("You fool!! It is already mark'd");
                                System.out.println(frameLine);
                            }
                        } catch (ArrayIndexOutOfBoundsException | McBotException e) {
                            System.out.println(frameLine);
                            System.out.println("I don't know which one to mark boi");
                            System.out.println(frameLine);
                        } catch (NumberFormatException e) {
                            System.out.println(frameLine);
                            System.out.println("That ain't a number boi");
                            System.out.println(frameLine);
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(frameLine);
                            System.out.println("Don't mess with me boi, that number is not in the list");
                            System.out.println(frameLine);
                        }
                        break;
                    }
                    case "unmark": {
                        try {
                            if (command[1].isBlank())
                                throw new McBotException();
                            int num = Integer.parseInt(command[1]);
                            Task t = arrList.get(num - 1);
                            if (t.isMarked()) {
                                t.undoDone();
                                System.out.println(frameLine);
                                System.out.println("Aye I'ave unmarked it:");
                                System.out.println("[" + t.getTaskIcon() + "][" + t.getStatusIcon() + "] " + t.getFullDetails());
                                System.out.println(frameLine);
                            }
                            else {
                                System.out.println(frameLine);
                                System.out.println("You fool!! It is already unmark'd");
                                System.out.println(frameLine);
                            }
                        } catch (ArrayIndexOutOfBoundsException | McBotException e) {
                            System.out.println(frameLine);
                            System.out.println("I don't know which one to unmark boi");
                            System.out.println(frameLine);
                        } catch (NumberFormatException e) {
                            System.out.println(frameLine);
                            System.out.println("That ain't a number boi");
                            System.out.println(frameLine);
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(frameLine);
                            System.out.println("Don't mess with me boi, that number is not in the list");
                            System.out.println(frameLine);
                        }
                        break;
                    }
                    case "todo": {
                        try {
                            String taskName = command[1];
                            if (taskName.isBlank())
                                throw new McBotException();
                            Task t = new ToDo(taskName);
                            arrList.add(t);
                            System.out.println(frameLine);
                            System.out.println("Got 'em down as todo:");
                            System.out.println("[" + t.getTaskIcon() + "][" + t.getStatusIcon() + "] " + t.getFullDetails());
                            System.out.println("Ye now have " + arrList.size() + " tasks in list lad");
                            System.out.println(frameLine);
                        } catch (McBotException | ArrayIndexOutOfBoundsException e) {
                            System.out.println(frameLine);
                            System.out.println("Sorry boi, ye can't leave todo task empty");
                            System.out.println(frameLine);
                        }
                        break;
                    }
                    case "deadline": {
                        try {
                            String[] str = command[1].split("/by ");
                            String taskName = str[0];
                            String deadlineDate = str[1];
                            if (taskName.isBlank())
                                throw new McBotException("you can't leave your deadline task empty");
                            if (deadlineDate.isBlank())
                                throw new McBotException("you can't leave your deadline date empty");
                            Task t = new Deadline(taskName, deadlineDate);
                            arrList.add(t);
                            System.out.println(frameLine);
                            System.out.println("Got 'em down as deadline:");
                            System.out.println("[" + t.getTaskIcon() + "][" + t.getStatusIcon() + "] " + t.getFullDetails());
                            System.out.println("Ye now have " + arrList.size() + " tasks in list lad");
                            System.out.println(frameLine);
                        } catch (McBotException e) {
                            System.out.println(frameLine);
                            System.out.println(e.getMessage());
                            System.out.println(frameLine);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(frameLine);
                            System.out.println("Fool, follow this format: deadline -TASKNAME- /by -DATE-");
                            System.out.println(frameLine);
                        }
                        break;
                    }
                    case "event": {
                        try {
                            String[] str = command[1].split("/at ");
                            String taskName = str[0];
                            String eventDetails = str[1];
                            if (taskName.isBlank())
                                throw new McBotException("you can't leave your event task empty");
                            if (eventDetails.isBlank())
                                throw new McBotException("you can't leave your event date/time empty");
                            Task t = new Event(taskName, eventDetails);
                            arrList.add(t);
                            System.out.println(frameLine);
                            System.out.println("Got 'em down as event:");
                            System.out.println("[" + t.getTaskIcon() + "][" + t.getStatusIcon() + "] " + t.getFullDetails());
                            System.out.println("Ye now have " + arrList.size() + " tasks in list lad");
                            System.out.println(frameLine);
                        } catch (McBotException e) {
                            System.out.println(frameLine);
                            System.out.println(e.getMessage());
                            System.out.println(frameLine);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(frameLine);
                            System.out.println("Fool, follow this format: event -TASKNAME- /at -DATE/TIME-");
                            System.out.println(frameLine);
                        }
                        break;
                    }
                    default: {
                        throw new McBotException("I don't understand a word ye're sayin'");
                    }
                }
            } catch (McBotException e) {
                System.out.println(frameLine);
                System.out.println(e.getMessage());
                System.out.println(frameLine);
            }
        }
    }
}
