# Kenobi User Guide
Kenobi is a chatbot to manage tasks written in Java and JavaFX.

- Quick Start
- Features
  - Adding Tasks
  - Reading Tasks
  - Marking Tasks
  - Deleting Tasks
  - Saving Tasks

## Quick Start
1. Download Kenobi jar file
2. Make sure you have Java 11 installed
3. Run Kenobi jar file

## Features
### Adding Tasks 
There are three types of tasks that kenobi recognizes: `todo`, `deadline`, and `event`

`todo` is a task that can be marked as done
syntax: `todo [taskname]`

`deadline` is a task that can be marked as done and have a `/by` date
syntax: `deadline [taskname] /by [date]`

`event` is a task that can be marked as done and have an `/at` date
syntax: `event [taskname] /at [date]`

Accepted `[date]` formats:
- `[YYYY]-[MM]-[DD]` (e.g. `2022-02-01` means 1 February 2022)
- `next [day of the week]` (e.g. `next monday` means the next monday)

#### Usage examples
Adding a todo named "Implement clickable Task":
```todo Implement clickable Task```

Adding a deadline named "CS2103T 2nd milestone" which is due next friday:
```deadline CS2103T 2nd milestone /by next friday```

Adding an event named "Google internship interview" at 5th March 2022:
```event Google internship interview /at 2022-03-05```

### Reading Tasks 
There are currently 2 commands to read tasks: `list` and `find` 

`list` lists all of your tasks known by Kenobi
syntax: `list`

`find` finds all of the tasks in the list containing a given term
syntax: `find [term]`

#### Usage examples
Listing all of my tasks:
```list```

Finding my tasks that contains the word "Google interview":
```find Google interview```

### Marking Tasks
To mark a task as done, use `mark` and use `unmark` to undo it.

`mark` marks a selected task as done
syntax: `mark [index]`

`unmark` removes the mark of a task
syntax: `unmark [index]`

`[index]` follows the task number shown by `list`

#### Usage examples
Marking my first todo as done:
```mark 1```

Removes the mark of my first todo:
```unmark 1```

### Deleting Tasks `delete`
To delete a task, use `delete`.

`delete` deletes a selected task
syntax: `delete [index]`

#### Example usage
Deleting my first task:
```delete 1```


### Saving Tasks
Kenobi saves the user's task on exit either on closing application or using the command `bye`

`bye` Saves the user tasks and exits the application

#### Example usage
Saving and exiting Kenobi:
```bye```
