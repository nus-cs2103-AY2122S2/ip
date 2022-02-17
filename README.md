## **CortanaTodo**
> “Productivity is being able to do things that you were never able to do before.” – Franz Kafka ([source](https://dansilvestre.com/productivity-quotes/))

### CortanaTodo frees your mind by keeping track of the things that you need to do, it's
- text-based
- easy to learn
- ~~FAST~~ SUPER FAST to use

### All you need to do is,
 1. Download it from [here](https://github.com/9teMare/ip/releases).
 2. Double click it.
 3. Add your tasks.
 4. Free your mind, let Cortana manage your tasks for you 😆
 
 And it's FREE!

### Features:
- [x] Managing todos
- [x] Managing deadlines
- [x] Managing events
- [x] Find tasks on the same date/time
- [x] Find tasks with keyword
- [X] View schedules of a day
- [X] Mark/unmark/delete tasks 

If you Java programmer, you can use it to practice Java too. Here's the `getResponse` method:
```java
 public String getResponse(String input) {
     try {
         setIsReturnError(false);
         Command c = Parser.parse(input);
         return c.execute(tasks, ui, storage);
     } catch (CortanaException e) {
         setIsReturnError(true);
         return ui.showErrorMessage(e.getMessage());
     }
 }
```
