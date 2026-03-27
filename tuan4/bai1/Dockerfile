# 1. Base image
FROM node:18

# 2. Tạo thư mục làm việc
WORKDIR /app

# 3. Copy package.json vào container
COPY package.json ./

# 4. Cài dependencies (nếu có)
RUN npm install

# 5. Copy toàn bộ code vào container
COPY . .

# 6. Mở port 3000
EXPOSE 3000

# 7. Chạy ứng dụng
CMD ["npm", "start"]