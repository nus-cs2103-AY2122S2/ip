# User Guide
Duke is a task managing desktop application that can be used via both the Command Line Interface (CLI) or the Graphical User Interface (GUI).

## Quickstart
1. Ensure you have Java 11 installed
2. Download Duke.jar from here
3. Run the app by double-clicking the .jar file
4. Type in your commands and hit Enter to execute them.

An in-depth list of commands can be found below.

## Features 

1. `todo`
2. `deadline`
3. `event`
4. `list`
5. `mark`
6. `unmark`
7. `delete`
8. `find`
9. `help`
10. `bye`

## Usage

### List All Entries In Task List
To list all the existing entries in the task list, simply type the command `list`. 

Points to note:
1. If there are **0** entries, Duke bot would respond with a message that would prompt the user the list of commands available.

#### format
**list** - Displays all the existing task.
  > list

#### response
```
Now you have 2 task(s) in the list.
1. [T][] make a strawberry milkshake!
2. [D][] finish my CS2103T assignment (by: Feb 02 2022 2359)
```

### Adding a todo, deadline or event
To create a new entry into the task list, converse with Duke bot by typing either command: `todo`, `deadline`, `event`.

Points to note:
1. All new task entries are marked as **"undone"**
2. `deadline` and `event` requires the **/by** and **/at** in the middle of the message respectively. This enables the bot to distinguish the features of the message.
3. Each command has an _optional_ **priority** tag that is appended at the front of the message.

#### Format

- **todo** `[priority] [task]` - Creates a todo task.

  > todo IMPORTANT read the first chapter of my star wars book  

- **deadline** `[priority] [task] /by [date time]` - Creates a deadline task that requires a date and optionally, a specific time to be provided.
It is important that the **date** follows a fixed format of **yyyy-MM-dd** and the time in **HHMM**

  > deadline URGENT buy Rosalynn a valentine's day gift /by 2022-02-13 
- **event** `[priority] [task] /at [location]` - Creates a event task that requires a location to be specified.

  > event attend Joe's performance /at Marina Esplanade

`[priority]` - In descending priority order: URGENT, IMPORTANT, NORMAL(_default_). Forces tasks with higher priorities to stack at the top of the task list.


### Edit Existing Tasks

To edit tasks that exist in the task list, Duke bot supports a few commands: `mark`, `unmark`, `delete`.

Points to note:

1. `[Index]` must be in range of the number of tasks that is in the task list. 
2. A single command corresponds to one action. For example, `mark 1` would not mark task 1 as incomplete even if it was already completed. You would need to execute `unmark 1` to change task 1 state.

#### Format
- mark `[index]` - Marks [index] task as completed

  > mark 1

- unmark `[index]` - Marks [index] task as incomplete
 
  > unmark 1

- Delete `[index]` - Removes [index] task from the list

  > delete 1

### Search for a task

To look for a task in the task list, execute the command: `find`

Points to note:
1. Duke would locate all the task that resembles the search term. 
2. `[search term]` is case insensitive.
3. If Duke fails to locate a task, it would respond:
```
nothing was found :(
```

#### Format
search `[search term]` - Search for a specific task in the list.
  > search book

#### Response
```
1. [T][] read a book
2. [D][] finish my harry potter book (by: Feb 02 2022 2359)
```

### I need help :smiling_face_with_tear:

To get a list of commands that will help a user with their interaction with Duke bot, execute the command: `help`

#### Format
`help` - Displays a list of commands that is executable by Duke bot.

#### Response
```
1. todo [priority] [task]
2. deadline [priority] [task] /by [date time]
3. event [priority] [task] /at [location]
[priority] = URGENT, IMPORTANT, NORMAL
-----i.e. deadline URGENT read my star-wars book /by 2022-03-12 1800 -----
4. list
5. mark X (mark X task as done)
6. unmark X (mark X task as undone)
7. delete X (delete X task from the list)
8. find [search term] - locates task(s)
9. help - display command list
10. bye - exit Duke bot
```

### Leaving Duke Bot

To exit the program, type the command: `exit`

Points to note:
1. All of your tasks would be saved and can be accessed the next time you start the bot.

#### Format
`exit`

#### Response
```
Bye! Hope to see you again soon!
```
