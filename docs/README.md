# User Guide
Duke is a **desktop app for managing tasks**. While it is a GUI, it works 
similar to a CLI, thus having the best of both worlds.

## Features 

### Add Tasks

Tell Duke the tasks you need recorded and their details

### Archive Tasks

Save completed tasks to a separate file and clear them from the list

### Delete Tasks

Remove unwanted tasks

### Mark/Unmark Tasks

Mark tasks as complete/incomplete

### List Tasks

Showcase all tasks in the list

## Usage

### `todo` - adds a To-Do to the list
Format: `todo [task description]`

Example of usage: 

`Todo call grandma`

Expected outcome:
```
Noted. I've added the task:
T | 0 | call grandma
```

### `deadline` - adds a deadline to the list

Format: `deadline [task description] /by [yyyy-mm-dd]`

Example of usage:

`Deadline project submission /by 2022-04-20`

Expected outcome:
```
Noted. I've added the task:
D | 0 | project submission | Apr 20, 2022
```

### `event` - adds an event to the list

Format: `event [task description] /at [yyyy-mm-dd]`


Example of usage:

`Event birthday party /at 2022-04-20`

Expected outcome:
```
Noted. I've added the task:
E | 0 | birthday party | Apr 20, 2022
```

### `list` - shows all tasks in list

Example of usage:

`list`

Expected outcome:
```
Here are the requested tasks:
1. T | 0 | call grandma
2. D | 0 | project submission | Apr 20, 2022
3. E | 0 | birthday party | Apr 20, 2022
```

### `mark` - marks a task as complete
Format: `mark [task id]`

Example of usage:

`mark 1`

Expected outcome:
```
Task:
T | 1 | call grandma
has been marked complete.
```

### `unmark` - marks a task as incomplete
Format: `unmark [task id]`
Example of usage:

`unmark 1`

Expected outcome:
```
Task:
T | 0 | call grandma
has been marked incomplete.
```

### `delete` - deletes a task from the list
Format: `delete [task id]`
Example of usage:

`delete 1`

Expected outcome:
```
Noted. I've removed this task:
T | 0 | call grandma
```

### `archive` - moves all completed tasks to archive
Example of usage:

`archive`

Expected outcome:
```
All completed tasks have been moved to the archive.
```