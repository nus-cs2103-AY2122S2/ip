# User Guide
-- --

## Features 
-- --
### 1. Add tasks

XzzzBot adds your task to the list. It supports 3 task typpes: 
`todo`, `deadline`, and `event`.

### 2. Delete tasks

XzzzBot removes unwanted tasks for you from the list.

### 3. List tasks

See all the task you have added!

### 4. Mark and unmark tasks

Tasks are meant to be done. Once done, you can mark them as done! 
Or unmark them, if you wish.

### 5. Find tasks

Forgot where a task is? Search for it with keywords.

## Usage
-- --
### `todo {task name}` - Add a `todo` task

Adds a `todo` task to Xzzzbot's list.

Example of usage: 

`todo cs2103 publish project`

Expected outcome:

The `todo` task is added.

```
okie!! i have added:
[T][ ] cs2103 publish project
now there are 1 tasks in the list! get to work
```

### `deadline {task name} /by {date}` - Add a `deadline` task

Adds a `deadline` task to Xzzzbot's list.
A `deadline` task must come with a date string in `yyyy-mm-dd` format.

Example of usage:

`deadline homework /by 2022-02-20`

Expected outcome:

The `deadline` task is added.

```
okie!! i have added:
[D][ ] homework (by: Feb 20 2022)
now there are 2 tasks in the list! get to work
```

### `event {task name} /at {date}` - Add an `event` task

Adds an `event` task to Xzzzbot's list.
An `event` task must come with a date string in `yyyy-mm-dd` format.

Example of usage:

`event cs2103 lecture /at 2022-02-18`

Expected outcome:

The `event` task is added.

```
okie!! i have added:
[E][ ] cs2103 (at: Feb 18 2022)
now there are 3 tasks in the list! get to work
```

### `delete {index}` - Delete the task at list index

Deletes the task with the given `index` in the task list. 
`index` should be within the list length.

Example of usage:

`delete 1`

Expected outcome:

The task is now deleted.

```
OK!!! i have removed this task:
[T][ ] cs2103 publish project
now there are 1 tasks in the list! get to work
```

### `list` - View the list of tasks

View the list of tasks that have been added to Xzzzbot.

Example of usage:

`list`

Expected outcome:

The list of tasks is provided.

```
here are your tasks
1. [T][ ] cs2103 publish project
2. [D][ ] submission (by: Feb 25 2022)
```

### `mark {index}` - Marks the task at index as done

Marks the task at the given `index` as done.
`index` should be within the list length.

Example of usage:

`mark 1`

Expected outcome:

The task is marked as done.

```
yay!!! this task is now marked as done
[D][X] homework (by: Feb 20 2022)
```

### `unmark {index}` - Marks the task at index as undone

Marks the task at the given `index` as undone.
`index` should be within the list length.
This method is the reverse of the `mark` method.

Example of usage:

`unmark 1`

Expected outcome:

The task is marked as undone.

```
this task is now marked as not done yet... do it soon!
[D][ ] homework (by: Feb 20 2022)
```

### `find {keyword}` - Search for tasks that contains the keyword

Search for all tasks that contains the given `keyword` and return a list
of all filtered tasks.

Example of usage:

`find cs2103`

Expected outcome:

View a list of all the tasks containing the given keyword.

```
Here are the matching tasks in the list:
1. [T][ ] cs2103 homework
2. [T][X] cs2103 submission
```

### `bye` - Terminate the program

Terminates the program and exit.

Example of usage:

`bye`

Expected outcome:

Exits the program.

```
Cya later~
```