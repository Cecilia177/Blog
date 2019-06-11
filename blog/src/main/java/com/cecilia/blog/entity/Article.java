package com.cecilia.blog.entity;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.GenerationTime;


/**
 * Article实体类
 * 
 * @author CeciliaYe
 * @since 2019/5/21 10:40
 *
 */

@Data
@Entity
@Table(name="tbl_article")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler","fieldHandler"})
public class Article {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(nullable = false, columnDefinition = "varchar(4) default '1'")
	private String isValid = "1";

	@NotNull
	@Column(nullable = false)
	private String title;
	
	@NotNull
	@Column(nullable = false)
	private String summary;
	
	@NotNull
	@Column(nullable = false)
	private String content;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "varchar(4) default '0'")
	private String isTop = "0";
	
	@NotNull
	@Column(nullable = false, columnDefinition = "INT default 0")
	private int views;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	@org.hibernate.annotations.CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false)
	@org.hibernate.annotations.UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date modifiedAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Category category;

	private Coverpic coverpic;

	@ManyToMany
	@JoinTable (
			name = "ARTICLE_TAG",
			joinColumns = @JoinColumn(name = "ARTICLE_ID"),
			inverseJoinColumns = @JoinColumn(name = "TAG_ID")
	)
	private Set<Tag> tags = new HashSet<>();



	@OneToMany(mappedBy = "article",
			fetch = FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private Collection<Comment> comments = new ArrayList<>();


}
