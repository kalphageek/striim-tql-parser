package datahub.skhynix.com.striimtqlparser.service;

import datahub.skhynix.com.striimtqlparser.catalog.entity.TqlAppEntity;
import datahub.skhynix.com.striimtqlparser.dto.TqlFile;

public interface TqlParser {
    public String getFilenamePattern();

    public TqlAppEntity parseTqlFile(TqlFile tqlFile);
}
