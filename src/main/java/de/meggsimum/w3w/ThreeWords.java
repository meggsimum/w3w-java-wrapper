package de.meggsimum.w3w;

/**
 * Class represents a "w3w-address" object holding the corresponding three
 * words.
 */
public class ThreeWords {

    /** the first word of this "w3w-address" */
    private String first;

    /** the first word of this "w3w-address" */
    private String second;

    /** the first word of this "w3w-address" */
    private String third;

    /**
     * Creates an empty {@linkplain ThreeWords} object.
     */
    public ThreeWords() {
    }

    /**
     * Creates a {@linkplain ThreeWords} object with the given three words.
     */
    public ThreeWords(String first, String second, String third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    /**
     * @return first word of this "w3w-address"
     */
    public String getFirst() {
        return first;
    }

    /**
     * @param first
     *            the first word of this "w3w-address" to set
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * @return second word of this "w3w-address"
     */
    public String getSecond() {
        return second;
    }

    /**
     * @param second
     *            the second word of this "w3w-address" to set
     */
    public void setSecond(String second) {
        this.second = second;
    }

    /**
     * @return third word of this "w3w-address"
     */
    public String getThird() {
        return third;
    }

    /**
     * @param third
     *            the third word of this "w3w-address" to set
     */
    public void setThird(String third) {
        this.third = third;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ThreeWords)) return false;

        ThreeWords that = (ThreeWords) o;

        if (!first.equals(that.first)) return false;
        if (!second.equals(that.second)) return false;
        return third.equals(that.third);

    }

    @Override
    public int hashCode() {
        int result = first.hashCode();
        result = 31 * result + second.hashCode();
        result = 31 * result + third.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ThreeWords{" +
                "first='" + first + '\'' +
                ", second='" + second + '\'' +
                ", third='" + third + '\'' +
                '}';
    }
}
