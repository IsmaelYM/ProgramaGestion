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

public class ModificarCliente implements WindowListener, ActionListener{
	Frame ventana = new Frame("Editar Cliente");
	Dialog dlgEdicion = new Dialog(ventana, "Edición", true);
	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);

	Label lblElegir = new Label("Elegir el cliente a editar:");

	Choice choClientes = new Choice();

	Button btnEditar = new Button("Editar");

	Conexion conexion = new Conexion();

	Label lblTitulo = new Label("------- Edición de Cliente -------");
	Label lblNombre = new Label("Nombre:               ");
	Label lblPrimerApellidoCliente = new Label("Primer Apellido:   ");
	Label lblSegundoApellidoCliente = new Label("Segundo Apellido:");
	Label lblCorreo = new Label("Correo:                 ");
	Label lblTelefonoCliente = new Label("Teléfono:              ");
	Label lblMensaje = new Label("Modificación de Cliente Correcta");

	TextField txtNombre = new TextField(10);
	TextField txtPrimerApellidoCliente = new TextField(10);
	TextField txtSegundoApellidoCliente = new TextField(10);
	TextField txtCorreo = new TextField(10);
	TextField txtTelefonoCliente = new TextField(10);

	Button btnModificar = new Button("Modificar");
	Button btnCancelar = new Button("Cancelar");

	String idCliente = "";

	ModificarCliente()
	{
		ventana.setLayout(new FlowLayout());
		ventana.setSize(220,220);
		ventana.addWindowListener(this);

		ventana.add(lblElegir);
		// Rellenar el Choice
		conexion.rellenarChoiceClientes(choClientes);
		ventana.add(choClientes);
		btnEditar.addActionListener(this);
		ventana.add(btnEditar);

		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e){}
	@Override
	public void windowClosing(WindowEvent e)
	{
		if(dlgEdicion.isActive())
		{
			dlgEdicion.setVisible(false);
			ventana.setVisible(false);
		}
		else if (dlgMensaje.isActive())
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
		if(e.getSource().equals(btnEditar))
		{
			if(choClientes.getSelectedIndex()!=0)
			{
				dlgEdicion.setLayout(new FlowLayout());
				dlgEdicion.setSize(240,290);
				dlgEdicion.addWindowListener(this);

				dlgEdicion.add(lblTitulo);
				dlgEdicion.add(lblNombre);
				dlgEdicion.add(txtNombre);
				dlgEdicion.add(lblPrimerApellidoCliente);
				dlgEdicion.add(txtPrimerApellidoCliente);
				dlgEdicion.add(lblSegundoApellidoCliente);
				dlgEdicion.add(txtSegundoApellidoCliente);
				dlgEdicion.add(lblCorreo);
				dlgEdicion.add(txtCorreo);
				dlgEdicion.add(lblTelefonoCliente);
				dlgEdicion.add(txtTelefonoCliente);

				btnModificar.addActionListener(this);
				btnCancelar.addActionListener(this);

				dlgEdicion.add(btnModificar);
				dlgEdicion.add(btnCancelar);

				dlgEdicion.setResizable(false);
				dlgEdicion.setLocationRelativeTo(null);

				String tabla[] = choClientes.getSelectedItem().split("-");
				String resultado = conexion.getDatosEdicion(tabla[0]);

				String datos[] = resultado.split("-");
				idCliente = datos[0];
				txtNombre.setText(datos[1]);
				txtPrimerApellidoCliente.setText("");
				txtSegundoApellidoCliente.setText("");
				txtCorreo.setText("");
				txtTelefonoCliente.setText("");

				dlgEdicion.setVisible(true);
			}
		}
		else if(e.getSource().equals(btnModificar))
		{
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(200,100);
			dlgMensaje.addWindowListener(this);
			
			if(txtNombre.getText().length()==0||txtPrimerApellidoCliente.getText().length()==0||txtSegundoApellidoCliente.getText().length()==0||txtCorreo.getText().length()==0||txtTelefonoCliente.getText().length()==0)
			{
				lblMensaje.setText("Los campos están vacíos");
			}
			else
			{
				// Modificar
				String sentencia = "UPDATE cliente SET nombreCliente='"+txtNombre.getText()+"', primerApellidoCliente='"+txtPrimerApellidoCliente.getText()+"', segundoApellidoCliente='"+txtSegundoApellidoCliente.getText()+"', correoElectronicoCliente = '"+txtCorreo.getText()+"',telefonoCliente = '"+txtTelefonoCliente.getText()+"' WHERE idCliente="+idCliente+";";
				int respuesta = conexion.modificarCliente(sentencia);
				if(respuesta!=0)
				{
					// Mostrar Mensaje Error
					lblMensaje.setText("Error en Modificación");
				}
			}

			dlgMensaje.add(lblMensaje);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);
		}
		else if (e.getSource().equals(btnCancelar))
		{
			dlgEdicion.setVisible(false);
		}
	}
}
