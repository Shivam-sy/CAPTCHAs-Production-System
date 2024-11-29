import java.awt.image.BufferedImage;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
       
        Config config = new Config();
        config.setWidth(200);
        config.setHeight(80);
        config.setLength(6);
        config.setNoise(100);  
        config.setDark(true);  

       
        Captcha captcha = new Captcha(config);

        GeneratedCaptcha generatedCaptcha = captcha.generate();

        BufferedImage captchaImage = generatedCaptcha.getImage();
        String captchaCode = generatedCaptcha.getCode();

        System.out.println("Captcha Code: " + captchaCode);
 
        displayImage(captchaImage);
    }

    private static void displayImage(BufferedImage image) {

        JFrame frame = new JFrame("CAPTCHA");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon(image);
        JLabel label = new JLabel(icon);

        frame.add(label);
        
        frame.pack();
        frame.setVisible(true);
    }
}
