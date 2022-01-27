package chatbot.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Storage {
    public static <T extends Serializable> boolean Save(String filePath, T item) {
        try {
            File file = new File(filePath);
            File parentFile = file.getParentFile();
            if (parentFile != null) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();

            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file, false)));
            objectOutputStream.writeObject(item);
            objectOutputStream.flush();
            objectOutputStream.close();
            return true;
        } catch (IOException ignored) {
            return false;
        }
    }

    public static <T extends Serializable> T Load(String fileName) {
        try {
            ObjectInputStream objectInputStream =
                    new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
            T item = (T) objectInputStream.readObject();
            objectInputStream.close();
            return item;
        } catch (Exception e) {
            return null;
        }
    }
}