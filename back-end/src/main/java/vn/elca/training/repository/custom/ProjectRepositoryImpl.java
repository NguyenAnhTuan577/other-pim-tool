package vn.elca.training.repository.custom;

import com.querydsl.jpa.impl.JPAQuery;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.entity.QProject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author dtx
 *
 */
public class ProjectRepositoryImpl implements ProjectRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    private QProject qProject = QProject.project;


    @Override
    public List<Project> findProjects(Short projectNumber, String name, String customer, String status) {
        return new JPAQuery<Project>(em)
                .from(qProject)
                .where(
                        (qProject.projectNumber.eq(projectNumber)
                                .or(qProject.name.containsIgnoreCase(name))
                                .or(qProject.customer.containsIgnoreCase(customer))
                        ).and(qProject.status.eq(status))
                )
                .orderBy(qProject.projectNumber.asc())
                .fetch();
    }
}
