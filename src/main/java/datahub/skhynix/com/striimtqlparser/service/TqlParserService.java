package datahub.skhynix.com.striimtqlparser.service;

import datahub.skhynix.com.striimtqlparser.catalog.entity.TqlAppEntity;
import datahub.skhynix.com.striimtqlparser.dto.TqlFile;

import java.util.List;

public interface TqlParserService {
    public List<TqlAppEntity> parseTqlApps(String striimHostname);
}