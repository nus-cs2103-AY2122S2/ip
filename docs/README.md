# User Guide

## Features 

### Listing all tasks: `list`

Shows a list of all tasks.

Format: `list`

### Adding a Todo: `todo`

Adds a task without any date/time attached to it.

Format: `todo DESC`

Example:

* `todo buy a new book` add a todo task with the description "buy a new book".

### Adding a Deadline task: `deadline`

Adds a task that need to be done before a specific date/time.

Format: `deadline DESC /by YYYY-MM-DD`

Example:

* `deadline return book /by 2022-04-24` add a deadline task with the description "return book" that needs to be done before 2022-04-24.

### Adding an Event task: `event`

Adds a task that start at a specific time and ends at a specific time.

Format: `event DESC /at YYYY-MM-DD`

Example:

* `event project meeting /at 2022-04-25` adds an event task with description "project meeting" that is done on 2022-04-25.

### Marking a task: `mark`

Marks the specified task as done.

Format: `mark INDEX`

Example:

* `mark 1` marks the 1st task shown when the `list` command is run.

### Unmarking a task: `unmark`

Unmarks the specified task as done.

Format: `unmark INDEX`

Example:

* `unmark 1` unmarks the 1st task shown when the `list` command is run.

### Deleting a task: `delete`

Deletes the specified task. Irreversible.

Format: `delete INDEX`

Example:

* `delete 1` deletes the 1st task shown when the `list` command is run.

### Finding tasks that contains the keyword: `find`

Find tasks whose description matches the given keyword.

Format: `find KEYWORD`

Example:

* `find meeting` finds and returns all tasks containing the keyword "meeting".

### Viewing schedule: `schedule`

Filters tasks that occur on the specified date.

Format: `schedule DATE`

Example:

* `schedule 2022-04-23` filters for tasks that occurs on 2022-04-23.

### Viewing help: `help`

Shows the list of commands.

Format: `help [COMMAND]`

Example:

* `help` shows information for all the available commands.
* `help todo` shows information for the `todo` command.

### Exiting: `bye`

Exits the program.

Format: `bye`
