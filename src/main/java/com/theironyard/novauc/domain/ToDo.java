package com.theironyard.novauc.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A ToDo.
 */
@Entity
@Table(name = "to_do")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ToDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "to_do_item", nullable = false)
    private String toDoItem;

    @NotNull
    @Column(name = "date_posted", nullable = false)
    private LocalDate datePosted;

    @NotNull
    @Column(name = "is_complete", nullable = false)
    private Boolean isComplete;

    @ManyToOne
    private Owner owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToDoItem() {
        return toDoItem;
    }

    public ToDo toDoItem(String toDoItem) {
        this.toDoItem = toDoItem;
        return this;
    }

    public void setToDoItem(String toDoItem) {
        this.toDoItem = toDoItem;
    }

    public LocalDate getDatePosted() {
        return datePosted;
    }

    public ToDo datePosted(LocalDate datePosted) {
        this.datePosted = datePosted;
        return this;
    }

    public void setDatePosted(LocalDate datePosted) {
        this.datePosted = datePosted;
    }

    public Boolean isIsComplete() {
        return isComplete;
    }

    public ToDo isComplete(Boolean isComplete) {
        this.isComplete = isComplete;
        return this;
    }

    public void setIsComplete(Boolean isComplete) {
        this.isComplete = isComplete;
    }

    public Owner getOwner() {
        return owner;
    }

    public ToDo owner(Owner owner) {
        this.owner = owner;
        return this;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ToDo toDo = (ToDo) o;
        if (toDo.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, toDo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ToDo{" +
            "id=" + id +
            ", toDoItem='" + toDoItem + "'" +
            ", datePosted='" + datePosted + "'" +
            ", isComplete='" + isComplete + "'" +
            '}';
    }
}
