package functional_programming;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Repository {
    private String uri;
    private Properties properties;

    public Repository() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream("src/main/java/functional_programming/Settings.properties"));
        String user = properties.getProperty("username");
        String password = properties.getProperty("password");
        String conString = properties.getProperty("conString");
        String conString2 = properties.getProperty("conString2");

        uri = String.format("%s%s%s%s",conString,user,password,conString2);
    }

    public void testPrint() {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("movies");

            Document doc = collection.find(eq("title", "Back to the Future")).first();
            if (doc != null) {
                System.out.println(doc.toJson());
            } else {
                System.out.println("No matching documents found.");
            }
        }
    }

    public List<Movie> getMoviesByField(String filterField, String strValue, int intValue) {

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> moviesCollection = database.getCollection("movies");

            List<Movie> movieList = new ArrayList<>();

            if (strValue == null) {
                for (Document doc : moviesCollection.find(new Document(filterField, intValue)))
                    movieList.add(Movie.fromDocument(doc));
            } else {
                for (Document doc : moviesCollection.find(new Document(filterField, strValue))) {
                    Movie m = Movie.fromDocument(doc);
                    if (m != null)
                        movieList.add(Movie.fromDocument(doc));
                }
            }
            return movieList;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}