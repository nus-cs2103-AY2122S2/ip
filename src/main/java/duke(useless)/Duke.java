//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Scanner;
//
//public class Duke {
//    private final static String line =
//            "____________________________________________________________\n";
//    private final Scanner s = new Scanner(System.in);
//    private boolean finished = false;
//    private final TaskList list = new TaskList();
//
//    public boolean isFinished() {
//        return this.finished;
//    }
//
//    private String read() {
//        String input = s.nextLine();
//        if (input.equals("bye")) {
//            this.finished = true;
//            return "Bye. Hope to see you again soon!";
//        }
//        else if (input.equals("list")) return listing();
//        else if (input.length() == 6
//                || input.length() == 7 && input.startsWith("mark "))
//            return mark(input);
//        else if (input.length() == 8
//                || input.length() == 9 && input.startsWith("delete "))
//            return delete(input);
//        else return add(input);
//    }
//
//    private void respond(String respond) {
//        respond = line + respond + "\n" + line;
//        System.out.println(respond);
//    }
//
//    private String listing() {
//        String s = "";
//        for (int i = 0; i < list.size(); i++) {
//            s += (i + 1) + ".";
//            s += list.get(i) + "\n";
//        }
//        return s;
//    }
//
//    private String mark(String str) {
//        try{
//            int i = Integer.parseInt(str.substring(5)) - 1;
//            Task t = list.get(i);
//            t.mark();
//            return "Nice! I've marked this task as done:\n  " + t;
//        }
//        catch (NumberFormatException e){
//            return "Please enter a number after mark! (E.g. mark 2)";
//        } catch (IndexOutOfBoundsException e) {
//            return "Please enter a valid number!";
//        }
//    }
//
//    private String delete(String str) {
//        try{
//            int i = Integer.parseInt(str.substring(7)) - 1;
//            Task t = list.get(i);
//            this.list.remove(t);
//            return "Noted. I've removed this task: \n  " +
//                    t + "\n     Now you have " + list.size() + " tasks in the list.";
//        }
//        catch (NumberFormatException e){
//            return "Please enter a number after delete! (E.g. delete 2)";
//        } catch (IndexOutOfBoundsException e) {
//            return "Please enter a valid number!";
//        }
//    }
//
//    private String add(String input) {
//        try {
//            Task t;
//            if (input.startsWith("todo ")) t = new Task(input.split(" ", 2)[1], Type.TODO);
//            else if (input.startsWith("event ")) {
//                t = new Task(input.split("/")[0].split(" ", 2)[1], Type.EVENT);
//                t.setTime(input.split("/at ", 2)[1]);
//            } else if (input.startsWith("deadline ")){
//                t = new Task(input.split("/")[0].split(" ", 2)[1], Type.DEADLINE);
//                t.setTime(input.split("/by ", 2)[1]);
//            } else {
//                throw new NoGoodException("");
//            }
//            this.list.add(t);
//            record(t + "\n");
//            return "Got it. I've added this task:\n  " +
//                    t + "\nNow you have " + this.list.size() + " tasks in the list.";
//        }
//        catch (ArrayIndexOutOfBoundsException e) {
//            return "Your expression of time is not valid";
//        } catch (NoGoodException e) {
//            return "OOPS!!! I'm sorry, but I don't know what that means :-(";
//        }
//    }
//
//    private void makeDirectory() throws IOException{
//        String fileName = "../data";
//        Path path = Paths.get(fileName);
//        if (!Files.exists(path)) {
//            Files.createDirectory(path);
//            creatFile();
//        }
//    }
//
//    private void creatFile() throws IOException {
//        File file = new File("../data/record.txt");
//        if (!file.createNewFile())  {
//            if (file.delete()) {
//                creatFile();
//            } else {
//                System.out.println("something is wrong.");
//            }
//        }
//    }
//
//    private void record(String info) {
//        try {
//            makeDirectory();
//            FileWriter myWriter = new FileWriter("../data/record.txt");
//            myWriter.write(info);
//            myWriter.close();
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//    }
//
//
//}
