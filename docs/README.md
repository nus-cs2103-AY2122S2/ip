# User Guide
Duke is a **desktop app for managing tasks**, designed for best use through **command line interface**(CLI). It is fast to learn and easy to use.
- [Quick start](#quick-start)
- [Features](#features)
  - [Adding a todo task](#adding-a-todo-task)
  - [Adding a deadline task](#adding-a-deadline-task)
  - [Adding an event task](#adding-an-event-task)
  - [Listing tasks](#listing-tasks)
  - [Finding tasks](#finding-tasks)
  - [Marking a task](#marking-a-task)
  - [Unmarking a task](#unmarking-a-task)
  - [Deleting a task](#deleting-a-task)
  - [Clearing all tasks](#clearing-all-tasks)
  - [Creating command alias](#creating-command-alias)
  - [Exiting the app](#exiting-the-app)
- [Usage summary](#usage-summary).

## Quick Start
1. Ensure you have [Java 11](https://www.oracle.com/java/technologies/downloads/#java11) or above installed on your computer.
2. Download Duke's latest version [here](https://github.com/1001mei/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Double-click the file to start the app.
5. Type your command in the dialog box and press Enter to execute it. Some examples you can try:
    - `todo read book` : adds a todo task "read book"
    - `mark 1` : marks the first task as done
    - `list` : displays all tasks in the list
    - `bye` : exits the app
6. Interested to know what other things you can do with Duke? Refer to the [Features](#features) section below.
7. Prefer to learn command syntaxes first? Refer to the [Usage summary](#usage-summary) section below.

## Features 
### Adding a todo task

Adds a todo task.

Command format: `todo [task name]`

Example: `todo read book`

### Adding a deadline task

Adds a task that has a deadline.

Command format: `deadline [task name] /by [YYYY-MM-DD]`

Example: `deadline return book /by 2022-02-28`

### Adding an event task

Adds a task that is happening at a certain time.

Command format: `event [task name] /at [when]`

Example: `event team meeting /at tonight`

### Listing tasks

Shows all tasks in the task list.

Command format: `list`

### Finding tasks

Finds all tasks that contains the specified keyword.

Command format: `find [keyword]`

Example: `find meet`

### Marking a task

Marks a task as done by its index.

Command format: `mark [task index]`

Example: `mark 1`

### Unmarking a task

Unmarks a task as not done yet by its index.

Command format: `unmark [task index]`

Example: `unmark 1`

### Deleting a task

Deletes a task by its index.

Command format: `delete [task index]`

Example: `delete 1`

### Clearing all tasks

Clears all tasks in the task list.

Command format: `clear`

### Creating command alias

Creates a new command that is more convenient for you to refer to an existing Duke command.

Command format: `alias [new command name] [existing Duke command]`

Example: `alias d delete`

### Exiting the app

Saves all changes to disk and exits the app.

Command format: `bye`

## Usage summary

| Command | Shortcut | Format | Example |
| --- | --- | --- | --- |
| todo | t | todo [task name] | todo read book |
| deadline | d | deadline [task name] /by [YYYY-MM-DD] | deadline return book /by 2022-03-31 |
| event | e | event [task name] /at [when] | event meeting /at tonight |
| list | ls | list | list |
| find | f | find [keyword] | find book |
| mark | m | mark [task index] | mark 2 |
| unmark | um | ummark [task index] | ummark 2 |
| delete | del | delete [task index] | delete 2 |
| clear | c | clear | clear |
| alias | a | alias [new command name] [existing Duke command] | alias d delete |
| bye | b | bye | bye |

> Some notes/tips on command format:
> - All commands are **case sensitive**.
> - Words enclosed in [ ] brackets are parameters to be supplied by the user.\
>    e.g. in `todo [task name]`, `task name` is a parameter which can be supplied as `todo read book`.
> - `task index` must be an integer. You can find the index of a task by calling the `list` command.
> - You can always use the shortcut name to replace the full command name.\
>    e.g. `ls` is the same as `list`, `m 3` is the same as `mark 3`
> - You can also create your own shortcut command using `alias` command if you wish.
