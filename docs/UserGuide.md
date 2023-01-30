# Ultoi User Guide

*Ultoi* is a desktop app for **managing tasks**. 
It combines the benefit of a Command Line Interface (CLI) and a Graphic User Interface (GUI).
If you can type fast, *Ultoi* can help you manage your tasks better than traditional GUI apps.

----------
## <span style="color:#e46c0b">Quick Start</span>

1. Ensure you have Java `11` or above installed in your computer.
2. Download the latest `ultoi.jar` from [here]().
3. Copy the file to the folder you want to use as the **home folder** for your Ultoi.
4. Double-click the file to start the app.
5. Refer to the [Features](#features) below for details of each command.

----------

## <span style="color:#e46c0b">Features</span> 

### Adding Tasks

Adds a task to *Ultoi*.
There are 3 types of tasks in Ultoi, `todo`, `deadline` and `event`. 
You can add any one of them.

#### To add a to-do:

Format: `todo DESCRIPTION`
* Adds a customised to-do event to *Ultoi* with specified `DESCRIPTION`.

Examples: 
* `todo play with Ultoi` Adds a to-do task `play with Ultoi`.

#### To add a deadline:

Format: `deadline DESCRIPTION /by DATETIME`
* Adds a customised deadline with specified `DESCRIPTION`, which is due by `DATETIME`.
* The format of `DATETIME` must be `YYYY-MM-DD HHMM` (in 24-hour format).

Examples: 
* `deadline finish assignment /by 2022-03-01 2359` Adds a deadline `finish assignment` which is to be completed by 11:59pm, 1 March 2022.

#### To add an event:

Format: `event DESCRIPTION /at DATETIME`
* Adds a customised event with specified `DESCRIPTION`, which takes place on `DATETIME`.
* The format of `DATETIME` must be `YYYY-MM-DD HHMM` (in 24-hour format).

Examples:
* `event prizing ceremony /at 2022-04-01 1200` Adds an event `prizing ceremony` which happens on 12:00nn, 1 April 2022.

### Viewing Tasks: `list`

Lists all current tasks stored.

Format: `list`

### Deleting Tasks: `delete`

Deletes a task.

Format: `delete INDEX`
* Deletes a task based on the specified `INDEX`.
* If you are not sure about the `INDEX` of a task, you can view it by typing `list`. 

Examples:
* `delete 1` Deletes the task with index `1` among all tasks.

### Marking Tasks

Marks a task as done or undone.
This can help you identify whic tasks you have done or have not done yet among all tasks.

#### To mark a task as done: `mark`

Format: `mark INDEX`
* Marks the task with specifed `INDEX` as done.

#### To mark a task as not done yet: `unmark INDEX`

Format: `unmark INDEX`
* Marks the task with specified `INDEX` as not done yet.

### Finding Tasks: `find`

Finds tasks with specified keywords.

Format: `find KEYWORD`
* Finds tasks whose description contains `KEYWORD`.

Examples:
* `find ceremony` Finds all tasks that contains the keyword `ceremony`.

### Sorting Tasks: 'sort'

Sorts all tasks based on their **priority**. Priority is determined based on the chronological order of their happening time, regardless of the task type.
Particularly, since todo tasks do not have a specified time, their priority will be the lowest. 
If two tasks have the same happening time, their priority will be determined by their alphabetical order.

Format: `sort`
* Sorts and shows all tasks based on their **priority** as defined above.

### Loading and Saving Tasks

When `Ultoi` starts up, it will try to load past task records from your computer. 
If `Ultoi` does not find such a record, it will start from empty.

`Ultoi` automatically saves your changes as you are using, so don't worry that you may lose your past tasks!

### Exit Ultoi: `bye`

Quits the app.

Format: `bye`
* You can either do this or simply close the window to quit the app.

----------

## <span style="color:#e46c0b">FAQ</span> 

**Q:** How do I transfer my data to another computer?

**A:** Simply copy the task records located in `home/data/` and paste it to the same path in your new computer.

----------

## <span style="color:#e46c0b">Command Summary</span>

| Action | Format & Examples |
| --- | --- |
| Add | `todo DESCRIPTION`<br>`deadline DESCRIPTION /by DATETIME`<br>`event DESCRIPTION /at DATETIME`<br>e.g. `deadline complete assignment /by 2022-03-01 2359`
| View | `list` |
| Delete | `delete INDEX`<br>e.g. `delete 1` |
| Mark | `mark INDEX`<br>`unmark INDEX` |
| Find | `find KEYWORD`<br>e.g. `find ceremony` |
| Sort | `sort` |
| Exit | `bye` |
