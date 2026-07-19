package data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import core.GlobalConstants;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.io.File;

public class Product {
    public static Product getProduct() {
        try {
            ObjectMapper mapper = JsonMapper.builder()
                    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                    .build();
            return mapper.readValue(new File(GlobalConstants.DATA_TEST_PATH + "product.json"), Product.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @JsonProperty("Register")
    private Register register;

    @JsonProperty("Login")
    private  Login login;

    public static class Register {
        @JsonProperty("fullname")
        private String fullname;
    }
    public static class Login {
        @JsonProperty("username")
        private String username;
        @JsonProperty("password")
        private String password;
    }

    public String getFullname() {
        return register.fullname;
    }

    public String getUsername() {
        return login.username;
    }

    public String getPassword() {
        return login.password;
    }
}


