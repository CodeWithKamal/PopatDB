import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PopatDB {
    private PopatDataStorage dataStorage;

    public PopatDB(String basePath) {
        dataStorage = new PopatDataStorage(basePath);
    }

    public void create(String collectionName, String documentId, HashMap<String, Object> data) throws IOException {
        dataStorage.store(collectionName, documentId, toJson(data));
    }

    public String read(String collectionName, String documentId) throws IOException {
        return dataStorage.retrieve(collectionName, documentId);
    }

    public void update(String collectionName, String documentId, HashMap<String, Object> newData) throws IOException {
        dataStorage.store(collectionName, documentId, toJson(newData));
    }

    public void delete(String collectionName, String documentId) throws IOException {
        dataStorage.delete(collectionName, documentId);
    }

    public List<String> list(String collectionName) throws IOException {
        return dataStorage.list(collectionName);
    }

    private String toJson(HashMap<String, Object> map) {
        return "{"+map.entrySet().stream()
        .map(e -> "\""+ e.getKey() + "\":\"" + String.valueOf(e.getValue()) + "\"")
        .collect(Collectors.joining(", "))+"}";
    }
}
