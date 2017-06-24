import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunLengthEncoding {

    public static String encode(String source) {
        StringBuilder dest = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            int runLength = 1;
            while (i + 1 < source.length() && source.charAt(i) == source.charAt(i + 1)) {
                runLength++;
                i++;
            }
            dest.append(runLength);
            dest.append(source.charAt(i));
        }
        return dest.toString();
    }

    public static String decode(String source) {
        StringBuilder dest = new StringBuilder();
        Pattern pattern = Pattern.compile("[0-9]+|[a-zA-Z]");
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            int number = Integer.parseInt(matcher.group());
            matcher.find();
            while (number-- != 0) {
                dest.append(matcher.group());
            }
        }
        return dest.toString();
    }

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        try {
            FileReader reader = new FileReader("string.txt");

            int k;
            while ((k = reader.read()) != -1) {
                builder.append((char) k);
            }
            // System.out.println(builder);
            FileWriter writer = new FileWriter("encoded.txt");
            String writeRes = encode(builder.toString());
            writer.write(writeRes);
            writer.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        File file = new File("string.txt");
        System.out.println("File Name: " + file.getName() + " " + file.length() + " bytes");
        File fileR = new File("encoded.txt");
        System.out.println("File Name: " + fileR.getName() + " " + fileR.length() + " bytes");

    }
}