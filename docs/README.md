# User Guide

## Features 

### Feature `help`
See user guide. 
- format: `help`

### Feature `list`
Lists all the tasks. 
- format: `list`

### Feature `todo`
Adds a todo task. 
- format: `todo <description>`
- example: `todo read book`

### Feature `deadline`
Adds a task with a deadline. 
- format: `deadline <description> /by yyyy-mm-dd`
- example: `deadline read book /by 2030-01-01`

### Feature `event`
Adds a task with a time period. 
- format: `event <description> /at <time>`
- example: `event read book /at 2-4pm`

### Feature `mark` `unmark`
Marks a task as completed or uncompleted by index. 
- format: `mark <index>`, `unmark <index>`
- example: `mark 1`, `unmark 2`

### Feature `delete`
Deletes a task by index. 
- format: `delete <index>`
- example: `delete 3`

### Feature `find`
Finds tasks that match with the keyword given. 
- format: `find <keyword>`
- example: `find book`

### Feature `duplicate`
Finds duplicate tasks. 
- format: `duplicate`

### Feature `save`
Saves all changes.  
- format: `save`

### Feature `clear`
Clears all tasks. 
- format: `clear`

## Usage

### Usage `save`
Duke does not save automatically.
Please `save` frequently to avoid loss of information.

### Usage `list`
Lists all the tasks in the format of 
`[task type][completed] task description (time)`.
- task type: `T` - `todo`; `D` - `deadline`; `E` - `event`. 
- completed: `X` - completed; ` ` - uncompleted. 
- example outcome:
  ```
  Here are the tasks in your list: 
  1.[T][ ] read book
  2.[D][X] assignment (by: 23 Feb 2023)
  3.[E][ ] class (at: 2-4pm)
  ```
