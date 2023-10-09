package datahub.skhynix.com.striimtqlparser.catalog.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CAT_CDCCOLL_INF")
@Getter
public class TqlAppEntity {
    @Id
    String appName;
    String hashValue;
    String sourceType;
    String destType;
}
