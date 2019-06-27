package pnj.ac.id.foodless.Model;

public class User {

    private String full_name;
    private String address;
    private String phone;
    private String email;
    private String password;
//    private String role;

    public User(String full_name, String address, String phone, String email, String password, String role) {
        this.full_name = full_name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
    //    this.role = role;
    }

    public User(String fullName, String alamat, String phoneNo, String eMail, String pass) {

    }

    public User(){

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

//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    public String getRole() {
//        return role;
//    }

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