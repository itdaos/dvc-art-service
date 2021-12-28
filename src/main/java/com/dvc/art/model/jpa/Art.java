package com.dvc.art.model.jpa;

import com.dvc.art.model.enums.ArtCategory;
import com.dvc.art.model.enums.ArtStatus;
import com.dvc.art.model.enums.ArtType;
import com.dvc.art.model.jpa.*;
import jdk.jfr.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="art_objects")
public class Art {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min=10, max=65, message="Title must be between 10 and 65 characters")
    private String title;

    @Enumerated(EnumType.STRING)
    private ArtCategory category;

    @Enumerated(EnumType.STRING)
    private ArtType type;

    @Enumerated(EnumType.STRING)
    private ArtStatus status;

    private String description;

    @JoinColumn(name = "owner_id")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    User owner;

    @JoinColumn(name = "author_id")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Author.class)
    Author author;

    @JoinColumn(name = "artstyle_id")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Artstyle.class)
    Artstyle artstyle;

    @Timestamp
    Long time_created;

    @Timestamp
    Long time_modified;

    public Art() {}

    public Art(String title, User owner) {
        this.title = title;
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Artstyle getArtstyle() {
        return artstyle;
    }

    public void setArtstyle(Artstyle artstyle) {
        this.artstyle = artstyle;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Long getTime_created() {
        return time_created;
    }

    public Long getTime_modified() {
        return time_modified;
    }
}
