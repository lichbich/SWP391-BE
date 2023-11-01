package fpt.demo.utils;

import fpt.demo.model.Category;
import fpt.demo.model.Product;
import fpt.demo.model.Provider;
import fpt.demo.model.dto.*;
import fpt.demo.repository.CategoryRepository;
import fpt.demo.repository.ProductRepository;
import fpt.demo.repository.ProviderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DTOMapper {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProviderRepository providerRepository;


    public FullCategoryDTO CategoryToDTO(Category category){
        return new FullCategoryDTO(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getDescription(),
                category.getProductSet()
        );
    }

    public Product CreateProductDTOToProduct(CreateProductDTO createProductDTO){
        // Create a new Product entity and set its properties based on the DTO
        Product product = Product.builder()
                .productName(createProductDTO.getProductName())
                .price(createProductDTO.getPrice())
                .importPrice(createProductDTO.getImportPrice())
                .description(createProductDTO.getDescription())
                .itemInStock(createProductDTO.getItemInStock())
                .itemInShelf(createProductDTO.getItemInShelf())
                .imagePath(createProductDTO.getImagePath())
                .build();

        // Set categories using their ID (Assuming you have a method to retrieve category by ID)
        // Currently failed
        Category category = categoryRepository.findById(createProductDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        product.setCategory(category);

        // Set the provider using its ID (Assuming you have a method to retrieve a provider by ID)
        Provider provider = providerRepository.findById(createProductDTO.getProviderId())
                .orElseThrow(() -> new EntityNotFoundException("Provider not found"));
        product.setProvider(provider);
        return product;
    }

    public List<Product> CreateProductDTOsToProducts(List<CreateProductDTO> createProductDTOs){
        List<Product> products = new ArrayList<>();
        for(CreateProductDTO item : createProductDTOs){
            products.add(CreateProductDTOToProduct(item));
        }
        return products;
    }

    public Product UpdateProductDTOToProduct(UpdateProductDTO updateProductDTO){
        // Create a new Product entity and set its properties based on the DTO
        Product product = Product.builder()
                .productName(updateProductDTO.getProductName())
                .price(updateProductDTO.getPrice())
                .importPrice(updateProductDTO.getImportPrice())
                .description(updateProductDTO.getDescription())
                .itemInStock(updateProductDTO.getItemInStock())
                .itemInShelf(updateProductDTO.getItemInShelf())
                .imagePath(updateProductDTO.getImagePath())
                .category(updateProductDTO.getCategory())
                .build();

        // Set categories using their IDs (Assuming you have a method to retrieve categories by ID)
//        Set<Category> categories = new HashSet<>();
//        for (Long categoryId : updateProductDTO.getCategoryIds()) {
//            Category category = categoryRepository.findById(categoryId)
//                    .orElseThrow(() -> new EntityNotFoundException("Category not found"));
//            categories.add(category);
//        }
//        product.setCategorySet(categories);

        if(updateProductDTO.getProviderId()!=null){
            // Set the provider using its ID (Assuming you have a method to retrieve a provider by ID)
            Provider provider = providerRepository.findById(updateProductDTO.getProviderId())
                    .orElseThrow(() -> new EntityNotFoundException("Provider not found"));
            product.setProvider(provider);
        }
        return product;
    }

    public List<ViewProductDTO> ProductIdsToViewProductDTOList(List<Long> productIds) {
        List<Product> products = productRepository.findAllById(productIds);

        List<ViewProductDTO> viewProductDTOs = products.stream()
                .map(this::ProductToViewProductDTO)
                .collect(Collectors.toList());

        return viewProductDTOs;
    }

    public List<ViewProductDTO> ProductsToViewProductDTOList(List<Product> products) {
        List<ViewProductDTO> viewProductDTOs = products.stream()
                .map(this::ProductToViewProductDTO)
                .collect(Collectors.toList());

        return viewProductDTOs;
    }

    public ViewProductDTO ProductToViewProductDTO(Product product) {
        ViewProductDTO viewProductDTO = ViewProductDTO.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .imagePath(product.getImagePath())
                .soldQuantity(product.getSoldQuantity())
                .itemInShelf(product.getItemInShelf())
                .provider(product.getProvider())
                .category(product.getCategory())
                .build();
        return viewProductDTO;
        // In case change category id list to category list
        // .categorySet(product.getCategorySet().stream()
        //                    .map(category -> new ViewCategoryDTO(category.getCategoryName(), category.getDescription()))
        //                    .collect(Collectors.toSet()))
        // .categoryIds(product.getCategorySet().stream()
        //                        .map(Category::getCategoryId)
        //                        .collect(Collectors.toSet()))
    }

    public ViewCategoryDTO CategoryToViewCategoryDTO(Category category){
        ViewCategoryDTO viewCategoryDTO = ViewCategoryDTO.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .description(category.getDescription())
                .build();
        return viewCategoryDTO;
    }

    //only return provider without setting status, created at, products list
    public Provider CreateProviderDTOToProvider(CreateProviderDTO providerDTO){
        Provider provider = Provider.builder()
                .providerName(providerDTO.getProviderName())
                .providerAddress(providerDTO.getProviderAddress())
                .providerEmail(providerDTO.getProviderEmail())
                .providerPhone(providerDTO.getProviderPhone())
                .build();
        return provider;
    }

    //only return list of provider without setting status, created at, products list
    public List<Provider> CreateProviderDTOsToProviders(List<CreateProviderDTO> providerDTOs){
        List<Provider> providers = new ArrayList<>();
        for(CreateProviderDTO item : providerDTOs){
            providers.add(CreateProviderDTOToProvider(item));
        }
        return providers;
    }

    //only return provider without setting status, created at, products list
    public Provider UpdateProviderDTOToProvider(UpdateProviderDTO providerDTO){
        Provider provider = Provider.builder()
                .providerName(providerDTO.getProviderName())
                .providerAddress(providerDTO.getProviderAddress())
                .providerEmail(providerDTO.getProviderEmail())
                .providerPhone(providerDTO.getProviderPhone())
                .build();
        return provider;
    }

}
