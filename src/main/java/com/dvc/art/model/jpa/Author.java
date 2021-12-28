package com.dvc.art.model.jpa;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name= "authors")
public class Author {
    @Basic
    @Column(name = "birthday")
    private Date birthday;
    @Basic
    @Column(name = "deathday")
    private Date deathday;
    @Basic
    @Column(name = "bio")
    private String bio;
    @Basic
    @Column(name = "pseudoname")
    private String pseudoname;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private long id;
    @Basic
    @Column(name = "full_name")
    private String fullName;
    @Basic
    @Column(name = "portrait_pic")
    private String portraitPic;

    public Author() {
    }

    public Author(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getDeathday() {
        return deathday;
    }

    public void setDeathday(Date deathday) {
        this.deathday = deathday;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPseudoname() {
        return pseudoname;
    }

    public void setPseudoname(String pseudoname) {
        this.pseudoname = pseudoname;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPortraitPic() {
        return portraitPic;
    }

    public void setPortraitPic(String portraitPic) {
        this.portraitPic = portraitPic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (id != author.id) return false;
        if (birthday != null ? !birthday.equals(author.birthday) : author.birthday != null) return false;
        if (deathday != null ? !deathday.equals(author.deathday) : author.deathday != null) return false;
        if (bio != null ? !bio.equals(author.bio) : author.bio != null) return false;
        if (pseudoname != null ? !pseudoname.equals(author.pseudoname) : author.pseudoname != null) return false;
        if (fullName != null ? !fullName.equals(author.fullName) : author.fullName != null) return false;
        if (portraitPic != null ? !portraitPic.equals(author.portraitPic) : author.portraitPic != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long result = id;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (deathday != null ? deathday.hashCode() : 0);
        result = 31 * result + (bio != null ? bio.hashCode() : 0);
        result = 31 * result + (portraitPic != null ? portraitPic.hashCode() : 0);
        result = 31 * result + (pseudoname != null ? pseudoname.hashCode() : 0);
        return (int) result;
    }
}