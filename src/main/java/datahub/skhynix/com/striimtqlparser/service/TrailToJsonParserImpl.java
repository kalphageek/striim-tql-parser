package datahub.skhynix.com.striimtqlparser.service;

import datahub.skhynix.com.striimtqlparser.catalog.entity.TqlAppEntity;
import datahub.skhynix.com.striimtqlparser.dto.TqlFile;

public class TrailToJsonParserImpl implements TqlParser {
    private final String filenamePattern = "TrailToJson";

    @Override
    public String getFilenamePattern() {
        return filenamePattern;
    }

    @Override
    public TqlAppEntity parseTqlFile(TqlFile tqlFile) {
        return new TqlAppEntity();
    }
}
