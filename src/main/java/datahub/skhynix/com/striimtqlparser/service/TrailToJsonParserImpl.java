package datahub.skhynix.com.striimtqlparser.service;

import datahub.skhynix.com.striimtqlparser.catalog.entity.TqlAppEntity;
import datahub.skhynix.com.striimtqlparser.dto.TqlFile;
import org.springframework.stereotype.Service;

@Service
public class TrailToJsonParserImpl implements TrailToJsonParser {

    @Override
    public TqlAppEntity parseTqlFile(TqlFile tqlFile) {
        return new TqlAppEntity();
    }
}
