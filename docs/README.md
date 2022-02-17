# Duke User Guide

<img width="512" alt="Ui" src="https://user-images.githubusercontent.com/74252295/154521574-0579350d-f5ad-44aa-a039-6c542d9e6077.png">
## Features

### Viewing Tasks
Duke allows users to keep track of all their tasks in a task list.

### Adding Tasks
Users are able to add 3 different types of tasks: Todo, Deadline & Event tasks.

### Deleting Tasks
Users are free to delete tasks.

### Marking & Unmarking Tasks
Users are able to mark tasks as complete when done. Otherwise, the task remain unmarked.

### Finding Tasks
Users are able to search for tasks.



---

## Usage

### `list` - View all tasks

Generates a list of current tasks the user has saved.

Example of usage:

`list`

Expected outcome:

Duke reads the saved task list in the `data` folder and outputs a list of tasks.

```
Here are your tasks:
1. [T][ ] groceries
2. [E][X] project meeting (at: Feb 15 2022 13:00 pm)
```


### `todo` - Add task: Todo

Creates a Todo task, a task which only has a name and no date and time associated with it.

Example of usage:

`todo bake`

Expected outcome:

Duke saves the Todo task and lets the user know that the task has been read and stored.

```
Got it. I've added this task:
[T][ ] bake
Now you have 3 tasks in the lists
```


### `deadline` - Add task: Deadline

Creates a Deadline task, a task which has a name and a date and/or time associated with it.

Example of usage:

`deadline hw /by 21-02-2022 12:22`

Expected outcome:

Duke saves the Deadline task and lets the user know that the task has been read and stored.

```
Got it. I've added this:
[D][ ] hw (by: Dec 21 2022 12:22 pm)
Now you have 4 tasks in the list
```


### `event` - Add task: Event

Creates a Event task, a task which has a name and a date and/or time associated with it.

Example of usage:

`event meeting /at 21-12-2022 12:22`

Expected outcome:

Duke saves the Event task and lets the user know that the task has stored as well as the total number of tasks currently saved.

```
Got it. I've added this:
[E][ ] meeting (at: Dec 21 2022 12:22 pm)
Now you have 5 tasks in the list
```


### `delete` - Deletes a task

Duke deletes a task (specified via task index as seen from `list`) from memory.

Example of usage:

`delete 2`

Expected outcome:

Duke deletes a task with index 1 from task list saved in `data` folder.

```
Noted. I've removed this task:
[E][X] project meeting (at: Feb 15 2022 13:00 pm)
Now you have 4 tasks in the list.
```


### `mark` - Marks a task

Duke marks a task as completed with an 'X'.

Example usage:

`mark 1`

Expected outcome:

Duke marks a task with index 1 with an 'X' from task list saved in `data` folder.

```
Nice! I've marked this task as done:
[T][X] groceries
```


### `unmark`- Unmarks a task

Duke unmarks a task as incomplete with a blank.

Example usage:

`unmark 1`

Expected outcome:

Duke unmarks a task with index 1 with an ' ' from task list saved in `data` folder.

```
OK, I've marked this task as not done yet:
[T][ ] groceries
```


### `bye` - Exits Duke

Duke shuts down and rest until the user next calls Duke to work!

Example usage:

`bye`

Expected outcome:

Duke bids the user farewell, retains the task list the user last saves and sleeps for the timebeing.

```
Bye. Hope to see you again soon!
```

