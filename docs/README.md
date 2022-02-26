# User Guide

## Features 

### Feature - Task Types

Tasks in the application is divided into three different categories:

- Todo
- Deadline
- Event

### Feature - Task Completion Status

Every task has a completion status completed or not yet completed.

Completed tasks are marked with a cross: `[X]`.

Incomplete tasks are not marked with a cross: `[X]`.

### Feature - Task Ordering

All tasks are stored in a a single list.

Every task in the list is ordered by natural integers.

The order is according to order of addition to the list, with the lowest in the ordering being the oldest added task.

To obtain the number of each task in the list and what to do with it, refer to `Usage`.

### Feature - Task Date Time

Specific tasks such as the Deadline and Event task types allow users to add the date-time for future reference.

To find out how to add date-times, refer to `Usage`.

### Feature - Deleting Tasks

A task can be deleted from the task list if it's index is provided.

To find out how to delete tasks, refer to `Usage`.

### Feature - Updating Task Description

The description of the tasks in the list can be updated without deleting and re-adding the task.

To find out how to update task descriptions, refer to `Usage`.

### Feature - Finding Matching Task Description

One is able to search through all tasks to find tasks that have task descriptions that match the search string.

To find out how to search task descriptions, refer to `Usage`.

### Feature - Saving Tasks

The list of tasks is saved in your computer automatically.

When the application is started, the saved task list will be loaded into the application, so that you may continue where you left off.

## Usage

### `list` - View all tasks currently in the task list

Calling this action will display a list of tasks that have been added

If the user is a first-time user, the list will be empty.

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list Master:
```

### `todo` - Adds a Todo task to the task list.

Calling this action will add a Todo task to the task list.

Todo tasks only contain description, have no date-time and are marked as incomplete by default.

Example of usage: 

`todo walk`

Expected outcome:

```
Noted Master. I'll add this task to your list.
    [T][ ] walk

There are now <insert number of tasks in list here> tasks in your list.
```

### `deadline` - Adds a Deadline task to the task list.

Calling this action will add a Deadline task to the task list.

Deadline tasks contain description, have a completion date-time and are marked as incomplete by default.

Deadlines must have a description and must have a date-time. They are seperated by `/by`

Example of usage: 

`deadline walk /by 2022-12-30 23:59:59`

Expected outcome:

```
Noted Master. I'll add this task to your list.
    [D][ ] walk (by: Dec 30 2022 2359)

There are now <insert number of tasks in list here> tasks in your list.
```

### `event` - Adds an Event task to the task list.

Calling this action will add an Event task to the task list.

Event tasks contain description, have a completion date-time and are marked as incomplete by default.

Events must have a description and must have a date-time. They are seperated by `/at`

Example of usage: 

`event walk /at 2022-12-30 23:59:59`

Expected outcome:

```
Noted Master. I'll add this task to your list.
    [E][ ] walk (by: Dec 30 2022 2359)

There are now <insert number of tasks in list here> tasks in your list.
```

### `mark` - Mark a task as completed.

Calling this action will mark a task at certain index of the task list as completed.

Example of usage:

`mark 1`

Expected outcome:

```
Congratulations Master, I've marked this task as done:
    [X] <insert task description and if applicable task date-time here>
```

### `unmark` - Mark a task as incomplete.

Calling this action will mark a task at certain index of the task list as incomplete.

Example of usage:

`unmark 1`

Expected outcome:

```
Very well Master, I've marked this task as not done yet:
    [] <insert task description and if applicable task date-time here>
```

### `delete` - Delete a task from the task list.

Calling this action will delete a task at certain index of the task list.

Example of usage:

`delete 1`

Expected outcome:

```
Noted Master. I'll remove this task from your list.
    [T][] <insert task description and if applicable task date-time here>
    
There are now <insert number of tasks in list here> tasks in your list.
```

### `update` - Update a task's description.

Calling this action will update a task's description at a certain index of the task list.

The update command must contain an index and a new description. They are seperated by `/description`.

Example of usage:

`update 1 /description walk more`

Expected outcome:

```
Noted Master. I'll update this task in your list. It is now:
    [T][ ] walk more
```
### `find` - Find all tasks that match a certain search string.

Calling this action will find all tasks with descriptions that match the search string the follow the `find` action.

Example of usage:

`find walk`

Expected outcome:

```
Here are the matching tasks in your list, Master:
1. [T][ ] walk more
```

### `bye` - Close Butler application

Calling this action will close the Butler application.

Example of usage:

`bye`

Expected outcome:

```
Farewell Master. Glad to be of service. Closing in 2s.
```




