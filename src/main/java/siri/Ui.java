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
        return "Hello from\n" + this.logo + "\n";
    }

    /**
     * Class method to print additional message if there is a backlog data.
     */
    public static String startUpSavedData() {
        return "Welcome back!!\n";
    }

    /**
     * Closes scanner and print exit message.
     */
    public String exit() {
        return "Bye!! Hope to see you again soon!!\n";
    }
}
