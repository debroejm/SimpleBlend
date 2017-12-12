package debroejm.simpleblend.util;

import debroejm.simpleblend.Color;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Utility that is used to scan pixel data for unique groups of color.
 */
public class PixelScanner {

    private final float epsilon;

    /**
     * Creates a new PixelScanner with a given epsilon value. The epsilon value
     * is used to determine the amount of similarity needed for pixels to be
     * considered in the same group.
     *
     * @param epsilon Float epsilon value
     */
    public PixelScanner(final float epsilon) {
        this.epsilon = epsilon;
    }

    /**
     * Scans a given selection of pixel data and returns a Collection of PixelClouds
     * representing unique groups of color.
     *
     * Pixel data is assumed to be in 32-bit ARGB form.
     *
     * @param data Integer array of pixel data
     * @return Collection of PixelClouds
     */
    public Collection<PixelCloud> scan(int[] data) {

        Set<PixelCloud> groups = new HashSet<>();

        for(int i = 0; i < data.length; i++) {

            final Color pixel = Color.ofRGB(data[i]);

            if(pixel.getAlpha() < 0.01f)
                continue;

            boolean found = false;
            for(final PixelCloud cloud : groups) {
                if(cloud.isSimilar(pixel, epsilon)) {
                    cloud.add(pixel);
                    found = true;
                    break;
                }
            }

            if(!found) {
                final PixelCloud cloud = new PixelCloud();
                cloud.add(pixel);
                groups.add(cloud);
            }
        }

        return groups;
    }
}
