package org.example;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;

public class ProductService {
    //TODO : DB 정보 본인 DB 정보로 바꿔서 사용
    private String dbURL = "jdbc:mysql://localhost:3306/Schema?serverTimezone=Asia/Seoul";//ex) "jdbc:mysql://localhost:3306/Schema?serverTimezone=Asia/Seoul";
    private String dbUser = "root";
    private String dbPasswd = "mypassword";
    private Connection conn;

    //TODO : 서버의 검증 용도로 비밀번호 입력
    private final String [] pw={"201710603", "201710604"};

    private Gson gson = new Gson();
    private HTTPResponseController hc;

    public ProductService() {
        hc = new HTTPResponseController();
    }

    public void connectDB() {
        //TODO : 본인 DB driver에 맞게 연결 메서드 작성 아래는 mysql에 해당하는 예시
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(dbURL, dbUser, dbPasswd);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void login(PrintWriter out,String requestBody) {
        String password = requestBody.split(":")[1].replace("\"", "").replace("}", "").replace("\r\n", "");

        for (int i = 0; i < pw.length; i++){
            if(pw[i].equals(password)) {
                //성공 Response 보냄
                hc.setSuccessLoginResponse(out);
                return;
            }
        }
        //실패 Response
        hc.setFailedResponse(out);
    }

    public void getProducts(PrintWriter out,String requestParam)  {
        connectDB();
        try {
            String content = "";
            PreparedStatement ps = conn.prepareStatement("select * from Products where admin_id=?");
            ps.setString(1, requestParam);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                content += gson.toJson(new Product(rs.getLong("order_id"), rs.getString("name"), rs.getString("status"),
                        rs.getString("created_at"))) + "\n";
            }

            //TODO : 상품 조회 Response를 보내는 코드를 작성하시오
            for(int i=0; i<pw.length; i++) {
                if(pw[i].equals(requestParam)) {
                    hc.setSuccessGetResponse(out, content);
                    return;
                }
            }
            //실패 response
            hc.setFailedResponse(out);
        } catch (SQLException e) {
            e.printStackTrace();
            hc.setFailedResponse(out);
        }finally{
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addProduct(PrintWriter out,String requestBody) {
        PreparedStatement ps;
        connectDB();
        try {
            ps = conn.prepareStatement("insert into Products(admin_id,name,status) values(?,?,?)");
            Product p = gson.fromJson(requestBody, Product.class);
            ps.setString(1, p.getAdminId());
            ps.setString(2, p.getName());
            ps.setString(3, p.getStatus());
            ps.executeUpdate();

            //TODO : 상품 추가 성공 Response를 보내는 코드를 작성하시오
            for(int i=0; i<pw.length; i++) {
                if(pw[i].equals(p.getAdminId())) {
                    hc.setSuccessPostResponse(out);
                    return;
                }
            }
            //실패 response
            hc.setFailedResponse(out);
        } catch (SQLException e) {
            e.printStackTrace();
            hc.setFailedResponse(out);
        }finally{
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void updateProduct(PrintWriter out,String requestBody){
        PreparedStatement ps;
        connectDB();
        try {
            ps = conn.prepareStatement("update products set name=?,status=? where order_id=?");
            Product p = gson.fromJson(requestBody, Product.class);
            ps.setString(1, p.getName());
            ps.setString(2, p.getStatus());
            ps.setLong(3, p.getOrderId());
            ps.executeUpdate();

            //TODO : 상품 update 성공 Response를 보내는 코드를 작성하시오(PUT 또는 PATCH)
            hc.setSuccessPatchResponse(out);

        } catch (SQLException e) {
            e.printStackTrace();
            hc.setFailedResponse(out);
        }finally{
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteProduct(PrintWriter out,String requestParam) {
        PreparedStatement ps;
        connectDB();
        try {
            ps = conn.prepareStatement("delete from products where order_id=?");
            ps.setString(1, requestParam);
            ps.executeUpdate();

            //TODO : 상품 삭제 성공 Response를 보내는 코드를 작성하시오
            hc.setSuccessDeleteResponse(out);
        } catch (SQLException e) {
            e.printStackTrace();
            hc.setFailedResponse(out);
        }finally{
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void setDefaultResponse(PrintWriter out) {
        //TODO : 실패  Response를 보내는 코드를 작성하시오
        hc.setFailedResponse(out);
    }

}
