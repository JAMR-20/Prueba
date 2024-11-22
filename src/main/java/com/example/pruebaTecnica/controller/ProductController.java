package com.example.pruebaTecnica.controller;

import com.example.pruebaTecnica.dto.ProductDto;
import com.example.pruebaTecnica.entity.ProductEntity;
import com.example.pruebaTecnica.exception.ClientNotFoundException;
import com.example.pruebaTecnica.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/productos")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto requestBody) throws ClientNotFoundException {
        ProductDto savedProduct = productService.save(
                requestBody.getTipoCuenta(),
                requestBody.getSaldo(),
                requestBody.getExentaGMF(),
                requestBody.getClienteId()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
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
