# User Guide

MyBoss is a desktop app for managing tasks with a Command Line Interface.
Manage your tasks with MyBoss!

![MyBoss application](.//Ui.png)

## Getting started

1. Ensure you have Java 11 or above installed.
2. Download the jar application [here](https://github.com/joszx/ip/releases).
3. Double click to open the application.

## Features 

### Manage your tasks!

Command line controlled app to manage your tasks!

### Boss pressure

Have the pressure of a boss watching your every move.

## Usage

**Notes on usage format:**
* Dates are in `yyyy-mm-dd` format
* `UPPER_CASE` words are parameters to be supplied by the user
* `TIME` has no format
* `TASKNAME` can be multiple words and has no fixed format
* `PRIORITY` can be `high`, `med` or `low` regardless of capitalization



### `list` - Lists all tasks

Format: `list`

### `bye` - Exits the application

Format: `bye`

### `mark` - Marks the specified task as done

Format: `mark INDEX`

Example: `mark 2`

### `unmark` - Marks the specified task as not done

Format: `unmark INDEX`

Example: `unmark 2`

### `delete` - Deletes the specified task

Format: `delete INDEX`

Example: `delete 3`

### `todo` - Adds a new todo task

Format: `todo TASKNAME`

Example: `todo borrow book`

### `deadline` - Adds a new task with deadline

Format: `deadline TASKNAME /by DATE`

Example: `deadline return book /by 2023-10-01`

### `event` - Adds a new event

Format: `event TASKNAME /at DATE TIME`

Example: `event borrow another book /at 2021-10-02`

### `find` - Finds tasks with the specified keyword in its name

Format: `find KEYWORD`

Example: `find book`

### `priority` - Sets the specified task's priority

Format: `priority INDEX PRIORITY`

Example: `priority 3 high`