package com.shop.linepig.domain.board.repository;


import com.shop.linepig.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
