
public class Prova {
	public static void main(String args[])
	{
		Client prova= new Client();
		double balance=prova.Balance(14930637);
		System.out.println("Saldo Disponibile: "+balance);
		int bon= prova.Bonifico(14930637);
		if(bon==200)
			System.out.println("bonifico effettuato con successo!");
		else
			System.out.println("bonifico non effettuato");
	}
}
