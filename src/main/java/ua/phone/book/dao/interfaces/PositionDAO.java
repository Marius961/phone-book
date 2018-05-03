package ua.phone.book.dao.interfaces;

import ua.phone.book.models.Position;

import java.util.List;

public interface PositionDAO {

    List<Position> getAllPositions();

    Position getPositionById(int id);

    int insertPosition(Position position);

    void updatePosition(Position position);

    void deletePosition(int id);
}
