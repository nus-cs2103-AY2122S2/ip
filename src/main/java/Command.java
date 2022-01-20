abstract class Command {

    protected enum CommandName {
        BYE {
            @Override
            public void run(String parameter, ItemList<Task> taskList) {
                System.out.println("Arrivederci!");
                System.exit(0);
            }
        },
        LIST {
            @Override
            public void run(String parameter, ItemList<Task> taskList) {
                System.out.println("Here you go sir:");
                taskList.printList();
            }
        },
        MARK {
            @Override
            public void run(String parameter, ItemList<Task> taskList) throws DukeExceptions {
                if (parameter.isBlank())
                    throw EmptyNumber.createEmptyNumber("Mark");
                try {
                    taskList.getItem(Integer.parseInt(parameter)).setDone();
                } catch (IndexOutOfBoundsException e) {
                    throw new ListIndexOutOfBound();
                }
                System.out.println("Alright! It's done:");
                System.out.println(taskList.getItem(Integer.parseInt(parameter)).toString());
            }  
        },
        UNMARK {
            @Override
            public void run(String parameter, ItemList<Task> taskList) throws DukeExceptions {
                if (parameter.isBlank())
                    throw EmptyNumber.createEmptyNumber("Unmark");
                try {
                    taskList.getItem(Integer.parseInt(parameter)).setUndone();
                } catch (IndexOutOfBoundsException e) {
                    throw new ListIndexOutOfBound();
                }
                System.out.println("Alright! It's done:");
                System.out.println(taskList.getItem(Integer.parseInt(parameter)).toString());
            }
        },
        TODO {
            @Override
            public void run(String parameter, ItemList<Task> taskList) throws DukeExceptions {
                if (parameter.isBlank())
                    throw EmptyTask.createEmptyTask("todo");
                Task todo = new ToDo(parameter);
                taskList.addItem(todo);
                System.out.println("Alright! Added that to the list: ");
                System.out.println(todo.toString());
                taskList.printNoItems();
            }
        },
        DEADLINE {
            @Override
            public void run(String parameter, ItemList<Task> taskList) throws DukeExceptions {
                if (parameter.isBlank())
                    throw EmptyTask.createEmptyTask("dateline");
                int index = parameter.indexOf("/by");
                if (index < 0)
                    throw EmptyDate.createEmptyDate("dateline");
                String task = parameter.substring(0, index);
                if (task.isBlank())
                    throw EmptyTask.createEmptyTask("dateline");
                String date = parameter.substring(index + 3, parameter.length());
                if (date.isBlank())
                    throw EmptyDate.createEmptyDate("dateline");
                Task deadline = new Deadline(task, date);
                taskList.addItem(deadline);
                System.out.println("Alright! Added that to the list: ");
                System.out.println(deadline.toString());
                taskList.printNoItems();
            }
        },
        EVENT {
            @Override
            public void run(String parameter, ItemList<Task> taskList) throws DukeExceptions {
                // TODO Auto-generated method stub
                if (parameter.isBlank())
                    throw EmptyTask.createEmptyTask("event");
                int index = parameter.indexOf("/at");
                if (index < 0)
                    throw EmptyDate.createEmptyDate("event");
                String task = parameter.substring(0, index);
                if (task.isBlank())
                    throw EmptyTask.createEmptyTask("event");
                String date = parameter.substring(index + 3, parameter.length());
                if (date.isBlank())
                    throw EmptyDate.createEmptyDate("event");
                Task event = new Event(task, date);
                taskList.addItem(event);
                System.out.println("Alright! Added that to the list: ");
                System.out.println(event.toString());
                taskList.printNoItems();
            }  
        },
        DELETE {
            @Override
            public void run(String parameter, ItemList<Task> taskList) throws DukeExceptions {
                if (parameter.isBlank())
                    throw EmptyNumber.createEmptyNumber("Delete");
                try {
                    Task deletedTask = taskList.getItem(Integer.parseInt(parameter));
                    taskList.deleteFromIndex(Integer.parseInt(parameter));
                    System.out.println("Alright, I've deleted this from the list:");
                    System.out.println(deletedTask.toString());
                    taskList.printNoItems();
                } catch (IndexOutOfBoundsException e) {
                    throw new ListIndexOutOfBound();
                }
                
            }
        };

        public abstract void run(String parameter, ItemList<Task> taskList) throws DukeExceptions;
    }

    abstract protected void run() throws DukeExceptions;

    static void runCommand(String input, ItemList<Task> taskList) throws DukeExceptions {
        String command;
        String parameter = "";
        String[] splited = input.split("\\s+");
        if (splited.length < 2) {
            command = input;
        } else {
            command = splited[0];
            parameter = input.substring(command.length() + 1, input.length());
        }
        try {
            CommandName.valueOf(command.toUpperCase()).run(parameter, taskList);
        } catch (IllegalArgumentException e) {
            throw InvalidCommand.createInvalidCommand(command);
        }
        
    }
}