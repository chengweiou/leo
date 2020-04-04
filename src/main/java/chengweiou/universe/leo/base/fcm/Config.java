package chengweiou.universe.leo.base.fcm;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "api.fcm")
@Component
@Data
public class Config {
    private String keyPath;
    private String dbUrl;
}
