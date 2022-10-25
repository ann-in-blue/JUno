package model;

/**
 * classe che rappresenta un giocatore.
 * DAti: nickname, avatar, partite giocate, vinte e perse, livello.
 * @author a-00
 *
 */
public class Player 
{	
	private String nickname;
	private String avatar; 	//rappresenta il path dell'immagine profilo del giocatore
	
	private int partiteGiocate;
	private int partitePerse;
	private int partiteVinte;
	
	/**
	 * Il livello di un giocatore può essere "Base", "Intermedio" o "Avanzato" in base al rapporto tra partite vinte e partite totali:
	 * - sotto al 50% di partite vinte è base
	 * - tra il 50% e il 75% è intermedio
	 * - sopra il 75% è avanzato.
	 */
	private String livello;

	public Player(String nickname, String avatar) {
		this.nickname = nickname;
		this.avatar = avatar;
		
		this.livello = "Base";
		
		
	}

	/**
	 * Costruttore della classe che prende in input una stringa che contiene tutti i dati. Utile quando le informazioni sono recuperate dal file.
	 * @param nickname
	 * @param avatar
	 * @param partiteGiocate
	 * @param partitePerse
	 * @param partiteVinte
	 * @param livello
	 */
	 public Player(String string) {
		 
		String[] p = string.split(",");
		 
		this.nickname = p[0];
		this.avatar = p[1];
		this.partiteGiocate = Integer.parseInt(p[2]);
		this.partitePerse = Integer.parseInt(p[3]);
		this.partiteVinte = Integer.parseInt(p[4]);
		this.livello = p[5];
	}


	//GETTERS E SETTERS dei vari campi
	 
	public String getNickname() {
		return nickname;
	}

//	public void setNickname(String nickname) {
//		this.nickname = nickname;
//	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getPartiteGiocate() {
		return partiteGiocate;
	}

	/**
	 * Metodo per incrementare il numero di partite giocate.
	 */
	public void addPartitaGiocata() {
		partiteGiocate++;
	}

	public int getPartitePerse() {
		return partitePerse;
	}

	public void addPartitaPersa() {
		partitePerse++;
	}

	public int getPartiteVinte() {
		return partiteVinte;
	}

	public void addPartitaVinta() {
		partiteVinte++;
	}

	public String getLivello() {
		return livello;
	}

	public void setLivello() 
	{
		double rapportoPartite = partiteVinte / partiteGiocate;
		if(rapportoPartite <= 0.50)
			livello = "Base";
		else if(rapportoPartite > 0.50 && rapportoPartite <= 0.75)
			livello = "Intermedio";
		else
			livello = "Avanzato";
		
	}
	
	@Override
	public String toString()
	{
		return nickname + ","+avatar+","+partiteGiocate+","+partiteVinte+","+partitePerse+","+livello;
	}

	
}
