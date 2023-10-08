package datahub.skhynix.com.striimtqlparser.service;

import datahub.skhynix.com.striimtqlparser.catalog.entity.TqlAppEntity;
import datahub.skhynix.com.striimtqlparser.dto.TqlFile;

import java.util.List;

public interface TrailToJsonParser {
    public <T> void parseTqlApps(String striimHostname);
    public void extractTqlTiles();
    public void moveToTempTqlFiles(); //recreate Temp Dir
    public List<TqlFile> compareTqlFiles();
    public List<TqlAppEntity> parseTrailToJsonTqls(List<TqlFile> tqlFiles);
    public <T> void saveTqlApps(List<T> tqlApps);
    public void backupDefalutTqlFiles(String filenamePatter);
}
