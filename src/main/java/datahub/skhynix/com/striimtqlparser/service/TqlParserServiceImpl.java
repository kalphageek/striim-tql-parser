package datahub.skhynix.com.striimtqlparser.service;

import datahub.skhynix.com.striimtqlparser.catalog.entity.TqlAppEntity;
import datahub.skhynix.com.striimtqlparser.common.AppProperties;
import datahub.skhynix.com.striimtqlparser.dto.TqlFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TqlParserServiceImpl implements TqlParserService {
    private final AppProperties appProperties;

    @Override
    public void parseAndSaveTqlFiles(String striimHostname) {
        extractTqlFiles(striimHostname);
        scpToTempTqlFiles(striimHostname);
        File tempDir = new File(appProperties.getTempPath());

        // 1. Trail To Json Parser
        parseAndSaveTqlFiles(tempDir, new TrailToJsonParserImpl());
    }

    private void parseAndSaveTqlFiles (File tempDir, TqlParser tqlParser) {
        log.info("# {} is started", tqlParser.getClass().getName());
        log.info("# tqlParser.filenamePattern is {}", tqlParser.getFilenamePattern());

        File[] tempFiles = tempDir.listFiles(file -> file.getName().contains(tqlParser.getFilenamePattern()));
        List<TqlFile> tqlFiles = compareTqlFiles(tempFiles); //modified tql files after the temp vs the all dir compare

        List<TqlAppEntity> tqlAppEntities = tqlFiles.stream()
                .map(tqlParser::parseTqlFile)
                .collect(Collectors.toList());
        saveTqlApps(tqlAppEntities);
        backupDefalutTqlFiles(tqlParser.getFilenamePattern());
        copyToDefalutTqlFiles(tqlParser.getFilenamePattern());
        log.info("# {} save {} apps", tqlParser.getClass().getName(), tqlAppEntities.size());
    }

    private List<TqlFile> compareTqlFiles(File[] files) {
        log.info("# compareTqlFiles is finished");
        return new ArrayList<>();
    }

    private void extractTqlFiles(String striimHostname) {
        log.info("# extractTqlFiles is finished");
    }

    private void scpToTempTqlFiles(String striimHostname) {
        log.info("# scpToTempTqlFiles is finished");
    }

    private void saveTqlApps(List<TqlAppEntity> tqlApps) {
        log.info("# saveTqlApps is finished");
    }

    private void copyToDefalutTqlFiles(String filenamePattern) {
        log.info("# copyToDefalutTqlFiles is finished");
    }

    private void backupDefalutTqlFiles(String filenamePattern) {
        log.info("# backupDefalutTqlFiles is finished");
    }
}
