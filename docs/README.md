# Duke User Guide

## Installation

To install duke application, download the jar file from this [link](https://github.com/Jacky142857/ip)

Make sure you have JDK 11 (Java) installed before proceeding. If not, proceed
[here](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html)
before carrying on.

## Features 

### Add tasks

Add one of the todo task, deadline task or event to the task list.

### List

List all the tasks in the taskList.

### Delete

Delete the task with index specified.

### Mark

Mark a task as done.

### Archive

Clear the taskList and store them somewhere else to be retrieved later.

### Unarchive

Retrieve the archived tasks.

### Find

Find all the tasks with description containing the keyword.

## Usage

### `todo` - Add a new todo task

It adds a new todo task to the task list.

Example of usage: 

`todo play basketball`

Expected outcome:

Duke adds a Todo task with the entered description.

```
Got it. I've added this task:
   [T][ ] play basketball
Now you have 1 tasks in the list.
```

### `deadline` - Add a new deadline task

Adds a new deadline task with a deadline. Put "/by" before the deadline.
For the deadline to be interpreted as a date, enter the deadline in the format of yyyy-mm-dd.

Example of usage:

`deadline finish assignment /by 2022-02-27`

Expected outcome:

Duke adds a Deadline task with description and deadline time.

```
Got it. I've added this task:
   [D][ ] finish assignment (by: Feb-27-2022)
Now you have 2 tasks in the list.
```

### `event` - Add a new event task

It adds a new event task with description and when the event happened.

Example of usage:

`event the day we first met /at 2021-12-20`

Expected outcome:

Duke adds an event with the description and when it occurred.

```
Got it. I've added this task:
   [E][ ] the day we first met (at: Dec-20-2021)
Now you have 3 tasks in the list.
```

### `list` - List down the status and description of each task

show all the tasks with their status in a list

Example of usage:

`list`

Expected outcome:

a list of tasks shown
```
Here are the tasks in your list:
1. [T][ ] play basketball
2. [D][ ] finish assignment (by: Feb-27-2022)
3. [E][ ] the day we first met (at: Dec-20-2021)
```

### `delete` - Delete a task

Delete the task with a certain index.

Example of usage:

`delete 1`

Expected outcome: 

The first task in the list will be deleted
```
Noted. I've removed this task:
[T][ ] play basketball
Now you have 2 tasks in the list
```


### `mark` - Mark a task as done

mark the task of with the given index as done. The first task in the list has an index of 1.

Example of usage:

`mark 1`

Expected outcome: 

The second task in the task list will have its status updated to done.

```
Nice! I've marked this task as done:
[D][X] finish assignment (by: Feb-27-2022)
```

### `find` - Search for one or more tasks with the keyword

Find all tasks with description that contain the keyword

Example of usage:

`find basketball`

Expected outcome: 

All the event with description which contains basketball will be displayed.

```
Here are the matching tasks in your list:
1.[T][ ] play basketball
```

### `archive` - Archive all the tasks

Archive all the tasks to another file and clear the current tasklist.

Example of usage:

`archive`

Expected outcome:

The current list will be cleared and tasks will be stored to another file.

```
your tasks have been archived, type 'unarchive to retrieve the archived list
```

### `unarchive` - Unarchive all the tasks

Retrieve all the tasks that are being archived.

Example of usage:

`unarchive`

Expected outcome:

The list of tasks that are archived will be appended to the current working list.

```
you have unarchived your task, now they are in your task list !
```

### `bye` - Exit the app
Example of usage:

`bye`

Expected outcome:

The app closes.