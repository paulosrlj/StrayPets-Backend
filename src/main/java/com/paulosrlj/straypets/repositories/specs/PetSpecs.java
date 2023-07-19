package com.paulosrlj.straypets.repositories.specs;

import com.paulosrlj.straypets.domain.entities.Pet;
import com.paulosrlj.straypets.domain.filters.PetFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class PetSpecs {

    public static Specification<Pet> filterPets(PetFilter filter) {

        return new Specification<Pet>() {
            @Override
            public Predicate toPredicate(Root<Pet> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                var predicates = new ArrayList<Predicate>();

                if (filter.getName() != null) {
                    predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.getName() + "%"));
                }

                if (filter.getType() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("type"), filter.getType()));
                }

                if (filter.getGender() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("gender"), filter.getGender()));
                }

                if (filter.getBreed() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("breed"), filter.getBreed()));
                }

                if (filter.getMissing() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("missing"), filter.getMissing()));
                }

                if (filter.getStart_adoption_date() != null && filter.getEnd_adoption_date() != null) {
                    predicates.add(criteriaBuilder.between(root.get("adoption_date"),
                            filter.getStart_adoption_date(),
                            filter.getEnd_adoption_date()));
                }

                if (filter.getCep() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("location").get("address").get("cep"), filter.getCep()));
                }

                if (filter.getSub_locality() != null) {
                    predicates.add(criteriaBuilder.like(
                            root.get("location").get("address").get("bairro"), "%" + filter.getSub_locality() + "%"
                    ));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }

}
