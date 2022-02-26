# User Guide

Sparrow is a **desktop app for managing tasks, optimised for use via a Command Line Interface** (CLI) while having the benefits of a Graphical User Interface (GUI). If you can type fast, Sparrow can get your task management tasks done faster than traditional GUI apps.

- [Quick start](#quick-start)
- [Features](#features)
    * [Adding tasks](#adding-tasks)
        + [Adding an event: `event`](#adding-an-event---event-)
        + [Adding a deadline: `deadline`](#adding-a-deadline---deadline-)
        + [Adding a to-do: `todo`](#adding-a-to-do---todo-)
    * [Viewing tasks](#viewing-tasks)
        + [Viewing all tasks: `list`](#viewing-all-tasks---list-)
        + [Finding a task: `find`](#finding-a-task---find-)
    * [Editing tasks](#editing-tasks)
        + [Marking a task: `mark`](#marking-a-task---mark-)
        + [Unmarking a task:`unmark`](#unmarking-a-task--unmark-)
        + [Prioritising a task: `prioritise`](#prioritising-a-task---prioritise-)
    * [Deleting tasks: `delete`](#deleting-tasks---delete-)
    * [Saving tasks: `bye`](#saving-tasks---bye-)
- [Command summary](#command-summary)

## Quick start

1. Ensure you have Java `11` or above installed in your computer.

2. Download the latest `sparrow.jar` from [here](https://github.com/sheimoria/ip/releases/tag/A-Release).

3. Copy the file to the folder you want to use as the *home folder* for your Sparrow.

4. Double-click the file to start the app. A GUI similar to below should appear in a few seconds.

- ![Ui](Ui.png)

5. Type a command in the command box and press Enter to execute it. e.g. typing `todo read book` and pressing Enter will add a to-do "read book" to your tasks.
   Some example commands you can try:

    - `list`: Lists all your tasks.

    - `todo read book`: Adds a to-do "read book" to your tasks.

    - `delete 1`: Deletes your 1st task.

    - `mark 1`: Marks your 1st task as done.

    - `bye`: Saves all your tasks to the hard disk.

Refer to the [features](#features) section below for the details of each command.

## Features

### Adding tasks

#### Adding an event: `event`

Adds an event to your tasks.

Format: `event DESCRIPTION /at YYYY-MM-DD HHMM`

Example: `event book sharing /at 2022-02-18 1400`

#### Adding a deadline: `deadline`

Adds a deadline to your tasks.

Format: `deadline DESCRIPTION /by YYYY-MM-DD HHMM`

Example: `deadline return book /by 2022-02-20 2220`

#### Adding a to-do: `todo`

Adds a to-do to your tasks.

Format: `todo DESCRIPTION`

Example: `todo read book`

### Viewing tasks

#### Viewing all tasks: `list`

Lists all your tasks.

Format: `list`

#### Finding a task: `find`

Finds your tasks whose descriptions contain the given keyword.

Format: `find KEYWORD`

Example: `find read book`

### Editing tasks

#### Marking a task: `mark`

Marks the task at the given index as done.

Format: `mark INDEX`

Example: `mark 1`

#### Unmarking a task:`unmark`

Unmarks the task at the given index; if the task is not marked already, nothing happens.

Format: `unmark INDEX`

Example: `unmark 1`

#### Prioritising a task: `prioritise`

Changes the priority level of the task at the given index to the given priority level.

Format: `prioritise INDEX PRIORITY_LEVEL`

- `PRIORITY_LEVEL` can be only be `low`, `medium`, or `high`

Example: `prioritise 1 high`

### Deleting tasks: `delete`

Deletes the task at the given index.

Format: `delete INDEX`

Example: `delete 1`

### Saving tasks: `bye`

Saves all your tasks to the hard disk.

Format: `bye`

## Command summary

| Command      | Format                                     | Example                                    |
|--------------|--------------------------------------------|--------------------------------------------|
| `event`      | `event DESCRIPTION /at YYYY-MM-DD HHMM`    | `event book sharing /at 2022-02-18 1400`   |
| `deadline`   | `deadline DESCRIPTION /by YYYY-MM-DD HHMM` | `deadline return book /by 2022-02-18 1400` |
| `todo`       | `todo DESCRIPTION`                         | `todo read book`                           |
| `list`       | `list`                                     | `list`                                     |
| `find`       | `find KEYWORD`                             | `find book`                                |
| `mark`       | `mark INDEX`                               | `mark 1`                                   |
| `unmark`     | `unmark INDEX`                             | `unmark 1`                                 |
| `prioritise` | `prioritise INDEX PRIORITY_LEVEL`          | `prioritise 1 high`                        |
| `delete`     | `delete INDEX`                             | `delete 1`                                 |
| `bye`        | `bye`                                      | `bye`                                      |
