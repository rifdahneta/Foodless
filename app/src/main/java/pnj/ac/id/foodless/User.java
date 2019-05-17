package pnj.ac.id.foodless;

public class User {

    private String full_name;
    private String address;
    private String phone;
    private String email;
    private String password;

    public User(){

    }

    public User (String full_name, String address, String phone, String email, String password){
        this.full_name = full_name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public String getFullName(){
        return full_name;
    }

    public void setFullName(String full_name){
        this.full_name = full_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(){
        this.address = address;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(){
        this.phone = phone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(){
        this.password = password;
    }
}
