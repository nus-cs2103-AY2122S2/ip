# User Guide

![This is an image](C:\Users\User\Desktop\iP\docs\Ui.png)

Duke but with Animal Crossing animals :D
## Features 

### Keeping track of tasks

You can add, delete, mark tasks as done/undone.

### Text-based interface

Command and control Duke using just your keyboard!

### Different types of tasks
Different type of task for different occasion
1. To-Do
2. Event
3. Deadline


## Usage

### `todo` - Create a To-Do task

Creates a new to-do task and add it into your tasklist

Format: `todo TASK_NAME`

Example of usage: 

`todo Wash shoe`

Expected outcome:

Confirmation from Duke that the task has been added

```
Got it! I've added
    [T][]Wash shoe
```

### `deadline` - Create a Deadline task

Creates a new task with a deadline and add it into your tasklist

Format: `deadline TASK_NAME /by yyyy-mm-dd HHMM`

Example of usage:

`deadline Sleep /by 2021-12-30 2300`

Expected outcome:

Confirmation from Duke that the task has been added

```
Got it! I've added
    [D][]Sleep (by: DEC 30 2021 23:00)
```

### `event` - Create an Event task

Creates a new event with a timing and add it into your tasklist

Format: `event TASK_NAME /at yyyy-mm-dd HHMM`

Example of usage:

`event Go dance party /at 2021-12-31 2300`

Expected outcome:

Confirmation from Duke that the task has been added

```
Got it! I've added
    [E][]Go dance party (at: DEC 31 2021 23:00)
```

### `mark` - Marks a task as completed

Marks a task as completed 

Format: `mark INDEX`

Example of usage:

`mark 1`

Expected outcome:

Confirmation from Duke that the task has been marked

```
Congrats on completing:
    [E][X]Go dance party (at: DEC 31 2021 23:00)
```

### `unmark` - Marks a task as uncompleted

Marks a task as uncompleted

Format: `unmark INDEX`

Example of usage:

`unmark 1`

Expected outcome:

Confirmation from Duke that the task has been unmarked

```
Congrats on not completing:
    [E][]Go dance party (at: DEC 31 2021 23:00)
```

### `list` - See all task

List down all existing tasks

Format: `list`

Example of usage:

`list`

Expected outcome:

A list of all existing tasks

```
Here are the task:
    1.[D][X]Sleep (by: DEC 30 2021 23:00)
    2.[E][]Go dance party (at: DEC 31 2021 23:00)
```
<div markdown="span" class="alert alert-warning"> :bulb: **Tip**
The number on the left of the task is their respective index, which will be used for `mark`, `unmark` and `delete`
</div>

### `find` - Find all tasks with a given keyword

Find all tasks based on a given keyword given by the user

Format: `find KEYWORD`

Example of usage:

`find apple`

Expected outcome:

A list of all existing tasks with the keyword "apple"

```
Here are the task:
    1.[T][X]Bake apple cake
    2.[T][]Eat apple
```

### `delete` - Delete a task

Delete a task based on its index in the tasklist

Format: `delete INDEX`

Example of usage:

`delete 1`

Expected outcome:

Confirmation from Duke that the task has been deleted

```
Removed this task:
    1.[T][X]Bake apple cake
```