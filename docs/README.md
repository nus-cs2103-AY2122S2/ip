# User Guide

---
## Features 
* Persistent task tracking
* Marking of tasks to show completion
* Quick task searching 
* CLI interface

### Feature - Persistent task tracking

Taskie automatically saves your tasks to the disk after you create them. 
No need to worry about losing your data!

### Feature - Task marking
Taskie allows you to mark task to show completion, use it as a shopping list
or to keep track of upcoming events!

---

## Usage

### `help` - Help Command

Returns some information about the various commands used by Taskie.

Example of usage: 

`help`

`help todo`

Expected outcome: 

```
To find out more about commands, please type "help {commandName}"
commandNames include: event, todo, deadline, find, mark, unmark, list, delete
```
---
### `todo` - Create a Todo Task

Creates a new todo task.

Example of usage:

`todo buy bread`

Expected outcome:

Description of the outcome.

```
Got it. I've added this task:
[T] [] buy bread
Now you have 1 tasks in the list.
```
---
### `deadline` - Create a Deadline Task

Creates a new deadline task. Input format `deadline {Task} /by {yyyy-mm-dd}`

Example of usage:

`deadline submit christmas report /by 2022-12-25`

Expected outcome:

```
Got it. I've added this task:
[D] [] submit christmas report (by: Dec 25 2022)
Now you have 2 tasks in the list.
```
---
### `event` - Create an Event Task

Creates a new event task. Input format `event {Task} /at {yyyy-mm-dd}`

Example of usage:

`event christmas dinner /at 2022-12-25`

Expected outcome:

```
Got it. I've added this task:
[E] [] christmas dinner (at: Dec 25 2022)
Now you have 3 tasks in the list.
```
---
### `mark` - Mark a Task

Marks a task as a specified index as completed. 
Index of a task can be found using the list command.

Example of usage:

`mark 1`

Expected outcome: Marks the first task in the current task list.

```
Nice! I've marked this task as done:
[T] [X] buy bread
```
---
### `unmark` - Unmark a Task

Marks a task as a specified index as not completed.
Index of a task can be found using the list command.

Example of usage:

`unmark 1`

Expected outcome: Unmarks the first task in the current task list.

```
OK! I've marked this task as not done yet:
[T] [] buy bread
```
---

### `delete` - Delete a Task

Deletes a task as a specified index.
Index of a task can be found using the list command.

Example of usage:

`delete 3`

Expected outcome: Deletes the third task in the current task list.

```
OK! I've deleted this task:
[E] [] christmas dinner (at: Dec 25 2022)
Now you have 2 tasks in the list.
```
---
### `list` - List Command

Returns the current saved list of taaks.

Example of usage:

`list`

Expected outcome: 

```
You Currently have 2 task in your list:
1. [T] [] buy bread
2. [D] [] submit christmas report (by: Dec 25 2022)
```
---
### `find` - Search Command

Returns the list of tasks that contains a specified string.

Example of usage:

`find bread`

Expected outcome: Return list of tasks that contain the string "bread", if no tasks match, will return an empty list.

```
Here are the matching tasks in your list:
1. [T] [] buy bread
```
---
### `bye` - Exit Command

Exits the program.

Example of usage:

`bye`

Expected outcome: Taskie will print out a goodbye message and automatically close the program after a few seconds.

```
Bye. Hope to see you again soon!
```
