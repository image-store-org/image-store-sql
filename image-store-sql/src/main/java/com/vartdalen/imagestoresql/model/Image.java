package com.vartdalen.imagestoresql.model;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column
    private String title;
    @Column
    private String category;
    @Column
    private byte[] bytes;
    @Column
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
