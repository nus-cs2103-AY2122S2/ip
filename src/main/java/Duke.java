import java.util.ArrayList;

public class Duke {

    private ArrayList<Task> list = new ArrayList<>();
    private int count = 0;
    private Ui ui;
    private Storage storage;
    private TaskMaster taskMaster;

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskMaster = new TaskMaster(storage.loadTasks());
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String[] userCommand = ui.readCommand();
                Command command = Parser.parse(userCommand[0], userCommand[1]);
                command.execute(this.taskMaster, this.ui, this.storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

}


//    //Ui
//    private void take_notes() throws DukeException {
//        Scanner sc = new Scanner(System.in);
//        while (true) {
//
//            String command = sc.next();
//            String details = sc.nextLine().trim();
//            // if there is a command to mark or unmark, this will be the item's id in the list
//            switch (command) {
//            case "bye":
//                System.out.println("------------------------------------------------------");
//                System.out.println("Bye. Have a great day!");
//                System.out.println("======================================================");
//                break;
//            case "list":
//                System.out.println("------------------------------------------------------");
//                System.out.println("Here are your tasks:");
//                for (int i = 1; i <= this.list.size(); i++) {
//                    System.out.println(i + ". " + this.list.get(i - 1));
//                }
//                System.out.println("======================================================");
//                break;
//            case "unmark":
//                try {
//                    System.out.println("------------------------------------------------------");
//                    Task unmark_task = this.list.get(Integer.parseInt(details) - 1);
//                    System.out.println(unmark_task.unmark());
//                    save_to_file();
//                    System.out.println("======================================================");
//                } catch (IndexOutOfBoundsException e) {
//                    throw new DukeException("No such task exists! Are you sure about that task number?");
//                }
//                break;
//            case "mark":
//                try {
//                    System.out.println("------------------------------------------------------");
//                    Task mark_test = this.list.get(Integer.parseInt(details) - 1);
//                    System.out.println(mark_test.mark());
//                    save_to_file();
//                    System.out.println("======================================================");
//                } catch (IndexOutOfBoundsException e) {
//                    throw new DukeException("No such task exists! Are you sure about that task number?");
//                }
//                break;
//            case "deadline":
//                System.out.println("------------------------------------------------------");
//                is_valid_task(details, "deadline");
//                String[] d_deets = details.split("/");
//                String dd = d_deets[1].trim().substring(3);
//                System.out.println(dd);
//                try {
//                    LocalDate.parse(dd);
//                } catch (DateTimeParseException e) {
//                    System.out.println("Please enter a date with the format yyyy-mm-dd");
//                }
//                Deadline deadline = new Deadline(d_deets[0].trim(), LocalDate.parse(dd));
//                add_task(deadline, true);
//                save_to_file();
//                break;
//            case "event":
//                System.out.println("------------------------------------------------------");
//                is_valid_task(details, "event");
//                String[] e_deets = details.split("/");
//                String de = e_deets[1].trim().substring(3);
//                try {
//                    LocalDate.parse(de);
//                } catch (DateTimeParseException e) {
//                    System.out.println("Please enter a date with the format yyyy-mm-dd");
//                }
//                Event event = new Event(e_deets[0].trim(), LocalDate.parse(de));
//                add_task(event, true);
//                save_to_file();
//                break;
//            case "todo":
//                System.out.println("------------------------------------------------------");
//                is_valid_task(details, "todo");
//                ToDo td = new ToDo(details);
//                add_task(td, true);
//                save_to_file();
//                break;
//            case "delete":
//                try {
//                    System.out.println("------------------------------------------------------");
//                    delete_task(Integer.parseInt(details) - 1);
//                    System.out.println();
//                    save_to_file();
//                    System.out.println("======================================================");
//                } catch (IndexOutOfBoundsException e) {
//                    throw new DukeException("No such task exists! Are you sure about that task number?");
//                }
//                break;
//            default:
//                throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//            }
//        }
//    }
//
//    //storage
//    private void record_data_input() {
//        String dir = System.getProperty("user.dir");
//        java.nio.file.Path path = java.nio.file.Paths.get(dir, "duke.txt");
//
//        try {
//            File myObj = new File(path.toString());
//            Scanner myReader = new Scanner(myObj);
//            System.out.println("I see you have an existing list.");
//            while (myReader.hasNextLine()) {
//                String line = myReader.nextLine();
//                String[] data = line.split("\\s*\\|\\s*");
//                String task = data[0];
//                switch (task) {
//                    case "T":
//                        ToDo td = new ToDo(data[2]);
//                        if (data[1].equals("1")) {
//                            td.mark();
//                        }
//                        add_task(td, false);
//                        break;
//                    case "D":
//                        Deadline deadline = new Deadline(data[2], LocalDate.parse(data[3]));
//                        add_task(deadline, false);
//                        if (data[1].equals('1')) {
//                            deadline.mark();
//                        }
//                        break;
//                    case "E":
//                        Event event = new Event(data[2], LocalDate.parse(data[3]));
//                        add_task(event, false);
//                        if (data[1].equals('1')) {
//                            event.mark();
//                        }
//                        break;
//                }
//
//            }
//            myReader.close();
//            for (int i = 1; i <= this.list.size(); i++) {
//                System.out.println(i + ". " + this.list.get(i - 1));
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("It seems you do not have an existing list, I will create it now.");
//            try {
//                File file = new File(path.toString());
//                file.getParentFile().mkdir();
//                file.createNewFile();
//                System.out.println("file created successfully");
//            } catch (IOException ioe) {
//                System.out.println("Failed to create file");
//            }
//        }
//    }
//
//    //taskmaster
//    private void delete_task(int i) {
//        Task removed_task = this.list.remove(i);
//        System.out.println("Noted. I've removed this task:\n" + removed_task +
//                "\nNow you have " + this.list.size() + " tasks in the list.");
//    }
//
//    //Task
//    private boolean is_valid_task(String details, String type) throws DukeException {
//        if (details.length() == 0) {
//            throw new DukeException("☹ OOPS!!! The description of a " + type + " cannot be empty.");
//        } else if ((type.equals("deadline") || type.equals("event")) && !details.contains("/")) {
//            throw new DukeException("☹ OOPS!!! The date of a " + type + " cannot be empty. Use / and type the date after it");
//        } else if ((type.equals("deadline") || type.equals("event")) && details.endsWith("/")) {
//            throw new DukeException("☹ OOPS!!! The date of a " + type + " cannot be empty. Type the date after your /");
//        } else {
//            return true;
//        }
//    }
//
//    //Ui
//    private void output(Task t) {
//        System.out.println(
//                "The following task has been added: \n" + t + "\n" +
//                        "Now you have " + this.count + " tasks in the list \n" +
//                        "======================================================");
//    }
//
//    //taskmaster
//    private void add_task(Task t, Boolean print) {
//        this.list.add(t);
//        this.count++;
//        if (print) {
//            output(t);
//        }
//    }
