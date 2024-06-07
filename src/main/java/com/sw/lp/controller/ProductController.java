package com.sw.lp.controller;

import com.sw.lp.entity.AppResponse;
import com.sw.lp.record.Product;
import com.sw.lp.service.ProductService;
import com.sw.lp.utils.JsonUtils;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

  private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

  private ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public AppResponse getProducts() {
    List<Product> machines = productService.getAllProducts(1);
    return AppResponse.ok(JsonUtils.transformTree(machines));
  }

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public AppResponse newProduct(@RequestBody Product product) {
    productService.saveProduct(product, 1); //TODO: get mill id from auth facade
    return AppResponse.ok("Success");
  }

}
