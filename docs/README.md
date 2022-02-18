# PokeJournal
> ""The world's greatest Pokémon Master is waiting for me!" -- Ash Ketchum

## User Guide

### Feature 1: Add Tasks

## Features
 - Add a type of task 
   - There are 3 types of tasks; Todos, Deadlines, and Events.
 - (Single or Mass) Delete a task.
 - List out all the current tasks available.
 - Search for a task using a keyword.
 - Complete and uncomplete a task
 - Bid the bot goodbye.

### Feature 1: Adding Tasks
Allow you to add a variety of different tasks to the program.

### Feature 2: Deleting Tasks
Allow you to delete or mass-delete tasks you recorded.

### Feature 3: Listing Tasks
Allow you to list out all the tasks you recorded.

### Feature 4: Search for a task
Allow you to search for relevant tasks using keywords.

### Feature 5: Complete and Uncomplete a task
Allow you to indicate the completion (or incompletion) of a task.

### Feature 6: Bid farewell
Bid the PokeBot goodbye.

## Usage

### `todo`
Adds a `todo` task, that allows record of a description and a completion status.

Example of usage: `todo Catch Giratina`

Expected outcome:
```
Pika, the PokeTask has been successfully registered!
  [T][ ] Catch Giratina
```

### `event`
Adds an `event`, that allows record of a description, location, and a completion status.

Example of usage: `event Catch Giratina /at Labrador Town`

Expected outcome:
```
Pika, the PokeTask has been successfully registered!
  [E][ ] Catch Giratina (At: Labrador Town)
```

### `deadline`
Adds a task with a deadline`, that allows record of a description, deadline, and a completion status.

Example of usage: `deadline Catch Giratina /by 2022-02-14`

Expected outcome:
```
Pika, the PokeTask has been successfully registered!
  [D][ ] Catch Giratina (By: 2022-02-15)
```

### `delete`
Allows deletion of a task previously recorded.

Example of usage: `delete 1`

Expected outcome:
```
Pika, status of the tasks deleted:
  [D][X] Catch Giratina (By: 2022-02-15) - Success
```

### `delete`
Allows deletion of multiple task previously recorded.

Example of usage: `delete 1, 3, 5`

Expected outcome:
```
Pika, status of the tasks deleted:
  [D][X] Catch Giratina (By: 2022-02-15) - Success
  [D][X] Catch Kyogre (By: 2022-02-15) - Success
  [D][X] Catch Rayquaza (By: 2022-02-15) - Success
```

### `list`
Returns a list of all your tasks recorded, their completion status, and description.

Example of usage: `list`

Expected outcome:
```
PIKA! Fetching your lovely tasks:
  1. [T][ ] Catch Giratina
  2. [T][X] Catch Kyogre

```
### `find`
Allows you to query tasks based on an associated keyword.

Example of usage: `find Latias`

Expected outcome:
```
PIKA! Fetching your lovely tasks:
   1. [E][X] Catch Latias (At: Labrador Town)
```

### `mark`
Allows you to mark a task as completed.

Example of usage: `mark 1`

Expected outcome:
```
Pika, the PokeTask has been successfully editted!
  [E][X] Catch Latias (At: Labrador Town)
```

### `unmark`
Allows you to unmark a completed task.

Example of usage: `unmark 1`

Expected outcome:
```
Pika, the PokeTask has been successfully editted!
  [E][ ] Catch Latias (At: Labrador Town)
```

### `bye`
Allows you to bid the bot farewell.

Example of usage: `bye`

Expected outcome:
```
Hope to see you again soon :(
Let's play video game the next time!
```