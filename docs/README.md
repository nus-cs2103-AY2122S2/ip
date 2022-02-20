# User Guide

## Features 

### Adding a Todo task: `todo`
Adds a Todo task into Duke.   
Format: `todo DESCRIPTION`  
Example: `todo read book`

### Adding an Event task: `event`  
Adds an Event task into Duke.  
Format: `event DESCRIPTION /at YYYY-MM-DD`  
Example: `event meeting /at 2022-02-21`

### Adding a Deadline task: `deadline`  
Adds a Deadline task into Duke.  
Format: `deadline DESCRIPTION /by YYYY-MM-DD`  
Example: `deadline submit IP /by 2022-02-21`

### Deleting a task: `delete`  
Deletes a specified task from Duke.  
Format: `delete TASK_NUMBER [MORE_TASK_NUMBERS]`   
Example: 
- `delete 1` deletes the first task in the task list
- `delete 2 3 4` deletes tasks 2, 3 and 4 in the task list

### Finding a task in Duke: `find`  
Find a task which contains the keywords specified.  
Format: `find KEYWORD [MORE_KEYWORDS]`  
Example: 
- `find read` returns all tasks with the keyword 'read' in it
- `find read movie` returns all tasks with the keywords 'read' and 'movie' in them

### Marking a task as complete: `mark`  
Marks a task from the existing task list as complete.  
Format: `mark TASK_NUMBER [MORE_TASK_NUMBERS]`  
Example: 
- `mark 3` marks the 3rd task in the task list as complete
- `mark 1 4` marks the 1st and 4th tasks in the task list as complete

### Marking a task as incomplete: `unmark`
Marks a task from the existing task list as incomplete.  
Format: `unmark TASK_NUMBER`  
Example: 
- `unmark 2` marks the 2nd task in the task list as incomplete
- `unmark 1 2 5` marks the 1st, 2nd and 5th tasks in the task list as incomplete

### Listing the existing task list: `list`
Lists all tasks in the existing task list.  
Format: `list`  
Example: `list`  

### Exiting Duke: `bye`
Exits from Duke and closes the application.  
Format: `bye`  
Example: `bye`


