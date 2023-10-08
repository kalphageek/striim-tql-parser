package datahub.skhynix.com.striimtqlparser.service;

import datahub.skhynix.com.striimtqlparser.catalog.entity.TqlAppEntity;
import datahub.skhynix.com.striimtqlparser.dto.TqlFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TrailToJsonParserImpl implements TrailToJsonParser {
    String trailToJsonPattern;
    @Override
    public <T> void parseTqlApps(String striimHostname) {
        extractTqlTiles();
        moveToTempTqlFiles();
        List<TqlFile> tqlFiles = compareTqlFiles();

        // Trail To Json TQL Parsing
        List<TqlFile> trailToJsonTqlFile = tqlFiles.stream()
                .filter(item -> item.getFilename().contains(trailToJsonPattern))
                .collect(Collectors.toList());

        List<TqlAppEntity> tqlAppEntities = parseTrailToJsonTqls(trailToJsonTqlFile);
        saveTqlApps(tqlAppEntities);
        backupDefalutTqlFiles(trailToJsonPattern);
    }

    @Override
    public List<TqlAppEntity>  parseTrailToJsonTqls(List<TqlFile> tqlFiles) {
        return new ArrayList<>();
    }

    @Override
    public void extractTqlTiles() {

    }

    @Override
    public void moveToTempTqlFiles() {

    }

    @Override
    public List<TqlFile> compareTqlFiles() {
        return null;
    }

    @Override
    public <T> void saveTqlApps(List<T> tqlApps) {

    }

    @Override
    public void backupDefalutTqlFiles(String filenamePattern) {

    }
}
