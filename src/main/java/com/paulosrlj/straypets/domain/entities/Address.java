package com.paulosrlj.straypets.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Address {

    @Column
    private String cep;

    @Column
    private String street;

    @Column
    private String sub_location;

    @Column
    private String city;
}
