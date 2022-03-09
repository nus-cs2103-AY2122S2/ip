# User Guide for Duke Task Manager

## Product showcase
![](./Ui.png)

## How to set up Duke Task Manager
1. Download JAR file from the latest release.
2. Run the file!

## Usage
- list / l
  - Lists all current tasks saved.
  - E.g., `list`
- todo / t, deadline / d, event / e
  - These are the 3 different types of tasks you can add to your list.
  - E.g., `todo <description of task>`
  - E.g., `deadline <description of task> /by <YYYY-MM-DD>`
  - E.g., `event <description of task> /at <YYYY-MM-DD>`
- mark / m, unmark / u
  - Marks your tasks as done or not done.
  - E.g., `mark 1`
  - E.g., `u 1`
- delete / del
  - Deletes a task from your list
  - E.g., `del 1`
- find / f
  - Displays all tasks which match the specified keyword
  - E.g., `f <keyword>`