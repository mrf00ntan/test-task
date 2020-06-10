package kz.eserzhanov.testtask.testtask.dto.dir;

import kz.eserzhanov.testtask.testtask.dto.BaseDto;
import lombok.Data;

@Data
public class CityDto extends BaseDto {
    private Long code;
    private String name;
    private Long countryId;
}
