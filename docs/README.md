# User Guide

## Features 

### Creates Todo task

Duke keeps track of your To-Do list.

### Creates deadline task

Duke keeps track of your deadlines and the date they are supposed to be completed by.

### Creates event task

Duke keeps track of your events and the date they are being held on.

### Finding tasks with keywords

Duke allows you to find tasks that contain keyword(s) that you input.

## Usage

### todo - Adds a todo task

Duke parses the input and adds the todo task into its memory.

Example of usage:

**todo borrow book**

Expected outcome:

```
Got it. I've added this task:
[T][ ] borrow book
Now you have 1 tasks in the list.
```

### deadline - Adds a deadline task

Duke parses the input and adds the deadline task into its memory.

Example of usage:

**deadline return book /by 2021-04-01**

Expected outcome:
```
Got it. I've added this task:
[D][ ] return book (by: Apr 1 2021)
Now you have 2 tasks in the list.
```

### event - Adds a event task

Duke parses the input and adds the event task into its memory.

Example of usage:

**event read book /at 2021-02-22**

Expected outcome:
```
Got it. I've added this task:
[E][ ] read book (by: Feb 22 2021)
Now you have 3 tasks in the list.
```

### doAfter - Adds a doAfter task

Duke parses the input and adds the event task into its memory.

Example of usage:

**doAfterTask read book /after 2021-02-22**

Expected outcome:
```
Got it. I've added this task:
[A][ ] read book (after: Feb 22 2021)
Now you have 3 tasks in the list.
```

### list - Lists all tasks

Duke lists all tasks that it is keeping track of.

Example of usage:

**list**

Expected outcome:
```
1. [T][ ] borrow book
2. [D][ ] return book (by: Mar 1 2021)
3. [E][ ] read book (by: Feb 22 2021)
4. [A][ ] read book (after: Feb 22 2021)
```

### done - Marks a task as done

Duke marks a task as done.

Example usage:

**done 1**

Expected outcome:
```
This task is marked as done:
1. [T][X] borrow book
```

### delete - Delete a task

Duke deletes the task from its memory.

Example usage:

**delete 1**

Expected outcome:
```
This task has been removed:
[T][✓] borrow book
Now you have 2 tasks in the list.

```
### find - Find related tasks

Duke finds the tasks that contain keyword(s) input from user.

Example usage:

**find book**

Expected outcome:
```
1. [D][✘] return book (by: Mar 1 2021)
2. [E][ ] read mooning (by: Feb 22 2021)
```
