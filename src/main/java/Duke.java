public class Duke {
    public static void main(String[] args) {

        TaskManager duke = new TaskManager(Storage.getSavedList());
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");
        duke.handleLogic();


    }

}
