# User Guide

Duke is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `duke.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.<br>
   ![Ui](Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`list`** and pressing Enter will list all current tasks.<br>


1. Refer to the [Features](#features) below for details of each command.

## Features 

### Viewing help: `help`

Opens the User's Guide in default browser.

Format: `help`

### Exit the program: `bye`

Closes Duke.

Format: `bye`

Examples:
* `bye`

### List all tasks: `list`

Displays all current tasks.

Format: `list'

### Find task by keyword: `find`

Finds task whose name contains keyword.

Format: `find [KEYWORD]`

Examples:
* `find home`
* `find school`

### Mark task as done: `mark`

Marks specified task as done.

Format: `mark INDEX`

Examples:
* `mark 1`


### Mark task as not done: `unmark`

Marks specified task as not done.

Format: `unmark INDEX`

Examples:
* `unmark 1`

### Add todo task: `todo`

Adds a todo task.

Format: `todo NAME`

Examples:
* `todo buy book`

### Add deadline task: `deadline`

Adds a deadline task.

Due date must be of form YYYY-MM-DD.

Format: `deadline NAME /by DUE_DATE`

Examples:
* `deadline return book /by 2021-12-03`

### Add event task: `event`

Adds an event task.

Date must be of form YYYY-MM-DD.

Format: `event NAME /at DATE`

Examples:
* `event project meeting /at 2021-12-03`

### Delete task: `delete`

Deletes a specified task.

Date must be of form YYYY-MM-DD.

Format: `delete INDEX`

Examples:
* `delete 1`
