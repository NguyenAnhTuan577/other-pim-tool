package vn.elca.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.elca.training.model.entity.Employee;

import java.util.Set;

/**
 * @author dtx
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Set<Employee> findByVisaIn(Set<String> visa);
    void deleteByIdIn(Long[] ids);
}
