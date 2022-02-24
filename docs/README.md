# User Guide
Duke is a **desktop app for managing tasks, optimized via a Command Line Interface** (CLI).

A Graphical User Interface (GUI) has also been added which mimics a chatbot you can interact with.

- [Quick Start](#quick-start)
- [Features](#features)
  - [Adding a task](#adding-a-task)
  - [Listing all tasks](#listing-all-tasks-list)
  - [Sorting all tasks by time](#sorting-all-tasks-by-time-sort)
  - [Finding tasks by keyword](#finding-tasks-by-keyword-find)
  - [Marking a task as done](#marking-a-task-as-done-mark)
  - [Unmarking a task](#unmarking-a-task-unmark)
  - [Deleting a task](#deleting-a-task-delete)
  - [Exiting the program](#exiting-the-program-bye)
- [Command Summary](#command-summary)
- [References](#references)

## Quick Start
1. Ensure that you have Java `11` or above installed on your Computer.
2. Download the latest `duke.jar` from [here](https://github.com/Decaxical/ip/releases).
3. Copy the file to the folder you want to use as the *home folder* for Duke.
4. Double-click the file to start the app.
5. Type the command in the command box and press `Enter` key or click the `Send` button to execute it.
6. Refer to the [Features](#features) below for details of each command.

## Features 
**Notes about the command format:**
- Parameters to be supplied by the user are in `UPPER_CASE`.

  e.g. `todo TASK_NAME`, `TASK_NAME` is a parameter to be supplied such as `todo homework`.
  
---
### Adding a task:
Duke supports 3 types of tasks: `todo`, `deadline` and `event`
### Adding a todo task: `todo`
Adds a task that has no date and time parameters.

Format: `todo TASK_NAME`

Example: `todo return book`

### Adding a deadline task: `deadline`
Adds a task that needs to be done before a specific date/time.

Format: `deadline TASK_NAME /by DD/MM/YYYY HH:MM`

Example: `deadline submit project /by 22/02/2022 22:22`

### Adding an event task: `event`
Adds a task that starts at a specific date/time.

Format: `event TASK_NAME /at DD/MM/YYYY HH:MM`

Example: `event volleyball training /at 24/02/2022 21:00`

---
### Listing all tasks: `list`

Shows a numbered list of all tasks in the task list.

Format: `list`

---
### Sorting all tasks by time: `sort`

Separates all tasks by type before sorting them by time. Upcoming tasks are displayed first.

Format: `sort`

---
### Finding tasks by keyword: `find`
Find tasks whose name contains the keyword supplied.

Format: `find KEYWORD`

Example: `find meeting`

---
### Marking a task as done: `mark`
Marks task at given index as done.

Format: `mark INDEX`

Example: `mark 4`

---
### Unmarking a task: `unmark`
Unmarks task at given index.

Format: `unmark INDEX`

Example: `unmark 4`

---
### Deleting a task: `delete`
Deletes task at given index.

Format: `delete INDEX`

Example: `delete 3`

---
### Exiting the program: `bye`

Exits the program.

Format: `bye`

## Command Summary

 Action  | Format, Examples 
 ---| ---
 Todo    | `todo TASK_NAME` </br> e.g., `todo return book`
 Deadline| `deadline TASK_NAME /by DD/MM/YYYY HH:MM` <br/>  e.g., `deadline submit project /by 22/02/2022 22:22` 
 Event   | `event TASK_NAME /at DD/MM/YYYY HH:MM` <br/>  e.g.,  `event volleyball training /at 24/02/2022 21:00`
 List    | `list`
 Sort    | `sort`
 Find    | `find KEYWORD` <br/>  e.g., `find meeting`
 Mark    | `mark INDEX` <br/>  e.g., `mark 4`
 Unmark  | `unmark INDEX` <br/>  e.g., `unmark 4`
 Delete  | `delete INDEX` <br/>  e.g., `delete 3`
 Exit    | `bye`

## References
[AddressBook-Level3](https://github.com/j-lum/addressbook-level3)
