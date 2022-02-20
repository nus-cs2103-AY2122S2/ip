# User Guide

Bobby is a desktop app for managing your tasks. Utilize commands to add, remove and mark various types of tasks and Bobby will remember these tasks for you.

## Features 
* Adding a To Do: todo
* Adding a Deadline: deadline
* Adding an Event: event
* Listing all tasks: list
* Marking a task as done: mark
* Umarking a task as done: unmark
* Locating task in list: find
* Deleting task: delete
* Undo a change to list: undo
* Exiting the program: bye
* Saving the data


### Adding a To Do

Command: `todo`
Adds a To Do to the list.

Format: todo DESCRIPTION

Examples:

* todo CS2103 user guide
* todo CS2106 quiz


### Adding a Deadline

Command: `deadline`
Adds a Deadline to the list.

Format: deadline DESCRIPTION /by YYYY-MM-DD

Examples:

* deadline CS2106 quiz /by 2022-02-20
* deadline CS2103 quiz /by 2022-02-21


### Adding an Event

Command: `event`
Adds an Event to the list.

Format: event DESCRIPTION /at DESCRIPTION_OF_EVENTTIME

Examples:

* event Family dinner /at friday 6pm
* event Recess week /at 20 February to 27 February


### Listing all tasks

Command: `list`
Shows a list of all tasks in the list.

Format: `list`


###: Marking a task

Command: `mark`
Marks a task as done with an X.

Format: mark INDEX

Example:

* mark 4


### Unmarking a task

Command: `unmark`
Unmarks a task by removing the X.

Format: unmark INDEX

Example:

* unmark 4


### Finding a task

Command: `find`
Finds a task in the list with a keyword that matches task description.

Format: find KEYWORD

Examples:

* find CS2103
* find quiz


### Deleting a task

Command: `delete`
Deletes a task from the list.

Format: delete INDEX

Example:

* delete 5


### Undo a change to the list

Command: `undo`
Reverses a change to the list by a previous command.

Format: `undo`


### Exit the program

Command: `bye`
Exits the program.

Format: `bye`


### Saving the data

Bobby's data of your list of tasks is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
