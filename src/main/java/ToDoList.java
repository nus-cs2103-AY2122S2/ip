/*
    ToDoList class creates a To Do List to simulate a normal day to day task list.
*/

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

class ToDoList {
    ArrayList<Task> lst;
    ArrayList<Deadline> dlLst;
    ArrayList<Event> eLst;

    public ToDoList() {
        lst = new ArrayList<Task>(100);
        dlLst = new ArrayList<Deadline>();
        eLst = new ArrayList<Event>();
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
                    if (inputSplit.length == 1 || inputSplit[1].trim().length() == 0) {
                        throw new SiriException("Please ENTER the item number to mark!!");
                    } else if (lst.size() == 0) {
                        throw new SiriException("There is currently no tasks!!");
                    } else {
                        try {
                            int index = Integer.parseInt(inputSplit[1]);
                            index--;

                            if (index >= lst.size() || index < 0) {
                                throw new SiriException("Please ENTER a number within the number of tasks!!");
                            } else {
                                this.markItem(index);
                                break;
                            }
                            
                        } catch (NumberFormatException nfe) {
                            throw new SiriException("Please ENTER a valid item number to mark!!");
                        }
                    }
                case "unmark":
                    if (inputSplit.length == 1 || inputSplit[1].trim().length() == 0) {
                        throw new SiriException("Please ENTER the item number to unmark!!");
                    } else if (lst.size() == 0) {
                        throw new SiriException("There is currently no tasks!!");
                    } else {
                        try {
                            int index = Integer.parseInt(inputSplit[1]);
                            index--;

                            if (index >= lst.size() || index < 0) {
                                throw new SiriException("Please ENTER a number within the number of tasks!!");
                            } else {
                                this.unmarkItem(index);
                                break;
                            }
                            
                        } catch (NumberFormatException nfe) {
                            throw new SiriException("Please ENTER a valid item number to unmark!!");
                        }
                    }
                case "list":
                    if (inputSplit.length == 1 || inputSplit[1].trim().length() == 0) {
                        this.print();
                        break;
                    } else {
                        throw new SiriException("OPPS!! list does not take in any parameter!!");
                    }
                case "todo":
                    if (inputSplit.length == 1 || inputSplit[1].trim().length() == 0) {
                        throw new SiriException("todo cannot be EMPTY!! Please ENTER something for todo!!");
                    } else {
                        ToDos todoTask = new ToDos(inputSplit[1]);
                        this.addItem(todoTask);
                        break;
                    }
                case "deadline":
                    if (inputSplit.length == 1 || inputSplit[1].trim().length() == 0) {
                        throw new SiriException("deadline cannot be EMPTY!! Please ENTER something for deadline!!");
                    } else {
                        String[] dlSplit = inputSplit[1].split(" /by ", 2);

                        if (dlSplit.length == 1 || dlSplit[1].trim().length() == 0) {
                            throw new SiriException("deadline has no date/time!! Please ENTER a date/time for deadline!!");
                        } else {

                            try {
                                Deadline dlTask;
                                String[] dlDateTime = dlSplit[1].split(" ", 2);
                                LocalDate dlDate = ToDoList.getDate(dlDateTime[0].trim());
                                if (dlDateTime.length == 1 || dlDateTime[1].trim().length() == 0) {
                                    dlTask = new Deadline(dlSplit[0], dlDate);
                                } else {
                                    LocalTime dlTime = ToDoList.getTime(dlDateTime[1].trim());
                                    dlTask = new Deadline(dlSplit[0], dlDate, dlTime);
                                }                        
                                this.addItem(dlTask);
                                break;
                            } catch (DateTimeParseException dtpe) {
                                throw new SiriException("deadline date/time format is wrong!!\nPlease ENTER your date time in DD-MM-YYYY HH:MM (if applicable) format!!");
                            }
                        }
                    }
                case "event":
                    if (inputSplit.length == 1 || inputSplit[1].trim().length() == 0) {
                        throw new SiriException("event cannot be EMPTY!! Please ENTER something for event!!");
                    } else {
                        String[] eventSplit = inputSplit[1].split(" /at ", 2);

                        if (eventSplit.length == 1 || eventSplit[1].trim().length() == 0) {
                            throw new SiriException("event has no date/time!! Please ENTER a date and time for event!!");
                        } else {
                            try {
                                String[] eventDateTime = eventSplit[1].split(" ", 2);
                                LocalDate eDate = ToDoList.getDate(eventDateTime[0].trim());
                                Event eventTask;
                                if (eventDateTime.length == 1 || eventDateTime[1].trim().length() == 0) {
                                    throw new SiriException("Missing date/time field!! Please ENTER date your date time in DD-MM-YYYY HH:MM format!!");
                                } else {
                                    LocalTime eTime = ToDoList.getTime(eventDateTime[1].trim());
                                    eventTask = new Event(eventSplit[0], eDate, eTime);
                                }
                                this.addItem(eventTask);
                                break;
                            } catch (DateTimeParseException dtpe) {
                                throw new SiriException("event date/time format is wrong!!\nPlease ENTER your date time in DD-MM-YYYY  HH:MM format!!");
                            }
                        }
                    }
                case "delete":
                    if (inputSplit.length == 1 || inputSplit[1].trim().length() == 0) {
                        throw new SiriException("Please ENTER the item number to delete!!");
                    } else if (lst.size() == 0) {
                        throw new SiriException("There is currently no tasks!!");
                    } else {
                        try {
                            int index = Integer.parseInt(inputSplit[1].trim());
                            index--;

                            if (index >= lst.size() || index < 0) {
                                throw new SiriException("Please ENTER a number within the number of tasks!!");
                            } else {
                                this.deleteTask(index);
                                break;
                            }
                            
                        } catch (NumberFormatException nfe) {
                            throw new SiriException("Please ENTER a valid item number to unmark!!");
                        }
                    }
                case "eprint":
                    if (inputSplit.length == 1 || inputSplit[1].trim().length() == 0) {
                        throw new SiriException("Please ENTER a date!!");
                    } else {
                        try {
                            LocalDate eCheckedDate = ToDoList.getDate(inputSplit[1].trim());
                            this.printEventOn(eCheckedDate);
                        } catch (DateTimeParseException dtpe) {
                            throw new SiriException("Please ENTER your date in DD-MM-YYYY format!!");
                        }
                    }
                    break;
                case "dlprint":
                    if (inputSplit.length == 1 || inputSplit[1].trim().length() == 0) {
                        throw new SiriException("Please ENTER a date!!");
                    } else {
                        try {
                            LocalDate dlCheckedDate = ToDoList.getDate(inputSplit[1].trim());
                            this.printDeadlineOn(dlCheckedDate);
                        } catch (DateTimeParseException dtpe) {
                            throw new SiriException("Please ENTER your date in DD-MM-YYYY format!!");
                        }
                    }
                    break;
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
        if (task instanceof Deadline) {
            Deadline tmp = (Deadline) task;
            dlLst.add(tmp);
        } else if (task instanceof Event) {
            Event tmp = (Event) task;
            eLst.add(tmp);
        }
        System.out.printf("Got it! I've added this task:\n%s\nTotal tasks on the list: %d\n",
                            task.getItemAndStatus(), this.lst.size());
    }

