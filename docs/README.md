# User Guide
Mum is always here to help you organise your tasks. Mum acts as a desktop messaging app that converses with you via 
texting. Talk to Mum! She will cater to you.
## Contents
1. [Quick Start](#quick-start)
2. [Overview](#overview)   
3. [Features](#features)

## Quick Start
1. This application only works on a computer.
2. Download the working version of Mum [here](http://google.com)
3. Open the jar file that you just downloaded.
4. Start organising your task!

## Overview
### Store Tasks
Mum helps to store tasks to be done. She will remember where you last finished off and give you details of tasks to be
completed.

### Remember Dates
Mum can keep track of your tasks with dates attached to them. This helps you to know when is the due date of tasks.

### Organise Tasks
Mum will organise your tasks into 3 categories: **To Do**, **Event**, **Deadline**.
She will not ~~forget~~ what you have completed or yet to complete, unless you told her to do so.

![Ui](Ui.png)


## Features
- [`commands`](#commands)
- [`list`](#list)
- [`todo`](#todo)
- [`event`](#event)
- [`deadline`](#deadline)
- [`mark`](#mark)
- [`unmark`](#unmark)
- [`find`](#find)
- [`delete`](#delete)
- [`bye`](#bye)
- [`palces`](#places)
- **[All Commands](#all-commands)**

## `commands` 
Provide a summary of all commands Mum understands
#### Usage
Key in this into the text box and press enter or click the enter button.
```
commands
```
Expected outcome:
- [x] Application display all commands understandable 

## `list`
List all tasks presently known to Mum
#### Usage
Key in this into the text box and press enter or click the enter button.
```
list
```
Expected outcome:
- [x] Application lists out all tasks it knows

## `todo`
A **todo** task type with a simple description of the task 
#### Usage
Key in this into the text box and press enter or click the enter button. \
`todo` `SPACE` `description`
```
todo buy clothes
```
Expected outcome:
- [x] Application adds a todo task into the list

## `event`
A **event** task type with a simple description of the task, the date and time of the event \
You can add in an optional location to this event so that Mum can remind you where this event will take place
#### Usage
Key in this into the text box and press enter or click the enter button. \
`event` `SPACE` `description` `SPACE` `/at` `date` `SPACE` `time` \
or \
`event` `SPACE` `description` `SPACE` `/at` `date` `SPACE` `time` `SPACE` `@` `location`
```
event farmer's market /at 2022-02-02 08:00 
event farmer's market /at 2022-02-02 08:00 @Changi
```
Expected outcome:
- [x] Application adds an event task into the list with a date and time

## `deadline`
A **deadline** task type with a simple description of the task, the due date and time of the task
#### Usage
Key in this into the text box and press enter or click the enter button. \
`deadline` `SPACE` `description` `SPACE` `/by` `date` `SPACE` `time`
```
deadline math homework /by 2022-03-01 18:00
```
Expected outcome:
- [x] Application adds a new deadline onto list with a due date and time

## `mark`
Cross out a task in the list that is done or no longer valid but still keeping it in the list
#### Usage
Key in this into the text box and press enter or click the enter button. \
`mark` `SPACE` `number`
```
mark 1
```
Expected outcome:
- [x] Application mark 1st task on the list

## `unmark`
Change back crossed out task in the list that is done or no longer valid to unfinished
#### Usage
Key in this into the text box and press enter or click the enter button. \
`unmark` `SPACE` `number`
```
unmark 1
```
Expected outcome:
- [x] Application unmarks 1st task on the list

## `find`
Find a task in list based on the keyword inputted by user
#### Usage
Key in this into the text box and press enter or click the enter button. \
`find` `SPACE` `keyword`
```
find clothes
```
Expected outcome:
- [x] find all tasks containing keyword ***clothes***

## `delete`
Delete a task in list and storage. Free mum of this task in the future.
#### Usage
Key in this into the text box and press enter or click the enter button. \
`delete` `SPACE` `index number`
```
delete 2
```
Expected outcome:
- [x] Application delete 2nd task on the list

## `bye`
Turn off Mum to let her rest, and the application closes
#### Usage
Key in this into the text box and press enter or click the enter button. 
```
bye
```
Expected outcome:
- [x] Application closes

## `places`
search tasks that are happening at this place
#### Usage
Key in this into the text box and press enter or click the enter button.
```
places orchard
```
Expected outcome:
- [x] tasks with location in Orchard will be listed out.

## All Commands
|Commands | Format |
|---------|--------|
|**Commands** | `commands` |
|**List** | `list` |
|**To Do** | `todo`< space >`description` |
|**Event** | `event`< space >`description`< space >`/at`< space >`YYYY-MM-DD`< space >`HH:MM`|
|**Deadline** | `deadline`< space >`description`< space >`/by`< space >`YYYY-MM-DD`< space >`HH:MM`|
|**Mark** | `mark`< space >`index` |
|**Un-Mark** | `unmark`< space >`index`|
|**Find** | `find`< space >`keyword` |
|**Delete** | `delete`< space >`index` |
|**Bye** | `bye` |
|**Places** | `places` or `places`< space >`location` |

