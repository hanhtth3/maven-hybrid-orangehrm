package data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import core.GlobalConstants;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.io.File;

public class Employee {
    public static Employee getEmployee() {
        try {
            ObjectMapper mapper = JsonMapper.builder()
                    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                    .build();
            return mapper.readValue(new File(GlobalConstants.DATA_TEST_PATH + "employee.json"), Employee.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @JsonProperty("fisrtName")
    private String fisrtName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("DOB")
    private String DOB;
    @JsonProperty("username")
    private String username;
    @JsonProperty("address")
    private String address;
    @JsonProperty("password")
    private String password;

    public String getFisrtName() {
        return fisrtName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDOB() {
        return DOB;
    }

    public String getAddress() {
        return address;
    }
}
