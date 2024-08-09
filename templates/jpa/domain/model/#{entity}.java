package #{group}.#{project}.feature.#{entityLowercase}.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "#{entityPlural}")
public class #{entity} {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private #{idType} id;
}
