import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StoreTest {

    @Test
    public void testAddArticle() {
        Store store = new Store();
        Article article = new Article(1, "Stylo", 1.5);
        store.addArticle(article);

        List<Article> articles = store.getAllArticles();
        assertEquals(1, articles.size());
        assertEquals(article, articles.get(0));
    }

    @Test
    public void testArticleAlreadyExist() {
        Article a1 = new Article(1, "Webcam", 35.0);
        Article a2 = new Article(1, "Microphone", 40.0); // Même ID

        Store store = new Store();
        store.addArticle(a1);

        // On s'attend à une exception lors du deuxième ajout
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            store.addArticle(a2);
        });

        assertEquals("Un article avec l'ID 1 existe déjà.", thrown.getMessage());
    }

    @Test
    public void testAddManyArticles() {
        Store store = new Store();
        Article a1 = new Article(1, "Clé USB", 8.99);
        Article a2 = new Article(2, "Chargeur", 15.49);

        store.addArticles(List.of(a1, a2));

        List<Article> result = store.getAllArticles();
        assertEquals(2, result.size());
        assertTrue(result.contains(a1));
        assertTrue(result.contains(a2));
    }

    @Test
    public void testAddManyWithOneWrong() {
        Article a1 = new Article(1, "Disque dur", 80.0);
        Article a2 = new Article(2, "SSD", 100.0);
        Article a3 = new Article(1, "Clé USB", 15.0); // Doublon d'ID avec a1

        Store store = new Store(List.of(a1));

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            store.addArticles(List.of(a2, a3));
        });

        assertEquals("Un article avec l'ID 1 existe déjà.", thrown.getMessage());
    }


    @Test
    public void testRemoveArticle() {
        Store store = new Store();
        Article a1 = new Article(1, "Cahier", 2.0);
        Article a2 = new Article(2, "Crayon", 0.5);
        store.addArticle(a1);
        store.addArticle(a2);

        store.removeArticleById(1);

        List<Article> articles = store.getAllArticles();
        assertEquals(1, articles.size());
        assertEquals(a2, articles.getFirst());
    }

    @Test
    public void testRemoveManyArticles() {
        Article a1 = new Article(1, "Tablette", 200.0);
        Article a2 = new Article(2, "Souris", 25.0);
        Article a3 = new Article(3, "Clavier", 45.0);

        Store store = new Store(List.of(a1, a2, a3));

        store.removeArticlesByIds(List.of(1, 3));

        List<Article> result = store.getAllArticles();
        assertEquals(1, result.size());
        assertTrue(result.contains(a2));
    }


    @Test
    public void testGetArticleById() {
        Article article = new Article(10, "Livre", 12.0);
        List<Article> initialState = List.of(article); // Java 9+ ou Arrays.asList(article) si Java 8
        Store store = new Store(initialState);

        Article result = store.getArticleById(10);

        assertNotNull(result);
        assertEquals(article, result);
    }
}
