package es.studium.Programa_Gestion;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class NuevoCliente implements WindowListener, ActionListener{
	Frame ventana = new Frame("Nuevo Cliente");
	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);

	Label lblTitulo = new Label("         Alta de Cliente       ");
	Label lblNombre = new Label("Nombre:               ");
	Label lblPrimerApellidoCliente = new Label("Primer Apellido:   ");
	Label lblSegundoApellidoCliente = new Label("Segundo Apellido:");
	Label lblCorreo = new Label("Correo:                 ");
	Label lblTelefonoCliente = new Label("Teléfono:              ");
	Label lblMensaje = new Label("Alta de Cliente Correcta");

	TextField txtNombre = new TextField(10);
	TextField txtPrimerApellidoCliente = new TextField(10);
	TextField txtSegundoApellidoCliente = new TextField(10);
	TextField txtCorreo = new TextField(10);
	TextField txtTelefonoCliente = new TextField(10);

	Button btnAceptar = new Button("Aceptar");
	Button btnCancelar = new Button("Cancelar");

	Conexion conexion = new Conexion();

	NuevoCliente()
	{
		ventana.setLayout(new FlowLayout());
		ventana.setSize(240,250);
		ventana.addWindowListener(this);

		ventana.add(lblTitulo);
		ventana.add(lblNombre);
		ventana.add(txtNombre);
		ventana.add(lblPrimerApellidoCliente);
		ventana.add(txtPrimerApellidoCliente);
		ventana.add(lblSegundoApellidoCliente);
		ventana.add(txtSegundoApellidoCliente);
		ventana.add(lblCorreo);
		ventana.add(txtCorreo);
		ventana.add(lblTelefonoCliente);
		ventana.add(txtTelefonoCliente);

		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);

		ventana.add(btnAceptar);
		ventana.add(btnCancelar);

		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		
		dlgMensaje.add(lblMensaje);
		dlgMensaje.setLayout(new FlowLayout());
		dlgMensaje.setSize(200, 100);
		dlgMensaje.addWindowListener(this);
		dlgMensaje.setLocationRelativeTo(null);
	}

	@Override
	public void windowOpened(WindowEvent e){}
	@Override
	public void windowClosing(WindowEvent e)
	{
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
		// Nuevo Cliente
		if(e.getSource().equals(btnAceptar))
		{
			conexion.conectar();
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(200,150);
			dlgMensaje.addWindowListener(this);
			if(txtNombre.getText().length()==0||txtPrimerApellidoCliente.getText().length()==0||txtSegundoApellidoCliente.getText().length()==0||txtCorreo.getText().length()==0||txtTelefonoCliente.getText().length()==0)
			{
				lblMensaje.setText("Los campos están vacíos");
			}
			
			else
			{
				// Dar de alta
				String sentencia = "INSERT INTO cliente (idCliente, nombreCliente, primerApellidoCliente, segundoApellidoCliente, correoElectronicoCliente, telefonoCliente) VALUES (null, '"+txtNombre.getText()+"','"+txtPrimerApellidoCliente.getText()+"','"+txtSegundoApellidoCliente.getText()+"', '"+txtCorreo.getText()+"', '"+txtTelefonoCliente.getText()+"');";
				int respuesta = conexion.nuevoCliente(sentencia);
				if(respuesta!=0)
				{
					// Mostrar Mensaje Error
					lblMensaje.setText("Error en Alta");
				}
			}

			dlgMensaje.add(lblMensaje);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);
		}
		else if(e.getSource().equals(btnCancelar))
		{
			txtNombre.setText("");
			txtPrimerApellidoCliente.setText("");
			txtSegundoApellidoCliente.setText("");
			txtCorreo.setText("");
			txtTelefonoCliente.setText("");
			txtNombre.requestFocus();
		}
	}
}
