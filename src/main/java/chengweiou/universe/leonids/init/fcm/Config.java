package chengweiou.universe.leonids.init.fcm;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "api.fcm")
public class Config {
    private String keyPath;
    private String dbUrl;

    @Override
    public String toString() {
        return "Config{" +
                "keyPath='" + keyPath + '\'' +
                ", dbUrl='" + dbUrl + '\'' +
                '}';
    }

    public String getKeyPath() {
        return keyPath;
    }

    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }
}
