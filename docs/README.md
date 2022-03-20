# User Guide
![UI Showcase](./Ui.png)

Duke chatbot provides a way to record and save your tasks efficiently!

- [Features](https://robinrojh.github.io/ip/#features)
- [Usage](https://robinrojh.github.io/ip/#usage)
  - [Add, Delete, and View](https://robinrojh.github.io/ip/#adding-and-deleting-tasks)
    - [todo](https://robinrojh.github.io/ip/#todo---adding-a-todo-object)
    - [deadline](https://robinrojh.github.io/ip/#deadline---adding-a-deadline-object)
    - [event](https://robinrojh.github.io/ip/#event---adding-an-event-object)
    - [delete](https://robinrojh.github.io/ip/#delete---delete-a-task)
    - [list](https://robinrojh.github.io/ip/#list---list-all-your-tasks)
  - [Mark Your Tasks](https://robinrojh.github.io/ip/#marking-tasks)
    - [mark](https://robinrojh.github.io/ip/#mark---mark-the-task-as-completed)
    - [unmark]()
  - [Useful Features](https://robinrojh.github.io/ip/#useful-features)
    - [find](https://robinrojh.github.io/ip/#find---find-a-task-in-the-list-based-on-a-keyword)
    - [sort](https://robinrojh.github.io/ip/#sort---sort-the-list-based-on-date-or-content)
  - [Tagging Your Tasks](https://robinrojh.github.io/ip/#useful-features)
    - [tag](https://robinrojh.github.io/ip/#tag---tag-a-task-with-a-keyword)
    - [untag](https://robinrojh.github.io/ip/#untag---untag-the-task)

## Features 

### Add, Delete, and View Your Tasks.

In Duke chatbot, you can add any of the three types of Tasks. 
- Todo: A simple task object with only content as argument
- Event: A task object with datetime that the event occurs at
- Deadline: A task object with datetime that serves as a deadline for the task

### Mark Your Tasks

Furthermore, you can mark your task objects as complete using Duke.
- Mark a task marks it as completed
- Unmark a task by using the index number of the task

### Sort Your Tasks

Viewing tasks based on the date is quite important. Urgent items should be addressed first! <br/>
Duke provides a sort feature to let you sort based on the date of the tasks and based on the contents of the tasks.

### Tag Your Tasks with Unique Tags

Duke also allows you to tag your tasks with tags of your own choice. This will allow you to view tasks more 
intuitively based on what the task is about.
- Add tag to a task
- Untag a task using the name of the existing tag

### Keyword Search on Tasks

Sometimes, you need to look through the whole list to find something you needed to do!
With keyword search, you can search for your task with keywords.

### Load Up Your List with Locally Stored File

Duke automatically saves every item in the list of tasks after every action. On application booting, 
Duke loads the save file.

## Usage
### Adding and Deleting Tasks
#### `todo` - Adding a ToDo object

Adds a ToDo task into the list. A ToDo task only has content, no date. 

Usage: `todo CONTENT`

Example: `todo hello world`

Expected outcome:
```
Adding a task: [T][] hello world
Now you got 1 task in the list!
```
#### `deadline` - Adding a Deadline Object

Adds a Deadline task into the list. A Deadline task contains a content and a due date. The
date and time must be in the format of `yyyy/MM/dd hhmm`.

Usage: `deadline CONTENT \by DATE`

Example: 
`deadline hello world \by 2022-03-23 1600`

Expected outcome:
```
Adding a task: [D][ ] hello world (by: 16:00 Wednesday, 03-23-2022)
Now you got 1 task in the list!
```

#### `event` - Adding an Event Object

Adds an Event task into the list. A Event task contains a content and a date when the event occurs. 
The date and time must be in the format of `yyyy/MM/dd hhmm`.

Usage: `event CONTENT \at DATE`

Example:
`event hello world \at 2022-04-12 1600`

Expected outcome:
```
Adding a task: [E][ ] hello world (at: 16:00 Tuesday, 04-12-2022)
Now you got 1 task in the list!
```
#### `delete` - Delete a Task

Deletes a task using the index of the target task.

Usage: `delete INDEX`

Example: 
```
todo hello world
delete 1
```

Expected outcome:
```
Ok, removing a task: [T][ ] hello world
Now you got 0 tasks in the list!
```
#### `list` - List all your tasks!
Lists all of your tasks that you have added until now.

Usage: `list`

Example:
```
todo hello world
deadline CS stuff \by 2022-12-25 1800
event meet up with friends \at 2022-02-23 1830
list
```
Expected outcome:
```
1: [T][ ] hello world
2: [D][ ] CS stuff (by: 18:00 Sunday, 12-25-2022)
3: [E][ ] meet up with friends (at: 18:30 Wednesday, 02-23-2022)
```
---
### Marking Tasks
#### `mark` - Mark the task as completed

Marks a task as completed.

Usage: `mark INDEX` 

Example:
```
todo hello world
mark 1
```

Expected outcome:
```
Nice! I have marked this task as done: [T][X] hello world
```
#### `unmark` - Unmark the task
Removes the mark from the task, marking it as incomplete.

Usage: `unmark INDEX`

Example: 
```
todo hello world
mark 1
unmark 1
```

Expected outcome:
```
I've unmarked this task: [T][ ] hello world
```
---
### Useful Features
#### `find` - Find a task in the list based on a keyword
Finds a task based on the given keyword. Case sensitive.

Usage: `find KEYWORD`

Example:
```
todo CS project planning
deadline CS stuff \by 2022-12-25 1800
event meet up with friends \at 2022-02-23 1830
find CS
```

Expected outcome: list of tasks with the given keyword.
```
1: [T][ ] CS project planning
2: [D][ ] CS stuff (by: 18:00 Sunday, 12-25-2022)  
```
#### `sort` - Sort the list based on date or content
Given a sort type of date or content, sorts the list based on that type.
The only available sort types are `date` and `content`. 

For date sorting, todo tasks will be pushed to the end of the list.

Usage: `sort SORTTYPE` 

Example
```
todo CS project planning
deadline CS stuff \by 2022-12-25 1800
event meet up with friends \at 2022-02-23 1830
sort date
sort content
```

Expected outcome:

For date sorting:
```
Sorting your tasks by date:
1: [E][ ] meet up with friends (at: 18:30 Wednesday, 02-23-2022)
2: [D][ ] CS stuff (by: 18:00 Sunday, 12-25-2022)
3: [T][ ] CS project planning
```
For content sorting:
```
sorting your tasks by content:
1: [T][ ] CS project planning
2: [D][ ] CS stuff (by: 18:00 Sunday, 12-25-2022)
3: [E][ ] meet up with friends (at: 18:30 Wednesday, 02-23-2022)
```

---
### Tagging Tasks
#### `tag` - Tag a task with a keyword
Tags the given word to a task. The tag must be single-worded (no spaces), and there can be multiple tags
per task.

Usage: `tag INDEX TAGNAME`

Example:
```
todo CS project planning
deadline CS stuff \by 2022-12-25 1800
event meet up with friends \at 2022-02-23 1830
tag 1 beautifulTag
```

Expected outcome:
```
Adding the following Tag #beautifulTag to [T][ ] CS project planning | #beautifulTag
```
#### `untag` - Untag the task
Deletes the tag of given word from a task.

Usage: `tag INDEX TAGNAME`

Example:
```
todo CS project planning
deadline CS stuff \by 2022-12-25 1800
event meet up with friends \at 2022-02-23 1830
tag 1 beautifulTag
untag 1 beautifulTag
```

Expected outcome:
```
Deleting the following Tag #beautifulTag from [T][ ] CS project planning
```
