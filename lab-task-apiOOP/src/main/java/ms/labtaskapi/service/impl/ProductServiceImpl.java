package ms.labtaskapi.service.impl;

import lombok.RequiredArgsConstructor;
import ms.labtaskapi.dto.ProductDto;
import ms.labtaskapi.dto.ProductRequestDto;
import ms.labtaskapi.entity.Product;
import ms.labtaskapi.entity.User;
import ms.labtaskapi.exception.NotFoundException;
import ms.labtaskapi.repo.ProductRepository;
import ms.labtaskapi.repo.UserRepository;
import ms.labtaskapi.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;



    @Override
    public ProductDto create(ProductRequestDto productRequestDto) {
        Product product = modelMapper.map(productRequestDto,Product.class);
        Product productInRepo = productRepository.save(product);
        return modelMapper.map(productInRepo,ProductDto.class);
    }

    @Override
    public ProductDto update(Long id, ProductRequestDto productRequestDto) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() ->
                new NotFoundException("product not found with id: " + id));
        existingProduct.setName(productRequestDto.getName());
        existingProduct.setPrice(productRequestDto.getPrice());
        return modelMapper.map(existingProduct,ProductDto.class);
    }

    @Override
    public void delete(Long id) {
        Product searchedProduct = productRepository.findById(id).orElseThrow(() ->
                new NotFoundException("product not found with id: " + id));
        productRepository.delete(searchedProduct);

    }

    @Override
    public ProductDto getProductById(Long id) {
        Product searchedProduct = productRepository.findById(id).orElseThrow(() ->
                new NotFoundException("product not found with id: " + id));
        return modelMapper.map(searchedProduct,ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return allProducts
                .stream()
                .map(product -> modelMapper.map(product,ProductDto.class)).toList();

    }

    @Override
    public List<ProductDto> buyProduct(Long userId, Long productId,int count) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException("user not found with id: " + userId));
        Product searchedProduct = productRepository.findById(productId)
                .orElseThrow(() ->
                new NotFoundException("product not found with id: " + productId));
        if(user.isEnable()&&user.getBalance()-count*searchedProduct.getPrice()
                >=0&&searchedProduct.getStockCount()*count > 0){
            user.setBalance(user.getBalance() - count*searchedProduct.getPrice());
           userRepository.save(user);
           searchedProduct.setStockCount(searchedProduct.getStockCount()-count);
           productRepository.save(searchedProduct);
          return getAllProducts();

        }
        else
            throw new IllegalStateException("Something is wrong");
    }

    @Override
    public List<ProductDto> returnProduct(Long userId, Long productId,int count) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException("user not found with id: " + userId));
        Product searchedProduct = productRepository.findById(productId).orElseThrow(() ->
                new NotFoundException("product not found with id: " + productId));
        if(user.isEnable())
        {
            user.setBalance(user.getBalance() + count*searchedProduct.getPrice());
            userRepository.save(user);
            searchedProduct.setStockCount(searchedProduct.getStockCount()+count);
            productRepository.save(searchedProduct);
            return getAllProducts();

        }
        else
            throw new IllegalStateException("Something is wrong");
    }

    }

