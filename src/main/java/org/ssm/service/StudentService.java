package org.ssm.service;

import org.springframework.stereotype.Service;
import org.ssm.mapper.student.StudentMapper;
import org.ssm.model.student.Student;

import javax.annotation.Resource;

/**
 * @author cly
 * @create 2019-11-20 22:22
 */
@Service
public class StudentService {

	@Resource
	private StudentMapper studentMapper;

	public Student getStudent(int id) {
		return studentMapper.selectByPrimaryKey(id);

	}
}
