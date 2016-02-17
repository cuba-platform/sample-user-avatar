package com.company.sample.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.cuba.core.entity.StandardEntity;

/**
 * @author petunin
 */
@Table(name = "UA_TASK")
@Entity(name = "ua$Task")
public class Task extends StandardEntity {
    private static final long serialVersionUID = -2816711442077082465L;

    @Column(name = "NAME", nullable = false)
    protected String name;

    @Column(name = "DESCRIPTION")
    protected String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ASSIGNEE_ID")
    protected UserExt assignee;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setAssignee(UserExt assignee) {
        this.assignee = assignee;
    }

    public UserExt getAssignee() {
        return assignee;
    }


}