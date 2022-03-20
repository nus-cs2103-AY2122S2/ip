# User Guide

BingChilling is a **desktop app for managing tasks. optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, BingChilling can get your contact management tasks done faster than traditional GUI apps.

* [Quick Start](#quick-start)
* [Features](#features)
  * [Adding a to do task : `todo`](#adding-a-to-do-task--todo)
  * [Adding an event task : `event`](#adding-an-event-task--event)
  * [Adding a deadline task : `deadline`](#adding-a-deadline-task--deadline)
  * [Locating tasks by description : `find`](#locating-tasks-by-description--find)
  * [Listing all task : `list`](#listing-all-task--list)
  * [Marking a task as done : `mark`](#marking-a-task-as-done--mark)
  * [Marking a task as not done : `unmark`](#marking-a-task-as-not-done--unmark)
  * [Postponing a task : `postpone`](#postponing-a-task--postpone)
  * [Deleting a task : `delete`](#deleting-a-task--delete)
  * [Clearing all entries : `clear`](#clearing-all-entries--clear)
  * [Exiting the program : `bye`](#exiting-the-program--bye)
  * [Saving the data](#saving-the-data)
  * [Editing the data file](#editing-the-data-file)
* [Command summary](#command-summary)

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest bingchilling.jar from [here](https://github.com/cwq2326/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your Bing Chilling bot.
4. Double-click the file to start the app.

---
## Features 

### Adding a to do task : `todo`

Adds a to do task into the task list.

Format: `todo TASK`

Example of usage: 

* `todo buy groceries`
* `todo watch webcast` 
###  Adding an event task : `event`

Adds an event task into the task list with the specified date.
Format: `event TASK /at DATE`
* The date **must be of the format** `DD/MM/YYYY` e.g. `1/7/2021`

Example of usage: 

* `event wedding /at 20/8/2029`
* `event career fest /at 12/3/2001` 

### Adding a deadline task : `deadline`

Adds an event task into the task list.

Format: `deadline <task> /by DATE`
* The date **must be of the format** `DD/MM/YYYY` e.g. `1/7/2021`

Example of usage: 

* `deadline assignment /by 20/8/2029`
* `deadline return book /by 12/3/2001` 

### Locating tasks by description : `find`

Finds tasks whose descriptions contain any of the given keywords.

Format: `find KEY_WORD`
* The search is case-sensitive. e.g `chill` will not match `CHILL`
* The order of the keywords matter. e.g. `river water` will not match `water river`
* Only the description is searched.
* Only substrings will be matched e.g. `Darker` will not match `Dark` but `Dark` will match `Darker`
* Tasks matching at least one keyword will be returned e.g. `join school` will return `join school choir`, `join school band`.

Examples of usage:
* `find career fair`
### Listing all task : `list`

Shows a list of all the task in the task list.

Format: `list`

### Marking a task as done : `mark`

Marks a task with `X` that indicates it is done.

Format: `mark INDEX`
* Marks a task in the task list at the specified INDEX. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, …​
* Can only mark a task which is not yet marked.

Example of usage: 

* `mark 1`
  * Marks the first task in the task list as done.
  
### Marking a task as not done : `unmark`

Unmarks a task with `X` that indicates it is not yet done.

Format: `unmark INDEX`
* Unmarks a task in the task list at the specified INDEX. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, …​
* Can only unmark a task which is already marked.

Example of usage: 

* `unmark 1`
  * Unmarks the first task in the task list as not yet done.

### Postponing a task : `postpone`

Postpone the specified task from the task list.

Format: `postpone INDEX DATE`
* Postpones the task at the specified INDEX to the specified DATE.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​
* The date **must be of the format** `DD/MM/YYYY` e.g. `1/7/2021`

Example of usage: 

* `postpone 1 12/5/2019`
  * Postpones the first task in the task list to `12/5/2019`

### Deleting a task : `delete`

Format: `delete INDEX`
* Deletes the task at the specified INDEX.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Example of usage: 

* `delete 1`
  * Deletes the first task in the task list.
  
### Clearing all entries : `clear`

Clears all entries from the task list.

Format: `clear`

### Exiting the program : `bye`

Exits the program.

Format: `bye`

### Saving the data
BingChilling data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file
BingChilling data are saved as a text file [JAR file location]/data/ekud.txt. Advanced users are welcome to update data directly by editing that data file.

` ⚠️ If your changes to the data file makes its format invalid, Bing Chilling will discard all data and start with an empty data file at the next run.`

---

## Command summary

| Action   | Format, Examples |
| -        | - |
| todo     | `todo TASK` eg. `todo buy milk` |
| event    | `event TASK /at DATE` eg. `event sports day /at 12/3/2021` |
| deadline | `deadline TASK /by DATE` eg. `deadline assignment /by 11/4/2022` |
| find | `find KEYWORD` eg. `find career fair` |
| list | `list` |
| mark | `mark INDEX` eg. `mark 1` |
| unmark | `mark INDEX` eg. `unmark 1` |
| postpone | `postpone INDEX DATE` eg. `postone 1 12/12/1212` |
| delete | `delete INDEX` eg. `delete 1` |
| clear | `clear` |
| bye | `bye` |
