package ra;

import ra.entity.Categories;

import java.util.List;
import java.util.Scanner;

public interface IShop <T>{
     void inputData(List<T> tList);
     void displayData();
}
