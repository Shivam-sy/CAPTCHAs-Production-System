import java.awt.Color;

public class Config {
    private int width = 200;
    private int height = 80;
    private int length = 5;
    private int noise = 50;
    private boolean dark = false;
    private String[] fonts = {};
    private Color[] lightPalette = {Color.BLACK, Color.BLUE, Color.RED, Color.GREEN};
    private Color[] darkPalette = {Color.WHITE, Color.CYAN, Color.MAGENTA, Color.YELLOW};
    private Color lightBackgroundColor = Color.WHITE;
    private Color darkBackgroundColor = Color.DARK_GRAY;

    // Getters and Setters for all properties
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getNoise() {
        return noise;
    }

    public void setNoise(int noise) {
        this.noise = noise;
    }

    public boolean isDark() {
        return dark;
    }

    public void setDark(boolean dark) {
        this.dark = dark;
    }

    public String[] getFonts() {
        return fonts;
    }

    public void setFonts(String[] fonts) {
        this.fonts = fonts;
    }

    public Color[] getLightPalette() {
        return lightPalette;
    }

    public Color[] getDarkPalette() {
        return darkPalette;
    }

    public Color getLightBackgroundColor() {
        return lightBackgroundColor;
    }

    public void setLightBackgroundColor(Color lightBackgroundColor) {
        this.lightBackgroundColor = lightBackgroundColor;
    }

    public Color getDarkBackgroundColor() {
        return darkBackgroundColor;
    }

    public void setDarkBackgroundColor(Color darkBackgroundColor) {
        this.darkBackgroundColor = darkBackgroundColor;
    }
}
