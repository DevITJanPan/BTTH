package ra.entity;
import ra.IShop;
import java.util.List;
import java.util.Scanner;

public class Categories implements IShop<Categories> {
    private int catalogId;
    private String catalogName;
    private boolean status;
    private List<Product> productArrayList;

    public List<Product> getProductArrayList() {
        return productArrayList;
    }

    public void setProductArrayList(List<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public Categories() {
    }

    public Categories(List<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }

    public Categories(int catalogId, String catalogName, boolean status) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.status = status;
    }
    @Override
    public void inputData(List<Categories> list) {
        Scanner scanner = new Scanner(System.in);
        boolean isCheckId = true;
        do {
            boolean isCheck = false;
            System.out.println("Mã danh Mục");
            int cateId = Integer.parseInt(scanner.nextLine());
            for ( Categories element:list) {
                if (element.getCatalogId()==cateId){
                    System.err.println("Mã đã tồn tại,vui lòng nhập  lại.");
                    isCheck = true;
                }
            }
            if (!isCheck){
                this.catalogId = cateId;
                isCheckId = false;
            }
        } while (isCheckId);

        boolean isCheckName = true;
        do {
            boolean isCheck = false;
            System.out.print("Nhập tên danh Mục");
            String catalogName = scanner.nextLine();
            for ( Categories element:list) {
                if (element.getCatalogName().equalsIgnoreCase(catalogName)){
                    System.err.println("Tên danh mục đã tồn tại, vui lòng nhập lại.");
                    isCheck = true;
                }

            }
            if (!isCheck){
                this.catalogName = catalogName;
                isCheckName = false;
            }
        } while (isCheckName);

    }


    @Override
    public void displayData() {
        String displayStatus = this.status?"Có hàng":"Hết hàng";
        System.out.printf("Mã danh mục: %d - Tên danh mục: %s - Trạng thái: %s  \n ", this.catalogId, this.catalogName, displayStatus);
    }
}
