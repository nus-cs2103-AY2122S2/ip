# **DukePro** 

> "Stop making fake quotes and crediting it to me" - Sun Tzu([source](https://ifunny.co/picture/stop-making-fake-quotes-up-and-crediting-it-to-me-PoQLZnqk8))

Ever missed an important assignment because it slipped your mind? Worry no more!
DukePro is a simple chatbot aimed at managing your tasks to ensure that you'll never miss another important task ever again. 

## **Its has:** 

- A simple based input method suited for fast typers
- Easy to learn commands
- A quirky assistant bot brimming with personality 
<img src="https://64.media.tumblr.com/e2358326f171b07a12cc09f46adc8a4f/d900fba9b3ad6ca6-88/s400x600/2568b87b5ccf0c7f2440e54df642328b90d7e0f6.png" alt="" width="100"/>

## **All you need to do is**

1.  Download it from [here](https://github.com/kev-intq/ip/releases/tag/Level-10)
2.  Double-click it
3. Add your tasks
4. Let it manage your tasks for you :smiley:

And it is **FREE!**

## Features

- [x] Managing simple tasks
- [x] Managing deadlines and events (with dates)
- [x] Genshin-themed UI

![](https://static.wikia.nocookie.net/gensin-impact/images/7/74/Icon_Emoji_066_Hu_Tao_Reciting_poetry.png/revision/latest/scale-to-width-down/250?cb=20210906044059)

If you are familiar with CSS and JavaFX, you can further customize the UI to suit your needs. Here is a snippet of the GUI code:
```java
private DialogBox(String text, Image img, String user) {
        try {
            FXMLLoader fxmlLoader;
            if (user.matches("user")) {
                fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBoxUser.fxml"));
            } else {
                fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBoxBot.fxml"));
            }
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setFill(new ImagePattern(img));
    }
```
