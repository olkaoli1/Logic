package ru.netology.tasks;

public class ProductRepository {
    private Product[] products = new Product[0];

    public void add(Product product) {
        if (findById(product.getId()) != null) {
            throw new AlreadyExistsException("Product with ID " + product.getId() + " already exists");
        }
        Product[] tmp = new Product[products.length + 1];
        System.arraycopy(products, 0, tmp, 0, products.length);
        tmp[tmp.length - 1] = product;
        products = tmp;
    }

    public Product[] findAll() {
        return products;
    }

    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void removeById(int id) {
        Product product = findById(id);
        if (product == null) {
            throw new NotFoundException("Product with ID " + id + " not found");
        }

        Product[] tmp = new Product[products.length - 1];
        int index = 0;
        for (Product p : products) {
            if (p.getId() != id) {
                tmp[index++] = p;
            }
        }
        products = tmp;
    }
}
