package bitcamp.api.common.config;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 상대방의 코드로 바뀐다. 외부에서 들어오는 애는 밖에서. 들어와서는 내꺼.(3000번 허가) - 두개를 매칭
// 아마존에서는 굉장히 길게 돼 있다. 이거를 헷깔리지 않게 상대방거를 위로 ,내꺼를 밑으로
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "")
public class CorsConfig implements WebMvcConfigurer {
    // MV를 연결해주는애
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:8080")
                .allowedOrigins("http://localhost:8080");
    }
}
