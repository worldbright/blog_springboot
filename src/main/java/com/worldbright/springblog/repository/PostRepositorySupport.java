package com.worldbright.springblog.repository;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.worldbright.springblog.domain.Post;
import com.worldbright.springblog.domain.QMember;
import com.worldbright.springblog.domain.QPost;
import com.worldbright.springblog.domain.QPostTag;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepositorySupport extends QuerydslRepositorySupport {
    public PostRepositorySupport() {
        super(Post.class);
    }

    public List<Post> findBySearchKeyword(String searchKeyword) {
        QPost post = QPost.post;
        return from(post)
                .leftJoin(post.member, QMember.member).fetchJoin()
                .leftJoin(post.postTags, QPostTag.postTag)
                .where(post.title.contains(searchKeyword)
                        .or(post.content.contains(searchKeyword))
                        .or(post.member.name.contains(searchKeyword))
                        .or(QPostTag.postTag.name.contains(searchKeyword))
                )
                .distinct()
                .fetch();
    }
}
