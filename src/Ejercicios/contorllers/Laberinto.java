package Ejercicios.contorllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Ejercicios.models.Celda;

/*
 * Un jugador está en la esquina superior izquierda (0,0) de un tablero m x n. En el tablero hay celdas
 * transitables (true) y no transitables (false). Encuentra un camino válido para ir a la esquina
 * inferior izquierda con el jugador, sabiendo que solo se puede mover hacia abajo y hacia la derecha.
 *
 * Ejemplo 1:
 *  Input:
 *    [
 *      [true,true,true,true]
 *      [false,false,false,true]
 *      [true,true,false,true]
 *      [true,true,false,true]
 *    ]
 *
 *  Output: [(0,0), (0,1), (0,2), (0,3), (1,3), (2,3), (3,3)]
 *
 * Ejemplo 2:
 *  Input:
 *    [
 *      [true,true,true,true]
 *      [false,true,true,true]
 *      [true,true,false,false]
 *      [true,true,true,true]
 *    ]
 *
 *  Output: [(0,0), (0,1), (1,1), (2,1), (3,1), (3,2), (3,3)]
 *
 */
public class Laberinto {

    public List<Celda> getPath(boolean[][] grid) {
        List<Celda> path = new ArrayList<>();
        Map<String, Boolean> cache = new HashMap<>();
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return path;
        }
        if (findPath(grid, 0, 0, path, cache)) {
            return path;
        }
        return path;
    }

    private boolean findPath(boolean[][] grid, int row, int col, List<Celda> path, Map<String, Boolean> cache) {
        if (row >= grid.length || col >= grid[0].length || !grid[row][col]) {
            return false;
        }

        String key = row + "," + col;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        Celda currentCell = new Celda(row, col);
        path.add(currentCell); 

        if (row == grid.length - 1 && col == grid[0].length - 1) {
            cache.put(key, true);
            return true;
        }

        if (findPath(grid, row, col + 1, path, cache)) {
            cache.put(key, true);
            return true;
        }

        if (findPath(grid, row + 1, col, path, cache)) {
            cache.put(key, true);
            return true;
        }

        path.remove(path.size() - 1);
        cache.put(key, false);
        return false;
    }
}