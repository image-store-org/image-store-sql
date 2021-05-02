package com.vartdalen.imagestoresql.model;
import com.vartdalen.imagestoresql.model.enumerator.ImageCategory;
import com.vartdalen.imagestoresql.model.enumerator.ImageExtension;
import com.vartdalen.imagestoresql.model.enumerator.ImageOrientation;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "image")
public class Image implements DatabaseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id")
    private long id;

    @NotNull
    @Column(name = "title")
    private String title;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "extension")
    private ImageExtension extension;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "orientation")
    private ImageOrientation orientation;
    
    @ElementCollection(targetClass = ImageCategory.class)
    @CollectionTable(name = "image_category", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "category")
    private Set<ImageCategory> categories;

    @CreationTimestamp
    @Column(name = "created", nullable = false, insertable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp created;

    public Image() {}

    public Image(long id, String title, ImageExtension extension, ImageOrientation orientation, ImageCategory[] categories) {
        this.id = id;
        this.title = title;
        this.extension = extension;
        this.orientation = orientation;
        this.categories = new HashSet<>(Arrays.asList(categories));
    }

    public long getId() { return id; }
    public String getTitle() { return title; }
    public ImageExtension getExtension() { return extension; }
    public ImageOrientation getOrientation() { return orientation; }
    public Set<ImageCategory> getCategories() { return categories; }
    public LocalDateTime getCreated() { return created.toLocalDateTime(); }

    public void setId(long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setExtension(ImageExtension extension) { this.extension = extension; }
    public void setOrientation(ImageOrientation orientation) { this.orientation = orientation; }
    public void setCategories(ImageCategory[] categories) { this.categories = new HashSet<>(Arrays.asList(categories)); }
}
