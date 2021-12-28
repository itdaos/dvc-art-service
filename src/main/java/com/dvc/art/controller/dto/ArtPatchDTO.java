package com.dvc.art.controller.dto;

import com.dvc.art.model.enums.ArtCategory;
import com.dvc.art.model.enums.ArtStatus;
import com.dvc.art.model.enums.ArtType;

import javax.validation.constraints.Size;

public class ArtPatchDTO {

    @Size(min=10, max=65, message = "The title mush be between 10 and 65 symbols")
    private String title;

    private ArtCategory category;

    private ArtType type;

    private ArtStatus status;

    private String description;

    private Long authorId;

    private Long ownerId;

    private Long artstyleId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArtCategory getCategory() {
        return category;
    }

    public void setCategory(ArtCategory category) {
        this.category = category;
    }

    public ArtType getType() {
        return type;
    }

    public void setType(ArtType type) {
        this.type = type;
    }

    public ArtStatus getStatus() {
        return status;
    }

    public void setStatus(ArtStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getArtstyleId() {
        return artstyleId;
    }

    public void setArtstyleId(Long artstyleId) {
        this.artstyleId = artstyleId;
    }

    public ArtPatchDTO() {
    }
}
