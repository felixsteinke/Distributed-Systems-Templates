FROM node:16.13.0-alpine AS builder
WORKDIR /src/app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build # -> ng build --prod

FROM nginx:1.21-alpine AS runner

COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=builder /src/app/dist/client-ui /usr/share/nginx/html

EXPOSE 4200
