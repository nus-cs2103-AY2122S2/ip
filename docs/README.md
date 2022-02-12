# Duke project

This is a chatbot with basic functionality to add, remove, update and delete tasks. It has a basic GUI written with JavaFX.


## Adding a Task
There are three types of Tasks: Todo, Deadline and Event.  
To add a Todo: `todo <taskname>`.    
To add a Deadline: `deadline <taskname> /by YYYY/MM/DD HHSS`.    
To add an Event: `event <taskname> /at <timing (no specific format)>`.  

## Removing a Task
To remove a task, type `delete <taskID>`.

## Viewing Tasks
To view your list of tasks, simply type `list`.

## Marking and Unmarking tasks
To mark a task as done, type `mark <taskID>`.    
To unmark a task as done, type `unmark <taskID>`.  

## Finding tasks
To find a task that has a name containing the specific keyword, type `find <keyword>`.  

## Update tasks
To update a task's name, type `update name <taskID> <newname>`.  
To update a Deadline/Event time,  type `update time <taskID> <newtime>`. (Ensure `<newtime>` follows the respective formatting).


