package controller;


import common.java.util.NumberUtil;
import common.java.util.Print;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.FxDatePickerUtil;
import javafx.util.FxTextFieldUtil;
import net.sf.jasperreports.engine.*;
import utill.FxmlPath;
import utill.JasperCompiler;
import utill.ReportPath;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class PrintController implements Initializable {

    @FXML
    private AnchorPane loginMainAnchorPane;
    @FXML
    private DatePicker expDp;
    @FXML
    private DatePicker mfdDp;
    @FXML
    private TextField prizeTf;
    @FXML
    private ComboBox<Integer> lblCountCb;

    private static Stage primaryStage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validations();
        compileReport();
    }

    //------------------------------------------------------------------------------------------------------------------
    private void validations() {
        FxTextFieldUtil.toOnlyDecimalPositive(prizeTf);
        FxDatePickerUtil.toFormatWithToday(mfdDp);
        FxDatePickerUtil.toFormat(expDp);
        mfdDp.setEditable(false);
        expDp.setEditable(false);
        lblCountCb.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        lblCountCb.getSelectionModel().selectFirst();
    }

    //------------------------------------------------------------------------------------------------------------------
    private void compileReport() {
        JasperCompiler.compileReport(ReportPath.EXPIRE_DATE_LABEL);
    }

    //------------------------------------------------------------------------------------------------------------------
    @FXML
    void printBtnOnAction(ActionEvent event) {
        print();
    }

    //------------------------------------------------------------------------------------------------------------------
    private void print() {
        if (FxDatePickerUtil.isNotEmptyDatePicker(expDp)) {
            if (FxTextFieldUtil.isNotEmpty(prizeTf)) {
                int labelCount = lblCountCb.getValue() + 1;
                for (int i = 0; i < labelCount; i++) {
                    printSingleLabel();
                }
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    private void printSingleLabel() {
        String mfDate = mfdDp.getValue().toString();
        String expDate = expDp.getValue().toString();
        String price = prizeTf.getText();
        InputStream jasperStream = null;
        try {

            jasperStream = Files.newInputStream(Paths.get(ReportPath.EXPIRE_DATE_LABEL + ".jasper"));
            Map<String, Object> parameters = new HashMap<>();
            // Add your two String parameters
            // Second String value
            parameters.put("MFD", "MFD " + mfDate);      // First String value
            parameters.put("EXP", "EXP " + expDate);
            parameters.put("PRICE", "Rs:" + NumberUtil.toCurrencyFormat(price));
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperStream,
                    parameters,
                    new JREmptyDataSource());
            boolean printDialog = false; // Show print dialog
            JasperPrintManager.printReport(jasperPrint, printDialog);
            Print.info("Printing completed!");
        } catch (Exception e) {
            Print.error(e.getMessage());
        }
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
