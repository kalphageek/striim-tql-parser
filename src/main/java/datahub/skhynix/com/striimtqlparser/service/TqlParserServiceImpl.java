package datahub.skhynix.com.striimtqlparser.service;

import datahub.skhynix.com.striimtqlparser.catalog.entity.TqlAppEntity;
import datahub.skhynix.com.striimtqlparser.catalog.repository.TqlAppRepository;
import datahub.skhynix.com.striimtqlparser.common.AppProperties;
import datahub.skhynix.com.striimtqlparser.dto.TqlFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TqlParserServiceImpl implements TqlParserService {
    private final AppProperties appProperties;
    private final TqlAppRepository tqlAppRepository;

    @Override
    public void parseAndSaveTqlFiles(String striimHostname) {
        extractTqlFiles(striimHostname);
        scpToTempTqlFiles(striimHostname);
        File tempDir = new File(appProperties.getTempPath());

        // 1. Trail To Json Parser
        parseAndSaveTqlFiles(tempDir, new TrailToJsonParserImpl());
    }

    /**
     * 1. Trail To Json Parser
     * @param tempDir
     * @param tqlParser
     */
    private void parseAndSaveTqlFiles (File tempDir, TqlParser tqlParser) {
        log.info("# {} is started", tqlParser.getClass().getName());
        log.info("# tqlParser.filenamePattern is {}", tqlParser.getFilenamePattern());
        // 1. TrailToJson 파일 선택 및 읽기
        File[] tempFiles = tempDir.listFiles(file -> file.getName().contains(tqlParser.getFilenamePattern()));
        // 2. 테이블에서 기존 데이터 읽기
        List<TqlAppEntity> currentTqlAppEntities = tqlAppRepository.findBySourceTypeAndDestType(tqlParser.getSourceType(), tqlParser.getDestType());
        // 3. 파일과 데이터 비교해서 파싱 대상 선별하기
        List<TqlFile> tqlFiles = compareTqlFiles(currentTqlAppEntities, tempFiles); //modified tql files after the temp vs the all dir compare
        // 4. 선별한 파일 파싱해서 Entity 생성하기
        List<TqlAppEntity> tqlAppEntities = tqlFiles.stream()
                .map(tqlParser::parseTqlFile)
                .collect(Collectors.toList());
        // 5. Entity 저장하기
        saveTqlApps(tqlAppEntities);
        // 6. 완료된 파일 default dir로 옮기
        copyToDefalutTqlFiles(tqlParser.getFilenamePattern());
        log.info("# {} save {} apps", tqlParser.getClass().getName(), tqlAppEntities.size());
    }

    /**
     * files의 내용으로 hashValue를 추출해서 TqlAppEntity에 저장된 hashValue와 비교한다
     * @param tqlAppEntities
     * @param files
     * @return
     */
    private List<TqlFile> compareTqlFiles(List<TqlAppEntity> tqlAppEntities, File[] files) {
        List<TqlFile> tqlFiles = Arrays.stream(files).map(file -> new TqlFile(file.getName(), getFileHash(file), file))
                .filter(tqlFile -> !compare(tqlAppEntities, tqlFile))
                .collect(Collectors.toList());

        log.info("# compareTqlFiles is finished");
        return tqlFiles;
    }

    private boolean compare(List<TqlAppEntity>tqlAppEntities, TqlFile tqlFile) {
        Iterator<TqlAppEntity> it = tqlAppEntities.iterator();
        while (it.hasNext()) {
            TqlAppEntity app = it.next();
            if (app.getAppName().equals(tqlFile.getAppName())) {
                if (app.getHashValue().equals(tqlFile.getHashValue())) return true;
                else return false;
            }
        }
        return false;
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

    private String getFileHash(File file) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("# TqlParserServiceImpl > getFileHash [" + file.getName() + "]" + e.toString());
        }

        try (InputStream is = new FileInputStream(file)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                md.update(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new RuntimeException("# TqlParserServiceImpl > getFileHash [" + file.getName() + "]" + e.toString());
        }
        byte[] hashBytes = md.digest();

        // 바이트 배열을 16진수 문자열로 변환
        StringBuilder sb = new StringBuilder();
        for (byte hashByte : hashBytes) {
            sb.append(String.format("%02x", hashByte));
        }
        return sb.toString();
    }

    // 문자열의 SHA-256 해시 값을 계산하는 메서드
    private String getStringHash(String input) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("# TqlParserServiceImpl > getStringHash [" + input + "]" + e.toString());
        }
        byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));

        // 바이트 배열을 16진수 문자열로 변환
        StringBuilder sb = new StringBuilder();
        for (byte hashByte : hashBytes) {
            sb.append(String.format("%02x", hashByte));
        }
        return sb.toString();
    }
}
