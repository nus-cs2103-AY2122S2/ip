import java.io.*;
import java.util.*;

public class Duke {

    public static class FastIO extends PrintWriter
    {
        BufferedReader br;
        StringTokenizer st;

        public FastIO()
        {
            super(new BufferedOutputStream(System.out));
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException  e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt()
        {
            return Integer.parseInt(next());
        }

        long nextLong()
        {
            return Long.parseLong(next());
        }

        double nextDouble()
        {
            return Double.parseDouble(next());
        }

        String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
    }
    public interface ICompletable {
        public void setCompleted(boolean value);
    }

    public static class Task implements ICompletable {

        protected Object item;
        protected boolean completed;

        public Task (Object obj) {
            item = obj;
            completed = false;
        }

        public void setCompleted(boolean value) {
            completed = value;
        }

        public String toString() {
            String display = completed ? "[X]" : "[ ]";
            return display + " " + item.toString();
        }
    }

    public static void main(String[] args) {

        FastIO io = new FastIO();
        ArrayList<Task> tasks = new ArrayList<>();

        greet();
        boolean exited = false;

        while (!exited) {
            String input = io.nextLine();
            if (input.equals("bye")) {
                exited = true;
            } else if (input.equals("list")) {
                list(tasks);
            } else {
                String[] tokens = input.split(" ");
                if (tokens[0].equals("mark")) {
                    mark(tasks, tokens[1]);
                } else if (tokens[0].equals("unmark")) {
                    unMark(tasks, tokens[1]);
                } else {
                    Task newTask = new Task(input);
                    store(tasks, newTask);
                }
            }
        }
        bye();
    }

    public static <T extends ICompletable> void mark(ArrayList<T> collection, String input) {
        int index = Integer.parseInt(input) - 1;
        String output = "Nice! I've marked this task as done:\n       ";
        collection.get(index).setCompleted(true);
        echo(output + collection.get(index).toString());
    }

    public static <T extends ICompletable> void unMark(ArrayList<T> collection, String input) {
        int index = Integer.parseInt(input) - 1;
        String output = "OK, I've marked this task as not done yet:\n       ";
        collection.get(index).setCompleted(false);
        echo(output + collection.get(index).toString());
    }

    public static <T> void store(ArrayList<? super T> collection, T value) {
        collection.add(value);
        echo("added: " + value.toString());
    }

    public static void list(ArrayList<? extends Object> collection) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:");
        int count = 0;
        for (Object var : collection) {
            count++;
            sb.append("\n     " + count + ". " + var.toString());
        }
        echo(sb.toString().trim());
    }

    public static void greet() {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
    }

    public static void echo(String input) {
        System.out.println("    ____________________________________________________________\n" +
                "     " + input + "\n" +
                "    ____________________________________________________________\n");
    }

    public static void bye() {
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
