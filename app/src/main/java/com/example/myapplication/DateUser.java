package com.example.myapplication;

//本地保存账号密码

public class DateUser {
    String[] Usersname= new String[200];
    int size1=0;
    int size2=0;
    String[] Password= new String[200];
    public void register(String username, String password){
        Usersname[size1]=username;
        Password[size2]=password;
        size2++;
        size1++;
    }
    public boolean login(String username,String password){
        for (int i=0;i<size1;i++){
            if (Usersname[i].equals(username)){
                if (Password[i].equals(password)){
                    return true;
                }
            }
        }
        return false;
    }
    public DateUser(){}
    private static DateUser instance;
    public static DateUser getInstance(){
        if (instance==null) {
            instance = new DateUser();
        }
        return  instance;
    }
}
