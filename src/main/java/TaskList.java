public class TaskList {

  private final String[] list;
  private int numTasks;

  public TaskList() {
    this.list = new String[100];
    this.numTasks = 0;
  }

  public void add(String task) {
    this.list[numTasks++] = task;
  }

  public void summary() {
    for (int i = 0; i < numTasks; i++) {
      System.out.printf("%d. %s%n", i + 1, list[i]);
    }
    System.out.println();
  }
}
