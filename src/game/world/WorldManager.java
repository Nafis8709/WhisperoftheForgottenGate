package game.world;


import java.util.HashMap;
import java.util.Map;
import game.world.rooms.*;

public class WorldManager {
    private Map<String, Room> rooms;
    private Room currentRoom;

    public WorldManager() {
        this.rooms = new HashMap<>();
        initializeRooms();
    }
    private void initializeRooms() {

        // Act I Rooms
        rooms.put("dark_alley", new DarkAlley());
        rooms.put("town_square", new TownSquare());
        rooms.put("shop", new Shop());
        rooms.put("shop_basement", new ShopBasement());

        // Act II Rooms
        rooms.put("forest_entrance", new ForestEntrance());
        rooms.put("forest_path", new ForestPath());
        rooms.put("forest_clearing", new ForestClearing());

        // Act III Rooms
        rooms.put("forest_shrine", new ForestShrine());
        rooms.put("ruined_glade", new RuinedGlade());

        // Act IV Rooms
        rooms.put("inn", new Inn());
        rooms.put("inn_archives", new InnArchives());
        rooms.put("forgotten_tunnels", new ForgottenTunnels());
        rooms.put("town_center_back", new TownCenterBack());

        // Act V Rooms
        rooms.put("catacombs_entrance", new CatacombsEntrance());
        rooms.put("catacombs_depths", new CatacombsDepths());
        rooms.put("original_gate", new OriginalGate());

        setupRoomConnections();

    }

   // private void setupRoomConnections() {
     //   rooms.get("dark_alley").addExit("mysterious_door", rooms.get("town_square"));
       // rooms.get("town_square").addExit("shop", rooms.get("shop"));
        //rooms.get("town_square").addExit("forest", rooms.get("forest_entrance"));
        //rooms.get("shop").addExit("basement", rooms.get("shop_basement"));
        //rooms.get("shop").addExit("town_square", rooms.get("town_square"));
    //}

    private void setupRoomConnections() {
        // Dark Alley -> Town Square (requires key)
        rooms.get("town_square").addExit("back", rooms.get("dark_alley"));

        // Town Square connections
        rooms.get("town_square").addExit("shop", rooms.get("shop"));
        rooms.get("town_square").addExit("store", rooms.get("shop"));
        rooms.get("town_square").addExit("forest", rooms.get("forest_entrance"));
        rooms.get("town_square").addExit("inn", rooms.get("inn"));
        rooms.get("town_square").addExit("behind", rooms.get("town_center_back"));
        rooms.get("town_square").addExit("center", rooms.get("town_center_back"));

        // Shop connections
        rooms.get("shop").addExit("town", rooms.get("town_square"));
        rooms.get("shop").addExit("square", rooms.get("town_square"));
        rooms.get("shop").addExit("back", rooms.get("town_square"));
        rooms.get("shop").addExit("basement", rooms.get("shop_basement"));
        rooms.get("shop").addExit("down", rooms.get("shop_basement"));

        // Basement connections
        rooms.get("shop_basement").addExit("up", rooms.get("shop"));
        rooms.get("shop_basement").addExit("shop", rooms.get("shop"));

        // Forest connections
        rooms.get("forest_entrance").addExit("town", rooms.get("town_square"));
        rooms.get("forest_entrance").addExit("back", rooms.get("town_square"));
        rooms.get("forest_entrance").addExit("deeper", rooms.get("forest_path"));
        rooms.get("forest_entrance").addExit("path", rooms.get("forest_path"));

        rooms.get("forest_path").addExit("back", rooms.get("forest_entrance"));
        rooms.get("forest_path").addExit("clearing", rooms.get("forest_clearing"));
        rooms.get("forest_path").addExit("shrine", rooms.get("forest_shrine"));

        rooms.get("forest_clearing").addExit("back", rooms.get("forest_path"));
        rooms.get("forest_clearing").addExit("path", rooms.get("forest_path"));
        rooms.get("forest_clearing").addExit("glade", rooms.get("ruined_glade"));

        rooms.get("forest_shrine").addExit("back", rooms.get("forest_path"));
        rooms.get("forest_shrine").addExit("glade", rooms.get("ruined_glade"));

        rooms.get("ruined_glade").addExit("shrine", rooms.get("forest_shrine"));
        rooms.get("ruined_glade").addExit("clearing", rooms.get("forest_clearing"));

        // Inn connections (Act IV)
        rooms.get("inn").addExit("town", rooms.get("town_square"));
        rooms.get("inn").addExit("square", rooms.get("town_square"));
        rooms.get("inn").addExit("archives", rooms.get("inn_archives"));
        rooms.get("inn").addExit("basement", rooms.get("inn_archives"));

        rooms.get("inn_archives").addExit("up", rooms.get("inn"));
        rooms.get("inn_archives").addExit("inn", rooms.get("inn"));
        rooms.get("inn_archives").addExit("tunnels", rooms.get("forgotten_tunnels"));

        // Town Center Back and Tunnels (Act IV)
        rooms.get("town_center_back").addExit("square", rooms.get("town_square"));
        rooms.get("town_center_back").addExit("front", rooms.get("town_square"));
        rooms.get("town_center_back").addExit("tunnels", rooms.get("forgotten_tunnels"));

        rooms.get("forgotten_tunnels").addExit("inn", rooms.get("inn_archives"));
        rooms.get("forgotten_tunnels").addExit("center", rooms.get("town_center_back"));
        rooms.get("forgotten_tunnels").addExit("catacombs", rooms.get("catacombs_entrance"));

        // Catacombs connections (Act V)
        rooms.get("catacombs_entrance").addExit("tunnels", rooms.get("forgotten_tunnels"));
        rooms.get("catacombs_entrance").addExit("up", rooms.get("forgotten_tunnels"));
        rooms.get("catacombs_entrance").addExit("deeper", rooms.get("catacombs_depths"));
        rooms.get("catacombs_entrance").addExit("down", rooms.get("catacombs_depths"));

        rooms.get("catacombs_depths").addExit("up", rooms.get("catacombs_entrance"));
        rooms.get("catacombs_depths").addExit("back", rooms.get("catacombs_entrance"));
        rooms.get("catacombs_depths").addExit("gate", rooms.get("original_gate"));
        rooms.get("catacombs_depths").addExit("bottom", rooms.get("original_gate"));

        rooms.get("original_gate").addExit("back", rooms.get("catacombs_depths"));
        rooms.get("original_gate").addExit("up", rooms.get("catacombs_depths"));
    }


    public void loadWorld(){
        currentRoom = rooms.get("dark_alley");
    }
    public Room getCurrentRoom() {
        return currentRoom;
    }
    public void setCurrentRoom(String roomId) {

        if(rooms.containsKey(roomId)) {
            this.currentRoom = rooms.get(roomId);
        }
    }
    public Map<String, Room> getRooms() {
        return rooms;
    }

}
