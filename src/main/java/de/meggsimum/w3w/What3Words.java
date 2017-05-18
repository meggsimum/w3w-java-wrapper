/**
 *
 */
package de.meggsimum.w3w;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

/**
 * A Java wrapper for the What3Words Web-API.
 *
 * @see <a href="https://docs.what3words.com/api/v2/">https://docs.what3words.com/api/v2/</a>
 *
 * @author Christian Mayer, meggsimum
 */
public class What3Words {

    /**
     * the w3w API-Key
     */
    private String apiKey;

    /**
     * the current language
     */
    private String language = "en";

    /**
     * the base URL to the w3w Web-API
     */
    private String baseUrl = "https://api.what3words.com/v2/";

    /**
     * Constructor creating a w3w-object bound to the given API-Key.
     *
     * @param apiKey
     */
    public What3Words(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Constructor creating your w3w-object bound to the given API-Key with a
     * given language.
     *
     * @param apiKey
     * @param language
     *            default Language
     */
    public What3Words(String apiKey, String language) {
        this.apiKey = apiKey;
        this.language = language;
    }

    /**
     * Converts 3 words object (in the given language) into a position object.
     *
     * @param threeWords
     *            "w3w-address" object in the given language
     * @param language
     *            string defining the language of the words (e.g. "de")
     * @return coordinates object holding the coordinates reprsenting the given
     *         words
     * @throws IOException
     * @throws What3WordsException
     */
    public Coordinates wordsToPosition(ThreeWords threeWords, String language) throws IOException, What3WordsException {
        double[] doubleCoordinates = wordsToPosition(new String[]{threeWords.getFirst(), threeWords.getSecond(), threeWords.getThird()});
        return new Coordinates(doubleCoordinates[0], doubleCoordinates[1]);
    }

    /**
     * Converts 3 words object into a position object.
     *
     * @param threeWords
     *            "w3w-address" object in the given language
     * @return coordinates object holding the coordinates reprsenting the given
     *         words
     * @throws IOException
     * @throws What3WordsException
     */
    public Coordinates wordsToPosition(ThreeWords threeWords) throws IOException, What3WordsException {
        return wordsToPosition(threeWords, this.language);
    }

    /**
     * Converts 3 words into a position specifying default language.
     *
     * @param words
     *            the 3 words representing the "w3w-address"
     * @return array holding the position in the form [lat, lon]
     * @throws IOException
     * @throws What3WordsException
     */
    public double[] wordsToPosition(String[] words) throws IOException,
            What3WordsException {
        return wordsToPosition(words, this.language);
    }

    /**
     * Converts 3 words into a position.
     *
     * @param words
     *            the 3 words representing the "w3w-address"
     * @param language
     * @return array holding the position in the form [lat, lon]
     * @throws IOException
     * @throws What3WordsException
     */
    public double[] wordsToPosition(String[] words, String language) throws IOException,
                What3WordsException {
        String url = String.format("%sforward?key=%s&addr=%s.%s.%s&lang=%s" ,this.baseUrl, this.apiKey, words[0], words[1], words[2], language);

        String response = this.doHttpGet(url);

        try {
            // parse the coordinates out of the JSON
            JSONObject json = new JSONObject(response);
            if (json.has("geometry")) {
                JSONObject jsonCoords = (JSONObject) json.get("geometry");
                double[] coords = new double[2];
                coords[0] = (Double) jsonCoords.get("lat");
                coords[1] = (Double) jsonCoords.get("lng");

                return coords;

            } else if (json.has("code")) {

                throw new What3WordsException("Error returned from w3w API: "
                        + json.getString("message"));

            } else if (json.has("status")) {

                if(json.get("status") instanceof JSONObject) {
                    JSONObject status = json.getJSONObject("status");
                    throw new What3WordsException("Error returned from w3w API: "
                            + status.getString("message"));
                } else {
                    throw new What3WordsException(
                            "Undefined error while fetching words by position");
                }

            } else {
                throw new What3WordsException(
                        "Undefined error while fetching words by position");
            }

        } catch (Exception e) {
            throw new What3WordsException(e.getMessage(), e);
        }

    }

    /**
     * Converts a position object into a "w3w-address" object (in the given
     * language)
     *
     * @param coordinates object holding the coordinates to be transformed
     * @param language string defining the language of the words (e.g. "de")
     * @return "w3w-address" object in the given language
     * @throws IOException
     * @throws What3WordsException
     */
    public ThreeWords positionToWords(Coordinates coordinates, String language) throws IOException, What3WordsException {
        String[] words = positionToWords(new double[]{coordinates.getLatitude(), coordinates.getLongitude()}, language);
        return new ThreeWords(words[0], words[1], words[2]);
    }

    /**
     * Converts a position object into a "w3w-address" object with default
     * language.
     *
     * @param coordinates object holding the coordinates to be transformed
     * @return "w3w-address" object
     * @throws IOException
     * @throws What3WordsException
     */
    public ThreeWords positionToWords(Coordinates coordinates) throws IOException, What3WordsException {
        return positionToWords(coordinates, this.language);
    }

    /**
     * Converts a position into a "w3w-address" with default language.
     *
     * @param coords
     *            Coordinates to be transformed in the form [lat, lon]
     * @return Array holding the "w3w-address" in the form [word1, word2, word3]
     * @throws IOException
     * @throws What3WordsException
     */
    public String[] positionToWords(double[] coords)
            throws What3WordsException, IOException {
        return positionToWords(coords, this.language);
    }

    /**
     * Converts a position into a "w3w-address".
     *
     * @param coords
     *            Coordinates to be transformed in the form [lat, lon]
     * @param language
     *            Language for the "w3w-address"
     * @return Array holding the "w3w-address" in the form [word1, word2, word3]
     * @throws IOException
     * @throws What3WordsException
     */
    public String[] positionToWords(double[] coords, String language)
        throws What3WordsException, IOException {
        String url = String.format("%sreverse?key=%s&coords=%s,%s&lang=%s" ,this.baseUrl, this.apiKey, coords[0], coords[1], language);
        String response = this.doHttpGet(url);

        try {
            // parse the words out of the JSON
            JSONObject json = new JSONObject(response);

            if (json.has("words") == true) {
                String jsonWords = (String) json.get("words");
                String[] words = jsonWords.split("\\.");

                return words;

            } else if (json.has("code")) {
                throw new What3WordsException("Error returned from w3w API: "
                        + json.getString("message"));

            } else if (json.has("status")) {

                if(json.get("status") instanceof JSONObject) {
                    JSONObject status = json.getJSONObject("status");
                    throw new What3WordsException("Error returned from w3w API: "
                            + status.getString("message"));
                } else {
                    throw new What3WordsException(
                            "Undefined error while fetching words by position");
                }

            } else {
                throw new What3WordsException(
                        "Undefined error while fetching words by position");
            }

        } catch (Exception e) {
            throw new What3WordsException(e);
        }

    }

    /**
     * Performs a HTTP GET request with the given URL.
     *
     * @param url
     * @return
     * @throws IOException
     */
    private String doHttpGet(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // ensure we use HTTP GET
        con.setRequestMethod("GET");
        StringBuffer response = new StringBuffer();
        BufferedReader in;
        try {
            // HTTP status codes 2xx
            in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));

        } catch (IOException e) {
            // HTTP status codes other than 2xx
            in = new BufferedReader(new InputStreamReader(
                    con.getErrorStream()));
        }
        // Read response body
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return this.language;
    }

    /**
     * @param language
     *            the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

}
