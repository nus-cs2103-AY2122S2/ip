# User Guide

Wonka is a **desktop app created with the sole purpose of task management, optimized for use via a 
Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, Wonka can increase your task management efficiency by **at least 40%**!

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `Wonka.jar` from [here](https://github.com/glennljw/ip/releases). 
3. Copy the file to the folder you want to use as the _home folder_ for your Wonka.
4. Double-click the file to start the app. The GUI similar to the image below should appear in a few seconds. Note how the app contains some sample data.

![](https://raw.githubusercontent.com/glennljw/ip/master/docs/Ui.png)

## Features and Usage

> Notes about the command format:
> * Words in `UPPPER_CASE` are the parameters to be supplied by the user.  
> e.g. in `todo DESCRIPTION`, DESCRIPTION is a parameter which can be used as `todo task`.

### Adding a task to be done: `todo`

Adds a task to be done.  
Format: `todo DESCRIPTION`

### Adding a task with a deadline: `deadline`

Adds a task with a deadline.  
Format: `deadline DESCRIPTION /by DEADLINE`

* `DEADLINE` should be specified as date, followed by time in **24h format.**  
  i.e. `YYYY-MM-dd hhmm`  

Examples:

* `deadline Create Wonka /by 2022-02-18 2359` adds a task into the task list with description `Create Wonka` and
  deadline `18 Feb 2022 11:59PM`.
* `deadline Submit Assignment /by 2022-02-20 2359` adds a task into the task list with description `Submit Assignment`
  and deadline `20 Feb 2022 11:59PM`.

### Adding an event: `event`

Adds an event with a given time.  
Format: `event DESCRIPTION /at TIME`

* `TIME` can be represented in any format desired by the user.  
  e.g. `/at whenever you feel like it :P`

Examples:

* `event Attend a meeting /at 12PM this Sunday` adds an event into the task list with description `Attend a meeting`
  and time `12PM this Sunday`.
* `event Feed Wonka /at up to you~~~` adds an event into the task list with description `Feed Wonka` and time
  `up to you ~~~`.

### Adding a task that requires a certain fixed duration: `fixed`

Adds a task that requires a fixed duration.
Format: `fixed DESCRIPTION /needs DURATION`

* `DURATION` can be represented in any format desired by the user.  
  e.g. `/needs approximately 10 hours? not sure`.

Examples:

* `fixed Study /needs 2 hours` adds a task into the task list with description `Study` and duration `needs 2 hours`.
* `fixed Shower /needs approximately 20 hours? I think so..` adds a task into the list with description `Shower` and
  duration `needs approximately 20 hours? I think so..`.

### Listing all tasks: `list`

Shows a list of all tasks added.  
Format: `list`

### Marking a task as done: `mark`

Marks a task as done.  
Format: `mark INDEX`

* Marks the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list.   
* The index **must be a positive integer** e.g. 1, 2, 3, ... 

Example:

* `mark 10` Marks the 10th task as done.
* `mark 1` Marks the 1st task as done.

### Unmarking a task that was marked as done: `unmark`

Unmarks a task that was marked as done.

* Unmarks the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list.   
* The index **must be a positive integer** e.g. 1, 2, 3, ...

Example:

* `unmark 2` Unmarks the 2nd task.
* `unmark 5` Unmarks the 5th task.

### Locating a task by name: `find`

Finds tasks whose name contain any of the given keywords.
Format: `find KEYWORD`

* The search is case-sensitive. e.g. `book` will not match `Book`
* Only the name is searched.
* Partial words will be matched. e.g. `Boo` will match `Book`
* Tasks with names matching will be returned as a list.

Examples:
* `find Study` returns `Study` and `Study for test`.
* `find Feed` returns `Feed Wonka`.

### Deleting a task: `delete`

Deletes the specified task from the task list.
Format: `delete INDEX`

* Deletes the task at the specified `INDEX`
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** e.g. 1, 2, 3, ...

Examples:

* `list` followed by `delete 7` deletes the 7th task on the list.

### Exiting the program: `bye`

Exits the program.
Format: `bye`

### Saving the data

Wonka automatically creates a folder in your hard disk on first launch, and automatically saves data to a 
save file in the folder after any command that changes the data.  

There is **no need to save manually.**
