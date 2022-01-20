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
    public void handleCommand(String s) throws SiriException{

        String[] inputSplit = s.split(" ", 2);

        if (inputSplit[0].equals("")) {
            throw new SiriException("Please ENTER something!!");
        } else {
            switch (inputSplit[0]) {
                case "mark":
                    if (inputSplit[1].equals("")) {
                        throw new SiriException("Please ENTER the item number to mark!!");
                    } else if (lst.size() == 0) {
                        throw new SiriException("There is currently no tasks!!");
                    } else {
                        try {
                            int index = Integer.parseInt(inputSplit[1]);
                            index--;

                            if (index >= lst.size() || index < 0) {
                                throw new SiriException("Please ENTER a number within the number of tasks!!");
                            } 
                            this.markItem(index);
                            break;
                            
                        } catch (NumberFormatException nfe) {
                            throw new SiriException("Please ENTER a valid item number to mark!!");
                        }
                    }
                case "unmark":
                    if (inputSplit[1].equals("")) {
                        throw new SiriException("Please ENTER the item number to unmark!!");
                    } else if (lst.size() == 0) {
                        throw new SiriException("There is currently no tasks!!");
                    } else {
                        try {
                            int index = Integer.parseInt(inputSplit[1]);
                            index--;

                            if (index >= lst.size() || index < 0) {
                                throw new SiriException("Please ENTER a number within the number of tasks!!");
                            } 
                            this.unmarkItem(index);
                            break;
                            
                        } catch (NumberFormatException nfe) {
                            throw new SiriException("Please ENTER a valid item number to unmark!!");
                        }
                    }
                case "list":
                    this.print();
                    break;
                case "todo":
                    if (inputSplit[1].equals("")) {
                        throw new SiriException("todo cannot be EMPTY!! Please ENTER something for todo!!");
                    } else {
                        ToDos todoTask = new ToDos(inputSplit[1]);
                        this.addItem(todoTask);
                        break;
                    }
                case "deadline":
                    if (inputSplit[1].equals("")) {
                        throw new SiriException("deadline cannot be EMPTY!! Please ENTER something for deadline!!");
                    } else {
                    String[] dlSplit = inputSplit[1].split(" /by ", 2);

                        if (dlSplit[1].equals("")) {
                            throw new SiriException("deadline has no date/time!! Please ENTER a date/time for deadline!!");
                        } else {
                            Deadline dlTask = new Deadline(dlSplit[0], dlSplit[1]);
                            this.addItem(dlTask);
                            break;
                        }
                    }
                case "event":
                    if (inputSplit[1].equals("")) {
                        throw new SiriException("event cannot be EMPTY!! Please ENTER something for event!!");
                    } else {
                        String[] eventSplit = inputSplit[1].split(" /at ", 2);

                        if (eventSplit[1].equals("")) {
                            throw new SiriException("event has no date/time!! Please ENTER a date/time for event!!");
                        } else {
                            Event eventTask = new Event(eventSplit[0], eventSplit[1]);
                            this.addItem(eventTask);
                            break;
                        }
                    }
                default:
                    throw new SiriException("OPPS!! I do not understand what you had keyed!! Please try again!!");
            }
        }

    }

    /*
        Method to add task item into the to do list.
    */
    public void addItem(Task task) {
        lst.add(task);
        System.out.printf("Got it! I've added this task:\n%s\nTotal tasks on the list: %d\n",
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
    public void markItem(int index) {
        lst.get(index).markDone();
    }

    /*
        Method to mark item of to do list undone.
    */
    public void unmarkItem(int index) {
        lst.get(index).markUndone();
    }
}