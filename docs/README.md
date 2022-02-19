# Duke User Guide
Duke is a chatbot that keeps track of all your tasks for you so that 
you won't forget ever again!

## Download
1. Ensure that you have java 11 installed on your computer
2. If you are a mac user, ensure that your Java Launcher has full disk access. you can follow the guide
[here](https://discussions.apple.com/thread/252709578).
3. Download the jar file to a directory that you want to use as Dukes home
directory.

## Features

### Viewing help : `help`

Get a list of all possible commands.

Format: `help`

### Viewing TaskList : `list`

lists out all tasks in the TaskList.

Format: `list`

### Search for a task : `find`

lists out all the tasks in the TaskList that contains keywords.

Format: `find MATCH`

Example: 
* `find assignment`
* `find g`

### Add a ToDo task : `todo`

Adds a new todo task to the TaskList.

Format: `todo TASKNAME`

Example:
* `todo buy groceries`
* `todo homework`

### Add a Deadline task : `deadline`
Adds a new deadline task to the TaskList.

Format: `deadline TASKNAME /by DATE`
* DATE is in the form YYYY-MM-DD

Example:
* `deadline assignment 1 /by 2022-09-18`
* `deadline project paper /by 2022-08-14`


### Add an Event task : `event`

Adds a new event task to the TaskList.

Format: event TASKNAME /at DATE
* DATE is in the form YYYY-MM-DD

Example:
* `event lunch /at 2022-01-03`
* `event birthday /at 2022-08-09`

### Mark a task : `mark`
Marks a task as completed.

Format: `mark INDEX`
* INDEX must be an integer.

Example:
* `mark 1`
* `mark 12`

### Unmark a task : `unmark`
Remove mark from a task.

Format: `unmark INDEX`
* INDEX must be an integer.

Example: 
* `unmark 1`
* `unmark 17`

### Delete a task : `delete`
deletes a task from the TaskList.

Format: `delete INDEX`

Example:
* `delete 1`
* `delete 14`

### Close the application : `bye`
Shuts down the application.

Format: `bye`

## Command Summary

|  Command   | Format                       | Examples                               |
|:----------:|------------------------------|----------------------------------------|
|   `help`   | `help`                       | `help`                                 |
|   `list`   | `list`                       | `list`                                 |
|   `find`   | `find MATCH`                 | `find assignment `                     |
|   `todo`   | `todo TASKNAME`              | `todo buy groceries`                   |
| `deadline` | `deadline TASKNAME /by DATE` | `deadline assignment 1 /by 2022-01-01` |
|  `event`   | `event TASKNAME /at DATE`    | `event birthday /at 2022-08-09`        |
|   `mark`   | `mark INDEX`                 | `mark 1`                               |
|  `unmark`  | `unmark INDEX`               | `unmark 12`                            |
|  `delete`  | `delete INDEX`               | `delete 3`                             |
|   `bye`    | `bye`                        | `bye`                                  |

