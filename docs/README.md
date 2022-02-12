# User Guide

SaiTasker is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). It is designed to help busy students organise their homework deadlines and co-curricular activities.

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `SaiTasker.jar` from [here](https://github.com/tyanhan/ip/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your SaiTasker.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.<br>
   ![image](https://user-images.githubusercontent.com/68331979/153713851-ce5b7e94-7a33-4d90-95a8-f5d3f22f03c0.png)

5. Type your command in the text box and press Enter, or click the Send button to execute it. e.g. typing **`list`** and pressing Enter will open the current list of tasks.<br>
   Some example commands you can try:
    * **`todo`**`Learn the basics of SaiTasker` : Adds a `ToDo` type task with description `Learn the basics of SaiTasker` to the task list.
    * **`delete`**`1` : Deletes the 1st task in your task list.
    * **`bye`** : Says goodbye to Saitama and saves your task list.

#### Refer to the [Features](#features) below for details of each command.

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo TASK_DESCRIPTION`, `TASK_DESCRIPTION` is a parameter.

* Items in square brackets are optional.<br>
  e.g `todo TASKNAME [TAG]` can be used as `todo Tutorial` or as `todo Tutorial --rw`, where `--rw` is a `TAG`.

* Tags are specified by `--` followed by the tag alias. The list of possible tags are shown [here](#tags).

There are 3 types of tasks that you can add to the task list.

1. A `ToDo` task
2. An `Event` task
3. A `Deadline` task

Each of these task types can be recurring or non recurring. To add a recurring task, you will need to tag it with one of the following tags shown [here](#tags).

### Adding a `ToDo`:
Adds a `ToDo` task to the task list.

Format: `todo TASK_DESCRIPTION`

Examples:
* `todo CS2103T Project`
* `todo --rw CS2103T Project`

### Adding an `Event`:
Adds an `Event` task to the task list.

Format: `event [TAG] TASK_DESCRIPTION /at LOCATION`

Examples:
* `event Attend CS2107 Tutorial /at COM1-0212`
* `event --rw Attend CS2107 Tutorial /at COM1-0212`

### Adding a `Deadline`:
Adds a `Deadline` task to the task list.

Format: `deadline [TAG] TASK_DESCRIPTION /by DD/MM/YYYY HH:MM`

Examples:
* `deadline Submit CS2103T Project /by 18/02/2022 10:00`
* `deadline --rw Submit CS2103T Project /by 18/02/2022 10:00`

### `List` your tasks:
Lists your current tasks in the task list.

Format: `list`

### `Delete` a task:
Deletes the numbered task in the task list.

Format: `delete TASK_NUMBER`

Example:
* `delete 2`

### `Mark` a task:
Marks the numbered task in the list as done.

Format: `mark TASK_NUMBER`

Example:
* `mark 2`

### `Unmark` a task:
Marks the numbered task in the list as not done.

Format: `unmark TASK_NUMBER`

Example:
* `unmark 2`

### `Find` a task:
Searches the task list and returns the list of tasks that contain the `QUERY`

Format: `find QUERY`

Example:
* `find (WEEKLY)`

### Exiting the app `Bye`:
Format: `bye`

## Tags
Tag | Description | Example
--rd | Task recurs daily | `todo --rd Check email`
--rw | Task recurs weekly | `deadline --rw CS2103T Project /by 
