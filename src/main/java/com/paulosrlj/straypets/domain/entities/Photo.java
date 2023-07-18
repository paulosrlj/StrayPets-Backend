package com.paulosrlj.straypets.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String photo_name;

    @Column(columnDefinition = "text")
    private String photo_uri;

    @ManyToOne
    @JoinColumn(name = "pet_id", referencedColumnName = "id")
    @JsonIgnore
    private Pet pet;
}
