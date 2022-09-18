# Conan
**_Conan_** is a task manager, who can hep you manage your day easily :v:.
Click (here)[https://github.com/Harish-coding/ip/releases/download/A-Release/Launcher.jar] to download! :arrow_down:

> "The key is not spending time, but investing it." - Stephen R. Covey [(Source)](https://www.inc.com/dan-scalco/22-time-management-quotes-to-inspire-you-to-achieve-your-goals.html) </br>

Conan draws inspiration from [Project Duke](https://nus-cs2103-ay2122s2.github.io/website/se-book-adapted/projectDuke/index.html), that was designed to help learn Software Engineering Techniques.

## Quick Links
+ [Quick Start](README.md#Quick-Start)</br>
+ [Features](README.md#Features)</br>
+ [User Guide](README.md#User-Guide)</br>
+ [Glossary](README.md#Glossary)</br>
+ [FAQ](README.md#FAQ)</br>
+ [Setting up in Intellij](README.md#Setting-up-in-Intellij)

## Quick Start

1. Download the jar file to your computer.
2. Run the command  `java -jar ip.jar` on ubuntu or CLI.
3. Try the sample commands given in the user guide.

## Features
+ Run _**Conan**_ on your command line.
+ _**Conan**_ can remember your tasks from previous runs and can recollect them if you want to. :wink:
+ _**Conan**_ warns you about possible clashes between your tasks.
+ _**Conan**_ does not allows duplicate tasks to exist.

## User Guide

Once you run the program in your Command line, a window will open through which Conan will </br>
introduce himself and ask for your name.
Once you are acquainted you could tell Conan the tasks you want to do.

#### Tasks

There are :four: kinds of tasks that Conan can store.
1. **Todo** - Keeps track of the task that do not have to be completed without sometime. </br> 
   Format: : todo {Task} in the command line for such commands.
2. **Event** - Any event that you have to attend an certain date and time, Conan can keep track of that for you. :stopwatch:
   Format: event {Task} /at YYYY-MM-DD HHMM
3. **Deadline** - Tasks that need to be completed within a time frame. :hourglass:
   Format: deadline {Task} /by YYYY-MM-DD HHMM
4. **Task** - any general tasks to keep track of.
   Format: {Task , more than 2 words}

**Note**: For Event and Deadline, time argument HHMM is not necessary to enter, however the date must be entered.

#### Operations

+ **add** : add a task to track.

  _Format_ : refer to [Tasks](README.md#tasks)</br>
  
  _Possible Error Warning_:</br>
  + If an empty task argument is added then task missing exception is raised.
  + For Event, if time agrument identifier, '/at' is not used time argument missing exception is raised.
  + For Deadline, if time agrument identifier, '/by' is not used time argument missing exception is raised.
  + For Event and Deadline, if the Date is not in {YYYY-MM-DD} format, error will be issued.
  + For Event and Deadline, if a time argument is used, it should be of {HHMM} format or else error will be issued.
  
  _Usage Example_:</br>
  + make pancakes
    > I have added: make pancakes, to your list of tasks.</br>
    > Number of tasks up to now: 1
  + todo do laundry
    > I have added: do laundry, to your list of tasks.</br>
    > Number of tasks up to now: 2
  + deadline submit cs3243 project /by 2022-02-20
    > I have added: submit cs3243 project, to your list of tasks.</br>
    > Number of tasks up to now: 3
  + event GEQ lecture /at 2022-02-25 0800
    > I have added: GEH lecture, to your list of tasks.</br>
    > Number of tasks up to now: 4

+ **bye** : To close the program. Conan stores all your tasks so that you can visit them later.</br>

  _Format_: bye</br>
  
  _Possible Error Warnings_:</br>
  + NONE
  
  _Usage Examples_:</br>
  + bye
    > Goodbye, Jules</br>
    > Hope I helped you complete your tasks!</br>
    > Have a great day ahead, enjoy ! (^-^)/</br>
    > Hope to see you next time! </br>
  
+ **delete** : Delete that task from the list.</br>
 
  _Format_: delete {Task Number}</br>
  
  _Possible Error Warnings_:</br>
  + If the argument is missing the task number, error is raised.
  + If the task index number is invalid, that is if it is less than or equal to 0 or greater than </br>
  the total number of tasks, an error warning is issued. 
  
  _Usage Examples_:</br>
  + delete 2
    > The following task has been removed from the list :
    > [D][ ] return book (by: FRIDAY 1 MARCH 2019)
  + delete 4
    > Task num: 4 isn't present. Type 'list' command to view the task numbers.</br>
    > Please try again!</br>
  
+ **due-on** : To view tasks that are due on this date.</br>
 
  _Format_: due-on {YYYY-MM-DD}</br>
  
  _Possible Error Warnings_:</br>
  + Issues an warning id the date argument is empty.
  + Issues an warning if the date is not in {YYYY-MM-DD} format.
  
  _Usage Examples_:</br>
  + due-on 2022-03-01
    > The following tasks are due on TUESDAY 1 MARCH 2022:<br/>
    > 1.[E][ ] Finance Seminar (at: TUESDAY 1 MARCH 2022)</br>
    > 2.[D][ ] finish cp assignment (by: TUESDAY 1 MARCH 2022)</br>
  + due-on 2022-02-20
    > Seems like you are free on SUNDAY 20 FEBRUARY 2022
  
+ **due-before** : To view tasks that are due before this date.</br>

  _Format_: due-before {YYYY-MM-DD}</br>
  
  _Possible Error Warnings_:</br>
  + Issues an warning id the date argument is empty.
  + Issues an warning if the date is not in {YYYY-MM-DD} format.
  
  _Usage Examples_:</br>
  + due-before 2022-03-02
    > The following tasks are due before WEDNESDAY 2 MARCH 2022:</br>
    > 1.[E][ ] Finance Seminar (at: TUESDAY 1 MARCH 2022)</br>
    > 2.[D][ ] finish cp assignment (by: TUESDAY 1 MARCH 2022)</br>
  + due-before 2022-02-25
    > Seems like you are free before FRIDAY 25 FEBRUARY 2022
  
+ **find** : To find tasks containing that keyword.</br>

  _Format_: find {Keyword}</br>
  
  _Possible Error Warnings_:</br> 
  + If the keyword is missing, error is raised.
  
  _Usage Examples_:</br>
  + find assign
    > The following tasks contain assign:</br>
    > 1.[D][ ] finish cp assignment (by: TUESDAY 1 MARCH 2022)
  + find monkey
    > No task containing: monkey, was found.

+ **list** : To view the list of tasks lined up.</br>

  _Format_: list</br>
  
  _Possible Error Warnings_: 
  + If the list is empty _Conan_ tells the user that the list is empty.
  
  _Usage Example_:</br>
  + list
    > 1.[T][ ] buy lunch </br>
    > 2.[D][ ] return book (by: FRIDAY 1 MARCH 2019) </br>
    > 3.[E][X] project team meeting (at: FRIDAY 22 FEBRUARY 2019) </br>
    > 4.[T][ ] buy shoes </br>
  + list
    > No tasks are there! Seems like you are free!</br>
    > Do you wanna add some tasks?</br>

+ **mark** : Mark that task as complete.</br>

  _Format_: mark {Task Number}</br>
  
  _Possible Error Warnings_: </br>
  + If the argument is missing the task number, error is raised.
  + If the task index number is invalid, that is if it is less than or equal to 0 or greater than </br>
  the total number of tasks, an error warning is issued. 
  
  _Usage Examples_: </br>
  + mark 3
    > Great job, on completing this task! \(^_^)/<br>
    > [T][X] edit pics</br>
  + mark 40
    > Task num: 40 isn't present. Type 'list' command to view the task numbers.</br>
    > Please try again!</br>

+ **unmark** : Mark that task as not complete.</br>

  _Format_: unmark {Task Number}</br>
  
  _Possible Error Warnings_:</br>
  + If the argument is missing the task number, error is raised.
  + If the task index number is invalid, that is if it is less than or equal to 0 or greater than </br>
  the total number of tasks, an error warning is issued. 
  
  _Usage Examples_:</br>  
  + unmark 2
    > Sure, I have unmarked this task: </br>
    > [D][ ] return book (by: FRIDAY 1 MARCH 2019)
  + unmark 19
    > Task num: 19 isn't present. Type 'list' command to view the task numbers.</br>
    > Please try again!</br> 
  
#### Remembering Tasks

Conan has the ability to remember tasks from your previous runs. If you want to access them, then run the program under the same name you used last time. :smile_cat:
And then type **yes** if you want to continue from last time, otherwise press **no**.

#### Abilities

- [X] Remember Tasks from previous runs, of a single user.
- [X] identify task clashes.
- [X] does not allow duplicate tasks.
- [ ] Be able to save data of different users on the same Pc.

***
#### For programmers

Open IDE and run `Launcher.main()`;

```java
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
```

## FAQ

**Q.** Can multiple users use the same app? </br>
**A.** This task manager is designed to track the tasks of a sigle user, at a time. This means if another </br>
user starts the app then your tasks will be erased.

**Q.** What happens after I type in the bye command? </br>
**A.** Once the bye command is enter, Conan shut downs, however the window, will still be open, in case you </br>
would like to review any of the actions perfrmed.

**Q.** What version of java is needed to run Conan? </br>
**A.** You would need to have Java 11, to run Conan on your device.

**Q.** If I try to add a duplicate task what would happen? </br>
**A.** The duplicate task will not be added to the list of tasks, instead a warning will be issued.

**Q.** Can I add a task that clashes with a previously added task? </br>
**A.** Yes, an warning will be isseud, but the task will be added. In case you would like to remove the task use</br>
the delete command.

**Q.** What happens if I input an empty Command? </br>
**A.** A warning will be issued to indicate that empty command has been entered.

**Q.** Conan window is still open after the I entered the bye command, doe this mean I can add more tasks? </br>
**A.** No, once you have typed, bye, conan program stops, and any command you enter will be not recorded, or carried </br>
out by conan. Instead run conan again.


## Glossary

| Action      | Description                                | Format           |
| ----------- | ------------------------------------------ | ---------------- |
| Add         | Adds a task to track                       | {Task , more than 2 words} </br> todo {Task} </br> event {Task} /at YYYY-MM-DD HHMM </br> deadline {Task} /by YYYY-MM-DD HHMM |
| Bye         | Closes the program and stores the tasks    | bye
| Delete      | Deletes a task from the list of tasks      | delete {Index Number}
| Due on      | Gets tasks that are due on {Date}          | due-on {YYYY-MM-DD}
| Due before  | Gets tasks that are due before {Date}      | due-before {YYYY-MM-DD}
| Find        | Finds tasks that have {Keyword}            | find {keyword}
| List        | Displays the lists of tracks added by user | list
| Mark        | Marks task at {Index number} as done       | mark {Index Number}
| Unmark      | Marks task at {Index number} as not done   | unmark {Index Number}

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
