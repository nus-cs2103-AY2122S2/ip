package chatbot.util;

import java.io.*;

public class Storage {
    public static <T extends Serializable> boolean Save(String filePath, T item) {
        try {
            File file = new File(filePath);
            File parentFile = file.getParentFile();
            if (parentFile != null) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file, false));
            objectOutputStream.writeObject(item);
            objectOutputStream.flush();
            objectOutputStream.close();
            return true;
        } catch (IOException ignored) {
            return false;
        }
    }

    public static <T extends Serializable> T Load(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            T item = (T) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return item;
        } catch (Exception e) {
            return null;
        }
    }
}