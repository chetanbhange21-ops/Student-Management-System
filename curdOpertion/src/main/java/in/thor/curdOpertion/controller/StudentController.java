package in.thor.curdOpertion.controller;

import in.thor.curdOpertion.dto.CreateStudentRequestDto;
import in.thor.curdOpertion.dto.CreateStudentResponseDto;
import in.thor.curdOpertion.dto.UpdateStudentRequestDto;
import in.thor.curdOpertion.entity.Student;
import in.thor.curdOpertion.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;

    }

    //create
    @PostMapping("/create")
    public ResponseEntity<CreateStudentResponseDto> createStudent(@RequestBody CreateStudentRequestDto studentRequestDto) {
        CreateStudentResponseDto createdStudent = studentService.createStudent(studentRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdStudent);
    }
    //read
    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student studentResp = studentService.getStudent(id);


        if (studentResp == null) {
            return ResponseEntity.notFound().build();
        }
//        if (studentResp==null){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
        return ResponseEntity.ok(studentResp);
    }
   // readAll
    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> studentList = studentService.getAllStudent();

        if (studentList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentList);
    }
    //updates
@PutMapping("/update/{id}")
    public ResponseEntity<Student>updateStudent(@PathVariable Long id,
                                                @RequestBody UpdateStudentRequestDto studentReq){
        Student studentResp=studentService.updateStudent(id,studentReq);

        if (studentResp==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentResp);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        Boolean isDeleted = studentService.deleteStudent(id);

        if (!isDeleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("record deleted");
    }

    @PatchMapping("/delete-soft/{id}")
    public ResponseEntity<String> deleteStudentSoftly(@PathVariable Long id) {
        Boolean isDeleted = studentService.deleteStudentSoftly(id);

        if(!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Record deleted");
    }
}