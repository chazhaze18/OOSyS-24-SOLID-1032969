// GsonStorage.java
package ljmu.vets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

public class GsonStorage implements DataStorage {
    private Gson gson = new GsonBuilder().create();

    @Override
    public void serialize(Object data, String filePath) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object deserialize(String filePath) {
        try (Reader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, Object.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

