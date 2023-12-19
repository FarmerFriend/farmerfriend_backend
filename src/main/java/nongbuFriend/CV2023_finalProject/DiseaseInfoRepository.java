package nongbuFriend.CV2023_finalProject;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiseaseInfoRepository extends JpaRepository<DiseaseInfo, Long> {
    Optional<DiseaseInfo> findByDiseaseName(String diseaseName);
}
