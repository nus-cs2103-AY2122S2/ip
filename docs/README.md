# Duke, your personal secretary
> "Give me six hours to chop down a tree and I will spend the first four sharpening the axe." - Abraham Lincoln

Knowing exactly what you have to do will boost your productivity:rocket:!
**Duke** is a CLI based todo-List app that helps keep you track of what to do at which time, until when.
You can download the app [here](https://github.com/B1LLP4RK/ip). Image below shows the chat-like interface of the app.

![Contribution guidelines for this project](./Ui.png)

## commands
1. `todo {task name}`
    - adds a task to the tasklist
2. `event {event name} \at {when}`
    - adds a task that starts at a time to the tasklist
3. `deadline {event name} \by {when}`
    - adds a task that is due at a particular time to the tasklist
4. `mark {task number}`/`unmark {task number}`
    - marks/unmarks a task done
5. `delete {task number}`
    - delete a particular task
6. `find {keyword}`
    - shows the task containing the keyword, case insensitive
## development roadmap
- [x] enable search using a **keyword**
- [ ] enable changing **task orders**
- [ ] enable setting **priority** to tasks
- [ ] enable **priority-based ordering**
## known issues
- giving multiple keywords delimited by space for `find` crashes the app (will be fixed soon)
## patch notes
- no patch yet
