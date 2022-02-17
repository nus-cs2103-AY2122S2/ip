# Project Description

This is a greenfield Java project for CS2103T Software Engineering. 

>  “Any fool can write code that a computer can understand. 
> Good programmers write code that humans can understand.”
> -Martin Fowler.

## What is Nexus?
***
Nexus is a basic Todo List with a built-in interface for users to interact with. 

**Features include:**
- [x] ~~Adding new Tasks~~ **IMPROVED! Able to add specific tasks such as: Todo, Deadlines and Events!**
- [x] Deleting Tasks.
- [x] Mark and Unmark tasks.
- [x] List the task in an interface.
- [ ] ~~Help Menu~~.Dropped due to the lack of time.

## Instructions
***

##Commands and their purpose
1. ``list`` shows the list of tasks that has yet to be done.
2. ``mark <integer>`` marks the task as completed.  
3. ``unmark <integer>`` un-marks the task as not completed.
4. ``todo <task>`` creates a new task that user has to do.
5. ``deadline <task> /by <date>`` creates a new deadline and a date in which the task has to be completed by.
6. ``event <task> /at <date>`` creates a new event and a date in which the event is held at.
7. ``find <description>`` finds tasks that matches the description.
8. ``delete <integer>`` deletes the specific task from the list.

note: `<integer>` represents the order of the specific task in the list

**All you need to do is**
1. Download the .jar file from [here](https://github.com/tandeshao/ip/releases/tag/shadowJar).
2. Open the command prompt/terminal.
3. Navigate to the directory of the .jar file.
4. Ensure that you have jdk set up in your local environment.
5. Run ``java -jar Launcher.jar``.
6. Let it manage your tasks for you 😃

***
If you are interested. Here's the ``Launcher`` class:
```java
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
```