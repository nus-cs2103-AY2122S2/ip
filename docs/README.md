# User Guide
MultiTask is a desktop app for **managing multiple tasks**, **optimized for use via a Command Line Interface** (CLI)
while still having the benefits of a Graphical User Interface (GUI). 
If you need a lightweight tasks manager, MultiTask empowers you to multitask efficiently than traditional todo app.

<img src = "Ui.png" width = "350" alt="Unable to load image! Try again later.">

## Table of Contents
  - [Quick Start](#quick-start)
  - [Summary of feature commands](#summary-of-feature-commands)
  - [Features](#features)
    - [Adding a Task : `todo`, `deadline` and `event`](#adding-a-task--todo-deadline-and-event)
    - [Deleting a Task : `delete`](#deleting-a-task--delete)
    - [Finding a Task : `find`](#finding-a-task--find)
    - [Listing all Tasks : `list`](#listing-all-tasks--list)
    - [Marking and Unmarking a Task : `mark` and `unmark`](#marking-and-unmarking-a-task--mark-and-unmark)
    - [Sorting tasks based on task type : `sort`](#sorting-tasks-based-on-task-type--sort)
    - [Filtering tasks based on task type : `filter`](#filtering-tasks-based-on-task-type--filter)
    - [Exiting and Saving : `bye`](#exiting-and-saving--bye)

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest MultiTask from [here](https://github.com/clement0010/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your MultiTask.
4. Double-click the file to start the app. The GUI similar to the screenshot above should appear in a few seconds.
5. Type the command in the command box and press Enter to execute it.
6. Refer to the [Features](#features) below for details of each command.

## Summary of feature commands
The table below shows a quick summary of the available

| Action           | Format, Examples                                                                                         |
|------------------|----------------------------------------------------------------------------------------------------------|
| **Add todo**     | `todo NAME_OF_TODO` <br> e.g., `todo Do CS2103T iP`                                                      |
| **Add deadline** | `deadline NAME_OF_DEADLINE /by DUE_DATE` <br> e.g., `deadline CS2103T iP Submission /by 18/02/2022 2359` |
| **Add event**    | `event NAME_OF_DEADLINE /at EVENT_DATE` <br> e.g., `event CS2103T Weekly Meeting /at 20/02/2022 1500`    |
| **List**         | `list`                                                                                                   |
| **Delete**       | `delete INDEX`<br> e.g., `delete 1 `                                                                     |
| **Mark**         | `mark INDEX`<br> e.g., `mark 1`                                                                          |
| **Unmark**       | `unmark INDEX`<br> e.g., `unmark 2`                                                                      |
| **Sort**         | `sort TYPE_OF_TASK` <br> e.g., `sort event`                                                              |
| **Find**         | `find KEYWORD`<br> e.g., `find CS2103T`                                                                  |
| **Filter**       | `filter TYPE_OF_TASK` <br> e.g., `filter deadline`                                                       |
| **Bye**          | `bye`                                                                                                    |



## Features
- Words in `UPPER_CASE` are the parameters to be supplied by the user.
  e.g. in `todo NAME_OF_TODO`, `NAME_OF_TODO` is a parameter which can be used as `todo submit homework`.
- Default date formatting in MultiTask is `dd/MM/yyyy HHmm`. e.g. `18/02/2022 2359`
- All `Add`, `Mark`, `Unmark`, `Delete` operations will save the latest data.

### Adding a Task : `todo`, `deadline` and `event`

Adds a new Task to MultiTask. A Task can be a `todo`, `deadline`, or `event` depending on which command you use.

Format:
-`todo NAME_OF_TODO` creates a todo in the task list.
-`deadline NAME_OF_DEADLINE /by DUE_DATE` creates a deadline with due date in the task list.
-`event NAME_OF_EVENT /at EVENT_DATE` creates an event with event date in the task list.

💡 **Note:** Date specified must be in `dd/MM/yyyy HHmm` format

Example:
- `todo Create jar file`
- `deadline CS2103T iP Submission /by 18/02/2022 2359`
- `event CS2103T Weekly Meeting /at 20/02/2022 1500`

### Deleting a Task : `delete`

Deletes the specified Task from MultiTask task list.

Format: `delete INDEX`
- Deletes the task at with the `INDEX`.
- The index refers to the numbering of the task. If you happen to forget the numbering, simply type `list` to retrieve all your tasks with their respective number.
- The index must be a **positive integer** and smaller or equal than the number of tasks available in MultiTask.

Examples:
* `delete 1` deletes the first task in the task list.

### Finding a Task : `find`

Find tasks with title containing the keyword.

Format: `find KEYWORD`
- The search will search for any task title that has `KEYWORD` as substring. 
e.g. `find iP` will match task title with `iP`.
- The search keyword is case-sensitive. e.g. `find iP` will not match task with `ip`

Examples:
-`find iP`

### Listing all Tasks : `list`

Displays a list of all tasks from MultiTask's task list.

Format: `list`

### Marking and Unmarking a Task : `mark` and `unmark`

Marks or unmarks a task in MultiTask's task list.

Format: `mark INDEX` or `unmark INDEX`
- Marks or unmarks the task with the `INDEX`.
- The index refers to the numbering of the task. If you happen to forget the numbering, simply type `list` to retrieve all your tasks with their respective number.
- The index must be a **positive integer** and smaller or equal than the number of tasks available in MultiTask.

Example:
- `mark 1` mark the first task in the task list as done.
- `unmark 2` unmark the second task in the task list as not done.

### Sorting tasks based on task type : `sort`

Sorts task in MultiTask's task list based on the task type supplied and order it chronologically.

Format: 
- `sort event` Sorts all events chronologically.
- `sort deadline` Sorts all deadlines chronologically.

💡 **Note**
- The numbering displayed here does not correspond to the actual numbering. 
Thus, be careful not to use the number for `mark`, `unmark` and `delete` command.
- The accepted task type here is `event` and `deadline` as they have date properties.


### Filtering tasks based on task type : `filter`

Filters task in MultiTask's task list based on the task type supplied.

Format: 
- `filter todo` Filters all tasks to display all todos.
- `filter event` Filters all tasks to display all events.
- `filter deadline` Filters all tasks to display all deadlines.

💡 **Note**
- The numbering displayed here does not correspond to the actual numbering. 
Thus, be careful not to use the number for `mark`, `unmark` and `delete` command.


### Exiting and Saving : `bye`

Disables the input field and send button and exits the program after 3 seconds. Saves the data into `data/tasks.txt` before exiting the program.

**Caution:** If you close the program or window without `bye` command, your changes will not be saved.

Format: `bye`

Expected outcome:
* Charizard will roar at you and invite you to burn more tasks next time.
* Charizard will inform you that the app will be closing in 3 seconds, and disable the input and send button.



