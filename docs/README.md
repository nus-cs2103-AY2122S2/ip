# User Guide

## Features 

### Create Tasks

Create tasks to keep track of things to do.

### Listing Tasks

View lists of tasks.

### Marking tasks

Mark a task as *done* or *not done*.

### Deleting tasks

Deletes a specified task.

### Finding tasks

Finds tasks that have names that contain the given text string.

## Usage

### `todo <name>` - Creates a task that can be marked as *done* or *not done*.

- Example: `todo return book`
  - Expected outcome: Creates a task called 'return book'.

### `deadline <name> /by <date> <time>` - Creates a task that can have a date and time.

- Example: `deadline return book /by  2021-01-01 18:00`
  - Expected outcome: Creates a task called 'return book' that has a deadline of 2021-01-01 at 18:00.

### `event <name> /at <date> <time>` - Creates a task that can have a date and time.

- Example: `event group project /at  2021-01-01 18:00`
  - Expected outcome: Creates a task called 'group project' that has a date and time of 2021-01-01 at 18:00.

### `list` - Shows all your current tasks.

- Example: `list`
  - Expected outcome: Shows the list of tasks.

### `mark <number>` - Marks a task on the list as *done* or *not done*

- Example: `mark 3`
  - Expected outcome: Marks task 3 on the list as *done*.

- Example: `unmark 3`
  - Expected outcome: Marks task 3 on the list as *not done*.

### `delete <number>` - Deletes the task with task index `<number>` on the list

- Example: `delete 3`
  - Expected outcome: Deletes task 3 on the list.

### `find <text>` - Fetch all tasks matching that text string

- Example: `find book`
  - Expected outcome: Shows all tasks that have names that contain 'book'.

## Others

### Short forms of commands

Use these short forms in place of commands to quickly refer to the command in question.
- `t` - `todo`
- `d` - `deadline`
- `e` - `event`
- `l` - `list`
- `m` - `mark`
- `um` - `unmark`
- `del` - `delete`
- `f` - `find`


