# User Guide
> “Why is your list so full of nonsense” – Lily

![App preview](Ui.png)

Lily is a CLI-based task manager. It attemps to embody the personality of a character in Duolingo of the same name. She is a disinterested teen with purple hair.

![Lily from Duolingo](https://c.tenor.com/8WRjkoq7QF0AAAAC/duolingo-lily.gif)

## Quick Start 
1. Check if you have Java 11 on your computer
2. Download the latest `Lily.jar` from [here](link)
3. Place it where it can make a `data` folder beside the .jar
4. Double click `Lily.jar` to start the app.

## Command List
1. `bye` exits the program
2. `list` the tasks you have to do
3. `find` tasks with the search word  
4. `todo` create a todo
5. `deadline` create a deadline with a date to do it by
6. `event` create an event with a date it's happening at
7. `mark` an event as done
8. `unmark` an event as undone

## Features

### Dates
Specify dates when creating `deadlines` and `events` in the form YYYY-MM-DD

### Auto-save
Lily automatically creates a `data` folder and saves your list to it if it has items. It automatically loads the data the next time you open it.

## Examples of Usage

### Find a task
`find 21`

Expected outcome: Displays all the tasks which have '21' in them

```
expected output
```

### Add a Todo
`todo Dye hair deep purple`

Expected outcome: Adds a new `Todo` with `Dye hair deep purple` as its `Description`.

```
expected output
```

### Add a Deadline
`deadline Complete genocide route on undertale /by 2022-02-11`

Expected outcome: Adds a new `Deadline` with `Complete genocide route on undertale` as its `Description` by 11th Feb 2022.

```
expected output
```

### Add an Event
`event EMONIGHT /at 2022-03-02`

Expected outcome: Adds a new `Event` with `EMONIGHT` as its `Description` happening on 2nd March 2022.

```
expected output
```

### Mark and unmark items

`mark 2`

Expected outcome: Marks the 2nd item as done

```
output
```

`unmark 2`

Expected outcome: Unmarks the 2nd item

```
output
```