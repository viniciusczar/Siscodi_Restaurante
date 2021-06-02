/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Delivery;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aluno
 */
public class FormCupomDelivery extends javax.swing.JDialog {

    /**
     * Creates new form FormCupom
     */
    public FormCupomDelivery() {
        initComponents();
    }
    
    private String endereco;
    private String telefone;
    private DefaultTableModel itensPedido;
    private DefaultListModel <String> modeloLista;
    private Date data;
    private ClasseDelivery dl;
    private final DateFormat DATA = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private final DecimalFormat decimal = new DecimalFormat("0.00");
    private String numDelivery;
    private String totalDelivery;
    private String troco;

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String getTelefone() {
        return telefone;
    }
    
    public void setTroco(String troco) {
        this.troco = troco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public void setTotalDelivery(String totalDelivery) {
        this.totalDelivery = totalDelivery;
    }

    public void setNumDelivery(String numDelivery) {
        this.numDelivery = numDelivery;
    }
    
    public void setItensPedido(DefaultTableModel itensPedido) {
        this.itensPedido = itensPedido;
    }
    
    public void preencheLista(){
        if (modeloLista == null){
            modeloLista = new DefaultListModel<>();
        }
        data = new Date();
        String aux = DATA.format(data);
        String desc;
        
        listaProdutos.setModel(modeloLista);
        
        
        
        for (int i = -1; i < itensPedido.getRowCount();i++){
            if (i < 0){
                modeloLista.addElement("==========PubManager.Soft==========");
                modeloLista.addElement("|---------------------DELIVERY-------------------|");
                modeloLista.addElement("===================================");
                modeloLista.addElement("                 Comanda de Entrega");
//                modeloLista.addElement("");
                modeloLista.addElement("===================================");
                modeloLista.addElement(aux);
                modeloLista.addElement("Pedido: "+ numDelivery);
//                modeloLista.addElement("");
                modeloLista.addElement("===================================");
                modeloLista.addElement(String.format("%-5s %-8s %-3s %-5s %-5s","COD","DESC","QTD.","V.Unit.","V.Total"));
                modeloLista.addElement("===================================");
            }else{
                if (String.valueOf(itensPedido.getValueAt(i,1)).length() > 9){
                    desc = String.valueOf(itensPedido.getValueAt(i,1)).substring(0, 6)+".";
                }else{
                    desc = String.valueOf(itensPedido.getValueAt(i,1));
                }

                modeloLista.addElement(String.format("%-5s %-8s %-5s %5s %8s", String.valueOf(itensPedido.getValueAt(i, 0)), desc,
                        String.valueOf(itensPedido.getValueAt(i, 2)),decimal.format(itensPedido.getValueAt(i, 3)),
                        decimal.format(itensPedido.getValueAt(i, 4))));
            }
        }
        modeloLista.addElement("===================================");
        
        String end = "Endereço: " + getEndereco();
        int x = 0;
        
        while (x < end.length()) {
            if (x < (end.length() - 35)) {
                modeloLista.addElement(end.substring(x, x+35));
                x += 35;
            } else {
                modeloLista.addElement(end.substring(x, end.length()));
                x = end.length() + 1;
            }
        }
        
        modeloLista.addElement("Telefone: "+ getTelefone());
        modeloLista.addElement("===================================");
        modeloLista.addElement(String.format("%-20s %-3s %10s","Total:","R$ ",decimal.format(Double.valueOf(totalDelivery))));
//        modeloLista.addElement("");
        modeloLista.addElement(String.format("%-20s %-3s %10s","Troco :","R$ ",decimal.format(Double.valueOf(troco))));
        modeloLista.addElement("");
//        modeloLista.addElement(String.format("%-20s %-3s %10s","Taxa:","    R$",decimal.format(Double.valueOf(totalDelivery)*0.10)));
//        modeloLista.addElement("");
//        modeloLista.addElement(String.format("%-20s %-3s %10s","Total + Taxa:","R$ ",decimal.format(Double.valueOf(totalDelivery)*1.10)));
        modeloLista.addElement("");
        modeloLista.addElement("===================================");
        modeloLista.addElement("           Volte Sempre!");
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listaProdutos = new javax.swing.JList();
        btImprimir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PubManager/ Consulta de Comanda");
        setAlwaysOnTop(true);
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        listaProdutos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        listaProdutos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaProdutos.setAutoscrolls(false);
        listaProdutos.setEnabled(false);
        jScrollPane1.setViewportView(listaProdutos);

        btImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/imprimir.png"))); // NOI18N
        btImprimir.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    private void btImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImprimirActionPerformed
        int aux = 0;
        try{
            
            //Cria o arquivo temporario e sea a pasta da aplicação como local de salvamento.
            File arquivo = File.createTempFile("temp", ".txt", 
                    new File(System.getProperty("user.dir")));

            
            
            //laço para inserir linhas
            try ( // escreve no arquivo temporário
                    BufferedWriter out = new BufferedWriter(new FileWriter(arquivo))) {
                
                
                //laço para inserir linhas
                while(aux < modeloLista.getSize()){
                    out.write(String.valueOf(modeloLista.getElementAt(aux)));
                    out.newLine();
                    aux++;
                }
            }
            
            
            
//            String print = "notepad.exe /P ";
            String print = "notepad.exe ";
            
            
            Runtime.getRuntime().exec(print+ arquivo.getAbsolutePath());
            
            arquivo.deleteOnExit();

        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btImprimirActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        modeloLista.clear();
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormCupomDelivery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormCupomDelivery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormCupomDelivery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormCupomDelivery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormCupomDelivery().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btImprimir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listaProdutos;
    // End of variables declaration//GEN-END:variables

    

    
}
