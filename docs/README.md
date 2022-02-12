# User Guide

## Features

### Task management
Yeowoo organises tasks into three different categories:
`Todo`, `Deadline`, and `Event`.

Simply specify the type of task, its description, and relevant information into Yeowoo 
and she will save them into a list. The data stored in the list will 
remain even if you exit the application, so you do not have to worry about losing track
of your tasks!

Likewise, Yeowoo allows you to delete a task from the list when it is deemed 
unnecessary.

Refer to the `Usage` section for more details.

### Marking a task as completed

By default, tasks are marked as not done when added to the list of tasks.
An incomplete task is marked with `[ ]`.

With Yeowoo, you can mark (or unmark) a task as completed.
A completed task will be marked by `[X]`.


### Viewing of schedules

A distinctive feature of Yeowoo is that it allows you to view all 
the tasks (deadlines or events) you have on a particular date.

Refer to the `Usage` section for more details.


## Usage

### `list` - View the list of tasks

Yeowoo displays all tasks in the list.

### `delete <task number>` - Delete a task 

Yeowoo removes the specified task from the list.

Example of usage: `delete 2`

### `mark <task number>` - Describe action

Yeowoo marks the specified task as completed.

Example of usage: `mark 3`

### `unmark <task number>` - Describe action

Yeowoo marks the specified task as not completed.

Example of usage: `unmark 2`

### `todo <description>` - Add a task of type ToDo

Yeowoo adds the `ToDo` task to the list.

Example of usage: `todo borrow book`

### `deadline <description> /by <Date> <Time>` - Add a task of type Deadline

Yeowoo adds the `Deadline` task to the list.

Example of usage:

`deadline submit CS2103 IP submission /by 2022-02-18 2pm`

<b>Note: Input for date must be in the following format</b> `YYYY-MM-DD`

### `event <description> /at <Date> <Time>` - Add a task of type Event

Yeowoo adds the `Event` task to the list.

`event 5km Marathon /at 2022-09-03 1800`

<b>Note: Input for date must be in the following format</b> `YYYY-MM-DD`

### `schedule <date>` - View schedule for a date

Yeowoo displays the lists of tasks due or happening on the given date.

Example of usage: `schedule 2022-02-18`

<b>Note: Input for date must be in the following format</b> `YYYY-MM-DD`

### `bye` - Exit the application

Yeowoo displays a goodbye message and application will terminate after 3 seconds.