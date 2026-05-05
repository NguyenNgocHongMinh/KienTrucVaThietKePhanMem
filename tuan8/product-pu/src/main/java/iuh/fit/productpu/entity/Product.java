package iuh.fit.productpu.entity;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    private static final long serialVersionUID = 1L; // THÊM DÒNG NÀY

    private String id;
    private String name;
    private double price;
    private int stock;
}
