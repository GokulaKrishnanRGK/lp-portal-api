package com.sw.lp.dao.mappers;

import com.sw.lp.constants.BlendType;
import com.sw.lp.constants.CountType;
import com.sw.lp.constants.EnduseType;
import com.sw.lp.constants.MaterialBlendType;
import com.sw.lp.constants.MaterialType;
import com.sw.lp.constants.ProcessType;
import com.sw.lp.constants.SpinningTechType;
import com.sw.lp.record.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ProductMapper implements RowMapper<Product> {

  @Override
  public Product mapRow(ResultSet rs, int numRows) throws SQLException {
    String name = rs.getString("name");
    String description = rs.getString("description");
    int millId = rs.getInt("mill_id");
    int productId = rs.getInt("product_id");
    CountType count = CountType.valueOfLabel(rs.getInt("count"));
    MaterialType materialType = MaterialType.valueOfLabel(rs.getInt("material_id"));
    ProcessType processType = ProcessType.valueOfLabel(rs.getInt("process_id"));
    BlendType blendType = BlendType.valueOfLabel(rs.getInt("blend_type_id"));
    SpinningTechType spinningTechType = SpinningTechType.valueOfLabel(rs.getInt("spinning_tech_id"));
    EnduseType enduseType = EnduseType.valueOfLabel(rs.getInt("enduse_id"));
    MaterialBlendType materialBlendType = MaterialBlendType.valueOfLabel(rs.getInt("material_blend_type_id"));
    String code = rs.getString("code");
    return new Product(productId, name, description, millId, count, materialType, processType, blendType, spinningTechType, enduseType,
        materialBlendType, code);
  }
}
