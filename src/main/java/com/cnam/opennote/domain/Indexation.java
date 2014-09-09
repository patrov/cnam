/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.opennote.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "indexation")
@XmlRootElement
@NamedQueries({
   //@NamedQuery(name = "Indexation.findAll", query = "SELECT i FROM Indexation i"),
    @NamedQuery(name = "Indexation.findByUid", query = "SELECT i FROM Indexation i WHERE i.uid = :uid"),
    @NamedQuery(name = "Indexation.findByContent", query = "SELECT i FROM Indexation i WHERE i.content = :content"),
    @NamedQuery(name = "Indexation.findByContainerUid", query = "SELECT i FROM Indexation i WHERE i.containerUid = :containerUid"),
    @NamedQuery(name = "Indexation.findByFieldname", query = "SELECT i FROM Indexation i WHERE i.fieldname = :fieldname"),
    @NamedQuery(name = "Indexation.findSubcontents", query = "SELECT i.content FROM Indexation i WHERE i.fieldvalue = :fieldvalue AND i.fieldname ='container' ORDER BY :order")
})
public class Indexation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "uid")
    private Integer uid;
    
    @ManyToOne
    @JoinColumn(name = "content_uid")
    private Content content;
    
    @Size(max = 11)
    @Column(name = "container_uid")
    private String containerUid;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "fieldname")
    private String fieldname;
    
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "fieldvalue")
    private String fieldvalue;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "content_type")
    private String contentType;

    public Indexation() {
    }

    public Indexation(Integer uid) {
        this.uid = uid;
    }

    public Indexation(String fieldname, String fieldvalue, String contentType) {
        this.fieldname = fieldname;
        this.fieldvalue = fieldvalue;
        this.contentType = contentType;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Content getContent() {
        return this.content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public String getContainerUid() {
        return containerUid;
    }

    public void setContainerUid(String containerUid) {
        this.containerUid = containerUid;
    }

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public String getFieldvalue() {
        return fieldvalue;
    }

    public void setFieldvalue(String fieldvalue) {
        this.fieldvalue = fieldvalue;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
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
        if (!(object instanceof Indexation)) {
            return false;
        }
        Indexation other = (Indexation) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cnam.opennote.Indexation[ uid=" + uid + " ]";
    }
    
}
