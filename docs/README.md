# DDXChatBot User Guide

## Features 

### Feature - Add Task

You could add a task that you plan to do.
A **Task** consists of three different types
- Todo
- Deadline
- Event

You could add any of them with detailed description and date to complete.

### Feature - Manage the tasks

The Tasks could be managed in different ways
- delete : you could delete an existing task
- list : you could list out all your added tasks
- mark : you could mark one task as done
- unmark : you could unmark one task as not done yet
- find : you could search your task by keyword

### Feature - Tags

You can also give your tasks different tags, to further categorize them in different groups.

### Feature - storage

Your tasks will be stored and processed in a file. This way, you would not lose your record.


## Usage

### `todo` - Add a todo

Add a todo task 

Example of usage: 

`todo (your description here) #(your tag here)`

Expected outcome:

Your todo Task will be added and get prompted existing number of tasks.

```
Got it. I've added this task:
	[T][ ] #{your tag} description
Now you have 1 tasks in the list.
```

### `deadline` - Add a deadline

Add a deadline task

Example of usage:

`deadline (your description here) /by (deadline in dd/mm/yyyy hhmm format) #(your tag here)`

Expected outcome:

Your deadline Task will be added and get prompted existing number of tasks.

```
Got it. I've added this task:
	[D][ ] #{your tag} (by Mon dd yyyy hh:mm) description
Now you have 1 tasks in the list.
```

### `event` - Add an event

Add an event task

Example of usage:

`event (your description here) /at (deadline in dd/mm/yyyy hhmm format) #(your tag here)`

Expected outcome:

Your event Task will be added and get prompted existing number of tasks.

```
Got it. I've added this task:
	[E][ ] #{your tag} (at Mon dd yyyy hh:mm) description
Now you have 1 tasks in the list.
```

### `mark` - Mark a task

Mark a task as done

Example of usage:

`mark (index in integer)`

Expected outcome:

A message show you have succeeded.
```
Nice! I've marked this task as done:
[T][X] #{tag} description
```

### `unmark` - Unmark a task

Unmark a task as not done yet

Example of usage:

`unmark (index in integer)`

Expected outcome:

A message show you have succeeded.

```
Okay, I've marked this task as not done yet:
[T][] #{tag} description
```

### `delete` - Delete a task

Delete a task and remove from list

Example of usage:

`delete (index in integer)`

Expected outcome:

A message show you have succeeded and prompt you number of existing tasks.

```
Okay, I've removed this task:
    [T][] #{tag} description
Now you have 0 tasks in the list.
```

### `list` - List all tasks

Display all the tasks.

Example of usage:

`list`

Expected outcome:

A message shows all tasks line by line.

```
Here are the tasks in your list:
1. [T][] #{tag} description
2. [E][] #{your tag} (at Mon dd yyyy hh:mm) description
3. [D][] #{your tag} (by Mon dd yyyy hh:mm) description
```

### `find` - Filter tasks by keyword

Display all the tasks that contains certain string.

Example of usage:

`find (your keyword string)`

Expected outcome:

A message shows all tasks line by line.

```
Here are the matching tasks in your list:
1. [T][] #{tag} description
```


