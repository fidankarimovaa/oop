package ms.labtaskapi.rest;


import lombok.RequiredArgsConstructor;
import ms.labtaskapi.dto.ProductDto;
import ms.labtaskapi.dto.ProductRequestDto;
import ms.labtaskapi.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ProductDto create(@RequestBody ProductRequestDto productRequestDto) {
        return productService.create(productRequestDto);
    }
    @PutMapping("/{id}")
    public ProductDto  update(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto) {
        return productService.update(id,productRequestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }
    @GetMapping("/{id}")
   public ProductDto getProductById(@PathVariable Long id){
       return productService.getProductById(id);
    }
    @GetMapping("/getAll")
    List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("buy/{userId}/{productId}/{count}")
    public List<ProductDto> buyProduct(@PathVariable Long userId,@PathVariable Long productId ,@PathVariable int count){
       return productService.buyProduct(userId,productId,count);
    }
    @GetMapping("return/{userId}/{productId}/{count}")
    public List<ProductDto> returnProduct(@PathVariable Long userId,@PathVariable Long productId,@PathVariable int count){
        return productService.returnProduct(userId,productId,count);



    }



}
