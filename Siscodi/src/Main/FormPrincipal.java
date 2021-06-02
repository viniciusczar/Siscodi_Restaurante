/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Clientes.frmClientes;
import DAO.ClasseDAO;
import Delivery.frmDeliveryInicio;
import Funcionario.FormFuncionario;
import JDBC.ConnectionFactory;
import Produtos.FormProduto;
import Util.ReportUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import net.sf.jasperreports.engine.JRException;
import java.text.DecimalFormat;

/**
 *
 * @author Aluno
 */
public class FormPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FormPrincipal
     */
    public FormPrincipal() {
        initComponents();
        atualizaHora();
        
        this.mnRelDelivery.setVisible(false);
        // Verificando se o Usuario é Colaborador. Se for, desabilitado alguns botões.
        //JOptionPane.showMessageDialog(null,lbNomeUsuario.getText());
       /* if(lbNomeUsuario.getText().equals("Colaborador")){
            jButton2.setEnabled(false);
            mnFuncionarios.setEnabled(false);
            jMenu2.setEnabled(false);
            jMenu1.setEnabled(false);
                 } */
       // JOptionPane.showMessageDialog(null,lbNomeUsuario.getText());
    }

    private pnMesaPadrao[][] mesa;
    private FormProduto frmProd;
    private VendasFull frVendasF;
    private FormFuncionario frmFunc;
    private frmDeliveryInicio frmInicio;
    private frmClientes frmCli;
    private static final DateFormat HORA = new SimpleDateFormat("HH:mm:ss");
    private static final DateFormat DATA = new SimpleDateFormat("dd/MM/yyyy");
    private final DecimalFormat decimal = new DecimalFormat("0.00");
    private ClasseDAO dao;
     
    
    
    public void controleAcesso(){
         jButton2.setEnabled(false);
         mnFuncionarios.setEnabled(false);
         jMenu2.setEnabled(false);
         jMenu1.setEnabled(false);
    }
    public void organizaMesas() {
        //Limpa o painel
        pnMesas.removeAll();
        pnMesas.repaint();

        //declara as variaveis utilizadas
        int altura, largura, qtdh, qtdv, aux = 0;

        //pega altura e largura do painel
        altura = pnMesas.getHeight();
        largura = pnMesas.getWidth();

        //quantidade de mesas por linha e coluna
        qtdv = (int) Math.floor(altura / 110);
        qtdh = (int) Math.floor(largura / 155);

        //define numero de mesas para o array
        if (mesa == null) {
            mesa = new pnMesaPadrao[qtdh][qtdv];
        }

        //laço para organizar as mesas dentro do painel
        for (int x = 0; x < qtdv; x++) {
            for (int y = 0; y < qtdh; y++) {
                if (mesa[y][x] == null) {
                    mesa[y][x] = new pnMesaPadrao();
                }
                pnMesas.add(mesa[y][x]);
                if (x == 0 && y == 0) {
                    mesa[y][x].setBounds(10, 10, 145, 100);
                } else if (x == 0 && y > 0) {
                    mesa[y][x].setBounds(y * 155 + 10, 10, 145, 100);
                } else {
                    mesa[y][x].setBounds(y * 155 + 10, x * 110 + 10, 145, 100);
                }
                aux++;
                if (aux < 10) {
                    mesa[y][x].modificaNum("00" + String.valueOf(aux));
                } else {
                    mesa[y][x].modificaNum("0" + String.valueOf(aux));
                }
            }
        }
        pnMesas.revalidate();
    }

    public void usuarioLogado(String x) {
        lbNomeUsuario.setText(x);
    }

    public void setHora(Date data) {
        lbHoraSistema.setText(HORA.format(data));
        lbDataSistema.setText(DATA.format(data));
    }

    public void atualizaHora() {
        ActionListener action = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setHora(new Date());
            }
        };
        Timer timer = new Timer(1000, action);
        timer.setInitialDelay(0);
        timer.setRepeats(true);
        timer.start();
    }

    public void abrirRelatorioProdutosPorCategoria() {
        InputStream inputStream = getClass().getResourceAsStream("/ProdutosPorCategoria.jasper");

        // mapa de parâmetros do relatório (ainda vamos aprender a usar)
        Map parametros = new HashMap();

        Object[] opcoes = {"PORÇÕES", "APERITIVOS", "BEBIDAS", "REFEIÇÕES", "PIZZAS", "LANCHES", "SOBREMESAS"};
        Object res = JOptionPane.showInputDialog(null,
                "Escolha a categoria", "Relação de produtos", JOptionPane.PLAIN_MESSAGE, null, opcoes, "");

        parametros.put("primeiraCategoria", res);

        try {

            // abre o relatório
            ReportUtils.openReport("ProdutosPorCategoria", inputStream, parametros,
                    ConnectionFactory.getPubManagerConnection());

        } catch (JRException exc) {
            exc.printStackTrace();
        }
    }

    public void abrirRelatorioDeliveryPorPedidos() {
        InputStream inputStream = getClass().getResourceAsStream("/DeliveryPorPedido.jasper");

        // mapa de parâmetros do relatório (ainda vamos aprender a usar)
        Map parametros = new HashMap();

        Object res = JOptionPane.showInputDialog(null,
                "Digite o numero do pedido:", "Relação de pedidos", JOptionPane.PLAIN_MESSAGE);

        parametros.put("primeiraCategoria", res);
        System.out.println(res);
        try {

            // abre o relatório
            ReportUtils.openReport("DeliveryPorPedido", inputStream, parametros,
                    ConnectionFactory.getPubManagerConnection());

        } catch (JRException exc) {
            exc.printStackTrace();
        }
    }
    
    public void abrirRelatorioVendasPorPedidos() {
        InputStream inputStream = getClass().getResourceAsStream("/VendaPorID.jasper");

        // mapa de parâmetros do relatório (ainda vamos aprender a usar)
        Map parametros = new HashMap();

        Object res = JOptionPane.showInputDialog(null,
                "Digite o numero da venda:", "Relação de vendas", JOptionPane.PLAIN_MESSAGE);

        parametros.put("primeiraCategoria", res);
        System.out.println(res);
        try {

            // abre o relatório
            ReportUtils.openReport("VendaPorID", inputStream, parametros,
                    ConnectionFactory.getPubManagerConnection());

        } catch (JRException exc) {
            exc.printStackTrace();
        }
    }
    
    public void abrirRelatorioVendasPorData() {
        InputStream inputStream = getClass().getResourceAsStream("/VendasPorData.jasper");

        // mapa de parâmetros do relatório (ainda vamos aprender a usar)
        Map parametros = new HashMap();

        String res = JOptionPane.showInputDialog(null,
                "Digite a data (dd/mm/aaaa):", "Relação de vendas", JOptionPane.PLAIN_MESSAGE);

        String dia, mes, ano;
        dia = res.substring(0, res.indexOf('/'));
        mes = res.substring(res.indexOf('/')+1, res.lastIndexOf('/'));
        ano = res.substring(res.lastIndexOf('/')+1, res.length());
        
        res = ano+'-'+mes+'-'+dia;
        
        parametros.put("primeiraCategoria", res);
        System.out.println(res);
        try {

            // abre o relatório
            ReportUtils.openReport("VendasPorData", inputStream, parametros,
                    ConnectionFactory.getPubManagerConnection());

        } catch (JRException exc) {
            exc.printStackTrace();
        }
    }
    
    public void abrirRelatorioVendasDetalhado() {
        InputStream inputStream = getClass().getResourceAsStream("/VendasPorMesa.jasper");

        // mapa de parâmetros do relatório (ainda vamos aprender a usar)
        HashMap parametros = new HashMap();

        String res = JOptionPane.showInputDialog(null,
                "Digite a data (dd/mm/aaaa):", 
                "Relação de vendas", JOptionPane.PLAIN_MESSAGE);
        
        String res2 = JOptionPane.showInputDialog(null, "Digite o numero da mesa: ",
                "Relação de vendas por mesa", JOptionPane.PLAIN_MESSAGE);

        String dia, mes, ano;
        dia = res.substring(0, res.indexOf('/'));
        mes = res.substring(res.indexOf('/')+1, res.lastIndexOf('/'));
        ano = res.substring(res.lastIndexOf('/')+1, res.length());
        
        res = ano+'-'+mes+'-'+dia;
        
        parametros.put("primeiraCategoria", res);
        parametros.put("numeroMesa", res2);
        System.out.println(res);
        try {

            // abre o relatório
            ReportUtils.openReport("VendasPorMesa", inputStream, parametros,
                    ConnectionFactory.getPubManagerConnection());

        } catch (JRException exc) {
            //exc.printStackTrace();
            JOptionPane.showMessageDialog(null, "Relatorio vazio ou dados passados estão incorretos!");
        }
    }
    
    public void abrirRelatorioDeliveryPorClientes() {
        InputStream inputStream = getClass().getResourceAsStream("/DeliveryPorClientes.jasper");

        // mapa de parâmetros do relatório (ainda vamos aprender a usar)
        Map parametros = new HashMap();

        Object res = JOptionPane.showInputDialog(null,
                "Digite o código do cliente:", "Relação de pedidos", JOptionPane.PLAIN_MESSAGE);

        parametros.put("primeiraCategoria", res);
        System.out.println(res);
        try {

            // abre o relatório
            ReportUtils.openReport("DeliveryPorClientes", inputStream, parametros,
                    ConnectionFactory.getPubManagerConnection());

        } catch (JRException exc) {
            exc.printStackTrace();
        }
    }
    
    public void abrirRelatorioDeliveryPorData() {
        InputStream inputStream = getClass().getResourceAsStream("/DeliveryPorData.jasper");

        // mapa de parâmetros do relatório (ainda vamos aprender a usar)
        Map parametros = new HashMap();

        String res = JOptionPane.showInputDialog(null,
                "Digite a data da venda (dd/mmm/aaaa):", "Relação de pedidos", JOptionPane.PLAIN_MESSAGE);

        String dia, mes, ano;
        dia = res.substring(0, res.indexOf('/'));
        mes = res.substring(res.indexOf('/')+1, res.lastIndexOf('/'));
        ano = res.substring(res.lastIndexOf('/')+1, res.length());
        
        res = ano+'-'+mes+'-'+dia;
        
        parametros.put("primeiraCategoria", res);
        System.out.println(res);
        try {

            // abre o relatório
            ReportUtils.openReport("DeliveryPorData", inputStream, parametros,
                    ConnectionFactory.getPubManagerConnection());

        } catch (JRException exc) {
            exc.printStackTrace();
        }
    }

    public void abrirRelatorioProdutos() {
        InputStream inputStream = getClass().getResourceAsStream("/Produtos.jasper");

        // mapa de parâmetros do relatório (ainda vamos aprender a usar)
        Map parametros = new HashMap();

        try {
            // abre o relatório
            ReportUtils.openReport("Produtos", inputStream, parametros,
                    ConnectionFactory.getPubManagerConnection());
        } catch (JRException exc) {
            exc.printStackTrace();
        }
    }

    public void abrirRelatorioVendas() {
        InputStream inputStream = getClass().getResourceAsStream("/Vendas.jasper");

        // mapa de parâmetros do relatório (ainda vamos aprender a usar)
        Map parametros = new HashMap();

        try {

            // abre o relatório
            ReportUtils.openReport("Vendas", inputStream, parametros,
                    ConnectionFactory.getPubManagerConnection());

        } catch (JRException exc) {
            exc.printStackTrace();
        }
    }
    
    public void abrirRelatorioFuncionarios() {
        InputStream inputStream = getClass().getResourceAsStream("/Funcionarios.jasper");

        // mapa de parâmetros do relatório (ainda vamos aprender a usar)
        Map parametros = new HashMap();

        try {

            // abre o relatório
            ReportUtils.openReport("Funcionarios", inputStream, parametros,
                    ConnectionFactory.getPubManagerConnection());

        } catch (JRException exc) {
            exc.printStackTrace();
        }
    }

    public void abrirRelatorioClientes() {
        InputStream inputStream = getClass().getResourceAsStream("/Clientes.jasper");

        // mapa de parâmetros do relatório (ainda vamos aprender a usar)
        Map parametros = new HashMap();

        try {

            // abre o relatório
            ReportUtils.openReport("Clientes", inputStream, parametros,
                    ConnectionFactory.getPubManagerConnection());

        } catch (JRException exc) {
            exc.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pnOpcoes = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbTotalGeral = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        lbExibindo = new javax.swing.JLabel();
        cmbFiltroMesa = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        lbNomeUsuario = new javax.swing.JLabel();
        lbHoraSistema = new javax.swing.JLabel();
        lbDataSistema = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        pnMesas = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        mnBarra = new javax.swing.JMenuBar();
        jMenu6 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        mnProdutos = new javax.swing.JMenuItem();
        mnFuncionarios = new javax.swing.JMenuItem();
        mnClientes = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mnRelProdutos = new javax.swing.JMenu();
        mnRelProdutosTodos = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        mnRelMesas = new javax.swing.JMenu();
        mnDetalhado = new javax.swing.JMenuItem();
        mnRelDelivery = new javax.swing.JMenu();
        rlPedido = new javax.swing.JMenuItem();
        rlPedidoCliente = new javax.swing.JMenuItem();
        rlPedidoData = new javax.swing.JMenuItem();
        mnRelClientes = new javax.swing.JMenu();
        mnRelClientesTodos = new javax.swing.JMenuItem();
        mnRelVendas = new javax.swing.JMenu();
        mnVendaTodas = new javax.swing.JMenuItem();
        mnVendaData = new javax.swing.JMenuItem();
        mnVendaID = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PubManager/ Menu Principal");
        setUndecorated(true);
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        pnOpcoes.setBackground(new java.awt.Color(255, 255, 255));
        pnOpcoes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        pnOpcoes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/LogoSistema2.png"))); // NOI18N

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        lbTotalGeral.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbTotalGeral.setText("0,00");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setText("TOTAL:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(lbTotalGeral)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTotalGeral)
                    .addComponent(jLabel4))
                .addContainerGap())
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/MotoDelivery.png"))); // NOI18N
        jButton2.setText("DELIVERY");
        jButton2.setToolTipText("Função ainda não implementada");
        jButton2.setEnabled(false);
        jButton2.setPreferredSize(new java.awt.Dimension(120, 120));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Critical_details.png"))); // NOI18N
        jButton4.setText("PRODUTOS");
        jButton4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton4.setPreferredSize(new java.awt.Dimension(120, 120));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Work_area.png"))); // NOI18N
        btnClientes.setText("CLIENTES");
        btnClientes.setPreferredSize(new java.awt.Dimension(120, 120));
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnOpcoesLayout = new javax.swing.GroupLayout(pnOpcoes);
        pnOpcoes.setLayout(pnOpcoesLayout);
        pnOpcoesLayout.setHorizontalGroup(
            pnOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnOpcoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnOpcoesLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnOpcoesLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnOpcoesLayout.setVerticalGroup(
            pnOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnOpcoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(36, 36, 36)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lbExibindo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbExibindo.setText("EXIBINDO:");

        cmbFiltroMesa.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cmbFiltroMesa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODAS", "DISPONÍVEL", "OCUPADA", "RESERVADA" }));
        cmbFiltroMesa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbFiltroMesaItemStateChanged(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("USUÁRIO:");

        lbNomeUsuario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbHoraSistema.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbHoraSistema.setText("00:00:00");

        lbDataSistema.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbDataSistema.setText("01/01/2015");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNomeUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDataSistema)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbHoraSistema)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lbNomeUsuario)))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lbHoraSistema, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(lbDataSistema))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnMesas.setBackground(new java.awt.Color(0, 102, 102));
        pnMesas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel3.setText("jLabel3");

        javax.swing.GroupLayout pnMesasLayout = new javax.swing.GroupLayout(pnMesas);
        pnMesas.setLayout(pnMesasLayout);
        pnMesasLayout.setHorizontalGroup(
            pnMesasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMesasLayout.createSequentialGroup()
                .addGap(245, 245, 245)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnMesasLayout.setVerticalGroup(
            pnMesasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMesasLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mnBarra.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/PubMgn Icon.png"))); // NOI18N
        mnBarra.add(jMenu6);

        jMenu1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        jMenu1.setText("CADASTROS");
        jMenu1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        mnProdutos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        mnProdutos.setText("PRODUTOS");
        mnProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnProdutosActionPerformed(evt);
            }
        });
        jMenu1.add(mnProdutos);

        mnFuncionarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        mnFuncionarios.setText("FUNCIONÁRIOS");
        mnFuncionarios.setEnabled(false);
        mnFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnFuncionariosActionPerformed(evt);
            }
        });
        jMenu1.add(mnFuncionarios);

        mnClientes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        mnClientes.setText("CLIENTES");
        mnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnClientesActionPerformed(evt);
            }
        });
        jMenu1.add(mnClientes);

        mnBarra.add(jMenu1);

        jMenu2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        jMenu2.setText("RELATÓRIOS");
        jMenu2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        mnRelProdutos.setText("PRODUTOS");
        mnRelProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnRelProdutosActionPerformed(evt);
            }
        });

        mnRelProdutosTodos.setText("TODOS");
        mnRelProdutosTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnRelProdutosTodosActionPerformed(evt);
            }
        });
        mnRelProdutos.add(mnRelProdutosTodos);

        jMenuItem1.setText("POR CATEGORIA");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        mnRelProdutos.add(jMenuItem1);

        jMenuItem3.setText("POR DESCRIÇÃO");
        mnRelProdutos.add(jMenuItem3);

        jMenu2.add(mnRelProdutos);

        mnRelMesas.setText("MESAS");

        mnDetalhado.setText("DETALHADA");
        mnDetalhado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnDetalhadoActionPerformed(evt);
            }
        });
        mnRelMesas.add(mnDetalhado);

        jMenu2.add(mnRelMesas);

        mnRelDelivery.setText("DELIVERY");
        mnRelDelivery.setEnabled(false);

        rlPedido.setText("POR PEDIDO");
        rlPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rlPedidoActionPerformed(evt);
            }
        });
        mnRelDelivery.add(rlPedido);

        rlPedidoCliente.setText("POR CLIENTE");
        rlPedidoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rlPedidoClienteActionPerformed(evt);
            }
        });
        mnRelDelivery.add(rlPedidoCliente);

        rlPedidoData.setText("POR DATA");
        rlPedidoData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rlPedidoDataActionPerformed(evt);
            }
        });
        mnRelDelivery.add(rlPedidoData);

        jMenu2.add(mnRelDelivery);

        mnRelClientes.setText("CLIENTES");

        mnRelClientesTodos.setText("TODOS");
        mnRelClientesTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnRelClientesTodosActionPerformed(evt);
            }
        });
        mnRelClientes.add(mnRelClientesTodos);

        jMenu2.add(mnRelClientes);

        mnRelVendas.setText("VENDAS");

        mnVendaTodas.setText("TODAS");
        mnVendaTodas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnVendaTodasActionPerformed(evt);
            }
        });
        mnRelVendas.add(mnVendaTodas);

        mnVendaData.setText("POR DATA");
        mnVendaData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnVendaDataActionPerformed(evt);
            }
        });
        mnRelVendas.add(mnVendaData);

        mnVendaID.setText("POR VENDA");
        mnVendaID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnVendaIDActionPerformed(evt);
            }
        });
        mnRelVendas.add(mnVendaID);

        jMenu2.add(mnRelVendas);

        mnBarra.add(jMenu2);

        jMenu4.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        jMenu4.setText("SISTEMA");
        jMenu4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jMenuItem2.setText("SAIR");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem2);

        mnBarra.add(jMenu4);

        setJMenuBar(mnBarra);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(pnOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 362, Short.MAX_VALUE)
                        .addComponent(lbExibindo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbFiltroMesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnMesas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbFiltroMesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbExibindo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnMesas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(1);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        organizaMesas();
    }//GEN-LAST:event_formComponentResized

    private void mnProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnProdutosActionPerformed
       // Verificando se o Usuario é Colaborador. Se for, desabilitado alguns botões.
        if (frmProd == null) {
            frmProd = new FormProduto();    
        } 
            frmProd.setModal(true);
            frmProd.setVisible(true);
                
                 
        
        
    }//GEN-LAST:event_mnProdutosActionPerformed

    private void mnFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnFuncionariosActionPerformed
        if (frmFunc == null) {
            frmFunc = new FormFuncionario();
        }

        frmFunc.setModal(true);
        frmFunc.setVisible(true);
    }//GEN-LAST:event_mnFuncionariosActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // AÇÃO BOTÃO VENDAS
       if(frVendasF == null){
            frVendasF = new VendasFull(); 
        } 
            frVendasF.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       if (frmProd == null) {
            frmProd = new FormProduto();
        }
            frmProd.setModal(true);
            frmProd.setVisible(true);
     
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed

        if (frmCli == null) {
            frmCli = new frmClientes();
        }
        frmCli.setModal(true);
        frmCli.setVisible(true);
        
    }//GEN-LAST:event_btnClientesActionPerformed

    private void mnRelProdutosTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnRelProdutosTodosActionPerformed
        abrirRelatorioProdutos();
    }//GEN-LAST:event_mnRelProdutosTodosActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        abrirRelatorioProdutosPorCategoria();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void mnRelClientesTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnRelClientesTodosActionPerformed
        abrirRelatorioClientes();
    }//GEN-LAST:event_mnRelClientesTodosActionPerformed

    private void rlPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rlPedidoActionPerformed
        abrirRelatorioDeliveryPorPedidos();
    }//GEN-LAST:event_rlPedidoActionPerformed

    public void filtroMesas(String estado) {
        //Limpa o painel
        pnMesas.removeAll();
        pnMesas.repaint();

        //declara as variaveis utilizadas
        int altura, largura, qtdh, qtdv, aux = 0, aux2 = 0;

        //pega altura e largura do painel
        altura = pnMesas.getHeight();
        largura = pnMesas.getWidth();

        //quantidade de mesas por linha e coluna
        qtdv = (int) Math.ceil(altura / 110);
        qtdh = (int) Math.ceil(largura / 155);

        //laço para organizar as mesas dentro do painel
        for (int x = 0; x < qtdv; x++) {
            for (int y = 0; y < qtdh; y++) {
                if (mesa[y][x].getStatus().equalsIgnoreCase(estado)) {
                    pnMesas.add(mesa[y][x]);
                    if (aux2 == 0 && aux == 0) {
                        mesa[y][x].setBounds(10, 10, 145, 100);
                    } else if (aux2 == 0 && aux > 0) {
                        mesa[y][x].setBounds(aux * 155 + 10, 10, 145, 100);
                    } else {
                        mesa[y][x].setBounds(aux * 155 + 10, aux2 * 110 + 10, 145, 100);
                    }
                    aux++;
                    if (aux >= qtdh) {
                        aux2++;
                        aux = 0;
                    }
                }

            }
        }
        pnMesas.revalidate();
    }

    private void cmbFiltroMesaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbFiltroMesaItemStateChanged
        String aux = cmbFiltroMesa.getSelectedItem().toString();
        if (aux.equalsIgnoreCase("todas")) {
            organizaMesas();
        } else {
            filtroMesas(aux);
        }
    }//GEN-LAST:event_cmbFiltroMesaItemStateChanged

    private void mnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnClientesActionPerformed

        if (frmCli == null) {
            frmCli = new frmClientes();
        }        
            frmCli.setModal(true);
            frmCli.setVisible(true);
    }//GEN-LAST:event_mnClientesActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        double vt = 0;
        for (pnMesaPadrao[] aux : mesa) {
            for (pnMesaPadrao aux2 : aux) {
                vt += aux2.getValorTotal();
            }
        }
        lbTotalGeral.setText(decimal.format(vt));
        
        pnMesas.repaint();
    }//GEN-LAST:event_formWindowGainedFocus

    private void rlPedidoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rlPedidoClienteActionPerformed
        abrirRelatorioDeliveryPorClientes();
    }//GEN-LAST:event_rlPedidoClienteActionPerformed

    private void rlPedidoDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rlPedidoDataActionPerformed
        abrirRelatorioDeliveryPorData();
    }//GEN-LAST:event_rlPedidoDataActionPerformed

    private void mnVendaTodasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnVendaTodasActionPerformed
        abrirRelatorioVendas();
    }//GEN-LAST:event_mnVendaTodasActionPerformed

    private void mnVendaDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnVendaDataActionPerformed
        abrirRelatorioVendasPorData();
    }//GEN-LAST:event_mnVendaDataActionPerformed

    private void mnVendaIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnVendaIDActionPerformed
        abrirRelatorioVendasPorPedidos();
    }//GEN-LAST:event_mnVendaIDActionPerformed

    private void mnDetalhadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnDetalhadoActionPerformed
        abrirRelatorioVendasDetalhado();
    }//GEN-LAST:event_mnDetalhadoActionPerformed

    private void mnRelProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnRelProdutosActionPerformed

        
    }//GEN-LAST:event_mnRelProdutosActionPerformed

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
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClientes;
    private javax.swing.JComboBox cmbFiltroMesa;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbDataSistema;
    private javax.swing.JLabel lbExibindo;
    private javax.swing.JLabel lbHoraSistema;
    private javax.swing.JLabel lbNomeUsuario;
    private javax.swing.JLabel lbTotalGeral;
    private javax.swing.JMenuBar mnBarra;
    private javax.swing.JMenuItem mnClientes;
    private javax.swing.JMenuItem mnDetalhado;
    private javax.swing.JMenuItem mnFuncionarios;
    private javax.swing.JMenuItem mnProdutos;
    private javax.swing.JMenu mnRelClientes;
    private javax.swing.JMenuItem mnRelClientesTodos;
    private javax.swing.JMenu mnRelDelivery;
    private javax.swing.JMenu mnRelMesas;
    private javax.swing.JMenu mnRelProdutos;
    private javax.swing.JMenuItem mnRelProdutosTodos;
    private javax.swing.JMenu mnRelVendas;
    private javax.swing.JMenuItem mnVendaData;
    private javax.swing.JMenuItem mnVendaID;
    private javax.swing.JMenuItem mnVendaTodas;
    private javax.swing.JPanel pnMesas;
    private javax.swing.JPanel pnOpcoes;
    private javax.swing.JMenuItem rlPedido;
    private javax.swing.JMenuItem rlPedidoCliente;
    private javax.swing.JMenuItem rlPedidoData;
    // End of variables declaration//GEN-END:variables
}
