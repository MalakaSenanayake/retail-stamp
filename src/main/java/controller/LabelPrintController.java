package controller;

import common.java.util.NumberUtil;
import common.java.util.Print;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.FxDatePickerUtil;
import javafx.util.FxTextFieldUtil;
import net.sf.jasperreports.engine.*;
import utill.JasperCompiler;
import utill.Path;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LabelPrintController implements Initializable {

    @FXML
    private AnchorPane loginMainAnchorPane;
    @FXML
    private DatePicker expDp;
    @FXML
    private DatePicker mfdDp;
    @FXML
    private TextField prizeTf;
    @FXML
    private TextField expireMonthTf;
    @FXML
    private ComboBox<Integer> lblCountCb;
    @FXML
    private CheckBox printDialogCb;

    private static Stage primaryStage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validations();
        compileReport();
    }

    @FXML
    void printBtnOnAction(ActionEvent event) {
        print();
    }

    @FXML
    void calculateDateBtnOnAction(ActionEvent event) {
         calculateDate();
    }

    //------------------------------------------------------------------------------------------------------------------
    private void validations() {
        FxTextFieldUtil.toOnlyDecimalPositive(prizeTf);
        FxTextFieldUtil.toOnlyDecimalPositive(expireMonthTf);
        FxDatePickerUtil.toFormatWithToday(mfdDp);
        FxDatePickerUtil.toFormat(expDp);
        mfdDp.setEditable(false);
        expDp.setEditable(false);
        lblCountCb.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        lblCountCb.getSelectionModel().selectFirst();
        expireMonthTf.setText("9");
    }

    //------------------------------------------------------------------------------------------------------------------
    private void calculateDate() {
        if (FxTextFieldUtil.isNotEmpty(expireMonthTf)) {
            FxDatePickerUtil.setDatePlusGivenMonths(mfdDp, expDp, Integer.parseInt(expireMonthTf.getText()));
        }

    }

    //------------------------------------------------------------------------------------------------------------------

    private void compileReport() {
        JasperCompiler.compileReport(Path.RETAIL_LABEL_REPORT);
    }

    //------------------------------------------------------------------------------------------------------------------

    private void print() {
        if (FxDatePickerUtil.isNotEmptyDatePicker(expDp)) {
            if (FxTextFieldUtil.isNotEmpty(prizeTf)) {
                int labelCount = lblCountCb.getValue();
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
            jasperStream = Files.newInputStream(Paths.get(Path.RETAIL_LABEL_REPORT + ".jasper"));
            Map<String, Object> parameters = new HashMap<>();
            // Add your two String parameters
            // Second String value
            parameters.put("MFD", "MFD: " + mfDate);      // First String value
            parameters.put("EXP", "EXP: " + expDate);
            parameters.put("PRICE", "MRP Rs:" + NumberUtil.toCurrencyFormat(price));
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperStream,
                    parameters,
                    new JREmptyDataSource());
            boolean printDialog = false; // Show print dialog
            printDialog = printDialogCb.isSelected();
            JasperPrintManager.printReport(jasperPrint, printDialog);
            Print.info("Printing completed!");
        } catch (Exception e) {
            Print.error(e.getMessage());
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    public static Stage getPrimaryStage() {
        return primaryStage; // Getter method for primaryStage
    }
    //------------------------------------------------------------------------------------------------------------------
}
