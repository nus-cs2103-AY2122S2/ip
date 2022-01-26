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
                        if (arrList.size() == 0)
                            throw new McBotException("Your list is empty boi");
                        System.out.println("Here are yer tasks boi:");
                        for (Task task : arrList) {
                            System.out.print(i + ".[" + task.getTaskIcon() + "]");
                            System.out.print("[" + task.getStatusIcon() + "] ");
                            System.out.println(task.getFullDetails());
                            i++;
                        }
                    } catch (McBotException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case "mark": {
                    try {
                        if (command[1].isBlank())
                            throw new invalidCommandException();
                        int num = Integer.parseInt(command[1]);
                        if (num < 1 || num > arrList.size())
                            throw new McBotException();
                        Task t = arrList.get(num - 1);
                        if (!t.isMarked()) {
                            t.markDone();
                            System.out.println("Aye I'ave marked it done:");
                            System.out.println("[" + t.getTaskIcon() + "][" + t.getStatusIcon() + "] " + t.getFullDetails());
                        } else {
                            System.out.println("You fool!! It is already mark'd");
                        }
                    } catch (ArrayIndexOutOfBoundsException | invalidCommandException e) {
                        System.out.println("I don't know which one to mark boi");
                    } catch (NumberFormatException e) {
                        System.out.println("That ain't a number that I accept boi");
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
                            System.out.println("Aye I'ave unmarked it:");
                            System.out.println("[" + t.getTaskIcon() + "][" + t.getStatusIcon() + "] " + t.getFullDetails());
                        }
                        else {
                            System.out.println("You fool!! It is already unmark'd");
                        }
                    } catch (ArrayIndexOutOfBoundsException | invalidCommandException e) {
                        System.out.println("I don't know which one to unmark boi");
                    } catch (NumberFormatException e) {
                        System.out.println("That ain't a number boi");
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
                        System.out.println("Got 'em down as todo:");
                        System.out.println("[" + t.getTaskIcon() + "][" + t.getStatusIcon() + "] " + t.getFullDetails());
                        System.out.println("Ye now have " + arrList.size() + " tasks in list lad");
                    } catch (invalidCommandException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("Sorry boi, ye can't leave todo task empty");
                    }
                    break;
                }
                case "deadline": {
                    try {
                        String[] str = command[1].split("/by ");
                        String taskName = str[0];
                        String deadlineDate = str[1];
                        if (taskName.isBlank())
                            throw new invalidCommandException("you can't leave your deadline task empty");
                        if (deadlineDate.isBlank())
                            throw new invalidCommandException("you can't leave your deadline date empty");
                        Task t = new Deadline(taskName, deadlineDate);
                        arrList.add(t);
                        System.out.println("Got 'em down as deadline:");
                        System.out.println("[" + t.getTaskIcon() + "][" + t.getStatusIcon() + "] " + t.getFullDetails());
                        System.out.println("Ye now have " + arrList.size() + " tasks in list lad");
                    } catch (invalidCommandException e) {
                        System.out.println(e.getMessage());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Fool, follow this format: deadline -TASKNAME- /by -DATE-");
                    }
                    break;
                }
                case "event": {
                    try {
                        String[] str = command[1].split("/at ");
                        String taskName = str[0];
                        String eventDetails = str[1];
                        if (taskName.isBlank())
                            throw new invalidCommandException("you can't leave your event task empty");
                        if (eventDetails.isBlank())
                            throw new invalidCommandException("you can't leave your event date/time empty");
                        Task t = new Event(taskName, eventDetails);
                        arrList.add(t);
                        System.out.println("Got 'em down as event:");
                        System.out.println("[" + t.getTaskIcon() + "][" + t.getStatusIcon() + "] " + t.getFullDetails());
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
                        System.out.println("Aye, I 'ave deleted it");
                        System.out.print("  [" + t.getTaskIcon() + "]");
                        System.out.print("[" + t.getStatusIcon() + "] ");
                        System.out.println(t.getFullDetails());
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
