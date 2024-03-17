FROM node:20
EXPOSE 3003

# Установить mc - средство навигации по контейнеру
RUN apt update
RUN apt install mc -y

# Внутри контейнера выберем рабочую папку - где будет распологаться мой код
WORKDIR /app

# Скопируем код из папки src в контейнер
COPY ./src /app

# Удаляем установленные снаружи зависимости
RUN rm -rf /app/node_modules


# Установим зависимости
RUN npm install

# Запустим код - выполнив команду из packaje.josn
# Перенесем процесс запуска контейнера
# 1 Вариант - в файл docker-compose
# CMD ["npm", "run", "start"]