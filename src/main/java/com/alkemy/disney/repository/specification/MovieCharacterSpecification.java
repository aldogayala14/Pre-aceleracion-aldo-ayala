package com.alkemy.disney.repository.specification;

import com.alkemy.disney.dto.MovieCharacterFilterDTO;
import com.alkemy.disney.entity.MovieCharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieCharacterSpecification {

    public Specification<MovieCharacterEntity> getByFilters(MovieCharacterFilterDTO movieCharacterFilterDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasLength(movieCharacterFilterDTO.getName())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + movieCharacterFilterDTO.getName().toLowerCase() + "%"
                        )
                );
            }

            if (StringUtils.hasLength(movieCharacterFilterDTO.getAge())) {
                String age = movieCharacterFilterDTO.getAge();
                predicates.add(
                        criteriaBuilder.equal(root.<Long>get("age"),age)
                );
            }

            if (StringUtils.hasLength(movieCharacterFilterDTO.getWeight())) {
                String weight = movieCharacterFilterDTO.getWeight();
                predicates.add(
                        criteriaBuilder.equal(root.<Long>get("weight"),weight)
                );
            }

            /*
            *if(!CollectionUtils.isEmpty(filterDTO.getCharacters())){
                Join<MovieCharacterEntity, MovieEntity> join = root.join("characters", JoinType.INNER);
                Expression<String> charactersId = join.get("id");
                predicates.add(charactersId.in(filterDTO.getCharacters()));

            }
            * */
            if(!CollectionUtils.isEmpty(movieCharacterFilterDTO.getMovies())){
                Join<MovieEntity,MovieCharacterEntity> join = root.join("movies", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(movieCharacterFilterDTO.getMovies()));

            }

            //Remove duplicates
            query.distinct(true);

            //Order resolver
            String orderByField = "name";
            query.orderBy(
                    movieCharacterFilterDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
