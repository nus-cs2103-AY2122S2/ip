# User Guide
Welcome to Duke! Your task Manager for all todo items, deadlines and events.

> Duke aims to also be your motivational assistant by giving you a daily quote of the day to boost your spirits!

![Sample Image for Duke](./Ui.png)

## Features 

### Todo, Deadlines, Events

Duke allows you to add Todo items, deadlines, and events which you may have!
Deadlines include an automatic date parser (for specific formats) to format your input date nicely!

### Mark, Unmark and Delete your items!

Duke allows you to mark, unmark and delete items which you may have!

### Always saves your tasks

To keep track of your tasks over time, Duke will always save your tasks as long as you exit with a "bye" statement!

### Reminders

Duke can give u reminders of deadlines you have within a specific time frame!

### Find

Duke can find and filter specific tasks for you based on your input!

## Usage

### `todo` - Adds a todo item into your task list!

Example of usage: 

`todo Finish your Painting!`

Expected outcome:

```
Fantabulous! You have added this Todo Item:
[T][] Finish your Painting
```

### `deadline` - Adds a deadline item into your task list!

Example of usage:

`deadline submit CS2103 Homework /by 2022-02-20`

Expected outcome:

```
Perfect! You have added this Deadline Item:
[D][] submit CS2103 Homework (By: Feb20 2022)
Remember to stick to your objective date!
```

### `list` - list out all items in your task list

Example of usage:

`list`

Expected outcome:

```
Here are your tasks:
1: [T][] Finish Painting
2: [D][Done!] Return Book to Library (By: Feb 20 2022)
These are all your tasks! Only 1 more remaining! Keep going!
```

### `find` - find some tasks

Example of usage:

`find Painting`

Expected outcome:

```
Here are your tasks that satisfy your criteria:
1: [T][] Finish Painting
```

### `remind` - remind you of upcoming deadline items

Example of usage:

`remind /before 2022-02-21`

Expected outcome:

```
Here are the upcoming deadlines that you have before Feb 21 2022:
1: [D][Done!] Return Book to Library (By: Feb 20 2022)
```

### `mark` - mark items for completion

Example of usage:

`mark 2`

Expected outcome:

```
Congratulations on completing the task!
[D][Done!] Return Book to Library (By: Feb 20 2022)
```

### `unmark` - unmark items for completion

Example of usage:

`unmark 2`

Expected outcome:

```
I have un-marked this task!
[D][] Return Book to Library (By: Feb 20 2022)
```

### `delete` - delete items from list

Example of usage:

`delete 2`

Expected outcome:

```
I have removed [D][] Return Book to Library (By: Feb 20 2022)
```

### `bye` - exits from program and saves your tasks!

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope I've motivated you as much as I could have, and SMILE :D
Closing this window now!
```


