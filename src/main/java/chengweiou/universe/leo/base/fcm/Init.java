package chengweiou.universe.leo.base.fcm;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;


@Component
public class Init implements CommandLineRunner {
    @Autowired
    private Config config;
    @Override
    public void run(String... args) throws Exception {
        FileInputStream serviceAccount = new FileInputStream(config.getKeyPath());
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(config.getDbUrl())
                .build();

        FirebaseApp.initializeApp(options);
    }
}
