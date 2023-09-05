# User Guide

## Features

1. List: View entire task list
2. Add: Add Todo, Deadline, Event task to list
3. Delete: Delete task from list
4. Mark: Mark task as done
5. Unmark: Mark task as undone
6. Find: Find task on list with specified keyword
7. Update: Edit task

## Usage

### `list` - List all tasks on the list

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][X] read book
2. [D][] return book (by: Nov 11 2018)
```

----------------------------------------------------------------------------------------

### `todo {description}` - Adds todo task to the list

Example of usage: 

`todo read book`

Expected outcome:

```
Gotcha back! I've added this task:
[T][] read book
Now you have 1 task in the list.
```

----------------------------------------------------------------------------------------

### `deadline {description} /by {date}` - Adds deadline task to the list

Example of usage:

`deadline return book /by 2018-11-11`

Expected outcome:

```
Gotcha back! I've added this task:
[D][] return book (by: Nov 11 2018)
Now you have 2 tasks in the list.
```

----------------------------------------------------------------------------------------

### `event {description} /at {date}` - Adds event task to the list

Example of usage:

`event project meeting /at school`

Expected outcome:

```
Gotcha back! I've added this task:
[E][] project meeting (at: school)
Now you have 3 tasks in the list.
```

----------------------------------------------------------------------------------------

### `delete {number}` - Deletes task from the list

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T][X] read book
Now you have 2 tasks in the list.
```

----------------------------------------------------------------------------------------

### `mark {number}` - Marks task on the list as done

Example of usage:

`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
[D][X] return book (by: Nov 11 2018)
```

----------------------------------------------------------------------------------------

### `unmark {number}` - Marks task on the list as undone

Example of usage:

`unmark 1`

Expected outcome:

```
OK, I've marked this task as not done yet:
[D][] return book (by: Nov 11 2018)
```

----------------------------------------------------------------------------------------

### `find {keyword}` - Finds tasks on the list that contains keyword

Example of usage:

`find book`

Expected outcome:

```
Here are the matching tasks in your list:
1. [D][] return book (by: Nov 11 2018)
```

----------------------------------------------------------------------------------------

### `update {number} {section} {content}` - Updates task section with new content

Example of usage:

`update 1 date 2019-10-05`

Expected outcome:

```
I have just updated your task to:
[D][] return book (by: Oct 05 2019)
```

----------------------------------------------------------------------------------------

### `bye` - Ends conversation with Duke

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```