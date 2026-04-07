# Hướng dẫn cấu hình Spring Security và CORS cho mạng LAN

## 📋 Tổng quan
Tài liệu này hướng dẫn cách cấu hình Spring Boot Security và CORS để cho phép truyền dữ liệu qua mạng LAN.

## 🔧 Các bước đã cấu hình

### 1. Thêm Spring Security Dependency

Thêm vào file `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

Sau đó chạy lệnh:
```bash
mvn clean install
```

### 2. File SecurityConfig.java

File này đã được tạo tại: `src/main/java/com/se/orderservice/config/SecurityConfig.java`

**Các tính năng:**
- ✅ Tắt CSRF protection (phù hợp cho REST API)
- ✅ Cấu hình CORS cho phép truy cập từ mạng LAN
- ✅ Stateless session (không lưu session)
- ✅ Cho phép tất cả requests (development mode)

### 3. Cấu hình application.properties

```properties
# Lắng nghe trên tất cả network interfaces
server.address=0.0.0.0
server.port=8080
```

## 🌐 Cách sử dụng trong mạng LAN

### Bước 1: Kiểm tra địa chỉ IP của máy chủ

**Windows:**
```cmd
ipconfig
```

**Linux/Mac:**
```bash
ifconfig
# hoặc
ip addr show
```

Tìm địa chỉ IP của interface mạng (ví dụ: `192.168.1.100`)

### Bước 2: Chạy ứng dụng

```bash
mvn spring-boot:run
```

Hoặc sau khi build:
```bash
mvn clean package
java -jar target/OrderService-0.0.1-SNAPSHOT.jar
```

### Bước 3: Kiểm tra kết nối

Từ máy khác trong mạng LAN, truy cập:
```
http://192.168.1.100:8080/orders
```

## 🔒 Tùy chỉnh Security

### Option 1: Cho phép tất cả (Development - Hiện tại)

```java
.authorizeHttpRequests(auth -> auth
    .anyRequest().permitAll()
)
```

### Option 2: Yêu cầu authentication cho một số endpoints

```java
.authorizeHttpRequests(auth -> auth
    .requestMatchers("/orders/**").authenticated()
    .anyRequest().permitAll()
)
```

### Option 3: Cấu hình Basic Authentication

Thêm vào `application.properties`:
```properties
spring.security.user.name=admin
spring.security.user.password=admin123
```

Và cập nhật SecurityConfig:
```java
.authorizeHttpRequests(auth -> auth
    .anyRequest().authenticated()
)
.httpBasic(Customizer.withDefaults())
```

## 🌍 Tùy chỉnh CORS

### Cho phép tất cả origins (Development)

```java
configuration.setAllowedOriginPatterns(Arrays.asList("*"));
```

### Chỉ cho phép các địa chỉ LAN cụ thể (Production)

```java
configuration.setAllowedOrigins(Arrays.asList(
    "http://192.168.1.100:3000",
    "http://192.168.1.101:3000",
    "http://10.0.0.50:8080"
));
```

### Cho phép tất cả địa chỉ trong dải mạng LAN

```java
configuration.setAllowedOriginPatterns(Arrays.asList(
    "http://192.168.*.*:*",  // Class C private network
    "http://10.*.*.*:*",      // Class A private network
    "http://172.16.*.*:*",    // Class B private network (16-31)
    "http://localhost:*"
));
```

## 🧪 Test API với CORS

### Sử dụng cURL

```bash
curl -X GET http://192.168.1.100:8080/orders \
  -H "Origin: http://192.168.1.101:3000" \
  -v
```

Kiểm tra response headers:
```
Access-Control-Allow-Origin: http://192.168.1.101:3000
Access-Control-Allow-Credentials: true
```

### Sử dụng JavaScript (từ Frontend)

```javascript
fetch('http://192.168.1.100:8080/orders', {
  method: 'GET',
  headers: {
    'Content-Type': 'application/json',
  },
  credentials: 'include' // Nếu cần gửi cookies
})
.then(response => response.json())
.then(data => console.log(data))
.catch(error => console.error('Error:', error));
```

### Sử dụng Postman

1. Mở Postman
2. Tạo request GET đến `http://192.168.1.100:8080/orders`
3. Vào tab "Headers" và kiểm tra response headers
4. Tìm header `Access-Control-Allow-Origin`

