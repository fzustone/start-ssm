package org.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.ssm.model.student.Student;
import org.ssm.service.StudentService;

/**
 * @author cly
 * @create 2019-11-20 22:22
 */
@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/student",method = RequestMethod.GET)
	public Student getStudent(@RequestParam(name = "id") int id){
		return studentService.getStudent(id);

	}
}
