# Bernie User Guide

## What is Bernie?
Bernie bot helps you to remember tasks! Track things to do, deadlines, and upcoming events.
Just type in the relevant words and Bernie will help you :)

![Bernie interface](Ui.png)

## Features 

### Create Tasks
There are 3 types of tasks that can be created:
1. `todo`
2. `deadline`
3. `event`

`todo` contains the description of the task. 

`deadline` contains the description of the task and the time by.

`event` contains the description of the task and the time it occurs.

### Mark or Unmark a task
You can `mark` a task as done or `unmark` a task as not done.

### List all tasks
You can `list` what are the tasks you've added, along with its details.

### Delete a task
You can `delete` tasks by the task number on the list.

### Find a task
You can `find` a task by a partial or full description.

### Remind
You can `remind` yourself of incomplete `deadline`.

### Exit
Type `bye`!

## Usage

### Summary

| Feature                             | Command                                 | 
|-------------------------------------|-----------------------------------------|
| 1. Create a `todo` task             | `todo <description>`                    |
| 2. Create a `deadline` task         | `deadline <description> /by YYYY-MM-DD` |
| 3. Create an `event` task           | `event <description> /at <time>`        |
| 4. `mark` a task as done            | `mark <taskNumber>`                     |
| 5. `unmark` a task as not done      | `unmark <taskNumber>`                   |
| 6. `list` all tasks created         | `list`                                  |
| 7. `delete` a task with task number | `delete <taskNumber>`                   |
| 8. `find` a task with description   | `find <description>`                    |
| 9. `remind` deadlines not done      | `remind`                                |
| 10. `bye` to exit program           | `bye`                                   |

### `todo` - Create a todo Task
Example of usage:

`todo <description>`

`todo borrow book`

Expected outcome:
```
Got ya. Added:
[T][ ] borrow book
You got 1 tasks waiting for ya!
```

### `deadline` - Create a deadline Task
Example of usage:

**Time for deadline must be in YYYY-MM-DD**

`deadline <description> /by YYYY-MM-DD`

`deadline return book /by 2022-01-28`

Expected outcome:
```
Got ya. Added:
[D][ ] return book (by: Jan 28 2022)
You got 2 tasks waiting for ya!
```

### `event` - Create an event Task
Example of usage:

Time is free to be whichever format you want

`event <description> /at <time>`

`event project meeting /at Mon 2-4pm`

Expected outcome:
```
Got ya. Added:
[E][ ] project meeting (at: Mon 2-4pm)
You got 3 tasks waiting for ya!
```

### `mark` - marks a task as done [X]
Example of usage:

`mark <taskNumber>`

`mark 1`

Expected outcome:
```
This is now done:
[T][X] borrow book
```

### `unmark` - unmark a task as not done [ ]
Example of usage:

`unmark <taskNumber>`

`unmark 1`

Expected outcome:
```
This is now undone:
[T][ ] borrow book
```

### `list` - List all tasks created
Example of usage: `list`

Expected outcome:
```
Here's what you need to do buddy:
1. [T][ ] borrow book
2. [D][ ] return book (by: Jan 28 2022)
3. [E][ ] project meeting (at: Mon 2-4pm)
```

### `delete` - Delete a task with the task number given
Example of usage:

`delete <taskNumber>`

`delete 3`

Expected outcome:
```
Got ya. Removed:
[E][ ] project meeting (at: Mon 2-4pm)
You got 2 tasks waiting for ya!
```

### `find` - Find a task based on description given
Example of usage:

`find <description>`

`find book`

Expected outcome:
```
I found these tasks in your list:
1. [T][ ] borrow book
2. [D][ ] return book (by: Jan 28 2022)
```

### `remind` - Reminds of deadlines not done
Example of usage:
`remind`

Expected outcome:
```
Got your back! Your deadlines to meet:
2. [D][ ] return book (by: Jan 28 2022)
```

### `bye` - Exit out of the app by typing bye
Example of usage:
`bye`

Expected outcome:
```
See ya!
```

## Acknowledgement

### Image sources:

[Bernie bot image](https://dribbble.com/shots/12058684-cute-cartoon-robot-character-mascot-logo-vector-illustration/attachments/3689064?mode=media)

[User image](https://static.wikia.nocookie.net/disney/images/f/f7/Profile-Russell.png/revision/latest/scale-to-width-down/516?cb=20190414091422)