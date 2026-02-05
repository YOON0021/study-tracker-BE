# 🧩 Study Tracker - 스터디 벌금 자동화 시스템

스터디 운영 시 반복되는 **문제 인증 확인**과 **벌금 계산** 과정을 자동화하기 위한 백엔드 서비스입니다. 

## 🛠 기술 스택 (Tech Stack)

- **Language**: Java 17
- **Framework**: Spring Boot 3.2
- **Build Tool**: Gradle
- **Database**: H2 Database
- **ORM**: Spring Data JPA
- **Security**: Spring Security, JWT (JSON Web Token)
- **API Documentation**: Swagger (SpringDoc OpenAPI 3)

## 🏗 프로젝트 구조 및 패키지 (Project Structure)

계층형 아키텍처(Layered Architecture)를 적용하여 유지보수성을 높였습니다.

- **Root Package**: `com.studytracker.study_tracker`
- **Controller**: API 엔드포인트 정의 및 요청 처리
- **Service**: 핵심 비즈니스 로직 및 트랜잭션 관리
- **Repository**: Spring Data JPA를 이용한 데이터베이스 접근
- **Domain**: 엔티티 클래스 및 핵심 도메인 모델
- **DTO**: 계층 간 데이터 전송을 위한 객체 (Record 활용)

## 📝 핵심 데이터 모델 (ERD 요약)

- **User**: 이메일, 암호화된 비밀번호, 역할(USER/LEADER) 정보를 관리합니다.
- **Study**: 스터디 이름 및 리더 ID 정보를 포함합니다.
- **Submission**: 문제 URL, 제출 시간, 성공 여부를 기록하여 문제 제출 현황을 관리합니다.
- **Fine**: 특정 유저에게 부과된 벌금 액수와 사유를 기록합니다.

## 🌟 핵심 구현 기능

1. **JWT 기반 보안 인증 시스템**
   - 로그인 시 사용자 ID가 포함된 JWT 토큰 발급
   - `JwtFilter`를 통한 요청별 유효성 검사 및 인가 처리
   - `BCryptPasswordEncoder`를 사용한 비밀번호 보안 강화

2. **벌금 자동 부과 시스템**
   - 리더가 문제 제출 건에 대해 '실패' 판정을 내릴 경우 자동으로 벌금 데이터 생성
   - `@Transactional`을 사용하여 판정 결과 업데이트와 벌금 생성을 하나의 작업 단위로 묶어 데이터 무결성 보장

3. **문제 제출 관리**
   - 인증된 사용자의 토큰 정보에서 유저 식별자를 추출하여 문제 제출 이력 자동 매핑

## 💡 기술적 고찰

- **단방향 연관관계**: JPA 설계 시 양방향 매핑으로 인한 무한 참조 문제를 방지하기 위해, 엔티티 간 참조를 최소화하고 필요한 경우 ID 참조 방식을 선택했습니다.
- **DB 영속성 확보**: 개발 편의를 위해 H2 메모리 DB를 사용했으나, 데이터 유실 방지를 위해 파일 기반(File-based) 저장 방식으로 설정을 고도화했습니다.
- - **보안 계층 분리**: `SecurityConfig`를 통해 공공 API(Swagger, 로그인 등)와 보호받아야 할 API를 명확히 분리하여 관리했습니다.

## 🚧 트러블 슈팅 (Troubleshooting)

- **404 Not Found 에러**: API 메서드(POST/GET) 불일치 및 주소 오타 문제를 Swagger UI를 통해 디버깅하여 해결했습니다.
- **Git 인증 오류**: GitHub의 패스워드 인증 지원 중단에 따라 **Personal Access Token(PAT)**을 발급받아 원격 저장소 푸시 문제를 해결했습니다.
