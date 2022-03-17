# User Guide

#### MeeTrack is a chat-bit task organizer which allows users to track tasks that need to be completed

## Installation

1. Download the latest ```.jar``` file from ```releases```
2. Place the .jar file in the desired installation location
3. Open up a ```command terminal``` and change directories to where the .jar is located
4. Run the .jar file using ```java -jar duke.jar```

## Features

- 3 types of tasks (ToDo, Event, Deadline)
- Add/delete tasks
- Mark/Un-mark tasks as done
- List all tasks in the list
- Find a task using a keyword
- Update tasks

##Usage

### `todo` - Adds a todo task

Adds a todo task to the list

Expected input format: 

`todo <task details>`

Example of usage:

`todo project meeting`

Expected outcome:
```
Yes sireeee!. I've added this task:
  [T][ ] project meeting
Now you have 1 tasks in the list
```

### `event` - Adds an event task

Adds an event task to the list

Expected input format:

`event <task details> /at <dd/MM/YYYY HHmm>`

Example of usage:

`event project meeting /at 2/12/2021 1800`

Expected outcome:
```
Yes sireeee!. I've added this task:
  [E][ ] project meeting (at:Dec 02 2021 1800)
Now you have 1 tasks in the list
```

### `deadline` - Adds a deadline task

Adds a deadline task to the list

Expected input format:

`deadline <task details> /by <dd/MM/YYYY HHmm>`

Example of usage:

`event return book /by 2/12/2021 1800`

Expected outcome:
```
Yes sireeee!. I've added this task:
  [D][ ] return book (by:Dec 02 2021 1800)
Now you have 1 tasks in the list
```
### `delete` - deletes task in list

deletes task in the list

Expected input format:

`delete <index of task>`

Example of usage:

`delete 1`

Expected outcome:
```
Oooh OK!. I've removed this task:
  [T][ ] return book
Now you have 0 tasks in the list
```

### `list` - Lists all task in list

Lists all task in the list

Expected input format:

`list`

Example of usage:

`list`

Expected outcome:
```
Here are the tasks in your list:
     1.[T][ ] read book
     2.[T][ ] return book
     3.[E][ ] project meeting (at:Dec 02 2021 1800)
     4.[T][ ] Finish project
     5.[D][ ] return documents (by:Dec 02 2021 1800)
```

### `mark/un-mark` - marks/un-marks task in list

marks/un-marks task in the list as done or not done

Expected input format:

`mark <index of task in list>`
`unmark <index of task in list>`

Example of usage:

`mark 1`

Expected outcome:
```
Nice! I've marked this task as done:
[T][X] read book
```
Example of usage:

`unmark 1`

Expected outcome:
```
I've marked this task as not done yet:
[T][] read book
```
### `update` - Updates task in list

Updates task in the list

Expected input format:

`update <index of task>/<detail/time>/<correction>`

Example of usage:

`update 1/detail/return book`
`update 1/time/18/03/2022 1800`

Expected outcome:
```
Ooohhh K! I have updated your task
```

### `bye` - Exits the program


Expected input format:

`bye`

Example of usage:

`bye`

Expected outcome:
```
Bye. Hope to see you again soon!
```