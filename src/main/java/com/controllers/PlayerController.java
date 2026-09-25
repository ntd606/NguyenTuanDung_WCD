package com.controllers;

import com.models.Player;
import com.models.PlayerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PlayerController", value = "/players")
public class PlayerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        PlayerDAO dao = new PlayerDAO();
        if (action != null) {
            if (action.equals("delete")) {
                int id = Integer.parseInt(request.getParameter("id"));
                dao.deletePlayer(id);
                response.sendRedirect("players");
                return;
            } else if (action.equals("edit")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Player p = dao.getPlayerById(id);
                request.setAttribute("player", p);
                request.setAttribute("isEdit", true);
            }
        }
        List<Player> list = dao.getAllPlayers();
        request.setAttribute("playerList", list);
        request.getRequestDispatcher("/player.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        PlayerDAO dao = new PlayerDAO();
        if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("playerId"));
            String name = request.getParameter("name");
            String fullName = request.getParameter("fullName");
            String age = request.getParameter("age");
            int indexId = Integer.parseInt(request.getParameter("indexId"));

            dao.updatePlayer(id, name, fullName, age, indexId);
        } else {
            String name = request.getParameter("name");
            String fullName = request.getParameter("fullName");
            String age = request.getParameter("age");
            int indexId = Integer.parseInt(request.getParameter("indexId"));

            dao.insertPlayer(name, fullName, age, indexId);
        }
        response.sendRedirect("players");
    }
}