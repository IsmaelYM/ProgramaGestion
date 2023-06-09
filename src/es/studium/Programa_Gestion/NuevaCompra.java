package es.studium.Programa_Gestion;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class NuevaCompra implements WindowListener, ActionListener{
	
	Frame ventana = new Frame("Nueva Compra");
	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);
	
	Label lblTitulo = new Label("         Nueva Compra        ");
	Label lblCantidad = new Label("Cantidad (unidades):");
	Choice choProductos = new Choice();
	Choice choClientes = new Choice();
	Label lblFechaCompra = new Label("Fecha");
	Label lblMensaje = new Label("Compra Realizada");
	
	TextField txtCantidad = new TextField(10);
	TextField txtFechaCompra = new TextField(10);
	
	Button btnSiguiente = new Button("Siguiente");
	Button btnCancelar = new Button("Cancelar");
	
	String idProducto = "";
	String idCliente = "";
	Conexion conexion = new Conexion();
	
	NuevaCompra(){
		
		ventana.setLayout(new FlowLayout());
		ventana.setSize(170, 260);
		ventana.addWindowListener(this);
		
		ventana.add(lblTitulo);
		
		conexion.rellenarChoiceClientes(choClientes);
		ventana.add(choClientes);
		
		ventana.add(lblFechaCompra);
		ventana.add(txtFechaCompra);
		
		conexion.rellenarChoiceProducto(choProductos);
		ventana.add(choProductos);
		
		ventana.add(lblCantidad);
		ventana.add(txtCantidad);
		
		btnSiguiente.addActionListener(this);
		btnCancelar.addActionListener(this);

		ventana.add(btnSiguiente);
		ventana.add(btnCancelar);

		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(btnSiguiente))
		{
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(200,100);
			dlgMensaje.addWindowListener(this);
			String tabla[] = choProductos.getSelectedItem().split("-");
			//String resultado = conexion.getDatosEdicionProducto(tabla[0]);
			idProducto = tabla[0];
			
			String tabla1[] = choClientes.getSelectedItem().split("-");
			String resultado1 = conexion.getDatosEdicion(tabla1[0]);
			String datos1[] = resultado1.split("-");
			idCliente = datos1[0];
			
			TextField txtNombre = new TextField();
			txtNombre.setText(datos1[1]);
			if(txtCantidad.getText().length()==0)
			{
				lblMensaje.setText("Los campos están vacíos");
			}
			else
			{
				// Dar de alta
				String fechaCompra = txtFechaCompra.getText();
				DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
				DateFormat outputFormat = new SimpleDateFormat("dd-MM-yy");

				try {
				    java.util.Date date = inputFormat.parse(fechaCompra);
				    String formattedDate = outputFormat.format(date);
				    String sentencia = "INSERT INTO comprar (idCompra, fechaCompra, cantidadCompra, idClienteFK, idProductoFK) VALUES (null, '"+ formattedDate +"', '"+ txtCantidad.getText() +"', "+idCliente+", "+idProducto+");";
				    // Resto del código para ejecutar la sentencia SQL
			


						
				int respuesta = conexion.altaCompra(sentencia);
				if(respuesta!=0)
				{
					// Mostrar Mensaje Error
					lblMensaje.setText("Error en Compra");
				}
				} catch (ParseException pe) {
				    // Manejo del error en caso de que la fecha no esté en el formato esperado
				    pe.printStackTrace();
				}
			}

			dlgMensaje.add(lblMensaje);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);
		}
		else if(e.getSource().equals(btnCancelar))
		{
			txtCantidad.setText("");
		}
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		if(dlgMensaje.isActive())
		{
			dlgMensaje.setVisible(false);
		}
		else
		{
			ventana.setVisible(false);
		}
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
