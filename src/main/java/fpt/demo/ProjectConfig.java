package fpt.demo;

import fpt.demo.model.*;
import fpt.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class ProjectConfig {

    @Transactional
    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository,
                                        CategoryRepository categoryRepository,
                                        ProviderRepository providerRepository,
                                        CartRepository cartRepository,
                                        OrderRepository orderRepository){
        return args -> {

            Category footwear = Category.builder()
                    .categoryName("Foot Wear")
                    .build();
            Category hat = Category.builder()
                    .categoryName("Hat")
                    .build();
            Category gloves = Category.builder()
                    .categoryName("Gloves")
                    .build();
            Category underpant = Category.builder()
                    .categoryName("Underpant")
                    .build();
            Category dress = Category.builder()
                    .categoryName("Dress")
                    .build();
            Category male = Category.builder()
                    .categoryName("Male")
                    .build();
            Category female = Category.builder()
                    .categoryName("Female")
                    .build();
            categoryRepository.saveAll(List.of(female,male,hat,footwear,dress,gloves,underpant));

            Provider vinGroup = Provider.builder()
                    .providerName("Vin Group")
                    .address("Ha Noi")
                    .providerEmail("vingroup@mail.con")
                    .providerPhone("09999")
                    .createAt(LocalDateTime.now())
                    .status(true)
                    .build();
            Provider masan = Provider.builder()
                    .providerName("Masan")
                    .address("Ho Chi Minh")
                    .providerEmail("masangroup@mail.con")
                    .providerPhone("09888")
                    .createAt(LocalDateTime.now())
                    .status(true)
                    .build();
            Provider nasa = Provider.builder()
                    .providerName("Nasa")
                    .address("US")
                    .providerEmail("nasa@mail.con")
                    .providerPhone("911")
                    .createAt(LocalDateTime.now())
                    .status(true)
                    .build();
            providerRepository.saveAll(List.of(masan,nasa,vinGroup));

            Product shoe = Product.builder()
                    .productName("Running shoes")
                    .price(12.99)
                    .description("This is a nice running shoe")
                    .importPrice(2.99)
                    .itemInStock(20)
                    .itemInShelf(3)
                    .createdAt(LocalDateTime.now())
                    .imagePath("goolgle.com")
                    .status(true)
                    .build();
            Product book = Product.builder()
                    .productName("Book of revelation")
                    .price(12.23)
                    .description("This is a nice magic book")
                    .importPrice(3.99)
                    .itemInStock(30)
                    .itemInShelf(12)
                    .createdAt(LocalDateTime.now())
                    .imagePath("book.com")
                    .status(true)
                    .build();
            Product invisible_cloak = Product.builder()
                    .productName("Cloak")
                    .price(11.59)
                    .description("This is a nice cloak shoe")
                    .importPrice(2.23)
                    .itemInStock(13)
                    .itemInShelf(3)
                    .createdAt(LocalDateTime.now())
                    .imagePath("cloak.com")
                    .status(true)
                    .build();


            shoe.setCategoryList(List.of(footwear, male, female));
            book.setCategoryList(List.of(male, female));
            invisible_cloak.setCategoryList(List.of(male, female, underpant, dress));

            shoe.setProvider(vinGroup);
            book.setProvider(nasa);
            invisible_cloak.setProvider(masan);

//            vinGroup.setProductList((List.of(shoe)));
//            masan.setProductList((List.of(invisible_cloak)));
//            nasa.setProductList(List.of(book));


//            Set<Product> productSet = new HashSet<>(Arrays.asList(shoe, book, invisible_cloak));
//            providerRepository.saveAll(List.of(masan,nasa,vinGroup));
            productRepository.saveAll(List.of(shoe,book,invisible_cloak));

//            not finished
//            vinGroup.setProductList((List.of(shoe)));
//            masan.setProductList((List.of(invisible_cloak)));
//            nasa.setProductList(List.of(book));

            Cart cart1 = Cart.builder().build();
            Cart cart2 = Cart.builder().build();
            Cart cart3 = Cart.builder().build();
            Cart cart4 = Cart.builder().build();

            //need to add cart directly to cartItem & add cartItems to cart
            //cartItem must add cart, else the field will be null, and cartId will be decided by the one added, not how the cart adds the list of cartItems
            CartItem item1 = CartItem.builder()
                    .cart(cart1)
                    .product(invisible_cloak)
                    .quantity(4)
                    .build();

            CartItem item2 = CartItem.builder()
                    .cart(cart1)
                    .product(book)
                    .quantity(2)
                    .build();

            CartItem item3 = CartItem.builder()
                    .cart(cart1)
                    .product(shoe)
                    .quantity(1)
                    .build();

            CartItem item4 = CartItem.builder()
                    .cart(cart2)
                    .product(shoe)
                    .quantity(1)
                    .build();

            CartItem item5 = CartItem.builder()
                    .cart(cart2)
                    .product(shoe)
                    .quantity(1)
                    .build();

            CartItem item6 = CartItem.builder()
                    .cart(cart3)
                    .product(shoe)
                    .quantity(1)
                    .build();

            //if list of cartItems are not added to the cart, the cartItem will not be rendered by the code
            cart1.setItems(List.of(item1,item2,item3));
            cart2.setItems(List.of(item4,item5));
            cart4.setItems(List.of(item6));

//            cart1.getItems().add(CartItem.builder()
//                    .cart(cart1)
//                    .product(invisible_cloak)
//                    .quantity(4)
//                    .build());

            cartRepository.saveAll(List.of(cart1,cart2,cart3,cart4));



        };
    }
}

