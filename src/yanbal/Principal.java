package yanbal;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import modelo.Clientes;
import modelo.ClientesDAO;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import modelo.DetalleMisVendedores;
import modelo.DetalleMisVentas;
import modelo.Eventos;
import modelo.Login;
import modelo.MisCuentas;
import modelo.MisVendedores;
import modelo.MisVendedoresDAO;
import modelo.MisVentas;
import modelo.MisVentasDAO;
import modelo.Productos;
import modelo.ProductosDAO;
import modelo.Vendedores;
import modelo.VendedoresDAO;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import javax.swing.JLabel;

//import java.util.logging.Level;
//import java.util.logging.Logger;
//import com.itextpdf.text.Image as PdfImage;
/**
 *
 * @author Bleider
 */
public class Principal extends javax.swing.JFrame {

    Login lo = new Login();
    Clientes cl = new Clientes();
    ClientesDAO client = new ClientesDAO();
    Vendedores ve = new Vendedores();
    VendedoresDAO vend = new VendedoresDAO();
    Productos pr = new Productos();
    ProductosDAO produ = new ProductosDAO();
    MisVentas mv = new MisVentas();
    MisVentasDAO misven = new MisVentasDAO();
    MisVendedores mvr = new MisVendedores();
    MisVendedoresDAO misvendedor = new MisVendedoresDAO();
    DetalleMisVentas dmv = new DetalleMisVentas();
    DetalleMisVendedores dmvr = new DetalleMisVendedores();
    MiCuenta micu = new MiCuenta();
    MisCuentas mc = new MisCuentas();
    Eventos event = new Eventos();
    AcercaDe acd = new AcercaDe();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel tmp = new DefaultTableModel();
    int item;
    private BigDecimal total_pagar = BigDecimal.ZERO;// inicializamos total_pagar en cero
    private JButton ultimoBotonSeleccionado = null;

    DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");

    /**
     * Creates new form Principal
     */
    public Principal() {

        initComponents();
        //Desactivar pestanias de jtabbepane 
        for (int i = 0; i < jTabbedPane1.getTabCount(); i++) {
            jTabbedPane1.setEnabledAt(i, false);
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Yanbal");
        setIconImage(new ImageIcon(getClass().getResource("/images/miniLogo.png")).getImage());

        this.jPanelContenedorNuevaVentaUsuario.setVisible(false);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        setImageButton(btnLogoPersonal, "/images/LogoBleider5.png");
        this.txtClientesId.setVisible(false);
        this.txtVendedoresId.setVisible(false);
        this.txtProductosId.setVisible(false);
        this.txtMisVentasRegistroId.setVisible(false);
        this.txtMisVentasAgregarIdProducto.setVisible(false);
        this.txtMisVendedoresAgregarIdProducto.setVisible(false);
        this.txtMisVendedoresRegistroId.setVisible(false);
        this.txtMisVentasAgregarDireccionCliente.setVisible(false);
        this.txtMisVentasAgregarTelefonoCliente.setVisible(false);
        //-----------------------------------------------------------
        this.btnProductosPdf.setVisible(false);
        this.btnClientesPdf.setVisible(false);
        this.btnVendedoresPdf.setVisible(false);
        SpinnerModel ModeloSpinner = new SpinnerNumberModel(1, 1, 100, 1); // Valor inicial, mínimo, máximo, incremento
        spinnerMisVentasAgregarCantidad.setModel(ModeloSpinner);
        JFormattedTextField txtField = ((JSpinner.DefaultEditor) spinnerMisVentasAgregarCantidad.getEditor()).getTextField();
        txtField.setEditable(false);
        spinnerMisVendedoresAgregarCantidad.setModel(ModeloSpinner);
        JFormattedTextField txtField2 = ((JSpinner.DefaultEditor) spinnerMisVendedoresAgregarCantidad.getEditor()).getTextField();
        txtField2.setEditable(false);
    }

    public void ListarClientes() {
        List<Clientes> ListarCl = client.ListarClientes();
        modelo = (DefaultTableModel) tableClientes.getModel();
        Object[] ob = new Object[5];
        for (int i = 0; i < ListarCl.size(); i++) {
            ob[0] = ListarCl.get(i).getId();
            ob[1] = ListarCl.get(i).getCodigo();
            ob[2] = ListarCl.get(i).getNombre();
            ob[3] = ListarCl.get(i).getDireccion();
            ob[4] = ListarCl.get(i).getTelefono();
            modelo.addRow(ob);
        }
        tableClientes.setModel(modelo);
    }

    public void ListarVendedores() {
        List<Vendedores> ListarVe = vend.ListarVendedores();
        modelo = (DefaultTableModel) tableVendedores.getModel();
        Object[] ob = new Object[5];
        for (int i = 0; i < ListarVe.size(); i++) {
            ob[0] = ListarVe.get(i).getId();
            ob[1] = ListarVe.get(i).getCodigo();
            ob[2] = ListarVe.get(i).getNombre();
            ob[3] = ListarVe.get(i).getDireccion();
            ob[4] = ListarVe.get(i).getTelefono();
            modelo.addRow(ob);
        }
        tableVendedores.setModel(modelo);
    }

    public void ListarProductos() {
        List<Productos> ListarPr = produ.ListarProductos();
        modelo = (DefaultTableModel) tableProductos.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getId();
            ob[1] = ListarPr.get(i).getCodigo();
            ob[2] = ListarPr.get(i).getNombre();
            ob[3] = ListarPr.get(i).formatoDecimal.format(ListarPr.get(i).getPrecio()); // agregamos formato decimal al precio

            modelo.addRow(ob);
        }
        tableProductos.setModel(modelo);

    }

