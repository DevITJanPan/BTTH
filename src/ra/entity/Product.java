package ra.entity;

import ra.IShop;

import java.util.List;
import java.util.Scanner;

public class Product implements IShop<Product> {
    private String producId;
    private String productName;
    private float price;
    private  String title;
    private int catalogId;
    private boolean status;


    public String getProducId() {
        return producId;
    }

    public void setProducId(String producId) {
        this.producId = producId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Product() {
    }

    public Product(String producId, String productName, float price, String title, int catalogId, boolean status) {
        this.producId = producId;
        this.productName = productName;
        this.price = price;
        this.title = title;
        this.catalogId = catalogId;
        this.status = status;
    }
    @Override
    public void inputData(List<Product> listProduc) {
        Scanner scanner = new Scanner(System.in);
        boolean isCheckId = true;
        do {
            boolean isCheck = true;
            System.out.println("Mã sản phẩm :");
            String producId = scanner.nextLine();
            if (producId.length()>5){
                if (producId.charAt(0)=='P'){
                    for (Product prod : listProduc) {
                        if (!prod.producId.equalsIgnoreCase(producId)){
                            isCheck = false;
                        }
                    }
                }
            }
            if (!isCheck){
                this.producId = producId;
                isCheckId=false;
            }else {
                System.err.println("Mã sản phẩm đã tồn tại, vui lòng nhập lại.");
            }
        }while (isCheckId);

        boolean isCheckName = true;
        do {
            boolean isCheck = false;
            System.out.println("Tên sản phẩm:");
            String productName = scanner.nextLine();
            for ( Product element:listProduc) {
                if (element.productName.equalsIgnoreCase(productName)){
                    System.err.println("Mã đã tồn tại, vui lòng nhập lại.");
                    isCheck = true;
                }
            }
            if (!isCheck){
                this.productName = productName;
                isCheckName = false;
            }
        } while (isCheckName);

        do {
            System.out.println("Giá sản phẩm:");
            this.price = Float.parseFloat(scanner.nextLine());
        }while (this.price<0);

        System.out.println("Tiêu đề:");
        this.title = scanner.nextLine();


        do {
            System.out.println("Trạng thái: ");
            System.out.println("1. Đã bán");
            System.out.println("2. Đang bán");
            byte choice = Byte.parseByte(scanner.nextLine());
            switch (choice){
                case 1:
                    this.status = true;
                    return;
                case 2:
                    this.status = false;
                    return;
                default:
                    System.out.println("Chọn từ 1-2");
                    break;
            }
        }while (true);
    }

    public void listCategories(List<Categories> tList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Danh mục:");
        for (Categories cate: tList) {
            cate.displayData();
        }
        boolean isExit = true;
        do {
            this.catalogId = Integer.parseInt(scanner.nextLine());
            for (Categories cate: tList) {
                if (cate.getCatalogId()==this.catalogId){
                    isExit = false;
                    break;
                }else {
                    System.err.println("Mã không tồn  tại, vui lòng nhập  lại.");
                }
            }
        }while (isExit);
    }
    @Override
    public void displayData() {
        String statusProduct = this.status?"Còn hàng":" Hết hàng";

        System.out.printf("Mã sản phẩm: %s - Tên sản phẩm: %s - Giá: %f - Trạng thái: %s \n", this.producId, this.productName, this.price,statusProduct  );
    }
}
