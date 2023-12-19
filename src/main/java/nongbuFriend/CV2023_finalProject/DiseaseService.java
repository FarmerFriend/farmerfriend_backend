package nongbuFriend.CV2023_finalProject;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor

public class DiseaseService {
    @Autowired
    private final DiseaseInfoRepository diseaseInfoRepository;

    @Transactional
    public void createPlants(List<DiseaseInfoSaveRequest> requestList){
        for (DiseaseInfoSaveRequest request : requestList) {
            DiseaseInfo diseaseInfo = new DiseaseInfo(request.getDiseaseName(), request.getDescription(), request.getSteps());
            diseaseInfoRepository.save(diseaseInfo);
        }
    }

//    @Transactional
//    public void getInfos(String diseaseName){
//        DiseaseInfo diseaseInfo = diseaseInfoRepository.findByDiseaseName(diseaseName)
//                .orElseThrow();
//
//    }

}
