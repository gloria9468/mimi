FROM openjdk:17

# 저장 디렉토리 생성
RUN mkdir -p /mimi-file-store/prod

# JAR 파일 복사
COPY mimi_spring.jar /app.jar

# 환경 변수 설정 (필요한 경우)
ENV SPRING_PROFILES_ACTIVE=prod

# 포트 노출
EXPOSE 8080

# JAR 파일 실행
CMD ["java", "-jar", "/app.jar"]
