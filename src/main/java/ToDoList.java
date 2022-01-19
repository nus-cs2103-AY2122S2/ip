/*
    ToDoList class creates a To Do List to simulate a normal day to day task list.
*/

import java.util.ArrayList;

class ToDoList {
    ArrayList<Task> lst;

    public ToDoList() {
        lst = new ArrayList<Task>(100);
    }

    /*
        Method to handle command pass to list
    */
    public void handleCommand(String s) {
        String[] inputSplit = s.split(" ", 2);
        
        System.out.println(inputSplit[0]);

        switch (inputSplit[0]) {
            case "mark":
                this.markItem(inputSplit[1]);
                break;
            case "unmark":
                this.unmarkItem(inputSplit[1]);
                break;
            case "list":
                this.print();
                break;
            case "todo":
                ToDos todoTask = new ToDos(inputSplit[1]);
                addItem(todoTask);
                break;
            case "deadline":
                String[] dlSplit = inputSplit[1].split(" /by ", 2);
                Deadline dlTask = new Deadline(dlSplit[0], dlSplit[1]);
                addItem(dlTask);
                break;
            case "event":
                String[] eventSplit = inputSplit[1].split(" /at ", 2);
                Event eventTask = new Event(eventSplit[0], eventSplit[1]);
                addItem(eventTask);
                break;
        }
    }

    /*
        Method to add task item into the to do list.
    */
    public void addItem(Task task) {
        lst.add(task);
        System.out.printf("Got it! I've added this task:\n %s\n Total tasks on the list: %d\n",
                            task.getItemAndStatus(), this.lst.size());
    }

    /*
        Method to print Task List out in order with status of each task.
    */
    public void print() {
        System.out.println("Task List:");
        lst.forEach((item) -> System.out.println((lst.indexOf(item)+1) + ". " + item.getItemAndStatus()));
    }

    /*
        Method to mark item of to do list done.
    */
    public void markItem(String index) {
        int itemNumber = Integer.parseInt(index);
        itemNumber--;
        lst.get(itemNumber).markDone();
    }

    /*
        Method to mark item of to do list undone.
    */
    public void unmarkItem(String index) {
        int itemNumber = Integer.parseInt(index);
        itemNumber--;
        lst.get(itemNumber).markUndone();
    }
}