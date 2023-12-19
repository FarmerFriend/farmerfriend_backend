package nongbuFriend.CV2023_finalProject;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class DiseaseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disease_id")
    private Long id;

    private String diseaseName;

    @Column(length = 10000)
    private String description;

    @Column(length = 10000)
    private String steps;

    public DiseaseInfo(String diseaseName, String description, String steps) {
        this.diseaseName = diseaseName;
        this.description = description;
        this.steps = steps;
    }

}
