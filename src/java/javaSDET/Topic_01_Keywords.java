package javaSDET;
//Class kế thừa class, interface kế thừa interface -->extend
//Class kế thừa interface -->implement
public class Topic_01_Keywords extends Topic_06 implements Topic_02 {
    //Chỉ có non abstract method không có abstract method
    //Khỏi tạo bình thường, cho phép kế thừa


    //Data type
    char c = 'A';
    byte bNumber = 10;
    short sNumber = 100;
    long lNumber = 55096;
    int iNumber = 1200;
    double dNumber = 1.2424;
    float fNumber = 23245656;
    boolean maridStatus = true;

    //Access Modifier
    //Variable
    private String studentName ="";
    String studentAddress ="";
    protected  int studentAge = 30;
    public double studentPoint = 9.5;

    @Override
    public void  clearSudents(){
        //Tự implement ở đây
    }

}
