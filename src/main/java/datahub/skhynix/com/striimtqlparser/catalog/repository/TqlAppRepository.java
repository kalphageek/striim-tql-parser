package datahub.skhynix.com.striimtqlparser.catalog.repository;

import datahub.skhynix.com.striimtqlparser.catalog.entity.TqlAppEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TqlAppRepository extends JpaRepository<TqlAppEntity, String> {
    public List<TqlAppEntity> findBySourceTypeAndDestType(String sourceType, String destType);
}
