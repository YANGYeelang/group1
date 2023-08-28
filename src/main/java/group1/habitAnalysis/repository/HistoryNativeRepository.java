package group1.habitAnalysis.repository;

import group1.habitAnalysis.entity.HistoryEntity;

import java.util.List;

public interface HistoryNativeRepository {
    List<HistoryEntity> findHistoryByUserEmail(String email);
}
