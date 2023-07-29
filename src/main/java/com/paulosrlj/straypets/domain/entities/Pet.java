package com.paulosrlj.straypets.domain.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paulosrlj.straypets.enums.PetGender;
import com.paulosrlj.straypets.enums.PetType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(name = "pet")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private PetType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true, name = "gender")
    private PetGender gender;

    @Column(nullable = true)
    private String breed;

    @Column(nullable = true)
    private Date adoption_date;

    @Column(nullable = true)
    private Date find_date;

    @Column(nullable = true)
    private String comments;

    @Column(nullable = false)
    private Boolean missing = false;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true, referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Photo> photos;

    public void adoptPet(User user) {
        this.setMissing(false);
        this.setUser(user);
        this.setAdoption_date(new Date());
    }

    public void markMissingPetAsFound(User user) {
        this.setMissing(false);
        this.setUser(user);
        this.setFind_date(new Date());
    }
}