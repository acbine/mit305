package com.example.tae.service;

import com.example.tae.entity.DummyData.Product.Project;
import com.example.tae.entity.DummyData.Product.ProjectPlan;
import com.example.tae.entity.DummyData.Product.ProjectPlanDTO;
import com.example.tae.repository.ProjectRepository.ProjectPlanRepository;
import com.example.tae.repository.ProjectRepository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    ProjectPlanRepository projectPlanRepository; //
    ProjectRepository projectRepository;

    @Override
    public List<String> productnameList() {
        List<Project> projectList=projectRepository.findAll();
        List<String> returnList2= new ArrayList<>();
        for(int n=0; n<projectList.size(); n++){
            returnList2.add(projectList.get(n).getProjectName());
        }

        return returnList2;
    }

    @Override
    public List<ProjectPlanDTO> list() { //모든 생산계획불러오기
        List<ProjectPlan> projectPlan=projectPlanRepository.findAll();
        List<ProjectPlanDTO> returnList = new ArrayList<>();
        for(int i=0; i<projectPlan.size(); i++){
            ProjectPlanDTO addListDTO = ProjectPlanDTO.builder()
                    .projectCode(projectPlan.get(i).getId())
                    .projectName(projectPlan.get(i).getProject().getProjectName())
                    .setProjectPlanDate(projectPlan.get(i).getProjectOutputDate())
                    .setouputNum(projectPlan.get(i).getOutPuteNum())
                    .build();
            returnList.add(addListDTO);
        }
        return returnList;
    }

    @Override
    public void register(String projectName,  String setouputNum , String setProjectPlanDate) { //생산계획등록\
        Project project = Project.builder().projectName(projectName).build();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = dateFormat.parse(setProjectPlanDate);

            ProjectPlan projectPlan = ProjectPlan.builder()
                    .project(project)
                    .projectOutputDate(date)
                    .outPuteNum(Integer.parseInt(setouputNum))
                    .build();

            projectPlanRepository.save(projectPlan);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int code) {
        projectPlanRepository.deleteById(code);
    }
}
