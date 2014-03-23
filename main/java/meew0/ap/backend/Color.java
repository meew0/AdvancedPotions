package meew0.ap.backend;

/**
 * Created by meew0 on 23.03.14.
 */
public class Color {

    public int red, green, blue, alpha;

    public Color() {
    }

    public Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Color(int red, int green, int blue, int alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getAlpha() {
        return alpha;
    }

    public void set(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void set(int red, int green, int blue, int alpha) {
        set(red, green, blue);
        this.alpha = alpha;
    }
}

