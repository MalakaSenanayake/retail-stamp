package controller.dashbord;

import controller.LoginController;
import dto.ItemDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.FxAlertsUtil;
import javafx.util.FxComboBoxUtil;
import javafx.util.FxTableUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardTableDesign1Controller implements Initializable {

    @FXML
    private TextField searchTf;

    @FXML
    private TableView<ItemDTO> itemTable;

    @FXML
    private TableColumn<ItemDTO, Integer> indexCol;

    @FXML
    private TableColumn<ItemDTO, String> nameCol;

    @FXML
    private TableColumn<ItemDTO, Double> costCol;

    @FXML
    private TableColumn<ItemDTO, String> sellingPrizeCol;

    @FXML
    private TableColumn<ItemDTO, Double> discountCol;

    @FXML
    private TableColumn<ItemDTO, Double> profitCol;

    @FXML
    private TableColumn<ItemDTO, String> removeCol;

    @FXML
    private ComboBox<String> searchTypeCombo;

    List<ItemDTO> itemList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();// initialize the table
        loadDataToComboBox(); // load data to combobox
    }

    @FXML
    void searchBtnActionPerformed(ActionEvent event) {
        loadDataToTable();
    }
    @FXML
    void sellingPrizeColOnEditCommit(TableColumn.CellEditEvent<ItemDTO, String> event) {
        ItemDTO items = event.getRowValue();
        items.setSellingPrize(event.getNewValue());
        items.setDiscount(3);
        List<Object> list = FxTableUtil.getData(itemTable);
        FxTableUtil.setData(itemTable, list);
    }
    @FXML
    void searchTypeComboOnAction(ActionEvent event) {
        getSelectedItemFromComboBox();
    }
    //------------------------------------------------------------------------------------------------------------------
    private void initTable() {
        indexCol.setCellValueFactory(new PropertyValueFactory("index"));
        nameCol.setCellValueFactory(new PropertyValueFactory("itemName"));
        costCol.setCellValueFactory(new PropertyValueFactory("cost"));

        sellingPrizeCol.setCellValueFactory(new PropertyValueFactory("sellingPrize"));
        sellingPrizeCol.setCellFactory(TextFieldTableCell.<ItemDTO>forTableColumn());

        discountCol.setCellValueFactory(new PropertyValueFactory("discount"));
        profitCol.setCellValueFactory(new PropertyValueFactory("profit"));
        removeCol.setCellValueFactory(new PropertyValueFactory("status"));
    }
    //------------------------------------------------------------------------------------------------------------------
    private void loadDataToTable() {
        itemList = new ArrayList<>();
        for (int i = 1; i < 1000; i++) {
            ItemDTO items = new ItemDTO();
            items.setIndex(i);
            items.setItemName("Item - " + i);
            items.setCost(i * 75);
            items.setSellingPrize(((i * 75) + 50) + "");
            items.setDiscount(0);
            items.setProfit(50);
            items.setStatus("available");
            itemList.add(items);
        }
        FxTableUtil.setData(itemTable, itemList);
    }
    //------------------------------------------------------------------------------------------------------------------
    private void loadDataToComboBox() {
        List<String> dataList = new ArrayList<>();
        dataList.add("All");
        dataList.add("Search by name");
        dataList.add("Search by address");
        dataList.add("Search by telephone");
        dataList.add("Search by ref");
        dataList.add("Search by location");
        FxComboBoxUtil.setValueToComboBox(searchTypeCombo,dataList);
        searchTypeCombo.getSelectionModel().selectFirst();
    }
    //------------------------------------------------------------------------------------------------------------------
    private void getSelectedItemFromComboBox() {
        String selectedItem = searchTypeCombo.getSelectionModel().getSelectedItem();
        FxAlertsUtil.informationMessage(selectedItem, LoginController.getPrimaryStage());
    }
    //------------------------------------------------------------------------------------------------------------------
}
