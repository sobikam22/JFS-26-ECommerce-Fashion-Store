import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVDataLoader {

    public static void loadProductsFromCSV(String filePath) {
        // Using try-with-resources to ensure BufferedReader automatically closes
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println("Reading products.csv seed data...");
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                System.out.println("Loaded Product: " + values[0]);
            }
        } catch (IOException e) {
            System.out.println("CSV file not found or could not be read (expected if file doesn't exist yet): " + e.getMessage());
        }
    }
}