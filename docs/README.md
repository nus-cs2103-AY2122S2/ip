# User Guide
Task List Assistant (TLA) helps you keep track of your tasks and more!\
TLA uses a chat format similar to chat apps when interacting with the assistant.

## Features 

### Tasks

TLA supports 3 main types of tasks:
- Todo Task (normal task with description)
- Event Task (task with a date)
- Deadline Task (task with a deadline)

### Exporting and Importing the Task List with a file
1. Duke saves to task.txt (same working directory) for every modification to the task list.
2. Duke will immediately import tasks from task.txt on startup.

## Usage

To launch the application, you will need Java 11. Download the latest release from [Releases](https://github.com/Kidsnd274/ip/releases)

Launch the application using this command line:
`java -jar TaskListAssistant.jar`

TLA accepts commands from users and modifies the task list in that way.\
These are the available commands:

### `list` - List out all the tasks and their descriptions

Shows all the current tasks in the task list and their descriptions.

Expected outcome:
```
Here are your tasks:
1. [T][x] Get help with CS2106's content
2. [E][ ] Project meeting with GES group (at: 05 Mar 2022)
3. [D][ ] Final version deadline for CS2103T (by: 18 Mar 2022)
4. [T][ ] Borrow book from library
```


### `todo (description)` - Adds a Todo Task with a description

Creates a new Todo Task with the inputted description and adds it to the task list.

Example of usage:

`todo Borrow book from library`

Expected outcome:
```
Added Borrow book from library, as a Todo Task
[T][ ] Borrow book from library
You currently have 4 tasks
```

### `event (description) /at (date YYYY-MM-DD)` - Adds an Event Task with a description and date

Creates a new Event Task with the inputted description and date.

Example of usage:

`event John's birthday /at 2022-03-18`

Expected outcome:
```
Added John's birthday (at: 18 Mar 2022), as a Event Task
[E][ ] John's birthday (at: 18 Mar 2022)
You currently have 5 tasks
```

### `deadline (description) /by (date YYYY-MM-DD)` - Adds a Deadline Task with a description and date

Creates a new Deadline Task with the inputted description and date.

Example of usage:

`deadline Book due date /by 2022-03-18`

Expected outcome:
```
Added Book due date (by: 18 Mar 2022), as a Deadline Task
[D][ ] Book due date (by: 18 Mar 2022)
You currently have 6 tasks
```

### `mark (task number)` - Marks a task as done.

Marks a task from the task list as done. Use `list` to check the task's number.

Example of usage:

`mark 3`

Expected outcome:
```
Marked as done:
[D][x] Final version deadline for CS2103T (by: 18 Mar 2022)
```

### `unmark (task number)` - Marks a task as undone.

Marks a task from the task list as undone. Use `list` to check the task's number.

Example of usage:

`unmark 3`

Expected outcome:
```
Marked as done:
[D][ ] Final version deadline for CS2103T (by: 18 Mar 2022)
```

### `delete (task number)` - Removes a task from the task list.

Removes a task from the task list. Use `list` to check the task's number.

Example of usage:

`delete 3`

Expected outcome:
```
Removed this task:
[T][ ] Borrow book from library
You currently have 3 tasks
```

### `postpone (task number) (date YYYY-MM-DD)` - Edit the date in Event and Deadline Tasks.

Edits the date in Event and Deadline Tasks. Use `list` to check the task's number.

Example of usage:

`list`

```Here are your tasks:
1. [T][x] Get help with CS2106's content
2. [E][ ] Project meeting with GES group (at: 05 Mar 2022)
3. [D][x] Final version deadline for CS2103T (by: 18 Mar 2022)
4. [T][ ] Borrow book from library
5. [E][ ] John's birthday (at: 18 Mar 2022)
6. [D][ ] Book due date (by: 18 Mar 2022)
```

`postpone 6 2022-03-20`

Expected outcome:
```
Date changed to 2022-03-20 for task:
[D][ ] Book due date (by: 20 Mar 2022)
```