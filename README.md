# Duke project

This is a chatbot with basic functionality to add, remove, update and delete tasks. It has a basic GUI written with JavaFX.


## Adding a Task
There are three types of Tasks: Todo, Deadline and Event.

To add a Todo: `todo <TASKNAME>`<br>
Example: `todo borrow book` adds the task `borrow book` to Duke.

To add a Deadline: `deadline <TASKNAME> /by <DEADLINE>`<br>
The format for `DEADLINE` is `YYYY-MM-DD HHMM`.<br>
Example: `deadline read book /by 2012-12-12 1530` adds a deadline to 
`read book` by `12th December 2012, 3:30PM`.

To add an Event: `event <TASKNAME> /at <STARTDATE> <ENDDATE>`<br>
The format for `STARTDATE` and `ENDDATE` is both `YYYY-MM-DD HHMM`.<br>
Example: `event house warming /at 2012-12-12 1530 2012-12-12 1700` adds an event
for `housewarming` which takes place from `12th December 2012, 3:30PM` to `12th December 2012, 5PM`.

## Removing a Task
To remove a task, type `delete <taskID>`.<br>
Example: `delete 3` deletes the task at index 3 if it exists.

## Viewing Tasks
To view your list of tasks, simply type `list`.

## Marking and Unmarking tasks
To mark a task as done, type `mark <taskID>`.    
To unmark a task as done, type `unmark <taskID>`.  
Example: `mark 3` or `unmark 3` either marks or unmarks the task at index 3 respectively.

## Finding tasks
To find a task that has a name containing the specific keyword, type `find <keyword>`.  
Example: `find library` returns all the tasks that contain the keyword `library`.

## Update task name
To update a task's name, type `update name <taskID> <NEWNAME>`.
Example: `update name 3 Borrow Book` updates the task name at index 3 to `Borrow Book`.

## Update deadline
To update a Deadline, type `update deadline <taskID> <NEWDEADLINE>`<br>
You only can use this command on tasks that are of `Deadline` type.<br>
The format for `NEWDEADLINE` is `YYYY-MM-DD HHMM`.<br>
Example: `update deadline 5 2012-12-12 0400` updates the deadline at index 5 to `12th December 2012, 4AM`.

## Update event time
To update an Event timing, type `update event time <taskID> <STARTTIMING> <ENDTIMING>`<br>
You only can use this command on tasks that are of `Event` type.<br>
The format for `STARTTIMING` and `ENDTIMING` is `YYYY-MM-DD HHMM`.<br>
Example: `update event time 4 2012-12-12 0400 2013-01-01 0600` updates the event at index 4
to start on `12th December 2012, 4AM` and end on `1st January 2013, 6AM`.
