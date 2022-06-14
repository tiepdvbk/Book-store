package all;

public class USER {
    public static int id;
    public static String name;
    public static String uname;
    public static String sdt;
    public static String mail;
    public static String addres;
    private static String pass;

    public static String getPass() {
        return pass;
    }
    public static void setPass(String pass) {
        USER.pass = pass;
    }

}
