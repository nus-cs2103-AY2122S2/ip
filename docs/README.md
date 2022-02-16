# User Guide

## Features 

### 1. Todo task
General task with no specific deadline or venue

### 2. Deadline task
Task with a specific deadline \
Deadline for the newly added task must be after the day that the task is added

### 3. Event task
Task with a specific venue

## Usage

### Type 'Hi' or anything to get started!

### 1. `todo` : Add a general task
Example of usage:
- `todo` Read a book

Expected outcome:
- task will be added to the task list and stored in a `.txt` file

Description of the outcome:
```
Nice! I've added this task:
[T][] Read a book
```

### 2. `deadline` - `by` : Add a Deadline task 
Example of usage: 
- `deadline` Submit homework `by` 2022-12-20

Expected outcome:
- task will be added to the task list and stored in a `.txt` file

Description of the outcome:
```
Nice! I've added this task:
[D][] Submit homework (by:Dec 20 2022)
```

### 3. `event` - `at` : Add a Event task
Example of usage:
- `event` Career fair `at` Auditorium

Expected outcome:
- task will be added to the task list and stored in a `.txt` file

Description of the outcome:
```
Nice! I've added this task:
[E][] Career fair (at: Auditorium)
```

### 4. `mark` : Mark nth task in the task list as done
Example of usage:
- `mark` 1

Expected outcome:
- First task in the `.txt` storage file will be marked done

Description of the outcome:
```
Nice! I've marked this task as done:
[T][X] Read a book
```

### 5. `unmark` : Mark nth task in the task list as not done
Example of usage:
- `unmark` 1

Expected outcome:
- First task in the `.txt` storage file will be marked as not done

Description of the outcome:
```
OK, I've marked this task as not done yet:
[T][] Read a book
```

### 6. `list` : List all tasks in the task list
Example of usage:
- `list` 

Expected outcome:
- All tasks in the `.txt` storage file will be listed

Description of the outcome:
```
1. [T][] Read a book
2. [D][] Submit homework (by:Dec 20 2022)
3. [E][] Career fair (at: Auditorium)
You have 3 tasks! Please rest well.
```

### 7. `delete` : Delete the nth task in the task list
Example of usage:
- `delete` 2

Expected outcome:
- Second task in the `.txt` storage file will be deleted

Description of the outcome:
```
Task is removed.
Now you have 2 left!
```

### 8. `find` : Find all tasks in task list with matching keyword or deadline
Example of usage:
- `find` book
- `find` 2022-12-20

Expected outcome:
- Tasks in the `.txt` storage file with the word book will be listed

Description of the outcome:
```
Here you go!
[T][] Read a book
Did you find the task you were looking for?
```
