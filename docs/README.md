# User Guide

Welcome to the user guide for Duke v2, your friendly text command based task manager

## Features 

1. `list`: Displays the list of all tasks
2. Adds a task to the list, supporting 3 types:
   1. `todo`: Adds a **TODO** with a description
   2. `event`: Adds an **Event** with a description and time which it occurs
   3. `deadline`: Adds an **Deadline** with a description and date to be completed by
3. `delete`: Deletes a specific task from the list
4. `mark`: Marks a task as done
5. `unmark`: Marks a task as undone
6. `find`: Find tasks matching a keyword
7. `undo`: Undo the last non-trivial command

## Usage

### `list` - Show the list of all saved tasks

Example of usage: 

`list`

Expected outcome:

```
1.[T][ ] Apply for internships
2.[E][ ] Hiking trip (at: 08 Feb 7-11am) 
```
---

### `todo [description]` - Adds a TODO to task list

Parameters:
- description (required): Description of TODO to add

Example of usage:

`todo Buy toothpaste`

Expected outcome:

```
The following new task has been added:
[T][ ] Buy toothpaste
You now have 1 task(s)!
```
---

### `event [description] /at [time]` - Adds a TODO to task list

Parameters:
- description (required): Description of event to add
- time (required): Time at which event occurs

Example of usage:

`event Worlds quarterfinals /at 01 Jan 7-9pm`

Expected outcome:

```
The following new task has been added:
[E][ ] Worlds quarterfinals (at: 01 Jan 7-9pm)
You now have 2 task(s)!
```
---

### `deadline [description] /by [date]` - Adds a TODO to task list

Parameters:
- description (required): Description of deadline to add
- date (required): Date the deadline is due, in DD/MM/YYYY format

Example of usage:

`deadline Assignment 1 /by 01/01/2022`

Expected outcome:

```
The following new task has been added:
[D][ ] Assignment 1 (by: 01 Jan)
You now have 3 task(s)!
```
---

### `delete [index]` - Deletes a task from list

Parameters:
- index (required): Index of task to delete

Example of usage:

`delete 1`

Expected outcome:
```
Noted: The following task has been deleted:
[T][ ]Buy toothpaste
You now have 2 task(s)!
```
---

### `mark [index]`/`unmark [index]` - Marks a given task as complete/incomplete

Parameters:
- index (required): Index of task to mark as complete/incomplete

Example of usage:

`mark 1`

Expected outcome:
```
Congrats! This task was marked as done:
[E][X] Worlds quarterfinals (at: 01 Jan 7-9pm)
```
---

### `find [keyword]` - Find tasks matching given keyword

Parameters:
- keyword (required): Case-sensitive keyword to use to search for tasks

Example of usage:

`find Assignment`

Expected outcome:
```
Here are the tasks matching your searched terms
1. [D][ ] Assignment 1 (by: 01 Jan)
```
---

### `undo` - Undo the last non-trivial command (affecting tasks in list)

Example of usage:

`undo`

Expected outcome:
```
The following command was undone:
mark 1
```
---

### `bye` - End the session for Duke

Example of usage:

`bye`

Expected outcome:
```
Goodbye!
```
