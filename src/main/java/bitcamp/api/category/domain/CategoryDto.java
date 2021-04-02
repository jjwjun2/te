package bitcamp.api.category.domain;

import lombok.Data;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Data
@Lazy
public class CategoryDto {
    private long ctgNo;
    private String ctgName;
}