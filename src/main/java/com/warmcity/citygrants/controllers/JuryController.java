package com.warmcity.citygrants.controllers;

import com.warmcity.citygrants.dto.ProjectApplJuryDTO;
import com.warmcity.citygrants.models.Project;
import com.warmcity.citygrants.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jury")
public class JuryController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/project/{juryId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectApplJuryDTO> getAllProjects(@PathVariable String juryId) {
        return projectService.getAllJuryProjects(juryId);
    }
}
