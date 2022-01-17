public class InputParser {
    public int run(String ss, Printer p) {
        if(ss.equals("bye")) {
            p.print(" Bye. Hope to see you again soon!");
            return -1;
        } else {
            p.print(ss);
            return 0;
        }
    }
}
