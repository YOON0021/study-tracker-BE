package com.studytracker.study_tracker;

import com.studytracker.study_tracker.service.UserService;
import com.studytracker.study_tracker.service.StudyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class StudyTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyTrackerApplication.class, args);
    }

    @Bean
    public CommandLineRunner studyTest(UserService userService, StudyService studyService) {
        return args -> {
            Long userId = userService.register("leader@test.com", "1234");
            Long studyId = studyService.createStudy(
                    userId,
                    "Spring 스터디",
                    "스프링 부트 실습 중심 스터디"
            );

            System.out.println("생성된 스터디 ID = " + studyId);
        };
    }
}
