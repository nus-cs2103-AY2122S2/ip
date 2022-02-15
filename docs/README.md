=============================================================================================



![JUKE](https://user-images.githubusercontent.com/73661051/152280664-189c867f-374f-4edb-99fa-cec2ed008df0.png)
> # Juke is ~cool~ awesome pawsome :dog::paw_prints:
Juke, an interactive chatbot, allows you freedom to plan and organize your tasks, such as:
-  _todo_
- _deadline_
- _event_

Downloading it is as easy as 1, 2, 3:
1. click this [link](https://github.com/zxgoh/ip/releases/tag/A-Jar)
2. download ip.jar
3. you're done, voilà :disguised_face:

**<div align="center">Here's a sneak peek into the inner-workings of Juke, using [filewriter](https://docs.oracle.com/javase/7/docs/api/java/io/FileWriter.html)</div>**
```
private static void writeToFile(String filePath, String textToAdd) throws IOException {
    FileWriter fw = new FileWriter(filePath, true);
    fw.write(textToAdd);
    fw.close();
    }
```
=============================================================================================
