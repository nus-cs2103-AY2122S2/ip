# User Guide
Duke is a task managing desktop application that can be used via both the Command Line Interface (CLI) 
or the Graphical User Interface (GUI).

## Quick Start
1. Ensure you have [Java 11](https://www.oracle.com/java/technologies/downloads/#java11) installed
2. Download Duke.jar from [here](https://github.com/lzf834/ip/releases/tag/Level-10)
3. Run the app by double-clicking the .jar file
4. Type in your commands and hit `Enter` to execute them.

An in-depth list of commands can be found below.

## Features 
- Add different types of tasks (***Todo***, ***Events*** and ***Deadlines***)
- Date and time for ***Events*** and ***Deadlines***
- `list` out all your tasks
- `mark`/`unmark` your tasks
- `delete` your old or completed tasks
- `find` your tasks!
- `reset` ALL your tasks

### Adding a ***Todo*** Task
Creates a new Todo task along with the name of the task provided.

Format: 
- `todo TASK_NAME`
  - **ALL** new tasks are marked as Incompleted by default.
  - `TASK_NAME` must not be empty.
 
Example: 
- `todo CS2103 IP` 
  - Creates a new ***Todo*** task called `CS2103 IP`.


### Adding a ***Event*** Task
Creates a new Event task along with the name of the task provided, as well as the **Date** and **Time** (Optional) of the event.

Format: 
- `event TASK_NAME /at TASK_DATE TASK_TIME`
  - **ALL** new tasks are marked as Incompleted by default.
  - `TASK_NAME` must not be empty.
  - `/at` **MUST** be added.
  - `TASK_DATE` must not be empty (Format is in **YYYY-MM-DD**).
  - `TASK_TIME` is **optional** (Format is in **HH:mm**).
 
Example: 
- `event CS2103 TP meeting /at 2022-02-24 09:00` 
  - Creates a new ***Event*** task called `CS2103 TP meeting (at 24 Feb 2022 09:00)`.
- `event CS2103 TP meeting /at 2022-02-24` 
  - Creates a new ***Event*** task called `CS2103 TP meeting (at 24 Feb 2022)`.
  
### Adding a ***Deadlines*** Task
Creates a new Deadline task along with the name of the task provided, as well as the **Date** and **Time** (Optional) of the event.

Format: 
- `deadline TASK_NAME /by TASK_DATE TASK_TIME`
  - **ALL** new tasks are marked as Incompleted by default.
  - `TASK_NAME` must not be empty.
  - `/by` **MUST** be added.
  - `TASK_DATE` must not be empty (Format is in **YYYY-MM-DD**).
  - `TASK_TIME` is **optional** (Format is in **HH:mm**).
 
Example: 
- `deadline CS2103 tutorial /by 2022-02-24 09:00` 
  - Creates a new ***Deadline*** task called `CS2103 tutorial (by 24 Feb 2022 09:00)`.
- `deadline CS2103 tutorial /by 2022-02-24` 
  - Creates a new ***Deadline*** task called `CS2103 tutorial (by 24 Feb 2022)`.
  
### Listing out all your tasks with `list`
Lists out all the tasks that have been added to your task list thus far.

Format: `list`
- All your tasks will be displayed in the format `INDEX) [TASK_TYPE][STATUS] NAME (DATE TIME)

### Marking and Unmakring tasks as complete/incomplete with `mark` and `unmark`
Marks the task at the index number entered as complete/incomplete with `[X]/[ ]` respectively.

Format:
- `mark INDEX`
	- Marks the task at index number `INDEX` as completed.
- `unmark INDEX`
	- Marks the task at index number `INDEX` as incomplete.
	
Example:

- `mark 1`
- `unmark 1`

### Deleting your tasks with `delete`
Deletes the task at the index number entered.

Format:
- `delete INDEX`
	- Deletes the task at index number `INDEX`.
	
Example:
- `delete 1`

### Find your task with `find`
Returns a list of tasks, along with their index number, that contains the text entered.

Format:
- `find KEYWORDS`
	- finds and lists out all tasks with `KEYWORDS` in their name.
	- Function is case-insensitive.
	
Example:
- `find tutorial`

### Clear all your tasks in the Duke with `reset`
Empties out the tasks in the Duke.

Format:
- `reset`
	- Empties out the tasks in the Duke.
	
Example:
- `reset`

### Exiting the application with `bye`
Format:
- `bye`
	- The application will save all your tasks locally and exit immediately.

Example:
- `bye`