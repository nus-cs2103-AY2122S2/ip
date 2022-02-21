# User Guide
Ace is a desktop application that helps you manage and keep track of tasks.
Ace utilises a Command Line Interface (CLI), which is much faster than the typical Graphical User Interface (GUI).

## Quick Start
1. Download the Ace program.
2. Double-click the jar file to start using the app.
3. Begin adding and managing tasks! Below are all features to get you started.

## Features

### `todo`: Add a todo
Adds a todo to the task list. A todo is a task with no set time or deadline.

**Format:** `todo {title}`

**Example:**

`todo homework`

The following task has been added:

[T][ ] homework

### `event`: Add an event
Adds an event to the task list. An event is a task that occurs at a certain time.

**Format:** `event {title} /at {date and time}`

**Example:**

`event lecture /at 23/2/2022 10:00`
The following task has been added:

[E][ ] lecture (at: 23222 10:00)

### `deadline`: Add a deadline
Adds a deadline to the task list. A deadline is a task that is due by a certain time.

Format: `deadline {title} /by {date and time}`

Example:

`deadline assignment /by 25/2/2022 23:59`

The following task has been added:

[D][ ] assignment (by: 23222 10:00)

### `mark`: Marks a task as complete
Marks a specific task, indicated by its index number, as complete.

Format: `mark {index}`

Example:

`mark 1`

The following task has been marked as complete:

[T][X] homework

### `unmark`: Mark a task as incomplete
Marks a specific task, indicated by its index number, as incomplete.

Format: `unmark {index}`

Example:

`unmark 1`

The following task has been marked as incomplete:

[T][ ] homework

### `delete`: Deletes a task from the task list
Deletes a specific task, indicated by its index number, from the task list.

Format: `delete {index}`

Example:

`delete 1`

The following task has been deleted:

[T][ ] homework

### `find`: Finds tasks by keyword
Lists all tasks that contain a given key word or phrase.

Format: `find {phrase}`

Example:

`find buy`

Here are the matching tasks:

1. [T][ ] buy eggs
2. [T][ ] buy milk

### `list`: Lists all tasks
Lists all tasks in the task list

Format: `list`

Example:

`list`

Here are your tasks:

1. [T][ ] buy eggs
2. [T][X] do homework
3. [D][X] tutorial (by: 20/2/22 2:22)

## Storing Data
All data is stored in the directory `data/tasks.txt`. Ace will create and manage this directory for you, so there is no need to create any folders or files.
