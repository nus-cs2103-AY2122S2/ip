public class Ui {
    public Ui() {
    }

    /* Initial greeting for Duke */
    public void startGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printResponse("Sup! Name's Duke \nHow can I help you today?");
    }

    /* Print in the response format */
    public void printResponse(String response) {
        System.out.println(
                "\n--------------------------------------------------------------------------------------------");
        System.out.println("Duke Speaking:\n");
        System.out.println(response);
        System.out.println(
                "--------------------------------------------------------------------------------------------\n");
    }

    /* Print in the response format */
    public void printError(String errorStatement) {
        printResponse("ERROR ERROR! DUKE HAS ERROR: \n \n" + errorStatement);
    }
}
