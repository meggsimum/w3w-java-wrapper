# w3w-java-wrapper
Java wrapper for the what3words API

Use the what3words API in your Java application (see http://developer.what3words.com/api)

## Methods

  - wordsToPosition --> Converts 3 words into a position
  - positionToWords --> Converts a position into a "w3w-address"

## Code Samples

```java
What3Words w3w = new What3Words(API_KEY);
String[] words = {"goldfish", "fuzzy", "aggregates"};
try {
    double[] coords = w3w.wordsToPosition(words);
    // ...
} catch (Exception e) {
    // do your error handling
}
```

```java
What3Words w3w = new What3Words(API_KEY);
Double[] coords = {49.422636, 8.320833};
try {
  String[] words = w3w.positionToWords(coords);
  // ...
} catch (Exception e) {
  // do your error handling
}
```
