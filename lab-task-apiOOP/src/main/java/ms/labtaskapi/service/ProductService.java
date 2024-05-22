package ms.labtaskapi.service;

import ms.labtaskapi.dto.ProductDto;
import ms.labtaskapi.dto.ProductRequestDto;

import java.util.List;

public interface ProductService {
    ProductDto create(ProductRequestDto productRequestDto);
    ProductDto  update(Long id, ProductRequestDto productRequestDto) ;
    void delete(Long id);
    ProductDto getProductById(Long id);
    List<ProductDto> getAllProducts();

    List<ProductDto> buyProduct(Long userId,Long productId,int count);
    List<ProductDto> returnProduct(Long userId,Long productId,int count);




}
