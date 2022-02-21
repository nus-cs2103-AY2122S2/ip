# User Guide
Taskmaster is a **desktop application for managing tasks via a Command Line Interface** (CLI) while having the
benefits of a Graphical User Interface (GUI). Taskmaster is simple, user-friendly, and it aims to help record and
manage tasks easily.

## Features

## Saving of tasks
**Taskmaster** automatically saves a copy of user's tasks in the task list into a *data file*.
This *data file* could be found in the directory that **Taskmaster** was executed in, in the
`/data/Duke.txt` file.

## Loading of tasks
**Taskmaster** automatically loads the user's tasks from the *data file* located in the
`/data/Duke.txt` file during the startup.

## Create tasks
The 3 main types of tasks:

**ToDo Tasks**: Tasks that do not have any due dates or a specific date or time that it is happening on.

**Deadline Tasks**: Tasks that have to be completed by the due date.

**Event Tasks** : Tasks that occur at a specific time and date.

### Marking and unmarking a task
- Users can **mark** tasks as "done" after completing it. Likewise, users can also **unmark** a task as "undone".

### Deleting a task
- Users can delete tasks if they wish to.

### List all tasks
- Users can list out all their tasks.

### Searching for tasks by name
- Users search for tasks that match a certain keyword or a date.

### Saving tasks
- Tasks are saved after closing the application.

### Help
- Users can list out the list of commands.


# Usage
### `list` - Display the list of tasks in the task list
Display all the tasks that have been added into the task list.

Format:
`list`

### `mark` - Mark a specific task as completed

Mark a selected task by specifying its index number in the list as "completed".

Format:

`mark <task-index>`

### `unmark` - Unmark a task as incomplete

Unmark a selected task by specifying its task index number as "uncompleted".

Format:

`unmark <task-index>`

### `todo` - Adds a new **ToDo** task to the task list

Create a new ToDo task by specifying it's name or description and add it to the current list of tasks.

Format:

`todo <todo-name/description>`

### `deadline` - Adds a new **Deadline** task to the task list

Create a new Deadline task by specifying it's name or description, due date and adds it to the current list of tasks.

Format:

`deadline <deadline-name> /by <deadline>`

### `event` - Adds a new **Event** task to the task list

Create a new Event task by specifying it's name or description, time and date of occurrence and adds it to the current list of tasks.

Format:

`event <event-description> /at <event-timestamp>`


### `delete` - Delete a specific task based on the index specified

Delete a task based on the task index number specified.

Format:

`delete <task-index>`

### `find` - Displays tasks containing the specified keyword

Displays all tasks that contains the specified keyword *(Case-insensitive)*.

Format:

`find <keyword>`

### `Help` - Displays the list of commands

Displays the list of commands that are available in the program.

Format:

`help`


### `bye` - Exits the application

Exits the application and saves all tasks in the data file.

Format:

`bye`
