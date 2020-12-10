package com.vartdalen.imagestoresql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @Column(name="id")
    private long id;
    private String title;
    private String category;
    private byte[] bytes;
    private Timestamp created;

    public Image() {}

    public Image(long id, String title, String category, byte[] bytes, Timestamp created) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.bytes = bytes;
        this.created = created;
    }

    public long getId() { return id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public byte[] getBytes() { return bytes; }
    public Timestamp getCreated() { return created; }

    public void setId(long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setCategory(String category) { this.category = category; }
    public void setBytes(byte[] bytes) { this.bytes = bytes; }
    public void setCreated(Timestamp created) { this.created = created; }
}
