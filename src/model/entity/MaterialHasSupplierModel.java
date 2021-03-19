package model.entity;

import model.datalayer.DataLayer;

public class MaterialHasSupplierModel extends DataLayer {

    public MaterialHasSupplierModel(String behaviorToSave) {
        super(behaviorToSave);

        this.table = "material_has_supplier";
        this.required = new String[]{
            "material_id",
            "supplier_id"
        };
    }

    public String getMaterialId() {
        return (String) data.get("material_id");
    }

    public void setMaterialId(String materialId) {
        data.put("material_id", materialId);
    }

    public String getSupplierId() {
        return (String) data.get("supplier_id");
    }

    public void setSupplierId(String supplierId) {
        data.put("supplier_id", supplierId);
    }

    public String getId() {
        return (String) data.get("id");
    }

    public void setId(String id) {
        data.put("id", id);
    }
}
