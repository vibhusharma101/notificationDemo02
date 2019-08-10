package com.vibhusharma.notificationdemo02;

public class Users extends UserId{

    private String name;
    private  String image;


    public Users(String name , String image)
    {
        this.name = name;
        this.image= image;

    }

    public Users()
    {

    }




    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
