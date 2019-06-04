package com.cecilia.blog.entity;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.annotations.GenerationTime;

@Embeddable
@Data
@Table(name="tbl_article_coverpic")
public class Coverpic {

	@NotNull
	@Column(nullable = false)
	private String coverpicURL;


	@Temporal(TemporalType.DATE)
	@Column(updatable = false)
	private Date createdAt;

	@Temporal(TemporalType.DATE)
	@Column(insertable = false, updatable = false)
	@org.hibernate.annotations.Generated(
			GenerationTime.ALWAYS
	)
	private Date modifiedAt;

//	@PrePersist
//	void createdAt() {
//		this.createdAt = new Date();
//	}
}
