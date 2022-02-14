# User Guide

---

*Stevie* is a task manager and scheduler coupled with an interactive chatbot interface to help you improve
productivity! The desktop application is packaged with a Graphical User Interface (GUI) that resembles
a classic messenger application. 

## Features

---
### Task Management

*Stevie* keeps track of all of your tasks! Simple **Add**, **Remove**, **Mark** or **Unmark** 
your tasks. 

### Storage

Stevie stores your tasks with a simple `.txt` file. You do not have to worry about excessive memory usage
as you add on more tasks!

### Undo

Do not worry about making mistakes! With the **Undo** feature, you can revert your command with 4 simple
letters!

### Finding your tasks

Having a long list of task? Stevie can help you find your tasks with a simple keyword search. Just type
in a word in the name of the task and let *Stevie* find it for you!

## Usage

### `bye` - Exiting the program

Bid *Stevie* farewell and close the application.

Example of usage: 

`bye`

Expected outcome:

*Stevie* displays a farewell message and exits in 3 seconds.

```
Bye! Hope to see you again!
Exiting in 3 seconds...
```

### `list` - Displaying a list of your tasks

Shows all the tasks that you have added thus far.

Example of usage:

`list`

Expected outcome:

*Stevie* displays a list of tasks that you have in your task list.
Tasks can be marked as done or not done.

```
You have the following upcoming tasks:
1.[E][ ] team project meeting (at 18/02/2022 18:00)
2.[D][X] team project submission (by 22/02/2022 23:59)
```

### `help` - Display a list of valid commands

Shows all of the valid user commands.

Example of usage:

`help`

Expected outcome:

*Stevie* displays a list of commands that can be executed by the user.

```
-"list": to display your activities.
-"bye": to end our session.
-"mark <i>" to mark the i-th task as done.
-"unmark <i>" to unmark the i-th task as done.
-"delete <i>" to delete the i-th task.
-"todo <task_name>" to add a todo task.
-"deadline <task_name> /by <date>" to add a deadline.
-\"event <event_name> /at <date>" to add an event.
-"undo" to undo the previous command.
Date should in format of dd/mm/yyyy HH:mm
```

### `find` - Displaying all tasks that contain keyword

Shows all the tasks that matches with keyword

`find [keyword]`

`[keyword]` is a case-sensitive string of characters that you expect to have in your tasks' name.

Example of usage:

`find meeting`

Expected outcome:

*Stevie* displays a list of tasks that you have in your task list that matches with the keyword.

```
1.[E][ ] team project meeting (at 18/02/2022 18:00)
```

### `mark` / `unmark` - Marks a task as completed or not

*Stevie* marks a particular task as completed or not completed.
`mark` sets the task as completed, while `unmark` marks the task as incomplete.

`mark [num]`

`unmark [num]`

`[num]` is an integer that represents the 1-based index of the task in the task list.

Example of usage:

`mark 1`

`unmark 2`

Expected outcome:

*Stevie* marks/unmarks the task in the task list.

```
1.[E][X] team project meeting (at 18/02/2022 18:00)
2.[D][X] team project submission (by 22/02/2022 23:59)

1.[E][X] team project meeting (at 18/02/2022 18:00)
2.[D][ ] team project submission (by 22/02/2022 23:59)
```

### `todo`/`deadline`/`event` - Adds a task to the task list
*Stevie* creates and adds the task to the task list based on the type of task specified by the command.

`todo [task_name]`

`deadline [task_name] /by [date]`

`event [task_name] /at [date]`

`[task_name]` is a string of characters that represents the name of the task.

`[date]` is a string of characters that represent the date of the task. Date is written in the format
of **dd/mm/yyyy HH:mm**.

Example of usage:

`todo homework`

`event career fair /at 21/02/2022 12:00`

Expected outcome:

*Stevie* adds the tasks to the task list.

```
Got it! I have added a new task:
[T][ ] homework

You have 3 tasks in your list.

Got it! I have added a new task:
[E][ ] career fair (at: 21/02/2022 12:00)

You have 4 tasks in your list.
```

### `delete` - Deletes a task from the task list
*Stevie* removes a task from the task list.

`delete [num]`

`[num]` is an integer that represents the 1-based index of the task in the task list.

Example of usage:

`delete 1`

Expected outcome:

*Stevie* deletes the task in the task list.

```
I have deleted the following task:
1.[E][X] team project meeting (at 18/02/2022 18:00)

You have 3 tasks in your list.
```

### `undo` - Undo previous command
*Stevie* undoes the previous command that altered the task list.
Commands with `undo` support are `delete`, `mark`, `unmark`, `todo`, `deadline`, `event`.

Example of usage:

`undo`

Expected outcome:

*Stevie* undoes the previous command.

```
Undo previous delete command!
```