package controller;

import helper.Form;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entity.MaterialHasSupplierModel;
import model.entity.MaterialModel;
import model.entity.MeasureTypeModel;
import model.entity.SupplierModel;

import java.util.ArrayList;

public class MaterialFormController {

    private MaterialModel materialModel;

    @FXML
    private Label labelFeedbackName;

    @FXML
    private Label labelFeedbackStock;

    @FXML
    private Label labelFeedbackMeasureType;

    @FXML
    private Label labelFeedbackAlertEnding;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldStock;

    @FXML
    private TextField textFieldAlertEnding;

    @FXML
    private TextField textFieldSearch;

    @FXML
    private ComboBox<MeasureTypeModel> comboBoxMeasureType;

    @FXML
    private TableView<SupplierModel> tableViewSupplier;

    @FXML
    private TableColumn<SupplierModel, CheckBox> tableColumnSupplierCheck;

    @FXML
    private TableColumn<SupplierModel, String> tableColumnSupplierName;

    @FXML
    private ObservableList<SupplierModel> observableListSupplier;

    @FXML
    private SortedList<SupplierModel> sortedListSupplier;

    @FXML
    protected void buttonCancelOnAction() {
        Main.changeScreen("material");
    }

    @FXML
    protected void buttonSubmitOnAction() {
        boolean block = false;

        Form.resetFeedback(
            labelFeedbackName,
            labelFeedbackStock,
            labelFeedbackMeasureType,
            labelFeedbackAlertEnding
        );
        Form.resetField(textFieldName, textFieldStock, textFieldAlertEnding);
        Form.resetField(comboBoxMeasureType);

        if (textFieldName.getText().isEmpty()) {
            Form.invalidField(textFieldName, labelFeedbackName, "Obrigatório");
            block = true;
        }

        if (textFieldStock.getText().isEmpty()) {
            Form.invalidField(textFieldStock, labelFeedbackStock, "Obrigatório");
            block = true;
        }

        if (textFieldAlertEnding.getText().isEmpty()) {
            Form.invalidField(
                textFieldAlertEnding,
                labelFeedbackAlertEnding,
                "Obrigatório"
            );

            block = true;
        }

        if (comboBoxMeasureType.getValue() == null) {
            Form.invalidField(
                comboBoxMeasureType,
                labelFeedbackMeasureType,
                "Obrigatório"
            );

            block = true;
        }

        if (block) {
            return;
        }

        MaterialModel materialModel = (this.materialModel != null ? this.materialModel : new MaterialModel(null));
        materialModel.setMeasureTypeId(
            comboBoxMeasureType.getSelectionModel().getSelectedItem().getId()
        );
        materialModel.setName(textFieldName.getText());
        materialModel.setStock(textFieldStock.getText());
        materialModel.setAlertEnding(textFieldAlertEnding.getText());

        boolean save = materialModel.save();

        if (materialModel.fail() != null) {
            System.out.println(materialModel.fail().getMessage());
            return;
        }

        if (!save) {
            if (materialModel.returnMessage().get("name") != null) {
                Form.invalidField(
                    textFieldName,
                    labelFeedbackName,
                    materialModel.returnMessage().get("name").toString()
                );
            }

            return;
        }

        for (int i = 0; i < tableViewSupplier.getItems().size(); i++) {
            if (tableViewSupplier.getItems().get(i).getCheckBox().isSelected()) {
                MaterialHasSupplierModel materialHasSupplierModel = new MaterialHasSupplierModel(null);
                materialHasSupplierModel.setSupplierId(tableViewSupplier.getItems().get(i).getId());
                materialHasSupplierModel.setMaterialId(materialModel.getId());
                materialHasSupplierModel.save();
            } else {
                MaterialHasSupplierModel materialHasSupplierModel = (MaterialHasSupplierModel) (new MaterialHasSupplierModel(null)).find(
                    "supplier_id = '" + tableViewSupplier.getItems().get(i).getId() + "' AND material_id = '" + materialModel.getId() + "'"
                ).fetch();

                if (materialHasSupplierModel != null) {
                    materialHasSupplierModel.destroy();
                }
            }
        }

        Main.changeScreen("material");
    }

    @FXML
    protected void initialize() {
        ArrayList measureTypeList = (new MeasureTypeModel(null)).find().fetchAll();
        comboBoxMeasureType.setItems(FXCollections.observableList(measureTypeList));

        Form.onlyNumber(textFieldStock);
        Form.onlyNumber(textFieldAlertEnding);

        if (Main.getStage().getUserData() == null) {
            InternalScreenController.setPageTitle("Materiais ● Adicionar novo");
        } else {
            this.materialModel = (MaterialModel) Main.getStage().getUserData();
            Main.getStage().setUserData(null);

            InternalScreenController.setPageTitle(
                "Materiais ● " + materialModel.getName()
            );

            textFieldName.setText(materialModel.getName());
            textFieldStock.setText(materialModel.getStock());
            textFieldAlertEnding.setText(materialModel.getAlertEnding());

            for (MeasureTypeModel measureTypeModel : comboBoxMeasureType.getItems()) {
                if (measureTypeModel.getId().equals(materialModel.getMeasureTypeId())) {
                    comboBoxMeasureType.getSelectionModel().select(measureTypeModel);
                    break;
                }
            }
        }

        tableColumnSupplierCheck.setCellValueFactory(
            new PropertyValueFactory<>("checkBox")
        );
        tableColumnSupplierName.setCellValueFactory(
            new PropertyValueFactory<>("name")
        );

        loadTableContentSupplier();
    }

    /**
     * Lista todos os fornecedores cadastrados na tabela e ativa o campo de
     * busca para filtrar os dados ao digitar.
     */
    private void loadTableContentSupplier() {
        ArrayList supplierList = (new SupplierModel(null)).find().order("id DESC").fetchAll();

        if (supplierList == null) {
            return;
        }

        this.observableListSupplier = FXCollections.observableList(supplierList);
        tableViewSupplier.setItems(observableListSupplier);

        FilteredList<SupplierModel> filteredList = new FilteredList<>(
            tableViewSupplier.getItems()
        );

        textFieldSearch.textProperty().addListener((
            (observableValue, oldValue, newValue) -> {
                filteredList.setPredicate(supplier -> {
                    if (newValue.isEmpty()) {
                        return true;
                    }

                    return supplier.getName().toLowerCase().contains(
                        newValue.toLowerCase()
                    );
                });
            }
        ));

        this.sortedListSupplier = new SortedList<>(filteredList);
        sortedListSupplier.comparatorProperty().bind(
            tableViewSupplier.comparatorProperty()
        );

        tableViewSupplier.setItems(sortedListSupplier);

        if (this.materialModel == null) {
            return;
        }

        ArrayList<MaterialHasSupplierModel> materialHasSupplierList = this.materialModel.getSuppliers();

        if (materialHasSupplierList == null) {
            return;
        }

        ArrayList supplierIds = new ArrayList();

        for (MaterialHasSupplierModel materialHasSupplierModel : materialHasSupplierList) {
            supplierIds.add(materialHasSupplierModel.getSupplierId());
        }

        for (int i = 0; i < tableViewSupplier.getItems().size(); i++) {
            if (supplierIds.contains(tableViewSupplier.getItems().get(i).getId())) {
                tableViewSupplier.getItems().get(i).getCheckBox().setSelected(true);
            }
        }
    }
}
