package com.comet.devjobplz.domain.jobs.job;

import com.comet.devjobplz.infra.db.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(indexes = {@Index(name = "job_companyId_index", columnList = "companyId")})
public class Job extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long companyId;

    private String title;

    private Integer minCareer;

    private Integer maxCareer;

    private LocalDateTime dueDate;

    private String source;

    @Enumerated(EnumType.STRING)
    private SourceType sourceType;

}
