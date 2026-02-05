package com.studytracker.study_tracker.repository;

import com.studytracker.study_tracker.domain.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    // 나중에 특정 스터디의 제출 목록을 가져올 때 사용
    List<Submission> findAllByStudyId(Long studyId);
}