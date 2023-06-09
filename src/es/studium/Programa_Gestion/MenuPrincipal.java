package es.studium.Programa_Gestion;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MenuPrincipal implements WindowListener, ActionListener{
	Frame ventana = new Frame("Menú Principal");

	MenuBar menuBar = new MenuBar();

	Menu mnuClientes = new Menu("Clientes");
	Menu mnuProductos = new Menu("Productos");
	Menu mnuProveedor = new Menu("Proveedores");
	Menu mnuCompras = new Menu("Compras");
	Menu mnuAyuda = new Menu("Ayuda");

	MenuItem mniNuevoCliente = new MenuItem("Nuevo");
	MenuItem mniListadoCliente = new MenuItem("Listado");
	MenuItem mniBajaCliente = new MenuItem("Baja");
	MenuItem mniModificarCliente = new MenuItem("Modificar");

	MenuItem mniNuevoProducto = new MenuItem("Nuevo");
	MenuItem mniListadoProducto = new MenuItem("Listado");
	MenuItem mniBajaProducto = new MenuItem("Baja");
	MenuItem mniModificarProducto = new MenuItem("Modificar");

	MenuItem mniNuevoProveedor = new MenuItem("Nuevo");
	MenuItem mniListadoProveedor = new MenuItem("Listado");
	
	MenuItem mniNuevaCompra = new MenuItem("Nueva Compra");
	MenuItem mniFacturas = new MenuItem("Facturas");
	
	MenuItem mniAyuda = new MenuItem("Ayuda");
	
	Conexion conexion = new Conexion();
	
	int tipoUsuario;

	MenuPrincipal(int t)
	{
		tipoUsuario = t;
		ventana.setLayout(new FlowLayout());
		ventana.setSize(400,400);
		ventana.addWindowListener(this);
		ventana.setBackground(Color.GRAY);

		mniNuevoCliente.addActionListener(this);
		mniListadoCliente.addActionListener(this);
		mniBajaCliente.addActionListener(this);
		mniModificarCliente.addActionListener(this);
		mnuClientes.add(mniNuevoCliente);
		
		if(tipoUsuario==0)
		{
			mnuClientes.add(mniListadoCliente);
			mnuClientes.add(mniBajaCliente);
			mnuClientes.add(mniModificarCliente);
		}

		mniNuevoProducto.addActionListener(this);
		mniListadoProducto.addActionListener(this);
		mniBajaProducto.addActionListener(this);
		mniModificarProducto.addActionListener(this);
		mnuProductos.add(mniNuevoProducto);
		if(tipoUsuario==0)
		{
			mnuProductos.add(mniListadoProducto);
			mnuProductos.add(mniBajaProducto);
			mnuProductos.add(mniModificarProducto);
		}
		
		mniNuevoProveedor.addActionListener(this);
		mniListadoProveedor.addActionListener(this);
		mnuProveedor.add(mniNuevoProveedor);
		if(tipoUsuario==0)
		{
			mnuProveedor.add(mniListadoProveedor);
			
		}
		
		mniNuevaCompra.addActionListener(this);
		mniFacturas.addActionListener(this);
		mnuCompras.add(mniNuevaCompra);
		if(tipoUsuario==0)
		{
			mnuCompras.add(mniFacturas);
			
		}
		mniAyuda.addActionListener(this);
		mnuAyuda.add(mniAyuda);
		if(tipoUsuario==0)
		{
			mnuAyuda.add(mniAyuda);
			
		}

		menuBar.add(mnuClientes);
		menuBar.add(mnuProductos);
		menuBar.add(mnuProveedor);
		menuBar.add(mnuCompras);
		menuBar.add(mnuAyuda);

		ventana.setMenuBar(menuBar);

		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
	@Override
	public void windowOpened(WindowEvent e){}
	@Override
	public void windowClosing(WindowEvent e)
	{
		conexion.apunteLog("Desconexión del Sistema");
		System.exit(0);
	}
	@Override
	public void windowClosed(WindowEvent e){}
	@Override
	public void windowIconified(WindowEvent e){}
	@Override
	public void windowDeiconified(WindowEvent e){}
	@Override
	public void windowActivated(WindowEvent e){}
	@Override
	public void windowDeactivated(WindowEvent e){}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Nuevo Usuario
		if(e.getSource().equals(mniNuevoCliente))
		{
			new NuevoCliente();
		}
		// Listado Usuarios
		else if(e.getSource().equals(mniListadoCliente))
		{
			new ListadoClientes();
		}
		// Eliminar Usuario
		else if(e.getSource().equals(mniBajaCliente))
		{
			new EliminarCliente();
		}
		// Modificar Usuario
		else if(e.getSource().equals(mniModificarCliente))
		{
			new ModificarCliente();
		}
		else if(e.getSource().equals(mniNuevoProducto))
		{
			new NuevoProducto();
		}
		else if(e.getSource().equals(mniListadoProducto))
		{
			new ListadoProductos();
		}
		else if(e.getSource().equals(mniBajaProducto))
		{
			new EliminarProducto();
		}
		else if(e.getSource().equals(mniModificarProducto))
		{
			new ModificarProducto();
		}
		else if(e.getSource().equals(mniNuevoProveedor))
		{
			new NuevoProveedor();
		}
		else if(e.getSource().equals(mniListadoProveedor))
		{
			new ListadoProveedores();
		}
		else if(e.getSource().equals(mniNuevaCompra))
		{
			new NuevaCompra();
		}
		else if(e.getSource().equals(mniFacturas))
		{
			new ListadoCompras();
		}
		if(e.getSource().equals(mniAyuda))
		{
			Ayuda ayuda = new Ayuda();
			ayuda.help();
					
		}
	}
}
