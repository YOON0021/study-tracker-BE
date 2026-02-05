# 🧩 스터디 벌금 자동화 시스템 (Study Tracker)

코딩 테스트 스터디 운영 시 반복되는 **문제 인증 확인**과 **벌금 계산**을 자동화하기 위한 백엔드 서비스입니다.

## 🛠 Tech Stack
- **Java 17**, **Spring Boot 3.2**
- **Spring Data JPA**, **H2 Database**
- **Spring Security**, **JWT**
- **Swagger (SpringDoc)**

## 🚀 핵심 기능
1. **JWT 인증 시스템**: 로그인 시 토큰 발급 및 필터 기반의 인가 처리
2. **스터디 그룹 관리**: 스터디 생성 및 멤버 초대
3. **과제 제출 및 채점**: 
   - 사용자가 문제 URL 제출
   - 관리자가 성공/실패 판정 (API)
4. **벌금 자동 부과**:
   - 과제 실패 판정 시, **Transactional** 안에서 즉시 벌금 내역(Fine) 생성 및 누적

## ⚡️ 기술적 의사결정 (중요!)
- **단방향 매핑 지향**: JPA의 양방향 매핑이 가져오는 복잡도와 순환 참조 문제를 피하기 위해, 꼭 필요한 곳이 아니면 ID 참조 방식을 사용하여 결합도를 낮췄습니다.
- **Security Filter 커스텀**: `UserDetailsService`를 통해 DB 기반 인증을 수행하고, `OncePerRequestFilter`를 상속받아 중복 검증 없이 매 요청마다 가볍게 토큰을 확인하도록 구현했습니다.
- **DTO 분리**: 엔티티를 직접 노출하지 않고 `Request/Response DTO`를 철저히 분리하여 API 스펙 변경이 DB 설계에 영향을 주지 않도록 했습니다.

## 📝 API 명세
(서버 실행 후) http://localhost:8080/swagger-ui/index.html 접속 시 확인 가능
