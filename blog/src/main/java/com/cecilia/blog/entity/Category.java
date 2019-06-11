package com.cecilia.blog.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@Entity
@Table(name="tbl_category")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(nullable = false, columnDefinition = "varchar(4) default '1'")
	private String isValid = "1";

	@NotNull
	private String categoryName;

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
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	Collection<Article> articles = new ArrayList<>();

	
}
