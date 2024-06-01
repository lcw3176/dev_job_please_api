package com.comet.devjobplz.domain.joblink;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(indexes = {@Index(name = "jobLink_index",columnList = "jobInfoId")})
public class JobLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long jobInfoId;

    private String source;

    @Enumerated(EnumType.STRING)
    private SourceType sourceType;


}
