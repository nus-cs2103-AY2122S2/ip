# User Guide

## Features 

### Add Todo task
A basic ```Todo``` task containing a description can be added.

### Add Deadline task
A ```Deadline``` task that need to be done before a specific date/time can be added.

### Add Event task
An ```Event``` task that occurs at a specific date/time/location can be added.

### Add DoAfter task
A ```DoAfter``` task that needs to be done after a specific time/task can be added.

<details>
<summary>Date & Time Formatting of Tasks</summary>
Datetime messages containing dates in the form of YYYY/MM/DD, or with delimiters - | / . will be converted to MMM d yyyy format. Time in HHMM format will similarly be converted to HH:MM format.
</details>

### List tasks
Tasks that have been added so far can be listed.

### Delete task
Task at a specified index can be deleted.

### Search for tasks
Matching tasks containing a keyword can be found and listed. 

### Marking tasks as (in)complete

Tasks at a specified index can be marked as complete or incomplete.

## Usage

### `todo` - Add a Todo task

Adds a ```Todo``` task containing a description.
<details><summary>
Example of usage: </summary>

`todo collect primogems`

Expected outcome:

Task of the type ```Todo``` containing the description 'read a book' will be added.

```
Got it! I have noted down the following task in your list.
[T][ ] collect primogems
```
</details>

### `deadline` - Add a Deadline task

Adds a ```Deadline``` task containing a description and a datetime string.
<details><summary>
Example of usage: </summary>

`deadline wish on Zhongli's banner by 2022/02/18 2359 GMT +8`

Expected outcome:

Task of the type ```Deadline``` containing the description 'wish on Zhongli's banner' and formatted datetime string 'Feb 18 2022 23:59 GMT +8' will be added.

```
Got it! I have noted down the following task in your list.
Remember the deadline!
[D][ ] wish on Zhongli's banner (by: Feb 18 2022 23:59 GMT +8)
```
</details>

### `event` - Add an event task

Adds an ```Event``` task containing a description and a datetime string.
<details><summary>
Example of usage: </summary>

`event Windtrace festival at 2022/02/08 online`

Expected outcome:

Task of the type ```Event``` containing the description 'Windtrace festival' and formatted datetime string 'Feb 8 2022 online' will be added.

```
Got it! I have noted down the following task in your list.
Do be there on time!
[E][ ] Windtrace festival (at: Feb 8 2022 online)
```
</details>

### `doafter` - Add a DoAfter task

Adds a ```DoAfter``` task containing a description and a datetime string.
<details><summary>
Example of usage: </summary>

`doafter collect 900 primogems after reaching AR 15`

Expected outcome:

Task of the type ```DoAfter``` containing the description 'collect 900 primogems' and formatted datetime string 'reaching AR 15' will be added.

```
Got it! I have noted down the following task in your list.
Don't forget it!
[A][ ] collect 900 primogems (at: reaching AR 15)
```
</details>

### `list` - List all tasks

Lists all tasks thus far.
<details><summary>
Example of usage:</summary>

`list`

Expected outcome:
A printed list of all saved tasks and the total number of tasks.

```
Hmm... Paimon keeps a clear record in her diary.
 1. [T][ ] collect primogems
 2. [D][ ] wish on Zhongli's banner (by: Feb 18 2022 23:59 GMT +8)
 3. [E][ ] Windtrace festival (at: Feb 8 2022 online)
 4. [A][ ] collect 900 primogems (at: reaching AR 15)
You have 4 tasks on your list.
```
</details>

### `delete` - Delete a task

Deletes a task at a specified index.
<details><summary>
Example of usage:</summary>

```delete 2```

Expected outcome:

Task at the specified index will be removed. The resulting list will be printed.

```
Noted, the task has been scrubbed off the list!
 1. [T][ ] collect primogems
 2. [E][ ] Windtrace festival (at: Feb 8 2022 online)
 3. [A][ ] collect 900 primogems (at: reaching AR 15)
You have 3 tasks on your list.
```
</details>

### `do` - Do a task

Marks a task at a specified index as complete.
<details><summary>
Example of usage:</summary>

```do 1```

Expected outcome:

Task at the specified index will be marked complete.

```
Task successfully updated.
```
A subsequent ```list``` command will result in
```
Noted, the task has been scrubbed off the list!
 1. [T][✓] collect primogems
 2. [E][ ] Windtrace festival (at: Feb 8 2022 online)
 3. [A][ ] collect 900 primogems (at: reaching AR 15)
You have 3 tasks on your list.
```
</details>

### `undo` - Undo a task

Marks a task at a specified index as incomplete.
<details><summary>
Example of usage:</summary>

```undo 1```

Expected outcome:

Task at the specified index will be marked complete.

```
Task successfully updated.
```
A subsequent ```list``` command will result in
```
Noted, the task has been scrubbed off the list!
 1. [T][ ] collect primogems
 2. [E][ ] Windtrace festival (at: Feb 8 2022 online)
 3. [A][ ] collect 900 primogems (at: reaching AR 15)
You have 3 tasks on your list.
```
</details>


### `find` - Search for keyword

Finds and lists tasks containing a keyword. The keyword is case-sensitive.
<details><summary>
Example of usage:</summary>

```find collect```

Expected outcome:

The list of tasks containing the keyword 'collect', and the number of such tasks.

```
Here are the matching tasks in your list:
 1. [T][ ] collect primogems
 2. [A][ ] collect 900 primogems (at: reaching AR 15)
You have 2 tasks on your list.
```
</details>