    public void ListarMisVentasRegistro() {
        List<MisVentas> ListarMv = misven.ListarMisVentas();
        modelo = (DefaultTableModel) tableMisVentasRegistro.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < ListarMv.size(); i++) {
            ob[0] = ListarMv.get(i).getId();
            ob[1] = ListarMv.get(i).getCodigo_cliente();
            ob[2] = ListarMv.get(i).getNombreCliente();
            ob[3] = ListarMv.get(i).formatoDecimal.format(ListarMv.get(i).getDebe());
            ob[4] = ListarMv.get(i).formatoDecimal.format(ListarMv.get(i).getAbonado());
            ob[5] = ListarMv.get(i).formatoDecimal.format(ListarMv.get(i).getTotal());
            modelo.addRow(ob);
        }
        tableMisVentasRegistro.setModel(modelo);
    }

    public void ListarRegistroMisVendedores() {
        List<MisVendedores> ListarMvr = misvendedor.ListarVentaMisVendedores();
        modelo = (DefaultTableModel) tableMisVendedoresRegistro.getModel();
        Object[] ob = new Object[5];
        for (int i = 0; i < ListarMvr.size(); i++) {
            ob[0] = ListarMvr.get(i).getId();
            ob[1] = ListarMvr.get(i).getCodigoVendedor();
            ob[2] = ListarMvr.get(i).getNombreVendedor();
            ob[3] = ListarMvr.get(i).getFecha();
            ob[4] = ListarMvr.get(i).getTotal();
            modelo.addRow(ob);
        }
        tableMisVendedoresRegistro.setModel(modelo);
    }

    public void LimpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void LimpiarTableAgregarMiVenta() {
        tmp = (DefaultTableModel) tableMisVentasAgregar.getModel();
        int fila = tableMisVentasAgregar.getRowCount();
        for (int i = 0; i < fila; i++) {
            tmp.removeRow(0);
        }
    }

    public void LimpiarTableAgregarMisVendedores() {
        tmp = (DefaultTableModel) tableMisVendedoresAgregar.getModel();
        int fila = tableMisVendedoresAgregar.getRowCount();
        for (int i = 0; i < fila; i++) {
            tmp.removeRow(0);
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

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnClientes = new javax.swing.JButton();
        btnTotalVentas = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnNuevaVenta = new javax.swing.JButton();
        btnVendedores = new javax.swing.JButton();
        btnMisVentasRegistroMisCuentas = new javax.swing.JButton();
        btnLogoPersonal = new javax.swing.JButton();
        jPanel2 = new FondoPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelBienvenida = new YanbalBienvenidaPanel();
        jPanelNuevaVentaUsuario = new javax.swing.JPanel();
        jPanelContenedorNuevaVentaUsuario = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtMisVentasAgregarCodigoProducto = new javax.swing.JTextField();
        txtMisVentasAgregarNombreProducto = new javax.swing.JTextField();
        txtMisVentasAgregarPrecioFactura = new javax.swing.JTextField();
        txtMisVentasAgregarPrecioVendido = new javax.swing.JTextField();
        txtMisVentasAgregarCodigoCliente = new javax.swing.JTextField();
        txtMisVentasAgregarNombreCliente = new javax.swing.JTextField();
        btnMisVentasAgregarGuardar = new javax.swing.JButton();
        btnMisVentasAgregarEliminar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableMisVentasAgregar = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        labelMisVentasAgregarTotalPagar = new javax.swing.JLabel();
        spinnerMisVentasAgregarCantidad = new javax.swing.JSpinner();
        txtMisVentasAgregarIdProducto = new javax.swing.JTextField();
        txtMisVentasAgregarDireccionCliente = new javax.swing.JTextField();
        txtMisVentasAgregarTelefonoCliente = new javax.swing.JTextField();
        lblTab1NuevaVentaVendedor = new javax.swing.JLabel();
        lblTab1NuevaVentaUsuario = new javax.swing.JLabel();
        jPanelNuevaVentaVendedor = new javax.swing.JPanel();
        jPanelContenedorNuevaVentaVendedor = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMisVendedoresAgregarCodigoProducto = new javax.swing.JTextField();
        txtMisVendedoresAgregarNombreProducto = new javax.swing.JTextField();
        btnMisVendedoresAgregarEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMisVendedoresAgregar = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMisVendedoresAgregarPrecio = new javax.swing.JTextField();
        spinnerMisVendedoresAgregarCantidad = new javax.swing.JSpinner();
        txtMisVendedoresAgregarCodigoVendedor = new javax.swing.JTextField();
        txtMisVendedoresAgregarNombreVendedor = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        dcMisVendedoresAgregarFechaLimite = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        labelMisVendedoresAgregarTotalPagar = new javax.swing.JLabel();
        btnMisVendedoresAgregarGuardar = new javax.swing.JButton();
        txtMisVendedoresAgregarIdProducto = new javax.swing.JTextField();
        lblTab2NuevaVentaUsuario = new javax.swing.JLabel();
        lblTab2NuevaVentaVendedor = new javax.swing.JLabel();
        jPanelProductos = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtProductosCodigo = new javax.swing.JTextField();
        txtProductosNombre = new javax.swing.JTextField();
        txtProductosPrecio = new javax.swing.JTextField();
        btnProductosActualizar = new javax.swing.JButton();
        btnProductosEliminar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableProductos = new javax.swing.JTable();
        btnProductosPdf = new javax.swing.JButton();
        btnProductosGuardar = new javax.swing.JButton();
        btnProductosNuevo = new javax.swing.JButton();
        txtProductosId = new javax.swing.JTextField();
        jPanelClientes = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtClientesCodigo = new javax.swing.JTextField();
        txtClientesNombre = new javax.swing.JTextField();
        txtClientesDireccion = new javax.swing.JTextField();
        btnClientesActualizar = new javax.swing.JButton();
        btnClientesGuardar = new javax.swing.JButton();
        btnClientesEliminar = new javax.swing.JButton();
        btnClientesEliminarTodo = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableClientes = new javax.swing.JTable();
        btnClientesPdf = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        txtClientesTelefono = new javax.swing.JTextField();
        txtClientesId = new javax.swing.JTextField();
        btnClientesNuevo = new javax.swing.JButton();
        jPanelVendedores = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtVendedoresCodigo = new javax.swing.JTextField();
        txtVendedoresNombre = new javax.swing.JTextField();
        txtVendedoresTelefono = new javax.swing.JTextField();
        txtVendedoresDireccion = new javax.swing.JTextField();
        btnVendedoresPdf = new javax.swing.JButton();
        btnVendedoresGuardar = new javax.swing.JButton();
        btnVendedoresActualizar = new javax.swing.JButton();
        btnVendedoresEliminar = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableVendedores = new javax.swing.JTable();
        txtVendedoresId = new javax.swing.JTextField();
        btnVendedoresNuevo = new javax.swing.JButton();
        jPanelVentasUsuario = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableMisVentasRegistro = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtMisVentasRegistroAbonar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtMisVentasRegistroCodigoCliente = new javax.swing.JTextField();
        txtMisVentasRegistroNombreCliente = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtMisVentasRegistroDeuda = new javax.swing.JTextField();
        btnMisVentasRegistroActualizar = new javax.swing.JButton();
        btnMisVentasRegistroEliminar = new javax.swing.JButton();
        MisVentasRegistroEliminarTodo = new javax.swing.JButton();
        btnMisVentasRegistrosPdf = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        txtMisVentasRegistroId = new javax.swing.JTextField();
        jPanelVentasVendedores = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tableMisVendedoresRegistro = new javax.swing.JTable();
        btnMisVendedoresRegistroPdf = new javax.swing.JButton();
        btnMisVendedoresRegistroEliminar = new javax.swing.JButton();
        btnMisVendedoresRegistroEliminarTodo = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        txtMisVendedoresRegistroId = new javax.swing.JTextField();

        jLabel2.setFont(new java.awt.Font("Gabriola", 0, 52)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Registros Yanbal");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1220, 700));
        setMinimumSize(new java.awt.Dimension(1220, 700));
        setPreferredSize(new java.awt.Dimension(1220, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));

        btnClientes.setBackground(new java.awt.Color(255, 153, 153));
        btnClientes.setFont(new java.awt.Font("Goudy Old Style", 1, 20)); // NOI18N
        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clientes.png"))); // NOI18N
        btnClientes.setText("Clientes");
        btnClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });

        btnTotalVentas.setBackground(new java.awt.Color(255, 153, 153));
        btnTotalVentas.setFont(new java.awt.Font("Goudy Old Style", 1, 20)); // NOI18N
        btnTotalVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MisVendedores.png"))); // NOI18N
        btnTotalVentas.setText("Ventas");
        btnTotalVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTotalVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTotalVentasActionPerformed(evt);
            }
        });

        btnProductos.setBackground(new java.awt.Color(255, 153, 153));
        btnProductos.setFont(new java.awt.Font("Goudy Old Style", 1, 20)); // NOI18N
        btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Producto.png"))); // NOI18N
        btnProductos.setText("Productos");
        btnProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });

        btnNuevaVenta.setBackground(new java.awt.Color(255, 153, 153));
        btnNuevaVenta.setFont(new java.awt.Font("Goudy Old Style", 1, 20)); // NOI18N
        btnNuevaVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Mi-venta.png"))); // NOI18N
        btnNuevaVenta.setText("Nueva Venta");
        btnNuevaVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaVentaActionPerformed(evt);
            }
        });

        btnVendedores.setBackground(new java.awt.Color(255, 153, 153));
        btnVendedores.setFont(new java.awt.Font("Goudy Old Style", 1, 20)); // NOI18N
        btnVendedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vendedor.png"))); // NOI18N
        btnVendedores.setText("Vendedores");
        btnVendedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVendedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVendedoresActionPerformed(evt);
            }
        });

        btnMisVentasRegistroMisCuentas.setBackground(new java.awt.Color(255, 153, 153));
        btnMisVentasRegistroMisCuentas.setFont(new java.awt.Font("Goudy Old Style", 1, 20)); // NOI18N
        btnMisVentasRegistroMisCuentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/misCuentas.png"))); // NOI18N
        btnMisVentasRegistroMisCuentas.setText("Mis Cuentas");
        btnMisVentasRegistroMisCuentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMisVentasRegistroMisCuentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMisVentasRegistroMisCuentasActionPerformed(evt);
            }
        });

        btnLogoPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LogoBleider5.png"))); // NOI18N
        btnLogoPersonal.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 4, 5, 4, new java.awt.Color(255, 51, 51)));
        btnLogoPersonal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogoPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoPersonalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(btnLogoPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(btnNuevaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnTotalVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnVendedores, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnMisVentasRegistroMisCuentas, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnLogoPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(btnNuevaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTotalVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVendedores, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMisVentasRegistroMisCuentas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 690));

        jPanel2.setBackground(new java.awt.Color(255, 153, 102));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 960, 140));

        jTabbedPane1.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout jPanelBienvenidaLayout = new javax.swing.GroupLayout(jPanelBienvenida);
        jPanelBienvenida.setLayout(jPanelBienvenidaLayout);
        jPanelBienvenidaLayout.setHorizontalGroup(
            jPanelBienvenidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        jPanelBienvenidaLayout.setVerticalGroup(
            jPanelBienvenidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 515, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab8", jPanelBienvenida);

        jPanelNuevaVentaUsuario.setPreferredSize(new java.awt.Dimension(960, 432));

        jPanelContenedorNuevaVentaUsuario.setBackground(new java.awt.Color(204, 188, 255));

        jLabel13.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel13.setText("Codigo Producto *");

        jLabel14.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel14.setText("Nombre Producto");

        jLabel15.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel15.setText("Precio Factura");

        jLabel16.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel16.setText("Precio a Vender *");

        jLabel17.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel17.setText("Cantidad *");

        jLabel18.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel18.setText("Codigo Cliente *");

        jLabel19.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel19.setText("Nombre Cliente");

        txtMisVentasAgregarCodigoProducto.setBackground(new java.awt.Color(204, 188, 255));
        txtMisVentasAgregarCodigoProducto.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 102, 51)));
        txtMisVentasAgregarCodigoProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMisVentasAgregarCodigoProductoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMisVentasAgregarCodigoProductoKeyTyped(evt);
            }
        });

        txtMisVentasAgregarNombreProducto.setBackground(new java.awt.Color(204, 188, 255));
        txtMisVentasAgregarNombreProducto.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 102, 51)));
        txtMisVentasAgregarNombreProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMisVentasAgregarNombreProductoKeyTyped(evt);
            }
        });

        txtMisVentasAgregarPrecioFactura.setEditable(false);
        txtMisVentasAgregarPrecioFactura.setBackground(new java.awt.Color(204, 188, 255));
        txtMisVentasAgregarPrecioFactura.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 102, 51)));

        txtMisVentasAgregarPrecioVendido.setBackground(new java.awt.Color(204, 188, 255));
        txtMisVentasAgregarPrecioVendido.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 102, 51)));
        txtMisVentasAgregarPrecioVendido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMisVentasAgregarPrecioVendidoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMisVentasAgregarPrecioVendidoKeyTyped(evt);
            }
        });

        txtMisVentasAgregarCodigoCliente.setBackground(new java.awt.Color(204, 188, 255));
        txtMisVentasAgregarCodigoCliente.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 102, 51)));
        txtMisVentasAgregarCodigoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMisVentasAgregarCodigoClienteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMisVentasAgregarCodigoClienteKeyTyped(evt);
            }
        });

        txtMisVentasAgregarNombreCliente.setBackground(new java.awt.Color(204, 188, 255));
        txtMisVentasAgregarNombreCliente.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 102, 51)));
        txtMisVentasAgregarNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMisVentasAgregarNombreClienteKeyTyped(evt);
            }
        });

        btnMisVentasAgregarGuardar.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        btnMisVentasAgregarGuardar.setText("Guardar Venta");
        btnMisVentasAgregarGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMisVentasAgregarGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMisVentasAgregarGuardarActionPerformed(evt);
            }
        });

        btnMisVentasAgregarEliminar.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        btnMisVentasAgregarEliminar.setText("Eliminar");
        btnMisVentasAgregarEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMisVentasAgregarEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMisVentasAgregarEliminarActionPerformed(evt);
            }
        });

        tableMisVentasAgregar.setBackground(new java.awt.Color(204, 203, 255));
        tableMisVentasAgregar.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        tableMisVentasAgregar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Producto", "Nombre Producto", "Cantidad", "Precio Factura", "Precio Vendido", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableMisVentasAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMisVentasAgregarMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableMisVentasAgregar);
        if (tableMisVentasAgregar.getColumnModel().getColumnCount() > 0) {
            tableMisVentasAgregar.getColumnModel().getColumn(0).setPreferredWidth(10);
            tableMisVentasAgregar.getColumnModel().getColumn(1).setPreferredWidth(30);
            tableMisVentasAgregar.getColumnModel().getColumn(2).setPreferredWidth(10);
            tableMisVentasAgregar.getColumnModel().getColumn(3).setPreferredWidth(15);
            tableMisVentasAgregar.getColumnModel().getColumn(4).setPreferredWidth(15);
            tableMisVentasAgregar.getColumnModel().getColumn(5).setPreferredWidth(15);
        }

        jLabel24.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel24.setText("Total a Pagar: ");

        labelMisVentasAgregarTotalPagar.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        labelMisVentasAgregarTotalPagar.setText("---------");

        spinnerMisVentasAgregarCantidad.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 3, 0, 3, new java.awt.Color(255, 102, 0)));
        spinnerMisVentasAgregarCantidad.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        spinnerMisVentasAgregarCantidad.setFocusable(false);

        txtMisVentasAgregarIdProducto.setBorder(null);

        javax.swing.GroupLayout jPanelContenedorNuevaVentaUsuarioLayout = new javax.swing.GroupLayout(jPanelContenedorNuevaVentaUsuario);
        jPanelContenedorNuevaVentaUsuario.setLayout(jPanelContenedorNuevaVentaUsuarioLayout);
        jPanelContenedorNuevaVentaUsuarioLayout.setHorizontalGroup(
            jPanelContenedorNuevaVentaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContenedorNuevaVentaUsuarioLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelContenedorNuevaVentaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelContenedorNuevaVentaUsuarioLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel14)
                        .addGap(55, 55, 55)
                        .addComponent(jLabel15)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel17)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel16))
                    .addGroup(jPanelContenedorNuevaVentaUsuarioLayout.createSequentialGroup()
                        .addComponent(txtMisVentasAgregarCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txtMisVentasAgregarNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtMisVentasAgregarPrecioFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(spinnerMisVentasAgregarCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(txtMisVentasAgregarPrecioVendido, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtMisVentasAgregarIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnMisVentasAgregarEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelContenedorNuevaVentaUsuarioLayout.createSequentialGroup()
                        .addGroup(jPanelContenedorNuevaVentaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(txtMisVentasAgregarCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanelContenedorNuevaVentaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(txtMisVentasAgregarNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(80, 80, 80)
                        .addComponent(btnMisVentasAgregarGuardar)
                        .addGap(36, 36, 36)
                        .addComponent(txtMisVentasAgregarDireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(txtMisVentasAgregarTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel24)
                        .addGap(10, 10, 10)
                        .addComponent(labelMisVentasAgregarTotalPagar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelContenedorNuevaVentaUsuarioLayout.setVerticalGroup(
            jPanelContenedorNuevaVentaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContenedorNuevaVentaUsuarioLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelContenedorNuevaVentaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16))
                .addGap(2, 2, 2)
                .addGroup(jPanelContenedorNuevaVentaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spinnerMisVentasAgregarCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelContenedorNuevaVentaUsuarioLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanelContenedorNuevaVentaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMisVentasAgregarCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMisVentasAgregarNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMisVentasAgregarPrecioFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMisVentasAgregarPrecioVendido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMisVentasAgregarIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMisVentasAgregarEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanelContenedorNuevaVentaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelContenedorNuevaVentaUsuarioLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(12, 12, 12)
                        .addComponent(txtMisVentasAgregarCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelContenedorNuevaVentaUsuarioLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(12, 12, 12)
                        .addComponent(txtMisVentasAgregarNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelContenedorNuevaVentaUsuarioLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnMisVentasAgregarGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtMisVentasAgregarDireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMisVentasAgregarTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelContenedorNuevaVentaUsuarioLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel24))
                    .addGroup(jPanelContenedorNuevaVentaUsuarioLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(labelMisVentasAgregarTotalPagar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblTab1NuevaVentaVendedor.setBackground(new java.awt.Color(255, 204, 255));
        lblTab1NuevaVentaVendedor.setFont(new java.awt.Font("MV Boli", 0, 24)); // NOI18N
        lblTab1NuevaVentaVendedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTab1NuevaVentaVendedor.setText("PARA VENDEDOR");
        lblTab1NuevaVentaVendedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTab1NuevaVentaVendedor.setOpaque(true);
        lblTab1NuevaVentaVendedor.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblTab1NuevaVentaVendedorMouseMoved(evt);
            }
        });
        lblTab1NuevaVentaVendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTab1NuevaVentaVendedorMouseClicked(evt);
            }
        });

        lblTab1NuevaVentaUsuario.setBackground(new java.awt.Color(204, 188, 255));
        lblTab1NuevaVentaUsuario.setFont(new java.awt.Font("MV Boli", 0, 24)); // NOI18N
        lblTab1NuevaVentaUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTab1NuevaVentaUsuario.setText("PARA USUARIO");
        lblTab1NuevaVentaUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTab1NuevaVentaUsuario.setOpaque(true);
        lblTab1NuevaVentaUsuario.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblTab1NuevaVentaUsuarioMouseMoved(evt);
            }
        });
        lblTab1NuevaVentaUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTab1NuevaVentaUsuarioMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelNuevaVentaUsuarioLayout = new javax.swing.GroupLayout(jPanelNuevaVentaUsuario);
        jPanelNuevaVentaUsuario.setLayout(jPanelNuevaVentaUsuarioLayout);
        jPanelNuevaVentaUsuarioLayout.setHorizontalGroup(
            jPanelNuevaVentaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNuevaVentaUsuarioLayout.createSequentialGroup()
                .addGap(470, 470, 470)
                .addComponent(lblTab1NuevaVentaVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanelContenedorNuevaVentaUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblTab1NuevaVentaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanelNuevaVentaUsuarioLayout.setVerticalGroup(
            jPanelNuevaVentaUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTab1NuevaVentaVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanelNuevaVentaUsuarioLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jPanelContenedorNuevaVentaUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblTab1NuevaVentaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTabbedPane1.addTab("tab1", jPanelNuevaVentaUsuario);

        jPanelNuevaVentaVendedor.setBackground(new java.awt.Color(255, 204, 255));

        jPanelContenedorNuevaVentaVendedor.setBackground(new java.awt.Color(255, 204, 255));

        jLabel3.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel3.setText("Codigo Producto *");

        jLabel4.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel4.setText("Nombre Producto");

        txtMisVendedoresAgregarCodigoProducto.setBackground(new java.awt.Color(255, 204, 255));
        txtMisVendedoresAgregarCodigoProducto.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 102, 51)));
        txtMisVendedoresAgregarCodigoProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMisVendedoresAgregarCodigoProductoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMisVendedoresAgregarCodigoProductoKeyTyped(evt);
            }
        });

        txtMisVendedoresAgregarNombreProducto.setBackground(new java.awt.Color(255, 204, 255));
        txtMisVendedoresAgregarNombreProducto.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 102, 51)));
        txtMisVendedoresAgregarNombreProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMisVendedoresAgregarNombreProductoKeyTyped(evt);
            }
        });

        btnMisVendedoresAgregarEliminar.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        btnMisVendedoresAgregarEliminar.setText("Eliminar");
        btnMisVendedoresAgregarEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMisVendedoresAgregarEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMisVendedoresAgregarEliminarActionPerformed(evt);
            }
        });

        tableMisVendedoresAgregar.setBackground(new java.awt.Color(255, 181, 255));
        tableMisVendedoresAgregar.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        tableMisVendedoresAgregar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Producto", "Nombre Producto", "Cantidad", "Precio", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableMisVendedoresAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMisVendedoresAgregarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableMisVendedoresAgregar);
        if (tableMisVendedoresAgregar.getColumnModel().getColumnCount() > 0) {
            tableMisVendedoresAgregar.getColumnModel().getColumn(0).setPreferredWidth(10);
            tableMisVendedoresAgregar.getColumnModel().getColumn(1).setPreferredWidth(30);
            tableMisVendedoresAgregar.getColumnModel().getColumn(2).setPreferredWidth(10);
            tableMisVendedoresAgregar.getColumnModel().getColumn(3).setPreferredWidth(15);
            tableMisVendedoresAgregar.getColumnModel().getColumn(4).setPreferredWidth(15);
        }

        jLabel6.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel6.setText("Precio");

        jLabel7.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel7.setText("Cantidad *");

        jLabel8.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel8.setText("Codigo Vendedor *");

        jLabel9.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel9.setText("Nombre Vendedor");

        txtMisVendedoresAgregarPrecio.setEditable(false);
        txtMisVendedoresAgregarPrecio.setBackground(new java.awt.Color(255, 204, 255));
        txtMisVendedoresAgregarPrecio.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 102, 51)));
        txtMisVendedoresAgregarPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMisVendedoresAgregarPrecioKeyPressed(evt);
            }
        });

        spinnerMisVendedoresAgregarCantidad.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 3, 0, 3, new java.awt.Color(255, 102, 51)));

        txtMisVendedoresAgregarCodigoVendedor.setBackground(new java.awt.Color(255, 204, 255));
        txtMisVendedoresAgregarCodigoVendedor.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 102, 51)));
        txtMisVendedoresAgregarCodigoVendedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMisVendedoresAgregarCodigoVendedorKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMisVendedoresAgregarCodigoVendedorKeyTyped(evt);
            }
        });

        txtMisVendedoresAgregarNombreVendedor.setBackground(new java.awt.Color(255, 204, 255));
        txtMisVendedoresAgregarNombreVendedor.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 102, 51)));
        txtMisVendedoresAgregarNombreVendedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMisVendedoresAgregarNombreVendedorKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel10.setText("Fecha Limite De Pago *");

        dcMisVendedoresAgregarFechaLimite.setBackground(new java.awt.Color(255, 204, 255));
        dcMisVendedoresAgregarFechaLimite.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 3, 0, 3, new java.awt.Color(255, 102, 51)));

        jLabel11.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel11.setText("Total a Pagar: ");

        labelMisVendedoresAgregarTotalPagar.setBackground(new java.awt.Color(255, 204, 255));
        labelMisVendedoresAgregarTotalPagar.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        labelMisVendedoresAgregarTotalPagar.setText("--------");

        btnMisVendedoresAgregarGuardar.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        btnMisVendedoresAgregarGuardar.setText("Guardar");
        btnMisVendedoresAgregarGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMisVendedoresAgregarGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMisVendedoresAgregarGuardarActionPerformed(evt);
            }
        });

        txtMisVendedoresAgregarIdProducto.setBackground(new java.awt.Color(255, 204, 255));
        txtMisVendedoresAgregarIdProducto.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 102, 51)));

        javax.swing.GroupLayout jPanelContenedorNuevaVentaVendedorLayout = new javax.swing.GroupLayout(jPanelContenedorNuevaVentaVendedor);
        jPanelContenedorNuevaVentaVendedor.setLayout(jPanelContenedorNuevaVentaVendedorLayout);
        jPanelContenedorNuevaVentaVendedorLayout.setHorizontalGroup(
            jPanelContenedorNuevaVentaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContenedorNuevaVentaVendedorLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanelContenedorNuevaVentaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelContenedorNuevaVentaVendedorLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel4)
                        .addGap(85, 85, 85)
                        .addComponent(jLabel7)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel6))
                    .addGroup(jPanelContenedorNuevaVentaVendedorLayout.createSequentialGroup()
                        .addComponent(txtMisVendedoresAgregarCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(txtMisVendedoresAgregarNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(spinnerMisVendedoresAgregarCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(txtMisVendedoresAgregarPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(btnMisVendedoresAgregarEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(txtMisVendedoresAgregarIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 877, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelContenedorNuevaVentaVendedorLayout.createSequentialGroup()
                        .addGroup(jPanelContenedorNuevaVentaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txtMisVendedoresAgregarCodigoVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanelContenedorNuevaVentaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(txtMisVendedoresAgregarNombreVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanelContenedorNuevaVentaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(dcMisVendedoresAgregarFechaLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(btnMisVendedoresAgregarGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addGap(0, 0, 0)
                        .addComponent(labelMisVendedoresAgregarTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanelContenedorNuevaVentaVendedorLayout.setVerticalGroup(
            jPanelContenedorNuevaVentaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContenedorNuevaVentaVendedorLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanelContenedorNuevaVentaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addGap(2, 2, 2)
                .addGroup(jPanelContenedorNuevaVentaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spinnerMisVendedoresAgregarCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMisVendedoresAgregarEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelContenedorNuevaVentaVendedorLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanelContenedorNuevaVentaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMisVendedoresAgregarCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMisVendedoresAgregarNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMisVendedoresAgregarPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMisVendedoresAgregarIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanelContenedorNuevaVentaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelContenedorNuevaVentaVendedorLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(2, 2, 2)
                        .addComponent(txtMisVendedoresAgregarCodigoVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelContenedorNuevaVentaVendedorLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(2, 2, 2)
                        .addComponent(txtMisVendedoresAgregarNombreVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelContenedorNuevaVentaVendedorLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(2, 2, 2)
                        .addComponent(dcMisVendedoresAgregarFechaLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelContenedorNuevaVentaVendedorLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnMisVendedoresAgregarGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelContenedorNuevaVentaVendedorLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel11))
                    .addGroup(jPanelContenedorNuevaVentaVendedorLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(labelMisVendedoresAgregarTotalPagar)))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        lblTab2NuevaVentaUsuario.setBackground(new java.awt.Color(204, 188, 255));
        lblTab2NuevaVentaUsuario.setFont(new java.awt.Font("MV Boli", 0, 24)); // NOI18N
        lblTab2NuevaVentaUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTab2NuevaVentaUsuario.setText("PARA USUARIO");
        lblTab2NuevaVentaUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTab2NuevaVentaUsuario.setOpaque(true);
        lblTab2NuevaVentaUsuario.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblTab2NuevaVentaUsuarioMouseMoved(evt);
            }
        });
        lblTab2NuevaVentaUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTab2NuevaVentaUsuarioMouseClicked(evt);
            }
        });

        lblTab2NuevaVentaVendedor.setBackground(new java.awt.Color(255, 204, 255));
        lblTab2NuevaVentaVendedor.setFont(new java.awt.Font("MV Boli", 0, 24)); // NOI18N
        lblTab2NuevaVentaVendedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTab2NuevaVentaVendedor.setText("PARA VENDEDOR");
        lblTab2NuevaVentaVendedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTab2NuevaVentaVendedor.setOpaque(true);
        lblTab2NuevaVentaVendedor.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblTab2NuevaVentaVendedorMouseMoved(evt);
            }
        });

        javax.swing.GroupLayout jPanelNuevaVentaVendedorLayout = new javax.swing.GroupLayout(jPanelNuevaVentaVendedor);
        jPanelNuevaVentaVendedor.setLayout(jPanelNuevaVentaVendedorLayout);
        jPanelNuevaVentaVendedorLayout.setHorizontalGroup(
            jPanelNuevaVentaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNuevaVentaVendedorLayout.createSequentialGroup()
                .addComponent(lblTab2NuevaVentaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblTab2NuevaVentaVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanelContenedorNuevaVentaVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanelNuevaVentaVendedorLayout.setVerticalGroup(
            jPanelNuevaVentaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNuevaVentaVendedorLayout.createSequentialGroup()
                .addGroup(jPanelNuevaVentaVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTab2NuevaVentaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTab2NuevaVentaVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jPanelContenedorNuevaVentaVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("tab2", jPanelNuevaVentaVendedor);

        jPanelProductos.setBackground(new java.awt.Color(204, 255, 204));

        jLabel30.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel30.setText("Codigo *");

        jLabel31.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel31.setText("Nombre *");

        jLabel32.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel32.setText("Precio *");

        txtProductosCodigo.setBackground(new java.awt.Color(204, 255, 204));
        txtProductosCodigo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 153, 153)));
        txtProductosCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProductosCodigoKeyTyped(evt);
            }
        });

        txtProductosNombre.setBackground(new java.awt.Color(204, 255, 204));
        txtProductosNombre.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 153, 153)));
        txtProductosNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProductosNombreKeyTyped(evt);
            }
        });

        txtProductosPrecio.setBackground(new java.awt.Color(204, 255, 204));
        txtProductosPrecio.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 153, 153)));
        txtProductosPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProductosPrecioKeyTyped(evt);
            }
        });

        btnProductosActualizar.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        btnProductosActualizar.setText("Actualizar");
        btnProductosActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProductosActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActualizarActionPerformed(evt);
            }
        });

        btnProductosEliminar.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        btnProductosEliminar.setText("Eliminar");
        btnProductosEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProductosEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosEliminarActionPerformed(evt);
            }
        });

        tableProductos.setBackground(new java.awt.Color(153, 255, 204));
        tableProductos.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        tableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Codigo", "Nombre", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProductosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableProductos);
        if (tableProductos.getColumnModel().getColumnCount() > 0) {
            tableProductos.getColumnModel().getColumn(0).setPreferredWidth(15);
            tableProductos.getColumnModel().getColumn(1).setPreferredWidth(15);
            tableProductos.getColumnModel().getColumn(2).setPreferredWidth(30);
            tableProductos.getColumnModel().getColumn(3).setPreferredWidth(15);
        }

        btnProductosPdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pdf.png"))); // NOI18N
        btnProductosPdf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnProductosGuardar.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        btnProductosGuardar.setText("Guardar");
        btnProductosGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProductosGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosGuardarActionPerformed(evt);
            }
        });

        btnProductosNuevo.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        btnProductosNuevo.setText("Nuevo");
        btnProductosNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProductosNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelProductosLayout = new javax.swing.GroupLayout(jPanelProductos);
        jPanelProductos.setLayout(jPanelProductosLayout);
        jPanelProductosLayout.setHorizontalGroup(
            jPanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProductosLayout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addGroup(jPanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelProductosLayout.createSequentialGroup()
                        .addGroup(jPanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelProductosLayout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addGap(70, 70, 70)
                                .addComponent(jLabel31)
                                .addGap(66, 66, 66)
                                .addComponent(jLabel32))
                            .addGroup(jPanelProductosLayout.createSequentialGroup()
                                .addGroup(jPanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtProductosCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnProductosGuardar))
                                .addGap(29, 29, 29)
                                .addGroup(jPanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtProductosNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnProductosActualizar))
                                .addGap(30, 30, 30)
                                .addGroup(jPanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnProductosEliminar)
                                    .addComponent(txtProductosPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelProductosLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(btnProductosNuevo)
                                .addGap(105, 105, 105)
                                .addComponent(btnProductosPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelProductosLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(txtProductosId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(32, 32, 32))
        );
        jPanelProductosLayout.setVerticalGroup(
            jPanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProductosLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelProductosLayout.createSequentialGroup()
                        .addGroup(jPanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32))
                        .addGap(2, 2, 2)
                        .addGroup(jPanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtProductosNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtProductosCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtProductosPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtProductosId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28)
                        .addGroup(jPanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnProductosGuardar)
                            .addComponent(btnProductosActualizar)
                            .addComponent(btnProductosEliminar)
                            .addComponent(btnProductosNuevo)))
                    .addComponent(btnProductosPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab3", jPanelProductos);

        jPanelClientes.setBackground(new java.awt.Color(204, 255, 204));

        jLabel34.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel34.setText("Codigo *");

        jLabel35.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel35.setText("Nombre *");

        jLabel36.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel36.setText("Direccion");

        txtClientesCodigo.setBackground(new java.awt.Color(204, 255, 204));
        txtClientesCodigo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 153, 153)));
        txtClientesCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClientesCodigoKeyTyped(evt);
            }
        });

        txtClientesNombre.setBackground(new java.awt.Color(204, 255, 204));
        txtClientesNombre.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 153, 153)));
        txtClientesNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClientesNombreKeyTyped(evt);
            }
        });

        txtClientesDireccion.setBackground(new java.awt.Color(204, 255, 204));
        txtClientesDireccion.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 153, 153)));
        txtClientesDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClientesDireccionKeyTyped(evt);
            }
        });

        btnClientesActualizar.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        btnClientesActualizar.setText("Actualizar");
        btnClientesActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClientesActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActualizarActionPerformed(evt);
            }
        });

        btnClientesGuardar.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        btnClientesGuardar.setText("Guardar");
        btnClientesGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClientesGuardar.setMaximumSize(new java.awt.Dimension(103, 27));
        btnClientesGuardar.setMinimumSize(new java.awt.Dimension(103, 27));
        btnClientesGuardar.setPreferredSize(new java.awt.Dimension(103, 27));
        btnClientesGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesGuardarActionPerformed(evt);
            }
        });

        btnClientesEliminar.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        btnClientesEliminar.setText("Eliminar");
        btnClientesEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClientesEliminar.setMaximumSize(new java.awt.Dimension(103, 27));
        btnClientesEliminar.setMinimumSize(new java.awt.Dimension(103, 27));
        btnClientesEliminar.setPreferredSize(new java.awt.Dimension(103, 27));
        btnClientesEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesEliminarActionPerformed(evt);
            }
        });

        btnClientesEliminarTodo.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        btnClientesEliminarTodo.setText("Eliminar Todo");
        btnClientesEliminarTodo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClientesEliminarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesEliminarTodoActionPerformed(evt);
            }
        });

        tableClientes.setBackground(new java.awt.Color(153, 255, 204));
        tableClientes.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        tableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Codigo", "Nombre", "Direccion", "Telefono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableClientesMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tableClientes);
        if (tableClientes.getColumnModel().getColumnCount() > 0) {
            tableClientes.getColumnModel().getColumn(0).setPreferredWidth(10);
            tableClientes.getColumnModel().getColumn(1).setPreferredWidth(15);
            tableClientes.getColumnModel().getColumn(2).setPreferredWidth(30);
            tableClientes.getColumnModel().getColumn(3).setPreferredWidth(30);
            tableClientes.getColumnModel().getColumn(4).setPreferredWidth(15);
        }

        btnClientesPdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pdf.png"))); // NOI18N
        btnClientesPdf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel38.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel38.setText("Telefono");

        txtClientesTelefono.setBackground(new java.awt.Color(204, 255, 204));
        txtClientesTelefono.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 153, 153)));
        txtClientesTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClientesTelefonoKeyTyped(evt);
            }
        });

        txtClientesId.setEditable(false);

        btnClientesNuevo.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        btnClientesNuevo.setText("Nuevo");
        btnClientesNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClientesNuevo.setMaximumSize(new java.awt.Dimension(103, 27));
        btnClientesNuevo.setMinimumSize(new java.awt.Dimension(103, 27));
        btnClientesNuevo.setPreferredSize(new java.awt.Dimension(103, 27));
        btnClientesNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelClientesLayout = new javax.swing.GroupLayout(jPanelClientes);
        jPanelClientes.setLayout(jPanelClientesLayout);
        jPanelClientesLayout.setHorizontalGroup(
            jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClientesLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelClientesLayout.createSequentialGroup()
                        .addGroup(jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel34)
                            .addComponent(txtClientesCodigo)
                            .addComponent(btnClientesGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanelClientesLayout.createSequentialGroup()
                                    .addComponent(jLabel35)
                                    .addGap(91, 91, 91))
                                .addGroup(jPanelClientesLayout.createSequentialGroup()
                                    .addComponent(txtClientesNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(30, 30, 30)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelClientesLayout.createSequentialGroup()
                                .addComponent(btnClientesActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)))
                        .addGroup(jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtClientesDireccion)
                            .addComponent(jLabel36)
                            .addComponent(btnClientesEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelClientesLayout.createSequentialGroup()
                                .addGroup(jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel38)
                                    .addGroup(jPanelClientesLayout.createSequentialGroup()
                                        .addComponent(txtClientesTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(49, 49, 49)
                                        .addComponent(txtClientesId, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(244, 244, 244))
                            .addGroup(jPanelClientesLayout.createSequentialGroup()
                                .addGroup(jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnClientesEliminarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanelClientesLayout.createSequentialGroup()
                                        .addGap(183, 183, 183)
                                        .addComponent(btnClientesNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnClientesPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74))))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 877, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );
        jPanelClientesLayout.setVerticalGroup(
            jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClientesLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel34)
                        .addComponent(jLabel35))
                    .addGroup(jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel38)
                        .addComponent(jLabel36)))
                .addGap(12, 12, 12)
                .addGroup(jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtClientesNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtClientesCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtClientesDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtClientesTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtClientesId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnClientesActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnClientesGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnClientesEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnClientesEliminarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnClientesNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnClientesPdf))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab4", jPanelClientes);

        jPanelVendedores.setBackground(new java.awt.Color(204, 255, 204));

        jLabel39.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel39.setText("Codigo *");

        jLabel40.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel40.setText("Nombre *");

        jLabel41.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel41.setText("Telefono");

        jLabel42.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel42.setText("Direccion");

        txtVendedoresCodigo.setBackground(new java.awt.Color(204, 255, 204));
        txtVendedoresCodigo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 153, 153)));
        txtVendedoresCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVendedoresCodigoKeyTyped(evt);
            }
        });

        txtVendedoresNombre.setBackground(new java.awt.Color(204, 255, 204));
        txtVendedoresNombre.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 153, 153)));
        txtVendedoresNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVendedoresNombreKeyTyped(evt);
            }
        });

        txtVendedoresTelefono.setBackground(new java.awt.Color(204, 255, 204));
        txtVendedoresTelefono.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 153, 153)));
        txtVendedoresTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVendedoresTelefonoKeyTyped(evt);
            }
        });

        txtVendedoresDireccion.setBackground(new java.awt.Color(204, 255, 204));
        txtVendedoresDireccion.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 153, 153)));
        txtVendedoresDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVendedoresDireccionKeyTyped(evt);
            }
        });

        btnVendedoresPdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pdf.png"))); // NOI18N
        btnVendedoresPdf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnVendedoresGuardar.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        btnVendedoresGuardar.setText("Guardar");
        btnVendedoresGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVendedoresGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVendedoresGuardarActionPerformed(evt);
            }
        });

        btnVendedoresActualizar.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        btnVendedoresActualizar.setText("Actualizar");
        btnVendedoresActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVendedoresActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVendedoresActualizarActionPerformed(evt);
            }
        });

        btnVendedoresEliminar.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        btnVendedoresEliminar.setText("Eliminar");
        btnVendedoresEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVendedoresEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVendedoresEliminarActionPerformed(evt);
            }
        });

        tableVendedores.setBackground(new java.awt.Color(153, 255, 204));
        tableVendedores.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        tableVendedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Codigo", "Nombre", "Telefono", "Direccion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableVendedores.setGridColor(new java.awt.Color(153, 153, 153));
        tableVendedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableVendedoresMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tableVendedores);
        if (tableVendedores.getColumnModel().getColumnCount() > 0) {
            tableVendedores.getColumnModel().getColumn(0).setPreferredWidth(10);
            tableVendedores.getColumnModel().getColumn(1).setPreferredWidth(10);
            tableVendedores.getColumnModel().getColumn(2).setPreferredWidth(30);
            tableVendedores.getColumnModel().getColumn(3).setPreferredWidth(20);
            tableVendedores.getColumnModel().getColumn(4).setPreferredWidth(30);
        }

        btnVendedoresNuevo.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        btnVendedoresNuevo.setText("Nuevo");
        btnVendedoresNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVendedoresNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVendedoresNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelVendedoresLayout = new javax.swing.GroupLayout(jPanelVendedores);
        jPanelVendedores.setLayout(jPanelVendedoresLayout);
        jPanelVendedoresLayout.setHorizontalGroup(
            jPanelVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVendedoresLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanelVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelVendedoresLayout.createSequentialGroup()
                        .addGroup(jPanelVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel39)
                            .addComponent(txtVendedoresCodigo)
                            .addComponent(btnVendedoresGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel40)
                            .addComponent(txtVendedoresNombre)
                            .addComponent(btnVendedoresActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(jPanelVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41)
                            .addComponent(txtVendedoresTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVendedoresEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanelVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelVendedoresLayout.createSequentialGroup()
                                .addGroup(jPanelVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel42)
                                    .addComponent(txtVendedoresDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(90, 90, 90)
                                .addComponent(txtVendedoresId, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelVendedoresLayout.createSequentialGroup()
                                .addComponent(btnVendedoresNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnVendedoresPdf)))))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanelVendedoresLayout.setVerticalGroup(
            jPanelVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVendedoresLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanelVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelVendedoresLayout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVendedoresTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(jPanelVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnVendedoresEliminar)
                            .addComponent(btnVendedoresNuevo)))
                    .addGroup(jPanelVendedoresLayout.createSequentialGroup()
                        .addGroup(jPanelVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanelVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelVendedoresLayout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addComponent(txtVendedoresId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelVendedoresLayout.createSequentialGroup()
                                        .addComponent(jLabel39)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtVendedoresCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanelVendedoresLayout.createSequentialGroup()
                                    .addComponent(jLabel42)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtVendedoresDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelVendedoresLayout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtVendedoresNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28)
                        .addGroup(jPanelVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnVendedoresGuardar)
                            .addComponent(btnVendedoresActualizar)
                            .addComponent(btnVendedoresPdf))))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab5", jPanelVendedores);

        jPanelVentasUsuario.setBackground(new java.awt.Color(255, 204, 255));

        tableMisVentasRegistro.setBackground(new java.awt.Color(255, 153, 255));
        tableMisVentasRegistro.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        tableMisVentasRegistro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Codigo Cliente", "Nombre Cliente", "Debe", "Abonado", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableMisVentasRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMisVentasRegistroMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tableMisVentasRegistro);
        if (tableMisVentasRegistro.getColumnModel().getColumnCount() > 0) {
            tableMisVentasRegistro.getColumnModel().getColumn(0).setPreferredWidth(10);
            tableMisVentasRegistro.getColumnModel().getColumn(1).setPreferredWidth(10);
            tableMisVentasRegistro.getColumnModel().getColumn(2).setPreferredWidth(30);
            tableMisVentasRegistro.getColumnModel().getColumn(3).setPreferredWidth(15);
            tableMisVentasRegistro.getColumnModel().getColumn(4).setPreferredWidth(15);
            tableMisVentasRegistro.getColumnModel().getColumn(5).setPreferredWidth(15);
        }

        jLabel1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel1.setText("Abonar");

        txtMisVentasRegistroAbonar.setBackground(new java.awt.Color(255, 204, 255));
        txtMisVentasRegistroAbonar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 102, 51)));
        txtMisVentasRegistroAbonar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMisVentasRegistroAbonarKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel5.setText("Codigo Cliente");

        jLabel12.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel12.setText("Nombre Cliente");

        txtMisVentasRegistroCodigoCliente.setEditable(false);
        txtMisVentasRegistroCodigoCliente.setBackground(new java.awt.Color(255, 204, 255));
        txtMisVentasRegistroCodigoCliente.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 102, 51)));

        txtMisVentasRegistroNombreCliente.setEditable(false);
        txtMisVentasRegistroNombreCliente.setBackground(new java.awt.Color(255, 204, 255));
        txtMisVentasRegistroNombreCliente.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 102, 51)));

        jLabel21.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel21.setText("Deuda");

        txtMisVentasRegistroDeuda.setEditable(false);
        txtMisVentasRegistroDeuda.setBackground(new java.awt.Color(255, 204, 255));
        txtMisVentasRegistroDeuda.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 102, 51)));

        btnMisVentasRegistroActualizar.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        btnMisVentasRegistroActualizar.setText("Actualizar");
        btnMisVentasRegistroActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMisVentasRegistroActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMisVentasRegistroActualizarActionPerformed(evt);
            }
        });

        btnMisVentasRegistroEliminar.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        btnMisVentasRegistroEliminar.setText("Eliminar");
        btnMisVentasRegistroEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMisVentasRegistroEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMisVentasRegistroEliminarActionPerformed(evt);
            }
        });

        MisVentasRegistroEliminarTodo.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        MisVentasRegistroEliminarTodo.setText("Eliminar Todo");
        MisVentasRegistroEliminarTodo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MisVentasRegistroEliminarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MisVentasRegistroEliminarTodoActionPerformed(evt);
            }
        });

        btnMisVentasRegistrosPdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pdf.png"))); // NOI18N
        btnMisVentasRegistrosPdf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMisVentasRegistrosPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMisVentasRegistrosPdfActionPerformed(evt);
            }
        });

        jLabel25.setBackground(new java.awt.Color(255, 204, 204));
        jLabel25.setFont(new java.awt.Font("Monotype Corsiva", 0, 36)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 153, 153));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("MIS VENTAS");
        jLabel25.setOpaque(true);

        javax.swing.GroupLayout jPanelVentasUsuarioLayout = new javax.swing.GroupLayout(jPanelVentasUsuario);
        jPanelVentasUsuario.setLayout(jPanelVentasUsuarioLayout);
        jPanelVentasUsuarioLayout.setHorizontalGroup(
            jPanelVentasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVentasUsuarioLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 953, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelVentasUsuarioLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(txtMisVentasRegistroId, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanelVentasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtMisVentasRegistroCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanelVentasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(txtMisVentasRegistroNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanelVentasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(txtMisVentasRegistroDeuda, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanelVentasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtMisVentasRegistroAbonar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanelVentasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelVentasUsuarioLayout.createSequentialGroup()
                        .addComponent(btnMisVentasRegistroActualizar)
                        .addGap(19, 19, 19)
                        .addComponent(btnMisVentasRegistroEliminar))
                    .addComponent(MisVentasRegistroEliminarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(btnMisVentasRegistrosPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelVentasUsuarioLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 877, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelVentasUsuarioLayout.setVerticalGroup(
            jPanelVentasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVentasUsuarioLayout.createSequentialGroup()
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanelVentasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelVentasUsuarioLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(txtMisVentasRegistroId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelVentasUsuarioLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(12, 12, 12)
                        .addComponent(txtMisVentasRegistroCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelVentasUsuarioLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(12, 12, 12)
                        .addComponent(txtMisVentasRegistroNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelVentasUsuarioLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(12, 12, 12)
                        .addComponent(txtMisVentasRegistroDeuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelVentasUsuarioLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(12, 12, 12)
                        .addComponent(txtMisVentasRegistroAbonar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelVentasUsuarioLayout.createSequentialGroup()
                        .addGroup(jPanelVentasUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnMisVentasRegistroActualizar)
                            .addComponent(btnMisVentasRegistroEliminar))
                        .addGap(4, 4, 4)
                        .addComponent(MisVentasRegistroEliminarTodo))
                    .addGroup(jPanelVentasUsuarioLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnMisVentasRegistrosPdf)))
                .addGap(4, 4, 4)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("tab6", jPanelVentasUsuario);

        jPanelVentasVendedores.setBackground(new java.awt.Color(255, 204, 255));

        tableMisVendedoresRegistro.setBackground(new java.awt.Color(255, 153, 255));
        tableMisVendedoresRegistro.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        tableMisVendedoresRegistro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Codigo Vendedor", "Nombre Vendedor", "Fecha Limite", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableMisVendedoresRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMisVendedoresRegistroMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tableMisVendedoresRegistro);
        if (tableMisVendedoresRegistro.getColumnModel().getColumnCount() > 0) {
            tableMisVendedoresRegistro.getColumnModel().getColumn(0).setPreferredWidth(10);
            tableMisVendedoresRegistro.getColumnModel().getColumn(1).setPreferredWidth(10);
            tableMisVendedoresRegistro.getColumnModel().getColumn(2).setPreferredWidth(30);
            tableMisVendedoresRegistro.getColumnModel().getColumn(3).setPreferredWidth(15);
            tableMisVendedoresRegistro.getColumnModel().getColumn(4).setPreferredWidth(15);
        }

        btnMisVendedoresRegistroPdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pdf.png"))); // NOI18N
        btnMisVendedoresRegistroPdf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMisVendedoresRegistroPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMisVendedoresRegistroPdfActionPerformed(evt);
            }
        });

        btnMisVendedoresRegistroEliminar.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        btnMisVendedoresRegistroEliminar.setText("Eliminar");
        btnMisVendedoresRegistroEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMisVendedoresRegistroEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMisVendedoresRegistroEliminarActionPerformed(evt);
            }
        });

        btnMisVendedoresRegistroEliminarTodo.setFont(new java.awt.Font("Rockwell", 0, 15)); // NOI18N
        btnMisVendedoresRegistroEliminarTodo.setText("Eliminar Todo");
        btnMisVendedoresRegistroEliminarTodo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMisVendedoresRegistroEliminarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMisVendedoresRegistroEliminarTodoActionPerformed(evt);
            }
        });

        jLabel47.setBackground(new java.awt.Color(255, 204, 204));
        jLabel47.setFont(new java.awt.Font("Monotype Corsiva", 0, 36)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 153, 153));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("VENTAS DE MIS VENDEDORES");
        jLabel47.setOpaque(true);

        javax.swing.GroupLayout jPanelVentasVendedoresLayout = new javax.swing.GroupLayout(jPanelVentasVendedores);
        jPanelVentasVendedores.setLayout(jPanelVentasVendedoresLayout);
        jPanelVentasVendedoresLayout.setHorizontalGroup(
            jPanelVentasVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanelVentasVendedoresLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelVentasVendedoresLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(txtMisVendedoresRegistroId, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(398, 398, 398)
                .addComponent(btnMisVendedoresRegistroEliminar)
                .addGap(30, 30, 30)
                .addComponent(btnMisVendedoresRegistroEliminarTodo)
                .addGap(60, 60, 60)
                .addComponent(btnMisVendedoresRegistroPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelVentasVendedoresLayout.setVerticalGroup(
            jPanelVentasVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVentasVendedoresLayout.createSequentialGroup()
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanelVentasVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMisVendedoresRegistroPdf)
                    .addGroup(jPanelVentasVendedoresLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanelVentasVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMisVendedoresRegistroId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMisVendedoresRegistroEliminar)
                            .addComponent(btnMisVendedoresRegistroEliminarTodo)))))
        );

        jTabbedPane1.addTab("tab7", jPanelVentasVendedores);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 960, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        // TODO add your handling code here:
        if (ultimoBotonSeleccionado != null) {
            ultimoBotonSeleccionado.setBackground(new Color(255, 153, 153)); // Restaura el color original
        }
        JButton botonActual = (JButton) evt.getSource();
        botonActual.setBackground(new Color(255, 153, 255)); // Cambia el color del botón actual
        ultimoBotonSeleccionado = botonActual;

        LimpiarTable();
        LimpiarCliente();
        ListarClientes();
        jTabbedPane1.setSelectedComponent(jPanelClientes);
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnVendedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendedoresActionPerformed
        // TODO add your handling code here:
        if (ultimoBotonSeleccionado != null) {
            ultimoBotonSeleccionado.setBackground(new Color(255, 153, 153)); // Restaura el color original
        }
        JButton botonActual = (JButton) evt.getSource();
        botonActual.setBackground(new Color(255, 153, 255)); // Cambia el color del botón actual
        ultimoBotonSeleccionado = botonActual;

        LimpiarTable();
        LimpiarVendedor();
        ListarVendedores();

        jTabbedPane1.setSelectedComponent(jPanelVendedores);
    }//GEN-LAST:event_btnVendedoresActionPerformed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        // TODO add your handling code here:
        if (ultimoBotonSeleccionado != null) {
            ultimoBotonSeleccionado.setBackground(new Color(255, 153, 153)); // Restaura el color original
        }
        JButton botonActual = (JButton) evt.getSource();
        botonActual.setBackground(new Color(255, 153, 255)); // Cambia el color del botón actual
        ultimoBotonSeleccionado = botonActual;

        LimpiarTable();
        LimpiarProducto();
        ListarProductos();
        jTabbedPane1.setSelectedComponent(jPanelProductos);
    }//GEN-LAST:event_btnProductosActionPerformed

    private void btnNuevaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaVentaActionPerformed
        // TODO add your handling code here:
        // Cambia el color del botón actual y restaura el color del botón anterior
        if (ultimoBotonSeleccionado != null) {
            ultimoBotonSeleccionado.setBackground(new Color(255, 153, 153)); // Restaura el color original
        }
        JButton botonActual = (JButton) evt.getSource();
        botonActual.setBackground(new Color(255, 153, 255)); // Cambia el color del botón actual
        ultimoBotonSeleccionado = botonActual;        
        
        this.jPanelContenedorNuevaVentaUsuario.setVisible(false);
        jTabbedPane1.setSelectedComponent(jPanelNuevaVentaUsuario);
