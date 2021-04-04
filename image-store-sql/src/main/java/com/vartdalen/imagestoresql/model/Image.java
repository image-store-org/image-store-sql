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
public class Image {

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
    @Column(name = "orientation")
    private ImageOrientation orientation;
    
    @ElementCollection(targetClass = ImageCategory.class)
    @CollectionTable(name = "image_category", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "category")
    private Set<ImageCategory> categories;
    
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "extension")
    private ImageExtension extension;

    @CreationTimestamp
    @Column(name = "created", nullable = false, insertable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp created;

    public Image() {}

    public Image(long id, String title, ImageOrientation orientation, ImageCategory[] categories, ImageExtension extension) {
        this.id = id;
        this.title = title;
        this.orientation = orientation;
        this.categories = new HashSet<>(Arrays.asList(categories));
        this.extension = extension;
    }

    public long getId() { return id; }
    public String getTitle() { return title; }
    public ImageOrientation getOrientation() { return orientation; }
    public Set<ImageCategory> getCategories() { return categories; }
    public ImageExtension getExtension() { return extension; }
    public LocalDateTime getCreated() { return created.toLocalDateTime(); }

    public void setId(long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setOrientation(ImageOrientation orientation) { this.orientation = orientation; }
    public void setCategories(ImageCategory[] categories) { this.categories = new HashSet<>(Arrays.asList(categories)); }
    public void setExtension(ImageExtension extension) { this.extension = extension; }
}
