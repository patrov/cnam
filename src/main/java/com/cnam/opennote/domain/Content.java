/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.opennote.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "content")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Content.findAll", query = "SELECT c FROM Content c"),
    @NamedQuery(name = "Content.findByUid", query = "SELECT c FROM Content c WHERE c.uid = :uid"),
    @NamedQuery(name = "Content.findByAppKey", query = "SELECT c FROM Content c WHERE c.appKey = :appKey"),
    @NamedQuery(name = "Content.findByModel", query = "SELECT c FROM Content c WHERE c.model = :model"),
    @NamedQuery(name = "Content.findByCreatedAt", query = "SELECT c FROM Content c WHERE c.createdAt = :createdAt"),
    @NamedQuery(name = "Content.findByState", query = "SELECT c FROM Content c WHERE c.state = :state"),
    @NamedQuery(name = "Content.findByOwnerUid", query = "SELECT c FROM Content c WHERE c.ownerUid = :ownerUid"),
    @NamedQuery(name = "Content.findByUpdatedAt", query = "SELECT c FROM Content c WHERE c.updatedAt = :updatedAt")})
public class Content implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "uid")
    private Integer uid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "appKey")
    private String appKey;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "model")
    private String model;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "data")
    private String data;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "state")
    private int state;
    @Basic(optional = false)
    @NotNull
    @Column(name = "owner_uid")
    private int ownerUid;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Content() {
    }

    public Content(Integer uid) {
        this.uid = uid;
    }

    public Content(Integer uid, String appKey, String model, String data, Date createdAt, int state, int ownerUid) {
        this.uid = uid;
        this.appKey = appKey;
        this.model = model;
        this.data = data;
        this.createdAt = createdAt;
        this.state = state;
        this.ownerUid = ownerUid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getOwnerUid() {
        return ownerUid;
    }

    public void setOwnerUid(int ownerUid) {
        this.ownerUid = ownerUid;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uid != null ? uid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Content)) {
            return false;
        }
        Content other = (Content) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cnam.opennote.Content[ uid=" + uid + " ]";
    }
    
}