//        String[] opcionVenta = {"Usuario", "Mis Vendedores"};
//        int opcionSeleccionada = JOptionPane.showOptionDialog(null, "Nueva venta para: ", "Nueva Venta", 0, JOptionPane.QUESTION_MESSAGE, null, opcionVenta, EXIT_ON_CLOSE);
//        if (opcionSeleccionada == 0) {
//            jTabbedPane1.setSelectedComponent(jPanelNuevaVentaUsuario);
//        }
//        if (opcionSeleccionada == 1) {
//            jTabbedPane1.setSelectedComponent(jPanelNuevaVentaVendedor);
//        }

    }//GEN-LAST:event_btnNuevaVentaActionPerformed

    private void btnTotalVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTotalVentasActionPerformed
        // TODO add your handling code here:
        if (ultimoBotonSeleccionado != null) {
            ultimoBotonSeleccionado.setBackground(new Color(255, 153, 153)); // Restaura el color original
        }
        JButton botonActual = (JButton) evt.getSource();
        botonActual.setBackground(new Color(255, 153, 255)); // Cambia el color del botón actual
        ultimoBotonSeleccionado = botonActual;
//-------------------------------------------------------------------------------
        String[] opcionVenta = {"Usuario", "Vendedor"};
        int opcionSeleccionada = JOptionPane.showOptionDialog(null, "¿Qué ventas desea ver?", "Venta Realizadas", 0, JOptionPane.QUESTION_MESSAGE, null, opcionVenta, EXIT_ON_CLOSE);
        if (opcionSeleccionada == 0) {
            jTabbedPane1.setSelectedComponent(jPanelVentasUsuario);
            LimpiarTable();
            ListarMisVentasRegistro();
        }
        if (opcionSeleccionada == 1) {
            jTabbedPane1.setSelectedComponent(jPanelVentasVendedores);
            LimpiarTable();
            ListarRegistroMisVendedores();
        }
    }//GEN-LAST:event_btnTotalVentasActionPerformed

    private void btnMisVentasRegistroMisCuentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMisVentasRegistroMisCuentasActionPerformed
        // TODO add your handling code here:
        if (ultimoBotonSeleccionado != null) {
            ultimoBotonSeleccionado.setBackground(new Color(255, 153, 153)); // Restaura el color original
        }
        JButton botonActual = (JButton) evt.getSource();
        botonActual.setBackground(new Color(255, 153, 255)); // Cambia el color del botón actual
        ultimoBotonSeleccionado = botonActual;

        if (misven.PagoCampanialMisCuentas() == BigDecimal.ZERO) { //Equivalente a: misven.PagoCampanialMisCuentas() == 0 
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Desea registrar un valor para pago de campaña?");
            if (confirmar == 0) {

                micu.registrarMisCuentas();
                micu.mostrarMisCuentas();
                micu.setVisible(true);
            }
        } else {
            micu.setVisible(true);
            micu.ActualizarMisCuentas();
            micu.mostrarMisCuentas();
        }

    }//GEN-LAST:event_btnMisVentasRegistroMisCuentasActionPerformed

    private void btnLogoPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoPersonalActionPerformed
        // TODO add your handling code here:
        acd.setVisible(true);
    }//GEN-LAST:event_btnLogoPersonalActionPerformed

    private void tableMisVentasAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMisVentasAgregarMouseClicked
        // TODO add your handling code here:
        int fila = tableMisVentasAgregar.rowAtPoint(evt.getPoint());
        txtMisVentasAgregarCodigoProducto.setText(tableMisVentasAgregar.getValueAt(fila, 0).toString());
        txtMisVentasAgregarNombreProducto.setText(tableMisVentasAgregar.getValueAt(fila, 1).toString());
        txtMisVentasAgregarPrecioFactura.setText(tableMisVentasAgregar.getValueAt(fila, 3).toString());
        txtMisVentasAgregarPrecioVendido.setText(tableMisVentasAgregar.getValueAt(fila, 4).toString());

        int cantidad = Integer.parseInt(tableMisVentasAgregar.getValueAt(fila, 2).toString());
        spinnerMisVentasAgregarCantidad.setValue(cantidad);
    }//GEN-LAST:event_tableMisVentasAgregarMouseClicked

    private void btnMisVentasAgregarEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMisVentasAgregarEliminarActionPerformed
        // TODO add your handling code here:
        if (tableMisVentasAgregar.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        } else {
            int pregunta = JOptionPane.showConfirmDialog(null, "¿Está Seguro De Eliminar?");
            if (pregunta == 0) {
                tmp = (DefaultTableModel) tableMisVentasAgregar.getModel();
                tmp.removeRow(tableMisVentasAgregar.getSelectedRow());
                TotalPagarMisVentas();
                LimpiarMiVenta();
                txtMisVentasAgregarCodigoProducto.requestFocus();
                labelMisVentasAgregarTotalPagar.setText("--------");
                JOptionPane.showMessageDialog(null, "Producto Eliminado Con Exito");

            }
        }
    }//GEN-LAST:event_btnMisVentasAgregarEliminarActionPerformed

    private void btnMisVentasAgregarGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMisVentasAgregarGuardarActionPerformed
        // TODO add your handling code here:
        if (tableMisVentasAgregar.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Debe ingresar productos a la tabla");
            return;
        }
        registrarMisVentas();
        registrarDetalleMisVentas();

        LimpiarTableAgregarMiVenta();
        LimpiarMiVenta();
        labelMisVentasAgregarTotalPagar.setText("-----");
        JOptionPane.showMessageDialog(null, "Venta agregada con éxito");
    }//GEN-LAST:event_btnMisVentasAgregarGuardarActionPerformed

    private void txtMisVentasAgregarNombreClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMisVentasAgregarNombreClienteKeyTyped
        // TODO add your handling code here:
        event.textKeyPress(evt);
    }//GEN-LAST:event_txtMisVentasAgregarNombreClienteKeyTyped

    private void txtMisVentasAgregarCodigoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMisVentasAgregarCodigoClienteKeyTyped
        // TODO add your handling code here:
        event.numberKeyPress(evt);
    }//GEN-LAST:event_txtMisVentasAgregarCodigoClienteKeyTyped

    private void txtMisVentasAgregarCodigoClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMisVentasAgregarCodigoClienteKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtMisVentasAgregarCodigoCliente.getText())) {
                int codigoCliente = Integer.parseInt(txtMisVentasAgregarCodigoCliente.getText());
                cl = client.BuscarClientesXCodigo(codigoCliente);
                if (cl.getNombre() != null) {
                    txtMisVentasAgregarNombreCliente.setText("" + cl.getNombre());
                    txtMisVentasAgregarDireccionCliente.setText("" + cl.getDireccion());
                    txtMisVentasAgregarTelefonoCliente.setText("" + cl.getTelefono());

                } else {
                    txtMisVentasAgregarCodigoCliente.setText("");
                    txtMisVentasAgregarCodigoCliente.requestFocus();
                    JOptionPane.showMessageDialog(null, "El cliente no existe");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el codigo del cliente");
                txtMisVentasAgregarCodigoCliente.requestFocus();
            }
        }
    }//GEN-LAST:event_txtMisVentasAgregarCodigoClienteKeyPressed

    private void txtMisVentasAgregarPrecioVendidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMisVentasAgregarPrecioVendidoKeyTyped
        // TODO add your handling code here:
        event.numberDecimalKeyPress(evt, txtMisVentasAgregarPrecioVendido);
    }//GEN-LAST:event_txtMisVentasAgregarPrecioVendidoKeyTyped

    private void txtMisVentasAgregarPrecioVendidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMisVentasAgregarPrecioVendidoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtMisVentasAgregarPrecioVendido.getText())) {
                int codigoPro = Integer.parseInt(txtMisVentasAgregarCodigoProducto.getText());
                String nombrePro = txtMisVentasAgregarNombreProducto.getText();

                //Quitamos punto y donde iba coma, ponemos punto
                String agregarPrecioFactura = convertirABigDecimal(txtMisVentasAgregarPrecioFactura.getText());
                String agregarPrecioVendido = convertirABigDecimal(txtMisVentasAgregarPrecioVendido.getText());

                int cantidad = Integer.parseInt(spinnerMisVentasAgregarCantidad.getValue().toString());
                String pVendidoPorCantidad = multiPrecioPorCantidad(cantidad, txtMisVentasAgregarPrecioVendido.getText());

                item++;
                tmp = (DefaultTableModel) tableMisVentasAgregar.getModel();
                for (int i = 0; i < tableMisVentasAgregar.getRowCount(); i++) {
                    if (tableMisVentasAgregar.getValueAt(i, 1).equals(txtMisVentasAgregarNombreProducto.getText())) {
                        JOptionPane.showMessageDialog(null, "El producto ya esta registrado");
                        return;
                    }
                }

                ArrayList lista = new ArrayList();
                lista.add(item);
                lista.add(codigoPro);
                lista.add(nombrePro);
                lista.add(cantidad);
                lista.add(agregarPrecioFactura);
                lista.add(agregarPrecioVendido);
                lista.add(pVendidoPorCantidad);
                Object[] O = new Object[7];
                O[0] = lista.get(1);
                O[1] = lista.get(2);
                O[2] = lista.get(3);
                O[3] = lista.get(4);
                O[4] = lista.get(5);
                O[5] = lista.get(6);
                O[6] = lista.get(0);

                tableMisVentasAgregar.setModel(tmp);
                tmp.addRow(O);
                TotalPagarMisVentas();
                LimpiarMiVenta();
                txtMisVentasAgregarCodigoProducto.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el precio a vender");
            }
        }
    }//GEN-LAST:event_txtMisVentasAgregarPrecioVendidoKeyPressed

    private void txtMisVentasAgregarNombreProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMisVentasAgregarNombreProductoKeyTyped
        // TODO add your handling code here:
        event.textKeyPress(evt);
    }//GEN-LAST:event_txtMisVentasAgregarNombreProductoKeyTyped

    private void txtMisVentasAgregarCodigoProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMisVentasAgregarCodigoProductoKeyTyped
        // TODO add your handling code here:
        event.numberKeyPress(evt);
    }//GEN-LAST:event_txtMisVentasAgregarCodigoProductoKeyTyped

    private void txtMisVentasAgregarCodigoProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMisVentasAgregarCodigoProductoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtMisVentasAgregarCodigoProducto.getText())) {
                int codigoProducto = Integer.parseInt(txtMisVentasAgregarCodigoProducto.getText());
                pr = produ.BuscarProductosXCodigo(codigoProducto);
                if (pr.getNombre() != null) {
                    txtMisVentasAgregarNombreProducto.setText("" + pr.getNombre());
                    //txtMisVentasAgregarPrecioFactura.setText( "" + pr.getPrecio());
                    txtMisVentasAgregarPrecioFactura.setText("" + formatoDecimal.format(pr.getPrecio()));

                    txtMisVentasAgregarIdProducto.setText("" + pr.getId());
                    txtMisVentasAgregarPrecioVendido.requestFocus();
                } else {
                    LimpiarMiVenta();
                    txtMisVentasAgregarCodigoProducto.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el codigo del producto");
                txtMisVentasAgregarCodigoProducto.requestFocus();
            }
        }
    }//GEN-LAST:event_txtMisVentasAgregarCodigoProductoKeyPressed

    private void btnMisVendedoresRegistroEliminarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMisVendedoresRegistroEliminarTodoActionPerformed
        // TODO add your handling code here:
        int pregunta = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar todos los registros?");
        if (pregunta == 0) {
            misvendedor.EliminarTodasVentasMisVendedores();
            misvendedor.EliminarTodoDetalleMisVendedores();

            limpiarTablaMisVendedoresRegistro();
            ListarRegistroMisVendedores();
            txtMisVendedoresRegistroId.setText("");
            JOptionPane.showMessageDialog(null, "Ventas eliminadas con éxito");
        }
    }//GEN-LAST:event_btnMisVendedoresRegistroEliminarTodoActionPerformed

    private void btnMisVendedoresRegistroEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMisVendedoresRegistroEliminarActionPerformed
        // TODO add your handling code here:
        if (tableMisVendedoresRegistro.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {

            int pregunta = JOptionPane.showConfirmDialog(null, "¿Está Seguro De eliminar?");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtMisVendedoresRegistroId.getText());
                misvendedor.EliminarVentasMisVendedores(id);
                misvendedor.EliminarDetalleMisVendedores(id);

                limpiarTablaMisVendedoresRegistro();
                ListarRegistroMisVendedores();
                txtMisVendedoresRegistroId.setText("");

                JOptionPane.showMessageDialog(null, "Venta Eliminada Con Exito");
            }
        }
    }//GEN-LAST:event_btnMisVendedoresRegistroEliminarActionPerformed

    private void btnMisVendedoresRegistroPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMisVendedoresRegistroPdfActionPerformed
        // TODO add your handling code here:
        pdfMiVendedores();
    }//GEN-LAST:event_btnMisVendedoresRegistroPdfActionPerformed

    private void tableMisVendedoresRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMisVendedoresRegistroMouseClicked
        // TODO add your handling code here:
        int fila = tableMisVendedoresRegistro.rowAtPoint(evt.getPoint());
        txtMisVendedoresRegistroId.setText(tableMisVendedoresRegistro.getValueAt(fila, 0).toString());
    }//GEN-LAST:event_tableMisVendedoresRegistroMouseClicked

    private void btnMisVentasRegistrosPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMisVentasRegistrosPdfActionPerformed

        pdfMiVenta();

        //        if (tableMisVentasRegistro.getSelectedRow() == -1) {
            //            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
            //        } else {
            //            pdfMiVenta();
            ////            try {
                ////                // TODO add your handling code here:
                ////                int id = Integer.parseInt(txtMisVentasRegistroId.getText());
                ////                File file = new File("src/pdf/miVenta" + id + ".pdf");
                ////                Desktop.getDesktop().open(file);
                ////            } catch (IOException ex) {
                ////                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                ////                System.out.println(ex.toString());
                ////            }
            //        }
    }//GEN-LAST:event_btnMisVentasRegistrosPdfActionPerformed

    private void MisVentasRegistroEliminarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MisVentasRegistroEliminarTodoActionPerformed
        // TODO add your handling code here:
        int pregunta = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar todos los registros?");
        if (pregunta == 0) {
            misven.EliminarTodasMisVentas();
            misven.EliminarTodoDetalleMisVentas();

            limpiarTablaMisVentasRegistro();
            ListarMisVentasRegistro();
            LimpiarMiVentaRegistro();
            JOptionPane.showMessageDialog(null, "Ventas eliminadas con éxito");
        }
    }//GEN-LAST:event_MisVentasRegistroEliminarTodoActionPerformed

    private void btnMisVentasRegistroEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMisVentasRegistroEliminarActionPerformed
        // TODO add your handling code here:
        if (tableMisVentasRegistro.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {

            int pregunta = JOptionPane.showConfirmDialog(null, "¿Está Seguro De eliminar?");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtMisVentasRegistroId.getText());
                misven.EliminarMisVentas(id);
                misven.EliminarDetalleMisVentas(id);

                limpiarTablaMisVentasRegistro();
                ListarMisVentasRegistro();
                LimpiarMiVentaRegistro();
                JOptionPane.showMessageDialog(null, "Venta Eliminada Con Exito");
            }
        }
    }//GEN-LAST:event_btnMisVentasRegistroEliminarActionPerformed

    private void btnMisVentasRegistroActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMisVentasRegistroActualizarActionPerformed
        // TODO add your handling code here:

        actualizarMisVentas();
        LimpiarMiVentaRegistro();
    }//GEN-LAST:event_btnMisVentasRegistroActualizarActionPerformed

    private void txtMisVentasRegistroAbonarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMisVentasRegistroAbonarKeyTyped
        // TODO add your handling code here:
        event.numberDecimalKeyPress(evt, txtMisVentasRegistroAbonar);
    }//GEN-LAST:event_txtMisVentasRegistroAbonarKeyTyped

    private void tableMisVentasRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMisVentasRegistroMouseClicked
        // TODO add your handling code here:
        int fila = tableMisVentasRegistro.rowAtPoint(evt.getPoint());
        txtMisVentasRegistroId.setText(tableMisVentasRegistro.getValueAt(fila, 0).toString());
        txtMisVentasRegistroCodigoCliente.setText(tableMisVentasRegistro.getValueAt(fila, 1).toString());
        txtMisVentasRegistroNombreCliente.setText(tableMisVentasRegistro.getValueAt(fila, 2).toString());
        txtMisVentasRegistroDeuda.setText(tableMisVentasRegistro.getValueAt(fila, 3).toString());
        //txtMisVentasRegistroAbonar.setText(tableMisVentasRegistro.getValueAt(fila, 4).toString());
    }//GEN-LAST:event_tableMisVentasRegistroMouseClicked

    private void btnVendedoresNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendedoresNuevoActionPerformed
        // TODO add your handling code here:
        LimpiarVendedor();
        LimpiarTable();
        ListarVendedores();
        txtVendedoresCodigo.requestFocus();
    }//GEN-LAST:event_btnVendedoresNuevoActionPerformed

    private void tableVendedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableVendedoresMouseClicked
        // TODO add your handling code here:
        int fila = tableVendedores.rowAtPoint(evt.getPoint());
        txtVendedoresId.setText(tableVendedores.getValueAt(fila, 0).toString());
        txtVendedoresCodigo.setText(tableVendedores.getValueAt(fila, 1).toString());
        txtVendedoresNombre.setText(tableVendedores.getValueAt(fila, 2).toString());
        txtVendedoresDireccion.setText(tableVendedores.getValueAt(fila, 3).toString());
        txtVendedoresTelefono.setText(tableVendedores.getValueAt(fila, 4).toString());
    }//GEN-LAST:event_tableVendedoresMouseClicked

    private void btnVendedoresEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendedoresEliminarActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtVendedoresId.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {

            int pregunta = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar?");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtVendedoresId.getText());
                vend.EliminarVendedor(id);
                JOptionPane.showMessageDialog(null, "Vendedor Eliminado Con Exito");
                LimpiarTable();
                LimpiarVendedor();
                ListarVendedores();
            }
        }
    }//GEN-LAST:event_btnVendedoresEliminarActionPerformed

    private void btnVendedoresActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendedoresActualizarActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtVendedoresId.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            if (!"".equals(txtVendedoresCodigo.getText()) && !"".equals(txtVendedoresNombre.getText())) {
                ve.setCodigo(Integer.parseInt(txtVendedoresCodigo.getText()));
                ve.setNombre(txtVendedoresNombre.getText());
                ve.setDireccion(txtVendedoresDireccion.getText());
                ve.setTelefono(txtVendedoresTelefono.getText());
                ve.setId(Integer.parseInt(txtVendedoresId.getText()));
                vend.ActualizarVendedor(ve);

                LimpiarTable();
                LimpiarVendedor();
                ListarVendedores();
                JOptionPane.showMessageDialog(null, "Vendedor Actualizado Con Exito");
                txtProductosCodigo.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Hay Campos Requeridos Vacios");
            }
        }
    }//GEN-LAST:event_btnVendedoresActualizarActionPerformed

    private void btnVendedoresGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendedoresGuardarActionPerformed
        // TODO add your handling code here:

        if (txtVendedoresCodigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un codigo");
            return;
        }

        ve = vend.BuscarVendedoresXCodigo(Integer.parseInt(txtVendedoresCodigo.getText()));
        if (ve.getId() != 0) {
            JOptionPane.showMessageDialog(null, "Error, ya existe un vendedor con ese codigo");
            return;
        }
        ve = vend.BuscarVendedoresXNombre(txtVendedoresNombre.getText().trim().replaceAll(" +", " "));
        if (ve.getId() != 0) {
            JOptionPane.showMessageDialog(null, "Error, ya existe un vendedor con ese nombre");
        } else {
            if (!"".equals(txtVendedoresCodigo.getText()) && !"".equals(txtVendedoresNombre.getText())) {

                ve.setCodigo(Integer.parseInt(txtVendedoresCodigo.getText()));
                ve.setNombre(txtVendedoresNombre.getText().trim().replaceAll(" +", " "));
                ve.setDireccion(txtVendedoresDireccion.getText());
                ve.setTelefono(txtVendedoresTelefono.getText());
                vend.RegistrarVendedor(ve);

                LimpiarTable();
                ListarVendedores();
                LimpiarVendedor();
                JOptionPane.showMessageDialog(null, "Vendedor Registrado Con Exito");
                txtVendedoresCodigo.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Hay Campos Requeridos Vacios");
            }
        }
    }//GEN-LAST:event_btnVendedoresGuardarActionPerformed

    private void txtVendedoresDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVendedoresDireccionKeyTyped
        // TODO add your handling code here:
        event.textKeyPress(evt);
    }//GEN-LAST:event_txtVendedoresDireccionKeyTyped

    private void txtVendedoresTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVendedoresTelefonoKeyTyped
        // TODO add your handling code here:
        event.numberKeyPress(evt);
    }//GEN-LAST:event_txtVendedoresTelefonoKeyTyped

    private void txtVendedoresNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVendedoresNombreKeyTyped
        // TODO add your handling code here:
        event.textKeyPress(evt);
    }//GEN-LAST:event_txtVendedoresNombreKeyTyped

    private void txtVendedoresCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVendedoresCodigoKeyTyped
        // TODO add your handling code here:
        event.numberKeyPress(evt);
    }//GEN-LAST:event_txtVendedoresCodigoKeyTyped

    private void btnClientesNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesNuevoActionPerformed
        // TODO add your handling code here:
        LimpiarCliente();
        LimpiarTable();
        ListarClientes();
        txtClientesCodigo.requestFocus();
    }//GEN-LAST:event_btnClientesNuevoActionPerformed

    private void txtClientesTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClientesTelefonoKeyTyped
        // TODO add your handling code here:
        event.numberKeyPress(evt);
    }//GEN-LAST:event_txtClientesTelefonoKeyTyped

    private void tableClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableClientesMouseClicked
        // TODO add your handling code here:
        int fila = tableClientes.rowAtPoint(evt.getPoint());
        txtClientesId.setText(tableClientes.getValueAt(fila, 0).toString());
        txtClientesCodigo.setText(tableClientes.getValueAt(fila, 1).toString());
        txtClientesNombre.setText(tableClientes.getValueAt(fila, 2).toString());
        txtClientesDireccion.setText(tableClientes.getValueAt(fila, 3).toString());
        txtClientesTelefono.setText(tableClientes.getValueAt(fila, 4).toString());
    }//GEN-LAST:event_tableClientesMouseClicked

    private void btnClientesEliminarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesEliminarTodoActionPerformed
        // TODO add your handling code here:
        int pregunta = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar todos los registros?");
        if (pregunta == 0) {
            client.EliminarTodosLosClientes();

            LimpiarTable();
            LimpiarCliente();
            ListarClientes();
            JOptionPane.showMessageDialog(null, "Se Han Eliminado Todos Los Registros");
            txtClientesId.setText("");
        }
    }//GEN-LAST:event_btnClientesEliminarTodoActionPerformed

    private void btnClientesEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesEliminarActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtClientesId.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {

            int pregunta = JOptionPane.showConfirmDialog(null, "¿Está Seguro De eliminar?");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtClientesId.getText());
                client.EliminarCliente(id);

                LimpiarTable();
                LimpiarCliente();
                ListarClientes();
                JOptionPane.showMessageDialog(null, "Cliente Eliminado Con Exito");
            }
        }
    }//GEN-LAST:event_btnClientesEliminarActionPerformed

    private void btnClientesGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesGuardarActionPerformed
        // TODO add your handling code here:

        if (txtClientesCodigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un codigo");
            return;
        }

        cl = client.BuscarClientesXCodigo(Integer.parseInt(txtClientesCodigo.getText()));
        if (cl.getId() != 0) {
            JOptionPane.showMessageDialog(null, "Error, ya existe un cliente con ese codigo");
            return;
        }
        cl = client.BuscarClientesXNombre(txtClientesNombre.getText().trim().replaceAll(" +", " "));
        if (cl.getId() != 0) {
            JOptionPane.showMessageDialog(null, "Error, ya existe un cliente con ese nombre");
        } else {
            if (!"".equals(txtClientesCodigo.getText()) && !"".equals(txtClientesNombre.getText())) {

                cl.setCodigo(Integer.parseInt(txtClientesCodigo.getText()));
                cl.setNombre(txtClientesNombre.getText().trim().replaceAll(" +", " "));//Omitir espacios entre cadenas
                cl.setDireccion(txtClientesDireccion.getText());
                cl.setTelefono(txtClientesTelefono.getText());
                client.RegistrarCliente(cl);

                LimpiarTable();
                LimpiarCliente();
                ListarClientes();
                JOptionPane.showMessageDialog(null, "Cliente Registrado Con Exito");
                txtClientesCodigo.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Hay Campos Requeridos Vacios");
            }
        }
    }//GEN-LAST:event_btnClientesGuardarActionPerformed

    private void btnClientesActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActualizarActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtClientesId.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            if (!"".equals(txtClientesCodigo.getText()) && !"".equals(txtClientesNombre.getText())) {
                cl.setCodigo(Integer.parseInt(txtClientesCodigo.getText()));
                cl.setNombre(txtClientesNombre.getText());
                cl.setDireccion(txtClientesDireccion.getText());
                cl.setTelefono(txtClientesTelefono.getText());
                cl.setId(Integer.parseInt(txtClientesId.getText()));
                client.ActualizarCliente(cl);

                LimpiarTable();
                LimpiarCliente();
                ListarClientes();
                JOptionPane.showMessageDialog(null, "Cliente Actualizado Con Exito");
                txtClientesCodigo.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Hay Campos Requeridos Vacios");
            }
        }
    }//GEN-LAST:event_btnClientesActualizarActionPerformed

    private void txtClientesDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClientesDireccionKeyTyped
        // TODO add your handling code here:
        event.textKeyPress(evt);
    }//GEN-LAST:event_txtClientesDireccionKeyTyped

    private void txtClientesNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClientesNombreKeyTyped
        // TODO add your handling code here:
        event.textKeyPress(evt);
    }//GEN-LAST:event_txtClientesNombreKeyTyped

    private void txtClientesCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClientesCodigoKeyTyped
        // TODO add your handling code here:
        event.numberKeyPress(evt);
    }//GEN-LAST:event_txtClientesCodigoKeyTyped

    private void btnProductosNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosNuevoActionPerformed
        // TODO add your handling code here:
        LimpiarProducto();
        LimpiarTable();
        ListarProductos();
        txtProductosCodigo.requestFocus();
    }//GEN-LAST:event_btnProductosNuevoActionPerformed

    private void btnProductosGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosGuardarActionPerformed

        if (txtProductosCodigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un codigo");
            return;
        }

        pr = produ.BuscarProductosXCodigo(Integer.parseInt(txtProductosCodigo.getText()));
        if (pr.getId() != 0) {
            JOptionPane.showMessageDialog(null, "Error, ya existe un producto con ese codigo");
            return;
        }
        pr = produ.BuscarProductosXNombre(txtProductosNombre.getText().trim().replaceAll(" +", " "));
        if (pr.getId() != 0) {
            JOptionPane.showMessageDialog(null, "Error, ya existe un producto con ese nombre");
        } else {
            if (!"".equals(txtProductosCodigo.getText()) && !"".equals(txtProductosNombre.getText()) && !"".equals(txtProductosPrecio.getText())) {

                pr.setCodigo(Integer.parseInt(txtProductosCodigo.getText()));
                pr.setNombre(txtProductosNombre.getText().trim().replaceAll(" +", " "));
                pr.setPrecio(new BigDecimal(txtProductosPrecio.getText().replace(".", "").replace(",", ".")));
                produ.RegistrarProducto(pr);

                LimpiarTable();
                LimpiarProducto();
                ListarProductos();

                JOptionPane.showMessageDialog(null, "Producto Registrado Con Exito");
            } else {
                JOptionPane.showMessageDialog(null, "Hay Campos Requeridos Vacios");
            }
        }

    }//GEN-LAST:event_btnProductosGuardarActionPerformed

    private void tableProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProductosMouseClicked
        // TODO add your handling code here:
        int fila = tableProductos.rowAtPoint(evt.getPoint());
        txtProductosId.setText(tableProductos.getValueAt(fila, 0).toString());
        txtProductosCodigo.setText(tableProductos.getValueAt(fila, 1).toString());
        txtProductosNombre.setText(tableProductos.getValueAt(fila, 2).toString());
        txtProductosPrecio.setText(tableProductos.getValueAt(fila, 3).toString());
    }//GEN-LAST:event_tableProductosMouseClicked

    private void btnProductosEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosEliminarActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtProductosId.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            int pregunta = JOptionPane.showConfirmDialog(null, "¿Está Seguro De Eliminar?");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtProductosId.getText());
                produ.EliminarProducto(id);

                LimpiarTable();
                LimpiarProducto();
                ListarProductos();
                JOptionPane.showMessageDialog(null, "Producto Eliminado Con Exito");
                txtProductosCodigo.requestFocus();
            }
        }
    }//GEN-LAST:event_btnProductosEliminarActionPerformed

    private void btnProductosActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActualizarActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtProductosId.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            if ("".equals(txtProductosPrecio.getText())) {
                JOptionPane.showMessageDialog(null, "Debe Digitar Un Precio");
            } else {
                if (!"".equals(txtProductosCodigo.getText()) && !"".equals(txtProductosNombre.getText()) && !"".equals(txtProductosPrecio.getText())) {
                    pr.setCodigo(Integer.parseInt(txtProductosCodigo.getText()));
                    pr.setNombre(txtProductosNombre.getText());
                    pr.setPrecio(new BigDecimal(txtProductosPrecio.getText().replace(".", "").replace(",", ".")));
                    pr.setId(Integer.parseInt(txtProductosId.getText()));
                    produ.ActualizarProducto(pr);
                    JOptionPane.showMessageDialog(null, "Producto Actualizado Con Exito");
                    LimpiarTable();
                    LimpiarProducto();
                    ListarProductos();
                } else {
                    JOptionPane.showMessageDialog(null, "Hay Campos Requeridos Vacios");
                }
            }

        }
    }//GEN-LAST:event_btnProductosActualizarActionPerformed

    private void txtProductosPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductosPrecioKeyTyped
        // TODO add your handling code here:
        event.numberDecimalKeyPress(evt, txtProductosPrecio);
    }//GEN-LAST:event_txtProductosPrecioKeyTyped

    private void txtProductosNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductosNombreKeyTyped
        // TODO add your handling code here:
        event.textKeyPress(evt);
    }//GEN-LAST:event_txtProductosNombreKeyTyped

    private void txtProductosCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductosCodigoKeyTyped
        // TODO add your handling code here:
        event.numberKeyPress(evt);
    }//GEN-LAST:event_txtProductosCodigoKeyTyped

    private void btnMisVendedoresAgregarGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMisVendedoresAgregarGuardarActionPerformed
        // TODO add your handling code here:
        if (tableMisVendedoresAgregar.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Debe ingresar productos a la tabla");
            return;
        }
        registrarVentasMisVendedores();
        registrarDetalleMisVendedores();
        LimpiarMisVendedores();
        LimpiarTableAgregarMisVendedores();
        labelMisVendedoresAgregarTotalPagar.setText("--------");
        JOptionPane.showMessageDialog(null, "Venta agregada con éxito");

    }//GEN-LAST:event_btnMisVendedoresAgregarGuardarActionPerformed

    private void txtMisVendedoresAgregarNombreVendedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMisVendedoresAgregarNombreVendedorKeyTyped
        // TODO add your handling code here:
        event.textKeyPress(evt);
    }//GEN-LAST:event_txtMisVendedoresAgregarNombreVendedorKeyTyped

    private void txtMisVendedoresAgregarCodigoVendedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMisVendedoresAgregarCodigoVendedorKeyTyped
        // TODO add your handling code here:
        event.numberKeyPress(evt);
    }//GEN-LAST:event_txtMisVendedoresAgregarCodigoVendedorKeyTyped

    private void txtMisVendedoresAgregarCodigoVendedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMisVendedoresAgregarCodigoVendedorKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtMisVendedoresAgregarCodigoVendedor.getText())) {
                int codigoVendedor = Integer.parseInt(txtMisVendedoresAgregarCodigoVendedor.getText());
                ve = vend.BuscarVendedoresXCodigo(codigoVendedor);
                if (ve.getNombre() != null) {
                    txtMisVendedoresAgregarNombreVendedor.setText("" + ve.getNombre());
                    dcMisVendedoresAgregarFechaLimite.requestFocus();

                } else {
                    txtMisVendedoresAgregarNombreVendedor.setText("");
                    txtMisVendedoresAgregarCodigoVendedor.setText("");
                    txtMisVendedoresAgregarCodigoVendedor.requestFocus();
                    JOptionPane.showMessageDialog(null, "El vendedor no existe");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el codigo del vendedor");
                txtMisVendedoresAgregarCodigoVendedor.requestFocus();
            }
        }
    }//GEN-LAST:event_txtMisVendedoresAgregarCodigoVendedorKeyPressed

    private void txtMisVendedoresAgregarPrecioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMisVendedoresAgregarPrecioKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtMisVendedoresAgregarPrecio.getText())) {
                int codigoPro = Integer.parseInt(txtMisVendedoresAgregarCodigoProducto.getText());
                String nombrePro = txtMisVendedoresAgregarNombreProducto.getText();

                int cantidad = Integer.parseInt(spinnerMisVendedoresAgregarCantidad.getValue().toString());
                // Convertimos a bigDecimal y luego a String
                String vendedoresAgregarPrecio = convertirABigDecimal(txtMisVendedoresAgregarPrecio.getText());
                String vendedoresPrecioTotal = multiPrecioPorCantidad(cantidad,txtMisVendedoresAgregarPrecio.getText());

                item++;
                tmp = (DefaultTableModel) tableMisVendedoresAgregar.getModel();
                for (int i = 0; i < tableMisVendedoresAgregar.getRowCount(); i++) {
                    if (tableMisVendedoresAgregar.getValueAt(i, 1).equals(txtMisVendedoresAgregarNombreProducto.getText())) {
                        JOptionPane.showMessageDialog(null, "El producto ya esta registrado");
                        return;
                    }
                }

                ArrayList lista = new ArrayList();
                lista.add(item);
                lista.add(codigoPro);
                lista.add(nombrePro);
                lista.add(cantidad);
                lista.add(vendedoresAgregarPrecio);
                lista.add(vendedoresPrecioTotal);
                Object[] O = new Object[6];
                O[0] = lista.get(1);
                O[1] = lista.get(2);
                O[2] = lista.get(3);
                O[3] = lista.get(4);
                O[4] = lista.get(5);
                O[5] = lista.get(0);
                tmp.addRow(O);
                tableMisVendedoresAgregar.setModel(tmp);
                TotalPagarMisVendedores();
                LimpiarMisVendedores();
                txtMisVendedoresAgregarCodigoProducto.requestFocus();
            }
        }
    }//GEN-LAST:event_txtMisVendedoresAgregarPrecioKeyPressed

    private void tableMisVendedoresAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMisVendedoresAgregarMouseClicked
        // TODO add your handling code here:
        int fila = tableMisVendedoresAgregar.rowAtPoint(evt.getPoint());
        txtMisVendedoresAgregarCodigoProducto.setText(tableMisVendedoresAgregar.getValueAt(fila, 0).toString());
        txtMisVendedoresAgregarNombreProducto.setText(tableMisVendedoresAgregar.getValueAt(fila, 1).toString());
        txtMisVendedoresAgregarPrecio.setText(tableMisVendedoresAgregar.getValueAt(fila, 3).toString());

        int cantidad = Integer.parseInt(tableMisVendedoresAgregar.getValueAt(fila, 2).toString());
        spinnerMisVendedoresAgregarCantidad.setValue(cantidad);
    }//GEN-LAST:event_tableMisVendedoresAgregarMouseClicked

    private void btnMisVendedoresAgregarEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMisVendedoresAgregarEliminarActionPerformed
        // TODO add your handling code here:
        if (tableMisVendedoresAgregar.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
            //return;
        } else {
            int pregunta = JOptionPane.showConfirmDialog(null, "¿Está Seguro De Eliminar?");
            if (pregunta == 0) {
                modelo = (DefaultTableModel) tableMisVendedoresAgregar.getModel();
                modelo.removeRow(tableMisVendedoresAgregar.getSelectedRow());
                TotalPagarMisVendedores();
                txtMisVendedoresAgregarCodigoProducto.requestFocus();
                labelMisVendedoresAgregarTotalPagar.setText("--------");
                JOptionPane.showMessageDialog(null, "Producto Eliminado Con Exito");
            }
        }
    }//GEN-LAST:event_btnMisVendedoresAgregarEliminarActionPerformed

    private void txtMisVendedoresAgregarNombreProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMisVendedoresAgregarNombreProductoKeyTyped
        // TODO add your handling code here:
        event.textKeyPress(evt);
    }//GEN-LAST:event_txtMisVendedoresAgregarNombreProductoKeyTyped

    private void txtMisVendedoresAgregarCodigoProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMisVendedoresAgregarCodigoProductoKeyTyped
        // TODO add your handling code here:
        event.numberKeyPress(evt);
    }//GEN-LAST:event_txtMisVendedoresAgregarCodigoProductoKeyTyped

    private void txtMisVendedoresAgregarCodigoProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMisVendedoresAgregarCodigoProductoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtMisVendedoresAgregarCodigoProducto.getText())) {
                int codigoProducto = Integer.parseInt(txtMisVendedoresAgregarCodigoProducto.getText());
                pr = produ.BuscarProductosXCodigo(codigoProducto);
                if (pr.getNombre() != null) {
                    txtMisVendedoresAgregarNombreProducto.setText("" + pr.getNombre());
                    txtMisVendedoresAgregarPrecio.setText("" + formatoDecimal.format(pr.getPrecio()));
                    txtMisVendedoresAgregarIdProducto.setText("" + pr.getId());
                    txtMisVendedoresAgregarPrecio.requestFocus();
                } else {
                    LimpiarMisVendedores();
                    txtMisVendedoresAgregarCodigoProducto.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el codigo del producto");
                txtMisVendedoresAgregarCodigoProducto.requestFocus();
            }
        }
    }//GEN-LAST:event_txtMisVendedoresAgregarCodigoProductoKeyPressed

    private void lblTab1NuevaVentaUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTab1NuevaVentaUsuarioMouseClicked
        this.jPanelContenedorNuevaVentaUsuario.setVisible(true);
       // jTabbedPane1.setSelectedComponent(jPanelContenedorNuevaVentaUsuario);
    }//GEN-LAST:event_lblTab1NuevaVentaUsuarioMouseClicked

    private void lblTab1NuevaVentaVendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTab1NuevaVentaVendedorMouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedComponent(jPanelNuevaVentaVendedor);
    }//GEN-LAST:event_lblTab1NuevaVentaVendedorMouseClicked

    private void lblTab2NuevaVentaUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTab2NuevaVentaUsuarioMouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedComponent(jPanelNuevaVentaUsuario);
        this.jPanelContenedorNuevaVentaUsuario.setVisible(true);
    }//GEN-LAST:event_lblTab2NuevaVentaUsuarioMouseClicked

    private void lblTab1NuevaVentaUsuarioMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTab1NuevaVentaUsuarioMouseMoved
        // TODO add your handling code here:
        configurarEfectoHoverEtiqueta(lblTab1NuevaVentaUsuario, 204,188,255);   // pasamos el label y el color RGB     
    }//GEN-LAST:event_lblTab1NuevaVentaUsuarioMouseMoved

    private void lblTab1NuevaVentaVendedorMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTab1NuevaVentaVendedorMouseMoved
        // TODO add your handling code here:
        configurarEfectoHoverEtiqueta(lblTab1NuevaVentaVendedor, 255, 204,255);
    }//GEN-LAST:event_lblTab1NuevaVentaVendedorMouseMoved

    private void lblTab2NuevaVentaUsuarioMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTab2NuevaVentaUsuarioMouseMoved
        // TODO add your handling code here:
        configurarEfectoHoverEtiqueta(lblTab2NuevaVentaUsuario, 204,188,255);
    }//GEN-LAST:event_lblTab2NuevaVentaUsuarioMouseMoved

    private void lblTab2NuevaVentaVendedorMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTab2NuevaVentaVendedorMouseMoved
        // TODO add your handling code here:
        configurarEfectoHoverEtiqueta(lblTab2NuevaVentaVendedor, 255,204,255);
    }//GEN-LAST:event_lblTab2NuevaVentaVendedorMouseMoved

    private void configurarEfectoHoverEtiqueta(JLabel label, int r, int g, int b) {
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label.setBackground(new Color(230,196,255));
             //   label.setOpaque(true);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label.setBackground(new Color(r, g, b));
               // label.setOpaque(true);
            }
        });
    }
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Principal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton MisVentasRegistroEliminarTodo;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnClientesActualizar;
    private javax.swing.JButton btnClientesEliminar;
    private javax.swing.JButton btnClientesEliminarTodo;
    private javax.swing.JButton btnClientesGuardar;
    private javax.swing.JButton btnClientesNuevo;
    private javax.swing.JButton btnClientesPdf;
    private javax.swing.JButton btnLogoPersonal;
    private javax.swing.JButton btnMisVendedoresAgregarEliminar;
    private javax.swing.JButton btnMisVendedoresAgregarGuardar;
    private javax.swing.JButton btnMisVendedoresRegistroEliminar;
    private javax.swing.JButton btnMisVendedoresRegistroEliminarTodo;
    private javax.swing.JButton btnMisVendedoresRegistroPdf;
    private javax.swing.JButton btnMisVentasAgregarEliminar;
    private javax.swing.JButton btnMisVentasAgregarGuardar;
    private javax.swing.JButton btnMisVentasRegistroActualizar;
    private javax.swing.JButton btnMisVentasRegistroEliminar;
    private javax.swing.JButton btnMisVentasRegistroMisCuentas;
    private javax.swing.JButton btnMisVentasRegistrosPdf;
    private javax.swing.JButton btnNuevaVenta;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnProductosActualizar;
    private javax.swing.JButton btnProductosEliminar;
    private javax.swing.JButton btnProductosGuardar;
    private javax.swing.JButton btnProductosNuevo;
    private javax.swing.JButton btnProductosPdf;
    private javax.swing.JButton btnTotalVentas;
    private javax.swing.JButton btnVendedores;
    private javax.swing.JButton btnVendedoresActualizar;
    private javax.swing.JButton btnVendedoresEliminar;
    private javax.swing.JButton btnVendedoresGuardar;
    private javax.swing.JButton btnVendedoresNuevo;
    private javax.swing.JButton btnVendedoresPdf;
    private com.toedter.calendar.JDateChooser dcMisVendedoresAgregarFechaLimite;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelBienvenida;
    private javax.swing.JPanel jPanelClientes;
    private javax.swing.JPanel jPanelContenedorNuevaVentaUsuario;
    private javax.swing.JPanel jPanelContenedorNuevaVentaVendedor;
    private javax.swing.JPanel jPanelNuevaVentaUsuario;
    private javax.swing.JPanel jPanelNuevaVentaVendedor;
    private javax.swing.JPanel jPanelProductos;
    private javax.swing.JPanel jPanelVendedores;
    private javax.swing.JPanel jPanelVentasUsuario;
    private javax.swing.JPanel jPanelVentasVendedores;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelMisVendedoresAgregarTotalPagar;
    private javax.swing.JLabel labelMisVentasAgregarTotalPagar;
    private javax.swing.JLabel lblTab1NuevaVentaUsuario;
    private javax.swing.JLabel lblTab1NuevaVentaVendedor;
    private javax.swing.JLabel lblTab2NuevaVentaUsuario;
    private javax.swing.JLabel lblTab2NuevaVentaVendedor;
    private javax.swing.JSpinner spinnerMisVendedoresAgregarCantidad;
    private javax.swing.JSpinner spinnerMisVentasAgregarCantidad;
    private javax.swing.JTable tableClientes;
    private javax.swing.JTable tableMisVendedoresAgregar;
    private javax.swing.JTable tableMisVendedoresRegistro;
    private javax.swing.JTable tableMisVentasAgregar;
    private javax.swing.JTable tableMisVentasRegistro;
    private javax.swing.JTable tableProductos;
    private javax.swing.JTable tableVendedores;
    private javax.swing.JTextField txtClientesCodigo;
    private javax.swing.JTextField txtClientesDireccion;
    private javax.swing.JTextField txtClientesId;
    private javax.swing.JTextField txtClientesNombre;
    private javax.swing.JTextField txtClientesTelefono;
    private javax.swing.JTextField txtMisVendedoresAgregarCodigoProducto;
    private javax.swing.JTextField txtMisVendedoresAgregarCodigoVendedor;
    private javax.swing.JTextField txtMisVendedoresAgregarIdProducto;
    private javax.swing.JTextField txtMisVendedoresAgregarNombreProducto;
    private javax.swing.JTextField txtMisVendedoresAgregarNombreVendedor;
    private javax.swing.JTextField txtMisVendedoresAgregarPrecio;
    private javax.swing.JTextField txtMisVendedoresRegistroId;
    private javax.swing.JTextField txtMisVentasAgregarCodigoCliente;
    private javax.swing.JTextField txtMisVentasAgregarCodigoProducto;
    private javax.swing.JTextField txtMisVentasAgregarDireccionCliente;
    private javax.swing.JTextField txtMisVentasAgregarIdProducto;
    private javax.swing.JTextField txtMisVentasAgregarNombreCliente;
    private javax.swing.JTextField txtMisVentasAgregarNombreProducto;
    private javax.swing.JTextField txtMisVentasAgregarPrecioFactura;
    private javax.swing.JTextField txtMisVentasAgregarPrecioVendido;
    private javax.swing.JTextField txtMisVentasAgregarTelefonoCliente;
    private javax.swing.JTextField txtMisVentasRegistroAbonar;
    private javax.swing.JTextField txtMisVentasRegistroCodigoCliente;
    private javax.swing.JTextField txtMisVentasRegistroDeuda;
    private javax.swing.JTextField txtMisVentasRegistroId;
    private javax.swing.JTextField txtMisVentasRegistroNombreCliente;
    private javax.swing.JTextField txtProductosCodigo;
    private javax.swing.JTextField txtProductosId;
    private javax.swing.JTextField txtProductosNombre;
    private javax.swing.JTextField txtProductosPrecio;
    private javax.swing.JTextField txtVendedoresCodigo;
    private javax.swing.JTextField txtVendedoresDireccion;
    private javax.swing.JTextField txtVendedoresId;
    private javax.swing.JTextField txtVendedoresNombre;
    private javax.swing.JTextField txtVendedoresTelefono;
    // End of variables declaration//GEN-END:variables

    //metodo para ajustar imagen a un jbutton
    private void setImageButton(JButton buttonName, String root) {
        ImageIcon image = new ImageIcon(getClass().getResource(root));
        Image scaledImage = image.getImage().getScaledInstance(buttonName.getWidth(), buttonName.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage);
        buttonName.setIcon(icon);
    }

    //Metodo para ajustar una imagen a JPanel o JFrame
    private class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/images/Yanbal-logo5.png")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }

    private class YanbalBienvenidaPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/images/BienvenidaYanbal.jpg")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }

    private void LimpiarCliente() {
        txtClientesId.setText("");
        txtClientesCodigo.setText("");
        txtClientesNombre.setText("");
        txtClientesDireccion.setText("");
        txtClientesTelefono.setText("");
    }

    private void LimpiarVendedor() {
        txtVendedoresId.setText("");
        txtVendedoresCodigo.setText("");
        txtVendedoresNombre.setText("");
        txtVendedoresDireccion.setText("");
        txtVendedoresTelefono.setText("");
    }

    private void LimpiarProducto() {
        txtProductosCodigo.setText("");
        txtProductosNombre.setText("");
        txtProductosPrecio.setText("");
    }

    private void LimpiarMiVenta() {
        txtMisVentasAgregarCodigoProducto.setText("");
        txtMisVentasAgregarNombreProducto.setText("");
        txtMisVentasAgregarPrecioFactura.setText("");
        txtMisVentasAgregarPrecioVendido.setText("");
        spinnerMisVentasAgregarCantidad.setValue(1);
        txtMisVentasAgregarIdProducto.setText("");
        txtMisVentasAgregarCodigoCliente.setText("");
        txtMisVentasAgregarNombreCliente.setText("");
        txtMisVentasAgregarCodigoProducto.requestFocus();
    }

    private void LimpiarMisVendedores() {
        txtMisVendedoresAgregarCodigoProducto.setText("");
        txtMisVendedoresAgregarNombreProducto.setText("");
        spinnerMisVendedoresAgregarCantidad.setValue(1);
        txtMisVendedoresAgregarPrecio.setText("");
        txtMisVendedoresAgregarCodigoVendedor.setText("");
        txtMisVendedoresAgregarNombreVendedor.setText("");
        dcMisVendedoresAgregarFechaLimite.setDate(null);
        txtMisVendedoresAgregarCodigoProducto.requestFocus();
    }

    private void LimpiarMiVentaRegistro() {
        txtMisVentasRegistroId.setText("");
        txtMisVentasRegistroCodigoCliente.setText("");
        txtMisVentasRegistroNombreCliente.setText("");
        txtMisVentasRegistroDeuda.setText("");
        txtMisVentasRegistroAbonar.setText("");
    }

    private void limpiarTablaMisVentasRegistro() {
        DefaultTableModel model = (DefaultTableModel) tableMisVentasRegistro.getModel();
        model.setRowCount(0); // Elimina todos los registros existentes
    }

    private void limpiarTablaMisVendedoresRegistro() {
        DefaultTableModel model = (DefaultTableModel) tableMisVendedoresRegistro.getModel();
        model.setRowCount(0); // Elimina todos los registros existentes
    }

    private void TotalPagarMisVentas() {
        total_pagar = BigDecimal.ZERO;
        int nFila = tableMisVentasAgregar.getRowCount();
        for (int i = 0; i < nFila; i++) {
            String calcular_ = tableMisVentasAgregar.getModel().getValueAt(i, 5).toString().replace(".", "").replace(",", ".");
            BigDecimal calcular = new BigDecimal(calcular_);
            total_pagar = total_pagar.add(calcular);
        }
        labelMisVentasAgregarTotalPagar.setText("$" + formatoDecimal.format(total_pagar));
    }

    private void TotalPagarMisVendedores() {
        total_pagar = BigDecimal.ZERO;
        int nFila = tableMisVendedoresAgregar.getRowCount();
        for (int i = 0; i < nFila; i++) {
            String calcular_ = tableMisVendedoresAgregar.getModel().getValueAt(i, 4).toString().replace(".", "").replace(",", ".");
            BigDecimal calcular = new BigDecimal(calcular_);
            total_pagar = total_pagar.add(calcular);
        }
        labelMisVendedoresAgregarTotalPagar.setText("$" + formatoDecimal.format(total_pagar));
    }

    private void registrarMisVentas() {

        int codigoCliente = Integer.parseInt(txtMisVentasAgregarCodigoCliente.getText());
        String nombreCliente = txtMisVentasAgregarNombreCliente.getText();
        BigDecimal abonar = BigDecimal.ZERO;
        BigDecimal deuda = total_pagar.subtract(abonar);
        BigDecimal total = total_pagar;
        mv.setCodigo_cliente(codigoCliente);
        mv.setNombreCliente(nombreCliente);
        mv.setDebe(deuda);
        mv.setAbonado(abonar);
        mv.setTotal(total);
        misven.RegistrarMisVentas(mv);
    }

    private void registrarVentasMisVendedores() {

        int codigoVendedor = Integer.parseInt(txtMisVendedoresAgregarCodigoVendedor.getText());
        String nombreVendedor = txtMisVendedoresAgregarNombreVendedor.getText();
        java.util.Date fechaUtil = dcMisVendedoresAgregarFechaLimite.getDate();
        java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());
        BigDecimal total = total_pagar;
        mvr.setCodigoVendedor(codigoVendedor);
        mvr.setNombreVendedor(nombreVendedor);
        //mvr.setFecha(fechaSql);
        mvr.setFecha(fechaSql);
        mvr.setTotal(total);
        misvendedor.RegistrarVentaMisVendedores(mvr);

    }

    private void registrarDetalleMisVentas() {
        int id = misven.idMaxMisVentas();
        for (int i = 0; i < tableMisVentasAgregar.getRowCount(); i++) {
            int codPro = Integer.parseInt(tableMisVentasAgregar.getValueAt(i, 0).toString());
            int cant = Integer.parseInt(tableMisVentasAgregar.getValueAt(i, 2).toString());
            String precFactura = tableMisVentasAgregar.getValueAt(i, 3).toString().replace(".", "").replace(",", ".");
            String precVendido = tableMisVentasAgregar.getValueAt(i, 4).toString().replace(".", "").replace(",", ".");
            String total_ = tableMisVentasAgregar.getValueAt(i, 5).toString().replace(".", "").replace(",", ".");

            BigDecimal precFact = new BigDecimal(precFactura);
            BigDecimal precVend = new BigDecimal(precVendido);
            BigDecimal total = new BigDecimal(total_);

            dmv.setCodigoProducto(codPro);
            dmv.setCantidad(cant);
            dmv.setPrecioFactura(precFact);
            dmv.setPrecioVendido(precVend);
            dmv.setTotal(total);
            dmv.setIdVenta(id);
            misven.RegistrarDetalleMisVentas(dmv);
        }
    }

    private void registrarDetalleMisVendedores() {
        int idVended = misvendedor.idMisVendedores();
        for (int i = 0; i < tableMisVendedoresAgregar.getRowCount(); i++) {
            int codPro = Integer.parseInt(tableMisVendedoresAgregar.getValueAt(i, 0).toString());
            int cant = Integer.parseInt(tableMisVendedoresAgregar.getValueAt(i, 2).toString());

            String prec_ = tableMisVendedoresAgregar.getValueAt(i, 3).toString().replace(".", "").replace(",", ".");
            String total_ = tableMisVendedoresAgregar.getValueAt(i, 4).toString().replace(".", "").replace(",", ".");

            BigDecimal prec = new BigDecimal(prec_);
            BigDecimal total = new BigDecimal(total_);

            dmvr.setCodigoProducto(codPro);
            dmvr.setCantidad(cant);
            dmvr.setPrecio(prec);
            dmvr.setTotal(total);
            dmvr.setIdMisVendedores(idVended);
            misvendedor.RegistrarDetalleMisVendedores(dmvr);
        }
    }

    private void actualizarMisVentas() {
        int filaSeleccionada = tableMisVentasRegistro.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
            return;
        }

        int id = Integer.parseInt(tableMisVentasRegistro.getValueAt(filaSeleccionada, 0).toString());
        String debe_ = tableMisVentasRegistro.getValueAt(filaSeleccionada, 3).toString().replace(".", "").replace(",", ".");
        String abono_ = tableMisVentasRegistro.getValueAt(filaSeleccionada, 4).toString().replace(".", "").replace(",", ".");
        String total_ = tableMisVentasRegistro.getValueAt(filaSeleccionada, 5).toString().replace(".", "").replace(",", ".");

        BigDecimal debe = new BigDecimal(debe_);
        BigDecimal abono = new BigDecimal(abono_);
        BigDecimal total = new BigDecimal(total_);

        if ("".equals(txtMisVentasRegistroAbonar.getText())) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un saldo");
            return;
        }
        //Comparamos si txtMisVentasRegistroAbonar es menor a cero
        if (new BigDecimal(txtMisVentasRegistroAbonar.getText()).compareTo(BigDecimal.ZERO) < 0) { // 
            JOptionPane.showMessageDialog(null, "El saldo debe ser positivo");
            return;
        }
        // Comparamos si txtMisVentasRegistroAbonar sea mayor a 'debe'
        if (new BigDecimal(txtMisVentasRegistroAbonar.getText()).compareTo(debe) > 0) {
            JOptionPane.showMessageDialog(null, "El saldo ingresado es mayor a la deuda actual");
            return;
        }
        if (new BigDecimal(txtMisVentasRegistroAbonar.getText()).compareTo(debe) == 0) {
            JOptionPane.showMessageDialog(null, "¡Cuenta saldada exitosamente!");
        }
        String abonarAux = txtMisVentasRegistroAbonar.getText().replace(".", "");
        BigDecimal abonar = new BigDecimal(abonarAux);
        BigDecimal abonoActual = abono.add(abonar);
        BigDecimal deudaActual = total.subtract(abonoActual);

        misven.ActualizarMisVentas(deudaActual, abonoActual, id);

        limpiarTablaMisVentasRegistro();
        ListarMisVentasRegistro();
        JOptionPane.showMessageDialog(null, "Actualizado con éxito");
    }

    private String convertirABigDecimal(String txt) {
        String textoPrecio = txt.replace(".", "").replace(",", ".");
        BigDecimal precioFact = new BigDecimal(textoPrecio);
        String precioFactFormateado = formatoDecimal.format(precioFact);
        return precioFactFormateado;
    }

    private String multiPrecioPorCantidad(int cantidad, String vapv) { // vapv (ventasAgregarPrecioVendido)
        String textoPrecio = vapv.replace(".", "").replace(",", ".");
        BigDecimal precioVend = new BigDecimal(textoPrecio);
        BigDecimal total = precioVend.multiply(new BigDecimal(cantidad)); // multiplicamos precioVend * cantidad
        String totalFormateado = formatoDecimal.format(total);
        return totalFormateado;
    }

    private void pdfMiVenta() {
        try {
            int id = misven.idMaxMisVentas();
            int idMaxDtm = misven.idMaxDetalleMisVentas();
            FileOutputStream archivo;
            //File directory = new File("C:\\Users\\Eduard\\Desktop\\YanbalPdf");
            File directory = new File("C:\\Users\\57301\\Desktop\\YanbalPdf");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File file = new File(directory, "MisVentas" + id + ".pdf");

            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            com.itextpdf.text.Image pdfImage = null;
            pdfImage = com.itextpdf.text.Image.getInstance(getClass().getResource("/images/yanbalCatalogo.png"));

            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.RED);
            fecha.add(Chunk.NEWLINE);
            Date date = new Date();
            fecha.add("Factura:" + id + "\n" + "Fecha: " + new SimpleDateFormat("dd-MM-yyyy").format(date) + "\n\n");

            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] ColumnaEncabezado = new float[]{20f, 10f, 60f, 30f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);

            Encabezado.addCell(pdfImage);

            String nombreApp = "Registro De Ventas Yanbal";
            String creador = "Bleider Hernandez Morales";
            String nombreUsu = "Gabriela Lopez Calderón";

            Encabezado.addCell("");
            Encabezado.addCell("Nombre App: " + nombreApp + "\nCreador: " + creador + "\nUsuario: " + nombreUsu);
            Encabezado.addCell(fecha);

            doc.add(Encabezado);

            Paragraph cliente = new Paragraph();
            cliente.add(Chunk.NEWLINE);
            cliente.add("""
                        Datos de los clientes
                        
                        """);
            doc.add(cliente);
