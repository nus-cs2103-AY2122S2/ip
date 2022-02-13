# User Guide

## Ron Chatbot
>“Sometimes our stop-doing list needs to be bigger than our to-do list.” - Patti Digh ([source](https://www.goodreads.com/work/quotes/13355889-four-word-self-help-simple-wisdom-for-complex-lives))

Ron is a chatbot personalised and improved from Duke with new built-in features for task tracking.

## Product View

![ProductSS](./Ui.png)

## Features 
Event types supported:
- Todo
- Deadline
- Event

### Feature-Todo

Addition of Todos into Task List (task to be completed with no fixed deadline).

### Feature-Deadline

Addition of Deadlines into Task List (task to be completed by a fixed date & time).

### Feature-Event

Addition of Events into Task List (events on a fixed date).

## Usage

### `todo` - Add Todo into Task List

Example of usage: 

`todo (argument)`

Expected outcome:

```
Task added!
[T][] (argument)
```

### `deadline` - Add Deadline into Task List

Example of usage:

`deadline (argument) /by (dd/mm/yyyy) (hhmm)`

Expected outcome:

```
Task added!
[D][] (argument (by: MMM dd yyyy hh.mm a))
```

### `event` - Add Event into Task List

Example of usage:

`event (argument) /at (dd/mm/yyyy)`

Expected outcome:

```
Task added!
[E][] (argument (by: MMM dd yyyy))
```

### `deadline` - Add Deadline into Task List

Example of usage:

`deadline (argument) /by (dd/mm/yyyy) (hhmm)`

Expected outcome:

```
Task added!
[D][] (argument (by: MMM dd yyyy hh.mm a))
```
### `list` - Lists all tasks in Task List

Example of usage:

`list`

Expected outcome:

```
The tasks on your list are as follows:
1. [][] (task description)
.
.
.
```

### `mark/unmark` - Marks/Unmarks a task from Task List

Example of usage:

Mark a task from a given index in the task list.

`mark/unmark (event index)`

Expected outcome:

Task will be marked/unmarked.

`mark`
```
Good job! The following task is marked as done: 
[][X] (task description)
```
`unmark`
```
No problem! The following task is marked as not done yet:
[][] (task description)
```

### `delete` - Delete a task from Task List

Example of usage:

Delete a task from a given index in the task list.

`delete (event index)`

Expected outcome:

Task removed from task list.

```
OK, the following task is removed:
[][] (task description)
There are (no. of tasks left) task(s) in the list. 
```

### `find` - Finds a task from Task List

Example of usage:

Finds a task matching the argument in the task list.

`find (argument)`

Expected outcome:

```
Here are some tasks that match your request:
1. [][] (task description)
.
.
.
```