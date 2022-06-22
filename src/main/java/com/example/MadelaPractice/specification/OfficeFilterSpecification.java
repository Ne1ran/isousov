package com.example.MadelaPractice.specification;

import com.example.MadelaPractice.entity.OfficeEntity;
import com.example.MadelaPractice.entity.OrganizationEntity;
import com.example.MadelaPractice.repository.OfficeRepo;
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
public class OfficeFilterSpecification {

    @Autowired
    private OfficeRepo officeRepo;

    public List<OfficeEntity> findOfficesFilter(Long orgId, String name, String phone, Boolean isActive){
        List<OfficeEntity> result = null;

        Specification specification = new Specification<OfficeEntity>() {
            List<Predicate> predicates = new ArrayList<>();
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                if (null != orgId){
                    predicates.add(criteriaBuilder.equal(root.get("orgId").get("id"), orgId));
                }
                if (null != name){
                    predicates.add(criteriaBuilder.like(root.get("name"), "%"+name+"%"));
                }
                if (null != phone){
                    predicates.add(criteriaBuilder.like(root.get("phone"), "%"+phone+"%"));
                }
                if (null != isActive){
                    predicates.add(criteriaBuilder.like(root.get("isActive"), "%"+isActive+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        result = this.officeRepo.findAll(specification);
        return result;
    }
}