//-----------------------------------------------------------------------------

            for (int i = 1; i <= id; i++) {
                //Cliente
                PdfPTable tabCliente = new PdfPTable(4);
                tabCliente.setWidthPercentage(100);
                tabCliente.getDefaultCell().setBorder(0);
                float[] columCliente = new float[]{45f, 50f, 40f, 30f};
                tabCliente.setWidths(columCliente);
                tabCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
                PdfPCell cl1 = new PdfPCell(new Phrase("Codigo Cliente", negrita));
                PdfPCell cl2 = new PdfPCell(new Phrase("Nombre Cliente", negrita));
                PdfPCell cl3 = new PdfPCell(new Phrase("Direccion", negrita));
                PdfPCell cl4 = new PdfPCell(new Phrase("Telefono", negrita));
                cl1.setBorder(0);
                cl2.setBorder(0);
                cl3.setBorder(0);
                cl4.setBorder(0);
                tabCliente.addCell(cl1);
                tabCliente.addCell(cl2);
                tabCliente.addCell(cl3);
                tabCliente.addCell(cl4);

                // obtenemos los registros de la tabla mis ventas por medio del id
                mv = misven.BuscarIdRegistroMisVentas(i);
                int codiClie = mv.getCodigo_cliente(); // Obtenemos codigo del cliente

                if (codiClie != 0) {
                    cl = client.BuscarClientesXCodigo(codiClie); // Por medio del codigo obtenemos los demas datos del cliente
                    String nombrCli = cl.getNombre();
                    String dirCli = cl.getDireccion();
                    String telCli = cl.getTelefono();

                    tabCliente.addCell("" + codiClie);
                    tabCliente.addCell(nombrCli);
                    tabCliente.addCell(dirCli);
                    tabCliente.addCell(telCli);

                    doc.add(tabCliente);

                    doc.add(new Paragraph("\n"));// Salto de linea entre tablas
//-----------------------------------------------------------------------------------
                    //Productos
                    PdfPTable tabProducto = new PdfPTable(6);
                    tabProducto.setWidthPercentage(100);
                    tabProducto.getDefaultCell().setBorder(0);
                    float[] columProducto = new float[]{45f, 50f, 25f, 40f, 40f, 35f};
                    tabProducto.setWidths(columProducto);
                    tabProducto.setHorizontalAlignment(Element.ALIGN_LEFT);
                    PdfPCell pr1 = new PdfPCell(new Phrase("Codigo Producto", negrita));
                    PdfPCell pr2 = new PdfPCell(new Phrase("Nombre Producto", negrita));
                    PdfPCell pr3 = new PdfPCell(new Phrase("Cantidad", negrita));
                    PdfPCell pr4 = new PdfPCell(new Phrase("Precio Factura", negrita));
                    PdfPCell pr5 = new PdfPCell(new Phrase("Precio Vendido", negrita));
                    PdfPCell pr6 = new PdfPCell(new Phrase("Total", negrita));
                    pr1.setBorder(0);
                    pr2.setBorder(0);
                    pr3.setBorder(0);
                    pr4.setBorder(0);
                    pr5.setBorder(0);
                    pr6.setBorder(0);
                    pr1.setBackgroundColor(BaseColor.PINK);
                    pr2.setBackgroundColor(BaseColor.PINK);
                    pr3.setBackgroundColor(BaseColor.PINK);
                    pr4.setBackgroundColor(BaseColor.PINK);
                    pr5.setBackgroundColor(BaseColor.PINK);
                    pr6.setBackgroundColor(BaseColor.PINK);
                    tabProducto.addCell(pr1);
                    tabProducto.addCell(pr2);
                    tabProducto.addCell(pr3);
                    tabProducto.addCell(pr4);
                    tabProducto.addCell(pr5);
                    tabProducto.addCell(pr6);

                    for (int j = 1; j <= idMaxDtm; j++) { // idMaxDtm --> id maximo de tabla `detalle_mis_ventas`
                        dmv = misven.BuscarIdMisVentas(j); // Traemos los datos de la tabla `mis_ventas` por medio del id
                        if (dmv.getIdVenta() == i) {

                            int codipro = dmv.getCodigoProducto();
                            int canti = dmv.getCantidad();
                            BigDecimal precfact = dmv.getPrecioFactura();
                            BigDecimal precvend = dmv.getPrecioVendido();
                            BigDecimal tot = dmv.getTotal();

                            //buscamos el nombre del producto
                            pr = produ.BuscarProductosXCodigo(codipro);
                            String nombrePro = pr.getNombre();

                            tabProducto.addCell("" + codipro);
                            tabProducto.addCell("" + nombrePro);
                            tabProducto.addCell("" + canti);
                            tabProducto.addCell("$" + formatoDecimal.format(precfact));
                            tabProducto.addCell("$" + formatoDecimal.format(precvend));
                            tabProducto.addCell("$" + formatoDecimal.format(tot));

                        }
                    }

                    doc.add(tabProducto);

                    doc.add(new Paragraph("\n"));// Salto de linea entre tablas
//----------------------------------------------------------------------------

                    //Abonado y deuda
                    PdfPTable tabAbono = new PdfPTable(3);
                    tabAbono.setWidthPercentage(100);
                    tabAbono.getDefaultCell().setBorder(0);
                    float[] columAbono = new float[]{35f, 40f, 50f};
                    tabAbono.setWidths(columAbono);
                    tabAbono.setHorizontalAlignment(Element.ALIGN_LEFT);
                    PdfPCell ab1 = new PdfPCell(new Phrase("Abonado", negrita));
                    PdfPCell ab2 = new PdfPCell(new Phrase("Debe", negrita));
                    PdfPCell ab3 = new PdfPCell(new Phrase("Total a pagar", negrita));

                    ab1.setBorder(0);
                    ab2.setBorder(0);
                    ab3.setBorder(0);
                    ab1.setBackgroundColor(BaseColor.PINK);
                    ab2.setBackgroundColor(BaseColor.PINK);
                    ab3.setBackgroundColor(BaseColor.PINK);
                    tabAbono.addCell(ab1);
                    tabAbono.addCell(ab2);
                    tabAbono.addCell(ab3);

                    BigDecimal abona = mv.getAbonado();
                    BigDecimal debe = mv.getDebe();
                    BigDecimal pagaTotal = mv.getTotal();

                    tabAbono.addCell("$" + formatoDecimal.format(abona));
                    tabAbono.addCell("$" + formatoDecimal.format(debe));
                    tabAbono.addCell("$" + formatoDecimal.format(pagaTotal));

                    doc.add(tabAbono);

                    doc.add(new Paragraph("""
                                      
                                      
                                      ______________________________________________________________________________
                                      
                                      
                                      
                                      """));
                }
            }
            //Cliente

