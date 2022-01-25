import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Van {
    public static void main(String[] args) {
        int counter = 0, index = 0;
        String[] parse, para;
        String command = "null";
        ArrayList<Task> tasks = new ArrayList<Task>();
        String home = System.getProperty("user.home");
        File file = new File("storage.txt");
        try {
            if (file.isFile()) {
                Scanner fileInput = new Scanner(file);
                while (fileInput.hasNextLine()) {
                    String fileLine = fileInput.nextLine();
                    String[] taskDetail = fileLine.split("\\|");
                    switch(taskDetail[0]) {
                        case "E":
                            tasks.add(new Event(taskDetail[2], taskDetail[3]));
                            if (taskDetail[1].equals("1")) {
                                tasks.get(counter).setDone();
                            }
                            counter++;
                            break;
                        case "D":
                            tasks.add(new Deadline(taskDetail[2], taskDetail[3]));
                            if (taskDetail[1].equals("1")) {
                                tasks.get(counter).setDone();
                            }
                            counter++;
                            break;
                        case "T":
                            tasks.add(new Todo(taskDetail[2]));
                            if (taskDetail[1].equals("1")) {
                                tasks.get(counter).setDone();
                            }
                            counter++;
                            break;
                    }
                }
            } else {
                file.createNewFile();
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        Scanner input = new Scanner(System.in);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String divide = "-------------------------------------------";
        System.out.println("Hello I am Van");
        System.out.println("How may i assist you");
        while (true) {
            try {
                System.out.println(divide);
                command = input.nextLine();
                parse = command.split("\\s+", 2);
                System.out.println(divide);
                if (command.equalsIgnoreCase("bye")) {
                    break;
                }
                LocalDateTime date;
                switch(parse[0].toLowerCase()) {
                    case "list":
                        System.out.println("Pending tasks:");
                        for (int i = 0; i < counter; i++) {
                            System.out.println(i+1 + ". " + tasks.get(i).getStatus());
                        }
                        break;
                    case "deadline":
                        if (parse.length != 2) {
                            throw new VanException("Invalid format. Please use: deadline <task> /by <date>");
                        }
                        para = parse[1].split(" /by ");
                        if (para.length != 2) {
                            throw new VanException("Invalid format. Please use: deadline <task> /by <date>");
                        }
                        try {
                            date = LocalDateTime.parse(para[1], dateFormat);
                        } catch (DateTimeParseException e) {
                            throw new VanException("Invalid date format. " + "Please use dd-MM-YYYY HH:mm e.g. 20-10-2022 1800");
                        }
                        tasks.add(new Deadline(para[0], date));
                        System.out.println("Task added");
                        System.out.println("  " + tasks.get(counter).getStatus());
                        counter++;
                        System.out.println(counter + " tasks pending.");
                        break;
                    case "event":
                        if (parse.length != 2) {
                            throw new VanException("Invalid format. Please use: event <task> /at <date>");
                        }
                        para = parse[1].split(" /at ");
                        if (para.length != 2) {
                            throw new VanException("Invalid format. Please use: event <task> /at <date>");
                        }
                        try {
                            date = LocalDateTime.parse(para[1], dateFormat);
                        } catch (DateTimeParseException e) {
                            throw new VanException("Invalid date format. " + "Please use dd-MM-YYYY HH:mm e.g. 20-10-2022 1800");
                        }
                        tasks.add(new Event(para[0], date));
                        System.out.println("Task added");
                        System.out.println("  " + tasks.get(counter).getStatus());
                        counter++;
                        System.out.println(counter + " tasks pending.");
                        break;
                    case "todo":
                        if (parse.length != 2) {
                            throw new VanException("Invalid format. Please use: todo <task>");
                        }
                        tasks.add(new Todo(parse[1]));
                        System.out.println("Task added");
                        System.out.println("  " + tasks.get(counter).getStatus());
                        counter++;
                        System.out.println(counter + " tasks pending.");
                        break;
                    case "mark":
                        if (parse.length != 2) {
                            throw new VanException("Invalid format. Please use mark <task number>");
                        }
                        try {
                            index = Integer.parseInt(parse[1]);
                        } catch (NumberFormatException ex) {
                            System.out.println("Please use integer numbers e.g. 1, 2");
                        }
                        if (index > counter) {
                            throw new VanException("Task number out of range");
                        }
                        tasks.get(index - 1).setDone();
                        break;
                    case "unmark":
                        if (parse.length != 2) {
                            throw new VanException("Invalid format. Please use unmark <task number>");
                        }
                        try {
                            index = Integer.parseInt(parse[1]);
                        } catch (NumberFormatException ex) {
                            System.out.println("Please use integer numbers e.g. 1, 2");
                        }
                        if (index > counter) {
                            throw new VanException("Task number out of range");
                        }
                        tasks.get(index - 1).setunDone();
                        break;
                    case "delete":
                        if (parse.length != 2) {
                            throw new VanException("Invalid format. Please use delete <task number>");
                        }
                        try {
                            index = Integer.parseInt(parse[1]);
                        } catch (NumberFormatException ex) {
                            System.out.println("Please use integer numbers e.g. 1, 2");
                        }
                        if (index > counter) {
                            throw new VanException("Task number out of range");
                        }
                        tasks.remove(index - 1);
                        counter--;
                        break;
                    default:
                        System.out.println("Unrecognised command.");
                }
            }
            catch (VanException ex) {
                System.out.println(ex);
            }
        }
        try {
            PrintWriter writer = new PrintWriter(file);
            for (int i = 0; i < counter; i++) {
                writer.print(tasks.get(i).saveStatus() + "\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Bye");
    }
}
