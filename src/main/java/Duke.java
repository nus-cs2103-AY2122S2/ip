import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class Duke {

    private static void appendToFile(String filePath, String textToAppend) {
        try {
            FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
            fw.write(textToAppend);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void changeLine(int lineNumber, String text, String filePath) throws IOException {
        File tempFile = new File("data/temp.txt");
        File inputFile = new File(filePath);

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;
        int i = 0;

        while((currentLine = reader.readLine()) != null) {

            if (i == lineNumber) {
                if (!text.equals("")) {
                    writer.write(text);
                }
            } else {
                writer.write(currentLine + System.getProperty("line.separator"));
            }

            i++;
        }
        writer.close();
        reader.close();

        Files.copy(Paths.get("data/temp.txt"),Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        Files.delete(Paths.get("data/temp.txt"));

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("========================");

        ArrayList<Task> list = new ArrayList<>(100);
        File f = new File("data/data.txt");
        f.getParentFile().mkdirs();

        try {
            if (!f.exists()) {
                f.createNewFile();
            } else {
                Scanner s = new Scanner(f).useDelimiter("\\|");
                String str;
                while(s.hasNextLine()) {
                    str = s.next();
                    if (str.equals("T")) {
                        int status = Integer.parseInt(s.next());
                        String activity = s.next();
                        Task newT = new Todo(activity);
                        newT.status = status;
                        list.add(newT);
                        s.nextLine();
                    } else if (str.equals("E")) {
                        int status = Integer.parseInt(s.next());
                        String activity = s.next();
                        String at = s.next();
                        Task newE = new Event(activity, at);
                        newE.status = status;
                        list.add(newE);
                        s.nextLine();
                    } else if (str.equals("D")) {
                        int status = Integer.parseInt(s.next());
                        String activity = s.next();
                        String by = s.next();
                        Task newD = new Deadline(activity, by);
                        newD.status = status;
                        list.add(newD);
                        s.nextLine();
                    } else {
                        System.out.println(str);
                    }
                }
                s.close();
            }
        } catch (IOException e) {
            System.exit(1);
        }


        while(true) {
            System.out.print("Me   : ");
            String message = sc.nextLine();
            String[] splitStr = message.split(" ", 2);
            boolean exitLoop = false;

            switch(splitStr[0]) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    exitLoop = true;
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        int num = i + 1;
                        System.out.print(num + ". ");
                        System.out.println(list.get(i).getStatus());
                    }
                    break;
                case "mark":
                    try {
                        int index = Integer.parseInt(splitStr[1]);
                        index--;
                        if (index < list.size() && index > -1) {
                            Task updateT = list.get(index);
                            updateT.status = 1;
                            list.set(index, updateT);
                            System.out.println("Nice! I've marked this task as done");
                            System.out.println(updateT.getStatus());
                            try {
                                changeLine(index,updateT.toString(),f.getPath());
                            } catch (IOException e) {
                                e.printStackTrace();
                                System.exit(1);
                            }
                        } else {
                            System.out.println("Invalid mark index");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid mark index");
                    }
                    break;
                case "unmark":
                    try{
                        int index = Integer.parseInt(splitStr[1]);
                        index--;
                        if (index < list.size() && index > -1) {
                            Task updateT = list.get(index);
                            updateT.status = 0;
                            list.set(index, updateT);
                            System.out.println("Oof! I've marked this task as undone");
                            System.out.println(updateT.getStatus());
                            try {
                                changeLine(index,updateT.toString(),f.getPath());
                            } catch (IOException e) {
                                e.printStackTrace();
                                System.exit(1);
                            }
                        } else {
                            System.out.println("Invalid mark index");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid mark index");
                    }
                    break;
                case "todo":
                    try {
                        if (splitStr[1].trim().equals("")) {
                            throw new ArrayIndexOutOfBoundsException("Blank");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Invalid todo command format.");
                        break;
                    }
                    Task newT = new Todo(splitStr[1]);
                    list.add(newT);
                    appendToFile(f.getPath(), newT.toString());
                    newT.addedTask();
                    System.out.println(newT.getStatus());
                    System.out.println("Now you've got " + list.size() +" tasks in the list.");

                    break;
                case "deadline":
                    try {
                        String[] tempStr = splitStr[1].split(" /by ");
                        Task newD = new Deadline(tempStr[0], tempStr[1]);
                        list.add(newD);
                        appendToFile(f.getPath(), newD.toString());
                        newD.addedTask();
                        System.out.println(newD.getStatus());
                        System.out.println("Now you've got " + list.size() +" tasks in the list.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Invalid deadline command format.");
                    }
                    break;
                case "event":
                    try {
                        String[] tempStr2 = splitStr[1].split(" /at ");
                        Task newE = new Event(tempStr2[0], tempStr2[1]);
                        list.add(newE);
                        appendToFile(f.getPath(), newE.toString());
                        newE.addedTask();
                        System.out.println(newE.getStatus());
                        System.out.println("Now you've got " + list.size() +" tasks in the list.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Invalid event command format.");
                    }
                    break;
                case "delete":
                    try {
                        int index = Integer.parseInt(splitStr[1]);
                        index--;
                        if (index < list.size() && index > -1) {
                            Task deletedT = list.remove(index);
                            System.out.println("Noted. I've removed this task:");
                            System.out.println(deletedT.getStatus());
                            System.out.println("Now you have " + list.size() + " tasks in the list.");
                            try {
                                changeLine(index,"",f.getPath());
                            } catch (IOException e) {
                                e.printStackTrace();
                                System.exit(1);
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid deletion index.");
                    }
                    break;
                default:
                    System.out.println("Invalid Task.\nValid tasks are: \"todo\", \"deadline\" and \"event\"");
                    break;

            }
            if (exitLoop) {
                break;
            }
        }
        sc.close();
    }
}
