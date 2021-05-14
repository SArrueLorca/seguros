import java.util.ArrayList;
import java.util.Scanner;

class main{
   public static void main(String args[]){
     //create array of employee object  

    ArrayList<Seguro> list = new ArrayList<Seguro>();
    ArrayList<Seguro> seguros_vendidos = new ArrayList<Seguro>();
    Scanner sc = new Scanner(System.in);
    Scanner b = new Scanner(System.in);
    
    int i=0;
    int j=0;
    int dias;
    String respuesta;
    
    while(true) {
	     System.out.print("----Ingreso de productos. Escriba 'salir' para dejar de agregarlos, presione otra tecla para continuar ----\n");
	   	 respuesta = sc.nextLine();
	     if(respuesta.equals("salir")){
	            break;
	     }
	     agregar_elementos(list);
        
    }   
    
	System.out.print("Ingrese días a simular\n");
	dias = b.nextInt();
    

     //list.add( new Seguro(12,12,"Full Cobertura") );
     //list.add( new Seguro(7,180,"Mega cobertura") );
     //list.add( new Seguro(15,50,"Full cobertura Super duper") );
     //list.add( new Seguro(7,12,"Super avance") );
     
     while(i<=dias && list.size()!=0) {
    	 System.out.println("-------- Dia: "+i+" --------");
    	 j=0;
    	 System.out.print("nombre, sellIn, price\n");
 
         while(j<list.size()) {
        	 list.get(j).cambia_precios(i);
          	 j=j+1;
           }
         i=i+1;
         
         System.out.print("----Ha finalizado un día----\n");

         while(true) {
        	 System.out.print("------MENU------\n");
             System.out.print("Ingrese los productos vendidos que desea retirar\n");
             System.out.print("Para ingresar un nuevo producto. Escriba 'agregar'\n");
             System.out.print("Para ver seguros ya vendidos. Escriba 'vendidos'\n");
             System.out.print("Escriba 'salir' para hacer transcurrir un dia\n");
        	 respuesta = sc.nextLine();
             if(respuesta.equals("salir") ){
                 break;
             }
             else if (respuesta.equals("agregar") ){
            	 agregar_elementos(list);
             }
             else if (respuesta.equals("vendidos") ){
            	 
            	 if(seguros_vendidos.size()!=0) {
            		 j=0;
                	 System.out.print("**Seguros vendidos**\n");
    	             while(j<seguros_vendidos.size()) {
    	            	  seguros_vendidos.get(j).muestra_info();
    	                  j=j+1;
    	             }  
            	 }else {
            		 System.out.print("No se han vendido seguros\n");
            	 }
  	 
             }
             
             else {
            
	        	 //Buscar y eliminar objeto
	             j=0;
	             while(j<list.size()) {
	                 if(list.get(j).name.equals(respuesta)) {
	                	seguros_vendidos.add( new Seguro(
	                			list.get(j).getDias(),
	                			list.get(j).getPrice(),
	                			list.get(j).getName()
	  
	                	 ));
	                 	list.remove(j);
	                 }
	                 	
	                  j=j+1;
	             }
	             if(j>list.size()&&list.size()!=0)
	            	 System.out.print("No se encontró elemento\n");
             }
         }
    	 
     }
     System.out.print("Fin de la simulación\n");
    
  }//Fin función de trabajo main
   
  static public void agregar_elementos(ArrayList<Seguro> list){
	   String nombre;
	   int precio;
	   int dias;
	   Scanner sc = new Scanner(System.in);
	   System.out.print("Ingrese nombre producto\n");
	   nombre = sc.nextLine();
	   System.out.print("Ingrese precio asociado\n");
	   precio = sc.nextInt();
	   System.out.print("Ingrese dias de venta\n");
	   dias = sc.nextInt();
	   list.add( new Seguro(dias,precio,nombre.toLowerCase()) );
  }

   static class Seguro{
	  int sellIn;
	  int valor_price;
	  String name;
	  int razon;
	  //Employee class constructor
	  Seguro( 
		  int dias,
		  int precio,
		  String nombre
	  ){
		 sellIn = dias;
		 valor_price = precio;
		 name = nombre;
		 
	     switch(nombre.toLowerCase()){
	       case "full cobertura":
	          razon=precio/dias;
	          break;
	       case "mega cobertura":
	          razon=0;
	          break;
	       case "full cobertura super duper":
	    	   razon=precio/dias;
	          break;
	       case "super avance":
	    	   razon=-2*precio/dias;
	          break;
	       default:
	          razon=precio/dias;
	      }
	  }

	public void cambia_precios(int contador){
		
		if(contador!=0) {
			if( (name.equals("full cobertura super duper") || name.equals("full cobertura") )) {
				
				if(sellIn==10 && valor_price*2<=100) {
					valor_price=valor_price*2;
					
				}else if(sellIn==10 && valor_price>50){	
					valor_price=100;
				//			
				}else if(sellIn==5 && valor_price>33){	
					valor_price=100;
				//				
				}else if(sellIn==5 && valor_price*3<=100){	
					valor_price=valor_price*3;
				}else if(sellIn==0) {
					valor_price=0;
				}else if(valor_price+razon<=100 && valor_price+razon>0) {
					valor_price=valor_price+razon;
				}
				
			}else if(name.equals("super avance")) {
				
				if(valor_price+2*razon>=0) {
					valor_price=valor_price+2*razon;
				}else {
					valor_price=0;
				}
				
			}else {
	
				if(valor_price-razon>=0) {
					valor_price=valor_price-razon;
				}
				if(!name.equals("Mega cobertura") && sellIn==0) {
					if(valor_price-2*razon>=0) {
						valor_price=valor_price-2*razon;
					}else {
						valor_price=0;
					}
				}
				
			}
		}
		System.out.print(name+ ", " +sellIn+ ", "+valor_price);
		System.out.println(); 
		if(sellIn>0) 
			sellIn=sellIn-1;
	}//Funcion Cambia precio
	
	public void muestra_info(){
		System.out.print("Producto vendido: "+name+ ", Dias restantes: " +sellIn+ ", Precio: "+valor_price+"\n");
	}
	
	public int getDias() { 
		return sellIn;
	}		  
	public int getPrice() { 
		return valor_price;
	}	
	public String getName() { 
		return name;
	}	


  }//SEGURO
} //MAIN