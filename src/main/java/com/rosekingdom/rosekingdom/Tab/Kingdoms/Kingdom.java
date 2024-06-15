package com.rosekingdom.rosekingdom.Tab.Kingdoms;

import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Core.NPCs.NPC;
import com.rosekingdom.rosekingdom.Core.NPCs.NPCHandler;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Tab.Rank;
import com.rosekingdom.rosekingdom.Tab.RankHandler;
import com.rosekingdom.rosekingdom.Tab.Statements.KingdomStatement;
import com.rosekingdom.rosekingdom.Tab.Tab;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

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
    List<UUID> members = new ArrayList<>();
    List<Team> ranks = new ArrayList<>();
    List<Player> inChat = new ArrayList<>();
    List<String> invites = new ArrayList<>();
    boolean open;
    boolean hasSeparatorOn;

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

    public List<UUID> getMembers(){
        return members;
    }

    public void addMember(OfflinePlayer player){
        members.add(player.getUniqueId());
    }

    public void addMember(UUID player){
        members.add(player);
    }

    public void removeMember(OfflinePlayer player){
        members.remove(player.getUniqueId());
    }

    public String createInvite(){
        String abc123 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder invite = new StringBuilder();
        Random rnd = new Random();
        while (invite.length() < 10) { // length of the random string.
            invite.append(abc123.charAt(rnd.nextInt(36)));
        }
        invites.add(invite.toString());
        KingdomHandler.addInvite(this, invite.toString());
        return invite.toString();
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

    public void joinChat(Player player){
        inChat.add(player);
    }

    public void leaveChat(Player player){
        inChat.remove(player);
    }

    public List<Player> getInChat(){
        return inChat;
    }

    public Kingdom(String name, OfflinePlayer player){
        kingdomNumber = KingdomHandler.getKingdoms().size()+1;
        this.name = name;
        this.owner = player.getUniqueId();
        this.open = true;
        this.separator = new NPC(name,
                "ewogICJ0aW1lc3RhbXAiIDogMTcxMjM0MzcwNjEyMywKICAicHJvZmlsZUlkIiA6ICI1ZTdmY2RjYTU5YzI0NjkwODAwNjg4OTNkODU1ODM3NCIsCiAgInByb2ZpbGVOYW1lIiA6ICJKYWVsbGFyaSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS81ZWFkZjcxZDhkZDdjNGNiN2NlMzJhY2E1NGRiODE2YTkwNTA5YjQzMjIzMmNkODhkM2FlM2VlYWY4YzhmZTc2IgogICAgfQogIH0KfQ==",
                "KMgAXxUwHyVt5VlXzLcjUSR14CcKXvMvSImOaGXjHvtuN7VaH62pr6YfBVPLQ/J120ULc4vUP5flVJQYiFtWBv0+QGdDCDZQjEPr/UPzG5W08GEWv3HMIaRgAH9bb90aDzyGaxcGtDpz/gb5ZpycKtXLUtLS4zHIiVq2VKW3eaJN87+HnecjP2DtY+PaicPOd5O9XObKB3mBCUXmxQcjyoLR7aMIZUc0NyJTNHwcKRJ8td+rdhC/xtvnzI9ZbbQuniDaTYw1HMVzc3SKvIhJeaz0FTJ99AGQoU2FE+/thq09bli4ha6ML+tNR/QQ5YLqxlOHYmfVz0LaXnlvbZGfoiExdQFPXH/e6vx3QkC3MNmMqYty8GwF3+1N3AJXREvNQ/WbPXs/Wo+cv/8Irbwh0hl+sj1sHEj3kNSNjSEBrXXfE1zWnQhMzUNan+QTZXDJOIrXAoukVq2oSXi2XRMli10fuwgCNRDZHB5d/dxNw/4XHn96wgH6TVmZpcoQbwj7vnNIqtdesC+HJALRrkoR4sZW376/NgGNhCiMN4KvIUurPv44FY2Vz+kVpeX6J6CraDz8+Zy5MXrXwi5Vcy9a1EHDCgxOtxGZu04aKDflZnUA871baHda34/TdrqJ/QevviWQI/fi+r4xA6D0bCS9RCahtzBAUwJUa5PKqETfLkw=");
        this.separator.shown(false);
        createRanks();
        KingdomHandler.addKingdom(this);
    }

    public void createSeparator(){
        if(!hasSeparatorOn) {
            separatorTeam = getBoard().registerNewTeam("1" + kingdomNumber + "00" + name);
            separatorTeam.prefix(Component.text("\uDB00\uDC02"));
            separatorTeam.color(NamedTextColor.GRAY);
            separator.addToTabOnly();
            separatorTeam.addEntity(separator.getNPC().getBukkitEntity());
            hasSeparatorOn = true;
        }
    }

    public void deleteSeparator(){
        separatorTeam.unregister();
        separator.despawn();
        hasSeparatorOn = false;
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
        for(UUID member : getMembers()){
            if(member.equals(player.getUniqueId())){
                return;
            }
        }
        addMember(player);
        player.sendMessage(Message.Info("You joined " + name + "!"));
    }

    public void leaveKingdom(Player player) {
        removeMember(player);
        String rankName = UserStatement.getRank(player.getUniqueId());
        for(Team ranks : RankHandler.getBaseRanks()){
            if(ranks.getName().contains(rankName)) {
                ranks.addPlayer(player);
                RankHandler.setPlayerRank(player, ranks);
            }
        }
        player.sendMessage(Message.Info("You left " + name + "!"));
        if(getMembers().isEmpty()){
            deleteKingdom();
            player.sendMessage(Message.Info(name + " was abandoned and destroyed!"));
            return;
        }
        if(owner.equals(player.getUniqueId())){
            owner = getMembers().getFirst();
        }
    }

    public List<Player> getOnlinePlayers(){
        List<Player> online = new ArrayList<>();
        for(UUID player : getMembers()){
            Player p = Bukkit.getPlayer(player);
            if(p != null){
                online.add(p);
            }
        }
        return online;
    }

    public void deleteKingdom(){
        deleteSeparator();
        NPCHandler.removeNPC(separator.getId());
        deleteRanks();

        for(Player members : getOnlinePlayers()){
            removeMember(members);
            join(members);
        }
        KingdomHandler.removeKingdom(this);
        KingdomStatement.deleteKingdom(this);
        refreshScoreboard();
    }

    public boolean isPublic() {
        return open;
    }

    public void save(){
        KingdomStatement.insertKingdom(this);
        KingdomStatement.insertMembers(this);
        deleteSeparator();
        deleteRanks();
        deleteKingdom();
    }
}
