   FROM openjdk:17-jdk-slim AS build

   # Устанавливаем необходимые утилиты
   RUN apt-get update && apt-get install -y --no-install-recommends findutils && rm -rf /var/lib/apt/lists/*

   WORKDIR /app

   # Копируем файлы Gradle
   COPY gradle/ gradle/
   COPY gradlew gradlew
   COPY build.gradle.kts build.gradle.kts
   COPY settings.gradle.kts settings.gradle.kts

   # Даем права на выполнение скрипта gradlew
   RUN chmod +x gradlew

   # Копируем исходный код
   COPY src/ src/

   # Собираем проект
   RUN ./gradlew build --no-daemon

   # Используем другой образ OpenJDK 17 для финального контейнера
   FROM openjdk:17-jdk-slim

   WORKDIR /app

   # Копируем собранный .jar файл из предыдущего этапа
   COPY --from=build /app/build/libs/*.jar app.jar

   # Указываем команду для запуска приложения
   CMD ["java", "-jar", "app.jar"]