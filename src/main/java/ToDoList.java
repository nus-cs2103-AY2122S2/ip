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
        Method to add task item into the to do list.
    */
    public void addItem(String s) {
        Task newItem = new Task(s);
        lst.add(newItem);
        System.out.println("added: " + s);
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