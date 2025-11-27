package controller.dashbord;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.util.FxAnchorPaneUtil;
import javafx.util.FxButtonUtil;
import utill.FxmlPath;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardMainController implements Initializable {

    @FXML
    private Button tableSample1Btn;

    @FXML
    private Button tableSample2Btn;

    @FXML
    private Button componentsBtn;

    @FXML
    private AnchorPane pageLoadingSpace;

    @FXML
    private Region backRegionPane;

    @FXML
    private ProgressIndicator loadingProgress;

    Button allButtons[];


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        config();
    }

    private void config() {
        allButtons = new Button[]{tableSample1Btn, tableSample2Btn, componentsBtn};
    }

    @FXML
    void componentsBtnOnAction(ActionEvent event) {
        viewAllComponentsPage();
    }


    @FXML
    void tableSample1BtnOnAction(ActionEvent event) {
        viewTableSample1Page();
    }


    @FXML
    void tableSample2BtnOnAction(ActionEvent event) {
        viewtablesample2();
    }


    private void viewAllComponentsPage() {
        FxButtonUtil.setSelectedBtn(allButtons, componentsBtn);
        try {
            FxAnchorPaneUtil.setPageToLodingSpace(FxmlPath.DASHBOARD_ALL_COMPONENTS, pageLoadingSpace);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void viewTableSample1Page() {
        FxButtonUtil.setSelectedBtn(allButtons, tableSample1Btn);
        try {
            FxAnchorPaneUtil.setPageToLodingSpace(FxmlPath.DASHBOARD_TABLE_DESIGN_1, pageLoadingSpace);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void viewtablesample2() {
        FxButtonUtil.setSelectedBtn(allButtons, tableSample2Btn);
        try {
            FxAnchorPaneUtil.setPageToLodingSpace(FxmlPath.EMPTY, pageLoadingSpace);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


}
