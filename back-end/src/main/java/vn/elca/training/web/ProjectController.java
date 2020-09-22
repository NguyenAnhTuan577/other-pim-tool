package vn.elca.training.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.service.ProjectService;

import javax.validation.Valid;
import java.util.List;

import static vn.elca.training.util.constant.Constant.ID;
import static vn.elca.training.util.constant.Constant.PROJECT_URL;

/**
 * @author dtx
 *
 */
@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping(value = PROJECT_URL + ID)
    public ProjectDto getProject(@PathVariable Long id) {
        return projectService.findOne(id);
    }

    @GetMapping(value = PROJECT_URL)
    public List<ProjectDto> getProjects(@RequestParam(required = false) String input, @RequestParam(required = false) String status) {
        return projectService.findProjects(input, status);
    }

    @PostMapping(value = PROJECT_URL)
    public ProjectDto saveProject(@RequestBody @Valid ProjectDto projectDto) {
        return projectService.save(projectDto);
    }

    @PutMapping(value = PROJECT_URL)
    public ProjectDto updateProject(@RequestBody @Valid ProjectDto projectDto) {
        return projectService.update(projectDto);
    }

    @DeleteMapping(value = PROJECT_URL)
    public void deleteProjects(@RequestBody ProjectDto projectDto) {
        projectService.delete(projectDto);
    }
}
