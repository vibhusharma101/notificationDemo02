package com.vibhusharma.notificationdemo02;

public class UserId {



    public String userId;


    public <T extends UserId> T withId(final String id)
    {
        this.userId=id;

        return (T)this;



    }















}
