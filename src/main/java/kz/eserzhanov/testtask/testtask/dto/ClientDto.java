package kz.eserzhanov.testtask.testtask.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ClientDto extends BaseDto{
    private Long iin;
    private Long cityId;
    private String fullName;

    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private Date birthDay;
    private Long phone;
}
