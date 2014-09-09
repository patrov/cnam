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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.json.simple.JSONObject;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "book")
@NamedQueries({
    @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),
    @NamedQuery(name = "Book.findByUid", query = "SELECT b FROM Book b WHERE b.uid = :uid"),
    @NamedQuery(name = "Book.findByTitle", query = "SELECT b FROM Book b WHERE b.title = :title"),
    @NamedQuery(name = "Book.findBySubtitle", query = "SELECT b FROM Book b WHERE b.subtitle = :subtitle"),
    @NamedQuery(name = "Book.findByAuthor", query = "SELECT b FROM Book b WHERE b.author = :author"),
    @NamedQuery(name = "Book.findByPublisher", query = "SELECT b FROM Book b WHERE b.publisher = :publisher"),
    @NamedQuery(name = "Book.findByPubyear", query = "SELECT b FROM Book b WHERE b.pubyear = :pubyear"),
    @NamedQuery(name = "Book.findByPubplace", query = "SELECT b FROM Book b WHERE b.pubplace = :pubplace"),
    @NamedQuery(name = "Book.findByPagecount", query = "SELECT b FROM Book b WHERE b.pagecount = :pagecount"),
    @NamedQuery(name = "Book.findByIsbn", query = "SELECT b FROM Book b WHERE b.isbn = :isbn")})
@XmlRootElement
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "uid")
    private Integer uid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "subtitle")
    private String subtitle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "author")
    private String author;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "publisher")
    private String publisher;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pubyear")
    private String pubyear;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "pubplace")
    private String pubplace;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pagecount")
    private int pagecount;
    @Basic(optional = false)
    @NotNull
    @OneToOne
    @JoinColumn(name = "content_uid")
    private Content content;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isbn")
    private int isbn;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "keywords")
    private String keywords;

    public Book() {
    }

    public Book(int uid) {
        this.uid = uid;
    }

    public Book(int uid, String title, String subtitle, String author, String publisher, String pubyear, String pubplace, int pagecount, Content content, int isbn, String keywords) {
        this.uid = uid;
        this.title = title;
        this.subtitle = subtitle;
        this.author = author;
        this.publisher = publisher;
        this.pubyear = pubyear;
        this.pubplace = pubplace;
        this.pagecount = pagecount;
        this.content = content;
        this.isbn = isbn;
        this.keywords = keywords;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPubyear() {
        return pubyear;
    }

    public void setPubyear(String pubyear) {
        this.pubyear = pubyear;
    }

    public String getPubplace() {
        return pubplace;
    }

    public void setPubplace(String pubplace) {
        this.pubplace = pubplace;
    }

    public int getPagecount() {
        return pagecount;
    }

    public void setPagecount(int pagecount) {
        this.pagecount = pagecount;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getKeywords() {
        return keywords;
    }

    public void hidrateFromJSON(JSONObject jsonData) {
        /*handle book Fields here*/
        this.setAuthor((String) jsonData.get("author"));
        this.setKeywords((String) jsonData.get("tags"));
        this.setSubtitle((String) jsonData.get("subtitle"));
        this.setTitle((String) jsonData.get("title"));
        this.setPubyear((String) jsonData.get("year"));
        this.setPublisher((String) jsonData.get("publisher"));
        this.setPubplace((String) jsonData.get("place"));
        this.content.setData(jsonData.toJSONString());
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
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
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.uid == null && other.uid != null) || (this.uid != null && !this.uid.equals(other.uid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cnam.opennote.domain.Book[ uid=" + uid + " ]";
    }
}
