package vn.elca.training.repository.custom;

import vn.elca.training.model.entity.Project;

import java.util.List;

/**
 * @author dtx
 *
 */
public interface ProjectRepositoryCustom {
    List<Project> findProjects(Short projectNumber, String name, String customer, String status);
}
