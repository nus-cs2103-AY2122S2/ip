# User Guide

Cap'n Dave is a **desktop app for managing and keeping track of your daily tasks, optimized for use via a Graphical User Interface (GUI)**.

- [Quick Start](#quick-start)
- [Features](#features)
  - [Viewing Help](#help)
  - [Adding a Todo task: `todo`](#todo)
  - [Adding a Deadline task: `deadline`](#deadline)
  - [Adding an Event task: `event`](#event)
  - [Listing all tasks: `list`](#list)
  - [Marking a task: `mark`](#mark)
  - [Unmarking a task: `unmark`](#unmark)
  - [Finding tasks by keywords: `find`](#find)
  - [Deleting a task: `delete`](#delete)
  - [Exiting the program: `bye`](#exit)
  - [Saving the data](#save)
- [FAQ](#faq)
- [Command summary](#summary)

<hr/>
<br/>

## <a name="quick-start"></a>Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest duke.jar from [here](https://github.com/xSaints19x/ip/releases).
3. Copy the file to the folder you want to use as the home folder for Cap'n Dave.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
5. Type the command in the command box and press Enter or click on the "Send" button to execute it. e.g. typing help and pressing Enter will open the help window. Some example commands you can try:
   - `list`: List all tasks
   - `todo coding everyday`: Adds a Todo task with description `coding everyday`
   - `delete 1`: Deletes the 1st task shown in the current list
   - `mark 1`: Marks the 1st task shown in the list as done
   - `bye`: Exits the program
6. Refer to the [Features](#features) below for details of each command.

<hr/>
<br/>

## Features 

:information_source: Notes about the command format:
- Words in `UPPER_CASE` are the parameters to be supplied by the user. <br/> e.g `todo TASK_DESCRIPTION`, `TASK_DESCRIPTION` is a parameter which can be used as `todo coding everyday`
- Items in square brackets are optional. <br/> e.g `deadline TASK DESCRIPTION /by DATE [TIME]` can be used as `deadline project meeting /by 2022-04-19 2359hrs` or as `deadline project meeting /by 2022-04-19`
- Extraneous parameters for commands that do not take in parameters (such as `help`, `list` and `bye`) will be ignored. <br/> e.g if the command specifies `help me!`, it will be interpreted as `help`.

<br/>

## <a name="help"></a>Viewing help: `help`
Shows a message containing a list of commands and format to type them in.
Format: `help`

<br/>

## <a name="todo"></a>Adding a Todo task: `todo`
Adds a Todo task to the task list.

Format: `todo TASK_DESCRIPTION`

Examples:
- `todo coding everyday`

<br/>

## <a name="deadline"></a>Adding a Deadline task: `deadline`
Adds a Deadline task to the task list.

Format: `deadline TASK_DESCRIPTION /by DATE [TIME]`

Examples: 
- `deadline project meeting /by 2022-04-19 2359hrs`
- `deadline project meeting /by 2022-04-19`

<br/>

## <a name="event"></a>Adding an Event task: `event`
Adds an Event task to the task list.

Format: `event TASK_DESCRIPTION /at DATE [TIME]`

Examples: 
- `event project meeting /at 2022-12-31 12-6pm`
- `event project meeting /at 2022-12-31`

<br/>

## <a name="list"></a>List all tasks: `list`
Lists all tasks in the task list

Format: `list`

<br/>

## <a name="mark"></a>Mark a task: `mark`
Mark a task in the task list as done.

Format: `mark INDEX`

Examples:
- `mark 1`

Expected outcome:

Mark the 1st task on the task list.

```
Task completed, good job matey!
1.[T][X] Add new tasks into this list! :)

Aye, Aye. Your next command:
```

<br/>

## <a name="unmark"></a>Unmark a task: `unmark`
Mark a task in the task list as not done.

Format: `unmark INDEX`

Examples:
- `unmark 1`

Expected outcome:

Unmark the 1st task on the task list.

```
Alright matey, hurry up and finish up this task arrr:
1.[T][ ] Add new tasks into this list! :)

Aye, Aye. Your next command:
```

<br/>

## <a name="find"></a>Find tasks matching keywords: `find`
Finds all tasks that matches the given keyword.

Format: `find coding`
- The keyword is case-sensitive e.g `Coding` will not match with `coding`
- The order of the keywords matters e.g `everyday coding` will not match `coding everyday`
- Only the task description is searched for.
- Full words will be matched e.g `cod` will match tasks with `coding`
- Only tasks matching the entire keyword input will be matched e.g `coding anyday` will not match `coding everyday`

Example:
`find coding`

Expected outcome:

Finds all tasks with description containing coding.

```
Avast ye Matey. Here goes your task list:
1.[T][ ] coding everyday
2.[D][ ] coding assignment (by: Feb 20 2022 2359hrs)

Aye, Aye. Your next command:
```

<br/>

## <a name="delete"></a>Delete a task: `delete`
Delete a task in the task list.

Format: `delete INDEX`

Example: 
`delete 1`

Expected outcome:

Deletes the 1st task on the task list.

```
Alright matey, task has been deleted good on ya.
1.[T][ ] Add new tasks into this list! :)

Aye, Aye. Your next command:
```

<br/>

## <a name="exit"></a>Exit the program: `bye`
Exits the program.

Format: `bye`

<br/>

## <a name="save"></a>Saving the data

Cap'n Dave data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

<hr/>
<br/>

## <a name="faq"></a>FAQ 

**Q**: How do I transfer my data to another Computer?
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Duke home folder.

<hr/>
<br/>

## <a name="summary"></a>Command Summary

| Command  | Format, Examples |
| ------------- | ------------- |
| Add Todo task  | `todo TASK_DESCRIPTION` <br/> e.g `todo coding everyday` |
| Add Deadline task | `deadline TASK_DESCRIPTION /by DATE [TIME]` <br/> e.g `deadline project meeting /by 2022-04-19 2359hrs` |
| Add Event task | `event TASK_DESCRIPTION /at DATE [TIME]` <br/> e.g `event project meeting /at 2022-12-31 12-6pm` |
| List | `list` |
| Mark | `mark INDEX` <br/> e.g `mark 1` |
| Unmark | `unmark INDEX` <br/> e.g `unmark 1` |
| Find | `find KEYWORD` <br/> e.g `find coding` |
| Delete | `delete INDEX` <br/> e.g `delete 1` |
| Help | `help` |
