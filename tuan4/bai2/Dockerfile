# 1. Base image
FROM python:3.9

# 2. Thư mục làm việc
WORKDIR /app

# 3. Copy file requirements
COPY requirements.txt .

# 4. Cài thư viện
RUN pip install -r requirements.txt

# 5. Copy code
COPY . .

# 6. Mở port 5000
EXPOSE 5000

# 7. Chạy app
CMD ["python", "app.py"]