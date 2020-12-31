package sjsu.cs157a.model;

import java.io.Serializable;
import java.util.Objects;

public class LearningPrinciple implements Serializable {

    int principle_id;
    //title of the learning principle, should be equal to method in the database
    String title;
    //the stage of the learning principle
    CYCLE cycle;

    String description;


    public LearningPrinciple(int principle_id, String title, CYCLE cycle, String description) {
        this.principle_id = principle_id;
        this.title = title;
        this.cycle = cycle;
        this.description = description;
    }

    public int getPrinciple_id() {
        return principle_id;
    }

    public void setPrinciple_id(int principle_id) {
        this.principle_id = principle_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CYCLE getCycle() {
        return cycle;
    }

    public void setCycle(CYCLE cycle) {
        this.cycle = cycle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LearningPrinciple)) return false;
        LearningPrinciple that = (LearningPrinciple) o;
        return principle_id == that.principle_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(principle_id);
    }


    public enum CYCLE{
        collect, process, reflect
    }

}
