package com.example.MadelaPractice.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "Список идентификаторов")
public class IdListModel {

    @Schema(description = "Идентификаторы (пользователи, офисы и т.д.)")
    private List<Long> ids = new ArrayList<>();

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
