# User Guide

Hi This is Halloumi ^_^. 
I can keep track of your tasks, deadlines and events for you, and am your own personal tasks manager.

## Features 

1. **ToDo** : A simple task with only a description that.
2. **Deadline** : A task with a specific deadline that needs to be completed before that deadline.
3. **Event** : A task that is going to happen occur on a specific date.

## Usage

### `todo` - add a todo task into the task list.

using this keyword with a description will add a todo task in the list.

Example of usage: 

`todo read book`

Expected outcome:

this will add the task of reading a book to the list.

```
New task added:
[T][ ] read book
```

### `deadline` - add a deadline task into the task list.

using this keyword with a description and a date after "/by" will add a deadline task in the list.

Example of usage: 

`deadline finish project /by 2022-06-10`

Expected outcome:

this will add the deadline task of finishing a project and set the deadline as June 10 2022.

```
New task added:
[D][ ] finish project (by: Jun 10 2022)
```

### `event` - add an event task into the task list.

using this keyword with a description and a date after "/at" will add a event task in the list.

Example of usage: 

`event go party /at 2022-06-10`

Expected outcome:

this will add the event task of going to a party and set the event date as June 10 2022.

```
New task added:
[E][ ] go party (at: June 10 2022)
```

### `mark` - marks a task as completed

using this keyword with a task number from the list will mark that task as complete
Example of usage: 

`mark 6`

Expected outcome:

this will mark the 6th task in the list as complete

```
Good Job! ^_^
Task number 6 has been marked as done!
[D][X] finish project (by: Jun 10 2022)
```

### `unmark` - marks a task as incompleted

using this keyword with a task number from the list will mark that task as incomplete
Example of usage: 

`unmark 6`

Expected outcome:

this will mark the 6th task in the list as incomplete

```
I've unmarked task number 6
Complete it soon! ^_^
[D][ ] finish project (by: Jun 10 2022))
```
### `delete` - delete a task

using this keyword with a task number from the list will delete that task from the list

Example of usage: 

`delete 6`

Expected outcome:

this will delete the 6th task in the list

```
Noted. I've removed this task:
[D][ ] finish project (by: Jun 10 2022)
```

### `find` - find a task

using this keyword with a keyword will find the tasks where the description contain that keyword

Example of usage: 

`find run`

Expected outcome:

this will find all the tasks where the description contains 'run'

```
Here are the matching tasks:
1. [T][ ] run
2. [D][ ] run (by: Jan 2 2007)
3. [E][ ] run (at: Jan 2 2007)
```
