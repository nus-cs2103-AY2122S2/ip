public class Duke {
    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println(duke.formatAnswer("hello"));
    }

    private String formatAnswer(String input) {
        /**
         * Prepares input for printing to System.out (adds top and bottom line)
         */

        // Variables
        String output;
        String line = "____________________________________________________________";

        output = "\t" + line + "\n"
                + "\t" + input + "\n"
                + "\t" + line + "\n";

        return output;

    }

    
}
