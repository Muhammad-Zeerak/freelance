import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.logging.*;

/**
 * This class acts as Parser
 */
public class Parser implements Visitor {

    /**
     * Parser function takes in the source as String -> which is the json file like "file.json or file.txt" or an URL
     * isSource tells whether a source or url
     *
     * @param Source : source in json (source.json)
     * @param logger   : logger which will report errors as warnings
     * @param isSource : true if it is a file false if an url
     * @return -> A list of Articles parsed
     */
    public static ArrayList<Article> parserJSON(String Source, Logger logger, boolean isSource) throws IOException {
        // A JSONParser object
        JSONParser parser = new JSONParser();
        ArrayList<Article> articlesList = null;
        JSONObject jsonObject = null;

        try {
            // parse with IOUtils to get json string else with file reader to get string
            if(!isSource) {
                String json = IOUtils.toString(URI.create(Source), Charset.forName("UTF-8"));
                jsonObject =  (JSONObject) parser.parse(json);
            } else {
                Object obj = parser.parse(new FileReader(Source));
                jsonObject = (JSONObject) obj;
            }

            // get status
            String status = (String) jsonObject.get("status");

            /**
             * Adding support for simple parser -> simple.txt
             * We assume that simple.txt means that it can only contain 4 things
             *      1. description
             *      2. publishedAt
             *      3. title
             *      4. url
             */

            // if the status above is null that means it is a simple.txt type source so
            if(status == null) {
                // now we also assume that the simple.txt type of data source will have only 1 article
                String description  = (String) jsonObject.get("description");
                String publishedAt  = (String) jsonObject.get("publishedAt");
                String title  = (String) jsonObject.get("title");
                String url  = (String) jsonObject.get("url");
                Article simpleArticle = new Article(title, description, url, publishedAt);

                System.out.println("Parser Type: Simple Parser - data source");
                System.out.println("Total Results: 1");
                // return this single article as list
                articlesList = new ArrayList<>();
                articlesList.add(simpleArticle);
                return articlesList;
            }

            // get totalResults
            Long totalResults = (Long) jsonObject.get("totalResults");
            // get articles array
            JSONArray articles = (JSONArray) jsonObject.get("articles");
            // Make the ArrayList of articles
            articlesList = new ArrayList<>();
            // Parse each article into the arrayList
            for (Object s : articles) {
                JSONObject object = (JSONObject) s;
                // get source
                JSONObject source = (JSONObject) object.get("source");
                // get name from source
                String name = (String) source.get("name");
                // get id from source
                String id = (String) source.get("id");
                // get author
                String author = (String) object.get("author");
                // get title
                String title = (String) object.get("title");
                // get description
                String description = (String) object.get("description");
                // get url
                String url = (String) object.get("url");
                // get urlToImage
                String urlToImage = (String) object.get("urlToImage");
                // get publishedAt
                String publishedAt = (String) object.get("publishedAt");
                // get content
                String content = (String) object.get("content");
                /**
                 * We assume that if any of the attribute extracted is null, This can happen in two cases:
                 *      1. If the value extracted is null itself.
                 *      2. If the attribute is not present in JSON then we will get null when we try to extract it
                 *
                 * So, in this case we assume this is not a valid article, so we report it as a Warning using the logger
                 */
                if(name == null) {
                    logger.log(Level.WARNING, "name is null");
                }
                if(id == null) {
                    logger.log(Level.WARNING, "id is null");
                }
                if(author == null) {
                    logger.log(Level.WARNING, "author is null");
                }
                if(title == null) {
                    logger.log(Level.WARNING, "title is null");
                }
                if(description == null) {
                    logger.log(Level.WARNING, "description is null");
                }
                if(url == null) {
                    logger.log(Level.WARNING, "url is null");
                }
                if(urlToImage == null) {
                    logger.log(Level.WARNING, "urlToImage is null");
                }
                if(publishedAt == null) {
                    logger.log(Level.WARNING, "publishedAt is null");
                }
                if(content == null) {
                    logger.log(Level.WARNING, "content is null");
                }
                // if none are null the make the article object because this is a valid article
                if(name != null && id != null && author != null && title != null && description != null && url != null && urlToImage != null && publishedAt != null && content != null) {
                    Article article = new Article(name, id, author, title, description, url, urlToImage, publishedAt, content);
                    // add to arrayList
                    articlesList.add(article);
                }
            }
            // print the details which will not be returned by the parser
            System.out.println("Parser Type: Advanced Parser - data source");
            System.out.println("Status: " + status);
            System.out.println("Total Results: " + totalResults);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // return the list of articles parsed
        return articlesList;
    }

    @Override
    public void accept(ParserVisitor parserVisitor) throws IOException {
        parserVisitor.visit(this);
    }
}