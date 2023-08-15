package ra.run;

import ra.entity.Categories;
import ra.entity.Product;

import java.util.*;

public class ShopManagement {
    static  List<Categories> categoriesList = new ArrayList<>();

    static List<Product>productList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        listDataCategories();
        listDataProduct();
        categorieStatus();
        do {
            menuShopManager();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    CatalogManager(scanner);
                    break;
                case 2:
                    producManager(scanner);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default: break;
            }
        }while (true);

    }

    public static void CatalogManager(Scanner scanner){
        boolean isExit = true;
        do {
            menuCatalogManager();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    Categories categories = new Categories();
                    categories.inputData(categoriesList);
                    categoriesList.add(categories);
                    System.out.println("Đã thêm xong.");
                    break;
                case 2:
                    Iterator<Categories> categoriesIterator  = categoriesList.iterator();
                    while (categoriesIterator.hasNext()){
                        Categories categories1 = categoriesIterator.next();
                        categories1.displayData();
                    }
                    break;
                case 3:
                    System.out.println("Mã danh mục cần cập nhật:");
                    int catalogId = Integer.parseInt(scanner.nextLine());
                    boolean isSearch =false;
                    Iterator<Categories> categoriesIterator2  = categoriesList.iterator();
                    while (categoriesIterator2.hasNext()){
                        Categories categories2 = categoriesIterator2.next();
                        int testId = categories2.getCatalogId();
                        if (catalogId ==testId){
                            System.out.printf("Tên sản phẩm cũ: $s\n",categories2.getCatalogName());
                            System.out.println("Cập nhật lại tên:");
                            String newName = scanner.nextLine();
                            categories2.setCatalogName(newName);
                            System.out.println("Đã cập nhật xong.");
                            isSearch = true;
                            break;
                        }
                    }
                    if (!isSearch){
                        System.out.println("Tên tìm kiếm không tồn tại.");
                    }
                    break;
                case 4:
                    System.out.println("Nhập mã danh mục muốn xóa: ");
                    int delCategories = Integer.parseInt(scanner.nextLine());
                    Iterator<Categories> categoriesIterator3  = categoriesList.iterator();
                    boolean isDel = false;
                    while (categoriesIterator3.hasNext()){
                        Categories categories3 = categoriesIterator3.next();
                        int test = categories3.getCatalogId();
                        if ( test== delCategories){

                            if (categories3.getProductArrayList()!=null){
                                categoriesList.remove(categories3);
                                isDel = true;
                            }else {
                                System.out.println("Danh mục đã có sản phẩm.");
                            }
                            break;
                        }
                    }
                    if (!isDel){
                        System.out.println("Không tồn tại mã danh mục");
                    }
                    break;
                case 5:
                    isExit = false;
                    break;
            }
        }while (isExit);
    }
    public static void  producManager(Scanner scanner){
        boolean isExit = true;
        do {
            menuProducManager();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    Product product = new Product();
                    product.inputData(productList);
                    product.listCategories(categoriesList);
                    productList.add(product);
                    System.out.println("Đã thêm xong.");
                    break;
                case 2:
                    Iterator<Product> productIterator = productList.iterator();
                    while (productIterator.hasNext()){
                        Product product1 = productIterator.next();
                        product1.displayData();
                    }
                    break;
                case 3:
                    System.out.println("Nhập mã sản phẩm :");
                    String productName = scanner.nextLine();
                    for (Product product3 : productList
                    ) {
                        if (product3.getProducId().toLowerCase().equals(productName.toLowerCase())){
                            System.out.printf("Giá bán cũ: %f \n", product3.getPrice());
                            System.out.println("Nhập giá bán mới : ");
                            float newPrice = Float.parseFloat(scanner.nextLine());
                            product3.setPrice(newPrice);
                        }
                    }
                    System.out.println("Đã cập nhật xong.");
                    break;
                case 4:
                    System.out.println("Nhập mã sản phẩm muốn xóa : ");
                    String delName = scanner.nextLine();
                    Iterator<Product> productIterator1 = productList.iterator();

                    while (productIterator1.hasNext()){
                        Product product1 = productIterator1.next();
                        if (product1.getProducId().toLowerCase().contains(delName.toLowerCase())){
                            productList.remove(product1);
                        }else {
                            System.out.println("Mã sản phẩm không tồn tại.");
                        }
                    }
                    break;
                case 5:
                    productList.sort(new Comparator<Product>() {
                        @Override
                        public int compare(Product o1, Product o2) {
                            return (int) (o1.getPrice()-o2.getPrice());
                        }
                    });
                    break;
                case 6:
                    productList.sort(new Comparator<Product>() {
                        @Override
                        public int compare(Product o1, Product o2) {
                            return o1.getProductName().compareTo(o2.getProductName());
                        }
                    });
                    break;
                case 7:
                    for (Categories cate : categoriesList) {
                        int cnt =0;
                        for (Product pro : productList) {
                            if (pro.getCatalogId()==cate.getCatalogId()){
                                cnt++;
                            }
                        }
                        System.out.printf("%s : %d \n", cate.getCatalogName(),cnt);
                    }
                    break;
                case 8:
                    System.out.println("Nhập tên cần tìm kiếm: ");
                    String searchName = scanner.nextLine();
                    Iterator<Product> productIterator2 = productList.iterator();
                    while (productIterator2.hasNext()){
                        Product product1 = productIterator2.next();
                        if (product1.getProductName().toLowerCase().contains(searchName.toLowerCase())){
                            product1.displayData();
                            break;
                        }
                    }
                    break;
                case 9:
                    isExit = false;
                    break;

            }
        }while (isExit);
    }

    public static void categorieStatus (){
        for (Categories categoies : categoriesList) {
            int cnt =0;
            for (Product pro : productList) {
                if (pro.getCatalogId()==categoies.getCatalogId()){
                    cnt++;
                }
            }
            if (cnt==0){
                categoies.setStatus(false);
            }else {
                categoies.setStatus(true);
            }
        }
    }

    public static void menuShopManager(){
        System.out.println("*************************SHOP MANAGEMENT***************");
        System.out.println("1. Quản lý danh mục sản phẩm");
        System.out.println("2. Quản lý sản phẩm");
        System.out.println("3. Thoát");
    }
    public static void menuCatalogManager(){
        System.out.println("***************** CATALOG MANAGEMENT**************");
        System.out.println("1. Thêm mới danh mục");
        System.out.println("2. Hiển thị thông tin các danh mục");
        System.out.println("3. Cập nhật tên danh mục theo mã danh mục");
        System.out.println("4. Xóa danh mục theo mã danh mục");
        System.out.println("5. Thoát ");
    }
    public static void menuProducManager(){
        System.out.println(" ***************** PRODUCT MANAGEMENT**************");
        System.out.println("1. Thêm mới sản phẩm ");
        System.out.println("2. Hiển thị thông tin sản phẩm");
        System.out.println("3. Cập nhật giá sản phẩm theo mã sản phẩm");
        System.out.println("4. Xóa sản phẩm theo mã sản phẩm");
        System.out.println("5. Sắp xếp sản phẩm theo giá sản phẩm tăng dần");
        System.out.println("6. Sắp xếp sản phẩm theo tên tăng dần");
        System.out.println("7. Thống kê số lượng sản phẩm theo danh mục sản phẩm");
        System.out.println("8. Tìm kiếm sản phẩm theo tên sản phẩm");
        System.out.println("9. Thoát (Quay lại Shop Management)");

    }


    public static void listDataCategories(){
        Categories categories = new Categories(01, "A",false);
        categoriesList.add(categories);
        categories = new Categories(02, "B",true);
        categoriesList.add(categories);
        categories = new Categories(03, "C",true);
        categoriesList.add(categories);
        categories = new Categories(04, "D",false);
        categoriesList.add(categories);
        categories = new Categories(05, "E",true);
        categoriesList.add(categories);
    }
    public  static  void listDataProduct(){
        Product product = new Product("P00001","A",10,"A++",01,true);
        productList.add(product);
        product = new Product("P00002","B",20,"B++",02,false);
        productList.add(product);
        product = new Product("P00003","C",30,"C++",03,true);
        productList.add(product);
        product = new Product("P00004","D",40,"D++",04,false);
        productList.add(product);
        product = new Product("P00005","E",50,"E++",05,true);

        productList.add(product);

    }
}
