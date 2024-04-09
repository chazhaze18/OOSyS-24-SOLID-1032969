// DataStorage.java
package ljmu.vets;

public interface DataStorage {
    void serialize(Object data, String filePath);
    Object deserialize(String filePath);
}

