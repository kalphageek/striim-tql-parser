package datahub.skhynix.com.striimtqlparser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.io.File;

@AllArgsConstructor
@Getter
public class TqlFile {
    String appName;
    String hashValue;
    File tqlFile;
}
