# User Guide

<div align="center">
    <img src="https://raw.githubusercontent.com/domlimm/ip/master/docs/Ui.png" />
</div>

Abby is a <b>desktop application that serves as a task manager for managing tasks via a Command Line Interface</b>
(CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, Abby can get your tasks managed much more efficiently and quick as compared to
traditional GUI applications.

## Features
| Feature | Description                                                   |
|---------|---------------------------------------------------------------|
| Add     | Add a new Task. Types available: `Todo`, `Deadline`, `Event`  |
| Delete  | Delete a specified Task via index                             |
| List    | List all tasks available                                      |
| Mark    | Mark a Task as completed                                      |
| Unmark  | Mark a Task as not completed                                  |
| Search  | Search for tasks that matches a specified date                |
| Find    | Search for all tasks that matches a specified keyword         |

## Usage

### `todo` - Adding a Todo Task

Adds a new Todo Task to your Task List.

Example of usage: 

`todo [TASK_DESCRIPTION]`

Expected outcome:

A new Todo task will be added to your Task List.

```
Got it. I've added this task:
  [T][] Test Todo
Now you have 1 task in the list.
```

<br>

### `deadline` - Adding a Task with a specified Deadline

Adds a new Task with a deadline to your Task List.

Example of usage:

`deadline [TASK_DESCRIPTION] /by yyyy-mm-dd`

Expected outcome:

A new task with specified deadline will be added to your Task List.

```
Got it. I've added this task:
  [D][] Test Deadline (by: Feb 12 2022)
Now you have 2 tasks in the list.
```

<br>

### `event` - Adding a Task with a specified date of event

Adds a new Task with a date of event to your Task List.

Example of usage:

`event [TASK_DESCRIPTION] /at yyyy-mm-dd`

Expected outcome:

A new task with specified date of event will be added to your Task List.

```
Got it. I've added this task:
  [E][] Test Event (at: Feb 12 2022)
Now you have 3 tasks in the list.
```

<br>

### `delete` - Removes a task

Removes a new Task from your Task List.

Example of usage:

`delete [INDEX]`

Expected outcome:

Task specified to be removed from your Task List.

```
Noted. I've removed this task:
  [E][] Test Event (at: Feb 12 2022)
Now you have 2 tasks in the list.
```

<br>

### `list` - Lists all tasks

Lists all tasks in your task list.

Example of usage:

`list`

Expected outcome:

All tasks will be displayed to you.

```
Here are the tasks in your list:
1. [T][] Test Todo
2. [D][] Test Deadline (by: Feb 12 2022)
```

<br>

### `mark` - Marks a task

Marks a specified task based on index as completed.

Example of usage:

`mark [INDEX]`

Expected outcome:

Task will be marked as completed.

```
Nice! I've marked this task as done:
[T][X] Test Todo
```

<br>

### `unmark` - Unmarks a task

Unmarks a specified task based on index as not done.

Example of usage:

`unmark [INDEX]`

Expected outcome:

Task will be marked as not done.

```
OK, I've marked this task as not done yet:
[T][] Test Todo
```

<br>

### `search` - Searches for tasks (Date)

Searches for tasks based on date. Either `Deadline` or `Event` task.

Example of usage:

`search [yyyy-mm-dd]`

Expected outcome:

Tasks found will be shown to user.

```
Here are the tasks with date, Feb 12 2022, in your list:
1. [D][] Test Deadline (by: Feb 12 2022)
```

<br>

### `find` - Looks for tasks (Keyword)

Looks for tasks based on keyword/s.

Example of usage:

`find [KEYWORDS]`

Expected outcome:

Tasks found will be shown to user.

```
Here are the matching tasks in your list:
1. [T][] Test Todo
2. [D][] Test Deadline (by: Feb 12 2022)
```

<br>
