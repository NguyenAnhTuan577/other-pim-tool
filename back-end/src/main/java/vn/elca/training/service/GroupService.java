package vn.elca.training.service;

import vn.elca.training.model.dto.GroupDto;

import java.util.List;

/**
 * @author dtx
 *
 */
public interface GroupService {
    GroupDto findOne(Long id);
    List<GroupDto> findAll();
    GroupDto save(GroupDto groupDto);
    GroupDto update(GroupDto groupDto);
    void delete(GroupDto groupDto);
}