    /*
        Method to delete task from Task List
    */
    public void deleteTask(int index) {
        Task removedTask = lst.remove(index);
        if (removedTask instanceof Deadline) {
            Deadline tmp = (Deadline) removedTask;
            dlLst.remove(tmp);
        } else if (removedTask instanceof Event) {
            Event tmp = (Event) removedTask;
            dlLst.remove(tmp);
        }
        System.out.printf("Successfully removed the following task:\n%s\nYou have %d tasks remaining!!\n", removedTask.getItemAndStatus(), this.lst.size());
    }

    /*
        Method to print Task List out in order with status of each task.
    */
    public void print() {
        if (lst.size() == 0) {
            System.out.println("There is currently no item on the list!!");
        } else {
            System.out.println("Task List:");
            lst.forEach((item) -> System.out.println((lst.indexOf(item)+1) + ". " + item.getItemAndStatus()));
        }
    }

    /*
        Method to mark item of to do list done.
    */
    public void markItem(int index) {
        lst.get(index).markDone();
    }

    public void printEventOn(LocalDate date) {
        ArrayList<Event> tmp = new ArrayList<Event>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-LLL-yyyy");

        if (eLst.size() != 0) {
            for (int i = 0; i < eLst.size(); i++) {
                if (eLst.get(i).dateCompare(date)) {
                    tmp.add(eLst.get(i));
                }
            }

            if (tmp.size() == 0) {
                System.out.printf("No event on %s!!\n", date.format(dtf));
            } else {
                System.out.printf("%d events on %s:\n", tmp.size(), date.format(dtf));
                tmp.forEach((item) -> System.out.println((tmp.indexOf(item) + 1) + ". " + item.getItemAndStatus()));
            }
        } else {
                System.out.printf("No event on %s!!\n", date.format(dtf));
        }
    }

    public void printDeadlineOn(LocalDate date) {
        ArrayList<Deadline> tmp = new ArrayList<Deadline>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-LLL-yyyy");

        if (dlLst.size() != 0) {
            for (int i = 0; i < dlLst.size(); i++) {
                if (dlLst.get(i).dateCompare(date)) {
                    tmp.add(dlLst.get(i));
                }
            }

            if (tmp.size() == 0) {
                System.out.printf("No deadline on %s!!\n", date.format(dtf));
            } else {
                System.out.printf("%d deadlines on %s:\n", tmp.size(), date.format(dtf));
                tmp.forEach((item) -> System.out.println((tmp.indexOf(item) + 1) + ". " + item.getItemAndStatus()));
            }
        } else {
            System.out.printf("No deadline on %s!!\n", date.format(dtf));
        }
    }

    /*
        Method to mark item of to do list undone.
    */
    public void unmarkItem(int index) {
        lst.get(index).markUndone();
    }

    private static LocalDate getDate(String dateString) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate ld = LocalDate.parse(dateString, dtf);

        return ld;
    }

    private static LocalTime getTime(String timeString) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime lt = LocalTime.parse(timeString, dtf);

        return lt;
    }
}