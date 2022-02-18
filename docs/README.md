# User Guide
Duke is a desktop application for managing your tasks, optimised for use via a Command Line Interface (CLI) while still 
having the benefits of a Graphical User Interface (GUI). If you can type fast, Duke can get your task management done 
faster than traditional GUI apps.

## Table of Contents
1. [Quick Start](#quick-start)
2. [Features](#features)
   1. [Adding a todo task: `todo`](#adding-a-todo-task-todo)
   2. [Adding an event task: `event`](#adding-an-event-task-event)
   3. [Adding a deadline task: `deadline`](#adding-a-deadline-task-deadline)
   4. [Deleting a person: `delete`](#deleting-a-person-delete)
   5. [Listing all tasks: `list`](#listing-all-tasks-list)
   6. [Saving the data](#saving-the-data)
   7. [Editing the data file](#editing-the-data-file)
3. [Command Summary](#command-summary)
4. [Acknowledgements](#acknowledgements)

---

## Quick Start
1. Ensure that you have Java 11 or above installed on your Computer
2. Download the latest `Duke.jar` from [here](https://github.com/sbhbenjamin/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Open the command window in that folder.
5. Run the command `java -jar Duke.jar` to launch the application.
6. Type the command in the message box and press enter or press the `Send` button to execute it. e.g. typing `list` and pressing Enter will show all of your tasks in the list.   
   Some example commands you can try:
    - `todo Complete CS2103T Tutorial`: Adds a todo task with description `Complete CS2103T Tutorial` to the task list.
    - `event CS2103T Lecture /at 2022-10-22`: Adds an event task with description `CS2103T Lecture` at `2022-10-22` to the task list.
    - `deadline CS3240 Milestone G1 /by 2022-10-22`: Adds a deadline task with description `CS3240 Milestone G1` at `2022-10-22` to the task list.
    - `delete 3`: Deletes the 3rd task shown in the task list.
    - `list`: List all tasks.
    - `bye`: Exits Duke.
7. Refer to the Features below for details of each command.

## Features
>  💡 **Notes about the command format**
> - Words in `UPPER_CASE` are parameters to be supplied by the user.  
>   e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo Complete CS2103T Tutorial`.
> - Items in square brackets are optional.  
>   e.g. `todo DESCRIPTION [/t TAGNAME]` can be used as `todo Complete CS2103T Tutorial /t CS2103T`.

### Adding a todo task: `todo`
Adds a todo task to the task list.  
Format: `todo DESCRIPTION [/t TAGNAME]`    
Examples:
- `todo Complete CS2103T Tutorial`
- `todo Complete CS2103T Tutorial /t CS2103T`

### Adding an event task: `event`
Adds an event task to the task list.  
Format: `event DESCRIPTION /at YYYY-MM-DD [/t TAGNAME]`  
Examples:
- `event CS2103T Lecture /at 2022-10-22`
- `event CS2103T Lecture /at 2022-10-22 /t CS2103T`

### Adding a deadline task: `deadline`
Adds a deadline task to the task list.  
Format: `deadline DESCRIPTION /by YYYY-MM-DD [/t TAGNAME]`  
Examples:
- `deadline CS3240 Milestone G1 /by 2022-10-22`
- `deadline CS3240 Milestone G1 /by 2022-10-22 /t CS3240`

### Deleting a person: `delete`
Removes a task from the task list.  
Format: `delete INDEX`
- Deletes the person at the specified `INDEX`
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...

### Listing all tasks: `list`
Lists all tasks in the task list.    
Format: `list`

Examples:
- `list` followed by `delete 2` deletes the 2nd task in the task list.

### Saving the data
Task list data are saved in the hard disk automatically after any command that changes the data.  
There is no need to save manually.

### Editing the data file
Task list data are saved as a `.txt` file in `[JAR file location]/data/duke.txt`  
Advanced users are welcome to update data directly by editing that data file.

## Command Summary

| Action   | Format, Examples                                                                                       |
|----------|--------------------------------------------------------------------------------------------------------|
| Todo     | `todo DESCRIPTION [/t TAGNAME]`   e.g `event CS2103T Lecture /at 2022-10-22`                           |
| Event    | `event DESCRIPTION /at YYYY-MM-DD [/t TAGNAME]`   e.g `event CS2103T Lecture /at 2022-10-22`           |
| Deadline | `deadline DESCRIPTION /by YYYY-MM-DD [/t TAGNAME]`   e.g `deadline CS3240 Milestone G1 /by 2022-10-22` |
| Delete   | `delete INDEX` e.g. `delete 2`                                                                         |
| List     | `list`                                                                                                 |
| Exit     | `bye`                                                                                                  |

## Acknowledgements
This user guide is adapted from Software Engineering Education's [AddressBook Level 3 (AB3) user guide](https://se-education.org/addressbook-level3/UserGuide.html#command-summary).


