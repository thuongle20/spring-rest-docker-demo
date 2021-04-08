package com.spys.ms.sample.model;

import com.google.gson.annotations.Expose;
import com.spys.ms.sample.config.SystemDefaultProperties;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 */
@Data
@Entity
@Table(name = "student")
public class Student
{

    @Id
    @Basic(optional = false)
    @Expose
    @NotNull
    @Column(name = "sid", nullable = false)
    private Integer sid;

    @Expose
    @Size(max = 20)
    @Column(name = "name", length = 20)
    private String name;

    @OneToMany(mappedBy = "student")
    private List<Registration> registrationList;

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (sid != null ? sid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof Student))
        {
            return false;
        }
        Student other = (Student) object;
        return !((this.sid == null && other.sid != null) || (this.sid != null && !this.sid.equals(other.sid)));
    }
}
