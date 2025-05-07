перейти из командной строки в папку web-rise-test
запустить docker-compose
приложение будет работать на порте 8082 

POST/api/v1/users создание пользователя.
GET/api/v1/users/{id} получение информации о пользователе.
PUT/api/v1/users/{id} изменение пользователя.
DELETE/api/v1/users/{id} удаление пользователя.

GET/api/v1/users/{id}/subscriptions получить подписки пользователя.
POST/api/v1/users/{id}/subscriptions добавить подписку.
DELETE/api/v1/users/{id}/subscriptions/{sub_id} удалить подписку.
GET/api/v1/subscriptions/top получить топ 3 подписки.

или зайти в постман и импортировать документацию с web-rise-test-v0.0.1.yaml выбрать OpenApi

