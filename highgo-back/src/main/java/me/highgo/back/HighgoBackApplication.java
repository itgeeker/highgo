package me.highgo.back;

import me.highgo.back.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:spring/applicationContext.xml") //导入spring的配置我呢间
public class HighgoBackApplication implements CommandLineRunner{

	@Autowired
	private UserMapper userMapper;

	public static void main(String[] args) {
		SpringApplication.run(HighgoBackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
