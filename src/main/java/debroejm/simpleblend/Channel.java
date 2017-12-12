package debroejm.simpleblend;

/**
 * Enumeration representing an ARGB color channel.
 */
public enum Channel {

    ALPHA(Color.ofRGB(0xFF000000)),
    RED  (Color.ofRGB(0xFFFF0000)),
    GREEN(Color.ofRGB(0xFF00FF00)),
    BLUE (Color.ofRGB(0xFF0000FF));

    private final Color color;
    Channel(final Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        switch(this) {
            case ALPHA: return "Alpha";
            case RED: return "Red";
            case GREEN: return "Green";
            case BLUE: return "Blue";
            default: return "?";
        }
    }

    /**
     * Retrieves the pure color associated with a given channel.
     *
     * I.e. the Channel RED will return a pure red color.
     *
     * @return Color value
     */
    public Color getColor() {
        return color;
    }
}
