import java.io.*;
import java.util.StringTokenizer;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {


        System.out.println("Hello! I'm Ducky! \nWhat can I do for you?" +
                "\n-----------------");

        while(true){
            Scanner myObj = new Scanner(System.in);
            String response = myObj.nextLine();
            if (response.equals("bye")){
                String byeResponse = "Bye. Hope to see you again soon! " +
                        "\n-----------------";
                System.out.println(byeResponse);
                break;
            }
            else {
                System.out.println(response + "\n-----------------");
            }
        }
    }
}


/**
 * Fast I/O
 * @source https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
 */
class FastIO extends PrintWriter
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
            catch (IOException e)
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
