package datahub.skhynix.com.striimtqlparser.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@AllArgsConstructor
@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "app.tql.paths")
public class AppProperties {
    private String tempPath;
    private String defaultPath;
    private String backupPath;
}
