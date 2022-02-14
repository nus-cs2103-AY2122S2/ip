# **Your Best Friend Pikachu!**

![Alt Text](https://c.tenor.com/9jn_TYgvSyQAAAAC/pikachu-pokemon.gif)
> #### _"Pika pika!"_

#### Pikachu helps you keep organised by recording your daily tasks and displaying them on command! :yellow_heart:

## Table of Contents
  * [Setup](#setup)
  * [Features](#features)
  * [Customisation](#customisation)

## Setup
1. Download the .jar file [here](https://github.com/jetrz/ip/releases/tag/A-Jar).
2. Open your local terminal and navigate to the folder which you saved the .jar file in, and run the following command:
```
java -jar ip.jar
```
3. That's it! Have fun with Pikachu!

## Features
- [x] To-dos, Deadline and Event task types implemented
- [x] Marking tasks as done/not done
- [x] Searching for tasks by name
- [ ] Interactive GUI (coming soon)

## Customisation
Want to customize your own Pikachu? Modify the following functions in the UI.java file to control what Pikachu says! 
```java
public static void printGreeting() {
    //Your customized greeting here!
}

public static void printCommands() {
    //Your customized command list here!
}

public static void printGoodbye() {
    //Your customized goodbye here!
}
```
