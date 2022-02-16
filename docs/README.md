# User Guide
Duke is a desktop app for managing tasks, deadline and events. It features a stunning Graphical User Interface (GUI). It is themed around the CS2103 module, with the chat assistant being Prof. It will save the states of all tasks upon successful exit and will automatically load all tasks from the previous session.

## Features 
**:information_source: Notes about the command format:**
* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo NAME`, `NAME` is a custom parameter that the user should supply (e.g. `todo homework`).


### Viewing help

Accessible via a `?` symbol next to the text field. It will spawn a text by Prof explaning all available commands.
<img width="401" alt="Screenshot 2022-02-16 at 11 39 28 PM" src="https://user-images.githubusercontent.com/70692871/154300334-17344713-5436-4686-8d86-8e10548b9c9b.png">

### Adding a task: `todo`

Adds a task to the task list.

Format: `todo NAME_OF_TASK`

Examples:
* `todo CS2103T homework`
* `todo Preparation for Hackathon`

Examples:
* `todo CS2103T homework` will result in `[ ][ ] CS2103T homework`
* `todo Preparation for Hackathon` will result in `[ ][ ] Hackathon`

### Adding a deadline: `deadline`

Adds a deadline to the task list.

Format: `deadline NAME_OF_TASK /by TIME`

Note: `TIME` can be any custom value / string. However, if given in the format of YYYY-MM-DD, the deadline timing will format itself to DD Mon YYYY

Examples:
* `deadline homework /by Monday` will result in `[D][ ] homework (by: Monday)`
* `deadline homework /by 2022-03-05` will result in `[D][ ] homework (by: 05 Mar 2022)`

### Adding an event: `event`

Adds an event to the task list.

Format: `event NAME_OF_TASK /at TIME`

Note: `TIME` can be any custom value / string. However, if given in the format of YYYY-MM-DD, the event timing will format itself to DD Mon YYYY

Examples:
* `event homework /at Monday` will result in `[E][ ] homework (at: Monday)`
* `event homework /at 2022-03-05` will result in `[E][ ] homework (at: 05 Mar 2022)`

### Listing all tasks : `list`

Shows a list of all persons in the address book.

Format: `list`

### Locating tasks by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD`

* The search is case-sensitive.
* Partial words can be matched e.g. `work` will match to `Homework`

### Deleting a task : `delete`

Deletes the specified task from the list.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …

Example:
* `list` followed by `delete 2` deletes the 2nd task in the task list.

### Marking a task : `mark`

Marks a task as done.

Format: `mark INDEX`

* Marks the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …

### Unmarking a task : `unmark`

Unmarks a marked task.

Format: `unmark INDEX`

* Marks the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …

### Exit : `bye`

Safely exits and closes the app. This will save all current tasks. The bot will wait 3 seconds after the command before closing.

Format: `bye`

### Saving data

Duke will save the data automatically upon successful exit. There is no need for any commands.

:exclamation: **DO NOT MODIFY THE SAVE FILE IN ANY WAY.** This will corrupt the data and may cause unforseen consequences upon start of Duke.

## Command summary

Action | Format, Examples
--------|------------------
**Add Normal Task** | `todo NAME_OF_TASK`
**Add Deadline** | `deadline NAME_OF_DEADLINE /by TIME` e.g., `deadline homework /by 2022-03-05`
**Add Event** | `event NAME_OF_DEADLINE /at TIME` e.g., `event homework /at 2022-03-05`
**Delete** | `delete INDEX` e.g., `delete 3`
**Find** | `find KEYWORD [MORE_KEYWORDS]` e.g., `find James Jake`
**List** | `list`
**Mark** | `mark INDEX` e.g., `mark 3`
**Unmark** | `unmark INDEX` e.g., `unmark 3`
**Exit** | `bye`
