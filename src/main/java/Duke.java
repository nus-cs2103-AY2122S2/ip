public class Duke {
    public static void main(String[] args) {

<<<<<<< .merge_file_hgHZFh
        TaskManager duke = new TaskManager(Storage.getSavedList());
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");
        duke.handleLogic();


=======
        TaskManager duke = new TaskManager();
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");
        duke.handleLogic();
        System.out.println("Bye! Hope to see you again soon");
>>>>>>> .merge_file_v0MEpf
    }

}
