# User Guide

## Features

### Task management

Manage your tasks with useful features:

- Add and delete important tasks.
- Mark tasks to indicate completion.
- Readily find tasks using keywords.

### Command-Line Interface

Blazing fast command-line interface for professional users.

### Graphical-User Interface

Friendly and intuitive graphical-user interface for new users.

## Usage

### `list` - Listing all stored tasks

Shows a list of all stored tasks.

Example of usage:

`list`

Expected outcome:

```
YOUR TASKS:
1. [T][ ] buy textbooks
2. [D][ ] finish CS homework (by: 23:59, Feb 22 2022)
3. [E][ ] attend presentation meeting (at: 10:00, Feb 24 2022)
4. [T][ ] buy gift for friend
```

### `todo` - Adding a todo task

Adds a todo task to the list of tasks.

Example of usage:

`todo call friend`

Expected outcome:

```
TASK ADDED:
[T][ ] call friend
5 TASK(S) NOW.
```

### `deadline` - Adding a deadline task

Adds a deadline task to the list of tasks.

Example of usage:

`deadline finish resume /by 28-02-2022 23:59`

Expected outcome:

```
TASK ADDED:
[D][ ] finish resume (by: 23:59, Feb 28 2022)
5 TASK(S) NOW.
```

### `event` - Adding an event task

Adds an event task to the list of tasks.

Example of usage:

`event birthday party /at 22-02-2022 16:00`

Expected outcome:

```
TASK ADDED:
[E][ ] birthday party (at: 16:00, Feb 22 2022)
5 TASK(S) NOW.
```

### `mark` - Marking a task

Marks a task from the list of tasks as complete.

Example of usage:

`mark 2`

Expected outcome:

```
TASK DONE:
[D][X] finish CS homework (by: 23:59, Feb 22 2022)
```

### `unmark` - Unmarking a task

Unmarks a task from the list of tasks as incomplete.

Example of usage:

`unmark 2`

Expected outcome:

```
TASK DONE:
[D][ ] finish CS homework (by: 23:59, Feb 22 2022)
```

### `undo` - Undoing the last command

Undoes the last command thereby reverting the list of tasks to the latest previous state.

Example of usage:

`undo`

Expected outcome:

```
LAST TASK UNDONE. CURRENT TASKS:
1. [T][ ] buy textbooks
2. [D][X] finish CS homework (by: 23:59, Feb 22 2022)
3. [E][ ] attend presentation meeting (at: 10:00, Feb 24 2022)
4. [T][ ] buy gift for friend
```

### `find` - Finding specific tasks

Finds a number of tasks that contain the input keywords.

Example of usage:

`find buy, homework`

Expected outcome:

```
MATCHING TASKS HERE:
1. [T][ ] buy textbooks
2. [D][X] finish CS homework (by: 23:59, Feb 22 2022)
3. [T][ ] buy gift for friend
```

### `delete` - Deleting a task

Deletes a task at the specific index in the list of tasks.

Example of usage:

`delete 2`

Expected outcome:

```
TASK REMOVED:
[D][X] finish CS homework (by: 23:59, Feb 22 2022)
3 TASK(S) NOW.
```

### `clear` - Deleting all tasks

Deletes all tasks in the list of tasks.

Example of usage:

`clear`

Expected outcome:

```
ALL TASKS CLEARED
```

### `bye` - Exiting the programme

Terminates the process running the programme.

Example of usage:

`bye`

Expected outcome:

```
BYE!!!
```