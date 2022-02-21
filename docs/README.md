# User Guide

Apollo is a **desktop app for managing tasks via Command Line Interface**. 
A Graphical User Interface have been added. This project is build upon the `Duke` project template. 

- [Quick Start](#quick-start)
- [Commands](#commands)
  - [Show how to use command: `help`](#show-how-to-use-command-help)
  - [Add a new task: `todo`,`deadline`,`event`](#add-a-new-task-tododeadlineevent)
  - [Delete a task: `delete`](#delete-a-task-delete)
  - [Find specific task: `task`](#find-specific-task-find)
  - [List all tasks: `list`](#list-all-tasks-list)
  - [Mark a task as done/not dont: `mark`,`unmark`](#mark-a-task-as-donenot-done-markunmark)
  - [Exit program: `exit`](#exit-program-exit)
- [Additional features](#additional-features)
  - [Save](#save)
  - [Load](#load)
- [Command Summary](#command-summary)
- [Credits](#credits)

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `apollo.jar` from [here](https://github.com/j4ck990/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your Apollo. 
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
   ![](https://j4ck990.github.io/ip/Ui.png)
5. Type the command in the command box and press `Enter` on your keyboard or click `Send` to execute it.
You can type `help` to see possible commands.

Refer to [Commands](#commands) 
for details for each command. 

## Commands

- Items to be filled by user are capitalised <br/>
 e.g. `todo DESCRIPTION`
- Optional items are wrapped in []
- e.g. `help [COMMAND]`

---

### Show how to use command: `help`
Show how to use specific command: `help [COMMAND]` <br/>
- `[COMMAND]` is supplied, shows help for that command. <br/>
e.g. `help todo`
- No `[COMMAND]` supplied, shows help for all commands. <br/>
e.g. `help`

---

### Add a new task: `todo`,`deadline`,`event`

#### Adding a todo task: `todo DESCRIPTION`
Adds a task without any due date and time. <br/>
e.g. `todo Go to the Gym`

#### Adding a deadline: `deadline DESCRIPTION /by DD-MM-YYYY HH:MM`
Adds a deadline task with the supplied due date and time. <br/>
e.g. `deadline Submit assignment /by 20-02-2022 23:59`

#### Adding an event: `event DESCRIPTION /at DD-MM-YYYY HH:MM`
Adds an event task that happens at the supplied date and time. <br/>
e.g. `event Booster shot /at 2-2-2022 14:00`

---

### Delete a task: `delete`
Delete a task by supplied index: `delete INDEX`<br/>
e.g. `delete 2`

---

### Find specific task: `find`
Searches task descriptions for supplied keyword: `find KEYWORD` <br/>
e.g. `find Assignment`

---

### List all tasks: `list`
Lists all current tasks.
e.g. `list`

---

### Mark a task as done/not done: `mark`,`unmark`
##### Mark task as done: `mark INDEX`
Task at supplied index is marked as done. <br/>
e.g. `mark 1`
##### Mark task as not done: `unmark INDEX`
Task at supplied index is marked as not done. <br/>
e.g. `unmark 1`

---

### Exit program: `exit`
Exits the program. 
e.g.`exit`

## Additional features
### Save
Apollo saves the current list of tasks automatically after each command. 

Save file: `/data/apollo.ser`

### Load
Apollo tries to load list of tasks from save file location. 

If save file cannot be loaded or does not exist, Apollo starts with an empty list of tasks.

## Command Summary

Command | Format | Examples
--- | --- | ---
help | `help [COMMAND]` | `help`<br/>`help deadline`
todo | `todo DESCRIPTION` | `todo Go to the Gym`
deadline | `deadline DESCRIPTION /by DD-MM-YYYY HH:MM` | `deadline Submit assignment /by 20-02-2022 23:59`
event | `event DESCRIPTION /at DD-MM-YYYY HH:MM` | `event Booster shot /at 2-2-2022 14:00`
delete | `delete INDEX` | `delete 2`
find | `find KEYWORD` | `find Assignment`
list | `list`
mark | `mark INDEX` | `mark 1`
unmark | `unmark INDEX` | `unmark 2`
exit | `exit`

## Credits
- [`Duke` Project Template](https://nus-cs2103-ay2122s2.github.io/website/se-book-adapted/projectDuke/index.html)
- [`Address Book 3` user guide](https://se-education.org/addressbook-level3/UserGuide.html)