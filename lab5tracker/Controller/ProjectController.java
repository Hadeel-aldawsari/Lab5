package com.example.lab5tracker.Controller;


import com.example.lab5tracker.ApiResponse.ApiResponse;
import com.example.lab5tracker.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    ArrayList<Project>projects=new ArrayList<>();

    @PostMapping("/create")
    public ApiResponse createProject(@RequestBody Project project) {
        projects.add(project);
        return new ApiResponse("Project created successfully");
    }

@GetMapping("/get-projects")
    public ArrayList<Project> getProjects() {
        return projects;
    }


    @PutMapping("/update/{index}")
    public ApiResponse updateProject(@RequestBody Project project,@PathVariable int index) {
        if(index>projects.size() )return new ApiResponse("Project index out of bounds");
        projects.set(index, project);
        return new ApiResponse("Project updated successfully");
    }
    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteProject(@PathVariable int index) {
        if(index>projects.size() )return new ApiResponse("Project index out of bounds");
        projects.remove(index);
        return new ApiResponse("Project deleted successfully");
    }


 @PutMapping("/change-status/{index}/{status}")
    public ApiResponse changeStatus(@PathVariable int index,@PathVariable String status) {
        if(projects.get(index).getStatus().equalsIgnoreCase("done")){
            return new ApiResponse("Project already done");}
        
        if (status.equalsIgnoreCase("not done")&& projects.get(index).getStatus().equalsIgnoreCase("not done"))
            return new ApiResponse("no changes to save");

        projects.get(index).setStatus(status);
        return new ApiResponse("Project status updated to done successfully");
    }

    @GetMapping("/search/{title}")
    public Project searchByTitle(@PathVariable String title) {
        for (Project project : projects) {
            if(project.getTitle().equalsIgnoreCase(title))
                return project;
            }
            return null;
        }


        @GetMapping("/get-by-company/{company}")
        public ArrayList<Project> getProjectsByCompany(@PathVariable String company) {
        ArrayList<Project> byCompany = new ArrayList<>();

        for (Project project : projects) {
            if(project.getCompanyName().equalsIgnoreCase(company))
                byCompany.add(project);
        }
        return byCompany;
        }

}
