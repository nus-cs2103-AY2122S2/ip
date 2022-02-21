# Duke User Guide

Duke is an interactive desktop application for task management.

![Screenshot](Ui.png)

## Features 

### Manage your tasks

Duke can help you keep track of your to-dos, events, and deadlines.

You can:
- create to-dos, events, and deadlines
- list your tasks, including event date/times and deadline due dates
- search for keywords within your task list
- mark tasks as done, or not done
- delete tasks

### Data persistence

Your tasks will persist even after you close Duke, of course -- a to-do list
is pointless if everything you note down disappears right after.

You can save and reload your data while using Duke, or let Duke load and save
your data automatically (on startup, and on exit).

## Quick Start

1. Ensure that you have [Java **11** (or newer)](https://adoptium.net/) installed on your computer.
2. Download the [latest `.jar` release of Duke](https://github.com/zhongfu/ip/releases) to any
   location on your computer.
3. Launch Duke by either:
   - double-clicking the `.jar` file, or
   - launching any terminal application (e.g. Windows Terminal, `Terminal.app`, `urxvt`, etc),
     navigating to the directory in which the `.jar` is located, and running `java -jar <filename>.jar`.
4. Once Duke has started, you can start using it. Try typing anything into the text box and pressing
   <kbd>Enter</kbd> to see a list of available commands.

## Usage

| Command                                                                       | Description                           |
|-------------------------------------------------------------------------------|---------------------------------------|
| [`todo <task name>`](#todot---add-a-new-to-do-item)                           | Add a new to-do item.                 |
| [`t <task name>`](#todot---add-a-new-to-do-item)                              | Add a new to-do item.                 |
| [`deadline <task name> /by <date> [time]`](#deadlined---add-a-new-deadline)   | Add a new deadline.                   |
| [`d <task name> /by <date> [time]`](#deadlined---add-a-new-deadline)          | Add a new deadline.                   |
| [`event <task name> /at <date> [time]`](#evente---add-a-new-event)            | Add a new event.                      |
| [`e <task name> /at <date> [time]`](#evente---add-a-new-event)                | Add a new event.                      |
| [`list`](#list---list-all-tasks)                                              | List all tasks.                       |
| [`find <query>`](#find---find-tasks-containing-keyword)                       | Find tasks containing `<query>`.      |
| [`mark <index>`](#markunmark---mark-task-as-done-or-not-done)                 | Mark task at `<index>` as done.       |
| [`unmark <index>`](#markunmark---mark-task-as-done-or-not-done)               | Mark task at `<index>` as not done.   |
| [`delete <index>`](#delete---delete-task)                                     | Delete tasks at `<index>`.            |
| [`save`](#save---save-task-list-to-disk)                                      | Save task list to disk.               |
| [`reload`](#reload---reload-task-list-from-disk)                              | Reload task list from disk.           |
| [`bye`](#bye---save-task-list-and-exit)                                       | Save task list and exit.              |

### `todo`/`t` - Add a new to-do item

Adds a new to-do item with the given description.

Format: `todo <task name>`

Examples:
- `todo Water the plants`
- `todo Feed the dog`

### `deadline`/`d` - Add a new deadline

Adds a new deadline with the given description and date/time.

`<date>` is a date in `year-month-day` (e.g. `2020-05-15`) or `day month year` (e.g. `1 Jul 2020`, `3 September 2008`)
format.

`[time]` is an optional time in 24-hour format, with or without seconds.

If a time is not included, then the time of the deadline is set to 00:00.

Format: `deadline <task name> /by <date> [time]`

Examples:
- `deadline Submit tax assessment /by 4 apr 2021`
- `deadline Pay the bills /by 1 january 2022 16:30`
- `d Take out the recycling /by 2022-05-30 08:20:30`

### `event`/`e` - Add a new event

Adds a new event with the given description and date/time.

Adds a new deadline with the given description and date/time.

`<date>` is a date in `year-month-day` (e.g. `2020-05-15`) or `day month year` (e.g. `1 Jul 2020`, `3 September 2008`)
format.

`[time]` is an optional time in 24-hour format, with or without seconds.

If a time is not included, then the time of the deadline is set to 00:00.

Format: `event <task name> /at <date> [time]`

Examples:
- `event Generic work conference /at 2023-02-25 08:20:00`
- `event Dinner with John /at 14 February 2022 12:30`
- `e Alice's birthday /at 4 apr 2021`

### `list` - List all tasks

Lists all tasks stored in the task list.

Format: `list`

### `find` - Find tasks containing keyword

Finds tasks that contain `<query>`.

Format: `find <query>`

Examples:
- `find work`
- `find tax assessment`

### `mark`/`unmark` - Mark task as done or not done

Marks the task at `<index>` as done (with `mark`) or not done (with `unmark`).

`<index>` refers to the index shown in the output of `list`.

Format: `mark <index>`, `unmark <index>`

Examples:
- `mark 5`
- `unmark 8`

### `delete` - Delete task

Deletes the task at `<index>`.

`<index>` refers to the index shown in the output of [`list`](#list---list-all-tasks).

Format: `delete <index>`

Examples:
- `delete 3`

### `save` - Save task list to disk

Saves the task list to `data/tasks.dat` in the current directory.

For instance, if your current directory in your terminal application is `C:\` and you launch Duke
with `java -jar C:\Users\Bob\duke.jar`, then the task list will be saved to `C:\data\tasks.dat` and
not `C:\Users\Bob\data\tasks.dat`.

Format: `save`

### `reload` - Reload task list from disk

Loads the task list from `data/tasks.dat` in the current directory.

For instance, if your current directory in your terminal application is `C:\` and you launch Duke
with `java -jar C:\Users\Bob\duke.jar`, then the task list will be loaded from `C:\data\tasks.dat`
and not `C:\Users\Bob\data\tasks.dat`.

Format: `reload`

### `bye` - Save task list and exit

Saves the task list to `data/tasks.dat` in the current directory, then exits.

For instance, if your current directory in your terminal application is `C:\` and you launch Duke
with `java -jar C:\Users\Bob\duke.jar`, then the task list will be saved to `C:\data\tasks.dat` and
not `C:\Users\Bob\data\tasks.dat`.

Format: `bye`
