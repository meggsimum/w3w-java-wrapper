# w3w-java-wrapper
Java wrapper for the [what3words](http://what3words.com/)[web-API](http://developer.what3words.com/api).

Use the what3words API in your Java applications.

## Methods

  ```double[] wordsToPosition(String[] words)```

    Converts a 3 words 'address' into a position
  ```String[] positionToWords(Double[] coords)```

    Converts a position into a 3 words 'address'

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
    w3w.setLanguage("de");
    String[] words = w3w.positionToWords(coords);
    System.out.println(words[0] +  "." + words[1] +  "." + words[2]);
} catch (Exception e) {
    // do your error handling
}
```
## Build
JDK and Maven need to be installed on your system.

  * Clone this repository
  * Enter your w3w API-key in the [What3WordsTest class](https://github.com/meggsimum/w3w-java-wrapper/blob/master/src/test/java/de/meggsimum/w3w/What3WordsTest.java#L17)
  * Run ``mvn package`` or
  * Run ``mvn package -DskipTests`` (in case you do not want to enter your API-key)
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


## Who do I talk to?
You need more information or support? Please contact us at

info__(at)__meggsimum__(dot)__de
