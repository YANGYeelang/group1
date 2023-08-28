package group1.habitAnalysis.repository.impl;

import group1.habitAnalysis.entity.CategoryEntity;
import group1.habitAnalysis.entity.HistoryEntity;
import group1.habitAnalysis.repository.HistoryNativeRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Repository
public class HistoryNativeRepositoryImpl implements HistoryNativeRepository {
    private final JdbcTemplate jdbcTemplate;

    public HistoryNativeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<HistoryEntity> findHistoryByUserEmail(String email) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT history_id,choice_id_dislike,choice_id_like,create_date,category_id FROM history WHERE 1=1");

        List<Object> params = new ArrayList<>();
        if (email != null){
            sb.append(" and user_email = ? order by create_date DESC");
            params.add(email);
        }

        return  this.jdbcTemplate.query(sb.toString(), new RowMapper<HistoryEntity>() {
            @Override
            public HistoryEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                int col = 1;
                HistoryEntity entity = new HistoryEntity();
                entity.setHistoryId(rs.getString(col++));
                entity.setChoiceIdDislike(rs.getString(col++));
                entity.setChoiceIdLike(rs.getString(col++));

                Timestamp createDate = rs.getTimestamp(col++);
                if (createDate != null){
                    entity.setCreateDate(createDate.toLocalDateTime());
                }

                CategoryEntity category = new CategoryEntity();
                category.setId(rs.getInt(col++));
                entity.setCategory(category);

                return entity;
            }
        }, params.toArray());

    }
}
