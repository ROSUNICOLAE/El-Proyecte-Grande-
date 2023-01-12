package com.codecool.App;

import com.codecool.App.Teacher;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class TeacherController {
    private List<Teacher> teachers = new ArrayList<>();

    // simple
    @GetMapping("/")
    public String index() {
        return "Hello learner!";
    }
//JSON for testing
//    {
//        "id": "2",
//         "subject": "English",
//         "email": "@@@"
//    }
    @PostMapping("/")
    public void createTeacher(@RequestBody Teacher teacher){
        System.out.println(teacher.getSubject());
    }

    // teacher
    @GetMapping("/teacher")
    public Teacher getTeacher() {
        Teacher teacher = new Teacher(0, "Math", "email@putpatch.ro");
        return teacher;
    }
   //trimitem teacher din postman, o trece, o mapeaza si o trimite inapoi in postman, atat
    @PostMapping("/teacher")
    public Teacher createTeacher2(@RequestBody Teacher teacher){
        return teacher;
    }

    @GetMapping("/showTeachers")
    public List<Teacher> myTeachers() {
        System.out.println(teachers);
        return teachers;
    }

    @PostMapping("/showTeachers")
    public void create(@RequestBody Teacher teacher) {
        teachers.add(teacher);
        System.out.println(teachers);
    }

// if not teacher with id the resource is updated and new resource is created
// workflow - *get*, post, get, put, get
// change path for put, before get change path again
    @PutMapping("/showTeachers/{id}")
    public void updateTeacher(@PathVariable int id, @RequestBody UpdateTeacher teacherDetails) {
        Map<Integer, Teacher> teachersMap = teachers.stream()
                .collect(Collectors.toMap(Teacher::getId, Function.identity()));
        Teacher storedTeacherDetails = teachersMap.get(id);
        teachersMap.put(id, storedTeacherDetails);
        storedTeacherDetails.setSubject(teacherDetails.getSubject());
        System.out.println(teachersMap);
        teachers.clear();
        teachers.addAll(teachersMap.values());
    }

    @DeleteMapping("/showTeachers/{id}")
    public void deleteTeacher(@PathVariable int id) {
        Map<Integer, Teacher> teachersMap = teachers.stream()
                .collect(Collectors.toMap(Teacher::getId, Function.identity()));
        teachersMap.remove(id);
        System.out.println(teachersMap);
        teachers.clear();
        teachers.addAll(teachersMap.values());
    }

    @PatchMapping("/showTeachers/{id}")
    public void updateTeacherSubject(@PathVariable int id, @RequestBody UpdateTeacher teacherDetails) {
        Map<Integer, Teacher> teachersMap = teachers.stream()
                .collect(Collectors.toMap(Teacher::getId, Function.identity()));
        Teacher storedTeacherDetails = teachersMap.get(id);
        teachersMap.put(id, storedTeacherDetails);
        storedTeacherDetails.setSubject(teacherDetails.getSubject());
        storedTeacherDetails.setEmail(teacherDetails.getEmail());
        System.out.println(teachersMap);
        teachers.clear();
        teachers.addAll(teachersMap.values());
    }
//test
}
