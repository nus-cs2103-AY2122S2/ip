import exceptions.DukeExceptions;
import exceptions.DukeInvalidInput;
import exceptions.DukeInvalidTodo;

import java.io.*;
import java.util.Objects;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void lineOne() {
        System.out.println("*************************************************************************");
    }

    public static void lineTwo() {
        System.out.println("|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|");
    }

    public static String makeDesc(String[] text, int len) {
        String newText = "";
        for (int i = 1; i < len; i++) {
            newText = newText + text[i] + " ";
        }
        return newText.trim();
    }

    public static void writeToFile(ArrayList<Task> list) throws IOException {
        try {
            File f = new File("/Users/riakhaitan/iP/ip/data/duke.txt");
            f.createNewFile();

            boolean directory = f.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(f, false);
            for (Task task : list) {
                String fileInput;
                Task ele = task;
                if (ele instanceof ToDo) {
                    fileInput = "[T][" + ele.getStatusIcon() + "]/" + ele.description;
                } else if (ele instanceof Deadline) {
                    Deadline deadL = (Deadline) ele;
                    fileInput = "[D][" + deadL.getStatusIcon() + "]/" + deadL.description + "/" + deadL.when;
                } else {
                    Event eve = (Event) ele;
                    fileInput = "[E][" + eve.getStatusIcon() + "]/" + eve.description + "/" + eve.at;
                }
                writer.write(fileInput + "\n");
                writer.flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void loadFromFile(ArrayList<Task> list) throws IOException {
        File f = new File("/Users/riakhaitan/iP/ip/data/duke.txt");
        f.createNewFile();
        String input, desc;
        Task t;
        Scanner in = new Scanner(f);
        while(in.hasNextLine()) {
            input = in.nextLine();
            String[] tSplit = input.split("/");
            String[] splitT = tSplit[0].split("]");
            switch(splitT[0]) {
                case "[T":
                    t = new ToDo(tSplit[1]);
                    break;
                case "[E":
                    t = new Event(tSplit[1], tSplit[2]);
                    break;
                case "[D":
                    t = new Deadline(tSplit[1], LocalDate.parse(tSplit[2]));
                    break;
                default:
                    t = new Task("eee");
                    break;
            }
            if(splitT[1].equals("[X")) {
                t.done();
                list.add(t);
            }
            else {
                list.add(t);
            }
        }
        printList(list, list.size());
        in.close();
    }

    public static int exitHalloumi() {
        lineOne();
        System.out.println("See you soon! Have a good day ^_^");
        lineOne();
        return 1;
    }

    public static void printList(ArrayList<Task> list, int size) {
        lineOne();
        System.out.println("List:");
        if(list.isEmpty()) {
            System.out.println("No tasks to complete! ^_^");
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
        lineOne();
    }

    public static void main(String[] args) throws DukeExceptions, IOException {
        Scanner sc = new Scanner(System.in);
        int num = 0;
        ArrayList<Task> lists = new ArrayList<Task>();
        loadFromFile(lists);
        lineOne();
        System.out.println("Hi! I'm Halloumi ^_^");
        System.out.println("What do you need help with today?");
        lineOne();
        do {
            String text = sc.nextLine();
            String[] textSplitOne = text.split("/"); //by and at
            String[] textSplit = textSplitOne[0].split(" ");
            String fullDesc = makeDesc(textSplit, textSplit.length);
            try {
                switch (textSplit[0]) {
                    case "bye":
                        num = exitHalloumi();
                        break;
                    case "list":
                        printList(lists, lists.size());
                        break;
                    case "mark":
                        lineOne();
                        System.out.println("Good Job! ^_^");
                        System.out.println("Task number " + textSplit[1] + " has been marked as done!");
                        int tNum = Integer.parseInt(textSplit[1]);
                        lists.get(tNum - 1).done();
                        System.out.println(lists.get(tNum - 1));
                        lineOne();
                        break;
                    case "unmark":
                        lineOne();
                        System.out.println("I've unmarked task number " + textSplit[1]);
                        System.out.println("Complete it soon! ^_^");
                        int tNo = Integer.parseInt(textSplit[1]);
                        lists.get(tNo - 1).undo();
                        System.out.println(lists.get(tNo - 1));
                        lineOne();
                        break;
                    case "delete" :
                        lineOne();
                        System.out.println("Noted. I've removed this task:");
                        //System.out.println("Complete it soon! ^_^");
                        int t2No = Integer.parseInt(textSplit[1]);
                        System.out.println(lists.get(t2No - 1));
                        lists.remove(t2No - 1);
                        lineOne();
                        break;
                    case "todo":
                        try {
                            if(fullDesc.equals(" ") || fullDesc.equals("")) {
                                throw new DukeInvalidTodo();
                            }
                        }
                        catch(DukeInvalidTodo e) {
                            System.err.println(e.getMessage());
                            continue;
                        }
                        lineTwo();
                        System.out.println("New task added:");
                        Task t = new ToDo(fullDesc);
                        lists.add(t);
                        System.out.println(t);
                        System.out.println("You have " + lists.size() + " tasks left now! ^_^");
                        lineTwo();
                        break;
                    case "event":
                        lineTwo();
                        System.out.println("New task added:");
                        Task t1 = new Event(fullDesc, textSplitOne[1]);
                        lists.add(t1);
                        System.out.println(t1);
                        System.out.println("You have " + lists.size() + " tasks left now! ^_^");
                        lineTwo();
                        break;
                    case "deadline":
                        lineTwo();
                        System.out.println("New task added:");
                        String[] date = textSplitOne[1].split(" ");
                        Task t2 = new Deadline(fullDesc, LocalDate.parse(date[1]));
                        lists.add(t2);
                        System.out.println(t2);
                        System.out.println("You have " + lists.size() + " tasks left now! ^_^");
                        lineTwo();
                        break;
                    default:
                        throw new DukeInvalidInput();

                }
            } catch (DukeInvalidInput e) {
                System.err.println(e.getMessage());
            }
        }
            while (num == 0) ;
            writeToFile(lists);
    }
}

