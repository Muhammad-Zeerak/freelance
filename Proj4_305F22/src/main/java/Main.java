import java.io.IOException;

// ApiKey -> 2c5f38d7d4a04fd1832a4977b2bf336f

// Some urls to test
// https://newsapi.org/v2/top-headlines?country=us&apiKey=2c5f38d7d4a04fd1832a4977b2bf336f
// https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=2c5f38d7d4a04fd1832a4977b2bf336f

// Note we will be asking the format but that does not matter because we don't really need it

/**
 * This is a driver to print the parsed articles
 */
public class Main {
    /**
     * Main driver program
     *
     * @param args : arguments provided on console
     */
    public static void main(String[] args) throws IOException {
        Visitor visitor = new Parser();
        visitor.accept(new ParserVisitorImpl());
    }
}

/**
 * Note for Proj4:
 *          No change in main method, visitor pattern is extended to support filtering
 */
