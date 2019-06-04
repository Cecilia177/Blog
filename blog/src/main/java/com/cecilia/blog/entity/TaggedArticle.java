package com.cecilia.blog.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Discription: This is an intermediate entity for a many-to-many association between Tag and Article
 */

@Data
@Entity
@Table(name = "TAG_ARTICLE")
@org.hibernate.annotations.Immutable
public class TaggedArticle {

    @Data
    @Embeddable
    public static class Id implements Serializable {

        @Column(name = "TAG_ID")
        private Long tagId;

        @Column(name = "ARTICLE_ID")
        private Long articleId;

        public boolean equals(Object o) {
            if (o != null && o instanceof Id) {
                Id that = (Id) o;
                return this.tagId.equals(that.tagId)
                        && this.articleId.equals(that.articleId);
            }
            return false;
        }

        public int hashCode() {
            return tagId.hashCode() + articleId.hashCode();
        }
    }

    @EmbeddedId
    private Id id = new Id();

    @ManyToOne
    @JoinColumn(
            name = "TAG_ID",
            insertable = false,
            updatable = false)
    private Tag tag;

    @ManyToOne
    @JoinColumn(
            name = "ARTICLE_ID",
            insertable = false,
            updatable = false)
    private Article article;

    public TaggedArticle(Tag tag, Article article) {
        this.tag = tag;
        this.article = article;

        this.id.tagId = tag.getId();
        this.id.articleId = article.getId();

        tag.getTaggedArticles().add(this);
        article.getTaggedArticles().add(this);
    }

}
