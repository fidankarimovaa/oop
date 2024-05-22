package ms.labtaskapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@Builder
public class ProductRequestDto {
    @NotBlank
    private String name;
    @Positive
    @NotNull
    private double price;
    @NotBlank
    private int stockCount;


}

