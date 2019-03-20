package com.Mystiko.smartmeterautomation;


    public class User {
        String username,password,name;
        int age;
        //two constructors for User
        public User(String Name,String UserName,String Password,int age){
            this.age=age;
            this.name=Name;
            this.username=UserName;
            this.password=Password;
        }

        public User(String UserName,String Password){
            this.username=UserName;
            this.password=Password;
            this.age = -1;//default value
            this.name="";//default value
        }
    }

