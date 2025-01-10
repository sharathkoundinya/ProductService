package org.redlotus.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Category extends BaseModel {
    @Id
    private long id;
    private String title;

}
