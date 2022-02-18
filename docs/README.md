# User Guide

Snorlax is a desktop app for you to manage your **tasks** and **contacts** so that you will **stop procrastinating** 
and **get things** done! Through an interactive Graphical User Interface (GUI), managing upcoming events and 
deadlines will never be the same again!

## User Interface

![](../docs/Ui.png)

## Before you start
Create the file
`data/duke.txt` and `data/contacts.txt` in the same directory as your jar file.

## Features 

### Managing Tasks

`list` - list out all your tasks <br>
`todo` - add a Todo task <br>
`deadline` - add a Deadline task in the form of '{task} /by {datetime}' <br>
`event` - add an Event task in the form of '{task} /at {datetime}' <br>
`mark` - mark task as done <br>
`unmark` - mark task as undone <br>
`delete` - delete task <br>
`find` - find task in task list <br>
`manage contacts` - switch to manage contacts <br>

### Managing contacts

`list` - list out all your contacts <br>
`add` - add a contact in the form of '{name} {number}' <br>
`update` - update a contact in the form of '{name} {number}' <br>
`delete` - delete contact <br>
`manage tasks` - switch to manage tasks <br>

## Usage

### Listing all your tasks : `list` when in Managing Tasks mode

List all tasks. <br>
If task starts with `[T]`, it is a `Todo` task. <br>
If task starts with `[E]`, it is an `Event` task. <br>
If task starts with `[D]`, it is a `Deadline` task. <br><br>
If task has `[X]`, it means task is already done. <br>
If task has `[X]`, it means task is not done yet.


Format: `manage tasks` then `list`


### Add a task : `todo` `deadline` or `event`

Adds a task. Task can be `todo`, `deadline` or `event` <br>

Format: <br>
* `todo sleep early`
* `deadline CS3244 Project /by 2022-04-04 23:59` 
* `event CS2100 Midterms /at 2022-03-03 19:00`

**Note**: Deadline must be in the format of `deadline {task} /by {date}` <br>
**Note**: Deadline must be in the format of `event {task} /at {date}` <br>
**Note**: Date must be in the format `YYYY-MM-DD HH:mm`


### Mark or unmark a task : `mark` or `unmark`

Mark a task as done or unmark a task as undone. <br>

Format: <br>
* `mark INDEX`
* `unmark INDEX`

**Note**: `INDEX` is the index of the task displayed in list.

### Delete a task : `delete`

Delete a task. <br>

Format: <br>
* `delete INDEX`

**Note**: `INDEX` is the index of the task displayed in list.

### Find a task with a specific keyword: `find`

Find tasks with the specific keyword. <br>

Format: <br>
* `find KEYWORD`

**Note**: `KEYWORD` is the word that should appear in your task name.

### Listing all your contacts : `list` when in Managing Contacts mode

List all contacts. <br>

Format: <br>
`manage contacts` then `list`

### Add a new contact : `add`

Add a new contact. <br>

Format: <br> 
* `add Chris Ong 12345678`
* `add Police 999`

**Note**: Add must be in the format of `add {name} {contact number}`

### Update an existing contact : `add`

Update an existing contact number to a new contact number. <br>

Format: <br>
* `update Chris Ong 87654321`

**Note**: Update must be in the format of `update {name} {contact number}` <br>
**Note**: Name must be in existing contact list.

### Delete an existing contact : `delete`

Delete an existing contact. <br>

Format: <br>
* `delete INDEX`

**Note**: `INDEX` is the index of the contact displayed in contact list.

### Switch from managing contacts to managing tasks and vice versa: `manage contacts` and `manage tasks`

Switch modes of managing contacts and managing tasks.

Format: <br>
* `manage contacts`
* `manage tasks`

### Exiting and saving all changes: `bye`

Exits the program and save all changes made to tasks and contacts

**Note**: If you do not exit, changes made will not be saved.