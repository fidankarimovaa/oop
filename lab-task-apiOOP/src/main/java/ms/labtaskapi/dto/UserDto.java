package ms.labtaskapi.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String userName;
    private String name;
    private String surname;
    private int age;
    private double balance;
    private boolean enable;
}
