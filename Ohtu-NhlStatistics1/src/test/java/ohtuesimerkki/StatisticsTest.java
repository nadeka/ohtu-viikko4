package ohtuesimerkki;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StatisticsTest {
    Statistics stats;
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void searchingExistingPlayerReturnsPlayer() {
        Player player = stats.search("Semenko");

        assertEquals("Semenko", player.getName());
    }

    @Test
    public void searchingNonexistingPlayerReturnsNull() {
        Player player = stats.search("Tossavainen");

        assertEquals(null, player);
    }

    @Test
    public void searchingExistingTeamReturnsListOfRightSize() {
        List<Player> players = stats.team("EDM");

        assertEquals(3, players.size());
    }

    @Test
    public void searchingExistingTeamReturnsRightTeamMembers() {
        List<Player> players = stats.team("EDM");

        for ( Player p : players ) {
            assertEquals("EDM", p.getTeam());
        }
    }

    @Test
    public void searchingNonexistingTeamReturnsEmptyList() {
        List<Player> players = stats.team("MDE");

        assertEquals(0, players.size());
    }

    @Test
    public void topScorersReturnsListOfRightSize() {
        List<Player> players = stats.topScorers(3);

        assertEquals(3, players.size());
    }

    @Test
    public void topScorersReturnsPlayersInRightOrder() {
        List<Player> players = stats.topScorers(3);

        assertEquals("Gretzky", players.get(0).getName());
        assertEquals("Lemieux", players.get(1).getName());
        assertEquals("Yzerman", players.get(2).getName());
    }
}