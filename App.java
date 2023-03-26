import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        String basePath = ".\\"; // base path to store all collections.
        PopatDB popatDB = new PopatDB(basePath);

        String collectionName = "users_collection";
        String documentId = "document1";
        
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", documentId);
        map.put("name", "Kamal");
        map.put("age", 20);
        map.put("gender", true);

        // Create a document
        popatDB.create(collectionName, documentId, map);

        // Read the document
        String retrievedData = popatDB.read(collectionName, documentId);
        System.out.println(retrievedData);

        // Update the document
        popatDB.update(collectionName, documentId, map);

        // Delete the document
        popatDB.delete(collectionName, documentId);

        // List all documents in the collection
        List<String> documents = popatDB.list(collectionName);
        System.out.println(documents); // []
    }
}
