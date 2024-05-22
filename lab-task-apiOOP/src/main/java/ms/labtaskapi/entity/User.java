package ms.labtaskapi.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_Name")
    private String userName;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "age")
    private int age;
    @Column(name = "balance")
    private double balance;
    @Column(name = "enable")
    private boolean enable ;
}
