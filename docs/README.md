# User Guide for Duke 

This is the user guide of Duke.

## Feature 1: Add a Task 

You can choose to add one of the following tasks to your list.

- todo 
- event 
- deadline 

## Feature 2: Delete a task

Delete a specific task easily. 

## Feature 3: Mark or Unmark a task

Mark a specific task as complete or incomplete! 

## Feature 4: Find a task 

You can find a specific task in your list using keywords.

## Add a Task

### `todo [TaskName]` 
- Adds a todo task to the list.

Example of usage:
`todo sleep`


### `deadline [TaskName]/by [YYYY-MM-DD]` 
- Adds a deadline task to the list.

Example of usage:
`deadline CS2040 assignment/by 2020-12-12`


### `event [TaskName]/at [YYYY-MM-DD]` 
- Adds a event task

Example of usage:
`event CS2040 tutorial/at 2020-10-31`

## Delete a task

### `delete [TaskID]`
- Delete task that is numbered as `[TaskID]` of the list

Example of usage:
`delete 1`

## Mark or Unmark a task

### `mark [TaskID]` 
- Mark the task that is numbered as `[TaskID]` as completed.

Example of usage:
`mark 2`

### `unmark [TaskID]`

- Unmark the task that is numbered as `[TaskID]` as incomplete.

Example of usage:
`unmark 3`

## Find a task

### `find [Keyword]` 
- Finds the task that contains the `[Keyword]` 

Example of usage:
`find assignment`

## List all tasks

### `list` 
- List all your tasks

Example of usage:
`list`
