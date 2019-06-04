package com.cecilia.blog.entity;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.annotations.GenerationTime;

@Embeddable
@Data
//@Table(name="tbl_article_coverpic")
public class Coverpic {

	@NotNull
	@Column(nullable = false)
	private String coverpicURL;


}
