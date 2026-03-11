import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class App {
    public static void main(String[] args) throws Exception {

        insert(1, "Enzo", "Sistemas de informação");
        insert(2,"Juliana","Comex");

        read();
        update(1, "Enzo Carvalho", "Computação");

        read();

        delete(1);

        read();


    }



    public static Connection getConnection()throws Exception{
        
        String url = "jdbc:postgresql://aws-0-us-west-2.pooler.supabase.com:6543/postgres?user=postgres.eoxordbymvqwcthaetpr&password=e6NMoibSWsoAZuh2";
       //String username = "postgres."
        //String password

        Connection con = DriverManager.getConnection(url);
        
        return con;
    }

    public static void insert(long id, String nome, String curso){
    String sql = "INSERT INTO aluno (id, nome, curso) VALUES (?, ?, ?)";

    try{
        Connection con = getConnection();

        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setLong(1, id);
        pstmt.setString(2, nome);
        pstmt.setString(3, curso);

        pstmt.executeUpdate();

        System.out.println("Registro inserido com sucesso!");

        con.close();

    }catch(Exception ex){
        ex.printStackTrace();
    }
}

public static void update(long id, String nome, String curso){
    String sql = "UPDATE aluno SET nome = ?, curso = ? WHERE id = ?";

    try{
        Connection con = getConnection();

        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setString(1, nome);
        pstmt.setString(2, curso);
        pstmt.setLong(3, id);

        pstmt.executeUpdate();

        System.out.println("Registro atualizado!");

        con.close();

    }catch(Exception ex){
        ex.printStackTrace();
    }
}

public static void delete(long id){
    String sql = "DELETE FROM aluno WHERE id = ?";

    try{
        Connection con = getConnection();

        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setLong(1, id);

        pstmt.executeUpdate();

        System.out.println("Registro removido!");

        con.close();

    }catch(Exception ex){
        ex.printStackTrace();
    }
}


    public static void read(){  
        String sql = "SELECT * FROM aluno";

        try{
            Connection con = getConnection();

            PreparedStatement pstmt = con.prepareStatement(sql);
            
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                long id = rs.getLong(1);
                String nome = rs.getString(2);
                String curso = rs.getString(3);
                System.out.println(id + ": " +nome +"- "+ curso);
            }

            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }
}
