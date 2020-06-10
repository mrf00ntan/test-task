package kz.eserzhanov.testtask.testtask.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class RequestDto {
    private Long userId;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private Date createdDate;
    private String requestParam;
}
