package com.prograCol.repository.dto;

import javax.validation.constraints.NotBlank;

public class FilterDto {

    @NotBlank(message = "Debe enviar una propiedad a filtrar.")
    private String name;
    @NotBlank(message = "Debe enviar el valor de propiedad a filtrar.")
    private String value;

    public FilterDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
