# Duke
> "A monkey hitting keys at random on a typewriter keyboard for an infinite amount of time will almost surely type any given text, such as the complete works of William Shakespeare." - Infinite Monkey Theorem ([source](https://en.wikipedia.org/wiki/Infinite_monkey_theorem))

Duke is a spy monkey that helps you manage your calendar so you can focus on working hard and completing your tasks. It's

- text-based (typing only!)
- easy to learn (simple commands)
- high performance

All you need to do is,

1. download it from [here](https://github.com/aidanaden/ip/releases/download/A-Release/ip-Duke-0.2.jar).
2. run the command `java -jar ip-Duke-0.2.jar` (make sure you have java 11 installed)
3. add your tasks/events and their respective start/end times.
4. run the `list` command to view your tasks!

And it is **FREE!**

Features:

- [x] Managing tasks
- [x] Managing deadlines
- [x] Tagging tasks

Commands:
1. `deadline` - creates new deadline task
2. `event` - creates new event task
3. `todo` - creates new todo task
4. `mark` - marks a task as complete
5. `unmark` - unmarks a task as not complete
6. `delete` - deletes a task at a given index
7. `find` - list down tasks that contain a given string
8. `tag` - tags a task at a given index
9. `list` - list down all tasks
10. `bye` - closes Duke and saves all tasks locally

## Examples:
1. To create a simple task:
```
todo <name of task>
```

2. To create a task with a deadline on 4th of December 2020:
```
deadline <name of task> /by 4/12/2020 1800
```

3. To create a task with a start date and end date
```
event <name of task> /at 3/12/2019 1900 to 2100
```

4. To list tasks:
```
list
```

5. To mark a task as completed (after running `list`):
```
mark <index number of task>
```

6. To unmark a task as completed (after running `list`):
```
unmark <index number of task>
```

7. To delete a task:
```
delete <index number of task>
```

8. To search for a task that contains a specific term:
```
find <search term>
```

9. To tag a task with a specific term:
```
tag <index number of task> <tag term>
```

10. To end the program and save the tasks to `data/list.txt`:
```
bye
```