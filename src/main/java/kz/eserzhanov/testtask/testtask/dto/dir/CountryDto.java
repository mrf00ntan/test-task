package kz.eserzhanov.testtask.testtask.dto.dir;

import kz.eserzhanov.testtask.testtask.dto.BaseDto;
import lombok.Data;

@Data
public class CountryDto extends BaseDto {
    private Long code;
    private String name;
    private CityDto capital;
}
