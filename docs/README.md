# User Guide
Duke is a chat bot application for managing your tasks, saving them
a task list.

## Features 

### Listing all tasks: list

Shows a list of all task.

Format: *list*

### Adding a todo task: todo

Adds a todo task to the task list.

Format: *todo [DESCRIPTION]*

Examples:

- todo read book
- todo wash dishes

### Adding a deadline task: deadline

Adds a deadline task to the task list.

Format: *deadline [DESCRIPTION] /by YYYY-MM-DD*

Examples:

- deadline return book /by 2022-02-19
- deadline finish assignment /by 2022-03-15

### Adding a event task: event

Adds a event task to the task list.

Format: *event [DESCRIPTION] /at YYYY-MM-DD*

Examples:

- event project meeting /at 2021-04-24
- event gathering /at 2022-03-01

### Marking a task: mark

Marks a task as done.

Format: *mark [INDEX]*

- Marks the task as done at the specified [INDEX]. The index must be
positive integer 1, 2, 3, ....

Example:

- mark 1

### Deleting a task: delete

Deletes a task from the task list.

Format: *delete [INDEX]*

- Deletes the task as done at the specified [INDEX]. The index must be
  positive integer 1, 2, 3, ....

Example:

- delete 1

### Finding task(s): Find

Find tasks by from a keyword.

Format: *find [KEYWORD]*

Example:

- find read

### Exiting the program: Bye

Exit the application.

Format: *bye*
