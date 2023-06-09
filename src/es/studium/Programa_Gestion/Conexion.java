package es.studium.Programa_Gestion;

import java.awt.Choice;
import java.awt.TextArea;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Conexion {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/aceitessl";
	String login = "admin";
	String password = "Studium2023;";
	String sentencia = "";

	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	
	
	//static String nombreUsuario;
	static String usuario;

	Conexion()
	{
		connection = this.conectar();
	}

	public Connection conectar()
	{
		try
		{
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			// Establecer la conexión con la BD Empresa
			return(DriverManager.getConnection(url, login, password));
		}
		catch (ClassNotFoundException cnfe)
		{
			System.out.println("Error 1-"+cnfe.getMessage());
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return null;
	}

	public int comprobarCredenciales(String u, String c)
	{
		usuario = u;
		String cadena = "SELECT * FROM usuarios WHERE nombreUsuario = '"+ u + "' AND claveUsuario = SHA2('" + c + "',256);";
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			rs = statement.executeQuery(cadena);
			apunteLog("Acceso a sistema");
			if(rs.next())
			{
				return rs.getInt("tipoUsuario");
			}
			else
			{
				return -1;
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 3-"+sqle.getMessage());
		}
		return -1;
	}

	public int nuevoCliente(String sentencia)
	{
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			statement.executeUpdate(sentencia);
			apunteLog("ALTA Cliente: "+sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 4-"+sqle.getMessage());
			return 1;
		}
	}

	public void rellenarListadoClientes(TextArea txaListado)
	{
		String sentencia = "SELECT idCliente, nombreCliente, primerApellidoCliente, correoElectronicoCliente FROM cliente;";
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			ResultSet resultado = statement.executeQuery(sentencia);
			while(resultado.next())
			{
				txaListado.append(resultado.getString("idCliente")+"  -  ");
				txaListado.append(resultado.getString("nombreCliente")+" ");
				txaListado.append(resultado.getString("primerApellidoCliente")+"    -    ");
				txaListado.append(resultado.getString("correoElectronicoCliente")+"\n");

			}

		}
		catch (SQLException sqle)
		{
			System.out.println("Error 5-"+sqle.getMessage());
		}
	}

	public void rellenarChoiceClientes(Choice choClientes)
	{
		String sentencia = "SELECT idCliente, nombreCliente FROM cliente ORDER BY 1;";
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			ResultSet resultado = statement.executeQuery(sentencia);
			choClientes.add("Elegir cliente...");
			while(resultado.next())
			{
				choClientes.add(resultado.getString("idCliente")+"-"+resultado.getString("nombreCliente"));
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 6-"+sqle.getMessage());
		}


	}

	public int eliminarCliente(String idCliente)
	{
		String sentencia = "DELETE FROM cliente WHERE idCliente = " + idCliente;
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia SQL
			statement.executeUpdate(sentencia);
			apunteLog("BAJA Cliente: "+sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 7-"+sqle.getMessage());
			return 1;
		}
	}

	public String getDatosEdicion(String idCliente)
	{
		String resultado = "";
		String sentencia = "SELECT * FROM cliente WHERE idCliente = " + idCliente;
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			ResultSet resultSet = statement.executeQuery(sentencia);
			if (resultSet.next()) {
	            resultado = resultSet.getString("idCliente") + "-" +
	                        resultSet.getString("nombreCliente") + "-" +
	                        resultSet.getString("correoElectronicoCliente");
	        }
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 8-"+sqle.getMessage());
		}
		return resultado;
	}

	public int modificarCliente(String sentencia)
	{
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia SQL
			statement.executeUpdate(sentencia);
			apunteLog("MODIFICAR Cliente: "+sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 9-"+sqle.getMessage());
			return 1;
		}
	}
	
	
	
	public int altaProducto(String sentencia)
	{
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			statement.executeUpdate(sentencia);
			apunteLog("ALTA Producto: "+sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 4-"+sqle.getMessage());
			return 1;
		}
	}

	public void rellenarListadoProductos(TextArea txaListadoProducto)
	{
		String sentencia = "SELECT idProducto, nombreProducto, stockProducto, precioProducto FROM producto;";
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			ResultSet resultado = statement.executeQuery(sentencia);
			while(resultado.next())
			{
				txaListadoProducto.append(resultado.getString("idProducto")+"    -    ");
				txaListadoProducto.append(resultado.getString("nombreProducto")+"    -    ");
				txaListadoProducto.append(resultado.getString("stockProducto")+"    -    ");
				txaListadoProducto.append(resultado.getString("precioProducto")+"\n");

			}

		}
		catch (SQLException sqle)
		{
			System.out.println("Error 5-"+sqle.getMessage());
		}
	}

	public int eliminarProducto(String idProducto)
	{
		String sentencia = "DELETE FROM producto WHERE idProducto = " + idProducto;
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia SQL
			statement.executeUpdate(sentencia);
			apunteLog("BAJA Producto: "+sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 7-"+sqle.getMessage());
			return 1;
		}
	}

	public String getDatosEdicionProducto(String idProducto)
	{
		String resultado = "";
		String sentencia = "SELECT * FROM producto WHERE idProducto = " + idProducto;
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			ResultSet resultSet = statement.executeQuery(sentencia);
			resultSet.next();
			resultado =(resultSet.getString("idProducto")+"-"+resultSet.getString("nombreProducto")+"-"+resultSet.getString("tipoProducto")+"-"+resultSet.getString("stockProducto")+"-"+resultSet.getString("precioProducto"));
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 8-"+sqle.getMessage());
		}
		return resultado;
	}

	public int modificarProducto(String sentencia)
	{
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia SQL
			statement.executeUpdate(sentencia);
			apunteLog("MODIFICAR Producto: "+sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 9-"+sqle.getMessage());
			return 1;
		}
	}
	
	public void rellenarChoiceProducto(Choice choProducto)
	{
		String sentencia = "SELECT idProducto, nombreProducto FROM producto ORDER BY 1;";
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			ResultSet resultado = statement.executeQuery(sentencia);
			choProducto.add("Elegir producto...");
			while(resultado.next())
			{
				choProducto.add(resultado.getString("idProducto")+"-"+resultado.getString("nombreProducto"));
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 6-"+sqle.getMessage());
		}
	}
	public int altaProveedor(String sentencia)
	{
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			statement.executeUpdate(sentencia);
			apunteLog("ALTA Proveedor: "+sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 4-"+sqle.getMessage());
			return 1;
		}
	}
	public void rellenarListadoProveedor(TextArea txaListadoProveedor)
	{
		String sentencia = "SELECT idProveedor, nombreProveedor, primerApellidoProveedor, segundoApellidoProveedor, telefonoProveedor, nombreProducto FROM proveedor JOIN producto ON proveedor.idProductoFK = producto.idProducto;";
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			ResultSet resultado = statement.executeQuery(sentencia);
			while(resultado.next())
			{
				txaListadoProveedor.append(resultado.getString("idProveedor")+". ");
				txaListadoProveedor.append(resultado.getString("nombreProveedor")+" ");
				txaListadoProveedor.append(resultado.getString("primerApellidoProveedor")+" ");
				txaListadoProveedor.append(resultado.getString("segundoApellidoProveedor")+"   -   ");
				txaListadoProveedor.append(resultado.getString("telefonoProveedor")+"   -   ");
				txaListadoProveedor.append(resultado.getString("nombreProducto")+"\n");

			}

		}
		catch (SQLException sqle)
		{
			System.out.println("Error 5-"+sqle.getMessage());
		}
	}
	public int altaCompra(String sentencia)
	{
		try
		{
			
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			statement.executeUpdate(sentencia);
			apunteLog("ALTA Compra: "+sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 4-"+sqle.getMessage());
			return 1;
		}
	}
	public void rellenarListadoFactura(TextArea txaListado) {
	    String sentencia = "SELECT idCompra, fechaCompra, cantidadCompra, nombreCliente, nombreProducto, precioProducto, (concat(cantidadCompra * precioProducto, ' €')) AS Total FROM comprar JOIN cliente ON comprar.idClienteFK = cliente.idCliente JOIN producto ON comprar.idProductoFK = producto.idProducto;";
	    try {
	        // Create a statement
	        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        // Create a ResultSet object to store the retrieved data and execute the SQL statement
	        ResultSet resultado = statement.executeQuery(sentencia);
	        while (resultado.next()) {
	            txaListado.append(resultado.getString("idCompra") + "     -     ");
	            txaListado.append(resultado.getString("nombreCliente") + "     -     ");
	            String fechaCompra = resultado.getString("fechaCompra");
	            if (fechaCompra != null) {
	                try {
	                    DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
	                    DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
	                    java.util.Date date = inputFormat.parse(fechaCompra);
	                    String formattedDate = outputFormat.format(date);
	                    txaListado.append(formattedDate + "    -    ");
	                } catch (ParseException e) {
	                    txaListado.append(fechaCompra + "    -    ");
	                }
	            } 
	            txaListado.append(resultado.getString("nombreProducto") + "     -     ");
	            txaListado.append(resultado.getString("cantidadCompra") + "     -     ");
	            txaListado.append(resultado.getString("precioProducto") + "     -     ");
	            txaListado.append(resultado.getString("Total") + "\n");
	        }
	    } catch (SQLException sqle) {
	        System.out.println("Error 5-" + sqle.getMessage());
	    }
	}

	/*public void rellenarListadoFactura(TextArea txaListado)
	{
		String sentencia = "SELECT idCompra, fechaCompra, cantidadCompra, nombreCliente, nombreProducto, precioProducto, (concat(cantidadCompra * precioProducto, ' €')) AS Total FROM comprar JOIN cliente ON comprar.idClienteFK = cliente.idCliente JOIN producto ON comprar.idProductoFK = producto.idProducto;";
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			ResultSet resultado = statement.executeQuery(sentencia);
			while(resultado.next())
			{
				txaListado.append(resultado.getString("idCompra")+"     -     ");
				txaListado.append(resultado.getString("nombreCliente")+"     -     ");
				txaListado.append(resultado.getString("fechaCompra")+"    -    ");
				txaListado.append(resultado.getString("nombreProducto")+"     -     ");
				txaListado.append(resultado.getString("cantidadCompra")+"     -     ");
				txaListado.append(resultado.getString("precioProducto")+"     -     ");
				txaListado.append(resultado.getString("Total")+"\n");
				

			}

		}
		catch (SQLException sqle)
		{
			System.out.println("Error 5-"+sqle.getMessage());
		}
	}*/
	
	public void apunteLog(String cadena) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String actual = dtf.format(now);
		
		String cadenaP = "[" + actual + "][" + usuario + "][" + cadena + "]";
		
		try {
			FileWriter fw = new FileWriter("Movimientos.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			pw.println(cadenaP);
			
			pw.close();
			bw.close();
			fw.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void setNombreUsuario (String nombreUsuario) {
		Conexion.usuario = nombreUsuario;
	}
	
}
