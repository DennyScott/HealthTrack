package business;

import java.io.Serializable;


class UserData implements Serializable, ApplicationConstants {
    private String name;
    private int age;
    private int weight;
    private int height;
    private int gender;//0 male, 1 female
    private boolean set;
    UserData(){
        setSet(false);
        setName("");
        setAge(0);
        setWeight(0);
        setHeight(0);
        setGender(1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public boolean isSet() {
        return set;
    }

    public void setSet(boolean set) {
        this.set = set;
    }
}
