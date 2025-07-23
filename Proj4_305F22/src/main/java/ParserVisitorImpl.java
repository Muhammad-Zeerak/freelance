import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.*;

public class ParserVisitorImpl implements ParserVisitor {
    /**
     * Runs the parser on the source (.json file) and returns the Arraylist of articles extracted
     *
     * @param source : the source of json like someFile.json
     * @param isSource : whether it is source or not
     *
     * @return arrayList of articles extracted
     */
    private static ArrayList<Article> getArticles(String source, boolean isSource) throws IOException {
        // All the necessary setting for Logger
        LogManager logManager = LogManager.getLogManager();
        String loggerName = Logger.GLOBAL_LOGGER_NAME;
        Logger logger = logManager.getLogger(loggerName);
        try {
            FileHandler fileHandler = null;
            if(isSource) {
                fileHandler = new FileHandler("LogFile_" + source + ".log");
            } else {
                fileHandler = new FileHandler("LogFile_URL.log");
            }
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.WARNING);
            logger.addHandler(fileHandler);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return Parser.parserJSON(source, logger, isSource);
    }

    /**
     * Prints the extracted articles with some details
     *
     * @param source : the source of json like someFile.json
     * @param isSource : whether it is source or not
     */
    public static void runParser(String source, boolean isSource) throws IOException {
        // call the function with source and logger
        ArrayList<Article> articles = getArticles(source, isSource);
        System.out.println("Total Valid articles: " + articles.size());
        System.out.println();

        // print each article
        for(Article a : articles) {
            System.out.println(a);
        }
    }

    /**
     * Prints the extracted articles with some details and filter
     *
     * @param source : the source of json like someFile.json
     * @param isSource : whether it is source or not
     * @param filter : filter to be applied
     */
    public static void runParser(String source, boolean isSource, String filter) throws IOException {
        // call the function with source and logger
        ArrayList<Article> articles = getArticles(source, isSource);
        System.out.println("Total Valid articles: " + articles.size());
        System.out.println();

        // applying filter
        ArrayList<Article> filteredArticles = new ArrayList<>();
        // add all the articles whose name is same as filter
        for(Article a : articles) {
            if(a.getName().equalsIgnoreCase(filter)) {
                filteredArticles.add(a);
            }
        }

        System.out.println("Total Valid filtered articles: " + filteredArticles.size());
        System.out.println();

        // print each filtered article
        for(Article a : filteredArticles) {
            System.out.println(a);
        }
    }

    @Override
    public void visit(Parser parser) throws IOException {
        Scanner input = new Scanner(System.in);

        // ask the source
        System.out.print("Press 1 for a file and 2 for a url source: ");
        int choice = input.nextInt();
        input.nextLine();

        // ask the format -> this really does not matter because we are validating this in our parsing method
        System.out.print("Press 1 for newsapi and 2 for simple format: ");
        int choice2 = input.nextInt();
        input.nextLine();

        if(choice == 1) {
            System.out.print("Enter filename: ");
            String filename = input.nextLine();
            // extending this visitor implementation for filtering
            if(choice2 == 1) {
                System.out.print("Which source articles? ");
                String source = input.nextLine();
                runParser(filename, true, source);
            } else {
                runParser(filename, true);
            }
        } else {
            System.out.print("Enter url: ");
            String url = input.nextLine();
            // extending this visitor implementation for filtering
            if(choice2 == 1) {
                System.out.print("Which source articles? ");
                String source = input.nextLine();
                runParser(url, false, source);
            } else {
                runParser(url, false);
            }
        }
    }
}
