public class Ui {
    Ui() {
    }

    void printBootUp() {
        System.out.println("Hello! I am Duck 🐥\nWhat can I do for you?");
    }

    void printLoadingError() {
        System.out.println("Since loading the save file has failed, your task list is currently empty");
    }

    void printLineSeparator() {
        System.out.println("____________________________________________________________");
    }

    void printException(DukeException e) {
        System.out.println(String.format("%s *quack*", e.getMessage()));
    }
}
