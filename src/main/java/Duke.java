import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.FormatStyle;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // initialisations for file I/O
        File file = new File(
                "data/duke.txt");
        BufferedReader readFile
                = new BufferedReader(new FileReader(file));
        FileWriter fw = new  FileWriter("data/duke.txt",true);
        PrintWriter writeFile = new  PrintWriter(fw);

        // System.out.println("first " + fileReader.readLine());
        ArrayList<Task> tasks = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int items = 0;
        String line = "";

        // datetime formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
//        LocalDateTime dateTime = LocalDateTime.parse(, formatter);

        // read tasks from file and add it to list
        while((line = readFile.readLine()) != null) {
            items++;
            String[] words = line.split(" ");
            String type = words[0];

            // check whether the task is marked
            boolean isDone = !words[1].equals("0");

            if(words[0].equals("T")) {
                sb = new StringBuilder();
                for(int i = 2; i < words.length; i++) {
                    sb.append(words[i]).append(" "); // get the description
                }
                tasks.add(new ToDo(sb.toString(), isDone));
            }
            else if(words[0].equals("D")) {
                sb = new StringBuilder();
                int count = 2;
                for(int i = 2; i < words.length; i++) {
                    count++;
                    if(words[i].equals("|"))
                        break;
                    sb.append(words[i]).append(" "); // get the description
                }
                String description = sb.toString();
                sb = new StringBuilder();
//                for(int i = count; i < words.length; i++) {
//                    sb.append(words[i]).append(" "); // get the description
//                }
                sb.append(words[count]).append(" ");
                sb.append(words[count+1]);
                tasks.add(new Deadline(description, LocalDateTime.parse(sb.toString(), formatter), isDone));
            }
            else {
                sb = new StringBuilder();
                int count = 2;
                for(int i = 2; i < words.length; i++) {
                    count++;
                    if(words[i].equals("|"))
                        break;
                    sb.append(words[i]).append(" "); // get the description
                }
                String description = sb.toString();
                sb = new StringBuilder();
//                for(int i = count; i < words.length; i++) {
//                    sb.append(words[i]).append(" "); // get the description
//                }
                sb.append(words[count]).append(" ");
                sb.append(words[count+1]);
                tasks.add(new Event(description, LocalDateTime.parse(sb.toString(), formatter), isDone));
            }
        }

        System.out.println("Hello!!! I'm jBot\nWhat can I do for you? :3");
        String full = br.readLine();
        String[] s = full.split(" ");

        label:
        while (true) {
            // "bye" to end the program
            switch (s[0]) {
                case "bye":
                    System.out.println("    Bye!! See you again soon!!");
                    break label;

                // if user requests to list their tasks
                case "list": {
                    System.out.println("    Here are the tasks in your lists:");
                    int n = 1;
                    for (Task t : tasks) {
                        String by = "";
                        if (t.getType().equals("D") || t.getType().equals("E"))
                            by = t.getBy().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
                        System.out.println("    " + n + ". [" + t.getType() + "][" + t.getDone() + "] " + t.desc + by);
                        n++;
                    }
                    break;
                }
                case "mark": {
                    writeFile.flush(); // need to flush first cause updates were stored in buffer
                    int n = Integer.parseInt(s[1]) - 1;
                    tasks.set(n, tasks.get(n).mark());
                    Task temp = tasks.get(n);
//                    String date = "";
//                    if(!(temp instanceof ToDo))
//                        date = " | " + LocalDateTime.parse(temp.getBy().toString(), formatter);
//                    System.out.println("dateee " + date);
                    if(!(temp instanceof ToDo)) {
                        String date = temp.getBy().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
                        updateTask(n + 1, tasks.get(n).type + " 1 " + tasks.get(n).desc +  " | " + date);

                    }
                    else
                        updateTask(n + 1, tasks.get(n).type + " 1 " + tasks.get(n).desc);

                    System.out.println("    Alright! I've marked this as done:\n      [" + temp.getDone() + "] " + temp.desc );

                    break;
                }
                case "unmark": {
                    writeFile.flush(); // need to flush first cause updates were stored in buffer
                    int n = Integer.parseInt(s[1]) - 1;
                    tasks.set(n, tasks.get(n).unmark());
                    Task temp = tasks.get(n);
                    if(!(temp instanceof ToDo)) {
                        String date = temp.getBy().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
                        updateTask(n + 1, tasks.get(n).type + " 0 " + tasks.get(n).desc +  " | " + date);

                    }
                    else
                        updateTask(n + 1, tasks.get(n).type + " 0 " + tasks.get(n).desc);

                    System.out.println("    Alright! I've marked this as not done:\n      [" + temp.getDone() + "] " + temp.desc);
                    break;
                }
                case "delete": {
                    writeFile.flush(); // need to flush first cause updates were stored in buffer
                    int n = Integer.parseInt(s[1]) - 1;
                    Task t = tasks.get(n);
                    tasks.remove(n);
                    deleteTask(n + 1);
                    System.out.println("    Alright! This task has been deleted:\n      [" + t.getType() + "][" + t.getDone() + "] " + t.desc + t.getBy());

                    break;
                }
                // user add a todo task
                case "todo":
                    Arrays.toString(full.split(" ", 2));
                    s = full.split(" ", 2);
                    if(s.length < 2) {
                        System.out.println("    Oops!! Description of ToDo can't be empty!!\n ");
                    }
                    else {
                        System.out.println("    Okay! I've added this task:  ");
                        tasks.add(new ToDo(s[1]));
                        System.out.println("      [T][ ] " + s[1]);
                        items++;
                        writeFile.println("T 0 " + s[1]);
                        System.out.println("\n    Now you have " + items + " tasks on your list");
                    }

                    break;
                // user add a event task
                case "event":
                    Arrays.toString(full.split(" ", 2));
                    s = findDate(full.split(" "));
                    System.out.println("    Okay! I've added this task into the list:\n  ");
                    tasks.add(new Event(s[0],LocalDateTime.parse(s[1], formatter)));
//                  System.out.println("full  " + s[1]);
                    System.out.println("      [E][ ] " + s[0]);
                    items++;
                    writeFile.println("E 0 " + s[0] + " | " + s[1]);

                    System.out.println("\n    Now you have " + items + " tasks on your list");

                    break;
                // user add a deadline task
                case "deadline":
                    Arrays.toString(full.split(" ", 2));
                    s = findDate(full.split(" "));
                    System.out.println("    Okay! I've added this task:\n  ");
                    tasks.add(new Deadline(s[0],LocalDateTime.parse(s[1], formatter)));
//                  System.out.println("full  " + s[1]);
                    System.out.println("      [D][ ] " + s[0]);
                    items++;
                    writeFile.println("D 0 " + s[0] + " | " + s[1]);

                    System.out.println("\n    Now you have " + items + " tasks on your list");

                    break;
                // user add items to list
                default:
                    System.out.println("    Sorry! I don't know what that means :'(");

                    break;
            }
            full = br.readLine();
            s = full.split(" ");
        }
        writeFile.close();
    }

    static String[] findDate(String[] full) {
        StringBuilder sb = new StringBuilder();
        String[] end = new String[100];
        int i = 0;
        for (String s : full) {
            if(s.charAt(0) == '/')
                break;
            i++;
        }
        // get the desc
        for(int j = 1; j < i; j++) {
            sb.append(full[j]).append(" ");
        }

        // get the time
        end[0] = sb.toString();
        sb = new StringBuilder();
//        for(int j = i+1; j < full.length; j++) {
//            sb.append(full[j]).append(" ");
//        }
        sb.append(full[i+1]).append(" ");
        sb.append(full[i+2]);

        end[1] = sb.toString();
//        end[1] = end[1].substring(1); // remove the slash
//        System.out.println("this is i " + end[1]);

        return end;
    }

     static void updateTask(int lineNumber, String data) throws IOException {
        Path path = Paths.get("data/duke.txt");
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.set(lineNumber - 1, data);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    static void deleteTask(int lineNumber) throws IOException {
        Path path = Paths.get("data/duke.txt");
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.remove(lineNumber - 1);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }

}

