package vn.elca.training.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.elca.training.model.dto.GroupDto;
import vn.elca.training.service.GroupService;

import java.util.List;

import static vn.elca.training.util.constant.Constant.GROUP_URL;
import static vn.elca.training.util.constant.Constant.ID;

/**
 * @author dtx
 *
 */
@RestController
public class GroupController {
    @Autowired
    private GroupService groupService;

    @GetMapping(value = GROUP_URL + ID)
    public GroupDto getGroup(@PathVariable Long id) {
        return groupService.findOne(id);
    }

    @GetMapping(value = GROUP_URL)
    public List<GroupDto> getGroups() {
        return groupService.findAll();
    }

    @PostMapping(value = GROUP_URL)
    public GroupDto saveGroup(@RequestBody GroupDto groupDto) {
        return groupService.save(groupDto);
    }

    @PutMapping(value = GROUP_URL)
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        return groupService.update(groupDto);
    }

    @DeleteMapping(value = GROUP_URL)
    public void deleteGroups(@RequestBody GroupDto groupDto) {
        groupService.delete(groupDto);
    }
}
