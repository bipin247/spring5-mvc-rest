package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @java.lang.Override
    public void run(java.lang.String... args) throws Exception {
        loadFruits();

        System.out.println("-----> Category Data Loaded = " + categoryRepository.count() );

        loadCustomers();

        System.out.println("---------> Customer Data Loaded = " + customerRepository.count() );
    }

    private void loadFruits() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);
    }

    private void loadCustomers() {
        customerRepository.save(new Customer(1L,"Bipin","Patel"));
        customerRepository.save(new Customer(2L,"Adam","Swindler"));
        customerRepository.save(new Customer(3L,"Stewart","Angle"));
        customerRepository.save(new Customer(4L,"Boring","747"));
        customerRepository.save(new Customer(5L,"Company","Matter"));
        customerRepository.save(new Customer(6L,"Douglas","Dolittle"));
    }
}
