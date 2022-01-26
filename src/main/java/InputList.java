import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class InputList {

    private int count;
    private ArrayList<Task> tasks;

    public InputList() {
        tasks = new ArrayList<Task>(100);

        try {
            File file = new File(
                    "tasklist.txt");
            file.createNewFile();

            BufferedReader br
                    = new BufferedReader(new FileReader(file));

            String st;

            while ((st = br.readLine()) != null) {
                String[] words = st.split(",");
                if(words[0].equals("TODO")) {
                    tasks.add(new Todo(words[2], words[1].equals("true")));
                } else if (words[0].equals("DEADLINE")) {
                    tasks.add(new Deadline(words[2], LocalDate.parse(words[3]), words[1].equals("true")));
                } else if (words[0].equals("EVENT")) {
                    tasks.add(new Event(words[2], LocalDate.parse(words[3]), words[1].equals("true")));
                }
            }

        } catch(IOException e) {
            System.out.println(e.getMessage() + "---IO Exception");
        }
    }

    public static void main(String[] args) {

    }

    public void writeToFile() {
        try {
            File file = new File("tasklist.txt");
            FileWriter writer = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(writer);

            for(int i = 0; i < tasks.size(); i++) {
                bw.write(tasks.get(i).getTaskString() + "\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printList() {
        for(int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1);
            System.out.println(". " + tasks.get(i).toString());
        }
    }

    public void add(Task newTask) {
        tasks.add(newTask);
        count++;
    }

    public void delete(int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index - 1).toString());
        tasks.remove(index - 1);
        count--;
    }

    public void mark(int index) {
        tasks.get(index - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index - 1).toString());
    }

    public void unmark(int index) {
        tasks.get(index - 1).markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index - 1).toString());
    }

    public int getCount() {
        return tasks.size();
    }
}
