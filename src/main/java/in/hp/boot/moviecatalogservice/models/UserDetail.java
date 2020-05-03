package in.hp.boot.moviecatalogservice.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDetail {
    private String fname;
    private String lname;
    private String email;
    private LocalDate date_of_birth;
}
