package com.sw.lp.service;

import com.sw.lp.dao.ProductDao;
import com.sw.lp.record.Product;
import java.util.List;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductService {

  private ProductDao productDao;

  public ProductService(ProductDao productDao) {
    this.productDao = productDao;
  }

  public void saveProduct(Product product, int millId) {
    productDao.saveProduct(product, millId);
  }

  public List<Product> getAllProducts(int millId) {
    return productDao.getAllProducts(millId);
  }
}
