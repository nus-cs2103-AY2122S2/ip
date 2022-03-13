# Tesseract User Guide

## Features 

### Feature - Add Task

Add a new task into the todo list. 
Currently three commands are available:
1. `todo` Add a todo task.
2. `deadline` Add a deadline due on a user-set date.
3. `event` Add an event that happens on a user-set date.

### Feature - Manage Tasks

Act on existing tasks.
1. `mark` Mark a task as done.
2. `unmark` Mark a task as undone.
3. `delete` Delete a task.
4. `archive` Archive a task so that it is still in the memory but will not be shown.

### Feature - Find Tasks

Find tasks according to user's needs.
1. `list` List all unarchived tasks on the todo list.
2. `filter` Display all tasks on a specific date.
3. `find` Display all tasks that include a specific keyword.

### Feature - Exit the system

`bye` Save all changes to memory and users are free to exit the system.

## Usage

### `todo` - Add a todo task

Add a todo task with description to the task list. 
Display success message upon successful addition.

Example of usage: 

`todo do homework`

Expected outcome:

```
This has been added to your schedule.
Wish you can finish it on time
    [T][ ]do homework
    Now you have 1 tasks waiting to be finished.
```

### `deadline` - Add a deadline task

Add a deadline task with description and due date to the task list. 
Display success message upon successful addition.
`/by` keyword is used to specified due date.
Date has be in YYYY-MM-DD format.

Example of usage: 

`deadline submit assignment /by 2022-01-23`

Expected outcome:

```
This has been added to your schedule.
Wish you can finish it on time
    [D][ ]submit assignment (by: Jan 23 2022)
    Now you have 2 tasks waiting to be finished.
```

### `event` - Add an event task

Add an event task with description and happening date to the task list. 
Display success message upon successful addition.
`/at` keyword is used to specified happening date.
Date has be in YYYY-MM-DD format.

Example of usage: 

`event performance /at 2022-03-23`

Expected outcome:

```
This has been added to your schedule.
Wish you can finish it on time
    [E][ ]performance (at: Mar 23 2022)
    Now you have 3 tasks waiting to be finished.
```

### `mark` - Mark a task as done

Mark the task at specific index as done.
Index must be positive integers 1, 2, 3...

Example of usage: 

`mark 1`

Expected outcome:

```
Wow you have finished a task! Excellent!
    [T][X]do homework
```

### `unmark` - Mark a task as undone

Mark the task at specific index as undone.
Index must be positive integers 1, 2, 3...

Example of usage: 

`unmark 1`

Expected outcome:

```
Seems like you have successfully undone your done task
    [T][ ]do homework
```

### `delete` - Delete a task

Delete a task at the specified index.
Index must be positive integer 1, 2, 3...
Display success message upon successful deletion.

Example of usage: 

`delete 1`

Expected outcome:

```
Okies the following task has been removed:
    [T][ ]do homework
    Now you have 2 tasks in the list~
```

### `archive` - Archive a task

Archive a task at the specified index.
Index must be positive integer 1, 2, 3... 
Display success message upon successful archive.

Example of usage: 

`archive 1`

Expected outcome:

```
The following task has been archived~
    [D][ ]submit assignment (by: Jan 23 2022)
```

### `list` - List all unarchived tasks

List all tasks that are not archived.

Example of usage: 

`list`

Expected outcome:

```
Look what I have noted down for you~
    1. [T][ ]CS2103 W6 Increment
    2. [D][ ]CS2103 Quiz (by: Feb 18 2022)
    3. [E][ ]performance (at: Mar 23 2022)
```

### `filter` - Filter tasks by date

Display all tasks on a specific date.
Date must be in the YYYY-MM-DD format.

Example of usage: 

`filter /by date 2022-02-18`

Expected outcome:

```
Here are the tasks filtered by 2022-02-18:
    1.[D][ ]2103 Quiz (by: Feb 18 2022)
```

### `find` - Find tasks by keyword

Find tasks containing a specific keyword.

Example of usage: 

`find Increment`

Expected outcome:

```
Here are the tasks filtered by Increment:
    1.[T][ ]CS2103 W6 Increment
```

### `bye` - Save all data

Save all changes into memory for future usage.

Example of usage: 

`bye`

Expected outcome:

```
Ok I'm gonna travel to another planet now
Hope to see you again when I'm back :P
```