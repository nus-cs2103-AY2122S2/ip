# User Guide

## Features 

### Add

Tell Ari your tasks with `todo`, `deadline` and `event`

### Mark

Ari helps you keep track of your tasks

### View

View the tasks that you have that day

## Usage

### `todo` - Adds a todo task to your list

Adds a todo task to your list. Ari will keep track of it.

Example of usage: 

`todo CS2103T User Guide`

Expected outcome:

The task will be added to list and results can be seen with `list`.

```
Understood, I have added this task to your list
    [T] [ ] CS2103T User Guide
You have 1 task(s) currently
```

### `deadline` - Adds a task with deadline to your list

Adds task with a deadline to your list. Ari will keep track of it.

Example of usage:

`deadline CS2103T iP Submission 2022-02-17`

Expected outcome:

The task will be added to list and results can be seen with `list`.

```
Understood, I have added this task to your list
    [D] [ ] CS2103T iP Submission (by: 17 Feb 2022)
You have 2 task(s) currently
```

### `event` - Adds an event task to your list

Adds event task to your list. Ari will keep track of it.

Example of usage:

`event CS2103T Lecture 2022-02-18`

Expected outcome:

The task will be added to list and results can be seen with `list`.

```
Understood, I have added this task to your list
    [E] [ ] CS2103T Lecture (at: 18 Feb 2022)
You have 3 task(s) currently
```

### `list` - Lists all tasks that Ari has noted

Lists all tasks that Ari has noted. Shows all tasks according to when it was added.

Example of usage:

`list`

Expected outcome:

A list of tasks that have been added.

```
Dear Master, here is a list of items you have added:
 1. [T] [ ] CS2103T User Guide
 2. [D] [ ] CS2103T iP Submission (by: 17 Feb 2022)
 3. [E] [ ] CS2103T Lecture (at: 18 Feb 2022)
```
### `delete` - Deletes a task from your list

Deletes a task from list.

Example of usage:

`delete 3`

Expected outcome:

The third task that was added with be deleted.

```
Yes Master, I have removed this task:
    [E] [ ] CS2103T Lecture (at: 18 Feb 2022)
You have 2 task(s) currently
```

### `mark` - Marks a task as done

Marks a task as done. Ari will keep track of it.

Example of usage:

`mark 1`

Expected outcome:

Task will be marked as done.

```
Yes Master, I have marked this task as done:
    [T] [X] CS2103T User Guide
```

### `unmark` - Un-marks a task as done

Undo a marked a task as done. Ari will keep track of it.

Example of usage:

`unmark 1`

Expected outcome:

Task will be marked as done.

```
Yes Master, I have marked this task as not done yet:
    [T] [ ] CS2103T User Guide
```

### `find` - Finds a task based on keyword

Finds a task based on the keyword you provided.

Example of usage:

`find CS2103T`

Expected outcome:

All tasks that have `CS2103T` in the description will be displayed.

```
Yes Master, here are the tasks in your list that matches the keyword:
 1. [T] [X] CS2103T User Guide
 2. [D] [ ] CS2103T iP Submission (by: 17 Feb 2022)
```

### `view` - View tasks on a specified date

Finds the tasks on a specific date.

Example of usage:

`view 2022-02-17`

Expected outcome:

All tasks on `2022-02-17` will be displayed.

```
Yes Master, here are the tasks you have on 17 Feb 2022:
 1. [D] [ ] CS2103T iP Submission (by: 17 Feb 2022)
```