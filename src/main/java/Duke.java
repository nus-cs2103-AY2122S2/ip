public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}

class printer {
    public void print(String ss) {
        System.out.println(String.format("    %s", "=========================================="));
        System.out.println(String.format("    %s", ss));
        System.out.println(String.format("    %s", "=========================================="));
    }
}

