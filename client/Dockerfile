FROM node:16-alpine3.16

WORKDIR .
COPY . .
RUN npm install
ENTRYPOINT ["npm", "run", "start"]
EXPOSE 3000
