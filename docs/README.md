# User Guide

## Features

### Feature-Add tasks

Add a todo, deadline or event task!

### Feature-List tasks

List and view all the added tasks!

### Feature-Mark and unmark task

Mark and unmark finished tasks!

### Feature-Find

Find added tasks using keywords!

### Feature-Remind

List all the upcoming deadlines!

## Usage

### `todo`

Add a todo task.

Example of usage: 

`todo run`

Expected outcome:

Todo task added.

```
New task! you better do it.
[T][] run
You have 1 task(s) to do you lazy bum!
```

### `deadline`

Add a deadline task.

Example of usage: 

`deadline sleep/by 2019-01-01`

Expected outcome:

deadline task added.

```
New task! you better do it.
[D][] sleep (by:Jan 1 2019)
You have 2 task(s) to do you lazy bum!
```
### `event`

Add an event task.

Example of usage: 

`event fishing/at 2019-01-01`

Expected outcome:

event task added.

```
New task! you better do it.
[E][] fishing (at:Jan 1 2019)
You have 3 task(s) to do you lazy bum!
```
### `list`

list all added task(s).

Example of usage: 

`list`

Expected outcome:

all added being listed.

```
You forgetful baka... here are your tasks:
1. [T][] run
2. [D][] sleep (by:Jan 1 2019)
3. [][] fishing (at:Jan 1 2019)
```
### `find`

find task(s) containing the keyword(s).

Example of usage: 

`find fish`

Expected outcome:

task(s) with keyword(s) being printed.

```
Found! Not that i need a thanks or anything.
1.[E][] fishing (at:Jan 1 2019)
```
### `mark`

mark task as done.

Example of usage: 

`mark 1`

Expected outcome:

task being marked.

```
Alright! Aright, i will mark it down!
[T][X] run
```
### `unmark`

mark task as not done.

Example of usage: 

`unmark 1`

Expected outcome:

task being unmarked.

```
You didn't actually finish?!
[T][] run
```
### `remind`

list all upcoming deadline.

Example of usage: 

`remind`

Expected outcome:

unfinished deadline being listed.

```
Better do it fast! Your upcoming deadline:
1. [D][] sleep (by:Jan 1 2019)
```
### `bye`

exit the program after 1 second.

Example of usage: 

`bye`

Expected outcome:

program exitted after 1 second.

```
Finally, you're leaving!
It's not like i will miss you or anything...
```



