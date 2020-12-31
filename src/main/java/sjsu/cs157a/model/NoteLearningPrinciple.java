package sjsu.cs157a.model;

public class NoteLearningPrinciple extends LearningPrinciple{
    //status of the learning principle, in reference to note, could be null
    String status;

    public NoteLearningPrinciple(int principle_id, String title, CYCLE cycle, String description, String status) {
        super(principle_id, title, cycle, description);
        this.status = status;
    }

    public NoteLearningPrinciple(LearningPrinciple learningPrinciple, String status) {
        this(learningPrinciple.principle_id,learningPrinciple.title,learningPrinciple.cycle,learningPrinciple.description, status);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
