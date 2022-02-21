# User Guide

**J.A.R.V.I.S.** is your personal desktop assistant for task management.

![Jarvis UI](./Ui.png)

## Quick Start

1. Ensure you have Java `11` or above installed in your computer.
2. Download the latest `jarvis.jar` from [here](https://github.com/NatalieTanML/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the home folder for Jarvis.
4. Double-click the file to start the app. The application GUI should appear in a few seconds.
5. Type the command in the input box and press Enter to execute it. e.g. typing `list` and pressing Enter will display the task list.
   Some example commands you can try:
   - `list`: Lists all tasks.
   - `todo submit project`: Creates a Todo called `submit project` to the task list.
   - `mark 1`: Marks the first task as done.
   - `event project meeting /at 2022-12-31 2359`: Creates an Event called `project meeting` scheduled for 31 December 2022, at 11:59pm.
   - `bye`: Exits the app.
6. Refer to the [Features](#features) below for details of each command.

## Features

> :grey_exclamation: **Notes about the command format:**
> - Words surrounded with angled brackets such as `<TODO_NAME>` are the parameters to be supplied by the user. The brackets themselves are not part of the command input.
> - All datetime inputs are to be formatted as `yyyy-MM-dd HHmm`, e.g. `2022-12-31 2359`.
> - Extraneous parameters for commands that do not take in parameters (such as `list`, `exit`) will be ignored.
  e.g. if the input is `list 123`, it will be interpreted as `list`.

### `list`: Listing all tasks 

- Shows a list of all tasks in the task list.

```
list
```

### `todo`: Add a Todo

- Adds a todo to the task list.

```
todo <TODO_NAME>
```

### `deadline`: Add a Deadline

- Adds a deadline to the task list with a specified datetime.

```
deadline <DEADLINE_NAME> /by <DATETIME>
```

### `event`: Add an Event

- Adds an event to the task list with a specified datetime.

```
event <EVENT_NAME> /at <DATETIME>
```

### `mark`: Mark a task as completed

- Marks a task at the specified `<INDEX>` as completed. The index refers to the index number shown in the displayed task list. 

```
mark <INDEX> 
```

### `unmark`: Mark a task as uncompleted

- Marks a task at the specified `<INDEX>` as uncompleted. The index refers to the index number shown in the displayed task list. 

```
unmark <INDEX> 
```

### `delete`: Delete a task

- Deletes a task at the specified `<INDEX>`. The index refers to the index number shown in the displayed task list. 

```
delete <INDEX> 
```

### `find`: Find tasks

- Searches for tasks that match/contains the specified `<KEYWORD>`.

```
find <KEYWORD> 
```

### `snooze`: Snooze a task

- Snoozes a deadline or event task at the specified `<INDEX>` by one day. The index refers to the index number shown in the displayed task list.

```
snooze <INDEX> 
```

### `bye`: Exit the app

- Saves the task list and exits the application.

```
bye
```

## Acknowledgments 

- Libraries used: [JavaFX](https://openjfx.io/), [JUnit5](https://github.com/junit-team/junit5)