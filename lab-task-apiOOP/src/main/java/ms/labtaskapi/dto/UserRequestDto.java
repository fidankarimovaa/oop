package ms.labtaskapi.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDto {
    @NotBlank
    private String userName;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotNull
    @Positive
    private int age;
    private double balance;
    private boolean enable;
}
