FROM node:16-alpine3.16

WORKDIR .
COPY . .
RUN npm install
ENTRYPOINT ["npm", "run", "start"]
EXPOSE ${REACT_APP_DOCKER_PORT}
