package ru.netology.product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.netology.product.data.Book;
import ru.netology.product.data.Product;
import ru.netology.product.data.Smartphone;
import ru.netology.product.manager.ProductManager;
import ru.netology.product.repository.Repository;

public class ManagerTest {
    Repository repository = new Repository();
    ProductManager manager = new ProductManager(repository);

    Product book1 = new Book(1, "Harry Potter 1", 499, "J.K.Rowling");
    Product smartphone1 = new Smartphone(2, "Galaxy S22", 70000, "Samsung");
    Product book2 = new Book(3, "Harry Potter 2", 599, "J.K.Rowling");
    Product book3 = new Book(4, "Harry Potter 3", 699, "J.K.Rowling");
    Product smartphone2 = new Smartphone(5, "iPhone 13", 60000, "Apple");

    @Test
    public void AddProductToRepository() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);

        Product[] expected = {book1, book2, book3};
        Product[] actual = manager.findAll();

        assertArrayEquals(expected, actual);
    }


    @Test
    public void RemoveByIdWhenOneProduct() {
        manager.add(book1);
        manager.remove(1);
        Product[] expected = {};
        Product[] actual = manager.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldRemoveByIdWhenSeveralProducts() {
        manager.add(book1);
        manager.add(smartphone1);
        manager.add(book2);
        manager.add(smartphone2);

        manager.remove(3);

        Product[] expected = {book1, smartphone1, smartphone2};
        Product[] actual = manager.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void SearchByNameWithNoResults() {
        manager.add(book1);
        manager.add(smartphone1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone2);
        manager.searchBy("Xiaomi");

        Product[] expected = {};
        Product[] actual = manager.searchBy("Xiaomi");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void SearchByNameWithOneResult() {
        manager.add(book1);
        manager.add(smartphone1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone2);

        manager.searchBy("Samsung");

        Product[] expected = {smartphone1};
        Product[] actual = manager.searchBy("Samsung");
    }

    @Test
    public void SearchByNameWithSeveralResults() {
        manager.add(book1);
        manager.add(smartphone1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone2);

        manager.searchBy("Potter");

        Product[] expected = {book1, book2, book3};
        Product[] actual = manager.searchBy("Potter");

        assertArrayEquals(expected, actual);
    }
}
