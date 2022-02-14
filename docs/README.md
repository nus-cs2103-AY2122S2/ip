# User Guide

## Features 

### Add tasks

Tell JetBot your tasks with `todo`, `deadline` and `event`.

### Mark

Mark your selected tasks as done when you've finished with them.

### List

List the tasks that you have added with the bot.

### Delete

Delete a task that you have added with the bot.

### Find

Search for a particular task that you have added with the bot.

### Duplicate detection

If a task has already been previously added, any attempts to add a task with the same type, description and date/time will fail.

## Usage

### `todo` - Adds a todo task to your list

Adds a todo task to your list.

Example of usage: 

`todo CS2103T UG`

Expected outcome:

The todo will be added and results can be seen with `list`.

```
Got it. I've added this task:
[T][ ] CS2103T UG
Now you have 1 task(s) in the list.
```

### `deadline` - Adds a deadline task to your list

Adds a deadline task to your list.

Example of usage:

`deadline CS2103T UG /by 2022-02-18 2359`

Expected outcome:

The deadline will be added and results can be seen with `list`.

```
Got it. I've added this task:
[D][ ] CS2103T UG (by: Feb-18-2022 2359)
Now you have 2 task(s) in the list.
```

### `event` - Adds an event task to your list

Adds an event task to your list.

Example of usage:

`event CS2103T Lecture /at 2022-02-18 1400`

Expected outcome:

The task will be added to list and results can be seen with `list`.

```
Got it. I've added this task:
[E][ ] CS2103T Lecture (at: Feb-18-2022 1400)
Now you have 3 task(s) in the list.
```

### `list` - Lists all tasks that you have added

Lists all tasks that you have added.

Example of usage:

`list`

Expected outcome:

A list of tasks that have been added.

```
 1.[T][ ] CS2103T UG
 2.[D][ ] CS2103T UG (by: Feb-18-2022 2359)
 3.[E][ ] CS2103T Lecture (at: Feb-18-2022 1400)
```
### `delete` - Deletes a task from your list

Deletes a task from your list.

Example of usage:

`delete 1`

Expected outcome:

The first task on the list is deleted.

```
No problem, I've deleted the following task:
[T][ ] CS2103T UG
There are now 2 task(s) remaining.
```

### `mark` - Marks a task as done

Marks a task as done.

Example of usage:

`mark 1`

Expected outcome:

Task will be marked as done.

```
Well done! I've marked this task as done:

[D][X] CS2103T UG (by: Feb-18-2022 2359)
```

### `unmark` - Unmarks a task

Unmark a task that was previously marked as done.

Example of usage:

`unmark 1`

Expected outcome:

Task will be unmarked.

```
No problem, I've marked this task as undone:

[D][] CS2103T UG (by: Feb-18-2022 2359)
```

### `find` - Finds a task based on keyword

Finds a task based on the keyword provided.

Example of usage:

`find Lecture`

Expected outcome:

All tasks that have `Lecture` in the description will be displayed.

```
Here are the matching tasks in your list:
2.[E][ ] CS2103T Lecture (at: Feb-18-2022 1400)
```
