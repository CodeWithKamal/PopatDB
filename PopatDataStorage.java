import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PopatDataStorage {
    private String basePath;

    public PopatDataStorage(String basePath) {
        this.basePath = basePath;
    }

    public void store(String collectionName, String documentId, String data) throws IOException {
        String filePath = getFilePath(collectionName, documentId);
        File collectionFile = new File(getCollectionPath(collectionName));
        if (!collectionFile.exists()) {
            collectionFile.mkdir();
        }
        Files.write(Paths.get(filePath), data.getBytes());
    }

    public String retrieve(String collectionName, String documentId) throws IOException {
        String filePath = getFilePath(collectionName, documentId);
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        return new String(bytes);
    }

    public void delete(String collectionName, String documentId) throws IOException {
        String filePath = getFilePath(collectionName, documentId);
        Files.deleteIfExists(Paths.get(filePath));
    }

    public List<String> list(String collectionName) throws IOException {
        File folder = new File(getCollectionPath(collectionName));
        String[] fileNames = folder.list();
        if (fileNames == null) {
            return List.of();
        }
        return List.of(fileNames);
    }

    private String getFilePath(String collectionName, String documentId) {
        return getCollectionPath(collectionName) + File.separator + documentId;
    }

    private String getCollectionPath(String collectionName) {
        return basePath + File.separator + collectionName;
    }
}
