package com.aquastilo.webapp.dto;

import com.aquastilo.webapp.model.User;

public class UserDto {

    private long id;

    private String email;

    public static UserDto fromUser(User user){
        UserDto dto = new UserDto();

        dto.email = user.getEmail();
        dto.id = user.getId();

        //TODO: agregar UriInfo
        return dto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
