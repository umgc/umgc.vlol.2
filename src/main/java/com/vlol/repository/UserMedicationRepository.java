package com.vlol.repository;

import com.vlol.model.UserMedication;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserMedicationRepository extends JpaRepository<UserMedication, Long> {
    
    @Query(value = "SELECT m FROM User_Medication m WHERE lower(m.brandName) LIKE lower(concat('%', :keyword, '%'))"
            + "OR lower(m.genericName) LIKE lower(concat('%', :keyword, '%'))"
            + "OR lower(m.drugAction) LIKE lower(concat('%', :keyword, '%'))", nativeQuery = true)
    public List<UserMedication> findMedicationByKeyword(@Param("keyword") String keyword);
    
    @Query(value = "SELECT m FROM User u, UserMedication m WHERE u.email = :email")
    public List<UserMedication> findMedicationByEmail(@Param("email") String email);
    
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM UserMedication m WHERE m.id = :id")
    public void deleteByPK(@Param("id") Long id);
}