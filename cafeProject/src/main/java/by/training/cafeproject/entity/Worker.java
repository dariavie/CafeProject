package by.training.cafeproject.entity;

import java.sql.Date;
import java.util.Objects;

public class Worker extends Person {
    private Date startOfWork;
    private Date endOfWork;
    private String specialization;

    public void setStartOfWork(Date startOfWork) {
        this.startOfWork = startOfWork;
    }

    public void setEndOfWork(Date endOfWork) {
        this.endOfWork = endOfWork;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Date getStartOfWork() {
        return startOfWork;
    }

    public Date getEndOfWork() {
        return endOfWork;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Worker worker = (Worker) o;
        return Objects.equals(startOfWork, worker.startOfWork) && Objects.equals(endOfWork, worker.endOfWork) && Objects.equals(specialization, worker.specialization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), startOfWork, endOfWork, specialization);
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id='" + getId() + '\'' +
                ", startOfWork=" + startOfWork +
                ", endOfWork=" + endOfWork +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
