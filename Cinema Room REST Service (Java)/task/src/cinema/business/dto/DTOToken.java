package cinema.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DTOToken {
    private final String token;

    public DTOToken(@JsonProperty("token") String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
