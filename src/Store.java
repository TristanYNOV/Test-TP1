import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Article> articles;

    // Constructeur par défaut
    public Store() {
        this.articles = new ArrayList<>();
    }

    // Constructeur avec état initial
    public Store(List<Article> initialArticles) {
        // Copie défensive pour éviter les effets de bord
        this.articles = new ArrayList<>(initialArticles);
    }

    public void addArticle(Article article) {
        articles.add(article);
    }

    public void addArticles(List<Article> articlesToAdd) {
        this.articles.addAll(articlesToAdd);
    }


    public List<Article> getAllArticles() {
        return new ArrayList<>(articles); // copie pour éviter modifications externes
    }

    public void removeArticleById(int id) {
        articles.removeIf(article -> article.getId() == id);
    }

    public void removeArticlesByIds(List<Integer> idsToRemove) {
        articles.removeIf(article -> idsToRemove.contains(article.getId()));
    }


    public Article getArticleById(int id) {
        for (Article article : articles) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }
}
