import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Captcha {
    private Config config;
    private Font[] fonts;
    private RandomStringGenerator randomStringGenerator;

    public Captcha() {
        this.setConfig(new Config());
    }

    public Captcha(Config config) {
        this.setConfig(config);
    }

    public void setRandomStringGenerator(RandomStringGenerator randomStringGenerator) {
        this.randomStringGenerator = randomStringGenerator;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public GeneratedCaptcha generate() {
        if (randomStringGenerator == null) {
            randomStringGenerator = new RandomStringGenerator(config.getLength());
        }

        this.fonts = config.getFonts().length > 0 ? loadCustomFonts() :
                GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();

        String code = randomStringGenerator.next();
        BufferedImage captchaImage = new BufferedImage(config.getWidth(), config.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = captchaImage.createGraphics();

        drawCode(graphics, code);
        drawNoise(graphics);

        return new GeneratedCaptcha(captchaImage, code);
    }

    private Font[] loadCustomFonts() {
        Font[] fonts = new Font[config.getFonts().length];
        if (config.getFonts().length == 0) return fonts;

        try {
            for (int i = 0; i < config.getFonts().length; i++) {
                File fontFile = new File(config.getFonts()[i]);
                Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(font);
                fonts[i] = font;
            }
        } catch (IOException | FontFormatException e) {
            throw new FontLoadException("Load custom fonts failed!", e);
        }

        return fonts;
    }

    private Font getRandomFont() {
        Random random = new Random();
        int[] fontStyles = {Font.PLAIN, Font.BOLD, Font.ITALIC, Font.BOLD + Font.ITALIC};
        int index = random.nextInt(fonts.length);
        int fontStyle = fontStyles[random.nextInt(fontStyles.length)];
        int fontSize = config.getHeight() / 2 + random.nextInt(15) - 5;

        return fonts[index].deriveFont(fontStyle, fontSize);
    }

    private Color getRandomColor() {
        Color[] currentPalette = config.isDark() ? config.getDarkPalette() : config.getLightPalette();
        Random random = new Random();
        return currentPalette[random.nextInt(currentPalette.length)];
    }

    private void drawCode(Graphics graphics, String code) {
        if (config.isDark()) {
            graphics.setColor(config.getDarkBackgroundColor());
            graphics.fillRect(0, 0, config.getWidth(), config.getHeight());
        } else {
            graphics.setColor(config.getLightBackgroundColor());
            graphics.fillRect(0, 0, config.getWidth(), config.getHeight());
        }

        FontMetrics fontMetrics = graphics.getFontMetrics();
        int totalWidth = fontMetrics.stringWidth(code);
        int charGap = (config.getWidth() - totalWidth) / (code.length() + 1);
        int x = charGap;
        int y = (config.getHeight() - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();

        Random random = new Random();

        for (int i = 0; i < code.length(); i++) {
            graphics.setColor(getRandomColor());

            int charX = x + fontMetrics.charWidth(code.charAt(i)) / 2;
            int angle = random.nextInt(41) - 20;

            Graphics2D g2d = (Graphics2D) graphics;
            g2d.rotate(Math.toRadians(angle), charX, y);
            Font randomFont = getRandomFont();
            graphics.setFont(randomFont);
            graphics.drawString(String.valueOf(code.charAt(i)), charX, y);
            g2d.rotate(-Math.toRadians(angle), charX, y);

            x += fontMetrics.charWidth(code.charAt(i)) + charGap;
        }
    }

    private void drawNoise(Graphics graphics) {
        Random random = new Random();
        for (int i = 0; i < config.getNoise(); i++) {
            int x1 = random.nextInt(config.getWidth());
            int y1 = random.nextInt(config.getHeight());
            int x2 = x1 + random.nextInt(50) - 25;
            int y2 = y1 + random.nextInt(50) - 25;
            graphics.setColor(getRandomColor());
            graphics.drawLine(x1, y1, x2, y2);
        }
    }
}
