package nongbuFriend.CV2023_finalProject;


public class DiseaseInfoSaveRequest {
    private String diseaseName;
    private String description;
    private String steps;

    public DiseaseInfoSaveRequest() {}

    public DiseaseInfoSaveRequest(String diseaseName, String description, String steps) {
        this.diseaseName = diseaseName;
        this.description = description;
        this.steps = steps;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public String getDescription() {
        return description;
    }

    public String getSteps() {
        return steps;
    }
}
