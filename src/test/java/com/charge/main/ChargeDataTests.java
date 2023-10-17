package com.charge.main;

import com.charge.entity.ChargeDataRecord;
import com.charge.entity.User;
import com.charge.exception.CommonException;
import com.charge.repository.ChargeDataRepo;
import com.charge.repository.UserRepo;
import com.charge.util.GeneralUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = SpringApplicationTests.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class ChargeDataTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ChargeDataRepo chargeDataRepo;
    @Autowired
    private UserRepo userRepo;


    // write test cases here
    @Test
    public void givenEmployees_whenGetEmployees_thenStatus200()
            throws Exception {
        ChargeDataRecord chargeDataRecord = new ChargeDataRecord();

        User user = userRepo.findByEmailIdAndUniqueId("kk8415906@gmail.com", "1234");
        if (GeneralUtils.isNull(user)) {
            throw new CommonException(HttpStatus.NOT_FOUND, "user not found");
        }
        chargeDataRecord.setSessionId(GeneralUtils.generateSessionId());
        chargeDataRecord.setStartTime(LocalDateTime.now());
        chargeDataRecord.setEndTime(LocalDateTime.now().plusMinutes(30));
        chargeDataRecord.setVehicleId("SDF121212WWQQ");
        chargeDataRecord.setCost(BigDecimal.valueOf(200L));
        chargeDataRecord.setUser(user);
        chargeDataRepo.save(chargeDataRecord);

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v0/chargeData")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}