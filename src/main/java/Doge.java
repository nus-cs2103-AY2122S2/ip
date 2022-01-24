import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.LocalTime;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

public class Doge {
    public static String startLine = "\n--------------------------------------------------------------------";
    public static String endLine = "--------------------------------------------------------------------\n";
    public static String greeting = "Doge:	Oh it's you again...\nDoge:	" + "What kind of trouble would you " +
            "inconvenience me with this time?";
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Greet
        System.out.println(startLine);
        System.out.println(greeting);
        System.out.println(endLine);

        try {
            tasks = load();
        } catch (DogeException e) {
            System.out.println(startLine);
            System.out.println("<ERROR> " + e);
            System.out.println(endLine);
        }

        while (sc.hasNext()) {
            try {
                String input = sc.nextLine().toLowerCase();
                response(input);
                if (input.equals("bye")) {
                    break;
                }
            } catch(DogeException e){
                System.out.println(startLine);
                System.out.println("<ERROR> " + e);
                System.out.println(endLine);
            }
        }
        sc.close();
    }

    public static void response(String input) throws DogeException {
        String[] words = input.split(" ");
        Command command;

        try {
            command = Command.valueOf(words[0].toLowerCase());
        } catch (IllegalArgumentException e) {
            throw new DogeException("What kind of command is that?? I don't understand!");
        }

        switch (command) {
        case bye:
            save(tasks);
            System.out.println(startLine);
            System.out.println("Doge:   Please don't ever bother me again, bye");
            System.out.println(endLine);
            break;
        case list:
            if (words.length > 1) {
                listTasksDue(tasks, words[1].trim(), words[2].trim(), words[3].trim());
            } else {
                list(tasks);
            }
            break;
        case todo:
            todo(input.substring(4));
            break;
        case deadline:
            deadline(input.substring(8));
            break;
        case event:
            event(input.substring(5));
            break;
        case delete:
            delete(input);
            break;
        case mark:
            mark(input);
            break;
        case unmark:
            unmark(input);
            break;
        }
    }

    public static void list(ArrayList<Task> tasks) {
        // Output List
        StringBuilder output = new StringBuilder(startLine);
        output.append("\n").append("Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            int numbering = i + 1;
            output.append("\n").append(numbering).append(") ➜ ").append(tasks.get(i));
        }

        output.append("\n").append(endLine);
        System.out.println(output);
    }

    public static void listTasksDue(ArrayList<Task> tasks, String limiter, String date, String time) throws DogeException {
        StringBuilder output = new StringBuilder(startLine);
        output.append("\n").append("Here are the tasks in your list:");
        LocalDateTime dueDateTime;

        try {
            dueDateTime = getDateTime(date + " " + time);
        } catch (IndexOutOfBoundsException e) {
            throw new DogeException("Can you please state an appropriate duration for the occurrence?");
        }

        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            LocalDateTime currDateTime;

            if (currTask instanceof Deadline) {
               Deadline currDeadline = (Deadline) currTask;
               currDateTime = currDeadline.dateTime;
            } else if (currTask instanceof Event) {
               Event currEvent= (Event) currTask;
               currDateTime = currEvent.dateTime;
            } else {
                continue;
            }

            if (currDateTime != null) {
                switch (limiter) {
                case "=":
                    if (!currDateTime.isEqual(dueDateTime)) {
                        continue;
                    }
                    break;
                case ">":
                    if (!currDateTime.isAfter(dueDateTime)) {
                        continue;
                    }
                    break;
                case "<":
                    if (!currDateTime.isBefore(dueDateTime)) {
                        continue;
                    }
                    break;
                case ">=":
                    if (!currDateTime.isAfter(dueDateTime) || !currDateTime.isEqual(dueDateTime)) {
                        continue;
                    }
                    break;
                case "<=":
                    if (!currDateTime.isBefore(dueDateTime) || !currDateTime.isEqual(dueDateTime)) {
                        continue;
                    }
                    break;
                default:
                    throw new DogeException("Invalid limiter specified!");
                }
            }
            int numbering = i + 1;
            output.append("\n").append(numbering).append(") ➜ ").append(currTask);
        }
        output.append("\n").append(endLine);
        System.out.println(output);
    }

    public static LocalDateTime getDateTime(String input) throws DogeException {
        LocalDateTime currDateTime = LocalDateTime.now();

        try {
            String[] temp = input.trim().split(" ");
            String[] tempTime = temp[1].split(":");
            LocalDate date = LocalDate.parse(temp[0]);
            LocalTime time = LocalTime.of(Integer.parseInt(tempTime[0]), Integer.parseInt(tempTime[1]));
            LocalDateTime inputDateTime = LocalDateTime.of(date, time);

            if (inputDateTime.isAfter(currDateTime)) {
                return inputDateTime;
            } else {
              throw new DateTimeException("Invalid date/time!");
            }
        } catch (DateTimeException e) {
            throw new DogeException("Are you lacking common sense? Invalid date/time!");
        }
    }

    public static void deadline(String input) throws DogeException {
        // Adding Deadline
        String[] deadline = input.split("/");
        String description = deadline[0].trim();

        if (description.isEmpty()) {
            throw new DogeException("Is it even possible to have a deadline for NOTHING?");
        } else if (deadline.length == 1) {
            throw new DogeException("Is it even possible to have a deadline with no END DATE?");
        } else {
            LocalDateTime dateTime = getDateTime(deadline[1].trim());
            Task currTask = new Deadline(description, dateTime);
            tasks.add(currTask);
            System.out.println(startLine);
            System.out.println("Stop troubling me, I've already added this task:");
            System.out.println(currTask);
            System.out.println(endLine);
        }
    }

    public static void todo(String input) throws DogeException {
        // Adding todo
        String currTask = input.trim();
        Task curr = new Todo(currTask);
        if (currTask.isEmpty()) {
            throw new DogeException("So doing NOTHING is a task?");
        } else {
            tasks.add(curr);
        }
        System.out.println(startLine);
        System.out.println("Stop troubling me, I've already added this task:");
        System.out.println(curr);
        System.out.println(endLine);
    }

    public static void event(String input) throws DogeException {
        // Adding Event
        String[] event = input.split("/");
        String description = event[0].trim();
        if (description.isEmpty()) {
            throw new DogeException("Is it even possible to have an event for NOTHING?");
        } else if (event.length == 1) {
            throw new DogeException("Is it even possible to have an event with no DATE?");
        } else {
            LocalDateTime dateTime = getDateTime(event[1].trim());
            Task currTask = new Event(description, dateTime);
            tasks.add(currTask);
            System.out.println(startLine);
            System.out.println("Stop troubling me, I've already added this task:");
            System.out.println(currTask);
            System.out.println(endLine);
        }
    }

    public static void delete(String input) throws DogeException {
        String[] info = input.split(" ");
        if (info.length == 1) {
            throw new DogeException("How am I suppose to delete something without knowing which task?");
        }

        try {
            Integer.parseInt(info[1]);
        } catch (NumberFormatException e) {
            throw new DogeException("Are you incapable of understanding what's an integer?");
        }

        if (Integer.parseInt(info[1]) > tasks.size() || Integer.parseInt(info[1]) < 0) {
            throw new DogeException("Can't you count? How am I supposed to delete something that doesn't exist?");
        } else {
            int pos = Integer.parseInt(info[1]) - 1;
            Task task = tasks.get(pos);
            System.out.println(startLine);
            System.out.println("I've already deleted for you! You're welcome.");
            System.out.println(task);
            tasks.remove(pos);
            System.out.println((tasks.size() > 1) ? "You have " + tasks.size() + " tasks left!" : "You " +
                    "have " + tasks.size() + " task left!");
            System.out.println(endLine);
        }

    }

    public static void mark(String input) throws DogeException {
        String[] info = input.split(" ");
        if (info.length == 1) {
            throw new DogeException("How am I suppose to mark something without knowing which task?");
        }

        try {
            Integer.parseInt(info[1]);
        } catch (NumberFormatException e) {
            throw new DogeException("Are you incapable of understanding what's an integer?");
        }

        if (Integer.parseInt(info[1]) > tasks.size() || Integer.parseInt(info[1]) < 0) {
            throw new DogeException("Can't you count? How am I supposed to mark something that doesn't exist?");
        } else {
            int pos = Integer.parseInt(info[1]) - 1;
            if (tasks.get(pos).isDone) {
                throw new DogeException("Your task has been marked before...");
            }
            tasks.get(pos).mark();
            System.out.println(startLine);
            System.out.println("Wasn't expecting you to finish that task, already marked for you!");
            System.out.println(tasks.get(pos));
            System.out.println(endLine);
        }

    }

    public static void unmark(String input) throws DogeException {
        String[] info = input.split(" ");
        if (info.length == 1) {
            throw new DogeException("How am I suppose to unmark something without knowing which task?");
        }

        try {
            Integer.parseInt(info[1]);
        } catch (NumberFormatException e) {
            throw new DogeException("Are you incapable of understanding what's an integer?");
        }

        if (Integer.parseInt(info[1]) > tasks.size() || Integer.parseInt(info[1]) < 0) {
            throw new DogeException("Can't you count? How am I supposed to unmark something that doesn't exist?");
        } else {
            int pos = Integer.parseInt(info[1]) - 1;
            if (!tasks.get(pos).isDone) {
                throw new DogeException("Your task is already unmarked, why unmark it again...");
            }
            tasks.get(pos).unmark();
            System.out.println(startLine);
            System.out.println("Knew that you didnt't finish that task, already unmarked it for you!");
            System.out.println(tasks.get(pos));
            System.out.println(endLine);
        }

    }

    public static void save(ArrayList<Task> tasks) throws DogeException {
        try {
            FileWriter fw = new FileWriter("./data/doge.txt");
            for (Task curr : tasks) {
                fw.write(System.lineSeparator() + curr.toString());
            }
            fw.close();
        } catch (IOException e) {
            throw new DogeException("Write to storage file failed!");
        }
    }

    public static ArrayList<Task> load() throws DogeException {
        try {
            Path p = Paths.get("./data");
            Files.createDirectories(p);
        } catch (IOException e) {
            throw new DogeException("Failed to create new directory!");
        }

        try {
            File f = new File( "./data/doge.txt");
            ArrayList<Task> temp = new ArrayList<>();

            if (f.createNewFile()) {
                System.out.println("<NOTICE> Storage file does not exist! Creating one right now...");
            } else {
                Scanner s = new Scanner(f);
                while (s.hasNextLine()) {
                    String[] curr = s.nextLine().split( "\\|");
                    String taskStatus = curr.length > 1 ? curr[1].trim() : null;
                    String[] dateTime;
                    Task currTask;
                    Date date;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    switch(curr[0].trim()) {
                    case "T":
                        currTask = new Todo(curr[2].trim());
                        if (taskStatus.equals("✓")) {
                            currTask.mark();
                        }
                        temp.add(currTask);
                        break;
                    case "D":
                        dateTime = curr[3].trim().substring(9).trim().split(" ");
                        date = new SimpleDateFormat("dd-MMM-yyyy").parse(dateTime[0]);
                        currTask = new Deadline(curr[2].trim(), getDateTime(sdf.format(date) + " " + dateTime[1]));
                        if (taskStatus.equals("✓")) {
                            currTask.mark();
                        }
                        temp.add(currTask);
                        break;
                    case "E":
                        dateTime = curr[3].trim().substring(3).trim().split(" ");
                        date = new SimpleDateFormat("dd-MMM-yyyy").parse(dateTime[0]);
                        currTask = new Event(curr[2].trim(), getDateTime(sdf.format(date) + " " + dateTime[1]));
                        if (taskStatus.equals("✓")) {
                            currTask.mark();
                        }
                        temp.add(currTask);
                        break;
                    }
                }
            }
            return temp;
        } catch (IOException e) {
            throw new DogeException("Failed to create new storage file!");
        } catch (ParseException e) {
            throw new DogeException("Failed to parse date!");
        }
    }
}
