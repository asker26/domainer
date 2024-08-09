package #{group}.#{project}.feature.#{entityLowercase}.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "#{entityPlural}")
public class #{entity} {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String description;

    private Long longitude;

    private Long latitude;

    private LocalDateTime modifiedAt;

    private LocalDateTime createdAt;
}
