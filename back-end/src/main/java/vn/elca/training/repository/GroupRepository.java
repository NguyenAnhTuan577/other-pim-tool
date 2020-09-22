package vn.elca.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.elca.training.model.entity.Group;

/**
 * @author dtx
 *
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    void deleteByIdIn(Long[] ids);
}
