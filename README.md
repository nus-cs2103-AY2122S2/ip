
# _Ducky User Guide_ 🦆
> "Be like a duck. Calm on the surface, but always paddling like the dickens underneath." - Micheal Caine ([Source](https://quotecatalog.com/quote/michael-caine-be-like-a-duck-baVAqop#:~:text=%E2%80%9CBe%20like%20a%20duck.,paddling%20like%20the%20dickens%20underneath.%E2%80%9D))

### Ducky is an interactive tool for you to track your tasks in one place. It's
- Cute 🐣
- ~~Easy~~ **Very _easy_** to use
- Text-based 🧑‍💻

<img width="592" alt="Ui" src="https://user-images.githubusercontent.com/61443625/156412118-9594a743-3d6d-4911-9521-6799d0a0556c.png">

## Features 

### Adding tasks

Let Ducky add either a todo, event, or deadline task with a task description. 🌟

### Deleting tasks

Made a mistake? 😱 Don't worry! Ducky can help to delete existing tasks.

### Listing tasks

Want to see the tasks you've made? 🗒 

Ducky can show you the tasks you've made so far.

### Marking/ Unmarking tasks

Completed a task? ✔️

Ducky can help you mark and unmark the tasks you've made so far.

### Saving your tasks

Want to save your tasks in a local file? ⬇️

Ducky can help save your tasks in a text file after you exit the GUI. 

## Usage

### `help` - Viewing help

Shows a message on the different functionalities Ducky can do.

Example of usage: 
`help`

Expected outcome:
Shows a list of commands that the user can do with Ducky.

```
> Type 'list' to see what you have in your task list
> Type 'todo <message>' to put a todo in your list
> Type 'deadline <message> /by <deadline>' to put a deadline in your list.
    - Date must be in 'DD MMMM YYYY' format or given in days.
    - e.g. mon or monday
> Type 'event <message> /at <date>' to put an event in your list.
    - Date must be in 'DD MMMM YYYY' format or given in days.
    - e.g. mon or monday
> Type 'mark <x>' to mark a task in your list
> Type 'unmark <x>' to unmark a task in your list
> Type 'find <x>' to find a task in your list
    - x is the word in task description to be found
```


### `todo` - Adding a todo
Adds a todo type task to Ducky.

Format: `todo <description>`

Example of usage:

`todo homework 5`

Expected outcome: Ducky adds a todo task to the list.

```
I have added the following todo:
[T][ ] homework 5
You now have 2 tasks
```

### `event` - Adding an event

Adds an event task with the given description and event date to Ducky.

Format: `event <description> /at <time>`

Example of usage:

- `event line dancing /at monday`
- `event line dancing /at mon`
- `event line dancing /at 21 february 2022`

Expected outcome: Ducky adds the event to the list

```
I have added the following event:
[E][ ] line dancing (at: 21 February 2022)
You now have 3 tasks
```

### `deadline` - Adding a deadline

Adds a deadline task with the given description and due date to Ducky.

Format: `deadline <description> /by <time>`

Example of usage:

- `deadline buy groceries /by monday`
- `deadline buy groceries for cs /by mon`
- `deadline buy groceries /by 21 february 2022`

Expected outcome: Ducky adds the deadline to the list.

```
I have added the following deadline:
[D][ ] buy groceries (at: 21 February 2022)
You now have 4 tasks
```

### `delete` - Deleting a task

Deletes a task with the given index in Ducky.

Format: `delete <index of task>`

Example of usage:
- `delete 4`

Expected outcome: Ducky deletes the task from the list and shows what was deleted.

```
I have removed this from your tasks:
[D][ ] buy groceries (at: 21 February 2022)
You now have 3 tasks
```

### `list` - Show all tasks

Shows all the tasks that Ducky has recorded.

Example of usage:
- `list`

Expected outcome: Ducky shows the list of tasks you have added.

```
Here are your tasks:
1. [T][ ] walk the dog
2. [T][ ] homework 5
3. [E][ ] line dancing (at: 21 February 2022)
```

### `find` - Find a task

Finds a task with the given key phrases in Ducky.

Format: `find <keyword>`

Example of usage:
- `find dancing`

Expected outcome: Ducky finds the task with the word "dancing".

```
Here are the tasks that contains "dancing":

1. [E][ ] line dancing (at: 21 February 2022)
```

### `mark` - Mark a task

Marks a task with the given index as done from Ducky.

Format: `mark <index>`

Example of usage:
- `mark 3`

Expected outcome: Ducky marks the third task in the list.

```
Ok, I have marked the following task:
[E][X] line dancing (at: 21 February 2022)
```

### `unmark` - Unmark a task

Unmarks a task with the given index as done from Ducky.

Format: `unmark <index>`

Example of usage:
- `unmark 3`

Expected outcome: Ducky unmarks the third task in the list.

```
Sure, I have unmarked the following task:
[E][ ] line dancing (at: 21 February 2022)
```
### `bye` - Exit Ducky

Exits Ducky and saves the tasks to the duke.txt file.

Format: `bye`

Example of usage:
- `bye`

Expected outcome: Ducky says bye and hopes to see you again. 🥺

```
Your tasks have been saved in duke.txt
This window will close in 3 seconds
Bye~ Hope to see you again soon!*✧･ﾟ:*(*❦ω❦)*:･ﾟ✧*
```

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/duke/Launcher.java` file, right-click it, and choose `Run Launcher.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see a Ducky Java window opening.
