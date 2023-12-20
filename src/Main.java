import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final String filename = "C:/Users/jakub/Desktop/equations.txt";
    public static void main(String[] args) throws FileNotFoundException{
        ExecutorService executor = Executors.newFixedThreadPool(3);

        File file = new File(filename);
        Scanner scan = new Scanner(file);

        // Na początku oddzielamy nasze wyniku od równanń dla czytelności
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (scan.hasNextLine()){
            String line = scan.nextLine();
            MyCallable call = new MyCallable(line);
            executor.submit(new MyFutureTask(call));
        }
        scan.close();
        executor.shutdown();
    }
}