package ehb.finalwork.manager.dao;

import com.rethinkdb.net.Cursor;
import ehb.finalwork.manager.dto.RethinkBeaconDto;
import ehb.finalwork.manager.error.CustomNotFoundWebSocketException;
import ehb.finalwork.manager.error.TooManyReturnValuesWebSocketException;
import ehb.finalwork.manager.model.Beacon;

public class BeaconDaoImpl extends BaseDaoImpl<Beacon, RethinkBeaconDto> implements BeaconDao {

    BeaconDaoImpl(Beacon entity) {
        super(entity);
    }

    @Override
    public Beacon getByMajorMinor(String major, String minor, String privateChannel) throws TooManyReturnValuesWebSocketException, CustomNotFoundWebSocketException {
        Cursor<Beacon> cursor = r.db("manager")
                .table("beacon")
                .filter(
                        row -> row.g("major").eq(Long.parseLong(major))
                                  .and(row.g("minor").eq(Long.parseLong(minor)))
                )
                .run(connectionFactory.createConnection(), Beacon.class);

        if (cursor.bufferedSize() == 1) {
            return cursor.toList().get(0);
        }
        else if (cursor.bufferedSize() > 1) {
            throw new TooManyReturnValuesWebSocketException("/beacon/" + privateChannel + "/getByMajorMinor", "Too Many Values");
        }

        throw new CustomNotFoundWebSocketException("/beacon/" + privateChannel + "/getByMajorMinor", "Not Found");
    }
}
