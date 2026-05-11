package com.example.MadelaPractice.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.*;

@Schema(description = "Создание поставщика")
public class SupplierSaveModel {

    @NotBlank
    @Size(max = 255)
    private String legalName;

    @NotBlank
    @Size(max = 20)
    private String inn;

    @NotBlank
    @Email
    @Size(max = 320)
    private String contactEmail;

    @Min(1)
    @Max(5)
    private Integer rating;

    @Size(max = 500)
    private String commentText;

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
