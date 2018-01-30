package com.shop.api;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.api.web.dto.ConsumeDto;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ApiApplicationTests {

	MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationConnect;


	String expectedJson;

	@Before
	public void setUp() throws JsonProcessingException {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationConnect).build();

	}

	@Test
	public void testShow() throws Exception {
		Runnable runnable1 = new Runnable() {
			@Override
			public void run() {
				String uri = "/member/consume";
				String token = "eyJhbGciOiJIUzUxMiJ9.eyJjbGllbnRJZCI6InpoYW5jaGFvbGlnZXNob3BwaW5nbWFsbCIsInBob25lIjoiMTg4MTgyNzkyOTEiLCJleHAiOjE1MTczMDgzMjJ9.u-eWZy48IoRshbXWxj1RCQbtMpdtzfvwrvsPU4TFTrDH26abtG8-Tg26QV4YAU9lH_LCXFlSqoYtwOecXyW1JA";
				ConsumeDto dto = new ConsumeDto();
				dto.setMemberId(7L);
				dto.setConsumeMoney(new BigDecimal(30000));
				String response = null;
				try {
					response = mockMvc
                            .perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(dto))
                                    .header("access_token", token))
                            .andReturn().getResponse().getContentAsString();
				} catch (Exception e) {
					e.printStackTrace();
				}
				log.info("runnable1返回值："+response);
			}
		};
		Runnable runnable2 = new Runnable() {
			@Override
			public void run() {
				String uri = "/member/consume";
				String token = "eyJhbGciOiJIUzUxMiJ9.eyJjbGllbnRJZCI6InpoYW5jaGFvbGlnZXNob3BwaW5nbWFsbCIsInBob25lIjoiMTg4MTgyNzkyOTEiLCJleHAiOjE1MTczMDgzMjJ9.u-eWZy48IoRshbXWxj1RCQbtMpdtzfvwrvsPU4TFTrDH26abtG8-Tg26QV4YAU9lH_LCXFlSqoYtwOecXyW1JA";
				ConsumeDto dto = new ConsumeDto();
				dto.setMemberId(7L);
				dto.setConsumeMoney(new BigDecimal(30000));
				String response = null;
				try {
					response = mockMvc
                            .perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(dto))
                                    .header("access_token", token))
                            .andReturn().getResponse().getContentAsString();
				} catch (Exception e) {
					e.printStackTrace();
				}
				log.info("runnable2返回值："+response);
			}
		};
		runnable1.run();
		runnable2.run();
	}

}
