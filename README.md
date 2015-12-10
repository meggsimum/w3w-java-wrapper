# w3w-java-wrapper
Java wrapper for the [what3words](http://what3words.com/) [web-API](http://developer.what3words.com/api).

Use the what3words API in your Java applications.

## Methods

  ```java
  double[] wordsToPosition(String[] words)
  ```

  Converts a 3 words 'address' into a position (assumes words in the default language 'en')
  
  ```java
  double[] wordsToPosition(String[] words, String language)
  ```

  Converts a 3 words 'address' (in the given language) into a position

  ```java
  String[] positionToWords(double[] coords)
  ```

  Converts a position into a 3 words 'address' (language 'en')
  
  ```java
  String[] positionToWords(double[] coords, String language)
  ```

  Converts a position into a 3 words 'address' (in the given language)

## Code Samples

```java
String apiKey = "my-super-w3w-apikey";
What3Words w3w = new What3Words(apiKey);
String[] words = {"goldfish", "fuzzy", "aggregates"};
try {
    double[] coords = w3w.wordsToPosition(words);
    System.out.println(coords[0] +  " / " + coords[1]);
} catch (Exception e) {
    // do your error handling
}
```

```java
String apiKey = "my-super-w3w-apikey";
What3Words w3w = new What3Words(apiKey);
Double[] coords = {49.422636, 8.320833};
try {
    // optionally set a different language
    String[] words = w3w.positionToWords(coords, "de");
    System.out.println(words[0] +  "." + words[1] +  "." + words[2]);
} catch (Exception e) {
    // do your error handling
}
```
## Build
JDK and Maven need to be installed on your system.

  * Clone this repository
  * Run ``mvn package -DW3W_API_KEY=YOUR-API-KEY`` or
  * Run ``mvn package`` in case you do not want to enter your API key for test. Tests requiring API key will be skipped.
  * Under ``target/`` you will find the file ``w3w-java-wrapper-<version>-jar-with-dependencies.jar`` which can be embedded into your Java application

## Contributions

Any contribution is warmly welcome:

  - Documentation
  - Bug reporting
  - Bug fixes
  - Code enhancements

## Credits

Thanks to [what3words ](http://what3words.com/) for offering such a great
project and for any help we received by their team.

Also a big thank you to all who contributed to this project:
  * [@chrismayer](https://github.com/chrismayer)
  * [@tsamaya](https://github.com/tsamaya)
  * [@jstastny](https://github.com/jstastny)


## Who do I talk to?
You need more information or support? Please contact us at

info__(at)__meggsimum__(dot)__de
