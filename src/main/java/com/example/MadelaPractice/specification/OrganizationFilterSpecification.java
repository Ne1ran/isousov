package com.example.MadelaPractice.specification;

import com.example.MadelaPractice.entity.OrganizationEntity;
import com.example.MadelaPractice.repository.OrganizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrganizationFilterSpecification {

    @Autowired
    private OrganizationRepo organizationRepo;

    public List<OrganizationEntity> findOrganizationFilter(String name, String inn, Boolean isActive){
        List<OrganizationEntity> result = null;

        Specification specification = new Specification<OrganizationEntity>() {
            List<Predicate> predicates = new ArrayList<>();
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                if (null != name){
                    predicates.add(criteriaBuilder.like(root.get("name"), "%"+name+"%"));
                }
                if (null != inn){
                    predicates.add(criteriaBuilder.like(root.get("inn"), "%"+inn+"%"));
                }
//                if (null != isActive){
//                    predicates.add(criteriaBuilder.like(root.get("name"), "%"+name+"%"));
//                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        result = this.organizationRepo.findAll(specification);
        return result;
    }
}
