package Controller.Pokemon_Viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Model.Pokemon_Viewer.ClientApp;
import Model.Pokemon_Viewer.Filter;
import Model.Pokemon_Viewer.Pokemon;
import Model.Pokemon_Viewer.PokemonDetails;

public class PokemonController implements ActionListener {
	ClientApp clApp;
	public List<Pokemon> pokeList = new ArrayList<Pokemon>();
	public List<PokemonDetails> pokeDeList = new ArrayList<PokemonDetails>();
	public List<Filter> pokemonHabitat = new ArrayList<Filter>();

	// public PokemonDetails[] pokeDeArr = new PokemonDetails[5] ;
	public int total = 20;
	public int index = 0;
	public int max = 0;
	public int totalPoke = 0;
	public int rows=10;
	
	public int cols=4;

	public PokemonController(ClientApp clApp) {
		this.clApp = clApp;
		// clApp.fetch(total);
		pokeList = clApp.getPokeList();
		try {
			clApp.fetchPokemonHab();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pokemonHabitat=clApp.getHabList();
	}

	public void loadList() throws IOException, InterruptedException {
		pokeList = clApp.getPokeList();
		totalPoke = clApp.fetch(total);
		max=0;
		index=0;
		total=20;
		//rows=(total/cols);
		// cols=4;
		pokeList = clApp.getPokeList();

		max = pokeList.size();
		// index=1;

	}
	public void loadHabPokeList(String filter) throws IOException, InterruptedException {
		pokeList = clApp.getPokeList();
		totalPoke = clApp.fetch(filter);
		pokeList = clApp.getPokeList();
		max=totalPoke;
		index=0;
		total=20;
		
		
		
		max = pokeList.size();
		// index=1;
		System.out.print("done");

	}
	public void find(Object t) throws IOException, InterruptedException {
		
		for(int i =0; i<pokemonHabitat.size();i++) {
			System.out.println("loop "+i);
			if(t.equals(pokemonHabitat.get(i).name)) {
				loadHabPokeList(pokemonHabitat.get(i).url);
				System.out.println("found");
				break;

				
				
			}
		}
		
	}
	public PokemonDetails getPokemon(Pokemon poke) throws IOException, InterruptedException {
		for (int i = 0; i < pokeDeList.size(); i++) {
			if (pokeDeList.get(i).name == poke.name) {
				int pos = i;
				PokemonDetails pd = pokeDeList.get(pos);
				for (int x = 1; i < pokeDeList.size(); x++) {

					pokeDeList.set(pokeDeList.size() - x, pokeDeList.get(pokeDeList.size() - 1 - x));
				}
				pokeDeList.set(0, pd);
				return pd;
			}
		}
		PokemonDetails pd = clApp.getPokemon(poke);
		if (pokeDeList.size() < 5 && !pokeDeList.contains(pd)) {
			if (pokeDeList.size() == 0) {
				pokeDeList.add(pd);
			} else {
				pokeDeList.add(pokeDeList.get(pokeDeList.size() - 1));
				for (int i = 1; i < pokeDeList.size(); i++) {

					pokeDeList.set(pokeDeList.size() - i, pokeDeList.get(pokeDeList.size() - 1 - i));
				}
				pokeDeList.set(0, pd);
			}
		} // pokeDeList.indexOf(pd)
		else if (!pokeDeList.contains(pd)) {
			for (int i = 1; i < pokeDeList.size(); i++) {

				pokeDeList.set(pokeDeList.size() - i, pokeDeList.get(pokeDeList.size() - 1 - i));
			}
			pokeDeList.set(0, pd);

		}

		else {
			int pos = pokeDeList.indexOf(pd);
			if (pos != 0) {
				for (int i = 1; i < pos + 1; i++) {

					pokeDeList.set(pos + 1 - i, pokeDeList.get(pos - 1 - i));
				}
				pokeDeList.set(0, pd);
			}

		}
		return pd;

	}

	public PokemonDetails getPokemon2(Pokemon poke) throws IOException, InterruptedException {

		PokemonDetails pd = clApp.getPokemon(poke);
		return pd;

	}

	public void getNextPokeList(boolean shift) throws IOException, InterruptedException {
		System.out.println(max);
		if (shift) {
			if (index + (totalPoke % 20) != max - 1) {
				if (index < max - total) // stops when diff is exactly 20
				{
					index = index + total;
				}
				// else if(index==max-12) {}
				else {
					if (max + totalPoke % 20 >= totalPoke)// checks if diff to end is greater then 20
					{
						total = totalPoke % 20;// changes to 12 if at end
					}

					index = max;// default get next
					clApp.getNext(total, index + 1);
					pokeList = clApp.getPokeList();
					max = pokeList.size();
				}
			}
		}

		else if (index >= total) {
			if (index == max - totalPoke % 20) {
				total = 20;
			}
			index = index - total;

		}

		// return pokeList;
	}

	public List<Pokemon> getPokeList() {
		return pokeList;
	}

	public int getTotal() {
		return total;
	}

	public int getIndex() {
		return index;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
