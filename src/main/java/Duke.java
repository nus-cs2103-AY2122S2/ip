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

    public static void main(String[] args) {

        FastIO io = new FastIO();
        ArrayList<String> tasks = new ArrayList<>();

        greet();
        boolean exited = false;

        while (!exited) {
            String input = io.nextLine();
            if (input.equals("bye")) {
                exited = true;
            } else if (input.equals("list")) {
                list(tasks);
            } else {
                store(tasks, input);
            }
        }
        bye();
    }

    public static <T> void store(Collection<? super T> collection, T value) {
        collection.add(value);
        echo("added: " + value.toString());
    }

    public static void list(Collection<? extends Object> collection) {
        StringBuilder sb = new StringBuilder("");
        for (Object var : collection) {
            sb.append(var.toString() + "\n     ");
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
