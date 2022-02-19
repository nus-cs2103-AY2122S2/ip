# Duke User Guide

## Features 

### List tasks

Keep track of various tasks in a task list

### Add tasks

Tasks can be added to the task list according to the task category (To-do, Event, Deadline)

### Delete tasks

Tasks can be deleted from the task list if added incorrectly

### Update tasks

Tasks can be updated from the task list to alter its description/date if its an event/deadline

### Find tasks

Tasks can be searched from the task list according to keyword provided

### Mark/unmark tasks

Tasks can be marked/unmarked from the task list once they are completed/uncompleted

## Usage

### `list` - list all tasks saved in task list

Returns a list of tasks the user has saved in the task list

Example of usage: 

`list`

Expected outcome:

Duke gets the list of tasks saved in ```Storage``` and returns the list

```
Here are the tasks in your list:
1.[T][] iP submission
2.[D][] Submit iP (by: Feb-21-2022 2359)
```

### `commands` - list all commands available on Duke

Returns a list of commands and how to use them

Example of usage:

`commands`

Expected outcome:

Duke gets the list of commands available and returns the list

```
Here are the commands as well as their usage:
----------------------------------------------
list: lists all tasks in task list
...
```

### `todo` - Adds a To-do task to the task list

User enters description for To-Do task to add into task list

Example of usage:

`todo iP submission`

Expected outcome:

Duke creates a To-Do task and adds it into the task list before returning how many tasks are inside the task list currently

```
Got it. I've added this task:
[T][] iP submission
Now you have 2 tasks in your list.
```

### `event` - Adds an Event task to the task list

User enters description for Event task as well as the date of the Event to add into task list

Example of usage:

`event Finished iP /at 22/2/2022 0000`

Expected outcome:

Duke creates an Event task and adds it into the task list before returning how many tasks are inside the task list currently

```
Got it. I've added this task:
[E][] Finished iP (at: Feb-22-2022 0000)
Now you have 2 tasks in your list.
```

### `deadline` - Adds a Deadline task to the task list

User enters description for Deadline task as well as the date of the Deadline to add into task list

Example of usage:

`deadline Submit iP /by 21/2/2022 2359`

Expected outcome:

Duke creates a Deadline task and adds it into the task list before returning how many tasks are inside the task list currently

```
Got it. I've added this task:
[D][] Submit iP (by: Feb-21-2022 2359)
Now you have 2 tasks in your list.
```

### `mark` - Marks a task as done

User enters index of task to be marked and Duke will mark the given task as done

Example of usage:

`mark 1`

Expected outcome:

Duke finds the task according to the index given in the list and marks it as done if it is currently not done. If the task is already done, Duke will inform the user that the specified task has already been completed

```
Nice! I've marked this task as done:
[D][X] Submit iP (by: Feb-21-2022 2359)
```

### `ummark` - Un-marks a task as done

User enters index of task to be un-marked and Duke will un-mark the given task as done

Example of usage:

`unmark 1`

Expected outcome:

Duke finds the task according to the index given in the list and un-marks it if it is currently done. If the task is not done, Duke will inform the user that the specified task has not been completed yet

```
Ok, I've marked this task as not done yet:
[D][] Submit iP (by: Feb-21-2022 2359)
```

### `delete` - delete specified task from task list

User enters index of task to be deleted and Duke will remove the task specified

Example of usage:

`delete 1`

Expected outcome:

Duke finds the task according to the index given in the list and deletes it before informing the user how many tasks are left in the list

```
Noted, I've removed this task:
[D][] Submit iP (by: Feb-21-2022 2359)
Now you have 2 tasks in your list.
```

### `find` - returns tasks that matches keyword saved in task list

Returns a list of tasks matching the keyword specified by the User

Example of usage:

`find iP`

Expected outcome:

Duke gets the list of tasks saved in ```Storage``` and returns the list matching the keyword provided. Take note that the keyword is case-sensitive so ```find iP``` and ```find ip``` will yield different results

```
Here are the matching tasks in your list:
1.[T][] iP submission
```

### `update` - update specified task from task list

User enters index of task to be updated as well as it's description and Duke will update the task specified with its new description

Example of usage:

`update 1 Submit iP`

Expected outcome:

Duke updates the specified task and updates the task list

```
Noted, I've updated this task:
1.[T][] submit iP
```

### `clear all` - removes all tasks saved in task list

Removes the list of tasks the user has saved in the task list

Example of usage:

`clear all`

Expected outcome:

Duke removes all the tasks stored in the list of tasks saved in ```Storage```

```
Noted. All tasks have been cleared!
```

### `bye` - exits the Duke program

Prints goodbye message and exits the program after a short buffer

Example of usage:

`bye`

Expected outcome:

Duke returns a goodbye message before closing the app

```
Bye. Hope to see you again soon!
```