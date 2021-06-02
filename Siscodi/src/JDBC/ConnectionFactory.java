/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JDBC;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Uma fábrica de conexões.
 *
 * @author Caique Godoy
 */
public class ConnectionFactory {

    /*
     * Este bloco estático será executado assim que esta classe for carregada,
     * sendo assim, será executado apenas uma vez.
     */
    static {
        try {
            /*
             * Carrega a classe com.mysql.jdbc.Driver, que é a implementação
             * do driver JDBC para o MySQL.
             */
            Class.forName( "com.mysql.jdbc.Driver" );

            // caso a classe não seja encontrada
        } catch ( ClassNotFoundException exc ) {

            /*
             * Como log usaremos o stacktrace das excessões, mas recomendo
             * que para um projeto real você utilize algum mecanismo de log
             * melhor, como o Log4J por exemplo.
             */
            exc.printStackTrace();

        }
    }

    /**
     * O método getConnection retorna uma conexão com o banco de dados baseado
     * nos parâmetros fornecidos.
     *
     * @param url "jdbc:mysql://10.190.81.160/pubmanagerdb" O endereço da base de dados.
     * @param usuario O usuário que tem permissão na base de dados especificada.
     * @param senha A senha do usuário especificado
     * @return Uma conexão com o banco de dados especificado na url.
     * @throws SQLException Caso ocorra algum problema durante a conexão.
     */
    public static Connection getConnection(
            String url ,
            String usuario,
            String senha ) throws SQLException {

        // retorna a conexão a partir do método getConnection de DriverManager
        return DriverManager.getConnection( url, usuario, senha );

    }

    /**
     * Obtém uma conexão para a base de dados sakila.
     *
     * @return Uma conexão para a base de dados sakila.
     */
    public static Connection getPubManagerConnection(){
        try{
        FileInputStream stream = new FileInputStream("ConDB.dll");
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        String linha = br.readLine();
        String Conexao = linha.substring(0, linha.indexOf('|'));
        String User = linha.substring(linha.indexOf('|')+1, linha.lastIndexOf('|'));
        String Senha = linha.substring(linha.lastIndexOf('|')+1, linha.length());
        return getConnection(
                Conexao,
                User,
                Senha);
        }catch(SQLException e){
            e.getMessage();
        }catch(IOException e){
            e.getMessage();
        }
        return null;
    }

}
