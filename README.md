# DUKE

> "Clutter is nothing more than postponed deadlines" ~ Barbara Hemphill ([source](https://nacm.org/pdfs/webinars/clutter-is-postponed-exercise-1031.pdf))

Duke is an organisational tool that helps you stay clutter free and keep track of your tasks!
It is:
 - Text-based
 - Easy to use
 - ~~Helpful~~ Extremely helpful

You can:
1. Add 3 kinds of tasks - Todo, Dealines, Events
2. Mark the tasks as done
3. List them down for easy viewing
4. Set priorities for your tasks
4. Delete items once you are done with them 👍

Examples of code syntax
To add Events, simply write `event <Name of Event> /at YYYY:MM:DD HH:MM`
> event Camp /at 2022:08:09 08:00
To set priority, simply write `priority <index> <priority>`
Priorities: low, medium, high
> priority 1 high


## List of Features
 - [x] Add 3 new tasks: Todo. Deadline, Event
 - [ ] Edit tasks
 - [ ] Export to calendar

If you Java programmer, you can use it to practice Java too. Here's the main method:
```java
public static void main(String[] args) {
    new Duke().listen();
}
```