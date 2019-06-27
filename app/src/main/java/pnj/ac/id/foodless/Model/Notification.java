package pnj.ac.id.foodless.Model;

public class Notification {

    private String userid;
    private String text;
    private String orderid;
    private boolean isorder;

    public Notification(String userid, String text, String orderid, boolean isorder) {
        this.userid = userid;
        this.text = text;
        this.orderid = orderid;
        this.isorder = isorder;
    }

    public Notification() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public boolean isIsorder() {
        return isorder;
    }

    public void setIsorder(boolean isorder) {
        this.isorder = isorder;
    }
}
