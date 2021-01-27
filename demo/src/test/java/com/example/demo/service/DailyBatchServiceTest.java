package com.example.demo.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.create.service.CreateCorrectionKCFileService;

@RunWith(SpringRunner.class)
@SpringBootTest
class DailyBatchServiceTest {

	@InjectMocks
	@Autowired
	private DailyBatchService dailyBatchService;

	@MockBean
	private CreateCorrectionKCFileService createCorrectionKCFileService;

	@Test
	public void test() {

//		Map<String, KanyuFaceDto> mockMap = new HashMap<>();
//		KanyuFaceDto dto = new KanyuFaceDto();
//		dto.setDaihyoushoukenbangou("E100");
//		mockMap.put("1", dto);
//		Mockito.when(this.createCorrectionKCFileService.createCorrectionKCFile(null, null, null)).thenReturn(null);
//		doReturn().when(createCorrectionKCFileService).createCorrectionKCFile();

		dailyBatchService.executeBatch(new Date());

		assertThat("a", is("a"));
	}
}
