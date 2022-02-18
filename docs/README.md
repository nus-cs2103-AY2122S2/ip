# User Guide

## Features 

### Create tasks

Jeff allows the tracking of 3 types of tasks:
- Todos: Task with no deadline.
- Deadlines: Task with a deadline.
- Events: Task with a specific timeframe.

### List tasks

Shows all tasks currently tracked by Jeff.

### Find tasks

Jeff allows the user to search through all the tasks, returning only
the ones that matches a given keyword.

### Mark and Unmark task

Jeff can keep track of what task are marked as done or not done.

### Delete task

Jeff can "forget" any task that the user doesn't want to track anymore.

### Save tasks

Jeff automatically saves any changes made to it's memory.

### Storing of notes

Jeff also allows store of short notes to prevent the user from forgetting
the important things.

## Usage

### `todo` - Adds a ToDo task

Adds a ToDo task for Jeff to keep track.

Example of usage: 

`todo homework`

Expected outcome:

Jeff would respond with a confirmation message, stating the task added and the
amount of task tracked by Jeff.

```
Got it. I've added this task:
	[T][ ] homework
Now you have 1 task(s) in the list.
```
### `deadline` - Adds a Deadline task

Adds a Deadline task for Jeff to keep track.

Example of usage: 

`deadline project meeting /by 20-02-2020 1800`

Expected outcome:

Jeff would respond with a confirmation message, stating the task added and the
amount of task tracked by Jeff.

```
Got it. I've added this task:
	[D][ ] project meeting (by: Feb 20 2022 06:00 pm)
Now you have 2 task(s) in the list.
```
### `event` - Adds a Event task

Adds a Event task for Jeff to keep track.

Example of usage: 

`event friend's concert /at 15-02-2020 2100`

Expected outcome:

Jeff would respond with a confirmation message, stating the task added and the
amount of task tracked by Jeff.

```
Got it. I've added this task:
	[E][ ] friend's concert (by: Feb 15 2022 09:00 pm)
Now you have 3 task(s) in the list.
```
### `list` - List out all the tasks

Show all the tasks currently being tracked by Jeff.

Example of usage: 

`list`

Expected outcome:

Jeff list out all the task indexed by the order of creation. 

```
	1.[T][ ] homework
	2.[D][ ] project meeting (by: Feb 20 2022 06:00 pm)
	3.[E][ ] friend's concert (by: Feb 15 2022 09:00 pm)
```
### `find` - Find matching tasks

Return all tasks matching a keyword given by the user.

Example of usage: 

`find project`

Expected outcome:

Jeff list out all the task with the keyword 'project' in it. 

```
Here are the matching tasks in your list:
	1.[D][ ] project meeting (by: Feb 20 2022 06:00 pm)
```
### `mark` - Mark a specific task as done

Mark a specific task as done based on the index given.

Example of usage: 

`mark 2`

Expected outcome:

Jeff would mark a 'X' on the second box. 

```
Nice! I've marked this task as done:
	[D][X] project meeting (by: Feb 20 2022 06:00 pm)
```
### `unmark` - Unmark a specific task as not done

Unmark a specific task as not done based on the index given.

Example of usage: 

`unmark 2`

Expected outcome:

Jeff would mark a ' ' on the second box. 

```
OK, I've marked this task as not done yet:
	[D][ ] project meeting (by: Feb 20 2022 06:00 pm)
```
### `delete` - Delete a specific task

Delete a specific task as not done based on the index given.

Example of usage: 

`delete 2`

Expected outcome:

Jeff would 'forget' the task entirely. 

```
Noted. I've removed this task:
	[D][ ] project meeting (by: Feb 20 2022 06:00 pm)
Now you have 2 task(s) in the list.
```
### `note` - Add to note

Add user input into note.

Example of usage: 

`note 2 + 2 = 4`

Expected outcome:

Jeff would respond with a confirmation message, stating the note that was added.

```
Added this note for you:
	2 + 2 = 4
```
### `note list` - List out all the notes

Show all the notes that Jeff is currently 'holding'.

Example of usage: 

`note list`

Expected outcome:

Jeff would respond all the notes he have, a new line for each note.

```
These are your notes:
	2 + 2 = 4
```
### `note clear` - Clear all the notes

Delete all the notes Jeff is 'holding'.

Example of usage: 

`note clear`

Expected outcome:

Jeff would delete all the note he has.

```
I've cleared all your notes for you!
```
### `bye` - Exit the Jeff program

Ends the Jeff program.

Example of usage: 

`bye`

Expected outcome:

Jeff would return a goodbye message, and promptly closes the program in 2 seconds.

```
Bye, my name is Jeff
```