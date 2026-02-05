package com.studytracker.study_tracker.service;

import com.studytracker.study_tracker.domain.Study;
import com.studytracker.study_tracker.repository.StudyRepository;
import com.studytracker.study_tracker.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class StudyService {

    private final StudyRepository studyRepository;
    private final UserService userService;

    public Long createStudy(Long leaderId, String title, String description) {
        User leader = userService.findById(leaderId);

        Study study = new Study(title, description, leader);
        Study savedStudy = studyRepository.save(study);

        return savedStudy.getId();
    }
}
