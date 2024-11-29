
import java.util.Random;

public class RandomStringGenerator {
    private static final String CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final int length;
    private final Random random;

    public RandomStringGenerator(int length) {
        this.length = length;
        this.random = new Random();
    }

    public String next() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARSET.length());
            stringBuilder.append(CHARSET.charAt(index));
        }
        return stringBuilder.toString();

    
    }
}