# Используем официальный образ Maven как базовый образ
FROM maven:3.8.3-openjdk-17

# Устанавливаем рабочую директорию в контейнере
WORKDIR /app

# Копируем все файлы проекта в рабочую директорию контейнера
COPY . .

# Запускаем Maven для сборки проекта
RUN mvn clean install -DskipTests

# Запускаем тесты через TestNG
CMD ["mvn", "test", "-Dsurefire.suiteXmlFiles=src/test/java/ru/avito/tests/MainTest.xml"]
