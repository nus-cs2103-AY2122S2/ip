# User Guide
Duke O.o is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Duke O.o can get your task management tasks done faster than traditional GUI apps.
* [Quick start](#quick-start)
* [Features](#features)
    * [Adding an Event task: `event`](#adding-an-event-task-event)
    * [Adding a Deadline task: `deadline`](#adding-a-deadline-task-deadline)
    * [Adding a ToDo task: `todo`](#adding-a-todo-task-todo)
    * [Listing all tasks: `list`](#listing-all-tasks-list)
    * [Marking a task as complete: `mark`](#marking-a-task-as-complete-mark)
    * [Marking a task as incomplete: `unmark`](#marking-a-task-as-incomplete-unmark)
    * [Deleting a  task: `delete`](#deleting-a-task-delete)
    * [Clearing all entries: `clear`](#clearing-all-entries-clear)
    * [Locating tasks by keyword: `find`](#locating-tasks-by-keyword-find)
    * [Viewing help window: `help`](#viewing-help-window-help)
    * [Exiting the program: `exit`](#exiting-the-program-exit)
    * [Saving the data](#saving-the-data)
    * [Editing the data file](#editing-the-data-file)

## Quick start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `duke.jar` from here.
3. Copy the file to the folder you want to use as the _home folder_ for task list.
4. Double-click the file to start the app. The GUI similar should appear in a few seconds.
5. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window.  
   Some example commands you can try:
    * `list` : Lists all entries.
    * `todo/Finish up Math homework` : Adds a ToDo task named `Finish up Math homework` to  task list.
    * `delete/3` : Deletes the 3rd contact shown in the current list.
    * `clear` : Deletes all task entries.
    * `exit` : Exits the app.
6. Refer to the [Features](#features) below for details of each command.

## Features
:exclamation:Notes about the command format:
- The first word in every command is not case sensitive.  
  e.g. in `todo/Finish Math homework` can be written as `TODO/Finish Math homework` as well
- Do not use `/` in the task names as it will be considered as an invalid input.  
  e.g. in `todo/Finish Math/homework` will be interpreted as an invalid input.
- One word commands must be input without any extra parameters at the back. (such as `help`, `list`, `exit` and `clear`)  
  This applies to whitespaces as well.
- All instances of `EVENT_DATE` and 'DEADLINE_DATE' will follow the format of `DDMMYYYY`.
- All instances of `EVENT_TIME` and 'DEADLINE_TIME' will follow the format of `HHMM`, in the 24 hour format.
- All instances of `INDEX` refers to the index number of the task as shown on the task list.  
  If any number other than a valid index is given, the command will not be executed.

### Adding an Event task: `event`

Adds an event task to the task list.

Format: `event/EVENT_NAME/EVENT_DATE/EVENT_TIME`

Examples:
+ `event/Lecture/16022022/1000`
  Creates a event task named `Lecture` that is due on 16th February 2022, 10:00am.
+ `event/Webinar/29052022/1800`
  Creates a event task named `Webinar` that is due on 29th May 2022, 6.00pm.

### Adding a Deadline task: `deadline`

Adds an deadline task to the task list.

Format: `event/DEADLINE_NAME/DEADLINE_DATE/DEADLINE_TIME`

Examples:
+ `deadline/Math homework/05082022/2359`
  Creates a deadline task named `Math homework` that is due on 5th August 2022, 11:59pm.
+ `deadline/Science project/13102022/1159`
  Creates a deadline task named `Science project` that is due on 13th October 2022, 11:59am.

### Adding a ToDo task: `todo`

Adds an todo task to the task list.

Format: `todo/TODO_NAME`

Examples:
+ `todo/Organize bookshelves`
  Creates a todo task named `Organize bookshelves`.
+ `todo/Return book to the library`
  Creates a todo task named `Return book to the library`.

### Listing all tasks: `list`

Lists all the task in the task list.

Format: `list`

### Marking a task as complete: `mark`

Marks a task as complete.

Format: `mark/INDEX`

Examples:
+ `mark/3`
  Marks the task at index 3 as shown on the tasklist as complete.

### Marking a task as incomplete: `unmark`

Marks a task as complete.

Format: `unmark/INDEX`

Examples:
+ `unmark/5`
  Marks the task at index 5 as shown on the tasklist as incomplete.

### Deleting a task: `delete`

Deletes a task from the task list.

Format: `delete/INDEX`

Examples:
+ `delete/1`
  Deletes the task at index 1 as shown on the tasklist.

### Clearing all entries: `clear`

Clears the entire task list.

Format: `clear`

### Locating tasks by keyword: `find`

Locates all the tasks in the task list that contains the given keyword

Format: `find/KEYWORD`
* Take note that the `KEYWORD` is strictly case-sensitive  
  e.g. `home` will match with `home` but `home` will not match with `HOME`

Examples:
+ `find/return`
  Returns a list of tasks from the task list containing the keyword `return`.

### Viewing help window: `help`

Displays a help window with sample usages of all the available commands.

Format: `help`

### Exiting the program: `exit`

Exits the program.

Format: `exit`

### Saving the data

There is no need to save the data manually as data saving occurs whenever changes to the data are made.

### Editing the data file

The data.txt file can be found within the data folder after running Duke O.o at least once.  
The first line shows the number of tasks in the saved file, and each subsequent line represents a different task in the format of  
`TASK_PREFIX/TASK_COMPLETION_STATE/TASK_DATE/TASK_TIME`.  
`TASK_PREFIX` can be either `T`, `D` or `E` to represent todo, deadline and event tasks respectively. (They must be capitalized)  
'TASK_COMPLETION_STATE' can be either `T` or `F` depending on the completion state of the task. (They must be capitalized)  
'TASK_DATE' is in the DDMMYYYY format and `TASK_TIME` is in the HHMM 24hr format.  