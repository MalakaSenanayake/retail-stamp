package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utill.FxmlPath;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private AnchorPane loginMainAnchorPane;

    private static Stage primaryStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void closeBthOnAction(ActionEvent event) {
        logOutSystem();
        System.exit(0);
    }

    @FXML
    void loginBtnOnAction(ActionEvent event) {
        ((Stage) loginMainAnchorPane.getScene().getWindow()).close();
        login();
    }

    //------------------------------------------------------------------------------------------------------------------
    private void login() {
        viewUserMainPage();
    }
    //------------------------------------------------------------------------------------------------------------------
    private void viewUserMainPage() {
        primaryStage = new Stage();
        AnchorPane rootpane = new AnchorPane();
        try {
            FXMLLoader loder = new FXMLLoader();
            loder.setLocation(FxmlPath.HOME);
            rootpane = loder.load();
            Scene sc = new Scene(rootpane);
            primaryStage.setMaximized(true);
            primaryStage.centerOnScreen();
            primaryStage.setTitle("Theme 1 @ creative IT"); // Application name
            primaryStage.getIcons().add(new Image("/image/appIcon.png"));
            primaryStage.setScene(sc);
            primaryStage.show();
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent we) {
                    System.out.println("closed");
                }

            });
            primaryStage.iconifiedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    System.out.println("minimize window");
                }
            });
        } catch (IOException ex) {
            System.err.println("LoginController - loadAdminMain" + ex);
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    private void logOutSystem() {
        System.out.println("logOutSystem");
    }
    //------------------------------------------------------------------------------------------------------------------
    public static Stage getPrimaryStage() {
        return primaryStage; // Getter method for primaryStage
    }
}
