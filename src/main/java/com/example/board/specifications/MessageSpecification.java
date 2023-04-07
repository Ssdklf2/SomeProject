package com.example.board.specifications;

import com.example.board.models.Message;
import com.example.board.models.enums.MessageType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class MessageSpecification implements Specification<Message> {

    List<SearchCriteria> list;

    public MessageSpecification() {
        this.list = new ArrayList<>();
    }

    public void addCriteria(SearchCriteria searchCriteria) {
        list.add(searchCriteria);
    }

    @Override
    public Predicate toPredicate(Root<Message> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        for (SearchCriteria criteria : list) {
            SearchOperation operation = criteria.getOperation();
            switch (operation) {
                case EQUAL -> {
                    if (criteria.getKey().equals("messageType")) {
                        MessageType type = MessageType.valueOf(criteria.getValue1());
                        predicates.add(
                                builder.equal(root.get(criteria.getKey()), type));
                    } else {
                        predicates.add(
                                builder.equal(root.get(criteria.getKey()), criteria.getValue1()));
                    }
                }
                case LIKE -> {
                    predicates.add(
                            builder.like(root.get(criteria.getKey()), "%" + criteria.getValue1() + "%"));
                }
                case BETWEEN -> {
                    predicates.add(
                            builder.between(
                                    root.get(criteria.getKey()),
                                    Integer.valueOf(criteria.getValue1()), Integer.valueOf(criteria.getValue2())));
                }
                case GREATER_THAN -> {
                    predicates.add(
                            builder.greaterThanOrEqualTo(
                                    root.get(criteria.getKey()), Integer.valueOf(criteria.getValue1())
                            )
                    );
                }
                case LESS_THAN -> {
                    predicates.add(
                            builder.lessThanOrEqualTo(
                                    root.get(criteria.getKey()), Integer.valueOf(criteria.getValue1())
                            )
                    );
                }
            }
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
