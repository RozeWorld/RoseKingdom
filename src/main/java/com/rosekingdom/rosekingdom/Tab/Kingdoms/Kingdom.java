package com.rosekingdom.rosekingdom.Tab.Kingdoms;

import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Core.NPCs.NPC;
import com.rosekingdom.rosekingdom.Core.NPCs.NPCHandler;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Tab.Rank;
import com.rosekingdom.rosekingdom.Tab.RankHandler;
import com.rosekingdom.rosekingdom.Tab.Tab;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Kingdom extends Tab {
    int kingdomNumber;
    String name;
    UUID owner;
    Team separatorTeam;
    NPC separator;
    List<Player> members = new ArrayList<>();
    List<Team> ranks = new ArrayList<>();
    boolean open;
    List<String> invites = new ArrayList<>();

    public int getKingdomNumber(){
        return kingdomNumber;
    }

    public void setKingdomNumber(int number) {
        kingdomNumber = number;
    }

    public UUID getOwner(){
        return owner;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void hideSeparator() {
        separator.despawn();
    }

    public void showSeparator() {
        separator.addToTabOnly();
    }

    public List<Player> getMembers(){
        return members;
    }

    public void addMember(Player player){
        members.add(player);
    }

    public void removeMember(Player player){
        members.remove(player);
    }

    public String createInvite(){
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, StandardCharsets.UTF_8);
        invites.add(generatedString);
        return generatedString;
    }

    public List<String> getInvites(){
        return invites;
    }

    public List<Team> getRanks(){
        return ranks;
    }

    public void addRank(Team rank){
        ranks.add(rank);
    }

    public void removeRank(Team rank){
        ranks.remove(rank);
    }

    public Kingdom(String name, Player player){
        kingdomNumber = KingdomHandler.getKingdoms().size()+1;
        this.name = name;
        this.owner = player.getUniqueId();
        this.open = true;
        createSeparator();
        createRanks();
        KingdomHandler.addKingdom(this);
    }

    public void createSeparator(){
        separatorTeam = getBoard().registerNewTeam("1" + kingdomNumber + "00" + name);
        separatorTeam.prefix(Component.text("\uDB00\uDC02"));
        separatorTeam.color(NamedTextColor.GRAY);
        separator = new NPC(name,
                "ewogICJ0aW1lc3RhbXAiIDogMTcxMjM0MzcwNjEyMywKICAicHJvZmlsZUlkIiA6ICI1ZTdmY2RjYTU5YzI0NjkwODAwNjg4OTNkODU1ODM3NCIsCiAgInByb2ZpbGVOYW1lIiA6ICJKYWVsbGFyaSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS81ZWFkZjcxZDhkZDdjNGNiN2NlMzJhY2E1NGRiODE2YTkwNTA5YjQzMjIzMmNkODhkM2FlM2VlYWY4YzhmZTc2IgogICAgfQogIH0KfQ==",
                "KMgAXxUwHyVt5VlXzLcjUSR14CcKXvMvSImOaGXjHvtuN7VaH62pr6YfBVPLQ/J120ULc4vUP5flVJQYiFtWBv0+QGdDCDZQjEPr/UPzG5W08GEWv3HMIaRgAH9bb90aDzyGaxcGtDpz/gb5ZpycKtXLUtLS4zHIiVq2VKW3eaJN87+HnecjP2DtY+PaicPOd5O9XObKB3mBCUXmxQcjyoLR7aMIZUc0NyJTNHwcKRJ8td+rdhC/xtvnzI9ZbbQuniDaTYw1HMVzc3SKvIhJeaz0FTJ99AGQoU2FE+/thq09bli4ha6ML+tNR/QQ5YLqxlOHYmfVz0LaXnlvbZGfoiExdQFPXH/e6vx3QkC3MNmMqYty8GwF3+1N3AJXREvNQ/WbPXs/Wo+cv/8Irbwh0hl+sj1sHEj3kNSNjSEBrXXfE1zWnQhMzUNan+QTZXDJOIrXAoukVq2oSXi2XRMli10fuwgCNRDZHB5d/dxNw/4XHn96wgH6TVmZpcoQbwj7vnNIqtdesC+HJALRrkoR4sZW376/NgGNhCiMN4KvIUurPv44FY2Vz+kVpeX6J6CraDz8+Zy5MXrXwi5Vcy9a1EHDCgxOtxGZu04aKDflZnUA871baHda34/TdrqJ/QevviWQI/fi+r4xA6D0bCS9RCahtzBAUwJUa5PKqETfLkw=");
        separator.addToTabOnly();
        separatorTeam.addEntity(separator.getNPC().getBukkitEntity());
    }

    public void deleteSeparator(){
        separatorTeam.unregister();
        hideSeparator();
    }

    public void createRanks(){
        for(Rank ranks : Rank.values()){
            Team rank = getBoard().registerNewTeam("1" + kingdomNumber + "0" + RankHandler.getRankNumber(ranks) + ranks.name());
            rank.prefix(Component.text(ranks.prefix));
            addRank(rank);
        }
    }

    public void deleteRanks(){
        for(Team ranks : ranks){
            ranks.unregister();
        }
        ranks = new ArrayList<>();
    }


    public void joinKingdom(Player player){
        String playerRankName = UserStatement.getRank(player.getUniqueId());
        String rankName = "1" + kingdomNumber + "0" + RankHandler.getRankNumber(playerRankName) + playerRankName;
        for(Team rank : getRanks()){
            if(rank.getName().contains(rankName)){
                rank.addPlayer(player);
                RankHandler.setPlayerRank(player, rank);
            }
        }
        if (!members.contains(player)){
            addMember(player);
            player.sendMessage(Message.Info("You joined " + name + "!"));
        }
    }

    public void leaveKingdom(Player player) {
        String rankName = UserStatement.getRank(player.getUniqueId());
        for(Team ranks : RankHandler.getBaseRanks()){
            if(ranks.getName().contains(rankName)) {
                removeMember(player);
                ranks.addPlayer(player);
                RankHandler.setPlayerRank(player, ranks);
                if(getMembers().isEmpty()){
                    deleteKingdom();
                }
            }
        }
    }

    public List<Player> getOnlinePlayers(){
        List<Player> online = new ArrayList<>();
        for(Player player : getMembers()){
            if(player.isOnline()){
                online.add(player);
            }
        }
        return online;
    }

    public void deleteKingdom(){
        NPCHandler.removeNPC(separator.getId());
        separatorTeam.unregister();
        deleteRanks();

        for(Player members : getOnlinePlayers()){
            String rankName = UserStatement.getRank(members.getUniqueId());
            for(Team ranks : RankHandler.getBaseRanks()){
                if(ranks.getName().contains(rankName)) {
                    removeMember(members);
                    ranks.addPlayer(members);
                    RankHandler.setPlayerRank(members, ranks);
                }
            }
        }
        KingdomHandler.removeKingdom(this);
        refreshScoreboard();
    }

    public boolean isPublic() {
        return open;
    }
}
