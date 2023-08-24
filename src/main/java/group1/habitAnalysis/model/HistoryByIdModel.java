package group1.habitAnalysis.model;

import group1.habitAnalysis.entity.ChoiceEntity;
import lombok.Data;

import java.util.List;

@Data
public class HistoryByIdModel {
    private HistoryModel history;
    private List<ChoiceEntity> choiceLike;
    private List<ChoiceEntity> choiceDislike;
}
