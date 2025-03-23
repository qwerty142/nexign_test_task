package org.example.repository.tables;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String number;

    String name;
}
