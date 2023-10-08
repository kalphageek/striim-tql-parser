package datahub.skhynix.com.striimtqlparser.service;

import datahub.skhynix.com.striimtqlparser.catalog.entity.TqlAppEntity;
import datahub.skhynix.com.striimtqlparser.dto.TqlFile;

import java.util.List;

public class TrailToJsonParserImpl implements TrailToJsonParser {
    @Override
    public <T> void parseTqlApps(String striimHostname) {
        extractTqlTiles();
        moveToTempTqlFiles();
        List<TqlFile> tqlFiles = compareTqlFiles();

        List<TqlAppEntity> tqlAppEntities = parseTrailToJsonTqls(tqlFiles);
        saveTqlApps(tqlAppEntities);
        backupDefalutTqlFile();
    }

    @Override
    public List<TqlAppEntity> parseTrailToJsonTqls(List<TqlFile> tqlFiles) {

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
    public void backupDefalutTqlFile() {

    }
}
