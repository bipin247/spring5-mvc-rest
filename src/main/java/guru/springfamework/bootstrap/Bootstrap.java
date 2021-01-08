package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @java.lang.Override
    public void run(java.lang.String... args) throws Exception {
        loadFruits();

        loadCustomers();

        loadVendors();
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

        System.out.println("-----> Category Data Loaded = " + categoryRepository.count());
    }

    private void loadCustomers() {
        customerRepository.save(new Customer(1L, "Bipin", "Patel"));
        customerRepository.save(new Customer(2L, "Adam", "Swindler"));
        customerRepository.save(new Customer(3L, "Stewart", "Angle"));
        customerRepository.save(new Customer(4L, "Boring", "747"));
        customerRepository.save(new Customer(5L, "Company", "Matter"));
        customerRepository.save(new Customer(6L, "Douglas", "Dolittle"));

        System.out.println("---------> Customer Data Loaded = " + customerRepository.count());

    }

    private void loadVendors() {
        Vendor vendor = new Vendor();
        vendor.setName("Western Tasty Fruits Ltd.");
        vendor.setId(1l);
        vendorRepository.save(vendor);
        vendor.setName("Exotic Fruits Company");
        vendor.setId(2l);
        vendorRepository.save(vendor);
        vendor.setName("Home Fruits");
        vendor.setId(3l);
        vendorRepository.save(vendor);
        vendor.setName("Fun Fresh Fruits Ltd.");
        vendor.setId(4l);
        vendorRepository.save(vendor);
        vendor.setName("Nuts for Nuts Company");
        vendor.setId(5l);
        vendorRepository.save(vendor);

        System.out.println("---------> Vendors Data Loaded = " + vendorRepository.count());

    }
}