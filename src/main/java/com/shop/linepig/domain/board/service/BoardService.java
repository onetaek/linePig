package com.shop.linepig.domain.board.service;

import com.shop.linepig.domain.admin.exception.AdminNotFoundException;
import com.shop.linepig.domain.admin.repository.AdminRepository;
import com.shop.linepig.domain.board.dto.MagazineCreateRequest;
import com.shop.linepig.domain.board.dto.request.BoardCreateByAdminRequest;
import com.shop.linepig.domain.board.dto.request.BoardCreateByUserRequest;
import com.shop.linepig.domain.board.dto.request.BoardUpdateByAdminRequest;
import com.shop.linepig.domain.board.dto.request.BoardUpdateByUserRequest;
import com.shop.linepig.domain.board.dto.response.BoardCategoryResponse;
import com.shop.linepig.domain.board.dto.response.BoardResponse;
import com.shop.linepig.domain.board.dto.response.BoardStatusResponse;
import com.shop.linepig.domain.board.dto.response.BoardTypeResponse;
import com.shop.linepig.domain.board.entity.Board;
import com.shop.linepig.domain.board.entity.enumeration.BoardCategory;
import com.shop.linepig.domain.board.entity.enumeration.BoardStatus;
import com.shop.linepig.domain.board.entity.enumeration.BoardType;
import com.shop.linepig.domain.board.exception.BoardNotFoundException;
import com.shop.linepig.domain.board.repository.BoardQueryRepository;
import com.shop.linepig.domain.board.repository.BoardRepository;
import com.shop.linepig.domain.board.repository.expression.BoardBooleanExpression;
import com.shop.linepig.domain.common.embeddable.UploadFile;
import com.shop.linepig.domain.upload.UploadFirebaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardQueryRepository boardQueryRepository;
    private final AdminRepository adminRepository;
    private final UploadFirebaseService uploadFirebaseService;

    public Page<BoardResponse> findAllByCategoryAndIsTopWithPagination(Pageable pageable, String category, Boolean isTop) {
        pageable = PageRequest.of(pageable.getPageNumber(),7);
        return boardQueryRepository.findAllWithPagination(
                pageable,
                BoardBooleanExpression.eqCategory(category == null ? null : BoardCategory.fromCode(category)),
                BoardBooleanExpression.eqIsTop(isTop))
                .map(BoardResponse::fromEntity);
    }

    public Page<BoardResponse> findMagazineWidthPagination(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), 7);
        return boardQueryRepository.findAllWithPagination(pageable,
                        BoardBooleanExpression.eqCategory(BoardCategory.MAGAZINE))
                .map(BoardResponse::fromEntity);
    }

    public List<BoardResponse> findAllByCategoryAndIsTop(String category, Boolean isTop) {
        return boardQueryRepository.findAll(
                        BoardBooleanExpression.eqCategory(category == null ? null : BoardCategory.fromCode(category)),
                        BoardBooleanExpression.eqIsTop(isTop))
                .stream()
                .map(BoardResponse::fromEntity)
                .collect(Collectors.toList());
    }

    //메거진 메인페이지 조회용 쿼리
    public List<BoardResponse> findAllByCategoryWithLimit(BoardCategory category, long limit) {
        return boardQueryRepository.findAllWithLimit(
                limit, BoardBooleanExpression.eqCategory(category))
                .stream()
                .map(BoardResponse::fromEntity)
                .collect(Collectors.toList());
    }

    //메거진 관리자 페이지 조회용 쿼리
    public List<BoardResponse> findAllByCategory(BoardCategory category) {
        return boardQueryRepository.findAll(BoardBooleanExpression.eqCategory(category))
                .stream()
                .map(BoardResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public BoardResponse findById(Long id) {
        Board findBoard = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(""));
        return BoardResponse.fromEntity(findBoard);
    }

    public void createByAdmin(BoardCreateByAdminRequest request, Long adminId) {
        Board board = BoardCreateByAdminRequest.toEntity(request, adminId);
        boardRepository.save(board);
    }

    //메거진글 작성 메서드
    public void createMagazine(MagazineCreateRequest request, Long adminId) {
        UploadFile uploadFile = uploadFirebaseService.uploadBase64EncodedFile(request.getBoardImage());
        boardRepository.save(MagazineCreateRequest.toEntity(request,uploadFile,adminId));
    }

    public void createByUser(BoardCreateByUserRequest request, Long memberId) {
        Board board = BoardCreateByUserRequest.toEntity(request, memberId);
        boardRepository.save(board);
    }

    public void updateByAdmin(BoardUpdateByAdminRequest request, Long id) {
        Board findBoard = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        findBoard.updateByAdmin(request.getTitleKo(), request.getTitleEn(), request.getContentKo(), request.getContentEn(),
                request.getIsTop(), BoardCategory.fromCode(request.getCategory()), BoardStatus.fromCode(request.getStatus()));
    }

    public void updateByUser(BoardUpdateByUserRequest request, Long id) {
        Board findBoard = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        findBoard.updateByUser(request.getContent());
    }

    public void deleteByAdmin(Long id, Long adminId) {
        Board findBoard = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        adminRepository.findById(adminId).orElseThrow(AdminNotFoundException::new);
        boardRepository.delete(findBoard);
    }

    public List<BoardStatusResponse> getStatuses() {
        return Stream.of(BoardStatus.values())
                .sorted(Comparator.comparing(
                        BoardStatus::getSequence,
                        Comparator.naturalOrder()))
                .map(BoardStatusResponse::fromEnum)
                .collect(Collectors.toList());
    }

    public List<BoardCategoryResponse> getCategories() {
        return Stream.of(BoardCategory.values())
                .sorted(Comparator.comparing(
                        BoardCategory::getSequence,
                        Comparator.naturalOrder()))
                .map(BoardCategoryResponse::fromEnum)
                .collect(Collectors.toList());
    }

    public BoardCategoryResponse getCategory(String category) {
        return BoardCategoryResponse.fromEnum(BoardCategory.fromCode(category));
    }

    public List<BoardTypeResponse> getTypes() {
        return Stream.of(BoardType.values())
                .sorted(Comparator.comparing(
                        BoardType::getSequence,
                        Comparator.naturalOrder()))
                .map(BoardTypeResponse::fromEnum)
                .collect(Collectors.toList());
    }
}
