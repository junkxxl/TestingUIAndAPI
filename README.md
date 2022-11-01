# Тестирование

---
В данном проекте происходит тестирование UI и API

## Описание
* Фреймворк автотестов построен поверх Spring Boot, с использованием набора технологий для автоматизированного тестирования
````
- Spring Boot - компоненты из коробки
- Spring Data JDBC - компоненты для взаимодействия с базами данных
- Rest-assured - компоненты для тестирования Rest API
- Selenide - компоненты для тестирования UI
````

## Архитектура
* src/main/java - основные компоненты и шаги
````
- me/junkxxl - базовый пакет
- me/junkxxl/testing/testapi - компоненты для взаимодействия с Rest сервисами
- me/junkxxl/testing/trelloandregard - модели в стиле PageObject для работы с Web приложениями
- me/junkxxl/testing/model - модели тестовых данных
````
* src/main/resources - здесь хранятся ресурсы, такие как:
````
- allure.properties
````
* src/test/resources - место для тестовых ресурсов
````
- application.properties - здесь храняться ресурсы Spring Boot
````
## Требования для запуска
````
- Java 11
- Добавить в переменную среду данные: "DB_USERNAME" , "DB_PASSWORD" , "Login" , "Email"
````
---
### Сборка и запуск
Для запуска всех тестов потребуется запустить:
````
mvn clean test
````
Для запуска отчета о пройденных тестах, нужно запустить
````
mvn allure:serve
````
Для запуска тестов UI нужно запустить
````
mvn -Dtest=TrelloAndRegardTest test
````
Для запуска тестов API нужно запустить
````
mvn -Dtest=TestApi test
