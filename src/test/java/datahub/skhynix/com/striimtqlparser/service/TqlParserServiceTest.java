package datahub.skhynix.com.striimtqlparser.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TqlParserServiceTest {
    String striimHostname = "1.1.1.1";
    @Autowired
    TqlParserService tqlParserService;

    @Test
    public void parseAndSaveTqlFiles_Test() {
        tqlParserService.parseAndSaveTqlFiles(striimHostname);
    }
}