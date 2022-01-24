
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String DUKE_DIRECTORY = "C:\\DukeDirectory";
    private static final String DUKE_TXTFILE = "C:\\DukeDirectory\\DukeSave.txt";

    public static void main(String[] args) throws DukeException, IOException {

        String greeting = "Hello! I'm Duke\nWhat can I do for you?";
        String separation = "\n******************************\n";
        String welcomeMsg = "Welcome to Duke!! \nIt seems like it's your first time using it!!";

        File dukeDirectory = new File(DUKE_DIRECTORY);
        File dukeFile = new File (DUKE_TXTFILE);



        if (!dukeDirectory.exists()) {
            throw new FileNotFoundException("Directory is not found! Please create one");
        }

        if (!dukeFile.exists()) {

            throw new FileNotFoundException("File is not found in your directory! Please create one!");
           }


        System.out.println(separation + greeting + separation + "\nYou past Todos:");

        readData(DUKE_TXTFILE);


        BufferedReader bufReader = new BufferedReader(new FileReader(DUKE_TXTFILE));
        ArrayList<Task> list_of_inputs = new ArrayList<>();

        String line = bufReader.readLine();
        while (line != null) {
            Task t = new Task(line);
            list_of_inputs.add(t);
            line = bufReader.readLine();
        }
        bufReader.close();

        StringBuffer buffer = new StringBuffer();
        String fileContents = buffer.toString();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            String inp = br.readLine();
            String[] temp = inp.split(" ",2);


            try {
                DukeException d = new DukeException();
                d.invalidCommands(inp);
            } catch (DukeException e) {
                System.err.println(e);
                continue;
            }

            if (inp.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (inp.equals("list")) {
                System.out.println("Here are your task(s):");
                for (int i = 0; i < list_of_inputs.size(); i++) {
                    System.out.println(((i + 1) + ". " + list_of_inputs.get(i).message()));

                }

            } else if (temp[0].equals("mark")) {
                int curr_no = Integer.parseInt(temp[1]) - 1;
                Task task_done = list_of_inputs.get(curr_no);
                System.out.println("Good work!! I have marked it done:\n" + task_done.markedDone());
                task_done.updateData(DUKE_TXTFILE);

            } else if (temp[0].equals("unmark")) {
                int curr_no = Integer.parseInt(temp[1]) - 1;
                Task task_undone = list_of_inputs.get(curr_no);
                task_undone.markedUndone();
                System.out.println("Alrightt! I have marked it undone:\n" + task_undone.markedUndone());
                task_undone.updateData(DUKE_TXTFILE);

            } else if (temp[0].equals("todo")) {
                Todos todo = new Todos(temp[1]);
                list_of_inputs.add(todo);
                todo.updateData(DUKE_TXTFILE);
                System.out.println("Okayy!! I've added this task:\n " + todo.message() + "\n You have " + list_of_inputs.size() + " tasks in the list.");

            }  else if (temp[0].equals("deadline")) {
                String[] deadL = temp[1].split("/by", 2);
                Deadline deadLineTemp = new Deadline(deadL[0],deadL[1]);
                list_of_inputs.add(deadLineTemp);
                deadLineTemp.updateData(DUKE_TXTFILE);
                System.out.println("Deadline for this task:\n " + deadLineTemp.message() + "\n You have " + list_of_inputs.size() + " tasks in the list.");

             } else if (temp[0].equals("event")) {
                String[] event = temp[1].split("/at", 2);
                Event eventTemp = new Event(event[0],event[1]);
                list_of_inputs.add(eventTemp);
                eventTemp.updateData(DUKE_TXTFILE);
                System.out.println("I have added this task and the event time is:\n " + eventTemp.message() + "\n You have " + list_of_inputs.size() + " tasks in the list.");

            } else if (temp[0].equals("delete")){
                int curr_no = Integer.parseInt(temp[1]) - 1;
                list_of_inputs.remove(curr_no);
                Delete deleteTemp = new Delete(list_of_inputs.get(curr_no).description);
                System.out.println(deleteTemp.message()+ "\nYou have " + list_of_inputs.size() + " tasks in the list.");

            }
        }

        //start saving the results for next return
        for (int i = 0; i < list_of_inputs.size() ; i++) {
            if (i == 0) {
                writeToFile(list_of_inputs.get(i).message());
            } else {
                list_of_inputs.get(i).updateData(DUKE_TXTFILE);
                //System.out.println("CKY " + i + list_of_inputs.get(i).getStatusIcon());
            }
        }



    }

    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fileWriter = new FileWriter(DUKE_TXTFILE);
        fileWriter.write(textToAdd);
        fileWriter.close();
    }


    private static void readData(String filePath) {
        try {
            File f = new File(filePath);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot read data!!!");
        }
    }



}
