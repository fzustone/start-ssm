package org.ssm.mapper.student;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import org.ssm.model.student.Student;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenly1
 * @since 2019-11-19
 */
@Repository
public interface StudentMapper extends BaseMapper<Student> {

	Student selectByPrimaryKey(int id);
}
