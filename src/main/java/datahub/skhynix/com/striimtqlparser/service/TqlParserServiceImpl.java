package datahub.skhynix.com.striimtqlparser.service;

import datahub.skhynix.com.striimtqlparser.catalog.entity.TqlAppEntity;
import datahub.skhynix.com.striimtqlparser.dto.TqlFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TqlParserServiceImpl <T> implements TqlParserService {
    private String trailToJsonPattern;
    private final T tqlParser;

    @Override
    public List<TqlAppEntity> parseTqlApps(String striimHostname) {
        extractTqlTiles(striimHostname);
        scpToTempTqlFiles(striimHostname);

        // 1. Trail To Json TQL Parsing
        List<TqlFile> modifiedFiles = compareTqlFiles(trailToJsonPattern);

        List<TqlAppEntity> tqlAppEntities = modifiedFiles.stream()
                .map(file -> ((TrailToJsonParser)tqlParser).parseTqlFile(file))
                .collect(Collectors.toList());
        saveTqlApps(tqlAppEntities);
        backupDefalutTqlFiles(trailToJsonPattern);
        copyTofalutTqlFiles(trailToJsonPattern);
        // --
        
        return new ArrayList<>();
    }

    private void copyTofalutTqlFiles(String trailToJsonPattern) {
    }

    private void extractTqlTiles(String striimHostname) {

    }

    private void scpToTempTqlFiles(String striimHostname) {

    }

    private List<TqlFile> compareTqlFiles(String filenamePattern) {
        return null;
    }

    private void saveTqlApps(List<TqlAppEntity> tqlApps) {

    }

    private void backupDefalutTqlFiles(String filenamePattern) {

    }
}
