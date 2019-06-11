package com.cecilia.blog.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenerationTime;

@Data
@Entity
@Table(name="tbl_tag")
public class Tag {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String tagName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	@org.hibernate.annotations.CreationTimestamp()
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false)
	@org.hibernate.annotations.UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date modifiedAt;

	@JsonIgnore
	@ManyToMany()
	@JoinTable (
			name = "ARTICLE_TAG",
			joinColumns = @JoinColumn(name = "TAG_ID"),
			inverseJoinColumns = @JoinColumn(name = "ARTICLE_ID")
	)
	private Set<Article> articles = new HashSet<>();

	public boolean equals(Object o) {
		if(o != null && o instanceof Tag) {
			Tag that = (Tag) o;
			return this.tagName.equals(that.tagName);
		}
		return false;
	}

	public int hashCode() {
		return this.tagName.hashCode();
	}

}
