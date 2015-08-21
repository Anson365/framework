import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.anson.nosql.RedisUtil;
import com.anson.service.interfaces.IUserService;
import com.anson.user.model.Users;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml","classpath:spring-mybatis.xml","classpath:spring-redis.xml"})
public class TestUserService {
	
	@Autowired
	private IUserService userService;

	@Test
	public void testGetUserById(){
		
		Users user = userService.getUserById(1);
		System.out.println(JSON.toJSONString(user));
	}

	@Autowired
	private RedisUtil redisUtil;

	@Test
	public void test() {
		long key = 20,value = 2;
		String token = redisUtil.genToken(20);
		redisUtil.set(key, value, 100);
		long id = Long.valueOf(redisUtil.get(token).toString());
		long id2 = Long.parseLong(redisUtil.get(id).toString());
		System.out.println(id);
		System.out.println(id2);
	}

}
