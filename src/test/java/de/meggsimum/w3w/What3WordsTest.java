package de.meggsimum.w3w;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for {@linkplain What3Words}
 * 
 * @author Christian Mayer, meggsimum
 */
public class What3WordsTest extends TestCase {
	
	/**
	 * Ensure to set your API-Key here before running the test suite
	 */
	private static final String API_KEY = "YOUR-API-KEY-HERE";

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public What3WordsTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(What3WordsTest.class);
	}

	/**
	 * Tests the words -> position API wrapper
	 */
	public void testWordsToPosition() {
		What3Words w3w = new What3Words(API_KEY);
		String[] words = {"goldfish", "fuzzy", "aggregates"};
		double[] coords;
		try {
			coords = w3w.wordsToPosition(words);
			assertEquals(2, coords.length);
			assertEquals(49.422636, coords[0]);
			assertEquals(8.320833, coords[1]);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	/**
	 * Tests the position -> words API wrapper
	 */
	public void testPositionToWords() {
		What3Words w3w = new What3Words(API_KEY);
		Double[] coords = {49.422636, 8.320833};
		String[] words;
		try {
			words = w3w.positionToWords(coords);
			assertEquals(3, words.length);
			assertEquals("goldfish", words[0]);
			assertEquals("fuzzy", words[1]);
			assertEquals("aggregates", words[2]);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	/**
	 * Tests the position -> words API wrapper after changing the language
	 */
	public void testChangeLang() {
		What3Words w3w = new What3Words(API_KEY);
		w3w.setLanguage("de");
		Double[] coords = {49.422636, 8.320833};
		String[] words;
		try {
			words = w3w.positionToWords(coords);
			assertEquals(3, words.length);
			assertEquals("kleid", words[0]);
			assertEquals("ober", words[1]);
			assertEquals("endlos", words[2]);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	/**
	 * Test for exception in case of an invalid API-key
	 */
	public void testWhat3WordsException() {
		What3Words w3w = new What3Words("my-senseless-key");
		Double[] coords = { 49.422636, 8.320833 };
		boolean thrown = false;
		try {
			w3w.positionToWords(coords);
		} catch (Exception e) {
			thrown = true;
			assertEquals("Error returned from w3w API: Missing or invalid key",
					e.getCause().getMessage());
		}
		assertTrue(thrown);
	}

}
