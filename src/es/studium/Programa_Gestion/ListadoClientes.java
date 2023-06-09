package es.studium.Programa_Gestion;

import java.awt.Button;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import com.itextpdf.io.exceptions.IOException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;


public class ListadoClientes implements WindowListener, ActionListener{
	Frame ventana = new Frame("Listado Clientes");
	
	Label lblId = new Label("Id");
	Label lblNombre = new Label("Nombre");
	Label lblCorreo = new Label("Correo");
	TextArea txaListado = new TextArea(6, 30);
	Button btnPdf = new Button("PDF");
	

	Conexion conexion = new Conexion();

	ListadoClientes()
	{
		ventana.setLayout(new FlowLayout());
		ventana.setSize(300,230);
		ventana.addWindowListener(this);
		
		// Rellenar el TextArea
		conexion.rellenarListadoClientes(txaListado);
		
		ventana.add(lblId);
		ventana.add(lblNombre);
		ventana.add(lblCorreo);
		ventana.add(txaListado);
		ventana.add(btnPdf);
		btnPdf.addActionListener(this);

		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
	@Override
	public void windowOpened(WindowEvent e){}
	@Override
	public void windowClosing(WindowEvent e)
	{
		ventana.setVisible(false);
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
		if(e.getSource().equals(btnPdf)) {
			try
			{
			//Initialize PDF writer
			PdfWriter writer = new PdfWriter("imprimir.pdf");
			//Initialize PDF document
			PdfDocument pdf = new PdfDocument(writer);
			// Initialize document
			Document document = new Document(pdf);
			//Add paragraph to the document
			document.add(new Paragraph("Clientes\n"+"Id   -   "+"Nombre   -   "+" Correo\n"+ txaListado.getText()));
			//Close document
			document.close();
			// Open the new PDF document just created
			Desktop.getDesktop().open(new File("imprimir.pdf"));
			}
			catch(IOException | java.io.IOException ioe) {}
		}
	}
}
