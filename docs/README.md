# User Guide

## Features 

### Add todo: `todo`

Adds a todo to the task list.

Format: `todo TODO_NAME`

Examples:

- `todo read book`<br><br>


### Add event: `event`

Adds an event to the task list.

Format: `todo EVENT_NAME /at EVENT_DATE`

- EVENT_DATE should be in **YYYY-MM-DD** format.

Examples:

- `event attend concert /at 2022-03-03`<br><br>


### Add deadline: `deadline`

Adds a deadline to the task list.

Format: `todo DEADLINE_NAME /by DEADLINE_DATE`

- DEADLINE_DATE should be in **YYYY-MM-DD** format.

Examples:

- `deadline return book /by 2022-03-04`<br><br>


### Delete task: `delete`

Deletes a task in the list.

Format: `delete TASK_INDEX`

- TASK_INDEX is the index of the task as seen in `list`
- TASK_INDEX **must be an valid index in the task list**<br><br>


### View task list: `list`

Shows the task list.

Format: `list`<br><br>


### Mark task: `mark`

Marks a task in the task list as done.

Format: `mark TASK_INDEX`

- TASK_INDEX is the index of the task as seen in `list`
- TASK_INDEX **must be an valid index in the task list**<br><br>


### Unmark task: `unmark`

Marks a task in the task list as undone.

Format: `unmark TASK_INDEX`

- TASK_INDEX is the index of the task as seen in `list`
- TASK_INDEX **must be an valid index in the task list**<br><br>


### Find task: `find`

Finds a task in the task list with names that contains the keyword.

Format: `find KEYWORD`

- `find` will return tasks as long as the keyword is within the task name.

Examples:

- `find book` shows `todo read book` and `deadline return book by 4th March 2022`
- `find book` will also show `todo read book` and `deadline return book by 4th March 2022`<br><br>


### Update task: `update`

Updates a task in the task list.

Format: `update TASK_INDEX /name NEW_NAME` or `update TASK_INDEX /date NEW_DATE`

- `update` can only update dates of events and deadlines.

Examples:

- `update 1 /name read library book` will update `todo read book` to `todo read library book`
- `update 3 /date 2022-04-04` will update `deadline return book by 4th March 2022` to `deadline return book by 4th April 2022`

