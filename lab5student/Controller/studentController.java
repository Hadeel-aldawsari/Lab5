package com.example.lab5student.Controller;


import com.example.lab5student.ApiResponse.ApiResponse;
import com.example.lab5student.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
public class studentController {
ArrayList <Student> students=new ArrayList<>();

@PostMapping("/create")
    public ApiResponse createStudent(@RequestBody Student student){
        students.add(student);
        return new ApiResponse("Student created successfully");
    }

    @GetMapping("/get-students")
    public ArrayList <Student> getStudents(){
    return students;
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateStudent(@RequestBody Student student,@PathVariable int index){
    if(index>students.size()){
        return new ApiResponse("Student not found");
    }
     students.set(index, student);
     return new ApiResponse("Student updated successfully");
    }


    @GetMapping("/classify/{index}")
    public ApiResponse classify(@PathVariable int index){
    double GPA=students.get(index).getGPA();
    if(GPA >=4.75)return new ApiResponse("First Honors");
    if (GPA>=4.50)return new ApiResponse("Second Honors");
    return new ApiResponse("Without Honors");
    }


    @GetMapping("/get-above-average")
    public ArrayList<Student> GetStudentAboveAverage(){
    ArrayList<Student>aboveAverage=new ArrayList<>();
    //calculate average
     double average=0;
     for (Student student : students) {
         average+=student.getGPA();
     }
     average/=students.size();

     //loop to add the student
        for (Student student : students) {
            if(student.getGPA()>average)aboveAverage.add(student);
        }

        return aboveAverage;
    }
}
