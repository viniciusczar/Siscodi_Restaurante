/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clientes.clientes;
import Delivery.ClasseDelivery;
import Delivery.ClasseVendaDelivery;
import Funcionario.ClasseFuncionario;
import Main.ClasseVendaMesa;
import Produtos.ClasseProduto;
import bemajava.BemaString;
import bemajava.Bematech;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aluno
 */
public class ClasseDAO {
    
    private DefaultTableModel model;
    private Connection con;
    private String Conexao = "";
    private String User = "";
    private String Senha = "";
    private PreparedStatement stm;
    private ResultSet rs;
//    private ClasseDelivery d;
//    private final String Conexao = "jdbc:mysql://10.190.81.160/pubmanagerdb";
//    private final String Conexao = "jdbc:mysql://localhost/pubmanagerdb";
//    private final String User = "senai";
//    private final String User = "root";
//    private final String Senha = "senai";
//    private final String Senha = "pub123";
    
    public void conectar() {
        
        try{
        FileInputStream stream = new FileInputStream("ConDB.dll");
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        String linha = br.readLine();
        Conexao = linha.substring(0, linha.indexOf('|'));
        User = linha.substring(linha.indexOf('|')+1, linha.lastIndexOf('|'));
        Senha = linha.substring(linha.lastIndexOf('|')+1, linha.length());
        
        }catch(Exception e){
            e.getMessage();
        }
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(Conexao,User,Senha);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void alteraStatusPedido(ClasseVendaDelivery de){
        conectar();
        try{
            stm = con.prepareStatement("UPDATE delivery "+
                    "set status = ? where idDelivery = ?");
            stm.setString(1, "FECHADO");
            stm.setInt(2, de.getIdDelivery());
            
            stm.execute();
            
            stm.close();
            con.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public boolean transferePedidosSelect(ClasseVendaDelivery de) {
        conectar();
        try{
            stm = con.prepareStatement("SELECT idDelivery, totalPedido, dataVenda FROM delivery WHERE (idDelivery LIKE ?)");
            stm.setInt(1, de.getIdDelivery());
            
            rs = stm.executeQuery();
            if (rs.next()){
                de.setIdDelivery(rs.getInt("idDelivery"));
                de.setTotalPedido(rs.getFloat("totalPedido"));
                de.setDataVenda(rs.getString("dataVenda"));
                return true;
            }else{
                System.out.println("");
                //JOptionPane.showMessageDialog(null, "Não existe nenhum cliente\n"+
                //        "Cadastrado com este Documento","Cadastro De Clientes",JOptionPane.INFORMATION_MESSAGE);
            }
            rs.close();
            stm.close();
            con.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } 
        return false;
        
    }
    
    public boolean transferePedidosInsert(ClasseVendaDelivery de){
        
        conectar();
        try{
            String query = "INSERT INTO vendas (idDelivery, totalVenda, dataVenda) values (?,?,?)";
            stm = con.prepareStatement(query);
            stm.setInt(1, de.getIdDelivery());
            stm.setFloat(2, de.getTotalPedido());
            stm.setString(3,de.getDataVenda());
            
            stm.execute();
            
            stm.close();
            con.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return false;
    }
    
    public boolean login(ClasseFuncionario f){
        conectar();
        try{
            stm = con.prepareStatement("select * from usuarios where Login = ? and Senha = ?");
            stm.setString(1, f.getLogin());
            stm.setString(2,f.getSenha());
            rs = stm.executeQuery();
            if (rs.next()){
                return true;
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public String retornaICMS(int x){
        try {
            conectar();
            stm = con.prepareStatement("select icms from produtos where id = ?");
            stm.setInt(1,x);
            rs = stm.executeQuery();
            rs.next();
            return ("0"+rs.getString("icms").replace(".", "")+"0");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return "";
    }
    
    public void cadastraICMS(){
        BemaString aux = new BemaString();
        Bematech.RetornoAliquotas(aux);
        String[] aliquotas = new String[20];
        int i = 0,i2 = 0;
        if (!aux.getBuffer().equals("")){
            do{
                aliquotas[i] = aux.getBuffer().substring(i2, i2+4);
                i++;
                i2 = i2+5;
            }while(i2 <= 75);
        }                    
        
      try {
            conectar();
            stm = con.prepareStatement("select distinct icms from produtos");
            rs = stm.executeQuery();
            while (rs.next())
            {
                for (int i3 = 0; i3 < 20;i3++){
                    if (aliquotas[i3].equals("0"+rs.getString("icms").replace(".", "")+"0")){
                        return;
                    }else{
                        i3 = 21;
                        System.out.println(Bematech.ProgramaAliquota("0"+
                                rs.getString("icms").replace(".", "")+"0",0));
                    }
                }
            }
        
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }  
    }
    
    public String retornaLogado(ClasseFuncionario f){
        String x = "";
        conectar();
        try{
            /*stm = con.prepareStatement("Select nome from funcionarios where idFuncionario ="+
                    " (select idFuncionario from usuarios where Login = ?)");*/
            stm = con.prepareStatement("select idFuncionario from usuarios where Login = ?");
            stm.setString(1, f.getLogin());
            rs = stm.executeQuery();
            if (rs.next()){
                x = rs.getString("nome");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return x;
    }
    
    public DefaultTableModel preencheTabela(String x) {
        try {
            conectar();
            stm = con.prepareStatement(x);
            rs = stm.executeQuery();

            int colunas = rs.getMetaData().getColumnCount();

            model = new DefaultTableModel() {
                public boolean isCellEditable(int linha, int coluna) {
                    return false;
                }
            };

            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }
            model.setColumnCount(0);

            for (int aux = 1; aux <= colunas; aux++) {
                model.addColumn(rs.getMetaData().getColumnName(aux).toUpperCase());
            }

            while (rs.next()) {
                Object[] linha = new Object[colunas];
                for (int i = 1; i <= colunas; i++) {
                    linha[i - 1] = rs.getObject(i);
                }
                model.insertRow(rs.getRow() - 1, linha);
            }

            return model;

        } catch (Exception e) {
            System.out.println(e);
        }
        return model;
    }
    public DefaultTableModel preencheTabelaPesquisa(String x) {
        try {            
            conectar();
            stm = con.prepareStatement(x);
            rs = stm.executeQuery();
           
           //codigo oriignal
            int colunas = rs.getMetaData().getColumnCount();
          
            model = new DefaultTableModel() {
                public boolean isCellEditable(int linha, int coluna) {
                    return false;
                }
            };

            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }
            model.setColumnCount(0);

            for (int aux = 1; aux <= colunas; aux++) {
                model.addColumn(rs.getMetaData().getColumnName(aux).toUpperCase());
            }

            while (rs.next()) {
                Object[] linha = new Object[colunas];
                for (int i = 1; i <= colunas; i++) {
                    linha[i - 1] = rs.getObject(i);
                }
                model.insertRow(rs.getRow() - 1, linha);
            }

            return model;

        } catch (Exception e) {
            System.out.println(e);
        }
        return model;
    }
    
    public int ultimoID(String x){
        conectar();
        int i = 0;
        try{
            stm = con.prepareStatement(x);
            rs = stm.executeQuery();
            if (rs.next()){
                i = rs.getInt(1);
                i++;
            }
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return i;
    }
    
    public void insereProduto(ClasseProduto p){
        conectar();
        try{
            String query = "Insert into produtos (descricao,preco,aliquota,icms,categoria,marca,quantidade) "+
                "values (?,?,?,?,?,?,?)";
            stm = con.prepareStatement(query);
            stm.setString(1, p.getDescricao());
            stm.setFloat (2, p.getPreco());
            stm.setString(3,p.getAliquota());
            stm.setFloat (4, p.getICMS());
            stm.setString(5,p.getCategoria());
            stm.setString(6,p.getMarca());
            stm.setInt(7, p.getQuantidade());
            
            stm.execute();
            
            stm.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Produto Salvo Com Sucesso","Cadastro de Produtos",
                    JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public void insereFuncionario(ClasseFuncionario f){
        conectar();
        try{
            String query = "Insert into funcionarios (funcao,nome,cpf,telefone) "+
                "values (?,?,?,?)";
            stm = con.prepareStatement(query);
            stm.setString(1, f.getFuncao().toUpperCase());
            stm.setString(2, f.getNome().toUpperCase());
            stm.setString(3, f.getCpf());
            stm.setString(4,f.getTelefone());
            stm.execute();

            stm = con.prepareStatement("Select MAX(IdFuncionario) from funcionarios");
            rs = stm.executeQuery();
            rs.next();
            f.setID(rs.getInt(1));
            
            rs.close();
            stm.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Funcionário Salvo Com Sucesso","Cadastro de Funcionários",
                    JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void insereUsuario(ClasseFuncionario f){
        conectar();
        try{
            String query = "Insert into usuarios (Senha,Login,IdFuncionario)"+
                "values (?,?,?)";
            stm = con.prepareStatement(query);
            stm.setString(1, f.getSenha().toUpperCase());
            stm.setString(2, f.getLogin().toUpperCase());
            stm.setInt(3, f.getID());
            
            stm.execute();
            
            stm.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Usuário Salvo Com Sucesso","Cadastro de Funcionários",
                    JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
      public void insereDelivery(ClasseDelivery d){
        conectar();
        try{
            String query = "Insert into delivery (idCliente,telefone,obs,endereco,totalPedido) "+
                "values (?,?,?,?,?,?)";
            stm = con.prepareStatement(query);
            stm.setInt(1, d.getIdCliente());
            stm.setString(2, d.getTelefone());
            stm.setString(3, d.getObs());
            stm.setString(4, d.getEndereco());
            stm.setFloat(5, d.getTotalPedido());
            stm.execute();
            
            stm = con.prepareStatement("Select MAX(idDelivery) from delivery");
            rs = stm.executeQuery();
            rs.next();
            d.setIdDelivery(rs.getInt(1));
            rs.close();
            
            query = "Insert into produtodelivery (precoProduto,qtdProduto,idDelivery,idProdutos) "+
                "values (?,?,?,?)";
            stm = con.prepareStatement(query);
            stm.setFloat(1, d.getPrecoProduto());
            stm.setInt(2, d.getQtdProduto());
            stm.setInt(3, d.getIdDelivery());
            stm.setInt(4, d.getIdProduto());
            stm.execute();

            stm.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Cliente Salvo Com Sucesso","Cadastro de Clientes",
                    JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void insereCliente(clientes c){
        conectar();
        try{
            String query = "Insert into clientes (nome,endereco,telefone,cpf,cidade) "+
                "values (?,?,?,?,?)";
            stm = con.prepareStatement(query);
            stm.setString(1, c.getNome().toUpperCase());
            stm.setString(2, c.getEndereco().toUpperCase());
            stm.setString(3, c.getTelefone());
            stm.setString(4, c.getCpf());
            stm.setString(4, null);
            stm.setString(5, c.getCidade().toUpperCase());
            stm.execute();

            stm.close();
            con.close();
//            JOptionPane.showMessageDialog(null, "Cliente Salvo Com Sucesso","Cadastro de Clientes",
//                    JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public boolean buscaFuncionario(ClasseFuncionario f){
        conectar();
        try{
            stm = con.prepareStatement("select * from funcionarios where cpf = ?");
            
            stm.setString(1, f.getCpf());
            
            rs = stm.executeQuery();
            if (rs.next()){
                f.setNome(rs.getString("Nome"));
                f.setTelefone(rs.getString("telefone"));
                f.setFuncao(rs.getString("funcao"));
                f.setID(rs.getInt("IdFuncionario"));
                
                stm = con.prepareStatement("Select * from usuarios where IdFuncionario = ?");
            
                stm.setInt(1, f.getID());
                rs = stm.executeQuery();
            
                if(rs.next()){
                   f.setLogin(rs.getString("Login"));
                   f.setSenha(rs.getString("Senha")); 
                }
            
            return true;
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        } 
        return false;
     }
    
    public boolean buscaPedido(clientes c){
        conectar();
        try{
            stm = con.prepareStatement("Select * from clientes where idClientes = ?");
            stm.setInt(1, c.getIdCliente());
            
            rs = stm.executeQuery();
            if (rs.next()){
                c.setNome(rs.getString("nome"));
                c.setEndereco(rs.getString("endereco"));
                c.setTelefone(rs.getString("telefone"));
                return true;
            }else{
                System.out.println("");
                //JOptionPane.showMessageDialog(null, "Não existe nenhum cliente\n"+
                //        "Cadastrado com este Documento","Cadastro De Clientes",JOptionPane.INFORMATION_MESSAGE);
            }
            rs.close();
            stm.close();
            con.close();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        } 
        return false;
     }
    
    public boolean buscaCliente(clientes c){
        conectar();
        try{
            stm = con.prepareStatement("Select * from clientes where telefone = ?");
            stm.setString(1, c.getTelefone());
            
            rs = stm.executeQuery();
            if (rs.next()){
                c.setIdCliente(rs.getInt("IdClientes"));
                c.setNome(rs.getString("Nome"));
                c.setEndereco(rs.getString("endereco"));
                c.setCidade(rs.getString("cidade"));
                c.setCpf(rs.getString("cpf"));
                return true;
            }
            rs.close();
            stm.close();
            con.close();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        } 
        return false;
     }
    
    public void alteraFuncionario(ClasseFuncionario f){
        conectar();
        try{
            stm = con.prepareStatement("UPDATE funcionarios "+
                    "set nome = ?,telefone = ?, funcao = ?, cpf = ?"+
                    " where IdFuncionario = ?");
            stm.setString(1, f.getNome());
            stm.setString(2, f.getTelefone());
            stm.setString(3, f.getFuncao());
            stm.setString(4, f.getCpf());
            stm.setInt(5, f.getID());
            
            stm.execute();
            
            stm.close();
            con.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
    
    public void alteraCliente(clientes c){
        conectar();
        try{
            stm = con.prepareStatement("UPDATE clientes "+
                    "set nome = ?,telefone = ?, endereco = ?, cpf = ?, cidade = ?"+
                    " where idClientes = ?");
            stm.setString(1, c.getNome());
            stm.setString(2, c.getTelefone());
            stm.setString(3, c.getEndereco());
            stm.setString(4, c.getCpf());
            stm.setString(5, c.getCidade());
            stm.setInt(6, c.getIdCliente());
            
            stm.execute();
            
            stm.close();
            con.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void alteraUsuario(ClasseFuncionario f){
        conectar();
        try{
            stm = con.prepareStatement("UPDATE usuarios set login = ?, senha = ? where IdFuncionario = ?");
            stm.setString(1, f.getLogin());
            stm.setString(2, f.getSenha());
            stm.setInt(3, f.getID());
            
            stm.execute();
            
            stm.close();
            con.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public boolean buscaProduto(ClasseProduto p){
        conectar();
        try{
            stm = con.prepareStatement("Select * from produtos where IdProdutos = ?");
            stm.setInt(1, p.getIdProduto());
            rs = stm.executeQuery();
            if (rs.next()){
                p.setDescricao(rs.getString("descricao"));
                p.setAliquota(rs.getString("aliquota"));
                p.setCategoria(rs.getString("categoria"));
                p.setICMS(rs.getFloat("icms"));
                p.setPreco(rs.getFloat("preco"));
                p.setMarca(rs.getString("marca"));
                p.setQuantidade(rs.getInt("quantidade"));
               
                return true;
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean buscaProdutoTabela(ClasseProduto p){
        conectar();
        try{
            stm = con.prepareStatement("Select IdProdutos,descricao,categoria,preco,marca,Quantidade from produtos where descricao like ?");
            stm.setString(1, p.getDescricao()+ "%");
            rs = stm.executeQuery();
            if (rs.next()){
                p.setIdProduto(rs.getInt("IdProdutos"));
                p.setDescricao(rs.getString("descricao"));
                p.setCategoria(rs.getString("categoria"));       
                p.setPreco(rs.getFloat("preco"));
                p.setMarca(rs.getString("marca"));
                p.setQuantidade(rs.getInt("Quantidade"));
                return true;
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
     public boolean buscaProdutoTabelaGeral(ClasseProduto p){
        conectar();
        try{
            stm = con.prepareStatement("Select IdProdutos,descricao,categoria,preco,marca,Quantidade from produtos");
            //stm.setString(1, p.getDescricao()+ "%");
            rs = stm.executeQuery();
            if (rs.next()){
                p.setIdProduto(rs.getInt("IdProdutos"));
                p.setDescricao(rs.getString("descricao"));
                p.setCategoria(rs.getString("categoria"));       
                p.setPreco(rs.getFloat("preco"));
                p.setMarca(rs.getString("marca"));
                p.setQuantidade(rs.getInt("Quantidade"));
                return true;
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
     public boolean insereIdAuxiliar(int x ){
         conectar();
         
         try{
            stm = con.prepareStatement("insert into IdAuxiliar value (?)");
            stm.setInt(1, x);
            stm.execute();

            stm.close();
            con.close();
             //p.setIdProduto(rs.getInt("Id"));
             return true;
         }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error ao iserir ID"); 
         }
         return false;
     }

    /**
     *
     * @return
     */
    public int buscaIdAuxiliar(){
         conectar();
         int x;
         try{
             stm = con.prepareStatement("select max(Id) from IdAuxiliar");  
             rs = stm.executeQuery();
             rs.next();
             x = rs.getInt(1);
             //rs.close();
             return x;
         }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error ao buscar ID : "+e); 
         }
         return 0;
     }
    public void apagarIdAuxiliar(){
         conectar();
         try{
             stm = con.prepareStatement("delete from IdAuxiliar");  
             stm.execute();
             stm.close();
             con.close();
         }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error excluir ID : "+e); 
         }
       
     }
    
//    public boolean buscaCliente(clientes c){
//        conectar();
//        try{
//            stm = con.prepareStatement("Select * from clientes where idClientes = ?");
//            stm.setInt(1, c.getIdCliente());
//            rs = stm.executeQuery();
//            if (rs.next()){
//                c.setNome(rs.getString("nome"));
//                c.setEndereco(rs.getString("endereco"));
//                return true;
//            }else{
//                JOptionPane.showMessageDialog(null, "Não existe nenhum cliente\n"+
//                        "Cadastrado com este Documento","Cadastro De Clientes",JOptionPane.INFORMATION_MESSAGE);
//            }
//            
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }
//        return false;
//    }
    
    
    public void alteraProduto(ClasseProduto p){
        conectar();
        try{
            stm = con.prepareStatement("UPDATE produtos "+
                    "set descricao = ?,categoria = ?, preco = ?, marca = ?, quantidade = ?"+
                    " where IdProdutos = ?");
            stm.setString(1, p.getDescricao());
            stm.setString(2, p.getCategoria());
            stm.setFloat(3, p.getPreco());
            stm.setString(4, p.getMarca());
            stm.setInt(5, p.getQuantidade());
            stm.setInt(6, p.getIdProduto());
            
            stm.execute();
            
            stm.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Atualizado");
        }catch(Exception e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null,"Erro ao modificar" + e);
        }
    }
    
    public void excluiFuncionario(ClasseFuncionario f){
      conectar();
      try{
          stm = con.prepareStatement("delete from funcionarios where IdFuncionario = ?");
          stm.setInt(1, f.getID());
          stm.execute();
          stm = con.prepareStatement("delete from usuarios where IdFuncionario = ?");
          stm.setInt(1, f.getID());
          stm.execute();
          JOptionPane.showMessageDialog(null,"Funcionário excluído com sucesso!",
                  "Cadastro de funcionários",JOptionPane.INFORMATION_MESSAGE);
          stm.close();
          con.close();
      }catch(Exception ex){
          JOptionPane.showMessageDialog(null,ex.getMessage(),
                  "Cadastro de funcionários",JOptionPane.INFORMATION_MESSAGE);   
      }
    }
    
    public void excluiCliente(clientes c){
      conectar();
      try{
          stm = con.prepareStatement("delete from clientes where IdClientes = ?");
          stm.setInt(1, c.getIdCliente());
          stm.execute();
          JOptionPane.showMessageDialog(null,"Cliente excluído com sucesso!",
                  "Cadastro de Clientes",JOptionPane.INFORMATION_MESSAGE);
          stm.close();
          con.close();
      }catch(Exception ex){
          JOptionPane.showMessageDialog(null,ex.getMessage(),
                  "Cadastro de Clientes",JOptionPane.INFORMATION_MESSAGE);   
      }
    }
    
    public void excluiPedido(ClasseVendaDelivery cvd){
      conectar();
      try{
          stm = con.prepareStatement("delete from delivery where idDelivery = ?");
          stm.setInt(1, cvd.getIdDelivery());
          stm.execute();
          JOptionPane.showMessageDialog(null,"Pedido transmitido!",
                  "Pedidos",JOptionPane.INFORMATION_MESSAGE);
          stm.close();
          con.close();
      }catch(Exception ex){
          JOptionPane.showMessageDialog(null,ex.getMessage(),
                  "Pedidos",JOptionPane.INFORMATION_MESSAGE);   
      }
    }
    public int buscaQuantidade(int id ){
         conectar();
         int x;
         try{
             stm = con.prepareStatement("select quantidade from produtos where IdProdutos = ?");
             stm.setInt(1,id);
             rs = stm.executeQuery();
             if(rs.next()){
                 x = rs.getInt("quantidade");
                  return x;
             }
            
         }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error ao quantidade : "+e); 
         }
         return 0;
     }
    public boolean cadastraVenda(ClasseVendaMesa cvm) {
        try {
            conectar();

            stm = con.prepareStatement("Insert into vendas(mesa,totalVenda,dataVenda) values (?,?,?)");
            stm.setInt(1, cvm.getIdMesa());
            stm.setFloat(2, cvm.getTotalVenda());
            stm.setString(3, cvm.getDataVenda());
            
            stm.executeUpdate();
            
            stm.close();
            
            for (int i = 0; i < cvm.getItensMesa().getRowCount(); i++) {
                stm = con.prepareStatement("Insert Into produtovenda(idVenda,IdProdutos,qtdProdutos,precoProduto)"+
                        " values ((select MAX(idVenda) from vendas),?,?,?)");
                stm.setInt(1, new Integer(cvm.getItensMesa().getValueAt(i, 0).toString()));
                stm.setInt(2, new Integer(cvm.getItensMesa().getValueAt(i, 2).toString()));
                stm.setFloat(3, new Float(cvm.getItensMesa().getValueAt(i, 3).toString()));
                
                stm.executeUpdate();
                stm.close();
            }
            return true;
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        
    }
    
    
    
     public boolean cadastraVendaDelivery(ClasseDelivery cd) {
        try {
            conectar();

            stm = con.prepareStatement("Insert into delivery(idClientes,telefone,obs,endereco,totalPedido,dataVenda,troco,status) values (?,?,?,?,?,?,?,?)");
            stm.setInt(1, cd.getIdCliente());
            stm.setString(2, cd.getTelefone());
            stm.setString(3, cd.getObs());
            stm.setString(4, cd.getEndereco());
            stm.setFloat(5, cd.getTotalPedido());
            stm.setString(6, cd.getDataVenda());
            stm.setFloat(7, cd.getTroco());
            stm.setString(8, cd.getStatus());
            
            stm.executeUpdate();
            
            stm.close();
            
            for (int i = 0; i < cd.getItensDelivery().getRowCount(); i++) {
                stm = con.prepareStatement("Insert Into produtodelivery(idDelivery,IdProdutos,qtdProduto,precoProduto)"+
                        " values ((select MAX(idDelivery) from delivery),?,?,?)");
                stm.setInt(1, new Integer(cd.getItensDelivery().getValueAt(i, 0).toString()));
                stm.setInt(2, new Integer(cd.getItensDelivery().getValueAt(i, 2).toString()));
                stm.setFloat(3, new Float(cd.getItensDelivery().getValueAt(i, 3).toString()));
                
                stm.executeUpdate();
                stm.close();
            }
            
            return true;
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        
    }
    
     public boolean vfCpfDuplicado(String qry){
        conectar();
        try{
            stm = con.prepareStatement(qry);
            rs = stm.executeQuery();
            if (rs.next()){
                return true;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
     
    
}
