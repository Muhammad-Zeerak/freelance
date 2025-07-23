/**
 * This class represents a single Article with all the data
 */
public class Article {
    // we move the id and name of each article outside the array in a single variable each
    private String id;
    private String name;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;


    /**
     * Parameterized Constructor
     *
     * @param id : id extracted from source
     * @param name : name extracted from source
     * @param author : author of article
     * @param title : title of article
     * @param description : description of article
     * @param url : url of article
     * @param urlToImage : an image url of article
     * @param publishedAt : date-time the article is published at
     * @param content : the content inside the article
     */
    public Article(String id, String name, String author, String title, String description, String url, String urlToImage, String publishedAt, String content) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    /**
     * Support for Assignment 2
     */
    public Article(String title, String description, String url, String publishedAt) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.publishedAt = publishedAt;
        // all others are null
        this.id = null;
        this.name = null;
        this.author = null;
        this.urlToImage = null;
        this.content = null;
    }

    /**
     *
     * @return a String representation of article data planned
     */
    @Override
    public String toString() {
        String res = "Article:\n";
        res += "Title: " + title;
        res += "\nAt: " + publishedAt;
        res += "\nURL: " + url;
        res += "\n" + description;
        res += "\n";
        return res;
    }

    // getter for name
    public String getName() {
        return name;
    }
}
