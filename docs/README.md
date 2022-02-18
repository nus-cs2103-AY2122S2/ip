# Duke

Duke is a task management application that you can use to track all your daily tasks. It includes features such as adding different types of tasks and even a search function.

## Features 

### List Tasks

See all the tasks on your task list, and their completion status!

### Add Tasks

Add 3 types of Tasks, Todos, Events and Deadlines!

### Delete Tasks

Delete Tasks that have been completed, or unwanted!

### Mark/Unmark Tasks

Mark Tasks as done, or Unmark if you made a mistake!

### Show Today's Tasks

List all the tasks in your list due today!

### Find Tasks

Find tasks according to any given keyword!

## Usage

### `list` - Shows all tasks in Duke

Shows the full task List.

Example of usage: 

`list`

Expected outcome: 

Displays all tasks in your list, with the completion status and dates.

Example of outcome.

```
Duke:
Here are the tasks in your list:
1.[T][X] Mop floor
2.[T][ ] Clean table
3.[D][ ] Assignment 1 (by: 2022-02-15)
```

### `todo` - Adds a Todo task

Adds a Todo task to be completed

Example of usage:

`todo run 2.4`

Expected outcome: 

Adds the uncompleted Todo task.

Example of outcome:

```
Duke:
Got it. I've added this task:
[T][ ] run 2.4
Now you have 4 tasks in the list.
```

### `event` - Adds an Event task

Adds an Event task to be completed at a date given in LocalDate format.

Example of usage:

`event go to school /at 2022-03-12`

Expected outcome: 

Adds the uncompleted Event task.

Example of outcome:

```
Duke:
Got it. I've added this task:
[E][ ] go to school (at: 2022-03-12)
Now you have 5 tasks in the list.
```

### `deadline` - Adds a Deadline task

Adds a Deadline task to be completed by a date given in LocalDate format.

Example of usage:

`Deadline complete essay /by 2022-03-11`

Expected outcome: 

Adds the uncompleted Deadline task.

Example of outcome:

```
Duke:
Got it. I've added this task:
[D][ ] complete essay (by: 2022-03-11)
Now you have 6 tasks in the list.
```

### `mark` - Marks a task as completed

Marks a task that has been completed as done, specified by its index in the list.

Example of usage:

`mark 1`

Expected outcome: 

Marks the 1st task in the list as done.

Example of outcome:

```
Duke:
Nice! I've marked this task as done:
[T][X] complete essay (by: 2022-03-11)
```

### `unmark` - Marks a task as completed

Unmarks a task that has been marked, specified by its index in the list.

Example of usage:

`unmark 1`

Expected outcome: 

Unmarks the 1st task in the list as done.

Example of outcome:

```
Duke:
OK, I've marked this task as not done yet:
[T][ ] complete essay (by: 2022-03-11)
```

### `delete` - Removes a task from the list 

Delete a task, specified by its index in the list.

Example of usage:

`delete 4`

Expected outcome: 

Deletes the 4th task in the list.

Example of outcome:

```
Duke:
Noted. I've removed this task:
[T][ ] complete essay (by: 2022-03-11)
Now you have 3 tasks in the list.
```

### `find` -  Finds all tasks that matches a keyword

Finds all tasks that matches a given keyword.

Example of usage:

`find midterms`

Expected outcome: 

Finds all tasks that contain 'midterms' in their description.

Example of outcome:

```
Duke:
Here are the matching tasks in your list:
1.[T][ ] Cs2107 midterms (at: 2022-03-11)
2.[T][ ] Cs2108 midterms (at: 2022-03-15)
```

### `today` -  Finds all tasks are due or happen today

Finds all events or deadlines that are due or happen today.

Example of usage:

`today`

Expected outcome: 

Shows all tasks that are due or happen today.

Example of outcome:

```
Duke: Here are the tasks due today:
[E][ ] Family dinner (at: 2022-02-11)
[D][ ] Clear storeroom (by: 2022-02-11)
```

### `bye` -  Exits from Duke application

Displays a closing message, then closes the application.

Example of usage:

`bye`

Expected outcome: 

Displays a closing message, then closes the application.

Example of outcome:

```
Duke: Bye. Hope to see you again soon!
```
