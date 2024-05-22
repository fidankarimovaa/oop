package ms.labtaskapi.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {
    //id olmalidir yoxsa error olacaq
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private int stockCount;
}
