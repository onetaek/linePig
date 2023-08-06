package com.shop.linepig.domain.common;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)//이게 있어야 Auditing을 함
@MappedSuperclass
public class BaseEntity {

    private Boolean deleted = Boolean.FALSE;//삭제 여부

    private LocalDateTime deletedOn = LocalDateTime.now();

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)//웹화면에서 파싱을 위한 파싱룰 지정
    @CreatedDate
    @Column(updatable = false)
    protected LocalDateTime createdAt; // 생성일시

    @Column(nullable = true ,updatable = false, length = 100)
    protected Long createdBy; // 생성자

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(nullable = false)
    protected LocalDateTime modifiedAt; // 수정일시

    @Column(nullable = true, length = 100)
    protected Long modifiedBy; // 수정자

    public String getCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초");
        return this.createdAt.format(formatter);
    }

}
