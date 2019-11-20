/**
 * ${DESCRIPTION}
 *
 * @author cly33
 * @create 2019-04-11 0:17
 */

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ssm.model.student.Student;
import org.ssm.service.StudentService;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestMybatis {
	private static Logger logger = Logger.getLogger(TestMybatis.class);

	@Resource
	private StudentService studentService = null;

	@Test
	public void test1() {
		Student user = studentService.getStudent(0);
		System.out.println(user.getName());
		logger.info("值：" + user.getName());
		logger.info(JSON.toJSONString(user));
	}

}
