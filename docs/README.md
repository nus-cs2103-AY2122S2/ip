
# User Guide

## Features

### Adding tasks: `todo`, `event`, `deadline`
Users would be able to create 3 different types of tasks that they can track.
1. `todo <task description>`
2. `event <task description> /at <dd/MM/yyyy HH:mm>`
3. `deadline <task description> /by <dd/MM/yyyy HH:mm>`

#### Usage
- `deadline complete IP user guide /by 16/02/2022 23:59`

---

### Clear list
Users would be able to clear the entire list in one command.
- `clear`

>❗️ Use at your own discretion.

---

### Delete/remove tasks
Users would be able to remove task(s) that they no longer want to keep track of by specifying the entry number of the task.
- `remove <entry number(s)>`

#### Usage
- `remove 9 1 8 3` — This would remove tasks 1, 3, 8, and 9 from the list.

---

### Exit
Users would be able to exit from the application using the user-input box.
- `exit`

>⚠️ Users are recommended to exit doing so in order to save the changes they have made to their list.

---

### Finding tasks
Users would be able to find tasks based on the description of it.
- `find <keyword>`

#### Usage
- `find book` — This would show a list of tasks that contain the word book such as "return book" or "confirm movie  booking".

---

### Help
Users would be able to get a list of commands in the application along with how to use them.
- `help`

---

### List tasks
Users would be able to display the entire list of tasks that they had created and/or modified.
- `list`

---

### Marking/un-marking tasks
Users would be able to mark/un-mark tasks in the list based on their entry number.
- `mark <entry number>`
- `unmark <entry number>`

#### Usage
- `mark 3` — This would mark the 3rd entry in the list.
- `unmark 4` — This would unmark the 4th entry in the list.

---

### View tasks/events that happen on a specified date
Users would be able to view what events they have on a certain day.
- `view <dd/MM/yyyy>`

#### Usage
- `view 14/02/2022` — This would display the tasks/events that are happening on 14 Feb 2022.