package org.sang.Utils;

import static org.springframework.util.DigestUtils.md5DigestAsHex;

public class MD5Util {

    public static String md5(String s){
        return md5DigestAsHex(s.getBytes());
    }

    public static void main(String[] args) {
        int id = 21172111;
        String  pwd = "123";

        int uid = 21172122;
        String password = "456";

        System.out.println(md5(id+pwd));
        System.out.println(md5(uid+password));
    }
}
