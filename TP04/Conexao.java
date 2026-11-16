//João Victor dos Santos, Luis Tiago Ferreira

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {

    private static final String URL = "jdbc:sqlite:aulajava.db";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver SQLite não encontrado: " + e.getMessage());
        }
        return DriverManager.getConnection(URL);
    }

    public static void inicializarBanco() {
        String sqlCargos = "CREATE TABLE IF NOT EXISTS tbcargos ("
                + "cd_cargo INTEGER PRIMARY KEY, "
                + "ds_cargo VARCHAR(20)"
                + ");";

        String sqlFuncs = "CREATE TABLE IF NOT EXISTS tbfuncs ("
                + "cod_func NUMERIC(9, 0) PRIMARY KEY, "
                + "nome_func VARCHAR(30), "
                + "sal_func DECIMAL(10, 2), "
                + "cod_cargo INTEGER, "
                + "FOREIGN KEY (cod_cargo) REFERENCES tbcargos(cd_cargo)"
                + ");";

        String insertCargos = "INSERT OR IGNORE INTO tbcargos (cd_cargo, ds_cargo) VALUES "
                + "(1, 'Administrativo'), "
                + "(2, 'Desenvolvedor'), "
                + "(3, 'Analista');";

        String insertFuncs = "INSERT OR IGNORE INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES "
                + "(101, 'Marcelo', 2000.00, 1), "
                + "(102, 'Marcelo Augusto', 3500.00, 2), "
                + "(103, 'Mariana Silva', 4200.00, 3), "
                + "(104, 'João Victor', 2800.00, 1);";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sqlCargos);
            stmt.execute(sqlFuncs);
            stmt.execute(insertCargos);
            stmt.execute(insertFuncs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}