//----------------------------------------------------------------------------
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(file);

        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    private void pdfMiVendedores() {
        try {
            int idMVendedores = misvendedor.idMisVendedores();
            int idDetMVendedores = misvendedor.idDetalleMisVendedores();
            FileOutputStream archivo;
            //File directory = new File("C:\\Users\\Eduard\\Desktop\\YanbalPdf");
            File directory = new File("C:\\Users\\57301\\Desktop\\YanbalPdf");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File file = new File(directory, "MisVendedores" + idMVendedores + ".pdf");

            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            com.itextpdf.text.Image pdfImage = null;
            pdfImage = com.itextpdf.text.Image.getInstance(getClass().getResource("/images/yanbalCatalogo.png"));

            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.RED);
            fecha.add(Chunk.NEWLINE);
            Date date = new Date();
            fecha.add("Factura:" + idMVendedores + "\n" + "Fecha: " + new SimpleDateFormat("dd-MM-yyyy").format(date) + "\n\n");

            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] ColumnaEncabezado = new float[]{20f, 10f, 60f, 30f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);

            Encabezado.addCell(pdfImage);

            String nombreApp = "Registro De Ventas Yanbal";
            String creador = "Bleider Hernandez Morales";
            String nombreUsu = "Gabriela Lopez Calderón";

            Encabezado.addCell("");
            Encabezado.addCell("Nombre App: " + nombreApp + "\nCreador: " + creador + "\nUsuario: " + nombreUsu);
            Encabezado.addCell(fecha);

            doc.add(Encabezado);

            Paragraph cliente = new Paragraph();
            cliente.add(Chunk.NEWLINE);
            cliente.add("""
                        Datos de los vendedores
                        
                        """);
            doc.add(cliente);
