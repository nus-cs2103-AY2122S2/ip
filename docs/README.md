# Duke User Guide

## Features
* The addition of Tasks (Events, Deadlines, Todos) to a Calendar
  * Adding singular tasks 
  * Adding recurring events (e.g. repeats every week)
* Listing all the tasks in the calendar
* The deletion of Tasks
* Marking tasks as complete/incomplete
* Find tasks by keyword
* Saving tasks to a local file
* Loading of tasks from local file on program startup

### Adding Tasks
Tasks of each specified type can be added to the calendar
#### 1. Creating a new Todo
Adds a new todo to the calendar

Example of usage:

`todo assignment`

Expected Outcome:

```
Got it! I've added this task: 
    [T][] assignment
Now you have 1 tasks in the list
```

#### 2. Creating a new Deadline
Adds a new deadline to the calendar

Example of usage:

`deadline report /by 22 Feb 2019`

Expected Outcome:

```
Got it! I've added this task:
    [D][] report (by: 22 Feb 2019)
Now you have 2 tasks in the list
```

#### 3. Creating a new Event
Adds a new event to the calendar

Example of usage:

`event concert /at 22 Feb 2019`

Expected Outcome:
```
Got it! I've added this task:
    [E][] concert (at: 22 Feb 2019)
Now you have 3 tasks in the list
```
#### 4. Adding recurring events
Adds a recurring event at set intervals until the specified end date

Example of usage:

`recurring event meeting /at 1 Mar 2019 /interval 7 /ends 8 Mar 2019`

Expected Outcome:
```
Got it! I've added all the recurring tasks to the calendar
Now you have 5 tasks in the list
```
### Listing Tasks in the Calendar

Lists all the tasks in the calendar

Example of usage:

`list`

Expected Outcome:
```
1. [T][] assignment
2. [D][] report (by: 22 Feb 2019)
3. [E][] concert (at: 22 Feb 2019)
4. [E][] meeting (at: 1 Mar 2019)
5. [E][] meeting (at: 8 Mar 2019)
```
### Deleting Tasks from the Calendar
Deletes a task at the specified index in the list

Example of usage:
`delete 1`

Expected Outcome:
```
Alright. I've deleted this task:
    [T][] assignment
```
### Marking Tasks as complete/incomplete

#### 1. Marking a task as complete
Marks the task at the specified index as complete

Example of usage:

`mark 1`

Expected Outcome:

```
Nice! I've marked this task as complete:
    [D][X] report (by: 22 Feb 2019)
```
### 2. Marking a task as incomplete
Marks the task at the specified index as incomplete

Example of usage:

`unmark 1`

Expected Outcome:
```
Alright. I've marked this task as incomplete:
    [D][] report (by: 22 Feb 2019)
```

### Find Task by keyword
Find tasks from list with the specified keyword

Example of usage:

`find report`

Expected Outcome:
```
1: [D][] report (by: 22 Feb 2019)
```

### Saving data to local storage
Saves data to local storage for reload on next startup

Example of usage:

File **auto-saves** on update 

Expected Outcome:

Save file is stored for future program usage

### Loading data from local storage
Loads data from local storage on startup if file is present, otherwise start new file

Example of usage:

File is **auto-loaded** from local storage on update

Expected Outcome:

Save file is loaded for current program use
