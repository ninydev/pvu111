FROM node:20
EXPOSE 5173

# Установить mc - средство навигации по контейнеру
RUN apt update
RUN apt install mc -y

# Внутри контейнера выберем рабочую папку - где будет распологаться мой код
WORKDIR /app

# Скопируем код из папки src в контейнер
COPY ./src /app

# Установим зависимости
RUN npm install

# Запустим код - выполнив команду из packaje.josn
CMD ["npm", "dev"]