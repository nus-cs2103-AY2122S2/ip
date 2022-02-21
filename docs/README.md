# User Guide
Koro Task Manager

## Quick Start

1. Ensure you have Java `11` installed in your computer.
2. Download the latest duke.jar from [here](https://github.com/janald99/ip)
3. Double-click the file to start the app.
4. Type the commands in the text box at the bottom and press Enter
to execute it. e.g typing `help` and pressing Enter will display a detailed guide.
5. Refer to the Features below for details of each command.

## Features 

### Adding a Task

- Adding a Todo task[^1] 
    - `todo (task description)`
  
- Adding a Deadline task[^2]
    - `deadline (task description) /by`
      
- Adding an Event task[^3]
    - `event (task description) /at (location)`
    

[^1]: *Todo*
A **task** that you ought to do, without any strict deadlines.

[^2]: *Deadline*
A **task** with a *deadline*

[^3]: *Event*
A **task** with a specific *location*.

### Deleting a Task

`delete (task number)`: deletes the task associated with the task number.

### Marking and Unmarking a Task as done

- `mark (task number)`: marks a task as *done*, represented by an `[X]`
- `unmark (task number)`: marks the task as *not done*, represented by a `[ ]`
    
### Printing the entire TaskList

`list`: prints the current tasklist.

### Searching for a Task

`find (keyword)`: displays tasks in the list that contain the given keyword.

### View Help

`help`: a user-friendly manual will be displayed to guide you.


```
Koro Task Manager is a user friendly ...
Here at the list of commands and what they do:
...
...
```

## FAQ
**Q**: How do I save my tasks after updating it?

**A**: Your tasks will automatically be saved in the file. Fret not!

![This is an image](https://janald99.github.io/ip/Ui.png)



## Enjoy managing your tasks!