//-----------------------------------------------------------------------------

            for (int i = 1; i <= idMVendedores; i++) {

                //Vendedor
                PdfPTable tabVendedor = new PdfPTable(4);
                tabVendedor.setWidthPercentage(100);
                tabVendedor.getDefaultCell().setBorder(0);
                float[] columVendedor = new float[]{45f, 50f, 40f, 30f};
                tabVendedor.setWidths(columVendedor);
                tabVendedor.setHorizontalAlignment(Element.ALIGN_LEFT);
                PdfPCell ve1 = new PdfPCell(new Phrase("Codigo Vendedor", negrita));
                PdfPCell ve2 = new PdfPCell(new Phrase("Nombre Vendedor", negrita));
                PdfPCell ve3 = new PdfPCell(new Phrase("Direccion", negrita));
                PdfPCell ve4 = new PdfPCell(new Phrase("Telefono", negrita));
                ve1.setBorder(0);
                ve2.setBorder(0);
                ve3.setBorder(0);
                ve4.setBorder(0);
                tabVendedor.addCell(ve1);
                tabVendedor.addCell(ve2);
                tabVendedor.addCell(ve3);
                tabVendedor.addCell(ve4);

                // obtenemos los registros de la tabla mis ventas por medio del id
                mvr = misvendedor.BuscarIdRegistroMisVendedores(i);
                if (mvr.getFecha() != null) {

                    int codiVende = mvr.getCodigoVendedor(); // Obtenemos codigo del vendedor
                    ve = vend.BuscarVendedoresXCodigo(codiVende); // Por medio del codigo obtenemos los demas datos del vendedor
                    String nombrVende = ve.getNombre();
                    String dirVende = ve.getDireccion();
                    String telVende = ve.getTelefono();

                    tabVendedor.addCell("" + codiVende);
                    tabVendedor.addCell(nombrVende);
                    tabVendedor.addCell(dirVende);
                    tabVendedor.addCell(telVende);

                    doc.add(tabVendedor);

                    doc.add(new Paragraph("\n"));// Salto de linea entre tablas
//-----------------------------------------------------------------------------------
                    //Productos

                    PdfPTable tabProducto = new PdfPTable(5);
                    tabProducto.setWidthPercentage(100);
                    tabProducto.getDefaultCell().setBorder(0);
                    float[] columProducto = new float[]{45f, 50f, 25f, 40f, 40f};
                    tabProducto.setWidths(columProducto);
                    tabProducto.setHorizontalAlignment(Element.ALIGN_LEFT);
                    PdfPCell pr1 = new PdfPCell(new Phrase("Codigo Producto", negrita));
                    PdfPCell pr2 = new PdfPCell(new Phrase("Nombre Producto", negrita));
                    PdfPCell pr3 = new PdfPCell(new Phrase("Cantidad", negrita));
                    PdfPCell pr4 = new PdfPCell(new Phrase("Precio ", negrita));
                    PdfPCell pr5 = new PdfPCell(new Phrase("Total", negrita));
                    pr1.setBorder(0);
                    pr2.setBorder(0);
                    pr3.setBorder(0);
                    pr4.setBorder(0);
                    pr5.setBorder(0);
                    pr1.setBackgroundColor(BaseColor.PINK);
                    pr2.setBackgroundColor(BaseColor.PINK);
                    pr3.setBackgroundColor(BaseColor.PINK);
                    pr4.setBackgroundColor(BaseColor.PINK);
                    pr5.setBackgroundColor(BaseColor.PINK);
                    tabProducto.addCell(pr1);
                    tabProducto.addCell(pr2);
                    tabProducto.addCell(pr3);
                    tabProducto.addCell(pr4);
                    tabProducto.addCell(pr5);

                    for (int j = 1; j <= idDetMVendedores; j++) {// idDetMVendedores --> id maximo de la tabla 'detalle_mis_vendedores'
                        dmvr = misvendedor.BuscarIdMisVendedores(j); // traemos los datos por el id de la tabla `detalle mis vendedores`
                        if (dmvr.getIdMisVendedores() == i) {
                            // hacer proceso
                            int codipro = dmvr.getCodigoProducto();
                            int canti = dmvr.getCantidad();
                            BigDecimal prec = dmvr.getPrecio();
                            BigDecimal tot = dmvr.getTotal();

                            //buscamos el nombre del producto
                            pr = produ.BuscarProductosXCodigo(codipro);
                            String nombrePro = pr.getNombre();

                            tabProducto.addCell("" + codipro);
                            tabProducto.addCell("" + nombrePro);
                            tabProducto.addCell("" + canti);
                            tabProducto.addCell("" + formatoDecimal.format(prec));
                            tabProducto.addCell("" + formatoDecimal.format(tot));
                        }
                    }

                    doc.add(tabProducto);

                    doc.add(new Paragraph("\n"));// Salto de linea entre tablas

//----------------------------------------------------------------------------
                    //Fecha limite 
                    Paragraph fechaLimite = new Paragraph();
                    Date fechaVence = mvr.getFecha();
                    fechaLimite.add(Chunk.NEWLINE);
                    fechaLimite.setAlignment(Element.ALIGN_RIGHT);
                    fechaLimite.add("Fecha limite: " + new SimpleDateFormat("dd-MM-yyyy").format(fechaVence));
                    doc.add(fechaLimite);

//-----------------------------------------------------------------------------
                    //falta paragraph de total a pagar
                    Paragraph pTotal = new Paragraph();
                    String pagaTotal_ = mvr.getTotal().toString();
                    BigDecimal pagaTotal = new BigDecimal(pagaTotal_);
                    String pagaTotalFormateado = formatoDecimal.format(pagaTotal);
                    pTotal.add(Chunk.NEWLINE);
                    pTotal.setAlignment(Element.ALIGN_RIGHT);
                    pTotal.add("Total a pagar:  $" + pagaTotalFormateado);
                    doc.add(pTotal);

                    doc.add(new Paragraph("""
                                      
                                      
                                      ______________________________________________________________________________
                                      
                                      
                                      
                                      """));
                }
            }

//----------------------------------------------------------------------------
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(file);

        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

}
