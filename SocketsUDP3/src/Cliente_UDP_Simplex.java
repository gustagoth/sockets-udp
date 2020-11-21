

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.Scanner;
import java.util.regex.Pattern;

import exceptions.tieneNumerosException;


public class Cliente_UDP_Simplex {

	public static void main(String[] args) {
		
		//VARIABLES
		String direccionIP;
		int puerto = 0;
		Scanner scanner = new Scanner(System.in);
		boolean chequearIp;
		boolean chequearPuerto;
		boolean chequearDireccion;
		boolean chequearNombre;
		boolean chequearTelefono;
		boolean chequearCiudad;
		boolean chequearPais;
		
		String ciudad = null;
		String pais = null;
		String nombre = null;
		String direccion;
		int telefono = 0;
		
		
		//COMIENZO
		System.out.println("Ingrese su nombre: ");
		do {
			chequearNombre=false;
			try {
				nombre = scanner.nextLine();
				tieneNumeros(nombre);
			} catch (tieneNumerosException e) {
				System.out.println("El nombre no puede llevar números");
				System.out.println("Reingresar: ");
				chequearNombre=true;
			}	
		} while (chequearNombre);
		
		System.out.println("Ingrese su dirección: ");
		direccion = scanner.nextLine();
		
		System.out.println("Ingrese su número de teléfono: ");
		do {
			chequearTelefono=false;
			try {
				telefono = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("El número no puede llevar letras");
				System.out.println("Reingresar: ");
				chequearTelefono=true;
			}	
		} while (chequearTelefono);
		
		
		System.out.println("Ingrese direccion IP server: ");
		direccionIP = scanner.nextLine();
		
		System.out.println("Ingrese puerto del server: ");
		do {
			chequearPuerto=false;
			try {
				puerto = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("El puerto debe contener solo números");
				System.out.println("Reingresar: ");
				chequearPuerto=true;
			}	
		} while (chequearPuerto);
		
		
		
		try{
			DatagramSocket  mySocket = new DatagramSocket();
	        
			String mensaje = "Nombre: " + nombre + " Dirección: " + direccion + " Teléfono: " +  telefono;
			
			byte[ ] buffer = mensaje.getBytes();
	        DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(direccionIP), puerto);
	        mySocket.send(datagram);
	        System.out.println("Envié la info");

	        
	        	        
	        
	        buffer = new byte[100];
	        datagram = new DatagramPacket(buffer, 100);
	        mySocket.receive(datagram);
	        String message = new String(datagram.getData(),0,datagram.getLength());
	        


	        
	        
	        
	        System.out.println("Se recibió un nuevo segmento UDP proveniente de la IP "+datagram.getAddress().getHostAddress()+" en el puerto remoto "+datagram.getPort());	        
	        System.out.println("El mensaje recibido es: ");
	        System.out.println(message);
	        
	        
	        
	        
	        
	        
	        System.out.println("Ingrese su nombre: ");
			do {
				chequearNombre=false;
				try {
					nombre = scanner.nextLine();
					tieneNumeros(nombre);
				} catch (tieneNumerosException e) {
					System.out.println("El nombre no puede llevar números");
					System.out.println("Reingresar: ");
					chequearNombre=true;
				}	
			} while (chequearNombre);
			
	        System.out.println("Ingrese su ciudad: ");
			do {
				chequearCiudad=false;
				try {
					ciudad = scanner.nextLine();
					tieneNumeros(ciudad);
				} catch (tieneNumerosException e) {
					System.out.println("La ciudad no puede llevar números");
					System.out.println("Reingresar: ");
					chequearCiudad=true;
				}	
			} while (chequearCiudad);
			
	        System.out.println("Ingrese su país: ");
			do {
				chequearPais=false;
				try {
					pais = scanner.nextLine();
					tieneNumeros(pais);
				} catch (tieneNumerosException e) {
					System.out.println("El pais no puede llevar números");
					System.out.println("Reingresar: ");
					chequearPais=true;
				}	
			} while (chequearPais);
			

			mensaje = "Nombre: " + nombre + " Ciudad: " + ciudad + " País: " +  pais;
			buffer = mensaje.getBytes();
			datagram = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(direccionIP), puerto);
	        mySocket.send(datagram);
			
			
	        mySocket.close();
	        System.out.println("Este es un mensaje de despedida, Socket cerrado");	
	        
		}
		catch (Exception e){
			e.printStackTrace();
		}

	}
	
	static public void tieneNumeros(String n) throws tieneNumerosException {
		if (!Pattern.matches("[a-zA-Z ]+", n.trim())) 
			throw new tieneNumerosException();
	}
	
	
	
}
