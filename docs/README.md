# User Guide
Duke is a chatbot that helps you keep track of various tasks.

## Features
### Adding a new task: `todo` `deadline` `event`

Adds a new to the task list.

Example of usage: 

`todo quiz`

`deadline project /by 12/12/2022 2359`

`event dinner /at 12/12/2022 2359`

Expected outcome:

```
Got it. I've added this task:
 [T][] quiz
Now you have 1 task(s) in the list.
```
### Listing all tasks: `list`

Display all the tasks.

Expected outcome:

```
Here are the task(s) in your list:
  1. [T][] quiz
```
### Marking a task: `mark` `unmark`

Marks a task as done/not done.

Example of usage:

`mark 1`

Expected outcome:
```
Nice! I've marked this task as done:
  [T][X] quiz
```

### Deleting a task: `delete`

Delete a task for the task list.

Example of usage:

`delete 1`

Expected outcome:
```
Got it. I've removed this task:
  [T][X] quiz
Now you have 0 task(s) in the list.
```

### Searching for tasks: `find`

Search for tasks that contains keywords.

Example of usage:

`find quiz`

Expected outcome:
```
Here are the matching tasks in your list:
  [T][] quiz
```

### Exiting the program: `bye`

Terminates the program.



