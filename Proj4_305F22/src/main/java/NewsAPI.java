import java.util.ArrayList;

/**
 * Class parses all the results including the status and totalResults
 * We also have articles ArrayList
 */
public class NewsAPI {
    private String status;
    private Long totalResults;
    private ArrayList<Article> articles;

    /**
     * Parameterized Constructor
     *
     * @param status : status returned from NewsAPI
     * @param totalResults : totalResults returned from NewsAPI
     * @param articles : an arrayList of articles returned from NewsAPI
     */
    public NewsAPI(String status, Long totalResults, ArrayList<Article> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    /**
     * Print details of the data extracted from JSON
     */
    public void printDetails() {
        System.out.println("Status: " + status);
        System.out.println("Total Results: " + totalResults);
        System.out.println("Articles extracted: " + articles.size());
        System.out.println();
        for(Article a : articles) {
            System.out.println(a);
        }
    }
}
