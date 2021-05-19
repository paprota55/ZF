package pl.paprota.zf.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import pl.paprota.zf.dto.EmployeeDTO;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class EmployeeControllerTest {

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenEmployeeDTO_whenAddEmployee_thenStatus200() throws Exception {

        MvcResult result = this.mockMvc.perform(post("/api/addEmployee")
                .contentType("application/json")
                .content("{\"salary\" : \"4050\", \"name\" : \"Zbigniew\", \"surname\" : \"Nowak\", \"age\" : \"42\"}"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        String message = result.getResponse().getContentAsString();
        assertEquals('E',message.charAt(0));
    }

    @Test
    void givenEmployeeDTO_whenAddEmployee_ReturnMessage(){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Oskar");
        employeeDTO.setSurname("Kowalski");
        employeeDTO.setSalary(5000D);
        employeeDTO.setAge(29);

        ResponseEntity<?> entity = employeeController.addEmployee(employeeDTO);

        assertAll(() -> {
            assertEquals(200, entity.getStatusCodeValue());
            assertEquals('E', Objects.requireNonNull(entity.getBody()).toString().charAt(0));
        });
    }
}