package com.shop.linepig.domain.board.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.linepig.domain.board.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.shop.linepig.domain.board.entity.QBoard.board;

@Repository
@RequiredArgsConstructor
public class BoardQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Page<Board> findAllWithPagination(Pageable pageable, BooleanExpression... expressions) {
        QueryResults<Board> results = jpaQueryFactory
                .selectFrom(board)
                .where(expressions)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(board.id.desc())
                .fetchResults();
        List<Board> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    public List<Board> findAll(BooleanExpression... expressions) {
        return jpaQueryFactory.selectFrom(board)
                .where(expressions)
                .fetch();
    }

}
