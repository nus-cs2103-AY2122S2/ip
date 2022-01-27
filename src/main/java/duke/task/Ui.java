package duke.task;

import java.util.ArrayList;

public class Ui {
    public Ui() {
    }

    public void greeting() {
        String logo = "__  __ ____________\n" +
                "\\ \\/ /|_  /_  /_  /\n" +
                " >  <  / / / / / / \n" +
                "/_/\\_\\/___/___/___|";
        System.out.println(logo);
        System.out.println("Hello uwu! I'm xzzz,");
        System.out.println("You can check your schedwle here (ɔ◔‿◔)ɔ ♥!");
    }

    public void goodbye() {
        System.out.println("Cya later~ ≧◉◡◉≦");
    }

    public void displayList(ArrayList<Task> toDoList) {
        int number = 1;
        System.out.println("here are your tasks ☜(ˆ▿ˆc)");
        for (Task item : toDoList) {
            System.out.println(number + ". " + item.toString());
            number++;
        }
    }

    public void showIllegalArgumentError(Exception ex) {
        System.out.println(ex);
    }

    public void showIncompleteArgumentError() {
        System.out.println("UH-OH!! you gotta fill in the description and deadline date " +
                "/ event date to create a valid task (> <ლ)");
    }

    public void confirmAddition(Task newTask, ArrayList<Task> toDoList) {
        System.out.println("okie!! (✿◠‿◠)  i have added: \n" +
                newTask + "\n" +
                "now there are " + toDoList.size() + " tasks in the list! get to work (ง︡'-'︠)ง");
    }

    public void doNotUnderstand() {
        System.out.println("sowwy i don't understand what that means ಠ_ಥ try something else pwease??");
    }

    public void markAsDone(ArrayList<Task> toDoList, int idx) {
        System.out.println("yay!!! this task is now marked as done ٩(˘◡˘)۶");
        System.out.println(toDoList.get(idx - 1).toString());
    }

    public void unmarkAsDone(ArrayList<Task> toDoList, int idx) {
        System.out.println("this task is now marked as not done yet... do it soon! ᕙ(`▿´)ᕗ");
        System.out.println(toDoList.get(idx - 1).toString());
    }

    public void confirmRemoval(Task removed, ArrayList<Task> toDoList) {
        System.out.println("OKI!! i have removed this task: \n" +
                removed + "\n" +
                "now there are " + toDoList.size() + " tasks in the list! get to work (ง︡'-'︠)ง");
    }

    public void showLoadingError() {
        System.out.println("UH-OH!! seems like the file is not in the right format... (⊙.⊙) \n" +
                "don't worry! I'll start a new file for you!");
    }

    /**
     * Prints the specified relevant tasks for the user.
     * @param relevantTasks List of relevant tasks containing a keyword.
     */
    public void showFindResult(ArrayList<Task> relevantTasks) {
        if (relevantTasks.isEmpty()) {
            System.out.println("There are no matching tasks （・⊝・）");
        } else {
            System.out.println("Here are the matching tasks in the list (｡◕‿◕｡) :");
            int counter = 1;
            for (Task task : relevantTasks) {
                System.out.println(counter + ". " + task);
                counter++;
            }
        }
    }
}
