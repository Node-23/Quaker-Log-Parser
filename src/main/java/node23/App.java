package node23;

import java.util.ArrayList;

import node23.Model.Game;
import node23.Services.SetGames;
import node23.View.MainView;

public class App 
{
    public static void main( String[] args )
    {
        ArrayList<Game> games = SetGames.instantiateGames();
        new MainView(games);
    }
}
