# User Guide

***

## About Calcifer

![](../src/main/resources/images/DaDuke.png)

<p><b>Calcifer is a task manager</b> to aid a user in keeping track of a list of tasks that have been completed 
or need to be completed.</p>
<p><b>Calcifer</b> operates mainly using CLI user commands, but can be started up using a GUI application 
to provide a more user-friendly design and interface.</p>

***

## Features

<p>The following list summarises the features included in <b>Calcifer</b>.</p>

- _**Add** new tasks_   
- _**Delete** existing tasks_
- _**List** all existing tasks_
- _**Mark/Unmark** an existing task_
- _**Find** tasks_

### Feature: Add

**Add**  more tasks to the existing task list.
There are three task types that can be added: `Event`, `Deadline`, `ToDo`.

### Feature: Delete

**Delete** existing tasks from the existing task list.
Any existing task can be deleted based on the task ID.

### Feature: List

**Lists** all existing tasks from the task list.

### Feature: Mark/Unmark

**Mark/Unmark** existing tasks in the task list as done/undone respectively.
Any existing task can be marked or unmarked based on the task ID.

### Feature: Find

**Finds** all existing tasks that include the specified search keyword.


***

## Usage

### `hi` - Displays welcome message

Displays a standard welcome message.

Example of usage:

`hi`

Expected outcome:

```
Hello I'm Calcifer.
What can I help you with today?
```


### `by` - Displays exit message

Displays a standard exit message.

Example of usage:

`bye`

Expected outcome:

```
Bye. See you later!
```

### `todo [todo_description]` - Adds a task of type ToDo

Adds a new `ToDo` task with the specified task description to the existing task list.
The modified task list gets written and saved back to the `taskHistory.txt` file.

Example of usage: 

`todo clean the house`

Expected outcome:

A new task of type `ToDo` will be added to the existing task list with the specified 
task description. The new `ToDo` task added will be displayed.

```
Got it. I've added this task:
[T][] clean the house
Now you have 1 tasks in the list.
```

### `event [event_description] /at [event_date DD-MM-YYYY]` - Adds a task of type Event

Adds a new `Event` task with the specified task description and date to the existing task list.
The modified task list gets written and saved back to the `taskHistory.txt` file.

Example of usage:

`event go to movie /at 21-02-2022`

Expected outcome:

A new task of type `Event` will be added to the existing task list with the 
specified task description and date. The new `Event` task added will be displayed.

```
Got it. I've added this task:
[E][] go to movie (at: Feb 21 2022)
Now you have 2 tasks in the list.
```

### `deadline [deadline_description] /by [deadline_date  DD-MM-YYYY]` - Adds a task of type Deadline

Adds a new `Deadline` task with the specified task description and date to the existing task list.
The modified task list gets written and saved back to the `taskHistory.txt` file.

Example of usage:

`deadline finish homework /by 20-02-2022`

Expected outcome:

A new task of type `Deadline` will be added to the existing task list with the
specified task description and date. The new `Deadline` task added will be displayed.

```
Got it. I've added this task:
[D][] finish homework (by: Feb 20 2022)
Now you have 3 tasks in the list.
```

### `delete [taskId]` - Delete task of specified taskId

Deletes an existing task at index `taskId - 1` from the existing task list.
The task is also deleted from the `taskHistory.txt` file.
The modified task list gets written and saved back to the `taskHistory.txt` file.

Example of usage:

`delete 3`

Expected outcome:

3rd task in the task list that is deleted will be displayed.

```
Noted. I've removed this task:
[D][] do homework (by: Feb 20 2022)
Now you have 2 tasks in the list.
```

### `list` - List all the existing tasks in the task list

Lists out all the existing tasks.
No modification is made to `taskHistory.txt` file.

Example of usage:

`list`

Expected outcome:

All existing tasks will be returned and displayed.

```
Here are the tasks in your list:
1. [T][] clean the house
2. [E][] go to movie (at: Feb 21 2022)
```

### `mark [taskId]` - Mark the task with taskId as done

Marks the task at index `taskId -1` as done (i.e. `[]` --> `[X]`).
This change is written to and saved in the `txtHistory.txt` file.

Example of usage:

`mark 2`

Expected outcome:

The task that has been marked as done is returned and displayed.

```
Nice! I've marked this task as done:
[E][X] go to movie (at: Feb 21 2022)
```


### `unmark [taskId]` - Unmark the task with taskId as undone

Unmarks the task at index `taskId -1` as undone (i.e. `[X] --> []`).
This change is written to and saved in the `txtHistory.txt` file.

Example of usage:

`unmark 2`

Expected outcome:

The task that has been marked as undone is returned and displayed.

```
OK, I've marked this task as not done yet:
[E][] go to movie (at: Feb 21 2022)
```

### `find [keyword(s)]` - Find tasks that contain the specified keyword

Finds the tasks that contain the specified keyword(s) in the returned String
from their `toString()` method.
The list of tasks found is returned and displayed.
No modification is made to `taskHistory.txt` file.

Example of usage:

`find at: Feb 21`

Expected outcome:

The task that has been marked as undone is returned and displayed.

**NOTE:** The index in the returned list may not be the task ID for each of the listed tasks.
Use `List` command to display full list to reference task IDs.


```
Here are the matching tasks in your list:
1. [E][] go to movie (at: Feb 21 2022)
```

***

## Image References

Images used in this application are from the following external links:

- [Calcifer Image](https://www.pngitem.com/middle/hbhmwhh_calcifer-hd-png-download/)
- [User Image](https://ghibli.fandom.com/wiki/Markl)
