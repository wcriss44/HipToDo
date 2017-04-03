package com.theironyard.novauc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Owner.
 */
@Entity
@Table(name = "owner")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Owner implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "service_branch", nullable = false)
    private String serviceBranch;

    @NotNull
    @Column(name = "company_name", nullable = false)
    private String companyName;

    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ToDo> toDos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Owner name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServiceBranch() {
        return serviceBranch;
    }

    public Owner serviceBranch(String serviceBranch) {
        this.serviceBranch = serviceBranch;
        return this;
    }

    public void setServiceBranch(String serviceBranch) {
        this.serviceBranch = serviceBranch;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Owner companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Set<ToDo> getToDos() {
        return toDos;
    }

    public Owner toDos(Set<ToDo> toDos) {
        this.toDos = toDos;
        return this;
    }

    public Owner addToDo(ToDo toDo) {
        this.toDos.add(toDo);
        toDo.setOwner(this);
        return this;
    }

    public Owner removeToDo(ToDo toDo) {
        this.toDos.remove(toDo);
        toDo.setOwner(null);
        return this;
    }

    public void setToDos(Set<ToDo> toDos) {
        this.toDos = toDos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Owner owner = (Owner) o;
        if (owner.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, owner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Owner{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", serviceBranch='" + serviceBranch + "'" +
            ", companyName='" + companyName + "'" +
            '}';
    }
}
