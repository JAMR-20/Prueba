package com.example.pruebaTecnica.controller;

import com.example.pruebaTecnica.dto.ProductDto;
import com.example.pruebaTecnica.dto.ProductRequestDto;
import com.example.pruebaTecnica.entity.ProductEntity;
import com.example.pruebaTecnica.exception.ClientNotFoundException;
import com.example.pruebaTecnica.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/productos")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/save")
    public ProductDto saveProduct(@RequestBody ProductDto productDataDto) throws ClientNotFoundException {
        return productService.save(productDataDto.getTipoCuenta(), productDataDto.getSaldo(), productDataDto.getExentaGMF(), productDataDto.getClienteId());
    }
    @PutMapping("/update/{id}")
    public ProductDto updateProduct (@PathVariable Long id, @RequestBody ProductEntity productEntity){
        return productService.updateProduct(id, productEntity);
    }
    @GetMapping("/findAll")
    public List<ProductEntity> findAll(){
        return productService.findAll();
    }
}
