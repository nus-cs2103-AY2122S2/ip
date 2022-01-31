# Tsundere

> Soon is not as good as now. – Seth Godin ([source](https://dansilvestre.com/productivity-quotes/))

Tsundere frees your mind of having to remember things you need to do while scolding you for forgeting. It's
- text-based
- easy to learn
- ~~ FAST~~ SUPER FAST to use

All you need to do is,

1. download it from [here](https://github.com/lowkaiwei98/ip/releases/tag/A-jar).
2. double-click it.
3. add your tasks.
4. let it manage your tasks for you :smiley:

And it is **FREE**!

Features:

[x] Managing tasks
[] Hot waifu anime girl (coming soon)
[] Reminders (coming soon)

If you Java programmer, you can use it to practice Java too. Here's the constructor for `Tsundere`:

```java
public Tsundere(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (TsundereException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
```

