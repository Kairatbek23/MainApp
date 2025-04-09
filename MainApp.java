import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import java.io.IOException;

public class MainApp extends Application {
    private TaskManager taskManager;
    private ListView<Task> taskListView;
    private TextField inputField;

    @Override
    public void start(Stage primaryStage) {
        taskManager = new TaskManager();
        taskListView = new ListView<>();
        inputField = new TextField();
        Button addButton = new Button("Add Task");
        Button deleteButton = new Button("Delete Selected");

        HBox inputArea = new HBox(5, inputField, addButton);
        inputArea.setPadding(new Insets(10));
        VBox mainLayout = new VBox(10, inputArea, taskListView, deleteButton);
        mainLayout.setPadding(new Insets(10));

        try {
            FileHandler.loadFromFile("tasks.txt", taskManager.getTaskList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Task t : taskManager.getTaskList().toList()) {
            taskListView.getItems().add(t);
        }

        addButton.setOnAction(e -> handleAddTask());
        deleteButton.setOnAction(e -> handleDeleteTask());

        primaryStage.setScene(new Scene(mainLayout, 400, 300));
        primaryStage.setTitle("Task Manager");
        primaryStage.show();
    }

    @Override
    public void stop() {
        try {
            FileHandler.saveToFile("tasks.txt", taskManager.getTaskList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleAddTask() {
        String text = inputField.getText().trim();
        if (!text.isEmpty()) {
            taskManager.addTask(text);
            taskListView.getItems().add(new Task(text));
            inputField.clear();
        }
    }

    private void handleDeleteTask() {
        int index = taskListView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            taskManager.deleteTask(index);
            taskListView.getItems().remove(index);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
