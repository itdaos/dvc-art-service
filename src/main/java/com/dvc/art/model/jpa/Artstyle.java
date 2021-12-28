package com.dvc.art.model.jpa;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "artstyles")
public class Artstyle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = 45)
    private String title;

    @Lob
    @Column(name = "summary")
    private String summary;

    @Column(name = "age")
    private Date age;

    public Date getAge() {
        return age;
    }

    public Artstyle setAge(Date age) {
        this.age = age;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public Artstyle setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Artstyle setTitle(String title) {
        this.title = title;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Artstyle setId(Long id) {
        this.id = id;
        return this;
    }
}