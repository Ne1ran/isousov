package com.example.MadelaPractice.model;

import com.example.MadelaPractice.entity.OfficeEntity;

public class OfficeOnlyIdModel {
    private Long id;

    public static OfficeOnlyIdModel toModel(OfficeEntity officeEntity){
        OfficeOnlyIdModel model = new OfficeOnlyIdModel();
        model.setId(officeEntity.getId());
        return model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OfficeOnlyIdModel() {
    }
}
