package siri;

class Ui {
    private String logo;
    public Ui(String logo) {
        this.logo = logo;
    }

    /**
     * Class method to print the start up message.
     */
    public String startUp() {
        return "Hello from" + this.logo + "\n";
    }

    /**
     * Class method to print additional message if there is a backlog data.
     */
    public static String startUpSavedData() {
        return "Welcome back!!\n";
    }

    // /**
    //  * Scans for next user input.
    //  *
    //  * @return String of the input that is taken in.
    //  */
    // public String takeInput() {
    //     System.out.println("What can I do for you?");
    //     return this.sc.nextLine();
    // }

    // /**
    //  * Class method to print a separator between different commands.
    //  */
    // public static void separator() {
    //     System.out.println("====================");
    // }

    /**
     * Closes scanner and print exit message.
     */
    public String exit() {
       return "Bye!! Hope to see you again soon!!\n";
    }
}
