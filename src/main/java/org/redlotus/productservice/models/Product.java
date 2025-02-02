package org.redlotus.productservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter @Setter
public class Product extends BaseModel  {
//    @Id
//    private long id;  //Moved to BaseModel class
    private String title;
    private String description;
    private double price;
    private String image;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    private Category category;


}
