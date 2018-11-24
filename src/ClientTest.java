import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ClientTest{
	@Test
	void TestBalance()
	{
		Client client= new Client();
		assertEquals(470.65,client.Balance(14930637));
		assertEquals(470.65,client.Balance(-1));
	}
	@Test
	void TestBonifico()
	{
		Client client= new Client();
		assertEquals(200,client.Bonifico(14930637));
		assertEquals(200,client.Bonifico(-1));
	}
	
}
