package controller;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SplashScreenController implements Initializable {
    @FXML
    private Label progressLabel;

    @FXML
    private Label statusLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void bindToTask(final Task<?> task) {
        statusLabel.textProperty().bind(task.messageProperty());
        // Format progress as percentage
        progressLabel.textProperty().bind(
                Bindings.createStringBinding(() -> {
                    double progress = task.getProgress();
                    return String.format("%.0f%%", progress * 100);
                }, task.progressProperty())
        );
    }
    // Manual update methods (alternative approach)---------------------------------------------------------------------
    public void updateStatus(String status) {
        Platform.runLater(() -> statusLabel.setText(status));
    }
    //------------------------------------------------------------------------------------------------------------------
    public void updateProgress(double progress) {
        Platform.runLater(() -> {
            progressLabel.setText(String.format("%.0f%%", progress * 100));
        });
    }
    //------------------------------------------------------------------------------------------------------------------
}
