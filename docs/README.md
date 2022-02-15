# User Guide

Duke is a **Personal assistant chat-bot desktop application** that can help you to
keep track of various tasks.

![UI Main](/ip/Ui.png)

## Table of content
1. [Quick Start](#quick-start)
2. [Usage](#usage)
3. [Command Summary](#command-summary)

## Quick Start
1. Ensure that you have ``Java 11`` installed on your Computer.

2. Download the latest release of ```duke.jar``` from [release](https://github.com/yl-ang/ip/releases).

3. Double-click the file to start the app.

4. Type in the command in the message box and press ```Send``` to send over to Duke.
Some example commands you can try out:
* **`list`** : Lists all the existing tasks.
* **`todo`** `iP User Guide` : Adds a task `iP User Guide` to Duke.
* **`deadline`** `user stories for tP /by 2022-02-15`: Adds a task `user stories for tP` with the following
deadline on `2022-02-15`


## Usage

**Notes about the command format:**<br>
* Items in square brackets are optional.<br>
  e.g. `INDEX [d/DESCRIPTION] [t/DATE]` can be used as `1 d/HELLO` or as `1 d/HELLO t/2022-02-14`.


### Adding a Task: `todo`,`deadline` and `event`

Adds a new task. A task can be a todo, deadline or event task.

Format:<br>
`todo DESCRIPTION`<br>
`deadline DESCRIPTION /by DATE`<br>
`event DESCRIPTION /at DATE`<br>
* `DESCRIPTION` refers to the description of the task.
* `DATE` refers to the new date of the task.
* `DATE` must be in the format YYYY-MM-DD.

Examples:<br>
`todo preparing for LSM1301 lab`<br>
`deadline user stories for tP /by 2022-02-15`<br>
`event GET1020 Midterm /at 2022-02-28`<br>

Expected outcome:
```
Got it. l've added this task:
    [T][] preparing for LSM1301 lab
Now you have 1 task in the list.
```

```
Got it. l've added this task:
    [D][] user stories for tP (by:
2022-02-15)
Now you have 2 task in the list.
```

```
Got it. l've added this task:
    [E][] GET1020 Midterm (at:
2022-02-28)
Now you have 3 task in the list.
```


### Listing all the current tasks : `list`

Shows a list of all the tasks the user currently have.

Format: `list`


### Updates existing task : `update`

Updates an existing task.

Format `update INDEX [d/DESCRIPTION] [t/DATE]`
* Updates the task at the specified `INDEX`. The INDEX refers to the index number shown in
the displayed list. The index **must be a positive integer** 1,2,3, ...
* Existing values will be updated to the input values.
* At least one of the optional fields must be provided.
* `DESCRIPTION` refers to the new description of the task.
* `DATE` field is optional and only can be used on `deadline` and `event` tasks.
* `DATE` refers to the new date of the task.
* `DATE` supplied must be in the format YYYY-MM-DD.

Examples:<br>
```update 1 d/CS3230 Assignment 5 t/2022-02-13```<br>
```update 1 d/CS3230 Assignment 5```<br>
```update 1 t/2022-02-13```<br>

Expected outcome:
```
I've updated this task:
[D][] CS3230 Assignment 5 (by: 2022-02-13)
```


### Mark a task as done: `mark`

Mark an existing task as done with a cross.

Format `mark INDEX`
* Mark the task at the specified `INDEX` as done. The INDEX refers to  the index number shown in the task list.
The index **must be a positive integer** 1,2,3, ...

Example:<br>
`mark 1`

Expected outcome:
```
Nice! I've marked this taks as done:
[D][X] CS3230 Assignment 5 (by: 2022-02-13)
```


### Undo a marked task: `unmark`

Unmark an existing done task by removing the cross.

Format `unmark INDEX`
* unmark the task at the specified `INDEX`. The INDEX refers to the index number shown in the task list.
The index **must be a positive integer** 1,2,3, ...

Example:<br>
`unmark 1`

Expected outcome:
```
Nice! I've marked this taks as done:
[D][] CS3230 Assignment 5 (by: 2022-02-13)
```


### Locating tasks by keyword: `find`

Finds existing tasks whose descriptions contain the given keyword.

Format `find KEYWORD`
* The search is case-sensitive. e.g `CODE` will not match `code`
* Only one `KEYWORD` is to be supplied. e.g if `CS2103 Assignments` is supplied as search keywords,
only `CS2103` be used in searching of the tasks.

Example:<br>
`find GET1020`

Expected outcome:
```
Here are the matching tasks in your list:
1. [E][] GET1020 Midterm (at:
2022-02-28)
```


### Delete a task: `delete`.

Delete the specified task from Duke.

Format `Delete INDEX`
* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the list displayed from running `list`.
* The index **must be a positive integer** 1,2,3, ...

Example:<br>
```list``` followed by ```delete 1``` deletes the 1st task in Duke.


### Exiting the application: `bye`

Exits the application.

Format `bye`


## Command Summary

| Action       | Format, Examples                                                                                                                                         |
|:-------------|:---------------------------------------------------------------------------------------------------------------------------------------------------------|
| **todo**     | `todo DESCRIPTION` <br> e.g. `todo CS2101 OP2 Slides`                                                                                                    |
| **deadline** | `deadline DESCRIPTION /by DATE` <br> e.g. `deadline CS2105 Assignment 1 /by 2022-02-19`                                                                  |
| **event**    | `event DESCRIPTION /at DATE` <br> e.g. `event DCTF /at 2022-02-13`                                                                                       |
| **list**     | `list`                                                                                                                                                   |
| **update**   | `update INDEX [d/DESCRIPTION] [t/DATE]` <br> e.g. `update 1 d/CS3230 Assignment 5 t/2022-02-13` `update 1 d/CS3230 Assignment 5` `update 1 t/2022-02-13` |
| **mark**     | `mark INDEX` <br> e.g. `mark 1`                                                                                                                          |
| **unmark**   | `unmark INDEX` <br> e.g. `unmark 1`                                                                                                                      |
| **find**     | `find KEYWORD` <br> e.g. `find CS2103` `find CS2105`                                                                                                     |
| **delete**   | `delete INDEX` <br> e.g. `delete 1`                                                                                                                      |
| **bye**      | `bye`                                                                                                                                                    |