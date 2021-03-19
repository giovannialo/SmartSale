package controller;

import helper.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entity.MaterialModel;

import java.util.ArrayList;

public class MaterialController {

    @FXML
    private TextField textFieldSearch;

    @FXML
    private TableView<MaterialModel> tableViewMaterial;

    @FXML
    private TableColumn<MaterialModel, String> tableColumnMaterialName;

    @FXML
    private TableColumn<MaterialModel, String> tableColumnMaterialStock;

    @FXML
    private TableColumn<MaterialModel, String> tableColumnMaterialAlertEnding;

    @FXML
    private ObservableList<MaterialModel> observableListMaterial;

    @FXML
    private SortedList<MaterialModel> sortedListMaterial;

    @FXML
    protected void buttonAddOnAction() {
        Main.changeScreen("material-form");
    }

    @FXML
    protected void buttonDeleteOnAction() {
        Table.removeModelItem(
            tableViewMaterial,
            observableListMaterial,
            sortedListMaterial
        );
    }

    @FXML
    protected void buttonEditOnAction() {
        Table.editModelItem(tableViewMaterial, "material");
    }

    @FXML
    protected void initialize() {
        InternalScreenController.setPageTitle("Materiais");

        tableColumnMaterialName.setCellValueFactory(
            new PropertyValueFactory<>("name")
        );
        tableColumnMaterialStock.setCellValueFactory(
            new PropertyValueFactory<>("stockFull")
        );
        tableColumnMaterialAlertEnding.setCellValueFactory(
            new PropertyValueFactory<>("alertEndingFull")
        );

        loadTableContentMaterial();
    }

    /**
     * Lista todos os materiais cadastrados na tabela e ativa o campo de busca
     * para filtrar os dados ao digitar.
     */
    private void loadTableContentMaterial() {
        ArrayList materialList = (new MaterialModel(null)).find().order("id DESC").fetchAll();

        if (materialList == null) {
            return;
        }

        this.observableListMaterial = FXCollections.observableList(materialList);
        tableViewMaterial.setItems(observableListMaterial);

        FilteredList<MaterialModel> filteredList = new FilteredList<>(
            tableViewMaterial.getItems()
        );

        textFieldSearch.textProperty().addListener((
            (observableValue, oldValue, newValue) -> {
                filteredList.setPredicate(material -> {
                    if (newValue.isEmpty()) {
                        return true;
                    }

                    String newValueLowerCase = newValue.toLowerCase();

                    if (material.getName().toLowerCase().contains(newValueLowerCase)) {
                        return true;
                    } else if (material.getStockFull().toLowerCase().contains(newValueLowerCase)) {
                        return true;
                    }

                    return material.getAlertEndingFull().toLowerCase().contains(newValueLowerCase);
                });
            }
        ));

        this.sortedListMaterial = new SortedList<>(filteredList);
        sortedListMaterial.comparatorProperty().bind(
            tableViewMaterial.comparatorProperty()
        );

        tableViewMaterial.setItems(sortedListMaterial);
    }
}
