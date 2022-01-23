import java.util.Scanner;

public class Dazz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String hr = "\t____________________________________________________________";
        String logo = "\t  ____       _       _____   _____\n" +
                "\t |  _\"\\  U  /\"\\  u  |\"_  / u |\"_  / u\n" +
                "\t/| | | |  \\/ _ \\/   U / / /  U / / /\n" +
                "\tU| |_| |\\ / ___ \\   \\/ /_   \\/ /_\n" +
                "\t |____/ u/_/   \\_\\  /____|  /____|\n" +
                "\t  |||_    \\\\    >>  _//<<,- _//<<,-\n" +
                "\t (__)_)  (__)  (__)(__) (_/(__) (_/";

        System.out.println(hr + "\n\tHello from\n" + logo + "\n\n"
                + "\tGood day!\n\tWhat can I do for you?\n" + hr);

        Reminder reminder = new Reminder();

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            String[] arr1 = command.split(" ");
            boolean byeFlag = arr1[0].equals("bye");
            System.out.println(hr);
            try {
                switch (arr1[0]) {
                case "bye":
                    System.out.println("\tBye. Hope to see you again soon!");
                    break;
                case "list":
                    reminder.list();
                    break;
                case "mark":
                    if (arr1.length < 2) {
                        throw new IncompleteCommandException("mark");
                    } else if (reminder.getSize() == 0) {
                        throw new EmptyListException();
                    } else {
                        int index = Integer.parseInt(arr1[1]);
                        reminder.mark(index);
                        break;
                    }
                case "unmark":
                    if (arr1.length < 2) {
                        throw new IncompleteCommandException("unmark");
                    } else if (reminder.getSize() == 0) {
                        throw new EmptyListException();
                    } else {
                        int index = Integer.parseInt(arr1[1]);
                        reminder.unmark(index);
                        break;
                    }
                case "delete":
                    if (arr1.length < 2) {
                        throw new IncompleteCommandException("delete");
                    } else if (reminder.getSize() == 0) {
                        throw new EmptyListException();
                    } else {
                        int index = Integer.parseInt(arr1[1]);
                        reminder.delete(index);
                        break;
                    }
                case "todo":
                    if (arr1.length < 2) {
                        throw new IncompleteCommandException("todo");
                    } else {
                        Task todo = new Todo(command.split("todo ")[1], TaskType.TODO.getTaskType());
                        reminder.add(todo);
                        break;
                    }
                case "deadline":
                    if (arr1.length < 2) {
                        throw new IncompleteCommandException("deadline");
                    } else {
                        String[] arr2 = command.split(" /by ");
                        if (arr2.length < 2) {
                            throw new IncompleteCommandException("'date' in deadline");
                        } else {
                            String description = arr2[0].substring(9) + " (by: " + arr2[1] + ")";
                            Task deadline = new Deadline(description, TaskType.DEADLINE.getTaskType());
                            reminder.add(deadline);
                            break;
                        }
                    }
                case "event":
                    if (arr1.length < 2) {
                        throw new IncompleteCommandException("event");
                    } else {
                        String[] arr3 = command.split(" /at ");
                        if (arr3.length < 2) {
                            throw new IncompleteCommandException("'date' in event");
                        } else {
                            String description = arr3[0].substring(6) + " (at: " + arr3[1] + ")";
                            Task event = new Event(description, TaskType.EVENT.getTaskType());
                            reminder.add(event);
                            break;
                        }
                    }
                case "":
                    throw new EmptyCommandException();
                default:
                    throw new InvalidCommandException();
                }
            } catch (DazzException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("\tOOPS!!! The index you have provided is invalid!");
            }
            System.out.println(hr);
            if (byeFlag) {
                break;
            }
        }
        scanner.close();
    }
}

