package vn.elca.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;
import vn.elca.training.model.entity.Project;
import vn.elca.training.repository.custom.ProjectRepositoryCustom;

import java.util.List;

/**
 * @author dtx
 *
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>, QueryDslPredicateExecutor<Project>, ProjectRepositoryCustom {
    List<Project> findAllByOrderByProjectNumberAsc();
    List<Project> findProjectsByStatusOrderByProjectNumberAsc(String status);
    List<Project> findProjectsByProjectNumberOrNameContainingIgnoreCaseOrCustomerContainingIgnoreCaseOrderByProjectNumberAsc(Short projectNumber, String name, String customer);
    Project findByProjectNumber(Short projectNumber);
    void deleteByIdIn(Long[] ids);
}
