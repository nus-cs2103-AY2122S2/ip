# Baron ChatBot User Guide

Baron ChatBot or **BCB** in short, is your personal assistant chatbot to help you keep track of tasks and manage them. Everyone should keep a list of tasks to do, so that they stay on track.

It is an easy-to-use desktop application that you can open on your laptop or desktop anywhere and anytime. If you can type fast, you can manage your tasks faster than any other applications.

- [Prerequisites](#prerequisites)
- [Quick Start](#quick-start)
- [Features](#features)
  * [Adding a ToDo task: `todo`](#adding-a-todo-task-todo)
  * [Adding a Deadline task: `deadline`](#adding-a-deadline-task-deadline)
  * [Adding an Event: `event`](#adding-an-event-event)
  * [Listing all tasks: `list`](#listing-all-tasks-list)
  * [Deleting a task: `delete`](#deleting-a-task-delete)
  * [Marking a task: `mark`](#marking-a-task-mark)
  * [Un-marking a task: `unmark`](#un-marking-a-task-unmark)
  * [Finding tasks with a keyword: `find`](#finding-tasks-with-a-keyword-find)
  * [Exiting the application: `bye`](#exiting-the-application-bye)
- [Notes](#notes)
- [Command Summary](#command-summary)

## Prerequisites

- You should have `Java 11` installed in your computer.
- You should know how to operate a shell application (i.e. cd in `Command Prompt` or `Terminal`).
- You should know how to run a `java` command in a shell application.

## Quick Start
1. Download the `jar` file from the release page [here](https://github.com/ngjunkang/ip/releases).
2. Move the `jar` file to an empty folder in where you want to store the `jar` file and the tasks.
3. Open a shell application and navigate to the folder created in step 2.
4. Run the `jar` file with the `java -jar` command in a shell application to run the application, e.g. `java -jar baron.jar`.
5. You can now enter commands into **BCB** and let it manage all your tasks.
6. Refer to Features below for the available commands.

## Features

### Adding a ToDo task: `todo`

Adds a To-Do task to the list.

Format: `todo <description>`

- `<description>` is the description/details of your task

Example of usage:

`todo return book`

Expected outcome:

Adds a To-Do task to return book

```
Got it. I've added this task:
  [T][ ] return book 
Now you have 1 task in your list.
```

### Adding a Deadline task: `deadline`

Adds a task with a deadline to the list.

Format: `deadline <description> /by <date/time>`

- `<description>` is the description/details of your task
- `<date/time>` is the date/time representing the deadline of your task in the format of `d/M/yyyy HH:mm`
    - `d` is the day (e.g. `1` or `01` for first day of the month)
    - `M` is the month (e.g. `9` or `09` for September)
    - `yyyy` is the year (e.g. 2022)
    - `HH` is the hour (e.g. `02` for the second hour of a day)
    - `mm` is the minute (e.g. `08` for the 8th minute of an hour)
- Both values cannot be blank

Example of usage:

`deadline submit physics assignment /by 02/11/2022 23:59`

Expected outcome:

Adds a task to submit physics assignment by 2nd November 2022 at 11:59pm.

```
Got it. I've added this task:
  [D][ ] submit physics assignment (by: Nov 02 2022, 23:59)
Now you have 1 task in your list.
```

### Adding an Event: `event`

Adds an Event to the list.

Format: `event <description> /at <date/time>`

- `<description>` is the description/details of your event
- `<date/time>` is the date/time of your event in the format of `d/M/yyyy HH:mm`
  - `d` is the day (e.g. `1` or `01` for first day of the month)
  - `M` is the month (e.g. `9` or `09` for September)
  - `yyyy` is the year (e.g. 2022)
  - `HH` is the hour (e.g. `02` for the second hour of a day)
  - `mm` is the minute (e.g. `08` for the 8th minute of an hour)
- Both values cannot be blank

Example of usage:

`event Google Interview /at 2/3/2022 13:30`

Expected outcome:

Adds an Event for Google Interview on 2nd March 2022 at 1:30pm.

```
Got it. I've added this task:
  [E][ ] Google Interview (at: Mar 02 2022, 13:30)
Now you have 1 task in your list.
```

### Listing all tasks: `list`

Lists out all the added tasks.

Format: `list`

Notes:
- `[T]` represents a To-Do task.
- `[D]` represents a Deadline task.
- `[E]` represents an Event.
- `[ ]` represents that the task is not done yet (a cross in the application).
- `[X]` represents that the task is done (a tick in the application).

### Deleting a task: `delete`

Deletes the specified task from the task list.

Format: `delete <index>`
- Deletes the task at the specified `<index>`
- `<index>` is the index/ID of the task when the command `list` is executed.

Example of usage:

`delete 1` deletes the first task from the task list and the subsequent tasks are pushed up by one.

### Marking a task: `mark`

Marks the specified task in the task list as done.

Format: `mark <index>`
- Marks the task at the specified `<index>` as done
- `<index>` is the index/ID of the task when the command `list` is executed

Example of usage:

`mark 1` marks the first task in the task list as done.

### Un-marking a task: `unmark`

Un-marks the specified task in the task list as not done yet.

Format: `unmark <index>`
- Un-marks the task at the specified `<index>` as not done yet
- `<index>` is the index/ID of the task when the command `list` is executed

Example of usage:

`unmark 1` un-marks the first task in the task list as not done yet.

### Finding tasks with a keyword: `find`

Finds tasks with description that contains the specified keyword (non-case-sensitive).

Format: `find <keyword>`
- `<keyword>` is the search query that you are looking for (e.g. `borrow book`)

Example of usage:

`find book` lists out all the tasks with description that contains `book`.

### Exiting the application: `bye`

Exits the application.

Format: `bye`

## Notes
1. A `data` folder will be created in the folder with the `jar` file. It is the storage of the tasks, so you can just leave it there in the folder.
2. Marked tasks is represented by a tick and un-marked tasks will be represented by a cross in the application.

## Command Summary

| Action   | Format                                   | Example                                                   |
|----------|------------------------------------------|-----------------------------------------------------------|
| todo     | `todo <description>`                     | `todo return book`                                        |
| deadline | `deadline <description> /at <date/time>` | `deadline submit physics assignment /by 02/11/2022 23:59` |
| event    | `event <description> /at <date/time>`    | `event Google Interview /at 2/3/2022 13:30`               |
| list     | `list`                                   | `list`                                                    |
| delete   | `delete <index>`                         | `delete 1`                                                |
| mark     | `mark <index>`                           | `mark 1`                                                  |
| unmark   | `unmark <index>`                         | `unmark 1`                                                |
| find     | `find <keyword>`                         | `find book`                                               |
| bye      | `bye`                                    | `bye`                                                     |
