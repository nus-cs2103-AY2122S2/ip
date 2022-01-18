import java.util.Scanner;

public class Dazz {
    public static void main(String[] args) throws DazzException {
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
            String[] arr1 = command.split("\\s+");
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
                            reminder.mark(Integer.parseInt(arr1[1]), true);
                            break;
                        }

                    case "unmark":
                       if (arr1.length < 2) {
                           throw new IncompleteCommandException("unmark");
                        } else if (reminder.getSize() == 0) {
                           throw new EmptyListException();
                        } else {
                           reminder.mark(Integer.parseInt(arr1[1]), false);
                           break;
                        }

                    case "todo":
                        if (arr1.length < 2) {
                            throw new IncompleteCommandException("todo");
                        } else {
                            Task todo = new Todo(command.substring(5));
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
                                Task deadline = new Deadline(arr2[0].substring(9), arr2[1]);
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
                                Task event = new Event(arr3[0].substring(6), arr3[1]);
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
            }
            System.out.println(hr);
            if (byeFlag) {
                break;
            }
        }
        scanner.close();
    }
}

