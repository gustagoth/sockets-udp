
import java.net.*;
import java.util.Scanner;

import exceptions.tieneNumerosException;

import java.io.*;

public class Server_UDP_Simplex {

	public static void main(String[] args) {
		int puertoEscucha = 0;
		Scanner scanner = new Scanner(System.in);
		boolean chequearPuerto;

		System.out.println("Ingrese puerto a utilizar: ");
		do {
			chequearPuerto=false;
			try {
				puertoEscucha = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("El puerto debe contener solo números");
				System.out.println("Reingresar: ");
				chequearPuerto=true;
			}	
		} while (chequearPuerto);
		
		
		
		
		try {
			DatagramSocket socketUDP = new DatagramSocket(puertoEscucha);
			System.out.println("Esperando recibir un segmento UDP...");			
			
			byte[ ] buffer = new byte[100];
	        DatagramPacket datagrama = new DatagramPacket(buffer, 100);
	        socketUDP.receive(datagrama);
	        String message = new String(datagrama.getData(),0,datagrama.getLength());
	        
	        System.out.println("Se recibió un nuevo segmento UDP proveniente de la IP "+datagrama.getAddress().getHostAddress()+" en el puerto remoto "+datagrama.getPort());
	        System.out.println("El mensaje recibido es: ");
	        System.out.println(message);
	        
	        buffer = "Ingrese ahora nombre, ciudad y país de residencia".getBytes();
	        
	        InetAddress address = datagrama.getAddress();
	        int puerto = datagrama.getPort();
	        
	        datagrama = new DatagramPacket(buffer, buffer.length, address, puerto);
	        socketUDP.send(datagrama);
	        
	        buffer = new byte[100];
	        datagrama = new DatagramPacket(buffer, 100);
	        socketUDP.receive(datagrama);
	        message = new String(datagrama.getData(),0,datagrama.getLength());
	        
	        System.out.println("Se recibió un nuevo segmento UDP proveniente de la IP "+datagrama.getAddress().getHostAddress()+" en el puerto remoto "+datagrama.getPort());
	        System.out.println("El mensaje recibido es: ");
	        System.out.println(message);
	        
	        
	        socketUDP.close();
			System.out.println("Este es un mensaje de despedida, Socket cerrado");			
			
		} 
		catch (BindException e){
			System.out.println("Puerto ya en uso!");
		}
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
