package com.models;

import com.utils.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    public List<Player> getAllPlayers() {
        List<Player> list = new ArrayList<>();
        String query = "SELECT * FROM player";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Player(
                        rs.getInt("player_id"),
                        rs.getString("name"),
                        rs.getString("full_name"),
                        rs.getString("age"),
                        rs.getInt("index_id")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insertPlayer(String name, String fullName, String age, int indexId) {
        String query = "INSERT INTO player (name, full_name, age, index_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, fullName);
            ps.setString(3, age);
            ps.setInt(4, indexId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePlayer(int playerId) {
        String sql = "DELETE FROM player WHERE player_id = ?";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, playerId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePlayer(int playerId, String name, String fullName, String age, int indexId) {
        String sql = "UPDATE player SET name = ?, full_name = ?, age = ?, index_id = ? WHERE player_id = ?";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, fullName);
            ps.setString(3, age);
            ps.setInt(4, indexId);
            ps.setInt(5, playerId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Player getPlayerById(int playerId) {
        String sql = "SELECT * FROM player WHERE player_id = ?";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, playerId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Player(
                        rs.getInt("player_id"),
                        rs.getString("name"),
                        rs.getString("full_name"),
                        rs.getString("age"),
                        rs.getInt("index_id")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Indexer getIndexerById(int indexId) {
        String sql = "SELECT * FROM indexer WHERE index_id = ?";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, indexId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Indexer(
                        rs.getInt("index_id"),
                        rs.getString("name"),
                        rs.getFloat("value_min"),
                        rs.getFloat("value_max")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String evaluatePlayer(Player player) {
        Indexer indexer = getIndexerById(player.getIndexId());
        if (indexer == null) return "Chưa có định mức";

        try {
            double playerValue = Double.parseDouble(player.getAge());
            if (playerValue >= indexer.getValueMin() && playerValue <= indexer.getValueMax()) {
                return "Đạt chuẩn (" + indexer.getName() + ")";
            } else {
                return "Không đạt (" + indexer.getName() + ")";
            }
        } catch (Exception e) {
            return "Lỗi định dạng số";
        }
    }
}