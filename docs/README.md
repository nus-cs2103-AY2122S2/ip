# Lily User Guide
> “Why is your list so full of nonsense” – Lily

![App preview](Ui.png)

Lily is a CLI-based task manager. It attemps to embody the personality of a character in Duolingo of the same name. She is a disinterested teen with purple hair.

![Lily from Duolingo](intro.gif)

## Quick Start 
1. Check if you have Java 11 on your computer
2. Download the latest `Lily.jar` from [here](https://github.com/ian-from-dover/ip/releases)
3. Place it where it can make a `data` folder beside the .jar
4. Double click `Lily.jar` to start the app.

## Command List
1. `bye` exits the program
2. `list` the tasks you have to do
3. `find` tasks with the search word  
4. `todo` create a todo
5. `deadline` create a deadline with a date to do it by
6. `event` create an event with a date it's happening at
6. `job` create a job that needs a number of hours to complete
7. `mark` a task as done
8. `unmark` a task as undone
8. `delete` a task

## Features

### Dates
Specify dates when creating `deadlines` and `events` in the form YYYY-MM-DD

### Auto-save
Lily automatically creates a `data` folder and saves your list to it if it has tasks. It automatically loads the data the next time you open it.

## Examples of Usage

### Quit Lily
`bye`

Expected outcome: Saves your list to the `data` folder and quits.

```
if your list had stuff, i've saved it.
don't bug me again. bye (´-ω-`)

*celebrating sticker*
```

### List tasks
`list`

Expected outcome: Displays the tasks on your list

```
you told me you had to:
1.[T][ ] Go out with Duo on another day
2.[T][ ] Sunday window shopping by myself
```

### Find a task
`find day` or `search day`

Expected outcome: Displays all the tasks which have 'day' in their description. The search is case-sensitive and does not search for dates.

```
ah. here are your tasks with "day" in them.
1.[T][ ] Go out with Duo on another day
2.[T][ ] Sunday window shopping by myself
```

### Add a Todo
`todo Dye hair deep purple`

Expected outcome: Adds a new `Todo` with `Dye hair deep purple` as its `Description`.

```
i've dumped this into your list:
[T][ ] Dye hair deep purple
so now you have 3 tasks happening. hope you're happy
```

### Add a Deadline
`deadline Complete genocide route on undertale /by 2022-02-11`

Expected outcome: Adds a new `Deadline` with `Complete genocide route on undertale` as its `Description` by 11th Feb 2022.

```
i've dumped this into your list:
[D][ ] Complete genocide route on undertale (by: 11 Feb 22)
so now you have 4 tasks happening. hope you're happy
```

### Add an Event
`event EMONIGHT /at 2022-03-02`

Expected outcome: Adds a new `Event` with `EMONIGHT` as its `Description` happening on 2nd March 2022.

```
i've dumped this into your list:
[E][ ] EMONIGHT (at: 02 Mar 22)
so now you have 5 tasks happening. hope you're happy
```

### Add a Job
`job Shake Shack shift /for 9`

Expected outcome: Adds a new `Job` with `Shake Shack shift` as its `Description` which needs `9.0`h to be done.

```
i've dumped this into your list:
[J][ ] Shake Shack shift (needs: 9.0h)
so now you have 6 tasks happening. hope you're happy
```

### Mark and unmark tasks
`mark 2` or `do 2` or `done 2`

Expected outcome: Marks the 2nd task as done

```
oh. you've finished it. okay
2.[T][X] Sunday window shopping by myself

*clap sticker*
```

`unmark 2`

Expected outcome: Unmarks the 2nd task

```
hey, you gotta get it done later, okay?
2.[T][ ] Sunday window shopping by myself

*mocking sticker*
```

### Delete a task
`delete 2` or `remove 2`

Expected outcome: Removes the 2nd task from the list

```
hmph. then why did you make me track your
[T][ ] Sunday window shopping by myself

anyway, now you're left with:
1.[T][ ] Go out with Duo on another day
2.[T][ ] Dye hair deep purple
3.[D][ ] Complete genocide route on undertale (by: 11 Feb 22)
4.[E][ ] EMONIGHT (at: 02 Mar 22)
5.[J][ ] Shake Shack shift (needs: 9.0h)

*mocking sticker*
```