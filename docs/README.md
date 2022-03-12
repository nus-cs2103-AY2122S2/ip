# ConnorBot User Guide

**ConnorBot v1.1.0** is a desktop application that manages your tasks and keeps track of them for you. It comes with a Graphical User Interface (GUI) that looks beautiful and organised, making your task management efficient and easy to do. 

## Features 

### Listing all tasks

* [`list`](#list---lists-all-tasks)

### Adding a task

* [`todo`](#todo---adds-a-todo-task)
* [`deadline`](#deadline---adds-a-deadline-task)
* [`event`](#event---adds-an-event-task)

### Deleting a task

* [`delete`](#delete---deletes-a-task)

### Clearing all tasks

* [`clear`](#clear---clears-all-tasks)

### Marking/Unmarking a task

* [`mark`](#mark---marks-a-task-as-completed)
* [`unmark`](#unmark---unmarks-a-task)

### Finding a task

* [`find`](#find---finds-a-task)

### Sorting tasks

* [`sort`](#sort---sorts-tasks)

### Exiting the program

* [`exit`](#exit---exits-the-program)

## Usage

### `list` - Lists all tasks

Shows a list of all current tasks recorded in ConnorBot.

Example of usage: 

`list`

Expected outcome:

```
Here are your current tasks:
    1. [T][ ] Call Mom
    2. [E][ ] Wedding Party (At: Mar 14 2022 8:30PM)
```

### `todo` - Adds a ToDo task

Adds a ToDo task into the task list.

Format:

`todo {task}`

Example of usage:

`todo Call Mom`

Expected outcome:

```
Alright, I've added a new task:
    [T][ ] Call Mom
You have 3 tasks.
```

### `deadline` - Adds a Deadline task

Adds a Deadline task into the task list.

Format:

`deadline {task} /by {DD-MM-YYYY HH:MM}`

Example of usage:

`deadline Assignment /by 15-05-2022 15:30`

Expected outcome:

```
Alright, I've added a new task:
    [D][ ] Assignment (By: May 15 2022 3:30PM)
You have 4 tasks.
```

### `event` - Adds an Event task

Adds a Event task into the task list.

Format:

`event {task} /at {DD-MM-YYYY HH:MM}`

Example of usage:

`event Birthday Party /at 13-04-2022 10:00`

Expected outcome:

```
Alright, I've added a new task:
    [E][ ] Birthday Party (At: Apr 13 2022 10:00AM)
You have 5 tasks.
```

### `delete` - Deletes a task

Deletes a task using its index from the task list.

Format:

`delete {index}`

Example of usage:

`delete 1`

Expected outcome:

```
Alright, I've deleted the task:
    [T][ ] Call Mom
```

### `clear` - Clears all tasks

Clears all tasks from the task list.

Example of usage:

`clear`

Expected outcome:

```
Poof! All your tasks are cleared!
```

### `mark` - Marks a task as completed

Marks a task as completed using its index in the task list.

Format:

`mark {index}`

Example of usage:

`mark 3`

Expected outcome:

```
Good job! I've marked the following task as completed:
    [D][X] Assignment (By: May 15 2022 3:30PM)
```

### `unmark` - Unmarks a task

Unmarks a task as uncompleted using its index in the task list.

Format:

`unmark {index}`

Example of usage:

`unmark 3`

Expected outcome:

```
Understood. I've unmarked the following task:
    [D][ ] Assignment (By: May 15 2022 3:30PM)
```

### `find` - Finds a task

Finds a task name in the task list by a keyword. All tasks that contain the keyword will be shown. The keyword can be more than one word or part of a word. The search is case-insensitive.

Format:

`find {keyword}`

Example of usage:

`find party`

Expected outcome:

```
Here are the matching tasks in your list:
    [E][ ] Wedding Party (At: Mar 14 2022 8:30PM)
    [E][ ] Birthday Party (At: Apr 13 2022 10:00AM)
```

### `sort` - Sorts tasks

Sorts tasks by the given argument. Currenetly there are two ways to sort:
* `type` sorts tasks by their type, ToDos, Deadlines and Events in that order.
* `time` sorts tasks by chronological order. Todos are put at the end since they do not have a specified time.

Format:

`sort type`
`sort time`

Example of usage:

`sort time`

Expected outcome:

```
Sorting tasks chronologically...
Here are your current tasks:
    1. [E][ ] Wedding Party (At: Mar 14 2022 8:30PM)
    2. [E][ ] Birthday Party (At: Apr 13 2022 10:00AM)
    3. [D][ ] Assignment (By: May 15 2022 3:30PM)
    4. [T][ ] Laundry	
```

### `exit` - Exits the program

Disables any further input, bids farewell to the user, and closes itself after 2 seconds.

Example of usage:

`exit`

Expected outcome:

```
Farewell. See you next time!
```
