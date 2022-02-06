# User Guide

## Features 

### Create tasks

Duke allows for the creation of 3 main types of tasks:

- ToDo: Tasks that do not have a date or time attached to it
- Deadline: Tasks that have to be completed by a given due date
- Event: Tasks that occur at a specific time and date

### Marking and unmarking a task

You can mark a task as "done" after completing it. Similarly, you can unmark a task as "undone".

### Deleting a task

You can delete a task if you no longer require it.

### List all tasks

You can view all your tasks at a glance.

### Searching for tasks by name/date

You can search for tasks that match a certain keyword or a date

### Saving tasks

Tasks are automatically saved after closing the application.

### Updating tasks

You can update and change a task's description.

## Usage

### `list` - Displays all tasks

Displays all tasks that have been created.

Format:

`list`

### `mark` - Mark a task as completed

Mark a selected task by its task number as "completed".

Format:

`mark <task-number>`

### `unmark` - Unmark a task

Unmark a selected task by its task number as "uncompleted".

Format:

`unmark <task-number>`

### `event` - Create a new Event task

Create a new Event task and adds it to the current list of tasks.

Format:

`event <event-description> /at <event-timestamp>`

### `deadline` - Create a new Deadline task

Create a new Deadline task and adds it to the current list of tasks.

Format:

`event <deadline-description> /by <due-date>`

### `todo` - Create a new ToDo task

Create a new ToDo task and adds it to the current list of tasks.

Format:

`todo <todo-description>`

### `delete` - Delete a task 

Delete a task given its task number.

Format:

`delete <task-number>`

### `find` - Displays tasks matching a description

Displays all tasks matching an input description string.

Format:

`find <description>`

### `date` - Displays tasks matching a date

Displays all Event and Deadline tasks matching an input date string.

Format:

`date <timestamp>`

### `update` - Update a task's description

Update the description of a task given its task number.

Format:

`update <task-number> <new-description>`

### `bye` - Exits the application

Exits the application an saves all tasks into a backup file.

Format:

`bye`

