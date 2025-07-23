import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * Url testing requires a valid connection -> if no internet then they might fail
 * Also they might change daily internally so the tests -> 5,6,7 might fail because of that
 * Because tests -> 5,6,7 were made for a specific day
 *
 */

class ParserTest {
    // logger for each tes
    LogManager logManager = LogManager.getLogManager();
    String loggerName = Logger.GLOBAL_LOGGER_NAME;
    Logger logger = logManager.getLogger(loggerName);

    @Test
    @DisplayName("Test for example.json")
    public void test01() throws IOException {
        ArrayList<Article> articles = Parser.parserJSON("example.json", logger, true);
        // expected valid articles in this file are 10
        int expected = 10;
        int actual = articles.size();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test for bad.json")
    public void test02() throws IOException {
        ArrayList<Article> articles = Parser.parserJSON("bad.json", logger, true);
        // expected valid articles in this file are 7
        int expected = 7;
        int actual = articles.size();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test for valid.json with 1 entry which is valid")
    public void test03() throws IOException {
        ArrayList<Article> articles = Parser.parserJSON("valid.json", logger, true);
        // expected valid articles in this file are 1
        int expected = 1;
        int actual = articles.size();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test for invalid.json with 2 entry both invalid (have a null attribute or attribute not available)")
    public void test04() throws IOException {
        ArrayList<Article> articles = Parser.parserJSON("invalid.json", logger, true);
        // expected valid articles in this file are 0
        int expected = 0;
        int actual = articles.size();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test for a valid url with 38 entry and 4 valid")
    public void test05() throws IOException {
        ArrayList<Article> articles = Parser.parserJSON("https://newsapi.org/v2/top-headlines?country=us&apiKey=2c5f38d7d4a04fd1832a4977b2bf336f", logger, false);
        // expected valid articles in this file are 6
        int expected = 9;
        int actual = articles.size();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test for a valid url with 70 entry and 6 valid")
    public void test06() throws IOException {
        ArrayList<Article> articles = Parser.parserJSON("https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=2c5f38d7d4a04fd1832a4977b2bf336f", logger, false);
        // expected valid articles in this file are 2
        int expected = 4;
        int actual = articles.size();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test for a valid url with 10 entry and all valid")
    public void test07() throws IOException {
        ArrayList<Article> articles = Parser.parserJSON("https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=2c5f38d7d4a04fd1832a4977b2bf336f", logger, false);
        // expected valid articles in this file are 10
        int expected = 10;
        int actual = articles.size();

        assertEquals(expected, actual);
    }

    /**
     * Note -> Proj3:
     *      In this Project the visitor I implemented has a return type void and because my implementation is unique to
     *      consists of only 1 method in which we directly validate what type of format is required and do not have separate
     *      classes for that. So, i think there is no need for testing that here. Hence, I have not created any tests.
     */

    /**
     * Note -> Proj4:
     *      In this Project i extended the visitor pattern to also include a filtering process so, like proj3 no testing
     *      needed for proj4 too.
     */
}