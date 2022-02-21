# Fluffers User Guide

## Description

Fluffers is a task manager chatbot! She can help you keep track of tasks and whether they have been completed or not.

NOTE: I DO NOT OWN THE IMAGES USED.

## Quick Start

1. Install Java 11 or higher
2. Download the latest .jar file at [the release page](https://github.com/FestiveCat/ip/releases).
3. Place the .jar file in a folder that you'd want the chatbot to be stored. (Data will be stored in the same folder too)
4. Run the .jar file
5. Enjoy the feline goodness!

## Features 

### Feature - Create new tasks!

Fluffers currently offers 4 types of tasks:

| Task type | Description | Usage | Example |
| --- | --- | --- | --- |
| To do task | This is the most basic task, like one that you will write on a post it note and paste it on your fridge! | `todo <description>` | `todo E-Learning homework` |
| Deadline task | This is a task with a fixed dateline, so that you know when to finish it by! | `deadline <description> /by <date>` | `deadline Submit E-Learning homework /by 2022-02-14 23:59` |
| Event task | This is an event that has a start and end time! | `event <description> /at <date> /until <date>` | `event Family Dinner /at 2022-03-01 18:00 /until 2022-03-1 20:00` |
| Between task | This is a special task that has to be done between two date/times! | `do <description> /between <date> /and <date>` | `do Mark students' homework /between 2022-05-03 15:00 /and 2022-05-05 23:59` |

**Note that the format of the date must be `yyyy-mm-dd hh:mm` exactly!**

Another example with the expected output:

Input: 

`deadline submit individual project /by 2022-02-18 23:59`

Response from Fluffers:

```
Meow! (Okay, added this task:
[D][ ] submit individual project (by: 2022-02-18 23:59)
There are 2 tasks in the list now.)
```

---

### Feature - View and find your tasks!

Fluffers allows you to check your tasks with the `list` command, as well as allowing you to find your tasks using `find`!

| Command type | Description | Usage | Example |
| --- | --- | --- | --- |
| List all tasks | Lists all the tasks you have created! | `list` | `list` |
| Finds a task | Finds a task with a given keyword! This keyword can be from the description or the time! | `find <keyword>` | `find homework`, `find 2022-02` |

Another example with the expected output:

Input: 

`find project`

Response from Fluffers:

```
Meow! (Here's what I found! (the numbering is kept the same as the "list" command))
2. [D][ ] submit individual project (by: 2022-02-18 23:59)
```

---

### Feature - Mark (and unmark) your tasks as done! Delete them too!

Fluffers allows you to mark and unmark your tasks as done with the `mark` and `unmark` commands! Once you're done with the task, you can delete them with the `delete` command too!

| Command type | Description | Usage | Example |
| --- | --- | --- | --- |
| Mark a task as done | Marks a task as done! You will see a `x` if it is marked as done! | `mark <task number>` | `mark 2` |
| Unmark a task as done | Unmarks a task as undone! All tasks are undone by default. | `unmark <task number>` | `unmark 3` |
| Deletes a task | Deletes a task. Irreversible! | `delete <task number>` | `delete 1` |

**Note that the task number is the number that is listed using the `list` and `find` commands!**

Another example with the expected output:

Input: 

`mark 2`

Response from Fluffers:

```
Meow! (Okay, this task is done:
[D][X] submit individual project (by: 2022-02-18 23:59))
```

---

### Feature - Say hi and bye to Fluffers!!

You can say hi and bye to Fluffers to turn her functionalities on and off! (Not sure why you'd want to do that, though...)

| Command type | Description | Usage | Example |
| --- | --- | --- | --- |
| Greetings | Says hi to Fluffers, waking her up if she was asleep! | `hi` | `hi` |
| Goodbye | Says goodbye to Fluffers, letting her sleep if she was awake! | `bye` | `bye` |
