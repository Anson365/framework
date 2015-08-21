import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
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
	
//	public void testRedis(){
//		Jedis jredis = new Jedis("sdf",23434);
//		jredis.set("safd", "sdf");
//		jredis.expire("sdfd",39089809);
//		jredis.get("sdf");
//		
//	}

}
