package com.goal.demo;

import com.goal.demo.config.AsyncSyncConfiguration;
import com.goal.demo.config.EmbeddedSQL;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(classes = { DemoApplication.class, AsyncSyncConfiguration.class })
@EmbeddedSQL
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

}
