package datahub.skhynix.com.striimtqlparser.service;

import datahub.skhynix.com.striimtqlparser.catalog.entity.TqlAppEntity;
import datahub.skhynix.com.striimtqlparser.dto.TqlFile;

public class TrailToJsonParserImpl implements TqlParser {
    private final String filenamePattern = "TrailToJson";
    private final String sourceType = "TRAIL";
    private final String destType = "JSON";

    @Override
    public String getFilenamePattern() {
        return filenamePattern;
    }

    @Override
    public String getSourceType() {
        return null;
    }

    @Override
    public String getDestType() {
        return null;
    }

    @Override
    public TqlAppEntity parseTqlFile(TqlFile tqlFile) {
        return new TqlAppEntity();
    }
}
