package ra;

import ra.entity.Categories;
import ra.entity.Product;

import java.util.*;

public class ShopManagement {
    static List<Categories> categoriesList = new ArrayList<>();
    static List<Product> productList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        do {
            System.out.println("************SHOP MANAGEMENT***************");
            System.out.println("1.Quản lý danh mục sản phẩm");
            System.out.println("2.Quản lý sản phẩm");
            System.out.println("3.Thoát");
            System.out.println("Sự lựa chọn của bạn là: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    ShopManagement.catalogMenu(scanner);
                    break;
                case 2:
                    ShopManagement.productMenu(scanner);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-3");
            }
        }while (true);
    }
    public static void catalogMenu(Scanner scanner){
        boolean isExit = true;
        do {
            System.out.println("*****************CATALOG MANAGEMENT***************");
            System.out.println("1.Thêm mới danh mục");
            System.out.println("2.Hiển thị thông tin các danh mục");
            System.out.println("3.Cập nhật tên danh mục theo mã danh mục");
            System.out.println("4.Xóa danh mục theo mã danh mục (Danh mục chưa chứa sản phẩm)");
            System.out.println("5.Thoát");
            System.out.println("Lựa chọn của bạn là: ");
            int choiceCatalogMenu = Integer.parseInt(scanner.nextLine());
            switch (choiceCatalogMenu){
                case 1:
                    ShopManagement.inputCatalog();
                    break;
                case 2:
                    ShopManagement.displayCatalog();
                    break;
                case 3:
                    ShopManagement.updateCatalog();
                    break;
                case 4:
                    ShopManagement.deleteCatalog();
                    break;
                case 5:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1-5");
            }
        }while (isExit);
    }
    public static void inputCatalog(){
        Categories catalog = new Categories();
        catalog.inputData(scanner, categoriesList,productList);
        categoriesList.add(catalog);
    }
    public static void displayCatalog(){
        for (Categories catalog: categoriesList) {
            catalog.displayData();
        }
    }
    public static void updateCatalog(){
        if (categoriesList.size()==0){
            System.err.println("Không có danh mục.");
            return;
        }
        boolean isExit= true;
        System.out.println("Nhập mã danh mục muốn cập nhật :");
        do {
            int updateId = Integer.parseInt(scanner.nextLine());
            boolean checkId = false;
            for (Categories catalog : categoriesList) {
                if (catalog.getCatalogId()==updateId){
                    System.out.println("Nhập tên mới của danh mục :");
                    catalog.setCatalogName(scanner.nextLine());
                    System.out.println("Đã cập nhật tên danh mục thành công.");
                    checkId = true;
                    isExit = false;
                }
            }
            if (!checkId){
                System.err.println("Mã danh mục không tồn tại, vui lòng nhập lại.");
            }
        }while (isExit);
    }
    public static void deleteCatalog(){
        if (categoriesList.size()==0){
            System.err.println("Không có danh mục :");
            return;
        }
        boolean isExit = true;
        System.out.println("Nhập mã danh mục muốn xóa :");
        do {
            int deleteId = Integer.parseInt(scanner.nextLine());
            boolean checkProduct = false;
            boolean checkDeleteId = false;
            for (Product product: productList) {
                if (product.getCatalogId()==deleteId){
                    System.err.println("Trong danh mục có chứa sản phẩm, vui lòng không xóa danh mục.");
                    checkProduct = true;
                    isExit = false;
                    break;
                }
            }
            if (!checkProduct){
                for (Categories catalog : categoriesList) {
                    if (catalog.getCatalogId()==deleteId){
                        categoriesList.remove(catalog);
                        System.out.println("Đã xóa danh mục thành công.");
                        checkDeleteId = true;
                        isExit = false;
                        break;
                    }
                }
                if (!checkDeleteId){
                    System.err.println("Mã danh mục không tồn tại, vui lòng nhập lại.");
                }
            }
        }while (isExit);
    }
    public static void productMenu(Scanner scanner){
        boolean isExit = true;
        do {
            System.out.println("***************** PRODUCT MANAGEMENT**************");
            System.out.println("1. Thêm mới sản phẩm (Khi thêm cho phép chọn danh mục sản phẩm mà sản phẩm thuộc về)");
            System.out.println("2. Hiển thị thông tin sản phẩm");
            System.out.println("3. Cập nhật giá sản phẩm theo mã sản phẩm");
            System.out.println("4. Xóa sản phẩm theo mã sản phẩm");
            System.out.println("5. Sắp xếp sản phẩm theo giá sản phẩm tăng dần");
            System.out.println("6. Sắp xếp sản phẩm theo tên tăng dần");
            System.out.println("7. Thống kê số lượng sản phẩm theo danh mục sản phẩm");
            System.out.println("8. Tìm kiếm sản phẩm theo tên sản phẩm");
            System.out.println("9. Thoát ");
            System.out.println("Lựa chọn của bạn là: ");
            int choiceProductMenu = Integer.parseInt(scanner.nextLine());
            switch (choiceProductMenu){
                case 1:
                    ShopManagement.inputProduct();
                    break;
                case 2:
                    ShopManagement.displayProduct();
                    break;
                case 3:
                    ShopManagement.updatePriceByProductId();
                    break;
                case 4:
                    ShopManagement.deleteProductByProductId();
                    break;
                case 5:
                    ShopManagement.sortProductByPrice();
                    break;
                case 6:
                    ShopManagement.sortProductByProductName();
                    break;
                case 7:
                    ShopManagement.getProductInCatalog();
                    break;
                case 8:
                    ShopManagement.searchProductByName();
                    break;
                case 9:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-9");
            }
        }while (isExit);
    }
    public static void inputProduct(){
        Product productNew = new Product();
        productNew.inputData(scanner, categoriesList,productList);
        System.out.println("********CATALOG***********");
        for (int i = 0; i < categoriesList.size(); i++) {
            System.out.printf("%d. %s\n",i+1,categoriesList.get(i).getCatalogName());
        }
        System.out.println("Chọn danh mục: ");
        int choiceCatalog = Integer.parseInt(scanner.nextLine());
        int catalogIdChoice = categoriesList.get(choiceCatalog-1).getCatalogId();
        productNew.setCatalogId(catalogIdChoice);
        productList.add(productNew);
    }
    public static void displayProduct(){
        for (Product product: productList) {
            product.displayData();
        }
    }
    public static void updatePriceByProductId(){
        if (productList.size()==0){
            System.err.println("Không có sản phẩm nào.");
            return;
        }
        boolean isExit = true;
        System.out.println("Nhập mã sản phẩm muốn cập nhật :");
        do {
            String updateId = scanner.nextLine();
            boolean checkId = false;
            for (Product productUpdate : productList) {
                if (productUpdate.getProductId().equals(updateId)){
                    System.out.println("Nhập giá mới của sản phẩm :");
                    productUpdate.setPrice(Float.parseFloat(scanner.nextLine()));
                    System.out.println("Đã cập nhật giá sản phẩm thành công.");
                    checkId = true;
                    isExit = false;
                }
            }
            if (!checkId){
                System.err.println("Mã sản phẩm không tồn tại, vui lòng nhập lại.");
            }
        }while (isExit);
    }
    public static void deleteProductByProductId(){
        if (productList.size()==0){
            System.err.println("Không có sản phẩm nào.");
            return;
        }
        boolean isExit = true;
        System.out.println("Nhập mã sản phẩm muốn xóa :");
        do {
            String deleteId = scanner.nextLine();
            boolean checkId = false;
            for (Product productDelete : productList) {
                if (productDelete.getProductId().equals(deleteId)){
                    productList.remove(productDelete);
                    System.out.println("Đã xóa sản phẩm thành công.");
                    checkId = true;
                    isExit = false;
                    break;
                }
            }
            if (!checkId){
                System.err.println("Mã sản phẩm không tồn tại, vui lòng nhập lại.");
            }
        }while (isExit);
    }
    public static void sortProductByPrice(){
        productList.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return (int) (o1.getPrice() - o2.getPrice());
            }
        });
        System.out.println("Đã sắp xếp sản phẩm theo giá sản phẩm tăng dần.");
    }
    public static void sortProductByProductName(){
        productList.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getProductName().compareTo(o2.getProductName());
            }
        });
        System.out.println("Đã sắp xếp sản phẩm theo tên tăng dần.");
    }
    public static void getProductInCatalog(){
        if (productList.size()==0){
            System.err.println("Không có sản phẩm nào.");
            return;
        }
        int[] arrCatalog = new int[categoriesList.size()];
        int arrCatalogIndex =0;
        for (int i = 0; i < categoriesList.size(); i++) {
            boolean check = false;
            for (int j = i+1; j < categoriesList.size() ; j++) {
                if ((categoriesList.get(j).getCatalogId())==categoriesList.get(i).getCatalogId()) {
                    check= true;
                    break;
                }
            }
            if (!check){
                arrCatalog[arrCatalogIndex]= categoriesList.get(i).getCatalogId();
                arrCatalogIndex++;
            }
        }

        int[] arrCountCatalog = new int[arrCatalogIndex];

        for (int i = 0; i < arrCatalogIndex; i++) {
            int cnt = 0;
            for (Product product : productList) {
                if (arrCatalog[i] == product.getCatalogId()) {
                    cnt++;
                }
            }
            arrCountCatalog[i]= cnt;
        }
        for (int i = 0; i < arrCatalogIndex; i++) {
            System.out.println("Danh mục "+arrCatalog[i]+" có "+arrCountCatalog[i]+" sản phẩm");
        }
    }
    public static void searchProductByName(){
        System.out.println("Nhập vào tên sản phẩm cần tìm kiếm :");
        String name = scanner.nextLine();
        boolean isSearch = false;
        System.out.println("Thông tin các sản phẩm tìm kiếm : ");
        for (Product product : productList) {
            if (product.getProductName().toUpperCase().contains(name.toUpperCase())){
                product.displayData();
                isSearch= true;
            }
        }
        if (!isSearch){
            System.err.println("Không tìm thấy sản phẩm cần tìm kiếm.");
        }
    }
}
