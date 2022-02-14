# User Guide

## Add tasks

### `todo`: Adds a ToDo task

Adds a ToDo task to Echo.

Format:
`todo <description>`
e.g.
- `todo read books`

### `deadline`: Adds a Deadline task

Adds a Deadline task to Echo.

Format:
`deadline <description> /by <date_time>`

e.g.
- `deadline do homework /by 2022-3-3 23:59`

### `event`: Adds an Event task

Adds an Event task to Echo

Format: `event <description> /at <date_time>`

e.g.
- `event read books /at 2022-2-2 9pm`

## List all tasks

###`list`Displays all tasks in Echo.

Format:
`list`

## Mark a task as done

###`mark`: Marks a task as completed


Format: `mark <task_number>`

e.g. `mark 1`(marks task 1 as done)
## Deletes a specific task.

###`delete`: Deletes a task

Format: `delete <task_number>`

e.g. `delete 1`(delete task 1)


##  Find tasks matching a keyword

###`find`:Displays all tasks matching a keyword.

Format: `find <description>`

e.g. `find book`(find any task containing keyword _**book**_ )

## Exit Duke

Format: `bye`

## View Schedule

###View all the tasks on a certain date

Format: `view schedule <yyyy-mm-dd>`

e.g. `view schedule 2022-2-2`