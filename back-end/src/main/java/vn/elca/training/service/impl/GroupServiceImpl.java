package vn.elca.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vn.elca.training.model.dto.GroupDto;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.entity.Group;
import vn.elca.training.repository.EmployeeRepository;
import vn.elca.training.repository.GroupRepository;
import vn.elca.training.service.GroupService;
import vn.elca.training.util.mapper.GroupMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dtx
 *
 */
@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public GroupDto findOne(Long id) {
        return groupMapper.toDto(groupRepository.findOne(id));
    }

    @Override
    public List<GroupDto> findAll() {
        return groupRepository
                .findAll()
                .stream()
                .map(groupMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GroupDto save(GroupDto groupDto) {
        Employee groupLeader = employeeRepository.findOne(groupDto.getGroupLeader().getId());
        Group group = groupMapper.toEntity(groupDto, groupLeader);
        group = groupRepository.save(group);
        return groupMapper.toDto(group);
    }

    @Override
    public GroupDto update(GroupDto groupDto) {
        Employee groupLeader = employeeRepository.findOne(groupDto.getGroupLeader().getId());
        Group group = groupMapper.toEntity(groupDto, groupLeader);
        group = groupRepository.save(group);
        return groupMapper.toDto(group);
    }

    @Override
    public void delete(GroupDto groupDto) {
        groupRepository.deleteByIdIn(groupDto.getDeletedIds());
    }
}
