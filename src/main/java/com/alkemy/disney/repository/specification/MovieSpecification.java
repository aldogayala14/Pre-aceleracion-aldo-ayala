package com.alkemy.disney.repository.specification;


import com.alkemy.disney.dto.MovieFilterDTO;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;



@Component
public class MovieSpecification {

    public Specification<MovieEntity> getByFilters(MovieFilterDTO filterDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filterDTO.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filterDTO.getName().toLowerCase() + "%"
                        )
                );
            }

            if(StringUtils.hasLength(filterDTO.getDate())){
                // TODO: Reuse this in a function
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(filterDTO.getDate(),formatter);
                predicates.add(
                        criteriaBuilder.equal(root.<LocalDate>get("creationDate"),date)
                );
            }

            if (StringUtils.hasLength(filterDTO.getIdGender())) {
                String gender = filterDTO.getIdGender();
                predicates.add(
                        criteriaBuilder.equal(root.<Long>get("genderId"),gender)
                );
            }

            //Remove duplicates
            query.distinct(true);

            //Order resolver
            String orderByField = "title";
            query.orderBy(
                    filterDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));


        };
    }
}

