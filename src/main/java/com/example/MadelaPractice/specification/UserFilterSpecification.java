package com.example.MadelaPractice.specification;

import com.example.MadelaPractice.entity.UserEntity;
import com.example.MadelaPractice.repository.UserRepo;
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
public class UserFilterSpecification {
    @Autowired
    private UserRepo userRepo;

    public List<UserEntity> findUserFilter(Long officeId, String firstName, String lastName, String middleName,
                                                   String position, Long docCode, Long citizenshipCode){
        List<UserEntity> result = null;

        Specification specification = new Specification<UserEntity>() {
            List<Predicate> predicates = new ArrayList<>();
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                if (null != officeId){
                    predicates.add(criteriaBuilder.equal(root.get("office_id").get("id"), officeId));
                }
                if (null != firstName){
                    predicates.add(criteriaBuilder.like(root.get("firstName"), "%"+firstName+"%"));
                }
                if (null != lastName){
                    predicates.add(criteriaBuilder.like(root.get("lastName"), "%"+lastName+"%"));
                }
                if (null != middleName){
                    predicates.add(criteriaBuilder.like(root.get("middleName"), "%"+middleName+"%"));
                }
                if (null != position){
                    predicates.add(criteriaBuilder.like(root.get("position"), "%"+position+"%"));
                }
                if (null != docCode){
                    predicates.add(criteriaBuilder.equal(root.get("document_id").get("code"), docCode));
                }
                if (null != citizenshipCode){
                    predicates.add(criteriaBuilder.equal(root.get("country_id").get("code"), citizenshipCode));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        result = this.userRepo.findAll(specification);
        return result;
    }
}
