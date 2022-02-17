# User Guide

## Features 
1. **Add different types of tasks**
    1. Todos
    2. Deadlines
    3. Events
    4. DoWithinTasks
    

2. **Delete and maintain tasks**
   

3. **Mark and unmark completed tasks**

### Feature 1 (Adding Tasks)

Allows the user to add a variety of tasks to the Chatbot with different formats

### Feature-2 (Delete Tasks)

Allows the user to maintain taskList by deleting tasks with *one* command

### Feature-3 (Mark/Unmark Tasks)

Keep track of which tasks have been completed!


## Usage

### `list`

full list of all tasks, their current completed status and description

Example of usage: 

`list`

Expected outcome:

```
1. D |  | return book | Oct 15 2019
2. T |  | eat lunch!
```

### `todo`

Adds a `todo` task -- containing a description, 
as well as a isCompleted field

Example of usage:

`todo eat lunch`

Expected outcome:

```
OK! Added this todo:
T |  | eat lunch!
```



### `event`

Adds a `todo` task -- containing a description, 
a isCompleted field, as well as a date field

Accepts dates in a *yyyy-mm-dd* format

Example of usage:

`event Isaac's Birthday /at 2019-10-15`

Expected outcome:

```
OK! Added this event:
E |  | Isaac's Birthday | Oct 15 2019
```

### `deadline`

Adds a `deadline` task -- containing a description,
a isCompleted field, as well as a date field

Accepts dates in a *yyyy-mm-dd* format

Example of usage:

`deadline CS2103 project /by 2019-10-16`

Expected outcome:

```
OK! Added this event:
D |  | CS2103 project | Oct 16 2019
```

### `deadline`

Adds a `deadline` task -- containing a description,
a isCompleted field, as well as a date field

Accepts dates in a *yyyy-mm-dd* format

Example of usage:

`deadline CS2103 project /by 2019-10-16`

Expected outcome:

```
OK! Added this event:
D |  | CS2103 project | Oct 16 2019
```


### `within`

Adds a `DoWithin` task -- containing a description,
a isCompleted field, a start date field and 
an end date field

Accepts dates in a *yyyy-mm-dd* format

Example of usage:

`within water plants /within 2019-10-16 2019-10-18`

Expected outcome:

```
OK! Added this doWithin Task:
W |  | water plants | Oct 16 2019 | Oct 18 2019
```

### `mark`

`mark` a task as completed by putting a X in their 
isCompleted field

Takes in an index, and marks the task at that index
as completed

Example of usage:

`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
T | X | eat lunch
```

### `unmark`

`unmark` a task as completed by making their
isCompleted field blank

Takes in an index, and unmarks the task at that index

Example of usage:

`mark 1`

Expected outcome:

```
OK, I've marked this task as not done yet:
T |  | eat lunch
```

### `delete`

`delete` a task completely from the tasklist

Takes in an index, and deletes the task at that index

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
T |  | eat lunch
```

### `find`

`find` a task that contains a specified keyword

Example of usage:

`find eat`

Expected outcome:

```
Matching tasks in your list:
1. T |  | eat lunch
2. T |  | eat dinner 

```




