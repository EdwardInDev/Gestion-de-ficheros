package es.ifp.programacion.uf3.practica.ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Programa que realiza operaciones de escritura y lectura
 * en un fichero de texto
 * 
 * Se muestra un menú con las diferentes opciones:
 * 
 * Crear un fichero (A)
 * Escribir en un fichero (B)
 * Leer de un fichero (C)
 * Borrar un fichero (D)
 * Salir del programa (E)
 * 
 * 
 * Ruta relativa del fichero (sobre la carpeta del proyecto)
		
 * @author Edward
 *
 */

public class ProgramaPrincipal {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String opcion;
		do {
			opcionesMenu();
			opcion = sc.nextLine();
		
					if (opcion.equals("A")) {
						creacionFichero();
					}
					else 
						if (opcion.equals("B")) {
							escribirFichero();
						}
						else 
							if (opcion.equals("C")) {
								lecturaFichero();
							}
							else 
								if (opcion.equals("D")) {
									borrarFichero();
								}
								else
									if (opcion.equals("E")) {	
									}
									else
										System.out.println("Opción incorrecta");
			
		}while (!opcion.equals("E"));
		System.out.println("Saliendo del programa");
	}
	
	/**
	 * Crear un fichero
	 */
	public static void creacionFichero() {
		Scanner sc = new Scanner(System.in);
		File file = null;
		String ruta;
		boolean result=false;
		System.out.println("Introduzca el nombre para el fichero:");
		ruta = sc.nextLine();
		file = new File(ruta);
		try {
			if(!file.exists()) {
				result = file.createNewFile();
				if (result)
					System.out.println("Fichero creado correctamente");
				else
					System.out.println("El fichero no ha sido creado.");
			}
			else
				System.out.println("El fichero ya ha sido creado");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("El fichero no ha podido ser creado");
		}
		
		
	}
	
	/**
	 * Escribir en un fichero
	 */
	public static void escribirFichero() {
		String ruta;
		FileWriter fw = null;
		Scanner sc = new Scanner(System.in);
		String cadena;
		BufferedWriter bw = null;
		String opcion;
		String tmp;
		boolean tipo;
		File file = null;
		
		System.out.println("Opciones de escritura:");
		System.out.println("Sobreescribir (S):");
		System.out.println("Añadir al final (A):");
		System.out.println("Introduzca opción de escritura (por defecto se añade al final)");
		opcion = sc.nextLine();
		
		if (opcion.equals("A"))
			tipo = true;
		else
			if (opcion.equals("S"))
				tipo = false;
			else{
				System.out.println("Opcion incorrecta de escritura:");
				tipo = true;
			}
		
		System.out.println("Introduzca el fichero sobre el que quiere escribir:");
		ruta = sc.nextLine();
		
		System.out.println("Introduzca el texto a escribir en el fichero:");
		cadena = sc.nextLine();
		try {
			file = new File(ruta);
			if (file.exists()) {
				fw = new FileWriter(file, tipo);
				bw = new BufferedWriter(fw);
				bw.write(cadena);
				bw.close();
				fw.close();
			}
			else {
				System.out.println("El fichero no existe, ¿desea crearlo?: (Sí/No)");
				tmp = sc.nextLine();
				
				if (tmp.equals("Sí")) {
					file.createNewFile();
					fw = new FileWriter(file, tipo);
					bw = new BufferedWriter(fw);
					bw.write(cadena);
					bw.close();
					fw.close();
				} 
				else {
					System.out.println("No se realiza la escritura al no crear el fichero.");
				}
			}
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 *  Lectura de un fichero
	 */
	public static void lecturaFichero() {
		FileReader fr = null;
		BufferedReader br = null;
		String linea="";
		String totalLineas="";
		Scanner sc = new Scanner(System.in);
		String ruta;
		File file;
		
		System.out.println("Introduzca el nombre del fichero a leer:");
		ruta = sc.nextLine();
		
		try {
			
			file = new File(ruta);
			if (file.exists()) {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				
				
				linea = br.readLine();
				while (linea !=null) {
					totalLineas += "\n"+linea;
					linea = br.readLine();
					
				}
				
				br.close();
				fr.close();
				System.out.println("El contenido del fichero es: "+totalLineas);
			}
			else 
				System.out.println("El fichero no se puede leer porque no existe");
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Borrar un fichero
	 */
	public static void borrarFichero() {
		Scanner sc = new Scanner(System.in);
		File file = null;
		String ruta;
		boolean result=false;
		
		System.out.println("Introduzca el nombre del fichero a borrar:");
		ruta = sc.nextLine();
		file = new File(ruta);
		
		if (file.exists()) {
			result = file.delete();
			
			if (result)
				System.out.println("Fichero borrado correctamente");
			else
				System.out.println("El fichero no ha podido ser borrado");
		}
		else
			System.out.println("No se puede borrar el fichero ya que no existe");
		
	}
	
	/**
	 *opciones de menú del programa
	 */
	public static void opcionesMenu() {
		System.out.println("A) Crear un fichero");
		System.out.println("B) Escribir en un fichero");
		System.out.println("C) Leer de un fichero");
		System.out.println("D) Borrar un fichero");
		System.out.println("E) Salir del programa (S)");
		System.out.println("Introduzca opcion:");
	}

}