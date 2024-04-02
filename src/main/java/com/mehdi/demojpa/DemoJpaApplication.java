package com.mehdi.demojpa;

import com.mehdi.demojpa.entities.Product;
import com.mehdi.demojpa.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoJpaApplication implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository ;

    public static void main(String[] args) {
        SpringApplication.run(DemoJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
         productRepository.save(new Product(null,"product 1",4200,3));
        productRepository.save(new Product(null,"product 2",8200,2));
        productRepository.save(new Product(null,"product 3",9800,1));
        List<Product> products =  productRepository.findAll();
        products.forEach(p->
                System.out.println(p.getName()));
        System.out.println("********************* FindBy Id = 1 (before update) **********\"");
        Product product=productRepository.findById(Long.valueOf(1)).get();
        System.out.println(product.toString());
        // Update Product
        product.setName("Smart Watch");
        product.setPrice(3499);
        product.setQuantity(23);
        productRepository.save(product);
        System.out.println("After update : "+productRepository.findById(Long.valueOf(1)).get().toString());


        System.out.println("******************** Find By name contains 1 **********\"");

        List<Product> c =  productRepository.findByNameContains("1");
        c.forEach(p->
        {
            System.out.println(p.toString());
        });

        System.out.println("******************* REPO SERACH **********\"");
        List<Product> d =  productRepository.search("%1%");
        d.forEach(p->
        {
            System.out.println(p.toString());
        });

        System.out.println("********************* PRICE GREATER THAN 5000 **********");
        List<Product> l =  productRepository.findByPriceGreaterThan(5000);
        l.forEach(p->
        {
            System.out.println(p.toString());
        });


        System.out.println("********************* By price = 5000 **********");
        List<Product> m =  productRepository.searchByPrice(5000.00);
        m.forEach(p->
        {
            System.out.println(p.toString());
        });

        // Suppression Product
        Product product1 =productRepository.findById(Long.valueOf(3)).get();
        if(product1 != null){
            System.out.println("Le produit "+product1.getName()+" va être supprimé !");
            productRepository.delete(product1);
            System.out.println("Produit supprimé avec succès !");
        }else
            System.out.println("Produit introuvable !");
    }
}
