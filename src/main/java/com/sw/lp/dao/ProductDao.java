package com.sw.lp.dao;

import com.sw.lp.mappers.ProductMapper;
import com.sw.lp.record.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("productDao")
public class ProductDao {

  static final Logger logger = LoggerFactory.getLogger(ProductDao.class);

  private JdbcTemplate jdbcTemplate;
  private NamedParameterJdbcTemplate namedJdbcTemplate;

  public ProductDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedJdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.namedJdbcTemplate = namedJdbcTemplate;
  }

  public void saveProduct(Product product, int millId) {
    String sql =
        "INSERT INTO product (mill_id, name, description, unit_id, count, material_id, process_id, blend_type_id, spinning_tech_id, "
            + "enduse_id, material_blend_type_id, code) values (:mill_id, :name, "
            + ":model, :make, :year, :machine_type)";
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("mill_id", millId);
    parameters.put("name", product.name());
    parameters.put("description", product.description());
    parameters.put("unit_id", product.unitId());
    parameters.put("count", product.count());
    parameters.put("material_id", product.materialType().getType());
    parameters.put("process_id", product.processType().getType());
    parameters.put("blend_type_id", product.blendType().getType());
    parameters.put("spinning_tech_id", product.spinningTechType().getType());
    parameters.put("enduse_id", product.enduseType().getType());
    parameters.put("material_blend_type_id", product.materialBlendType().getType());
    parameters.put("code", product.code());
    namedJdbcTemplate.update(sql, parameters);
  }

  public List<Product> getAllProducts(int millId) {
    String sql = "SELECT * FROM product where product.mill_id=?";
    return jdbcTemplate.query(sql, new ProductMapper(), millId);
  }

}
