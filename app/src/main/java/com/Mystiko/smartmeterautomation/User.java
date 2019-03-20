package com.Mystiko.smartmeterautomation;


    public class User {
        String username,password,name;
        int age;

        public User(String Name,String UserName,String Password,int age){
            this.age=age;
            this.name=Name;
            this.username=UserName;
            this.password=Password;
        }

        public User(String UserName,String Password){
            this.username=UserName;
            this.password=Password;
            this.age = -1;
            this.name="";
        }
    }

