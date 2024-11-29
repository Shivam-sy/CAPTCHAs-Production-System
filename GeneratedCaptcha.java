import java.awt.image.BufferedImage;

public class GeneratedCaptcha {
    private final BufferedImage image;
    private final String code;

    public GeneratedCaptcha(BufferedImage image, String code) {
        this.image = image;
        this.code = code;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getCode() {
        return code;
    }
}
