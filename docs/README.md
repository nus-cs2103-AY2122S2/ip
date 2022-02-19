# User Guide

Duke is a desktop application for managing personal tasks. Users can create different types of tasks such as Deadlines, Todos, and Events. Users can interact with it through text commands, while presenting it in a simple-to-understand chatbot interface.

## Quick Start & Installation

0. Ensure you have installed Java 11 on your computer
1. Download the latest `Duke.jar` file here
2. Move the `.jar` file into your desired folder
3. Run the app by double clicking on `Duke.jar` file
4. Enter your commands in the text box and hit `Enter` to run them

# Features 

### Quick Summary
Action | Format 
-------|--------
__Adding todo__ | `todo TODO_DESCRIPTION`
__Adding deadline__ | `deadline DEADLINE_DESCRIPTION /by yyyy-MM-dd [HHmm]`
__Adding event__ | `event EVENT_DESCRIPTION /at yyyy-MM-dd [HHmm]`
__Listing all tasks__ | `list`
__Marking task__ | `mark INDEX_NUMBER`
__Unmarking task__| `mark INDEX_NUMBER`
__Deleting task__ | `delete INDEX_NUMBER`
__Finding list of tasks by keyword__ | `find KEYWORD`
__Exiting the application__ | `bye`

### Adding Tasks
There are 3 types of tasks that can be added to the task manager:

- **todo** is a task that has no dates
- **deadline** is a task that must be completed by the given date (and time)
- **event** is a task that occurs at the given date (and time)

#### Adding a todo: `todo DESCRIPTION`
Creates a new Todo with the specified `DESCRIPTION`
- **Usage:** `todo DESCRIPTION`
- Examples:
  - `todo Finish Homework`
  - `todo buy groceries`

#### Adding a deadline: `deadline DESCRIPTION /by DATE [TIME]`
Creates a new Deadline with the given `DESCRIPTION`, `DATE` and `TIME (optional)`.
`TIME` is assumed to be in 24hrs format.
- **Usage:** 
  - `deadline DESCRIPTION /by YYYY-MM-DD HHmm`
  - `deadline DESCRIPTION /by YYYY-MM-DD`

- Examples:
  - `deadline Submit english essay /by 2022-02-20 2359`
  - `deadline college application /by 2022-12-01 2359`


#### Adding an event: `event DESCRIPTION /at DATE [TIME]`
Creates a new Event with the given `DESCRIPTION`, `DATE` and `TIME (optional)`.
`TIME` is assumed to be in 24hrs format.
- **Usage:**
  - `event DESCRIPTION /at YYYY-MM-DD HHmm`
  - `event DESCRIPTION /at YYYY-MM-DD`

- Examples:
  - `event Attack on Titan season finale /at 2022-03-06 0000`
  - `event cs1101s midterm test /at 2022-03-06 0900`

### Listing Tasks
Displays all the tasks in the list.
**Usage:** `list`
- Each task has a __index number__ to identify the task in the list
- __Index number__ is used to refer to a task in other commands


### Marking and Unmarking tasks
Tasks in the list can be marked as done, or unmarked to set it as undone.

Marked tasks will be labeled with `[X]` and unmarked tasks are labeled with `[]`

**Usage:**
- `mark INDEX`
    - Marks the task at index number `INDEX` as done.
- `unmark INDEX`
    - Marks the task at index number `INDEX` as undone.

Examples:
- `mark 2` - marks the second item as done
- `unmark 1` - marks the first item as undone


### Deleting tasks
Tasks in the list can be deleted using the keyword `delete`

**Usage:** `delete INDEX`
- Deletes the task at with index number `INDEX`

Examples:
- `delete 1`  - deletes the first item

### Finding tasks
Finds tasks in the tasklist containing the specified keyword.

**Usage:** `find KEYWORD`
- Note: `KEYWORD` provided by the user is case sensitive

Examples:
- `find midterm` - returns a list of tasks containing the word `midterm`.
- `find essay` - returns a list of tasks containing the word `essay`.

### Quitting the application
**Usage:** `bye`
- The program will close 1.5 seconds after sending this command.

## Frequently Asked Questions

**Q: Will the list of tasks be saved?**
A: Yes, the list will be saved automatically after each command. They will be loaded again when the user launches the application again.

**Q: Where is the savefile?**
A: The savefile is a file named `duke.txt`, and can be found in the same folder where `Duke.jar` is stored, under a folder called `data`.

**Q: Can I edit the savefile?**
A: It is not recommended to edit the savefile as this may corrupt the data stored inside. If memory is corrupted, a fresh savefile will override the corrupted one, and all old data will be lost.