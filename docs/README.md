# User Guide

## Features 

### Create tasks

Echo supports 3 types of tasks:

- ToDo: Task without a date or time.
- Deadline: Task to be completed by a specific date and time.
- Event: Task that occur at a specific date and time.

### List all tasks

Displays all tasks at a glance.

### Mark / unmark a task

Marks / umarks a task to update its status of completion.

### Delete a task

Deletes a task when the task is no longer required.

### Search for tasks

Searches for tasks that matches a keyword.

### Save tasks

Save is performed automatically whenever there are changes.

## Usage

### `list`: Displays all tasks

Displays all tasks in Echo.

Format:
`list`

### `todo`: Adds a ToDo task

Adds a ToDo task to Echo.

Format:
`todo <description>`

### `deadline`: Adds a Deadline task

Adds a Deadline task to Echo.

Format:
`deadline <description> /by <date_time>`

### `event`: Adds an Event task

Adds an Event task to Echo

Format: `event <description> /at <date_time>`

### `mark`: Marks a task as completed

Marks a specific task as completed.

Format: `mark <task_number>`

### `unmark`: Unmarks a task

Unmarks a specific task as uncompleted.

Format: `unmark <task_number>`

### `delete`: Deletes a task

Deletes a specific task.

Format: `delete <task_number>`

### `find`: Find tasks matching a keyword

Displays all tasks matching a keyword.

Format: `find <description>`

### `bye`: Exits Echo

Exits Echo :(. Goodbye!

Format: `bye`