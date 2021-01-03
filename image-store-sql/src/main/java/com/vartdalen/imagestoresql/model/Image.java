package com.vartdalen.imagestoresql.model;
import com.vartdalen.imagestoresql.model.enumerator.ImageCategory;
import com.vartdalen.imagestoresql.model.enumerator.ImageOrientation;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "orientation")
    @Enumerated(EnumType.STRING)
    private ImageOrientation orientation;
    @ElementCollection(targetClass = ImageCategory.class)
    @CollectionTable(name = "image_category", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Set<ImageCategory> categories;
    @Column(name = "bytes")
    private byte[] bytes;
    @Column(name = "created")
    private Timestamp created;

    public Image() {}

    public Image(long id, String title, ImageOrientation orientation, ImageCategory[] categories, byte[] bytes, Timestamp created) {
        this.id = id;
        this.title = title;
        this.orientation = orientation;
        this.categories = new HashSet<>(Arrays.asList(categories));
        this.bytes = bytes;
        this.created = created;
    }

    public long getId() { return id; }
    public String getTitle() { return title; }
    public ImageOrientation getOrientation() { return orientation; }
    public Set<ImageCategory> getCategories() { return categories; }
    public byte[] getBytes() { return bytes; }
    public Timestamp getCreated() { return created; }

    public void setId(long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setOrientation(ImageOrientation orientation) { this.orientation = orientation; }
    public void setCategories(ImageCategory[] categories) { this.categories = new HashSet<>(Arrays.asList(categories)); }
    public void setBytes(byte[] bytes) { this.bytes = bytes; }
    public void setCreated(Timestamp created) { this.created = created; }
}
