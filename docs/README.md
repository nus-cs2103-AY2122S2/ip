# User Guide

**Meep is a desktop application for helping you remember things you need to do.** 
While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface).

![Ui](Ui.png)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `meep.jar` from [here](https://github.com/zzkzzzz/ip/releases).

3. Copy the file to the folder you want to use as the _home folder_.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will print the help instructions.<br>

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

##  Features 

### Viewing help : `help`

Shows a message explaning how to use the application.

Format: `help`


### Listing tasks: `list`

#### Lists all the tasks

Format: `list​`

Examples:
* `list`

#### Listing tasks before given date

Lists all the tasks.

Format: `list [ date & time ]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
 Note for [ date & time ]: enter it strictly in the following format
       dd/mm/yyyy [ hh : mm ] | eg: 02/12/2019 18:00             
</div>

Examples:
* `list 02/12/2019 18:00`
* `list 05/02/2022 21:00`


### Adding task : `todo` `deadline` `event`

#### Adding todo task

tasks without any date/time attached to it.

Format: `todo [task title]`

Examples:
* `todo CS2103 week1 quiz`

#### Adding event task

tasks that start at a specific time and ends at a specific time.

Format: `event [ task title ] /on [ date & time ]`

Examples:
* `event CS2103 tP group discussion /on 05/02/2022 14:00`
* `event movie with JunJie /on 09/02/2022 19:00`

#### Adding deadline task

tasks that need to be done before a specific date/time.

Format: `deadline [ task title ] /by [ date & time ]​`

Examples:
* `deadline CS2103 iP submission /by 17/02/2022 12:00`
* `deadline clean my room /by 09/02/2022 19:00`

### Locating task by a keyword: `find`

Find tasks whose descriptions contain any of the given keywords.

Format: `find [ KEYWORD ]`

* The search is case-sensitive. e.g `book` will match `BOOK`
* Partial words will be matched e.g. `book` will not match `books`
* Only support one keyword search. Context after `find` will be treated as one keyword.

Examples:
* `find book` returns `read book`
* `find CS2103` returns `CS2103 iP submission` and `CS2103 tP discussion`

### Mark task as done/undone: `mark` `unmark`

Mark a task as done or undone with given index

Format: `mark [ TASK INDEX ]` `unmark [ TASK INDEX ]`

* Mark/unmark the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `mark 2` marks the 2nd task as done.
* `list` followed by `unmark 3` marks the 2nd task as undone.
* `find report` followed by `mark 1` marks the 1st task in the results of the `find` command.

### Deleting a task : `delete`

Deletes the specified task from task list.

Format: `delete [ TASK INDEX ]`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd task in the task list.
* `find report` followed by `delete 1` deletes the 1st task in the results of the `find` command.

### Exiting the program : `bye`

Exits the program. All the data will be stored in `data.txt` file.

Format: `bye`