## 🔥 Firewall Configuration

### Windows Firewall

Mở port 8080:
```cmd
netsh advfirewall firewall add rule name="Spring Boot App" dir=in action=allow protocol=TCP localport=8080
```

Hoặc tắt firewall cho mạng private (không khuyến khích):
- Mở "Windows Defender Firewall"
- Chọn "Turn Windows Defender Firewall on or off"
- Tắt cho "Private network"

### Linux (Ubuntu/Debian)

```bash
sudo ufw allow 8080/tcp
sudo ufw reload
```

### Linux (CentOS/RHEL)

```bash
sudo firewall-cmd --zone=public --add-port=8080/tcp --permanent
sudo firewall-cmd --reload
```

## 📱 Test từ các thiết bị khác nhau

### Từ máy tính khác

```bash
curl http://192.168.1.100:8080/orders
```

### Từ điện thoại

Mở trình duyệt trên điện thoại (đảm bảo kết nối cùng WiFi):
```
http://192.168.1.100:8080/orders
```

### Từ ứng dụng web (React, Angular, Vue)

Cấu hình API base URL:
```javascript
const API_BASE_URL = 'http://192.168.1.100:8080';

axios.get(`${API_BASE_URL}/orders`)
  .then(response => console.log(response.data));
```

## 🐛 Troubleshooting

### Lỗi: Connection refused

- ✅ Kiểm tra ứng dụng đã chạy chưa
- ✅ Kiểm tra port đúng (8080)
- ✅ Kiểm tra `server.address=0.0.0.0` trong application.properties

### Lỗi: CORS policy

- ✅ Kiểm tra SecurityConfig đã có `@Configuration` và `@EnableWebSecurity`
- ✅ Kiểm tra `.cors()` đã được cấu hình trong SecurityFilterChain
- ✅ Kiểm tra allowedOrigins hoặc allowedOriginPatterns

### Lỗi: Access denied / 403 Forbidden

- ✅ Kiểm tra `.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())`
- ✅ Tắt CSRF: `.csrf(csrf -> csrf.disable())`
- ✅ Kiểm tra log để xem chi tiết

### Không kết nối được từ máy khác

- ✅ Ping từ máy khác: `ping 192.168.1.100`
- ✅ Kiểm tra firewall
- ✅ Đảm bảo cùng mạng LAN
- ✅ Thử `telnet 192.168.1.100 8080`

## 📊 Monitoring và Logging

Xem log CORS và Security:
```properties
logging.level.org.springframework.security=DEBUG
logging.level.org.springframework.web.cors=DEBUG
```

## 🔐 Best Practices cho Production

1. **Không cho phép tất cả origins:**
   ```java
   configuration.setAllowedOrigins(Arrays.asList("http://specific-ip:port"));
   ```

2. **Bật authentication:**
   ```java
   .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
   ```

3. **Sử dụng HTTPS:**
   - Cấu hình SSL certificate
   - Force HTTPS redirect

4. **Rate limiting:**
   - Thêm dependency như Bucket4j
   - Giới hạn số request từ mỗi IP

5. **API Key hoặc JWT:**
   - Implement JWT authentication
   - Validate API keys

## 📚 Tài liệu tham khảo

- [Spring Security Reference](https://docs.spring.io/spring-security/reference/)
- [Spring Boot CORS Configuration](https://spring.io/guides/gs/rest-service-cors/)
- [MDN - CORS](https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS)

## 🎯 Summary

Với cấu hình hiện tại:
- ✅ Spring Security đã được cấu hình
- ✅ CORS cho phép truy cập từ tất cả origins (development mode)
- ✅ Server lắng nghe trên `0.0.0.0:8080` (tất cả network interfaces)
- ✅ CSRF đã tắt (phù hợp REST API)
- ✅ Session stateless

**Để chạy trong môi trường production, hãy:**
1. Giới hạn allowedOrigins chỉ các IP cụ thể
2. Bật authentication
3. Sử dụng HTTPS
4. Thêm rate limiting

