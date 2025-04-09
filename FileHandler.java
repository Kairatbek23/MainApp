import java.io.*;

public class FileHandler {
    public static void saveToFile(String filename, TaskList tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        TaskList.Node curr = tasks.getHead();
        while (curr != null) {
            writer.write(curr.task.getDescription());
            writer.newLine();
            curr = curr.next;
        }
        writer.close();
    }

    public static void loadFromFile(String filename, TaskList tasks) throws IOException {
        File file = new File(filename);
        if (!file.exists()) return;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                tasks.add(new Task(line.trim()));
            }
        }
        reader.close();
    }
}
