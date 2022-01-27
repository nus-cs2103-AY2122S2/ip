package van;

import java.util.Scanner;
import java.util.ArrayList;

public class Ui {

  private Scanner userInput;

  public Ui() {
    System.out.println("Hello I am Van");
    System.out.println("How may i assist you");
    this.userInput = new Scanner(System.in);
  }

  public String standBy() {
    this.printDivider();
    String command = userInput.nextLine();
    return command;
  }

  public void exitMessage() {
    System.out.println("Bye");
  }

  public void printDivider() {
    System.out.println("-------------------------------------------");
  }

  public void invalidMessage(String message) {
    this.printDivider();
    System.out.println(message);
  }

  public void taskMessage (Task task, int count) {
    this.printDivider();
    System.out.println("Task added");
    System.out.println(" " + task.getStatus());
    System.out.println(count + " tasks pending");
  }

  public void printList (ArrayList<Task> taskList) {
    this.printDivider();
    System.out.println("Pending tasks:");
    for (int i = 0; i < taskList.size(); i++) {
      System.out.println(i+1 + ". " + taskList.get(i).getStatus());
    }
  }
}
