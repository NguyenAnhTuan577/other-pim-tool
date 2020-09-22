package vn.elca.training.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author dtx
 *
 */
@Entity
@Table(name = "group_table")
@Getter
@Setter
@NoArgsConstructor
public class Group extends GenericEntity {

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "group_leader_id")
    private Employee groupLeader;

    @OneToMany(mappedBy = "group")
    private Set<Project> projects = new HashSet<>();
}
