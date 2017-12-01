package debroejm.simpleblend;

/**
 * Enumeration representing an ARGB color channel.
 */
public enum Channel {

    ALPHA(new Color(0.0f, 0.0f, 0.0f, 1.0f)),
    RED  (new Color(1.0f, 0.0f, 0.0f, 1.0f)),
    GREEN(new Color(0.0f, 1.0f, 0.0f, 1.0f)),
    BLUE (new Color(0.0f, 0.0f, 1.0f, 1.0f));

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
