## Technical content

* Java: OpenJDK 17.0.5 LTS
* NestNG: 7.8.0
* Selenium WebDriver: 4.9.1
* Maven: 3.9.0
* Allure TestNG Version: 2.22.0

## Running tests

### Execution in GitHub Actions 

1. **Запуск**: запускается по расписанию каждый день в 00:00 по МСК на [странице](https://github.com/Doma-Sapiens/test-web/actions/workflows/main.yml)
2. **Анализ результата**: https://doma-sapiens.github.io/test-web/

### Execution from command line

1. **Клонирование репозитория**: Клонировать репозиторий с GitHub командой `git clone https://github.com/Doma-Sapiens/test-web.git`.
2. **Переход в каталог проекта**: Перейти в каталог проекта: `cd [Путь к клонированному проекту]`.
3. **Установка зависимостей**: Запустить `mvn clean install` для установки всех зависимостей из файла `pom.xml`.
4. **Запуск тестов через XML**: Выполнить команду `mvn test -Dsurefire.suiteXmlFiles=src/test/java/ru/avito/tests/MainTest.xml` для запуска тестов, указанных в XML файле.
5. **Генерация Allure отчета**: Выполните команду `mvn allure:report` для генерации отчета.
6. **Просмотр отчета**: /test-web/target/site/allure-maven-plugin/index.html

### Execution in IDEA

1. **Клонирование репозитория:** Клонировать репозиторий с GitHub по url https://github.com/Doma-Sapiens/test-web.git
2. **Запуск тестов**:
   * Через контекстное меню: Найти и нажать правую кнопку мыши, выбрать"Run 'MainTest'".
   * Через меню "Run": Открыть файл MainTest.xml, в меню нажать кнопку "Run".
   * С помощью комбинации клавиш: Открыть файл **`MainTest.xml`** и нажать **`Shift + F10`** (для IntelliJ IDEA).
3. **Просмотр отчета**: /test-web/target/site/allure-maven-plugin/index.html

## Links

1. Тест-план: https://github.com/Doma-Sapiens/test-web/blob/main/Test-plan.md
2. Тест-кейсы: https://github.com/Doma-Sapiens/test-web/blob/main/TestCases.md
3. Результаты тестов: https://doma-sapiens.github.io/test-web/

