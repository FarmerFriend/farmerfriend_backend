package nongbuFriend.CV2023_finalProject;

import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/disease")
public class DiseaseController {
    private static Logger logger = LoggerFactory.getLogger(DiseaseController.class);

//    private final DiseaseService diseaseService;

    @PostMapping("")
    public String detectDisease(@RequestParam("image") MultipartFile image) {
        try {
            String fileName = image.getOriginalFilename();
            String filePath = "C:/Users/ganks/OneDrive/사진/plants/" + fileName;
            image.transferTo(new File(filePath));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("image", new FileSystemResource(filePath));

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:5000/predict", requestEntity, String.class);
            return response.getBody();

        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

//    @PostMapping("/create")
//    public String createDiseaseInfos(@RequestBody List<PlantSaveRequest> requestList){
//        diseaseService.createPlants(requestList);
//        return "식물 리스트 DB 저장 완료";
//    }

    @PostMapping("/tts")
    public ResponseEntity<ByteArrayResource> textToSpeech(@RequestBody String text) throws IOException {
        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
            SynthesisInput input = SynthesisInput.newBuilder()
                    .setText(text)
                    .build();

            VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                    .setLanguageCode("ko-KR")
                    .setSsmlGender(SsmlVoiceGender.FEMALE)
                    .build();

            AudioConfig audioConfig = AudioConfig.newBuilder()
                    .setAudioEncoding(AudioEncoding.MP3)
                    .build();

            SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);
            ByteString audioContents = response.getAudioContent();

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=output.mp3");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new ByteArrayResource(audioContents.toByteArray()));
        }
    }
}
