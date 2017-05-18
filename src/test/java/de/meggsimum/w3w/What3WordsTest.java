package de.meggsimum.w3w;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeNotNull;

/**
 * Unit test for {@linkplain What3Words}
 *
 * @author Christian Mayer, meggsimum
 */
public class What3WordsTest {

    /**
     * The api key is read from command line arguments or can also be entered here manually.
     */
    private String apiKey = null;
    /**
     * Name of the system property hodling the API key for W3W.
     */
    public static final String API_KEY_PROPERTY = "W3W_API_KEY";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * Checks if the API key is either hard coded or passed via system properties.
     * Only if this precondition is true, the tests are executed.
     */
    @Before
    public void beforeMethod() {
        // Try to read the API key from system properties only in case it was not hard coded.
        if (apiKey == null) {
            apiKey = System.getProperty(API_KEY_PROPERTY);
        }
        // Fall back to environment variable in case API key was not provided as property
        if(apiKey == null) {
            apiKey = System.getenv(API_KEY_PROPERTY);
        }
        assumeNotNull(apiKey);
    }

    /**
     * Tests the words -> position API wrapper
     */
    @Test
    public void testWordsToPosition() throws Exception {
        What3Words w3w = new What3Words(apiKey);
        String[] words = {"goldfish", "fuzzy", "aggregates"};
        double[] coords;
        coords = w3w.wordsToPosition(words);
        assertEquals(2, coords.length);
        assertEquals(49.422636, coords[0], 0.1);
        assertEquals(8.320833, coords[1], 0.1);
    }

    /**
     * Tests the position -> words API wrapper
     */
    @Test
    public void testPositionToWords() throws Exception {
        What3Words w3w = new What3Words(apiKey);
        double[] coords = {49.422636, 8.320833};
        String[] words;
        words = w3w.positionToWords(coords);
        assertEquals(3, words.length);
        assertEquals("goldfish", words[0]);
        assertEquals("fuzzy", words[1]);
        assertEquals("aggregates", words[2]);
    }

    /**
     * Tests the position -> words API wrapper after changing the language
     */
    @Test
    public void testChangeLang() throws Exception {
        What3Words w3w = new What3Words(apiKey);
        w3w.setLanguage("de");
        double[] coords = {49.422636, 8.320833};
        String[] words;
        try {
            words = w3w.positionToWords(coords);
            assertEquals(3, words.length);
            assertEquals("kleid", words[0]);
            assertEquals("ober", words[1]);
            assertEquals("endlos", words[2]);
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Test for exception in case of an invalid API-key
     */
    @Test
    public void testInvalidWhat3WordsAPIKeyException() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Authentication failed; invalid API key");
        What3Words w3w = new What3Words(UUID.randomUUID().toString() + apiKey);
        double[] coords = {49.422636, 8.320833};
        w3w.positionToWords(coords);
    }

    // Object API tests

    @Test
    public void testWordsToPositionObj() throws Exception {
        What3Words w3w = new What3Words(apiKey);
        ThreeWords words = new ThreeWords("goldfish", "fuzzy", "aggregates");
        Coordinates coords = w3w.wordsToPosition(words);
        assertEquals(49.422636, coords.getLatitude(), 0.000001);
        assertEquals(8.320833, coords.getLongitude(), 0.000001);
    }

    @Test
    public void testWordsToPositionWithLangObj() throws Exception {
        What3Words w3w = new What3Words(apiKey);
        ThreeWords words = new ThreeWords("kleid", "ober", "endlos");
        Coordinates coords = w3w.wordsToPosition(words, "de");
        assertEquals(49.422636, coords.getLatitude(), 0.000001);
        assertEquals(8.320833, coords.getLongitude(), 0.000001);
    }

    @Test
    public void testPositionToWordsObj() throws Exception {
        What3Words w3w = new What3Words(apiKey);
        Coordinates coordinates = new Coordinates(49.422636, 8.320833);
        ThreeWords threeWords = w3w.positionToWords(coordinates);
        assertEquals(new ThreeWords("goldfish", "fuzzy", "aggregates"), threeWords);
    }

    @Test
    public void testPositionToWordsWithLangObj() throws Exception {
        What3Words w3w = new What3Words(apiKey);
        Coordinates coordinates = new Coordinates(49.422636, 8.320833);
        ThreeWords threeWords = w3w.positionToWords(coordinates, "de");
        assertEquals(new ThreeWords("kleid", "ober", "endlos"), threeWords);
    }

    @Test
    public void testGerman3wordAddress()throws Exception  {
        What3Words w3w = new What3Words(apiKey, "de");
        String[] words = {"zulassen", "fährst", "wächst"};
        double[] coords = w3w.wordsToPosition(words);
        assertEquals(2, coords.length);
        assertEquals(50.049496, coords[0], 0.000001);
        assertEquals(-110.681137, coords[1], 0.000001);
    }

    @Test
    public void testFrench3wordAddress()throws Exception  {
        What3Words w3w = new What3Words(apiKey, "fr");
        String[] words = {"garçon", "étaler", "frôler"};
        double[] coords = w3w.wordsToPosition(words);
        assertEquals(2, coords.length);
        assertEquals(48.246860, coords[0], 0.000001);
        assertEquals(16.099389, coords[1], 0.000001);
    }

    @Test
    public void testWordsToPositionInvalid3WordAddress() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Invalid or non-existent 3 word address");
        What3Words w3w = new What3Words(apiKey);
        String[] words = {"no", "address", "here"};
        double[] coords = w3w.wordsToPosition(words);

    }

    @Test
    public void testPositionToWordsInvalidLanguage() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("The 'lang' parameter is invalid or missing a language code");
        What3Words w3w = new What3Words(apiKey);
        double[] coords = {49.422636, 8.320833};
        String[] words = w3w.positionToWords(coords, "XX");

    }
}