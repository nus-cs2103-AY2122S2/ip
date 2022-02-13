# User Guide
Charizard is a **desktop app for managing tasks** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you are a _Pokemon Fan_ who is keen on letting _Charizard_ burn away your tasks for you, ***this is the app*** for you!

## Summary of feature commands
If you would to have a quick summary of feature usage without diving into the detailed usages, the table below serves as a quick guide.

Action | Format, Examples
--------|------------------
**Add Todo** | `todo <name of todo> [/p PRIORITY]` <br> e.g., `todo Apply for internship`
**Add Deadline** | `deadline <name of deadline> /by <date> [/p PRIORITY]` <br> e.g., `deadline CS3243 Project 1 /by 2022/02/18 2359 /p medium`
**Add Event** | `event <name of event> /at <date> [/p PRIORITY]` <br> e.g., `event CS2103T Meeting /at 2022/02/15 2100 /p low`
**Delete** | `delete INDEX`<br> e.g., `delete 1 `
**Mark** | `mark INDEX`<br> e.g., `mark 1`
**Unmark** | `unmark INDEX`<br> e.g., `unmark 1`
**List** | `list`
**Sort** | `sort`
**Change Priority** | `priority INDEX PRIORITY` <br> e.g., `priority 1 high`
**Find** | `find KEYWORD`<br> e.g., `find CS2103T`


## Features 
1. _Add tasks_ such as `todo` `deadline` or `event` tasks to Charizard's burning list.
2. _Mark tasks_ that are ready for Charizard to burnt.
3. _Unmark tasks_ to signify Charizard that the task was not burnt completely.
4. Assign _priority_ to tasks and _sort_ tasks based on priority.
5. _Customized response images_ by Charizard based on the type of command.

## Usage

### Adding a Task : `todo` `deadline` and `event`

Adds a new Task to Charizard's burning wish list. A Task can be a `todo`, `deadline`, or `event`

Format: 
* `todo <name of todo> [/p PRIORITY]`
* `deadline <name of deadline> /by <date> [/p PRIORITY]`
* `event <name of event> /at <date> [/p PRIORITY]`
* `PRIORITY` must be one of `HIGH` `LOW` or `MEDIUM` and is case-insensitive.
* If no priority is specified, the default priority is `LOW`

:bulb: **Note:** Date must be in `yyyy/MM/dd HHmm` format

Example:
* `todo Apply for internship`
* `deadline CS3243 Project 1 /by 2022/02/18 2359 /p medium`
* `event CS2103T Meeting /at 2022/02/15 2100 /p high`

Expected outcome:
* Charizard adds a new `todo` task with name _Apply for internship_ with _LOW_ priority.
* Charizard adds a new `deadline` task with name _CS3243 Project 1_ with deadline set at _February 18 2022 2359hrs_ and _MEDIUM_ priority .
* Charizard adds a new `event` task with name _CS2103T Project Meeting_ at _February 15 2022 2100hrs_ with _HIGH_ priority.
* If the input is in an invalid format, Charizard will respond to you with the format he expects.

### Deleting a Task : `delete`
Deletes the specified Task from Charizard's burning wish list.

Format: `delete INDEX`
* Deletes the task at the specified `INDEX`.
* The index refers to the number shown in Charizard's burning wish list. If you forget the index of a task, try using `list` command to list down all tasks and their index.
* The index **must be a positive integer** and smaller or equal to the number of tasks in Charizard's burning wish list.

Examples:
* `delete 1`

Expected outcome:
* If Charizard's burning wish list has at least 1 task, the 1st task will be deleted.
* If Charizard's burning wish list has no task, or the input is invalid, Charizard will respond to you with the format he expects.

### Finding a Task : `find`
Finds a task whose name contains a substring of the given input.

Format: `find KEYWORD`
* The search will search for any task name that contains `KEYWORD` as substring. e.g. `find CS` will match task `CS2103T` and task `CS3243`
* The search is case-sensitive. e.g. `find cs` will not match task `CS2103T`

Examples:
* `find CS2103T`

Expected outcome:
* Charizard will display a list of all tasks with names matching `CS2103T`.
* If no tasks match `CS21O3T`, Charizard displays an empty list of tasks.

### Listing all Tasks : `list`
Shows a list of all tasks in Charizard's burning wish list.

Format: `list`

Expected outcome:
* Charizard will display all tasks in its burning wish list.

### Marking and Unmarking a Task : `mark` and `unmark`
Marks a task in Charizard's burning wish list that is ready to be burnt by Charizard. Charizard will happily burnt the task.

Alternatively, unmark a task that was previously marked. Charizard will be shocked that the task was not burnt completely.

Format: `mark INDEX` or `unmark INDEX`
* Marks or unmarks the task at the specified `INDEX`.
* The index refers to the number shown in Charizard's burning wish list. If you forget the index of a task, try using `list` command to list down all tasks and their index.
* The index **must be a positive integer** and smaller or equal to the number of tasks in Charizard's burning list.

Example:
* `mark 1`
* `unmark 1`

Expected outcome:
* If Charizard's burning wish list has at least 1 task, the 1st task will be marked with **[X]**
* If Charizard's burning wish list has at least 1 task, the 1st task will be unmarked with **[]**. If the 1st task is originally not marked, nothing happens.
* If Charizard's burning wish list has less than 1 task, Charizard will tell you that the index is invalid.
* If `INDEX` is not an integer, Charizard will respond to you with the format he expects.

### Sorting tasks based on priority : `sort`
Sorts tasks in Charizard's burning wish list based on priority of tasks, in the order of HIGH->MEDIUM->LOW priority.

Format: `sort`

:bulb: **Note** After you sort the list, take note that the index of the same task may be changed the next time you use `mark` `unmark` or `delete` command.

Expected outcome:
* Charizard will sort all tasks in its burning wish list from highest to lowest priority and displays all of them.

### Change priority of a Task : `priority`
Changes the priority of a task in Charizard's burning wish list.

Format: `priority INDEX PRIORITY`
* Changes priority of the task at the specified `INDEX`.
* The index refers to the number shown in Charizard's burning wish list. If you forget the index of a task, try using `list` command to list down all tasks and their index.
* The index **must be a positive integer** and smaller or equal to the number of tasks in Charizard's burning list.
* `PRIORITY` must be `HIGH` `MEDIUM` or `LOW` and is case-insensitive.

Example:
* `priority 1 HIGH`

Expected outcome:
* If Charizard's burning wish list has at least 1 task, the 1st task will have its priority changed to `HIGH`
* If Charizard's burning wish list has less than 1 task, Charizard will tell you that the index is invalid.
* If `INDEX` is not an integer, or `PRIORITY` is not a valid priority, Charizard will respond to you with the format he expects.

### Exiting and Saving : `bye`
Disables the input field and send button and exits the program after 3 seconds. Saves the data into `data/tasks.txt` before exiting the program.

:exclamation: **Caution:** If you close the program or window without `bye` command, your changes will not be saved.

Format: `bye`

Expected outcome:
* Charizard will roar at you and invite you to burn more tasks next time.
* Charizard will inform you that the app will be closing in 3 seconds, and disable the input and send button.




